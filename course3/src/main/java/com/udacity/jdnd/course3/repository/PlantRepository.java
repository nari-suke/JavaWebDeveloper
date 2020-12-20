package com.udacity.jdnd.course3.repository;

import com.udacity.jdnd.course3.data.Plant;
import org.hibernate.type.TrueFalseType;

import java.math.BigDecimal;
import java.util.List;

public class PlantRepository {
    public List<Plant> findByPriceLessThan(BigDecimal num) {
        return (List<Plant>) num;
    }

    public boolean deliveryCompleted(Long id) {
        return true;
    }
}
