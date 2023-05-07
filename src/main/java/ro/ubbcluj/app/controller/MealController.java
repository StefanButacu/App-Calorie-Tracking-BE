package ro.ubbcluj.app.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubbcluj.app.domain.Meal;
import ro.ubbcluj.app.domain.dto.MealDTO;
import ro.ubbcluj.app.service.MealService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/meal")
public class MealController {

    private final MealService mealService;
    private final ModelMapper modelMapper;

    public MealController(MealService mealService, ModelMapper modelMapper) {
        this.mealService = mealService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{meal_id}")
    public ResponseEntity<?> findMealById(@PathVariable("meal_id") Long mealId) {
        Meal meal = mealService.findMealById(mealId);
        if (meal == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(modelMapper.map(meal, MealDTO.class), HttpStatus.OK);
    }
}
