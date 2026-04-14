package edu.co.ustavillavicencio.quiz2.controllers;

import edu.co.ustavillavicencio.quiz2.controllers.dtos.hospital.HospitalRequest;
import edu.co.ustavillavicencio.quiz2.controllers.dtos.hospital.HospitalResponse;
import edu.co.ustavillavicencio.quiz2.services.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping
    public ResponseEntity<HospitalResponse> create(@Valid @RequestBody HospitalRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<HospitalResponse>> findAll() {
        return ResponseEntity.ok(hospitalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(hospitalService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospitalResponse> update(@PathVariable Long id, @Valid @RequestBody HospitalRequest request) {
        return ResponseEntity.ok(hospitalService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hospitalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
