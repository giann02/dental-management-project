package com.ProyectoIntegrador.service;



import com.ProyectoIntegrador.dto.PacienteDTO;
import com.ProyectoIntegrador.entity.Odontologo;
import com.ProyectoIntegrador.entity.Paciente;
import com.ProyectoIntegrador.exception.BadRequestException;
import com.ProyectoIntegrador.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);
    private final PacienteRepository pacienteRepository;

    private final ObjectMapper mapper;


    public void agregarPaciente(PacienteDTO paciente){
        LOGGER.info("Se inicio una operacion de guardado del odontologo con apellido " + paciente.getApellido());

        var exists = pacienteRepository.findAll().stream().anyMatch(p -> p.getDni().equals(paciente.getDni()) );

        if (exists){
            throw new BadRequestException("The dentist already exists");
        }
        var patient = Paciente
                .builder()
                .nombre(paciente.getNombre())
                .apellido(paciente.getApellido())
                .dni(paciente.getDni())
                .fechaIngreso(LocalDate.now())
                .email(paciente.getEmail())
                .domicilio(paciente.getDomicilio())
                .build();
        pacienteRepository.save(patient);
    }

    public void actualizarPaciente(PacienteDTO paciente){
        LOGGER.info("Se inicio una operacion de actualizado de paciente con ID= " + paciente.getId());
        var patient = pacienteRepository.findById(paciente.getId()).orElseThrow(() -> new ResourceNotFoundException ("Patient with id: " + paciente.getId() + "does not exist"));
        var patientUpdated = Paciente
                .builder()
                .nombre(paciente.getNombre())
                .apellido(paciente.getApellido())
                .dni(paciente.getDni())
                .fechaIngreso(paciente.getFechaIngreso())
                .email(paciente.getEmail())
                .domicilio(paciente.getDomicilio())
                .build();

        pacienteRepository.save(patientUpdated);
    }

    public PacienteDTO buscarPaciente(Long id){
        LOGGER.info("Se inicio una operacion de busqueda de pacientes con ID " + id);
        return pacienteRepository.findById(id).map(patient -> mapper.convertValue(patient,PacienteDTO.class)).orElseThrow(() -> new ResourceNotFoundException ("Patient with id: " + id + "does not exist"));
    }

    public List<PacienteDTO> listaPacientes(){
        LOGGER.info("Se inicio una operacion de listado de pacientes ");
        var patients = pacienteRepository.findAll();
        if (patients.isEmpty()){
            throw new ResourceNotFoundException("The list is empty");
        }
        return patients.stream().map(patient -> mapper.convertValue(patient, PacienteDTO.class)).collect(Collectors.toList());
    }

    public void borrarPaciente(Long id) throws ResourceNotFoundException{
        LOGGER.warn("Se realizo una operacion de eliminado de odontologo con id " + id);
        pacienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException ("Patient with id: " + id + "does not exist"));
        pacienteRepository.deleteById(id);
    }

    public PacienteDTO buscarPacienteByEmail(String email){
        LOGGER.info("Se inicio una operacion de busqueda de pacientes con email " + email);
        var patient = pacienteRepository.findByEmail(email);
        if (patient.isEmpty()) {
            throw new ResourceNotFoundException("The email is not registered");
        }
        return patient.map(p -> mapper.convertValue(p, PacienteDTO.class)).get();
    }

}
