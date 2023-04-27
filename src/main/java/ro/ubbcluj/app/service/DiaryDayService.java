package ro.ubbcluj.app.service;

import org.springframework.stereotype.Service;
import ro.ubbcluj.app.domain.*;
import ro.ubbcluj.app.domain.dto.DiaryDayMealFoodDTO;
import ro.ubbcluj.app.domain.dto.MealFoodDTO;
import ro.ubbcluj.app.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiaryDayService {
    private final DiaryDayRepository diaryDayRepository;
    private final MealRepository mealRepository;
    private final DiaryDayMealRepository diaryDayMealRepository;
    private final FoodRepository foodRepository;
    private final FoodMealRepository foodMealRepository;

    public DiaryDayService(DiaryDayRepository diaryDayRepository, MealRepository mealRepository, DiaryDayMealRepository diaryDayMealRepository, FoodRepository foodRepository, FoodMealRepository foodMealRepository) {
        this.diaryDayRepository = diaryDayRepository;
        this.mealRepository = mealRepository;
        this.diaryDayMealRepository = diaryDayMealRepository;
        this.foodRepository = foodRepository;
        this.foodMealRepository = foodMealRepository;
    }

    public DiaryDayMealFoodDTO getDiaryDayMealFoodDTOForDay(LocalDate dayDate) {
        // get the diary day
        // for the day get the mealsDTO
        // for each mealDTO get the foodsDTO
        //
        // {
        //
        // }
        DiaryDayMealFoodDTO diaryDayMealFoodDTO = new DiaryDayMealFoodDTO();
        Optional<DiaryDay> diaryDayByDay = diaryDayRepository.getDiaryDayByDay(dayDate);
        if (diaryDayByDay.isPresent()) {
            // get meals for diary
            DiaryDay diaryDay = diaryDayByDay.get();
            List<Meal> meals = mealRepository.getMealsFromDiary(diaryDay.getId());
            List<MealFoodDTO> mealFoodDTOS = new ArrayList<>();
            meals.forEach(meal -> {
                List<Food> foodsForMeal = foodRepository.getFoodsForMeal(meal.getId());
                mealFoodDTOS.add(new MealFoodDTO(meal.getId(), meal.getName(), foodsForMeal));
            });
            diaryDayMealFoodDTO.setMealDTOList(mealFoodDTOS);
            diaryDayMealFoodDTO.setDiaryDay(diaryDay.getDay().toString());
            diaryDayMealFoodDTO.setDiaryDayId(diaryDay.getId());
        }
        return diaryDayMealFoodDTO;
    }

    public DiaryDayMealFoodDTO addDiaryDayByDate(LocalDate dayDate) {
        Optional<DiaryDay> diaryDayByDay = diaryDayRepository.getDiaryDayByDay(dayDate);
        if (diaryDayByDay.isEmpty()) {
            DiaryDay newDiaryDay = new DiaryDay(); // Factory get diary day based on user preference
            newDiaryDay.setDay(dayDate);
            // add meals
            Meal meal1 = new Meal(); meal1.setName("Breakfast");
            Meal meal2 = new Meal(); meal2.setName("Lunch");
            Meal meal3 = new Meal(); meal3.setName("Dinner");
            meal1 = mealRepository.save(meal1);
            meal2 = mealRepository.save(meal2);
            meal3 = mealRepository.save(meal3);
            newDiaryDay = diaryDayRepository.save(newDiaryDay);
            DiaryDayMealId diaryDayMealId1 = new DiaryDayMealId(newDiaryDay.getId(), meal1.getId());
            DiaryDayMealId diaryDayMealId2 = new DiaryDayMealId(newDiaryDay.getId(), meal2.getId());
            DiaryDayMealId diaryDayMealId3 = new DiaryDayMealId(newDiaryDay.getId(), meal3.getId());
            DiaryDayMeal diaryDayMeal1 = new DiaryDayMeal(diaryDayMealId1);
            DiaryDayMeal diaryDayMeal2 = new DiaryDayMeal(diaryDayMealId2);
            DiaryDayMeal diaryDayMeal3 = new DiaryDayMeal(diaryDayMealId3);
            diaryDayMealRepository.saveAll(List.of(diaryDayMeal1, diaryDayMeal2, diaryDayMeal3));
        }
        return getDiaryDayMealFoodDTOForDay(dayDate);
    }

    public void addFoodToDiary(Long diaryDayId, Long mealId, Long foodId, Double quantity) {
        // validate that foodId, mealId, diary day exists
        // update the quantity
        FoodMeal foodMeal = new FoodMeal(new FoodMealId(foodId, mealId), quantity);
        foodMealRepository.save(foodMeal);
    }
}
