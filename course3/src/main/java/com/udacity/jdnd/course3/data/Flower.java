package com.udacity.jdnd.course3.data;

import javax.persistence.*;

@Entity
public class Flower extends Plant{
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}