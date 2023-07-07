package com.ProyectoIntegrador.controller;

import com.ProyectoIntegrador.dto.TurnoDTO;
import com.ProyectoIntegrador.entity.Odontologo;
import com.ProyectoIntegrador.entity.Paciente;
import com.ProyectoIntegrador.entity.Turno;
import com.ProyectoIntegrador.exception.BadRequestException;
import com.ProyectoIntegrador.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.service.OdontologoService;
import com.ProyectoIntegrador.service.PacienteService;
import com.ProyectoIntegrador.service.TurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/turnos")
public class TurnoController {
    private final TurnoService turnoService;

    @PostMapping
    public ResponseEntity<HttpStatus> registarTurno(@RequestBody TurnoDTO turno)  {
        turnoService.agregarTurno(turno);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<HttpStatus> actualizarTurno(@RequestBody TurnoDTO turno){
        turnoService.actualizarTurno(turno);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTurnos(){
        var shifts = turnoService.buscarTurnos();
        return new ResponseEntity<>(shifts,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarTurnoPorId(@PathVariable Long id){
        var shift = turnoService.buscarTurno(id);
        return new ResponseEntity<>(shift,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<TurnoDTO>> buscarTurnosPorFechaPacienteYOdontologo(@Param("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha, @Param("odontologoId") Long odontologoId, @Param("pacienteId") Long pacienteId ){
        var shifts = turnoService.buscarTurnosPorIdOdontologosYPacientesYFecha(odontologoId, pacienteId, fecha);
        return new ResponseEntity<>(shifts,HttpStatus.OK);
    }
}
