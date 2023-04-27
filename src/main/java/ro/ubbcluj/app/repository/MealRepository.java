package ro.ubbcluj.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubbcluj.app.domain.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
}
