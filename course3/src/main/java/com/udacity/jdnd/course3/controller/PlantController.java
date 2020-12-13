package com.udacity.jdnd.course3.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.course3.data.Plant;
import com.udacity.jdnd.course3.data.PlantDTO;
import com.udacity.jdnd.course3.service.PlantService;
import com.udacity.jdnd.course3.view.Views;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plant")
public class PlantController {

    @Autowired
    private PlantService plantService;

    public PlantDTO getPlantDTO(String name){
        //Plant plant =plantService.getPlantByName(name);
        return convertPlantDTO(plantService.getPlantByName(name));
    }

    @JsonView(Views.Public.class)
    public Plant getFilteredPlant(String name){
        return plantService.getPlantByName(name);
    }

    private PlantDTO convertPlantDTO(Plant plant){
        PlantDTO plantDTO = new PlantDTO();
        BeanUtils.copyProperties(plant,plantDTO);
        return plantDTO;
    }

}
