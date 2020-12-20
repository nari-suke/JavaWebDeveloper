package com.udacity.jdnd.course3;

import com.udacity.jdnd.course3.data.Delivery;
import com.udacity.jdnd.course3.data.Plant;
import com.udacity.jdnd.course3.repository.PlantRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class Lesson3ExercisesApplicationTests {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PlantRepository plantRepository;

    @Test
    public void testPricelessThan(){
        //test boundary conditions
        Plant p = testEntityManager.persist(new Plant("Foo Leaf", 4.99));
        testEntityManager.persist(new Plant("Bar Weed", 5.01));

        List<Plant> cheapPlants = plantRepository.findByPriceLessThan(BigDecimal.valueOf(5));
        Assert.assertEquals(1, cheapPlants.size(), "Size");
        Assert.assertEquals(p.getId(), cheapPlants.get(0).getId(), "Id");
    }

    @Test
    public void setDeliveryCompleted(){
        Plant p =testEntityManager.persist(new Plant("Baz Root",9.99));
        Delivery d = testEntityManager.persist(new Delivery("Leonard Bernstein","234 West Side", LocalDateTime.now()));

        d.setPlants(Lists.newArrayList(p));
        p.setDelivery(d);
        Assert.assertFalse(plantRepository.deliveryCompleted(p.getId()));
        d.setCompleted(true);
        Assert.assertTrue(plantRepository.deliveryCompleted(p.getId()));
    }
}
