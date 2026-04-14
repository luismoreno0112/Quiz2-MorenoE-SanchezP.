package edu.co.ustavillavicencio.quiz2.config;

import edu.co.ustavillavicencio.quiz2.entities.UserApp;
import edu.co.ustavillavicencio.quiz2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.countByRole("ADMIN") == 0) {
            UserApp admin = UserApp.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("Admin123*"))
                    .role("ADMIN")
                    .build();
            userRepository.save(admin);
        }
    }
}
