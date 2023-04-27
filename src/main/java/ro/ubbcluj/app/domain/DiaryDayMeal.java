package ro.ubbcluj.app.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "diary_day_meal")
public class DiaryDayMeal {
    @EmbeddedId
    private DiaryDayMealId diaryDayMealId;

    public DiaryDayMeal() {
    }

    public DiaryDayMeal(DiaryDayMealId diaryDayMealId) {
        this.diaryDayMealId = diaryDayMealId;
    }
}
