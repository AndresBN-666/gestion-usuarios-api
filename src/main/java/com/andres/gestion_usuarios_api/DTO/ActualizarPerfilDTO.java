package com.andres.gestion_usuarios_api.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ActualizarPerfilDTO {

    @NotBlank(message = "Nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "La nueva clave no puede estar vacia")
    private String nuevaClave;
}
