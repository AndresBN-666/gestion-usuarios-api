package com.andres.gestion_usuarios_api.repository;

import com.andres.gestion_usuarios_api.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
