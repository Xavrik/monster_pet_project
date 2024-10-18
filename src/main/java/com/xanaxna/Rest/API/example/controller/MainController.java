package com.xanaxna.Rest.API.example.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xanaxna.Rest.API.example.DTO.CatDTO;
import com.xanaxna.Rest.API.example.entity.Cat;
import com.xanaxna.Rest.API.example.repository.CatRepo;
import com.xanaxna.Rest.API.example.service.MailSenderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name= "main_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private final CatRepo catRepo;
    private final MailSenderService mailSenderService;

    //TODO 01 Add description to swagger for all method
    @Operation(
            summary = "Adding nw cat to Database",
            description = "Take cat DTO , build, and save to Database"
    )
    @PostMapping("api/add")
    public void addCat(@RequestBody CatDTO catDTO){

        log.info("New row: " + catRepo.save(
                Cat.builder()

                    .age(catDTO.getAge())
                    .weight(catDTO.getWeight())
                    .name(catDTO.getName())
                    .build())
        );

    }

    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getAll(){
        return catRepo.findAll();
    }

    @GetMapping("/api")
    public Cat getCat(@RequestParam int id){
        return catRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id){
        catRepo.deleteById(id);
    }


    //ToDO 02 Need refactor method, change CatRepo to CatDTO
    @PutMapping("/api/update")
    public String changeCat(@RequestBody Cat cat){
        if (!catRepo.existsById(cat.getId())){
            return "Not find cat";
        }
        return catRepo.save(cat).toString();
    }

    @GetMapping("/hello")
    public void sayHellobyMail(@RequestParam int id){
        var cat = catRepo.findById(id).orElseThrow();
        mailSenderService.sendMail(
                "email",
                "Hello ",
                "Hello my name is " + cat.getName() + " " + cat.getAge() + " " + cat.getWeight()
        );
    }


}
