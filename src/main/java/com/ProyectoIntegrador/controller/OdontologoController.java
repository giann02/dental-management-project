package com.ProyectoIntegrador.controller;

import com.ProyectoIntegrador.dto.OdontologoDTO;
import com.ProyectoIntegrador.entity.Odontologo;
import com.ProyectoIntegrador.entity.Paciente;
import com.ProyectoIntegrador.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.service.OdontologoService;
import com.ProyectoIntegrador.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/odontologos")
public class OdontologoController {
    private final OdontologoService odontologoService;


    @PostMapping
    public ResponseEntity<HttpStatus> registrarNuevoOdontologo(@RequestBody OdontologoDTO odontologo){
        odontologoService.agregarOdontologo(odontologo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<HttpStatus> actualizarOdontologo(@RequestBody OdontologoDTO odontologo){
        odontologoService.actualizarOdontologo(odontologo);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping
    public ResponseEntity<List<OdontologoDTO>> buscarOdontologo(){
        var dentists = odontologoService.listaOdontologos();
        return new ResponseEntity<>(dentists,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> buscarOdontologoPorId(@PathVariable Long id){
        var dentist = odontologoService.buscarOdontologo(id);
        return new ResponseEntity<>(dentist,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id){
        odontologoService.borrarOdontologo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
