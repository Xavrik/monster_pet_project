package com.xanaxna.Rest.API.example.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


//@Service
@Configuration
public class KafkaProducer {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;


    //    @Autowired
//    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaConfiguration kafkaConfiguration;

    @Autowired
    public KafkaProducer(
//            KafkaTemplate<String, String> kafkaTemplate,
            KafkaConfiguration kafkaConfiguration) {
//        this.kafkaTemplate = kafkaTemplate;
        this.kafkaConfiguration = kafkaConfiguration;
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
//      ProducerFactory<String, String> producerFactory = this.producerFactory();
     return new KafkaTemplate<>(producerFactory());
    }


    public void sendMessage(String message){
       kafkaTemplate().send(kafkaConfiguration.topic().name(),"1",message);


//       kafkaTemplate(kafkaConfiguration.topic().name(),message)
//     kafkaTemplate.send(kafkaConfiguration.topic().name(),message);
    }





}
