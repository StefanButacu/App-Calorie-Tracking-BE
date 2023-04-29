package ro.ubbcluj.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@CrossOrigin

public class MealController {

    @PostMapping("/meal/{meal_id}/food")
    public ResponseEntity<?> saveFoodToMeal(@PathVariable("meal_id") Long mealId,@RequestBody Long foodId){

        // 1. Retrieve the meal from db
        // 2. Get the food with foodId from db
        // 3. Append to the Meal the new food
        // 4. Store Meal in db
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    // 1. Store meal in db
    // 2. Access the diary day.
    // 3. Store in db the meals for the day.

}
