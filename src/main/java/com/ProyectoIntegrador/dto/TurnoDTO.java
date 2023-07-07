package com.ProyectoIntegrador.dto;

import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor

@NoArgsConstructor
@Builder
public class TurnoDTO {
    private Long id;
    private LocalDate fecha;
    private Long odontologo_id;
    private Long paciente_id;

}
