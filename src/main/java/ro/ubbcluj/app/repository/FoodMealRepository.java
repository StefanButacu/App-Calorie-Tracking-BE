package ro.ubbcluj.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubbcluj.app.domain.FoodMeal;
import ro.ubbcluj.app.domain.FoodMealId;

@Repository
public interface FoodMealRepository extends JpaRepository<FoodMeal, FoodMealId> {
}
