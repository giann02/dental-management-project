package com.ProyectoIntegrador.dto;

import com.ProyectoIntegrador.entity.Domicilio;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PacienteDTO {

    private Long id;
    @NotBlank(message = "Nombre field can't be empty")
    private String nombre;
    @NotBlank(message = "Apellido field can't be empty")
    private String apellido;
    @NotBlank(message = "Dni field can't be empty")
    private String dni;
    private LocalDate fechaIngreso;
    @NotBlank(message = "Email field can't be empty")
    private String email;
    private Domicilio domicilio;
}
