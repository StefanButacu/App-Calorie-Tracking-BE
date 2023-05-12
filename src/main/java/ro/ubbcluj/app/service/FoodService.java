package ro.ubbcluj.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.ubbcluj.app.domain.CALORIE_VALUE;
import ro.ubbcluj.app.domain.Food;
import ro.ubbcluj.app.domain.dto.FoodDetailsDTO;
import ro.ubbcluj.app.repository.FoodRepository;

import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;
    private static final Double HUNDRED = 100.0;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food findFoodById(Long id) {
        return foodRepository.findById(id).orElse(null);
    }

    public Long calculateCaloriesForFood(Long foodId, Double quantity) {
        Food food = foodRepository.findById(foodId).orElse(null);
        if (food == null)
            return 0L;
        return (long) Math.floor(CALORIE_VALUE.PROTEIN.getCalorie() * food.getProtein() * quantity / HUNDRED +
                CALORIE_VALUE.CARBOHYDRATE.getCalorie() * food.getCarbohydrate() * quantity / HUNDRED +
                CALORIE_VALUE.LIPID.getCalorie() * food.getLipid() * quantity / HUNDRED);
    }

    public List<Food> getFoodsByName(String foodName) {
        return foodRepository.findAllByName(foodName);
    }

    public Page<Food> getFoodsByPage(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    public Food save(FoodDetailsDTO toSaveFood) {
        Food food = new Food();
        food.setName(toSaveFood.getName());
        food.setProtein(toSaveFood.getProtein());
        food.setCarbohydrate(toSaveFood.getCarbohydrate());
        food.setLipid(toSaveFood.getLipid());
        return foodRepository.save(food);
    }
}
