package com.andres.gestion_usuarios_api.serviceImpl;

import com.andres.gestion_usuarios_api.DTO.ActualizarPerfilDTO;
import com.andres.gestion_usuarios_api.DTO.PerfilUsuarioDTO;
import com.andres.gestion_usuarios_api.DTO.UsuarioDTO;
import com.andres.gestion_usuarios_api.entity.UsuarioEntity;
import com.andres.gestion_usuarios_api.mapper.UsuarioMapper;
import com.andres.gestion_usuarios_api.repository.UsuarioRepository;
import com.andres.gestion_usuarios_api.service.UsuarioService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }


    @Override
    public List<UsuarioDTO> listarTodos() {
        return usuarioMapper.toDtoList(usuarioRepository.findAll());
    }

    @Override
    public Optional<UsuarioEntity> listarPorRol(String rol) {
        return Optional.empty();
    }

    @Override
    public void actualizarPerfil(ActualizarPerfilDTO dto) {
        String nombre = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("nombre = " + nombre);
        UsuarioEntity usuario = usuarioRepository.findByNombre(nombre)
                .orElseThrow(()-> new UsernameNotFoundException("No existe el usuario con el nombre: " + nombre));
        usuario.setNombre(dto.getNombre());
        usuario.setClave(passwordEncoder.encode(dto.getNuevaClave()));
        usuarioRepository.save(usuario);
    }

    @Override
    public PerfilUsuarioDTO obtenerPerfil() {
        String nombre = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        UsuarioEntity usuario = usuarioRepository.findByNombre(nombre)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario con nombre: " + nombre));
        return PerfilUsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .rol(usuario.getRol().name())
                .build();
    }

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO dto) {
        if (usuarioRepository.existsByNombre(dto.getNombre())){
            throw new RuntimeException("Nombre de usuario ya existe");
        }

        UsuarioEntity usuarioGuardar = usuarioMapper.toEntity(dto);
        usuarioGuardar.setClave(passwordEncoder.encode(dto.getClave()));

        return usuarioMapper.toDTO(usuarioRepository.save(usuarioGuardar));

    }
}
