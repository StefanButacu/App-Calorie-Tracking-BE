package ro.ubbcluj.app.domain.dto;

import ro.ubbcluj.app.domain.Food;

import java.util.List;

public class MealFoodDTO {

    private Long mealId;
    private String mealName;

    private List<Food> foodList;

    public MealFoodDTO() {
    }

    public MealFoodDTO(Long mealId, String mealName, List<Food> foodList) {
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

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
}
