package com.example.Gazprom.model;

import com.example.Gazprom.QueryRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Wiki {

    private static final ObjectMapper objectMapper = getObjectMapper();

    private static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return objectMapper;
    }

    public static void parse(String src, QueryRepository repository) throws JsonProcessingException {
        try (BufferedReader br = new BufferedReader(new FileReader(src))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count % 2 == 1) {
                    JsonNode node = objectMapper.readTree(line);
                    getQuote(node, repository);
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Quote> customGet(String category, MongoTemplate mongoTemplate) {
        Query query = new Query();
        category = category.substring(0,1).toUpperCase(Locale.ROOT) + category.substring(1).toLowerCase(Locale.ROOT);
        query.addCriteria(Criteria.where("category").is(category));
        List<Quote> quotes = mongoTemplate.find(query, Quote.class);
        return quotes;
    }

    private static void getQuote(JsonNode node, QueryRepository repository) {
        ArrayNode array = (ArrayNode) node.get("category");
        for (JsonNode jsonNode : array) {
            Quote q = new Quote(
                    node.get("wiki").asText(),
                    convertToDate(node.get("create_timestamp")),
                    convertToDate(node.get("timestamp")),
                    node.get("language").asText(),
                    jsonNode.asText(),
                    node.get("title").asText(),
                    convertToArray((ArrayNode) node.get("auxiliary_text")));
            repository.insert(q);
        }
    }

    public static List<String> convertToArray(ArrayNode array) {
        final List<String> result = new ArrayList<>();
        if (array == null) {
            return result;
        }
        array.forEach(jsonNode -> result.add(jsonNode.asText()));
        return result;
    }

    public static LocalDateTime convertToDate(JsonNode node) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        return LocalDateTime.parse(node.asText(), inputFormatter);
    }
}
