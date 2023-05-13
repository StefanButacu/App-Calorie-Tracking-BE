package ro.ubbcluj.app.domain.dto.foodDTOS;

import java.util.List;

public class DiaryDayMealFoodDTO {

    private String diaryDay;
    private List<MealFoodDTO> mealDTOList;

    public DiaryDayMealFoodDTO() {
    }

    public DiaryDayMealFoodDTO(String diaryDay, List<MealFoodDTO> mealDTOList) {
        this.diaryDay = diaryDay;
        this.mealDTOList = mealDTOList;
    }

    public String getDiaryDay() {
        return diaryDay;
    }

    public void setDiaryDay(String diaryDay) {
        this.diaryDay = diaryDay;
    }

    public List<MealFoodDTO> getMealDTOList() {
        return mealDTOList;
    }

    public void setMealDTOList(List<MealFoodDTO> mealDTOList) {
        this.mealDTOList = mealDTOList;
    }
}
