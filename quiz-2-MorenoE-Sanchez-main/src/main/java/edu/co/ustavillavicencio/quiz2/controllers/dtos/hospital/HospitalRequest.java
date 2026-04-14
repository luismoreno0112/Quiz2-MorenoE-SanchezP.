package edu.co.ustavillavicencio.quiz2.controllers.dtos.hospital;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalRequest {

    @NotBlank(message = "El nombre del hospital es obligatorio")
    @Size(max = 120, message = "El nombre del hospital no puede superar 120 caracteres")
    private String name;
}
