package de.neuefische.java.hhjava242springweb.products;

import de.neuefische.java.hhjava242springweb.exception.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public List<ProductLite> getProducts() {
        return productService.getAllProducts()
                .stream()
                .map(p -> new ProductLite(p.id(), p.title()))
                .toList();
    }

    @GetMapping("{productId}")
    public Product getProducts(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Product postProduct(@RequestBody NewProductDto newProduct) {
        Product productToSave = new Product(null, newProduct.title(), newProduct.price());

        return productService.saveProduct(productToSave);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNoSuchElementException(NoSuchElementException exception) {
        exception.printStackTrace();
        return new ApiError(exception.getMessage());
    }
}
