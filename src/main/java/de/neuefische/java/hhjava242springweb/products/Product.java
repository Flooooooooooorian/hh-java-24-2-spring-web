package de.neuefische.java.hhjava242springweb.products;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
public record Product(
        @Id
        String id,
        String title,
        double price
) {
}
