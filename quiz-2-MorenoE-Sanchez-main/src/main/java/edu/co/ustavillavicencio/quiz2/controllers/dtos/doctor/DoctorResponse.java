package edu.co.ustavillavicencio.quiz2.controllers.dtos.doctor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DoctorResponse {
    private Long id;
    private String username;
    private String name;
    private String lastName;
    private Integer age;
    private String specialization;
    private Long hospitalId;
    private String hospitalName;
}
