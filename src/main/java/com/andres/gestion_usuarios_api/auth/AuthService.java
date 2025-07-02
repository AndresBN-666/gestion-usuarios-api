package com.andres.gestion_usuarios_api.auth;

import com.andres.gestion_usuarios_api.entity.Rol;
import com.andres.gestion_usuarios_api.entity.UsuarioEntity;
import com.andres.gestion_usuarios_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse registrar (AuthRequest request) {
        if (usuarioRepository.existsByNombre(request.getNombre())){
            throw new RuntimeException("El nombre ya existe");
        }

        UsuarioEntity usuario = new UsuarioEntity().builder()
                .nombre(request.getNombre())
                .clave(passwordEncoder.encode(request.getClave()))
                .rol(Rol.USER)
                .build();

        usuarioRepository.save(usuario);
        return new AuthResponse("Usuario Registrado Correctamente");
    }

}
