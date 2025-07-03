package com.andres.gestion_usuarios_api.service;

import com.andres.gestion_usuarios_api.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<UsuarioEntity> listarTodos();
    Optional<UsuarioEntity> listarPorRol(String rol);

}
