package ro.ubbcluj.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.ubbcluj.app.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
