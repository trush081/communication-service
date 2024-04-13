package com.trentonrush.communicationservice.repositories;

import com.trentonrush.communicationservice.models.Communication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationRepository extends MongoRepository<Communication, String> {
}
