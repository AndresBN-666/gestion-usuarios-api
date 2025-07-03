package com.andres.gestion_usuarios_api.controller;

import com.andres.gestion_usuarios_api.DTO.ActualizarPerfilDTO;
import com.andres.gestion_usuarios_api.DTO.PerfilUsuarioDTO;
import com.andres.gestion_usuarios_api.DTO.UsuarioDTO;
import com.andres.gestion_usuarios_api.entity.UsuarioEntity;
import com.andres.gestion_usuarios_api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @Operation(
            summary = "Listar todos los usuarios",
            description = "Este endpoint retorna todos los usuarios registrados. Solo accesible para rol ADMIN.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente"),
                    @ApiResponse(responseCode = "403", description = "Acceso denegado por falta de permisos")
            }
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodosLosUsuarios() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/perfil")
    public ResponseEntity<UsuarioEntity> verMiPerfil(Authentication auth) {
        UsuarioEntity usuario = (UsuarioEntity) auth.getPrincipal();
        return ResponseEntity.ok(usuario);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("/perfil")
    public ResponseEntity<String> actualizarPerfil(@RequestBody @Valid ActualizarPerfilDTO dto){
        usuarioService.actualizarPerfil(dto);
        return ResponseEntity.ok("Usuario actualizado correctamente");
    }


    @GetMapping("/verPerfil")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PerfilUsuarioDTO> verPerfil(){
        return ResponseEntity.ok(usuarioService.obtenerPerfil());
    }

    @PostMapping("/crear")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> crearUsuario(UsuarioDTO dto){
        UsuarioDTO usuario = usuarioService.crearUsuario(dto);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);

    }

}
