package ro.ubbcluj.app.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubbcluj.app.domain.Food;
import ro.ubbcluj.app.domain.dto.FoodDetailsDTO;
import ro.ubbcluj.app.service.FoodService;

@RestController
@CrossOrigin
@RequestMapping("/api/food")
public class FoodController {

    private final FoodService foodService;
    private final ModelMapper modelMapper;
    @Autowired
    public FoodController(FoodService foodService, ModelMapper modelMapper) {
        this.foodService = foodService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{food_id}")
    public ResponseEntity<?> findFoodById(@PathVariable("food_id") Long foodId){
        Food food = foodService.findFoodById(foodId);
        if (food == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(modelMapper.map(food, FoodDetailsDTO.class), HttpStatus.OK);
    }

}
