package de.neuefische.java.hhjava242springweb.products;

import org.junit.jupiter.api.Test;

import javax.sound.sampled.Port;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    ProductRepository productRepository = mock(ProductRepository.class);
    ProductService productService = new ProductService(productRepository);

    @Test
    void getAllProducts() {
        //GIVEN
        Product p1 = new Product("1", "p1", 0.99);
        List<Product> products = List.of(p1);

        when(productRepository.findAll()).thenReturn(products);

        //WHEN
        List<Product> actual = productService.getAllProducts();

        //THEN
        verify(productRepository).findAll();
        assertEquals(List.of(p1), actual);
    }

    @Test
    void getProductById() {
        //GIVEN
        Product p1 = new Product("1", "p1", 0.99);

        when(productRepository.findById("1")).thenReturn(Optional.of(p1));

        //WHEN
        Product actual = productService.getProductById("1");

        //THEN
        verify(productRepository).findById("1");
        assertEquals(p1, actual);
    }
}
