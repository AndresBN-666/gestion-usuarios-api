package com.andres.gestion_usuarios_api.auth;

import com.andres.gestion_usuarios_api.entity.Rol;
import com.andres.gestion_usuarios_api.entity.UsuarioEntity;
import com.andres.gestion_usuarios_api.jwt.JwtService;
import com.andres.gestion_usuarios_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

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

    public AuthResponse login (AuthRequest request) {
        UsuarioEntity usuario = usuarioRepository.findByNombre(request.getNombre())
                .orElseThrow(() -> new RuntimeException("El nombre no existe"));

        if (!passwordEncoder.matches(request.getClave(), usuario.getClave())){
            throw new RuntimeException("Contraseña incorrecta");
        }
        String token = jwtService.generarToken(usuario);
        return new AuthResponse(token);
    }

}
