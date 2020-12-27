package com.udacity.jdnd.course3.controller;

import com.udacity.jdnd.course3.RecipientAndPrice;
import com.udacity.jdnd.course3.data.Delivery;
import com.udacity.jdnd.course3.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    @PostMapping
    public Long scheduleDelivery(@RequestBody Delivery delivery) {
        return deliveryService.save(delivery);
    }

    @GetMapping("/bill/{deliverId}")
    public RecipientAndPrice getBill(@PathVariable Long deliverId){
        return deliveryService.getBill(deliverId);
    }
}