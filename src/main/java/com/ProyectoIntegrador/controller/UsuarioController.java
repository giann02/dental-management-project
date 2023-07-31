package com.ProyectoIntegrador.controller;


import com.ProyectoIntegrador.dto.TurnoDTO;
import com.ProyectoIntegrador.entity.Usuario;
import com.ProyectoIntegrador.service.UsuarioService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> buscarTurnos(){
        return usuarioService.buscarUsuarios();
    }

}
