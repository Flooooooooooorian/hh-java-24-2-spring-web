package de.neuefische.java.hhjava242springweb;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public String getProduct(@RequestParam String test) {
        return "Products: " + test;
    }

    @GetMapping("{productId}")
    public String getProducts(@PathVariable String productId) {
        return "product: " + productId;
    }

    @PostMapping()
    public String postProduct(@RequestBody String productBody) {
        return "post: " + productBody;
    }
}
