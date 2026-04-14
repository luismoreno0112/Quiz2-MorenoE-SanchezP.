package edu.co.ustavillavicencio.quiz2.services;

import edu.co.ustavillavicencio.quiz2.config.jwt.JwtService;
import edu.co.ustavillavicencio.quiz2.controllers.dtos.auth.AuthRequest;
import edu.co.ustavillavicencio.quiz2.controllers.dtos.auth.AuthResponse;
import edu.co.ustavillavicencio.quiz2.controllers.dtos.auth.SignupRequest;
import edu.co.ustavillavicencio.quiz2.entities.UserApp;
import edu.co.ustavillavicencio.quiz2.exception.BusinessRuleException;
import edu.co.ustavillavicencio.quiz2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public void signup(SignupRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new BusinessRuleException("El username ya existe");
        }

        if ("ADMIN".equals(request.getRole()) && userRepository.countByRole("ADMIN") >= 1) {
            throw new BusinessRuleException("Ya existe un usuario ADMIN registrado");
        }

        UserApp user = UserApp.builder()
                .username(request.getUsername().trim())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtService.generateToken(userDetailsService.loadUserByUsername(request.getUsername()));
        return new AuthResponse(token);
    }
}
