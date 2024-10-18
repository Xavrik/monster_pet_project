package com.xanaxna.Rest.API.example.controller;


import com.xanaxna.Rest.API.example.entity.Dog;
import com.xanaxna.Rest.API.example.repository.DogRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dogs")
@RequiredArgsConstructor
public class DogController {
    private final DogRepo dogRepo;


    @GetMapping("/all")
    public List<Dog> getAllDogs() {
        return dogRepo.findAll();
    }

    @GetMapping
    public ResponseEntity<Dog> getDogByName(@RequestParam String name) {
        var dog =  dogRepo.findByName(name);
        if(dog == null) {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
        return new ResponseEntity<>(dog, HttpStatus.OK);
    }

//    @GetMapping("/")
//    public List<Dog> getAllDogByName(@RequestParam String name) {
//        var dog =  dogRepo.findByName(name);
//        if(dog == null) {
//            ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
//            return null;
//        }
//        return Collections.singletonList(dogRepo.findAllByName(name));
//    }

    @PostMapping
    public Dog addDog(@RequestBody Dog dog) {

        if (dogRepo.findByNameAndAge(dog.getName(), dog.getAge()) != null) {
          return  dogRepo.findByName(dog.getName());
        }
        dog.setId(UUID.randomUUID());
        return dogRepo.save(dog);
    }

}


