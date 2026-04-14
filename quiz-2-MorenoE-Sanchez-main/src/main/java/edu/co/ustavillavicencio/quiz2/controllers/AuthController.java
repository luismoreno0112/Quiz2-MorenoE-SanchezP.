package edu.co.ustavillavicencio.quiz2.controllers;

import edu.co.ustavillavicencio.quiz2.controllers.dtos.auth.AuthRequest;
import edu.co.ustavillavicencio.quiz2.controllers.dtos.auth.AuthResponse;
import edu.co.ustavillavicencio.quiz2.controllers.dtos.auth.SignupRequest;
import edu.co.ustavillavicencio.quiz2.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
