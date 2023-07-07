package com.ProyectoIntegrador.repository;

import com.ProyectoIntegrador.dto.TurnoDTO;
import com.ProyectoIntegrador.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long> {


    @Query(value = "SELECT t FROM Turno t WHERE t.odontologo.id = :odontologoId AND t.odontologo.id = :pacienteId AND t.fecha = :fecha")
    List<Turno> findByOdontologoAndPacienteAndFecha(@Param("odontologoId") Long odontologoId,
                                                    @Param("pacienteId") Long pacienteId,
                                                    @Param("fecha") LocalDate fecha);
}
