package ro.ubbcluj.app.domain;

import jakarta.persistence.*;
import ro.ubbcluj.app.domain.user.User;

@Entity
@Table(name = "food_meal")
public class FoodMeal {

    @EmbeddedId
    private FoodMealId foodMealId;

    private Double quantity;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;


    public FoodMeal(FoodMealId foodMealId) {
        this.foodMealId = foodMealId;
    }

    public FoodMeal() {

    }

    public FoodMeal(FoodMealId foodMealId, Double quantity, User user) {
        this.foodMealId = foodMealId;
        this.quantity = quantity;
        this.user = user;
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

