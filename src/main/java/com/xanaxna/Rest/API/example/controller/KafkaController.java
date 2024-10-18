package com.xanaxna.Rest.API.example.controller;

import com.xanaxna.Rest.API.example.repository.CatRepo;
import com.xanaxna.Rest.API.example.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class KafkaController {

    @Autowired
private final KafkaProducer kafkaProducer;
private final CatRepo catRepo;

    public KafkaController(KafkaProducer kafkaProducer, CatRepo catRepo) {
        this.kafkaProducer = kafkaProducer;
        this.catRepo = catRepo;
    }


    @PostMapping("/kafka/send")
    public String sendToKafka(@RequestParam int id){
        var cat = catRepo.findById(id).orElseThrow();
        kafkaProducer.sendMessage( cat.toString());


        return "Good Cat";

    }



}
