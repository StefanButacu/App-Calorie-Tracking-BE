package ro.ubbcluj.app.service;

import org.springframework.stereotype.Service;
import ro.ubbcluj.app.domain.Food;
import ro.ubbcluj.app.domain.FoodMeal;
import ro.ubbcluj.app.domain.FoodMealId;
import ro.ubbcluj.app.domain.Meal;
import ro.ubbcluj.app.domain.dto.foodDTOS.DiaryDayMealFoodDTO;
import ro.ubbcluj.app.domain.dto.foodDTOS.FoodDetailsQuantityDTO;
import ro.ubbcluj.app.domain.dto.foodDTOS.FoodWithCalorieDTO;
import ro.ubbcluj.app.domain.dto.foodDTOS.MealFoodDTO;
import ro.ubbcluj.app.domain.user.User;
import ro.ubbcluj.app.repository.FoodMealRepository;
import ro.ubbcluj.app.repository.FoodRepository;
import ro.ubbcluj.app.repository.MealRepository;
import ro.ubbcluj.app.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiaryDayService {
    private final MealRepository mealRepository;
    private final FoodRepository foodRepository;
    private final FoodMealRepository foodMealRepository;

    private final UserRepository userRepository;
    private final FoodService foodService;

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
        List<Meal> allMeals = mealRepository.findAll();
        for (Meal dbMeal : allMeals) {
            MealFoodDTO mealFoodDTO = new MealFoodDTO();
            mealFoodDTO.setMealName(dbMeal.getName());
            mealFoodDTO.setMealId(dbMeal.getId());
            List<FoodWithCalorieDTO> foodWithCalorieDTOS = new ArrayList<>();
            for (FoodMeal foodMeal : foodMeals) {
                if (dbMeal.getId().equals(foodMeal.getFoodMealId().getMealId())) {
                    Optional<Food> foodById = foodRepository.findById(foodMeal.getFoodMealId().getFoodId());
                    if (foodById.isPresent()) {
                        Food food = foodById.get();
                        foodWithCalorieDTOS.add(new FoodWithCalorieDTO(food.getId(), food.getName(), foodMeal.getQuantity(), foodService.calculateCaloriesForFood(food.getId(), foodMeal.getQuantity())));
                    }
                }
            }
            mealFoodDTO.setFoodList(foodWithCalorieDTOS);
            mealFoodDTOS.add(mealFoodDTO);
        }
        dto.setMealDTOList(mealFoodDTOS);
        return dto;

    }

    public void addFoodToDiary(LocalDate dayDate, Long mealId, Long foodId, Double quantity, Long userId) {
        // update the quantity
        FoodMealId foodMealId = new FoodMealId(foodId, mealId, dayDate, userId);
        FoodMeal existingFood = foodMealRepository.findById(foodMealId).orElse(null);
        if (existingFood != null) {
            existingFood.setQuantity(existingFood.getQuantity() + quantity);
            foodMealRepository.save(existingFood);
        } else {
            Optional<User> userById = userRepository.findById(userId);
            if (userById.isPresent()) {
                User user = userById.get();
                FoodMeal foodMeal = new FoodMeal(foodMealId, quantity, user);
                foodMealRepository.save(foodMeal);
            }
        }
    }

    public void removeFoodFromMeal(LocalDate localDate, Long mealId, Long foodId, Long userId) {
        foodMealRepository.deleteById(new FoodMealId(foodId, mealId, localDate, userId));
    }

    public FoodDetailsQuantityDTO getFoodDetailsForMeal(LocalDate dayDate, Long mealId, Long foodId, Long userId) {
        FoodDetailsQuantityDTO dto = new FoodDetailsQuantityDTO();
        FoodMeal foodMeal = foodMealRepository.findById(new FoodMealId(foodId, mealId, dayDate, userId)).orElse(null);
        Food food = foodRepository.findById(foodId).orElse(null);
        if (foodMeal == null || food == null) {
            return null;
        }
        dto.setId(foodId);
        dto.setName(food.getName());
        dto.setQuantity(foodMeal.getQuantity());
        dto.setProteinPerCent(food.getProtein());
        dto.setCarbohydratePerCent(food.getCarbohydrate());
        dto.setLipidPerCent(food.getLipid());
        return dto;
    }

    public FoodMeal updateFoodToDiary(LocalDate dayDate, Long mealId, Long foodId, Double quantity, Long userId) {
        FoodMealId foodMealId = new FoodMealId(foodId, mealId, dayDate, userId);
        FoodMeal foodMeal = foodMealRepository.findById(foodMealId).orElse(null);
        if (foodMeal == null) return null;
        foodMeal.setQuantity(quantity);
        foodMealRepository.save(foodMeal);
        return foodMeal;
    }
}
