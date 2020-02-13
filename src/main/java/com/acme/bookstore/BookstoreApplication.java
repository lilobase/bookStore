package com.acme.bookstore;

import com.acme.bookstore.common.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.jackson.datatype.VavrModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

    @Autowired
    public void configJackson(ObjectMapper jackson) {
        jackson
                .registerModule(new VavrModule())
                .registerModule(new JavaTimeModule());
    }
}
