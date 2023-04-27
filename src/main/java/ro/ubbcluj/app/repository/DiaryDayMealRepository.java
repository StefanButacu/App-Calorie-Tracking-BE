package ro.ubbcluj.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubbcluj.app.domain.DiaryDayMeal;
import ro.ubbcluj.app.domain.DiaryDayMealId;

@Repository
public interface DiaryDayMealRepository extends JpaRepository<DiaryDayMeal, DiaryDayMealId> {

}
