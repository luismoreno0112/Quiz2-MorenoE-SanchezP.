package edu.co.ustavillavicencio.quiz2.controllers.dtos.doctor;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorCreateRequest {

    @NotBlank(message = "El username del usuario es obligatorio")
    private String username;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 80, message = "El nombre no puede superar 80 caracteres")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 80, message = "El apellido no puede superar 80 caracteres")
    private String lastName;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "La edad mínima es 18")
    @Max(value = 100, message = "La edad máxima es 100")
    private Integer age;

    @NotBlank(message = "La especialización es obligatoria")
    @Size(max = 120, message = "La especialización no puede superar 120 caracteres")
    private String specialization;

    @NotNull(message = "El hospitalId es obligatorio")
    private Long hospitalId;
}
