package ro.ubbcluj.app.domain.dto;

import ro.ubbcluj.app.domain.Food;

import java.util.List;

public class MealFoodDTO {

    private Long mealId;
    private String mealName;

    private List<FoodWithCalorieDTO> foodList;

    public MealFoodDTO() {
    }

    public MealFoodDTO(Long mealId, String mealName, List<FoodWithCalorieDTO> foodList) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.foodList = foodList;
    }

    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public List<FoodWithCalorieDTO> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodWithCalorieDTO> foodList) {
        this.foodList = foodList;
    }
}
