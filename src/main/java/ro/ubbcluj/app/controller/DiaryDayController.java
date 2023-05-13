package ro.ubbcluj.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubbcluj.app.domain.FoodMeal;
import ro.ubbcluj.app.domain.dto.foodDTOS.DiaryDayMealFoodDTO;
import ro.ubbcluj.app.domain.dto.foodDTOS.FoodDetailsQuantityDTO;
import ro.ubbcluj.app.domain.dto.foodDTOS.FoodQuantityDTO;
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
//        Long userId = Long.parseLong(jwtTokenService.extractId(token.substring(7)));
        Long userId = Long.valueOf(token);

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
//        Long userId = Long.parseLong(jwtTokenService.extractId(token.substring(7)));
        Long userId = Long.valueOf(token);

        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        diaryDayService.addFoodToDiary(localDate, mealId, foodQuantityDTO.getFoodId(), foodQuantityDTO.getQuantity(), userId);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/food")
    public ResponseEntity<?> deleteFoodFromMeal(HttpServletRequest request,
                                                @RequestParam(value = "date") String date,
                                                @RequestParam(value = "meal") Long mealId,
                                                @RequestParam(value = "food") Long foodId) {
        String token = request.getHeader("Authorization");
        if (token == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Long userId = Long.valueOf(token);
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        diaryDayService.removeFoodFromMeal(localDate, mealId, foodId, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/food")
    public ResponseEntity<?> getFoodDetailsFromMeal(HttpServletRequest request,
                                                    @RequestParam(value = "date") String date,
                                                    @RequestParam(value = "meal") Long mealId,
                                                    @RequestParam(value = "food") Long foodId) {
        String token = request.getHeader("Authorization");
        if (token == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        Long userId = Long.valueOf(token);
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        FoodDetailsQuantityDTO foodDetailsForMeal = diaryDayService.getFoodDetailsForMeal(localDate, mealId, foodId, userId);
        if (foodDetailsForMeal == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(foodDetailsForMeal, HttpStatus.OK);
    }

    @PutMapping("/food")
    public ResponseEntity<?> updateFoodToMeal(HttpServletRequest request,
                                              @RequestParam(value = "date") String date,
                                              @RequestParam(value = "meal") Long mealId,
                                              @RequestBody FoodQuantityDTO foodQuantityDTO) {

        String token = request.getHeader("Authorization");
        if (token == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        Long userId = Long.parseLong(jwtTokenService.extractId(token.substring(7)));
        Long userId = Long.valueOf(token);

        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        FoodMeal foodMeal = diaryDayService.updateFoodToDiary(localDate, mealId, foodQuantityDTO.getFoodId(), foodQuantityDTO.getQuantity(), userId);
        if (foodMeal == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(foodMeal, HttpStatus.ACCEPTED);
    }


}
