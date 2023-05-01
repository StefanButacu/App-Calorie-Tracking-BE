package ro.ubbcluj.app.service;

import org.springframework.stereotype.Service;
import ro.ubbcluj.app.domain.*;
import ro.ubbcluj.app.domain.dto.DiaryDayMealFoodDTO;
import ro.ubbcluj.app.domain.dto.FoodWithCalorieDTO;
import ro.ubbcluj.app.domain.dto.MealFoodDTO;
import ro.ubbcluj.app.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiaryDayService {
    private final DiaryDayRepository diaryDayRepository;
    private final MealRepository mealRepository;
    private final FoodRepository foodRepository;
    private final FoodMealRepository foodMealRepository;

    private final FoodService foodService;

    public DiaryDayService(DiaryDayRepository diaryDayRepository, MealRepository mealRepository, FoodRepository foodRepository, FoodMealRepository foodMealRepository, FoodService foodService) {
        this.diaryDayRepository = diaryDayRepository;
        this.mealRepository = mealRepository;
        this.foodRepository = foodRepository;
        this.foodMealRepository = foodMealRepository;
        this.foodService = foodService;
    }

    public DiaryDayMealFoodDTO getDiaryDayMealFoodDTOForDay(LocalDate dayDate) {
        DiaryDayMealFoodDTO diaryDayMealFoodDTO = new DiaryDayMealFoodDTO();
        Optional<DiaryDay> diaryDayByDay = diaryDayRepository.getDiaryDayByDay(dayDate);
        if (diaryDayByDay.isPresent()) {
            // get meals for diary
            DiaryDay diaryDay = diaryDayByDay.get();
            List<Meal> meals = mealRepository.getMealsFromDiary(diaryDay.getId());
            List<MealFoodDTO> mealFoodDTOS = new ArrayList<>();
            meals.forEach(meal -> {
                List<FoodWithCalorieDTO> foodsForMeal = foodRepository.getFoodsForMeal(meal.getId())
                        .stream().map(food -> {
                            Double quantity = foodMealRepository.findById(new FoodMealId(food.getId(), meal.getId())).get().getQuantity();
                            return new FoodWithCalorieDTO(food.getId(), food.getName(), quantity, foodService.calculateCaloriesForFood(food.getId(), quantity));
                        }).toList();
                mealFoodDTOS.add(new MealFoodDTO(meal.getId(), meal.getName(), foodsForMeal));
            });
            diaryDayMealFoodDTO.setMealDTOList(mealFoodDTOS);
            diaryDayMealFoodDTO.setDiaryDay(diaryDay.getDay().toString());
            diaryDayMealFoodDTO.setDiaryDayId(diaryDay.getId());
            return diaryDayMealFoodDTO;
        }
        else
            return addDiaryDayByDate(dayDate);
    }



    public DiaryDayMealFoodDTO addDiaryDayByDate(LocalDate dayDate) {
        Optional<DiaryDay> diaryDayByDay = diaryDayRepository.getDiaryDayByDay(dayDate);
        if (diaryDayByDay.isEmpty()) {
            DiaryDay newDiaryDay = new DiaryDay(); // Factory get diary day based on user preference
            newDiaryDay.setDay(dayDate);
            // add meals
            Meal meal1 = new Meal(); meal1.setName("Breakfast") ; meal1.setDiaryDay(newDiaryDay);
            Meal meal2 = new Meal(); meal2.setName("Lunch"); meal2.setDiaryDay(newDiaryDay);
            Meal meal3 = new Meal(); meal3.setName("Dinner"); meal3.setDiaryDay(newDiaryDay);
            newDiaryDay.setMeals(List.of(meal1, meal2, meal3));
             diaryDayRepository.save(newDiaryDay);
        }
        return getDiaryDayMealFoodDTOForDay(dayDate);
    }

    public void addFoodToDiary( Long mealId, Long foodId, Double quantity) {
        // validate that foodId, mealId, diary day exists
        // update the quantity
        FoodMealId foodMealId = new FoodMealId(foodId, mealId);
        FoodMeal foodMeal = new FoodMeal(foodMealId, quantity);
        foodMealRepository.save(foodMeal);
    }
}
