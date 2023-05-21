package ro.ubbcluj.app.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.ubbcluj.app.domain.user.User;
import ro.ubbcluj.app.domain.dto.AutheticationRequest;
import ro.ubbcluj.app.service.JwtTokenService;
import ro.ubbcluj.app.service.UserService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/login")
public class LoginController {

    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    public LoginController(UserService userService, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }


    @PostMapping()
    public ResponseEntity<?> login(@RequestBody AutheticationRequest autheticationRequest){
        User user = userService.login(autheticationRequest.getUsername(), autheticationRequest.getPassword());
        String jwtToken = jwtTokenService.generateToken(user);
        if(user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return ResponseEntity.ok(jwtToken);
    }
}
