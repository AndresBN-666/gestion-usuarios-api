package com.andres.gestion_usuarios_api;

import com.andres.gestion_usuarios_api.DTO.UsuarioDTO;
import com.andres.gestion_usuarios_api.entity.Rol;
import com.andres.gestion_usuarios_api.entity.UsuarioEntity;
import com.andres.gestion_usuarios_api.mapper.UsuarioMapper;
import com.andres.gestion_usuarios_api.repository.UsuarioRepository;
import com.andres.gestion_usuarios_api.serviceImpl.UsuarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {


    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void listarUsuarios_deberiaRetornarListaDeDTOs() {
        List<UsuarioEntity> listaUsuarios = List.of(
                new UsuarioEntity(1L, "admin", "123", Rol.ADMIN),
                new UsuarioEntity(2L, "user", "456", Rol.USER)
        );

        List<UsuarioDTO> dtos = List.of(
                new UsuarioDTO("admin", null, Rol.ADMIN),
                new UsuarioDTO("user", null, Rol.USER)
        );

        when(usuarioRepository.findAll()).thenReturn(listaUsuarios);
        when(usuarioMapper.toDtoList(listaUsuarios)).thenReturn(dtos);

        //Act
        List<UsuarioDTO> resultado = usuarioService.listarTodos();

        //Asert
        assertEquals(2, resultado.size());
        assertEquals("admin", resultado.get(0).getNombre());

    }


    @Test
    void crearUsuario_deberiaGuardarUsuarioCuandoNoExiste(){
        //Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre("andres");
        usuarioDTO.setClave("123456");

        UsuarioEntity entity = new UsuarioEntity();
        entity.setNombre("andres");
        entity.setClave("encrypted");

        UsuarioDTO esperado = new UsuarioDTO();
        esperado.setNombre("andres");

        when(usuarioRepository.existsByNombre(usuarioDTO.getNombre())).thenReturn(false);
        when(usuarioMapper.toEntity(usuarioDTO)).thenReturn(entity);
        when(passwordEncoder.encode("123456")).thenReturn("encrypted");
        when(usuarioRepository.save(entity)).thenReturn(entity);
        when(usuarioMapper.toDTO(entity)).thenReturn(esperado);

        //ACT
        UsuarioDTO resultado = usuarioService.crearUsuario(usuarioDTO);

        //Assert
        assertEquals("andres", resultado.getNombre());
        verify(usuarioRepository, times(1)).save(entity);
    }

    @Test
    void crearUsuario_deberiaLanzarExcepcionCuandoNombreExiste() {
        //Arrange
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre("andres");

        when(usuarioRepository.existsByNombre("andres")).thenReturn(true);

        //Act && Asert

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> usuarioService.crearUsuario(usuarioDTO));
        assertEquals("Nombre de usuario ya existe", ex.getMessage());
        verify(usuarioRepository, never()).save(any());
    }
}
