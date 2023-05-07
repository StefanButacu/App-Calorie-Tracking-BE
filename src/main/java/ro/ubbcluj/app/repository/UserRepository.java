package ro.ubbcluj.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubbcluj.app.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
}
