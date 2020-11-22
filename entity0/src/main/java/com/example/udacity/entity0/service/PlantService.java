package com.example.udacity.entity0.service;

import com.example.udacity.entity0.entity.Plant;
import org.springframework.stereotype.Service;

@Service
public class PlantService {
    public Plant getPlantByName(String name){
        return new Plant();
    }
}