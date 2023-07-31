package com.ProyectoIntegrador.service;

import com.ProyectoIntegrador.entity.Usuario;
import com.ProyectoIntegrador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UsuarioService implements UserDetailsService {
    UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByEmail(username);
        return usuarioBuscado.orElseThrow(() -> new UsernameNotFoundException("Nombre de usuario no encontrado"));
    }

    public List<Usuario> buscarUsuarios(){
        return usuarioRepository.findAll();
    }
}
