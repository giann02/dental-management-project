package com.ProyectoIntegrador.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate fecha;

    @ManyToOne()
    @JoinColumn(name="paciente_id",referencedColumnName = "id")
    @JsonIgnore
    private Paciente paciente;
    @ManyToOne()
    @JoinColumn(name = "odontologo_id", referencedColumnName = "id")
    @JsonIgnore
    private Odontologo odontologo;

}
