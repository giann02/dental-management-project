package com.ProyectoIntegrador.service;

import com.ProyectoIntegrador.dto.TurnoDTO;
import com.ProyectoIntegrador.entity.Odontologo;
import com.ProyectoIntegrador.entity.Paciente;
import com.ProyectoIntegrador.entity.Turno;
import com.ProyectoIntegrador.exception.BadRequestException;
import com.ProyectoIntegrador.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.repository.OdontologoRepository;
import com.ProyectoIntegrador.repository.PacienteRepository;
import com.ProyectoIntegrador.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TurnoService {
    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);
    private final TurnoRepository turnoRepository;
    private final OdontologoRepository odontologoRepository;

    private final PacienteRepository pacienteRepository;

    private final ObjectMapper mapper;


    public void agregarTurno (TurnoDTO turno){
        LOGGER.info("Se inicio una operacion de guardado de turno con ID= " + turno.getId());
        var odontologo = odontologoRepository.findById(turno.getOdontologo_id())
                .orElseThrow(() -> new ResourceNotFoundException("The odontologo with id: " + turno.getOdontologo_id() + " does not exist"));

        var paciente = pacienteRepository.findById(turno.getPaciente_id())
                .orElseThrow(() -> new ResourceNotFoundException("The paciente with id: " + turno.getPaciente_id() + " does not exist"));
        if (turno.getFecha().isBefore(LocalDate.now())){
            throw new BadRequestException("Date invalid. You have to put a date after today.");
        }
        var shift = Turno
                .builder()
                .fecha(turno.getFecha())
                .odontologo(odontologo)
                .paciente(paciente)
                .build();

        turnoRepository.save(shift);

    }
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        turnoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The shift with id: " + id + "does not exist"));
        LOGGER.warn("Se inicio una operacion de eliminado de turno con ID= " + id);
        turnoRepository.deleteById(id);

    }
    public void actualizarTurno(TurnoDTO turno){
        LOGGER.info("Se inicio una operacion de actualizado de turno con ID= " + turno.getId());
        var shift = turnoRepository.findById(turno.getId()).orElseThrow(() -> new ResourceNotFoundException("The shift with id: " + turno.getId() + "does not exist"));

        var shiftUpdated = Turno
                .builder()
                .id(turno.getId())
                .fecha(turno.getFecha())
                .paciente(pacienteRepository.findById(turno.getPaciente_id()).get())
                .odontologo(odontologoRepository.findById(turno.getOdontologo_id()).get())
                .build();
        turnoRepository.save(shiftUpdated);
    }
    public TurnoDTO buscarTurno(Long id) {
        LOGGER.info("Se inició una operación de búsqueda de turno con ID " + id);

        return turnoRepository.findById(id)
                .map(turno -> {
                    var turnoDTO = mapper.convertValue(turno, TurnoDTO.class);
                    turnoDTO.setOdontologo_id(turno.getOdontologo().getId());
                    turnoDTO.setPaciente_id(turno.getPaciente().getId());
                    return turnoDTO;
                })
                .orElseThrow(() -> new ResourceNotFoundException("The shift with id: " + id + " does not exist"));
    }
    public List<TurnoDTO> buscarTurnos(){
        LOGGER.info("Se inicio una operacion de listado de turnos ");
       var turnos = turnoRepository.findAll();
       if (turnos.isEmpty()){
           throw new ResourceNotFoundException("The list of shifts is empty");
       }
       return turnos.stream().map(turno -> {
           var turnoDTO = mapper.convertValue(turno, TurnoDTO.class);
           turnoDTO.setPaciente_id(turno.getPaciente().getId());
           turnoDTO.setOdontologo_id(turno.getOdontologo().getId());
           return turnoDTO;
       }).collect(Collectors.toList());
    }

    public List<TurnoDTO> buscarTurnosPorIdOdontologosYPacientesYFecha(Long odontologoId, Long pacienteId, LocalDate fecha){
        var turnos = turnoRepository.findByOdontologoAndPacienteAndFecha(odontologoId,pacienteId,fecha);
        if (turnos.isEmpty()){
            throw new ResourceNotFoundException("The list of shifts is empty");
        }
        return turnos.stream().map(turno -> {
            var turnoDTO = mapper.convertValue(turno, TurnoDTO.class);
            turnoDTO.setOdontologo_id(turno.getOdontologo().getId());
            turnoDTO.setPaciente_id(turno.getPaciente().getId());
            return turnoDTO;
        }).collect(Collectors.toList());

    }


}
