// Controlador encargado de gestionar las operaciones relacionadas con los doctores

package edu.co.ustavillavicencio.quiz2.controllers;

import edu.co.ustavillavicencio.quiz2.controllers.dtos.doctor.DoctorCreateRequest;
import edu.co.ustavillavicencio.quiz2.controllers.dtos.doctor.DoctorResponse;
import edu.co.ustavillavicencio.quiz2.services.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponse> create(@Valid @RequestBody DoctorCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.create(request));
    }
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(doctorService.count());
    }
    @GetMapping
    public ResponseEntity<List<DoctorResponse>> findAll() {
        return ResponseEntity.ok(doctorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.findById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<DoctorResponse> myProfile() {
        return ResponseEntity.ok(doctorService.findMe());
    }
}
