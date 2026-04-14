package edu.co.ustavillavicencio.quiz2.services;

import edu.co.ustavillavicencio.quiz2.controllers.dtos.doctor.DoctorCreateRequest;
import edu.co.ustavillavicencio.quiz2.controllers.dtos.doctor.DoctorResponse;
import edu.co.ustavillavicencio.quiz2.entities.Doctor;
import edu.co.ustavillavicencio.quiz2.entities.Hospital;
import edu.co.ustavillavicencio.quiz2.entities.UserApp;
import edu.co.ustavillavicencio.quiz2.exception.BusinessRuleException;
import edu.co.ustavillavicencio.quiz2.repositories.DoctorRepository;
import edu.co.ustavillavicencio.quiz2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final HospitalService hospitalService;

    public DoctorResponse create(DoctorCreateRequest request) {
        UserApp user = userRepository.findByUsername(request.getUsername().trim())
                .orElseThrow(() -> new BusinessRuleException("El usuario indicado no existe"));

        if (!"DOCTOR".equals(user.getRole())) {
            throw new BusinessRuleException("El usuario indicado no tiene rol DOCTOR");
        }

        if (doctorRepository.existsByUser(user)) {
            throw new BusinessRuleException("Ese usuario DOCTOR ya está asociado a un registro de doctor");
        }

        Hospital hospital = hospitalService.getEntity(request.getHospitalId());

        Doctor doctor = Doctor.builder()
                .user(user)
                .name(request.getName().trim())
                .lastName(request.getLastName().trim())
                .age(request.getAge())
                .specialization(request.getSpecialization().trim())
                .hospital(hospital)
                .build();

        return toResponse(doctorRepository.save(doctor));
    }

    public List<DoctorResponse> findAll() {
        return doctorRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }
    public long count() {
        return doctorRepository.count();
    }
    public DoctorResponse findById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Doctor no encontrado"));
        return toResponse(doctor);
    }

    public DoctorResponse findMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new BusinessRuleException("No hay un usuario autenticado");
        }

        Doctor doctor = doctorRepository.findByUserUsername(authentication.getName())
                .orElseThrow(() -> new BusinessRuleException("No existe un doctor asociado al usuario autenticado"));

        return toResponse(doctor);
    }

    private DoctorResponse toResponse(Doctor doctor) {
        return DoctorResponse.builder()
                .id(doctor.getId())
                .username(doctor.getUser().getUsername())
                .name(doctor.getName())
                .lastName(doctor.getLastName())
                .age(doctor.getAge())
                .specialization(doctor.getSpecialization())
                .hospitalId(doctor.getHospital().getId())
                .hospitalName(doctor.getHospital().getName())
                .build();
    }
}
