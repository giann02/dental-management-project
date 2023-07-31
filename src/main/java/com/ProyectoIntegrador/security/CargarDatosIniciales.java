package com.ProyectoIntegrador.security;

import com.ProyectoIntegrador.entity.Usuario;
import com.ProyectoIntegrador.entity.UsuarioRole;
import com.ProyectoIntegrador.repository.UsuarioRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargarDatosIniciales implements ApplicationRunner {
    private UsuarioRepository usuarioRepository;

    public CargarDatosIniciales(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passCifrada = bCryptPasswordEncoder.encode("1234");
        var cantidadDeUsuarios = usuarioRepository.findAll().size();
        if (cantidadDeUsuarios<=2){
            Usuario usuario = new Usuario("Gian","user","gianUsuarioNormal2@gmail.com",passCifrada, UsuarioRole.ROLE_USER);
            Usuario usuario2 = new Usuario("Gian","admin","gianUsuarioAdmin2@gmail.com",passCifrada, UsuarioRole.ROLE_ADMIN);
            usuarioRepository.save(usuario);
            usuarioRepository.save(usuario2);
        }

    }
}