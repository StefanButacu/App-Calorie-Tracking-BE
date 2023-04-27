package ro.ubbcluj.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ubbcluj.app.domain.DiaryDay;
import ro.ubbcluj.app.domain.Meal;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DiaryDayRepository extends JpaRepository<DiaryDay, Long> {

    Optional<DiaryDay> getDiaryDayByDay(LocalDate dayDate);
}
