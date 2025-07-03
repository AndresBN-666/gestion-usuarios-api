package com.andres.gestion_usuarios_api.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PerfilUsuarioDTO {
    private Long id;
    private String nombre;
    private String rol;
}
