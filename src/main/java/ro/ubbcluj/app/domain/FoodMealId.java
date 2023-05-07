package ro.ubbcluj.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class FoodMealId implements Serializable {
    @Column(name = "foodId")
    private Long foodId;
    @Column(name = "mealId")
    private Long mealId;

    @Column(name = "diaryDay")
    private LocalDate diaryDay;

    @Column(name = "userId")
    private Long userId;

    public FoodMealId() {
    }

    public FoodMealId(Long foodId, Long mealId, LocalDate diaryDay, Long userId) {
        this.foodId = foodId;
        this.mealId = mealId;
        this.diaryDay = diaryDay;
        this.userId = userId;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public LocalDate getDiaryDay() {
        return diaryDay;
    }

    public void setDiaryDay(LocalDate diaryDay) {
        this.diaryDay = diaryDay;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
