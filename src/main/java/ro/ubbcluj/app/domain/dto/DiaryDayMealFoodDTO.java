package ro.ubbcluj.app.domain.dto;

import java.util.List;

public class DiaryDayMealFoodDTO {

    private Long diaryDayId;
    private String diaryDay;
    private List<MealFoodDTO> mealDTOList;

    public DiaryDayMealFoodDTO() {
    }

    public DiaryDayMealFoodDTO(String diaryDay, List<MealFoodDTO> mealDTOList, Long diaryDayId) {
        this.diaryDayId = diaryDayId;
        this.diaryDay = diaryDay;
        this.mealDTOList = mealDTOList;
    }

    public Long getDiaryDayId() {
        return diaryDayId;
    }

    public void setDiaryDayId(Long diaryDayId) {
        this.diaryDayId = diaryDayId;
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
