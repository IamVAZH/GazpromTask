package com.example.Gazprom.controller;

import com.example.Gazprom.DAO.QuoteDAO;
import com.example.Gazprom.model.Quote;
import com.example.Gazprom.model.Wiki;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wiki")
public class RootController {
    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping(value = "/{title}")
    @ResponseStatus(HttpStatus.OK)
    public String getQuery(@PathVariable(value = "title") String title,
                           @RequestParam(value = "pretty", required = false) String requestParam) {
        List<Quote> list = Wiki.customGet(title, mongoTemplate);
        List<QuoteDAO> daoList = new ArrayList<>();
        list.forEach(q -> {
            daoList.add(new QuoteDAO(q.getWiki(),
                    q.getCreate_timestamp().atZone(ZoneId.systemDefault()).toEpochSecond(),
                    q.getTimestamp().atZone(ZoneId.systemDefault()).toEpochSecond(),
                    q.getLanguage(),
                    q.getCategory(),
                    q.getTitle(),
                    q.getAuxiliary_text()));
        });
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        String json = "";

        try {
            mapper.writeValue(out, daoList);
            if (requestParam != null) {
                json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(daoList);
            } else {
                json = out.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}
