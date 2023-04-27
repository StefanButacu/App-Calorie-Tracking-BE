package ro.ubbcluj.app.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_meal")
public class FoodMeal {

    @EmbeddedId
    private FoodMealId foodMealId;

    private Double quantity;
    public FoodMeal(FoodMealId foodMealId) {
        this.foodMealId = foodMealId;
    }

    public FoodMeal() {

    }

    public FoodMeal(FoodMealId foodMealId, Double quantity) {
        this.foodMealId = foodMealId;
        this.quantity = quantity;
    }

    public FoodMealId getFoodMealId() {
        return foodMealId;
    }

    public void setFoodMealId(FoodMealId foodMealId) {
        this.foodMealId = foodMealId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}

