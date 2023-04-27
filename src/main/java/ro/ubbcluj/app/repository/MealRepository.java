package ro.ubbcluj.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.ubbcluj.app.domain.Meal;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query(value = "select m from Meal m " +
            "left join DiaryDayMeal ddm on ddm.diaryDayMealId.mealId = m.id " +
            "left join DiaryDay dd on ddm.diaryDayMealId.diaryDayId = dd.id " +
            "where dd.id = :diaryDayId " )
    List<Meal> getMealsFromDiary(@Param("diaryDayId") Long diaryDayId);

}
