package com.xanaxna.Rest.API.example.controller;

import com.xanaxna.Rest.API.example.DTO.AuthDTO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Setter
@RestController
public class AuthController {

    @Value("${client-id}")
    private String clientId;

    @Value("${resource-url}")
    private String resourceServerUrl;

    @Value("${grant-type}")
    private String grantType;


//    @PostMapping("/auth")
    @GetMapping("/auth")
    public String auth(@RequestBody AuthDTO authDTO){
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        var body = "client_id=" + clientId +
                "&username=" + authDTO.login() +
                "&password=" + authDTO.password()+
                "&grant_type=" + grantType;

        var requestEntity = new HttpEntity<>(body, headers);
        var restTemplate = new RestTemplate();
        var response = restTemplate.exchange(
                resourceServerUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        if(response.getStatusCode().value() == 200){
            return response.getBody();
        }
        return null;

    }



}
