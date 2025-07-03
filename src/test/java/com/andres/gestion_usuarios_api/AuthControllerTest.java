package com.andres.gestion_usuarios_api;

import com.andres.gestion_usuarios_api.DTO.RegistrarDTO;
import com.andres.gestion_usuarios_api.DTO.UsuarioDTO;
import com.andres.gestion_usuarios_api.auth.AuthController;
import com.andres.gestion_usuarios_api.auth.AuthRequest;
import com.andres.gestion_usuarios_api.auth.AuthResponse;
import com.andres.gestion_usuarios_api.auth.AuthService;
import com.andres.gestion_usuarios_api.entity.UsuarioEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @Test
    void login_deberiaRetornarToken() {
        //Arrange
        AuthRequest request = new AuthRequest("andres", "123456",null);
        AuthResponse response = new AuthResponse("fake-jwt-token");

        when(authService.login(request)).thenReturn(response);

        //Act
       ResponseEntity<AuthResponse> resultado = authController.login(request);
       //Assert

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals("fake-jwt-token", response.getMensaje());
    }

    @Test
    void registro_deberiaRetornarUsuarioCreado(){
        //Arrange
        RegistrarDTO dto = new RegistrarDTO();
        dto.setNombre("andres");
        dto.setClave("123456");

        UsuarioEntity usuarioCreado = new UsuarioEntity();
        usuarioCreado.setNombre("andres");
        AuthResponse response = new AuthResponse("Usuario Registrado Correctamente: " + usuarioCreado.getNombre());

        when(authService.registrar(dto)).thenReturn(response);

        //Act
        ResponseEntity<AuthResponse> resultado = authController.registrar(dto);

        //Assert
        assertEquals(HttpStatus.CREATED, resultado.getStatusCode());
        assertEquals("Usuario Registrado Correctamente: " + usuarioCreado.getNombre(), response.getMensaje());

    }
}
