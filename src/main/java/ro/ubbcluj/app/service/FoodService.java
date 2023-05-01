package ro.ubbcluj.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubbcluj.app.domain.CALORIE_VALUE;
import ro.ubbcluj.app.domain.Food;
import ro.ubbcluj.app.repository.FoodRepository;

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
        if(food == null)
            return 0L;
        return (long) Math.floor(CALORIE_VALUE.PROTEIN.getCalorie() * food.getProtein() * quantity / HUNDRED +
                CALORIE_VALUE.CARBOHYDRATE.getCalorie() * food.getCarbohydrate() * quantity / HUNDRED +
                CALORIE_VALUE.LIPID.getCalorie() * food.getLipid() * quantity / HUNDRED);
    }
}
