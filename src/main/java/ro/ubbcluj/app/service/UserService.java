package ro.ubbcluj.app.service;

import org.springframework.stereotype.Service;
import ro.ubbcluj.app.domain.User;
import ro.ubbcluj.app.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;


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
}
