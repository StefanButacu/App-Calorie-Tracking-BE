package ro.ubbcluj.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubbcluj.app.domain.Food;
import ro.ubbcluj.app.domain.dto.FoodDetailsDTO;
import ro.ubbcluj.app.service.FoodService;
import ro.ubbcluj.app.service.JwtTokenService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
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

    @GetMapping("/foods")
    public ResponseEntity<List<FoodDetailsDTO>> getFoods(@PageableDefault(size = 20) Pageable pageable) {
        Page<Food> foods;
        foods = foodService.getFoodsByPage(pageable);
        List<FoodDetailsDTO> foodDetailsDTOS = foods.map(food -> modelMapper.map(food, FoodDetailsDTO.class)).stream().toList();
        return ResponseEntity.ok(foodDetailsDTOS);
    }

    @GetMapping("/foods/search")
    public ResponseEntity<List<FoodDetailsDTO>> getFoodsByName(@RequestParam(value = "name") String foodName) {
        List<Food> foods = foodService.getFoodsByName(foodName);
        List<FoodDetailsDTO> foodDetailsDTOS = foods.stream().map(food -> modelMapper.map(food, FoodDetailsDTO.class)).toList();
        return ResponseEntity.ok(foodDetailsDTOS);
    }
}
