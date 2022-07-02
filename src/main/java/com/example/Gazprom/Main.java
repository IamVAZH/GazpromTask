package com.example.Gazprom;

import com.example.Gazprom.model.Wiki;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner runner(QueryRepository repository, MongoTemplate mongoTemplate) {
        return args -> {
            Wiki.parse(args[0], repository);
        };
    }
}