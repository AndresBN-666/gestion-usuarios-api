package com.andres.gestion_usuarios_api;

import com.andres.gestion_usuarios_api.DTO.PerfilUsuarioDTO;
import com.andres.gestion_usuarios_api.DTO.UsuarioDTO;
import com.andres.gestion_usuarios_api.controller.UsuarioController;
import com.andres.gestion_usuarios_api.entity.Rol;
import com.andres.gestion_usuarios_api.security.JwtAuthFilter;
import com.andres.gestion_usuarios_api.service.UsuarioService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UsuarioController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthFilter.class)
})
@AutoConfigureMockMvc(addFilters = false)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearUsuario() throws Exception {
        UsuarioDTO request = new UsuarioDTO("andres", "123456", Rol.USER);
        UsuarioDTO response = new UsuarioDTO("andres", "123456", Rol.USER);

        when(usuarioService.crearUsuario(any(UsuarioDTO.class))).thenReturn(response);

        mockMvc.perform(post("/usuarios/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("andres"));
    }

    @Test
    void testListarUsuarios() throws Exception {
        List<UsuarioDTO> usuarioDTOS = List.of(
                new UsuarioDTO("andres", "123456", Rol.USER),
                new UsuarioDTO("maria", "abcdef", Rol.ADMIN)
        );

        when(usuarioService.listarTodos()).thenReturn(usuarioDTOS);

        mockMvc.perform(get("/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("andres"))
                .andExpect(jsonPath("$[1].nombre").value("maria"));

    }

    @Test
    @WithMockUser(username = "andres", roles = {"USER"})
    void testVerPerfil() throws Exception {
        PerfilUsuarioDTO perfil = new PerfilUsuarioDTO(1L, "andres", Rol.USER.name());

        when(usuarioService.obtenerPerfil()).thenReturn(perfil);

        mockMvc.perform(get("/usuarios/verPerfil")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("andres"))
                .andExpect(jsonPath("$.rol").value("USER"));
    }


}
