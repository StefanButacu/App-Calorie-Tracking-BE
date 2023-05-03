package ro.ubbcluj.app.service;

import org.springframework.stereotype.Service;
import ro.ubbcluj.app.domain.Meal;
import ro.ubbcluj.app.repository.MealRepository;

@Service
public class MealService {

    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public Meal findMealById(Long mealId) {
        return mealRepository.findById(mealId).orElse(null);
    }
}
