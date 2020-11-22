package com.example.udacity.entity0.entity;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "plant")
public class Flower extends Plant{
    private String color;

    /* getters and setters*/
}