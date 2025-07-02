package com.andres.gestion_usuarios_api.auth;

import lombok.Data;

@Data
public class AuthRequest {

    private String nombre;
    private String clave;
}
