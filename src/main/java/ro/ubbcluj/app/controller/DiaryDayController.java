package ro.ubbcluj.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubbcluj.app.domain.dto.DiaryDayMealFoodDTO;
import ro.ubbcluj.app.domain.dto.FoodQuantityDTO;
import ro.ubbcluj.app.service.DiaryDayService;
import ro.ubbcluj.app.service.JwtTokenService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/diary")
public class DiaryDayController {

    private final DiaryDayService diaryDayService;
    private final JwtTokenService jwtTokenService;

    @Autowired
    public DiaryDayController(DiaryDayService diaryDayService, JwtTokenService jwtTokenService) {
        this.diaryDayService = diaryDayService;
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping("{date}")
    public ResponseEntity<?> getDiaryDay(HttpServletRequest request, @PathVariable("date") String date) {
        String token = request.getHeader("Authorization");
        if (token == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Long userId = Long.parseLong(jwtTokenService.extractId(token.substring(7)));

        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        DiaryDayMealFoodDTO diaryDayMealFoodDTOForDay = diaryDayService.getDiaryDayMealFoodDTOForDay(localDate, userId);
        return new ResponseEntity<>(diaryDayMealFoodDTOForDay, HttpStatus.OK);
    }

    @PostMapping("{date}/meal/{meal_id}/food")
    public ResponseEntity<?> saveFoodToMeal(HttpServletRequest request,
                                            @PathVariable("meal_id") Long mealId, @PathVariable("date") String date, @RequestBody FoodQuantityDTO foodQuantityDTO) {

        String token = request.getHeader("Authorization");
        if (token == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Long userId = Long.parseLong(jwtTokenService.extractId(token.substring(7)));

        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        diaryDayService.addFoodToDiary(localDate, mealId, foodQuantityDTO.getFoodId(), foodQuantityDTO.getQuantity(), userId);
        // should return something DiaryDayMealFoodDTO maybe
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
