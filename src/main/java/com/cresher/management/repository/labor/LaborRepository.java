package com.cresher.management.repository.labor;


import com.cresher.management.model.Labor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaborRepository extends MongoRepository<Labor, String> {

}