package com.example.progettodb22.repository;

import com.example.progettodb22.entity.Città;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CittàRepository extends MongoRepository<Città, String> {

    Città findByComune(String Comune);
}
