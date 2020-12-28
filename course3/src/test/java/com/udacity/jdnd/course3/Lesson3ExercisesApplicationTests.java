package com.udacity.jdnd.course3;

import com.udacity.jdnd.course3.data.Delivery;
import com.udacity.jdnd.course3.data.Plant;
import com.udacity.jdnd.course3.repository.PlantRepository;
import com.udacity.jdnd.course3.repository.PlantRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;

@DataJpaTest
@RunWith(SpringRunner.class)
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
        Assertions.assertThat(cheapPlants.size()).isEqualTo(1);
        Assertions.assertThat(cheapPlants.get(0).getId()).isEqualTo(p.getId());
    }

    @Test
    public void setDeliveryCompleted(){
        Plant p =testEntityManager.persist(new Plant("Baz Root",9.99));
        Delivery d = testEntityManager.persist(new Delivery("Leonard Bernstein","234 West Side", LocalDateTime.now()));

        d.setPlants(Lists.newArrayList(p));
        p.setDelivery(d);

        d.setCompleted(false);
        Assert.assertFalse(plantRepository.deliveryCompleted(p.getId()));
        d.setCompleted(true);
        Assert.assertTrue(plantRepository.deliveryCompleted(p.getId()));
    }
}
