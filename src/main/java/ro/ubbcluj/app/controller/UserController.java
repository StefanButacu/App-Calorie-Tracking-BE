package ro.ubbcluj.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubbcluj.app.domain.dto.userDTOS.EnumDTO;
import ro.ubbcluj.app.domain.dto.userDTOS.UserDetailsDTO;
import ro.ubbcluj.app.domain.dto.userDTOS.UserFitnessRequestDTO;
import ro.ubbcluj.app.domain.dto.userDTOS.UserRegisterRequestDTO;
import ro.ubbcluj.app.domain.user.*;
import ro.ubbcluj.app.service.JwtTokenService;
import ro.ubbcluj.app.service.UserService;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    private final ModelMapper modelMapper;
    private final JwtTokenService jwtTokenService;

    public UserController(UserService userService, ModelMapper modelMapper, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<?> findById(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        User user = userService.findById(Long.valueOf(jwtTokenService.extractId(token)));
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
//        return new ResponseEntity(modelMapper.map(user, UserDetailsDTO.class), HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


    @PostMapping("/goals")
    public ResponseEntity<?> updateUserGoal(@RequestBody UserFitnessRequestDTO userFitnessRequestDTO) {
        userService.requestUserDietPlan(userFitnessRequestDTO);
        return null;
    }

    @GetMapping("/activity-levels")
    public ResponseEntity<List<EnumDTO>> getAllActivityLevels() {
        List<EnumDTO> activityLevels = Arrays.stream(ActivityLevel.values())
                .map(activityLevel ->
                        new EnumDTO(activityLevel.name(), activityLevel.getText()))
                .toList();
        return ResponseEntity.ok(activityLevels);
    }

    @GetMapping("/genders")
    public ResponseEntity<List<EnumDTO>> getAllGenders() {
        List<EnumDTO> genders = Arrays.stream(Gender.values())
                .map(gender -> new EnumDTO(gender.name(), gender.getText()))
                .toList();
        return ResponseEntity.ok(genders);
    }

    @GetMapping("/weight-goals")
    public ResponseEntity<List<EnumDTO>> getAllWeightGoals() {
        List<EnumDTO> weightGoals = Arrays.stream(WeightGoal.values())
                .map(weightGoal -> new EnumDTO(weightGoal.name(), weightGoal.getText()))
                .toList();
        return ResponseEntity.ok(weightGoals);
    }

    @GetMapping("/diet-types")
    public ResponseEntity<List<EnumDTO>> getAllDietTypes() {
        List<EnumDTO> dietTypes = Arrays.stream(DietType.values())
                .map(dietType -> new EnumDTO(dietType.name(), dietType.getText()))
                .toList();
        return ResponseEntity.ok(dietTypes);
    }
}
