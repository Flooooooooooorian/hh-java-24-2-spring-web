package de.neuefische.java.hhjava242springweb.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Test
    @DirtiesContext
    public void getAllProducts() throws Exception {
        //GIVEN
        Product p1 = new Product("1", "apple", 0.99);
        productRepository.save(p1);

        //WHEN
        mockMvc.perform(get("/api/products"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                              "id": "1",
                              "title": "apple"
                            }
                        ]
                        """));
    }

    @Test
    @DirtiesContext
    public void postProduct() throws Exception {
        //GIVEN
        //WHEN
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "pineapple",
                                    "price": 3.99
                                }
                                """))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "title": "pineapple",
                            "price": 3.99
                        }
                        """))
                .andExpect(jsonPath("$.id").exists());


    }
}
