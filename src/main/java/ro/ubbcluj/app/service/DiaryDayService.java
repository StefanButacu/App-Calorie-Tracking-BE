package ro.ubbcluj.app.service;

import org.springframework.stereotype.Service;
import ro.ubbcluj.app.domain.*;
import ro.ubbcluj.app.domain.dto.DiaryDayMealFoodDTO;
import ro.ubbcluj.app.domain.dto.FoodWithCalorieDTO;
import ro.ubbcluj.app.domain.dto.MealFoodDTO;
import ro.ubbcluj.app.repository.FoodMealRepository;
import ro.ubbcluj.app.repository.FoodRepository;
import ro.ubbcluj.app.repository.MealRepository;
import ro.ubbcluj.app.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiaryDayService {
    private final MealRepository mealRepository;
    private final FoodRepository foodRepository;
    private final FoodMealRepository foodMealRepository;

    private final UserRepository userRepository;
    private final FoodService foodService;
    private final Long USER_ID = 1L;

    public DiaryDayService(MealRepository mealRepository, FoodRepository foodRepository, FoodMealRepository foodMealRepository, UserRepository userRepository, FoodService foodService) {
        this.mealRepository = mealRepository;
        this.foodRepository = foodRepository;
        this.foodMealRepository = foodMealRepository;
        this.userRepository = userRepository;
        this.foodService = foodService;
    }

    public DiaryDayMealFoodDTO getDiaryDayMealFoodDTOForDay(LocalDate dayDate, Long userId) {
        List<FoodMeal> foodMeals = foodMealRepository.getFoodMealsByDayAndUser(dayDate, userId);
        DiaryDayMealFoodDTO dto = new DiaryDayMealFoodDTO();
        dto.setDiaryDay(dayDate.toString());
        List<MealFoodDTO> mealFoodDTOS = new ArrayList<>();

        // can get all meals for this user
        // TODO - create a relation between user and meals
        // u1 - 0, 1, 2
        // u2 - 3, 4 , 5 ,6 7 - this one has 5 meals/day
        List<Meal> allMeals = mealRepository.findAll();
        for(Meal dbMeal: allMeals){
            MealFoodDTO mealFoodDTO = new MealFoodDTO();
            mealFoodDTO.setMealName(dbMeal.getName());
            mealFoodDTO.setMealId(dbMeal.getId());
            List<FoodWithCalorieDTO> foodWithCalorieDTOS = new ArrayList<>();
            for(FoodMeal foodMeal: foodMeals) {
                if(dbMeal.getId().equals(foodMeal.getFoodMealId().getMealId())) {
                    Food food = foodRepository.findById(foodMeal.getFoodMealId().getFoodId()).get();
                    foodWithCalorieDTOS.add( new FoodWithCalorieDTO(food.getId(), food.getName(), foodMeal.getQuantity(), foodService.calculateCaloriesForFood(food.getId(), foodMeal.getQuantity())));
                }
            }
            mealFoodDTO.setFoodList(foodWithCalorieDTOS);
            mealFoodDTOS.add(mealFoodDTO);
        }
        dto.setMealDTOList(mealFoodDTOS);
        return dto;

    }

    public void addFoodToDiary(LocalDate dayDate, Long mealId, Long foodId, Double quantity, Long userId) {
        // validate that foodId, mealId, diary day exists
        // update the quantity
        FoodMealId foodMealId = new FoodMealId(foodId, mealId, dayDate, userId);
        // get the User
        User user = userRepository.findById(USER_ID).get();

        FoodMeal foodMeal = new FoodMeal(foodMealId, quantity, user);
        foodMealRepository.save(foodMeal);
    }
}
