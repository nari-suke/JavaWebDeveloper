package com.udacity.jdnd.course1exercises.lesson2.exercise1;

/**
 * Food Service that performs business logic operations regarding food
 */

import org.springframework.stereotype.Service;

// ③@Serviceアノテーションを定義する。（@Componentでも可能だが、具体的な方がより良い）
// クラス内でRepositoryが定義されている。同様にDIが可能か確認する。
@Service
public class FoodService {
    private FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Boolean isFoodAvailableAtMealTime(String foodName, MealTime mealTime) {
        return foodRepository.getFood(mealTime).stream()
                .filter(food -> food.getName().equals(foodName))
                .findFirst()
                .isPresent();
    }

    public void addFood(String foodName, Integer calories, MealTime mealTime) {
        foodRepository.addFood(mealTime, new FoodData(foodName, calories));
    }

    public FoodRepository getFoodRepository() {
        return foodRepository;
    }

    public void setFoodRepository(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }
}
