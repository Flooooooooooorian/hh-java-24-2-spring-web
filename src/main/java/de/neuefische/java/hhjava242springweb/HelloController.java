package de.neuefische.java.hhjava242springweb;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hello")
public class HelloController {

    @GetMapping
    public String getHello() {
        return "Hello";
    }

    @GetMapping("/bye")
    public String getBye() {
        return "Bye";
    }

    @PostMapping
    public String postHello() {
        return "POST HELLO";
    }
}
