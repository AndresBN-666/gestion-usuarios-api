package com.andres.gestion_usuarios_api.auth;

import com.andres.gestion_usuarios_api.entity.Rol;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {

    @NotBlank
    private String nombre;

    @NotBlank
    private String clave;

    private Rol rol;
}
