package com.ProyectoIntegrador.service;

import com.ProyectoIntegrador.dto.OdontologoDTO;
import com.ProyectoIntegrador.entity.Odontologo;
import com.ProyectoIntegrador.exception.BadRequestException;
import com.ProyectoIntegrador.exception.ResourceNotFoundException;
import com.ProyectoIntegrador.repository.OdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class OdontologoService {

    private final OdontologoRepository odontologoRepository;

    private final ObjectMapper mapper;
    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);

    public void agregarOdontologo(OdontologoDTO odontologo){
        LOGGER.info("Se inicio una operacion de guardado del odontologo con apellido " + odontologo.getApellido());

        var dentists = odontologoRepository.findAll();


        var exist = dentists.stream().anyMatch(dentist -> dentist.getMatricula().equals(odontologo.getMatricula()));
        if (exist){
            throw new BadRequestException("The dentist already exists");
        }
        var dentist = Odontologo
                .builder()
                .matricula(odontologo.getMatricula())
                .nombre(odontologo.getNombre())
                .apellido(odontologo.getApellido())
                .build();
        odontologoRepository.save(dentist);
    }

    public void actualizarOdontologo(OdontologoDTO odontologo){
        LOGGER.info("Se inicio una operacion de actualizado de odontologo con ID= " + odontologo.getId());
        var dentist = odontologoRepository.findById(odontologo.getId()).orElseThrow(() -> new ResourceNotFoundException("Dentist with ID:" + odontologo.getId() + " does not exist "));
        var dentistUpdated = Odontologo
                .builder()
                .id(odontologo.getId())
                .matricula(odontologo.getMatricula())
                .nombre(odontologo.getNombre())
                .apellido(odontologo.getApellido())
                .build();
        odontologoRepository.save(dentistUpdated);
    }

    public OdontologoDTO buscarOdontologo(Long id){
        LOGGER.info("Se inicio una operacion de busqueda de odontologo con ID " + id);
        return odontologoRepository.findById(id)
                .map(dentist -> mapper.convertValue(dentist, OdontologoDTO.class)).orElseThrow(() -> new ResourceNotFoundException("Dentist with ID:" + id + " does not exist)"));
    }

    public List<OdontologoDTO> listaOdontologos(){
        LOGGER.info("Se inicio una operacion de listado de odontologos ");
        var dentists = odontologoRepository.findAll();
        if (dentists.isEmpty()){
            throw new ResourceNotFoundException("The list is empty");
        }

        return dentists.stream().map(dentist -> mapper.convertValue(dentist, OdontologoDTO.class)).collect(Collectors.toList());

    }

    public void borrarOdontologo(Long id) throws ResourceNotFoundException {
        odontologoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dentist with ID: " + id + " does not exist"));
        LOGGER.warn("Se realizo una operacion de eliminado de odontologo con id " + id);
        odontologoRepository.deleteById(id);


    }


}
