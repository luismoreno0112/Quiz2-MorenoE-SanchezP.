package edu.co.ustavillavicencio.quiz2.repositories;

import edu.co.ustavillavicencio.quiz2.entities.Doctor;
import edu.co.ustavillavicencio.quiz2.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    boolean existsByUser(UserApp user);
    Optional<Doctor> findByUserUsername(String username);
}
