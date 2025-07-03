package com.andres.gestion_usuarios_api.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuarioDTO {
    private Long id;
    private String nombre;
    private String rol;
}
