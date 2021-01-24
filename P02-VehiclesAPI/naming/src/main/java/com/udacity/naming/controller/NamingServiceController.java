package com.udacity.naming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value="/prices")
public class NamingServiceController {
    @Autowired
    RestTemplate restTemplate;


    @GetMapping(value="/{carId}")
    public String getCarId(@PathVariable String carId){
        String response = restTemplate.exchange("http://pricing-service/prices/{carId}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, carId).getBody();

        return "Name -  " + carId + " \n Details " + response;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
