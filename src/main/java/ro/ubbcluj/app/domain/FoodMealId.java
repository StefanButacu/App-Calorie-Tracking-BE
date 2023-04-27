package ro.ubbcluj.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class FoodMealId implements Serializable {
    @Column(name = "foodId")
    private Long foodId;

    @Column(name = "mealId")
    private Long mealId;

    public FoodMealId() {
    }

    public FoodMealId(Long foodId, Long mealId) {
        this.foodId = foodId;
        this.mealId = mealId;
    }
}
