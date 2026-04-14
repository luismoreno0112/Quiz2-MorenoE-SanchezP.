package edu.co.ustavillavicencio.quiz2.repositories;

import edu.co.ustavillavicencio.quiz2.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByUsername(String username);
    long countByRole(String role);
}
