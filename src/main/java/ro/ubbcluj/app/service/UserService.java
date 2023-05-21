package ro.ubbcluj.app.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ro.ubbcluj.app.domain.dto.userDTOS.UserFitnessRequestDTO;
import ro.ubbcluj.app.domain.dto.userDTOS.UserRegisterRequestDTO;
import ro.ubbcluj.app.domain.user.DietPlanDTO;
import ro.ubbcluj.app.domain.user.User;
import ro.ubbcluj.app.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @Value(value = "${fitness.api.key}")
    private String FITNESS_API_KEY;

    @Value(value = "${fitness.api.host}")
    private String FITNESS_API_HOST;

    @Value(value = "${fitness.api.url}")
    private String FITNESS_API_URL;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(String username, String password) {
        User user = loadUserByUsername(username);
        if (user == null) {
            return null;
        }
        boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());
        if (isPasswordMatch) {
            return user;
        }
        return null;
    }

    public User loadUserByUsername(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public DietPlanDTO requestUserDietPlan(UserFitnessRequestDTO requestDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", FITNESS_API_KEY);
        headers.set("X-RapidAPI-Host", FITNESS_API_HOST);
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(FITNESS_API_URL)
                .queryParam("age", requestDTO.getAge())
                .queryParam("gender", requestDTO.getGender().getApiValue())
                .queryParam("height", requestDTO.getHeight())
                .queryParam("weight", requestDTO.getWeight())
                .queryParam("activitylevel", requestDTO.getActivityLevel().getApiValue())
                .queryParam("goal", requestDTO.getWeightGoal().getApiValue());

        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uriBuilder.build().toUri());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        // Process the response
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            JSONObject response = new JSONObject(responseEntity.getBody());
            JSONObject dietPlan = response.getJSONObject("data").getJSONObject(requestDTO.getDietType().getApiValue());
            Double calorie = Math.floor(response.getJSONObject("data").getDouble("calorie"));
            Double protein = Math.floor(dietPlan.getDouble("protein"));
            Double carbohydrate = Math.floor(dietPlan.getDouble("carbs"));
            Double lipids = Math.floor(dietPlan.getDouble("fat"));
            return new DietPlanDTO(calorie, protein, carbohydrate, lipids);
        } else {
            return new DietPlanDTO(2000.0, 137.0, 137.0, 100.0);
        }
    }

    public User registerUser(UserRegisterRequestDTO userRegisterRequestDTO) {
        DietPlanDTO dietPlan = requestUserDietPlan(userRegisterRequestDTO.getUserFitnessRequest());
        User user = createUser(userRegisterRequestDTO, userRegisterRequestDTO.getUserFitnessRequest(), dietPlan);
        user = userRepository.save(user);
        return user;
    }

    private User createUser(UserRegisterRequestDTO userRegisterRequestDTO, UserFitnessRequestDTO userFitnessRequestDTO, DietPlanDTO dietPlan) {
        User user = new User();
        // TODO - remove calorieGoal because we have protein, carbo, lipids grams
        user.setUsername(userRegisterRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterRequestDTO.getPassword()));
        user.setStartWeight(userRegisterRequestDTO.getStartWeight());
        user.setCurrentWeight(userFitnessRequestDTO.getWeight());
        user.setGoalWeight(userRegisterRequestDTO.getGoalWeight());
        user.setHeight(userFitnessRequestDTO.getHeight());
        ///
        user.setCalorieGoal(dietPlan.getCalorie());
        user.setProteinGoal(dietPlan.getProtein());
        user.setCarbohydrateGoal(dietPlan.getCarbs());
        user.setLipidGoal(dietPlan.getLipid());
        ///
        user.setActivityLevel(userFitnessRequestDTO.getActivityLevel());
        user.setGender(userFitnessRequestDTO.getGender());
        user.setWeightGoal(userFitnessRequestDTO.getWeightGoal());
        //
        return user;
    }


}
