package ro.ubbcluj.app.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ro.ubbcluj.app.domain.dto.userDTOS.UserFitnessRequest;
import ro.ubbcluj.app.domain.user.User;
import ro.ubbcluj.app.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Value(value = "${fitness.api.key}")
    private String FITNESS_API_KEY;

    @Value(value = "${fitness.api.host}")
    private String FITNESS_API_HOST;

    @Value(value = "${fitness.api.url}")
    private String FITNESS_API_URL;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String username, String password) {
        User user = loadUserByUsername(username);
        if (user == null) {
            // throw un-authroized something
            return null;
        }
        // TODO
        //  - something with encrypt
        if(user.getPassword().equals(password)) {
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

    public User registerUser(){

        UserFitnessRequest request = new UserFitnessRequest(25, "male", 180D, 70D, 5, "extremegain");

        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", FITNESS_API_KEY);
        headers.set("X-RapidAPI-Host", FITNESS_API_HOST);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request entity
        // Build query parameters using UriComponentsBuilder
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(FITNESS_API_URL)
                .queryParam("age", request.getAge())
                .queryParam("gender", request.getGender())
                .queryParam("height", request.getHeight())
                .queryParam("weight", request.getWeight())
                .queryParam("activitylevel", request.getActivityLevel())
                .queryParam("goal", request.getGoal());


        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uriBuilder.build().toUri());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);


        // Process the response
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            JSONObject response = new JSONObject(responseEntity.getBody());
            System.out.println(response);
        } else {
            System.out.println("Error: " + responseEntity.getStatusCode());
        }
        return null;
    }

}
