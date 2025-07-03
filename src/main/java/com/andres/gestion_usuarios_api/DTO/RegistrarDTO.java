package com.andres.gestion_usuarios_api.DTO;

import com.andres.gestion_usuarios_api.entity.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrarDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "La clave es obligatoria")
    @Size(min = 6, message = "La clave debe tener al menos 6 caracteres")
    private String clave;

    private Rol rol; // puede ser null, lo tratamos en el servicio
}
