package ro.ubbcluj.app.service;

import org.springframework.stereotype.Service;
import ro.ubbcluj.app.domain.DiaryDay;
import ro.ubbcluj.app.domain.DiaryDayMeal;
import ro.ubbcluj.app.domain.DiaryDayMealId;
import ro.ubbcluj.app.domain.Meal;
import ro.ubbcluj.app.repository.DiaryDayMealRepository;
import ro.ubbcluj.app.repository.DiaryDayRepository;
import ro.ubbcluj.app.repository.MealRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DiaryDayService {

    private final DiaryDayRepository diaryDayRepository;
    private final MealRepository mealRepository;

    private final DiaryDayMealRepository diaryDayMealRepository;


    public DiaryDayService(DiaryDayRepository diaryDayRepository, MealRepository mealRepository, DiaryDayMealRepository diaryDayMealRepository) {
        this.diaryDayRepository = diaryDayRepository;
        this.mealRepository = mealRepository;
        this.diaryDayMealRepository = diaryDayMealRepository;
    }

    public DiaryDay getDiaryDayByDate(LocalDate dayDate) {
        Optional<DiaryDay> diaryDayByDay = diaryDayRepository.getDiaryDayByDay(dayDate);
        if(diaryDayByDay.isEmpty()){
            // store into db the day and return the inserted day
            DiaryDay newDiaryDay = new DiaryDay(); // Factory get diary day based on user preference
            // add meals
            Meal meal1 = new Meal();
            Meal meal2 = new Meal();
            Meal meal3 = new Meal();
            meal1 = mealRepository.save(meal1);
            meal2 = mealRepository.save(meal2);
            meal3 = mealRepository.save(meal3);
            newDiaryDay = diaryDayRepository.save(newDiaryDay);
            DiaryDayMealId diaryDayMealId1 = new DiaryDayMealId(newDiaryDay.getId(),meal1.getId());
            DiaryDayMealId diaryDayMealId2 = new DiaryDayMealId(newDiaryDay.getId(),meal2.getId());
            DiaryDayMealId diaryDayMealId3 = new DiaryDayMealId(newDiaryDay.getId(),meal3.getId());
            DiaryDayMeal diaryDayMeal1 = new DiaryDayMeal(diaryDayMealId1);
            DiaryDayMeal diaryDayMeal2 = new DiaryDayMeal(diaryDayMealId2);
            DiaryDayMeal diaryDayMeal3 = new DiaryDayMeal(diaryDayMealId3);
            diaryDayMealRepository.saveAll(List.of(diaryDayMeal1,diaryDayMeal2, diaryDayMeal3));
            return newDiaryDay;
            //
        }
        else return  diaryDayByDay.get();
    }
}
