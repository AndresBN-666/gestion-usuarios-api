package com.andres.gestion_usuarios_api.auth;

import com.andres.gestion_usuarios_api.DTO.RegistrarDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authController {

    private final AuthService authService;

    @PostMapping("/registrar")
    public ResponseEntity<AuthResponse> registrar(@RequestBody @Valid RegistrarDTO dto) {
        System.out.println(dto.getNombre());
        System.out.println(dto.getClave());
        return ResponseEntity.ok(authService.registrar(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }
}
