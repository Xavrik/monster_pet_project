package com.xanaxna.Rest.API.example.repository;

import com.xanaxna.Rest.API.example.entity.Dog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface DogRepo extends MongoRepository<Dog, UUID> {

    Dog findByName(String name);
    Dog findByNameAndAge(String name, Integer age);
    Dog findAllByName(String name);

}
