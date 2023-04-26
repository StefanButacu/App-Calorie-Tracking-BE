package ro.ubbcluj.app.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_meal")
public class FoodMeal {

    @EmbeddedId
    private FoodMealId foodMealId;

    public FoodMeal(FoodMealId foodMealId) {
        this.foodMealId = foodMealId;
    }

    public FoodMeal() {

    }
}

