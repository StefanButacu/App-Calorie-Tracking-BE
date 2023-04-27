package ro.ubbcluj.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.ubbcluj.app.domain.dto.DiaryDayMealFoodDTO;
import ro.ubbcluj.app.service.DiaryDayService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@CrossOrigin
@Controller
@RequestMapping("/api/diary")
public class DiaryDayController {


    private final DiaryDayService diaryDayService;

    @Autowired
    public DiaryDayController(DiaryDayService diaryDayService) {
        this.diaryDayService = diaryDayService;
    }

    @PostMapping("/{date}")
    public ResponseEntity<DiaryDayMealFoodDTO> addDiaryDay( @PathVariable("date") String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        DiaryDayMealFoodDTO diaryDayByDate = diaryDayService.addDiaryDayByDate(localDate);
        return new ResponseEntity<>(diaryDayByDate, HttpStatus.OK);
    }
    @GetMapping("{date}")
    public ResponseEntity<DiaryDayMealFoodDTO> getDiaryDay( @PathVariable("date") String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        DiaryDayMealFoodDTO diaryDTO = diaryDayService.getDiaryDayMealFoodDTOForDay(localDate);
        return new ResponseEntity<>(diaryDTO, HttpStatus.OK);
    }
    @PostMapping("{diary_day_id}/meal/{meal_id}/food/{food_id}")
    public ResponseEntity<?> saveFoodToMeal(@PathVariable("diary_day_id") Long diaryDayId,
                                            @PathVariable("meal_id") Long mealId,
                                            @PathVariable("food_id") Long foodId){
        // can be Long diary day id
        // TODO Create FoodQuantityDTO
        Double quantity = 3.5;
        diaryDayService.addFoodToDiary(diaryDayId, mealId, foodId, quantity);
        // should return something DiaryDayMealFoodDTO maybe

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }
}
