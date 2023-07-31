package com.ProyectoIntegrador.dto;

import com.ProyectoIntegrador.entity.Turno;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OdontologoDTO {
    private Long id;
    @NotBlank(message = "This field can't be empty")
    private String matricula;
    @NotBlank(message = "This field can't be empty")
    private String nombre;
    @NotBlank(message = "This field can't be empty")
    private String apellido;


}
