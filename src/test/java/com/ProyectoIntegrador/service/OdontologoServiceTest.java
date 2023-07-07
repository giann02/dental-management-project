package com.ProyectoIntegrador.service;

import com.ProyectoIntegrador.dto.OdontologoDTO;

import com.ProyectoIntegrador.exception.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;


import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest


class OdontologoServiceTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologoTest(){
        OdontologoDTO odontologoAGuardar= new OdontologoDTO(1L,"A5","Gian","Luca");
        odontologoService.agregarOdontologo(odontologoAGuardar);
        var odontologoGuardado = odontologoService.buscarOdontologo(odontologoAGuardar.getId());
        assertEquals(1L,odontologoGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarOdontologoPorIdTest(){
        Long idABuscar=1L;
        OdontologoDTO odontologoBuscado=odontologoService.buscarOdontologo(idABuscar);
        assertNotNull(odontologoBuscado);
    }
    @Test
    @Order(3)
    public void buscarOdontologosTest(){
        var odontologos= odontologoService.listaOdontologos();
        Integer cantidadEsperada=1;
        assertEquals(cantidadEsperada,odontologos.size());
    }
    @Test
    @Order(4)
    public void actualizarOdontologoTest(){
        var odontologoAActualizar = new OdontologoDTO(1L,"A34","Gian","Panigatti");
        odontologoService.actualizarOdontologo(odontologoAActualizar);
        var odontologoActualizado= odontologoService.buscarOdontologo(odontologoAActualizar.getId());
        assertEquals("Gian",odontologoActualizado.getNombre());
    }
    @Test
    @Order(5)
    public void eliminarOdontologoTest() throws ResourceNotFoundException {
        Long idAEliminar = 1L;

        odontologoService.borrarOdontologo(idAEliminar);

        assertThrows(ResourceNotFoundException.class, () -> {
            var cantidadOdontologos = odontologoService.listaOdontologos();
            assertEquals(0, cantidadOdontologos.size());
        });

    }

}

