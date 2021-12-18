package com.jeffrey.springboot.mongodb;


import com.jeffrey.springboot.Book;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MbookRepository extends MongoRepository<Mbook, Long> {
    List<Mbook> findByTitle(String title);
}
