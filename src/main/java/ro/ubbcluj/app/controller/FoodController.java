package ro.ubbcluj.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.ubbcluj.app.domain.Food;
import ro.ubbcluj.app.service.FoodService;

@Controller
@CrossOrigin
@RequestMapping("/api/food")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/{food_id}")
    public ResponseEntity<?> findFoodById(@PathVariable("food_id") Long foodId){
        Food food = foodService.findFoodById(foodId);
        if (food == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

}
