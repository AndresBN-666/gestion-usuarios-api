package com.andres.gestion_usuarios_api.serviceImpl;

import com.andres.gestion_usuarios_api.entity.UsuarioEntity;
import com.andres.gestion_usuarios_api.repository.UsuarioRepository;
import com.andres.gestion_usuarios_api.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public List<UsuarioEntity> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<UsuarioEntity> listarPorRol(String rol) {
        return Optional.empty();
    }
}
