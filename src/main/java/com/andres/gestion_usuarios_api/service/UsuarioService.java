package com.andres.gestion_usuarios_api.service;

import com.andres.gestion_usuarios_api.DTO.ActualizarPerfilDTO;
import com.andres.gestion_usuarios_api.DTO.PerfilUsuarioDTO;
import com.andres.gestion_usuarios_api.DTO.UsuarioDTO;
import com.andres.gestion_usuarios_api.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<UsuarioDTO> listarTodos();
    Optional<UsuarioEntity> listarPorRol(String rol);
    void actualizarPerfil(ActualizarPerfilDTO dto);
    PerfilUsuarioDTO obtenerPerfil();
    UsuarioDTO crearUsuario(UsuarioDTO dto);

}
