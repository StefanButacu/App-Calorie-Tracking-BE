package ro.ubbcluj.app.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class DiaryDayMealId implements Serializable {
    @Column(name = "diaryDayId")
    private Long diaryDayId;

    @Column(name = "mealId")
    private Long mealId;

    public DiaryDayMealId() {
    }

    public DiaryDayMealId(Long diaryDayId, Long mealId) {
        this.diaryDayId = diaryDayId;
        this.mealId = mealId;
    }
}
