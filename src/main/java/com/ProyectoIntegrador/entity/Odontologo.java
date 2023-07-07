package com.ProyectoIntegrador.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "odontologos")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String matricula;
    @Column
    private String nombre;
    @Column
    private String apellido;

    @JsonIgnore
    @OneToMany(mappedBy = "odontologo")
    private Set<Turno> turnos = new HashSet<>();



}
