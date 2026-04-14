package edu.co.ustavillavicencio.quiz2.controllers.dtos.hospital;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class HospitalResponse {
    private Long id;
    private String name;
}
