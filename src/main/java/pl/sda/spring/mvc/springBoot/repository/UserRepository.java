package pl.sda.spring.mvc.springBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.spring.mvc.springBoot.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
