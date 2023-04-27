package ro.ubbcluj.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.ubbcluj.app.domain.DiaryDay;
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

    @GetMapping("/{date}")
    public ResponseEntity<?> addDiaryDay( @PathVariable("date") String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        DiaryDay diaryDayByDate = diaryDayService.getDiaryDayByDate(localDate);
        return new ResponseEntity<>(diaryDayByDate, HttpStatus.OK);
    }

    @PostMapping("{date}/meal/{meal_id}/food")
    public ResponseEntity<?> saveFoodToMeal(@PathVariable("date") String date,
                                            @PathVariable("meal_id") Long mealId,
                                            @RequestBody Long foodId){
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        // Retrieve day form db
        DiaryDay diaryDay = diaryDayService.getDiaryDayByDate(localDate);
        // Add the food to the meal


        // 1. Retrieve the meal from db
        // 2. Get the food with foodId from db
        // 3. Append to the Meal the new food
        // 4. Store Meal in db
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }
}
