package com.udacity.DogGraphQL.repository;


import com.udacity.DogGraphQL.entity.Dog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
