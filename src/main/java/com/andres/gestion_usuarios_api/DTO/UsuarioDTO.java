package com.andres.gestion_usuarios_api.DTO;

import com.andres.gestion_usuarios_api.entity.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String clave;

    @NotNull
    private Rol rol;
}
