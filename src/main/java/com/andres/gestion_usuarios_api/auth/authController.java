package com.andres.gestion_usuarios_api.auth;

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
    public ResponseEntity<AuthResponse> registrar(@RequestBody AuthRequest authRequest) {
        System.out.println(authRequest.getNombre());
        System.out.println(authRequest.getClave());
        return ResponseEntity.ok(authService.registrar(authRequest));
    }
}
