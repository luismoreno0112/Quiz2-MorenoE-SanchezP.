package edu.co.ustavillavicencio.quiz2.services;

import edu.co.ustavillavicencio.quiz2.controllers.dtos.hospital.HospitalRequest;
import edu.co.ustavillavicencio.quiz2.controllers.dtos.hospital.HospitalResponse;
import edu.co.ustavillavicencio.quiz2.entities.Hospital;
import edu.co.ustavillavicencio.quiz2.exception.BusinessRuleException;
import edu.co.ustavillavicencio.quiz2.repositories.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalResponse create(HospitalRequest request) {
        if (hospitalRepository.existsByNameIgnoreCase(request.getName().trim())) {
            throw new BusinessRuleException("Ya existe un hospital con ese nombre");
        }

        Hospital hospital = Hospital.builder()
                .name(request.getName().trim())
                .build();

        return toResponse(hospitalRepository.save(hospital));
    }

    public List<HospitalResponse> findAll() {
        return hospitalRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public HospitalResponse findById(Long id) {
        return toResponse(getEntity(id));
    }

    public HospitalResponse update(Long id, HospitalRequest request) {
        Hospital hospital = getEntity(id);

        hospitalRepository.findByNameIgnoreCase(request.getName().trim())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new BusinessRuleException("Ya existe otro hospital con ese nombre");
                });

        hospital.setName(request.getName().trim());
        return toResponse(hospitalRepository.save(hospital));
    }

    public void delete(Long id) {
        Hospital hospital = getEntity(id);
        hospitalRepository.delete(hospital);
    }

    public Hospital getEntity(Long id) {
        return hospitalRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Hospital no encontrado"));
    }

    private HospitalResponse toResponse(Hospital hospital) {
        return HospitalResponse.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .build();
    }
}
