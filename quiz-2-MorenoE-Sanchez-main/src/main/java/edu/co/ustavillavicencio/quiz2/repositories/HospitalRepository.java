package edu.co.ustavillavicencio.quiz2.repositories;

import edu.co.ustavillavicencio.quiz2.entities.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Optional<Hospital> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}
