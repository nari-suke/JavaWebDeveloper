package com.udacity.jdnd.course1exercises;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ①まず、Springbootアプリケーションであることを明示する。
// @SpringBootApplication = @Configuration + @EnableAutoConfiguration +
@SpringBootApplication
public class Course1ExercisesApplication {

	public static void main(String[] args) {
		SpringApplication.run(Course1ExercisesApplication.class, args);
	}

}
