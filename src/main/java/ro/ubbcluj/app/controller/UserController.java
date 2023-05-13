package ro.ubbcluj.app.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubbcluj.app.domain.user.User;
import ro.ubbcluj.app.domain.dto.userDTOS.UserDetailsDTO;
import ro.ubbcluj.app.service.UserService;

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
    public ResponseEntity<?> registerUser() {
        userService.registerUser();
        return null;
    }

}
