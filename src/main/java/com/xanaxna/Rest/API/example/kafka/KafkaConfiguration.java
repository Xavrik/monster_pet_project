package com.xanaxna.Rest.API.example.kafka;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;


@Component
public class KafkaConfiguration {



    String topic = "kafka_test";

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(topic)
                .partitions(1)
                .replicas(1)
                .compact()
                .build();

//        return new NewTopic(
//                "kafka_test",
//                1,
//                (short) 1
//        );
    }
}
