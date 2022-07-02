package com.example.Gazprom;

import com.example.Gazprom.model.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends MongoRepository<Quote, String> {
}
