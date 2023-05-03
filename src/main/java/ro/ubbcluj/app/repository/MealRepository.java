package ro.ubbcluj.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.ubbcluj.app.domain.Meal;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query(value = "select d.meals from DiaryDay d " +
                "join d.meals where d.id = :diaryDayId")
    List<Meal> getMealsFromDiary(@Param("diaryDayId") Long diaryDayId);

}
