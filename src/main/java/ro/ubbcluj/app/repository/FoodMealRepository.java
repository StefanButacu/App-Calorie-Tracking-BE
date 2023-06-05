package ro.ubbcluj.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.ubbcluj.app.domain.FoodMeal;
import ro.ubbcluj.app.domain.FoodMealId;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FoodMealRepository extends JpaRepository<FoodMeal, FoodMealId> {

    @Query("select fm from FoodMeal fm " +
            "left join Food f on fm.foodMealId.foodId = f.id " +
            "left join Meal m on fm.foodMealId.mealId = m.id " +
            "where fm.user.id = :userId and fm.foodMealId.diaryDay = :diaryDay ")
    List<FoodMeal> getFoodMealsByDayAndUser(@Param("diaryDay") LocalDate diaryDay, @Param("userId") Long userId);

}
