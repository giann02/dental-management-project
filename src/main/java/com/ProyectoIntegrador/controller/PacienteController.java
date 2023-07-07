package com.ProyectoIntegrador.controller;


import com.ProyectoIntegrador.dto.PacienteDTO;
import com.ProyectoIntegrador.entity.Paciente;
import com.ProyectoIntegrador.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<HttpStatus> registrarNuevoPaciente(@RequestBody PacienteDTO paciente){
        pacienteService.agregarPaciente(paciente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<HttpStatus> actualizarPaciente(@RequestBody PacienteDTO paciente){
        pacienteService.actualizarPaciente(paciente);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping
    public ResponseEntity<List<PacienteDTO>> buscarPacientes(){
        var patients = pacienteService.listaPacientes();
        return new ResponseEntity<>(patients,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPacientePorId(@PathVariable Long id){
        var patient = pacienteService.buscarPaciente(id);
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) {
        pacienteService.borrarPaciente(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<PacienteDTO> buscarPacientePorEmail(@PathVariable String email){
        var patient = pacienteService.buscarPacienteByEmail(email);
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }

}
