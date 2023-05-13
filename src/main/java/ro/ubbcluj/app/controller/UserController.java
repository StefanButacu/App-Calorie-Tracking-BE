package ro.ubbcluj.app.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubbcluj.app.domain.dto.userDTOS.UserDetailsDTO;
import ro.ubbcluj.app.domain.dto.userDTOS.UserFitnessRequestDTO;
import ro.ubbcluj.app.domain.dto.userDTOS.UserRegisterRequestDTO;
import ro.ubbcluj.app.domain.user.ActivityLevel;
import ro.ubbcluj.app.domain.user.Gender;
import ro.ubbcluj.app.domain.user.User;
import ro.ubbcluj.app.domain.user.WeightGoal;
import ro.ubbcluj.app.service.UserService;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> findById(@PathVariable("id") Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(modelMapper.map(user, UserDetailsDTO.class), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        User user = userService.registerUser(userRegisterRequestDTO);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(modelMapper.map(user, UserDetailsDTO.class), HttpStatus.OK);
    }


    @PostMapping("/goals")
    public ResponseEntity<?> updateUserGoal(@RequestBody UserFitnessRequestDTO userFitnessRequestDTO) {
        userService.requestUserDietPlan(userFitnessRequestDTO);
        return null;
    }

    @GetMapping("/activity-levels")
    public ResponseEntity<List<ActivityLevel>> getAllActivityLevels() {
        List<ActivityLevel> activityLevels = Arrays.stream(ActivityLevel.values()).toList();
        return ResponseEntity.ok(activityLevels);
    }
    @GetMapping("/genders")
    public ResponseEntity<List<Gender>> getAllGenders() {
        List<Gender> activityLevels = Arrays.stream(Gender.values()).toList();
        return ResponseEntity.ok(activityLevels);
    }
    @GetMapping("/weight-goals")
    public ResponseEntity<List<WeightGoal>> getAllWeightGoals() {
        List<WeightGoal> activityLevels = Arrays.stream(WeightGoal.values()).toList();
        return ResponseEntity.ok(activityLevels);
    }
}
