package com.andres.gestion_usuarios_api.mapper;

import com.andres.gestion_usuarios_api.DTO.UsuarioDTO;
import com.andres.gestion_usuarios_api.entity.UsuarioEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioEntity toEntity(UsuarioDTO usuarioDTO);

    UsuarioDTO toDTO(UsuarioEntity usuarioEntity);

    List<UsuarioDTO> toDtoList(List<UsuarioEntity> usuarioEntityList);
}
