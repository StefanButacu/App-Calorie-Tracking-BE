package ro.ubbcluj.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.ubbcluj.app.domain.Food;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

//    @Query(value = "select new ro.ubbcluj.app.domain.DTO.MealFoodDTO(m.id, m.name, f.id, " +
//            " f.name, f.protein, f.lipid, f.carbohydrate ) from Food f " +
//            "left join FoodMeal fm on fm.foodMealId.foodId = f.id " +
//            "left join Meal m on fm.foodMealId.mealId = m.id " +
//            "" )
//    List<MealFoodDTO> getFoodsForMeal(@Param("mealId") Long mealId);
    @Query(value = "select f from Food f " +
            "left join FoodMeal fm on fm.foodMealId.foodId = f.id " +
            "left join Meal m on fm.foodMealId.mealId = m.id " +
            "where m.id = :mealId " )
    List<Food> getFoodsForMeal(@Param("mealId") Long mealId);
    @Query("SELECT f FROM Food f WHERE lower(f.name) LIKE lower(concat('%', :foodName, '%'))")
    List<Food> findAllByName(@Param("foodName") String foodName);
}
