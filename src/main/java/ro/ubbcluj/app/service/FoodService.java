package ro.ubbcluj.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubbcluj.app.domain.Food;
import ro.ubbcluj.app.repository.FoodRepository;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food findFoodById(Long id) {
        return foodRepository.findById(id).orElse(null);

    }
}
