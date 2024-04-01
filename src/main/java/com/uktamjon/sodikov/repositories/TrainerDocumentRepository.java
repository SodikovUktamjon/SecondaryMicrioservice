package com.uktamjon.sodikov.repositories;

import com.uktamjon.sodikov.domains.mongoDbSummary.TrainerDocument;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface TrainerDocumentRepository extends MongoRepository<TrainerDocument, Integer> {
     TrainerDocument findByUsername(String username);

}