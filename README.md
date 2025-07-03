# Gestión de Usuarios API

Este proyecto es una API REST construida con Spring Boot para la gestión de usuarios. Incluye autenticación con JWT, control de roles, documentación Swagger y validaciones. Ideal como parte de un portafolio backend.

## 🛠️ Tecnologías utilizadas

- Spring Boot 3.2.5
- Spring Security
- JWT (Json Web Token)
- Spring Data JPA
- MySQL
- Swagger (Springdoc OpenAPI)
- MapStruct
- Maven

## 🔐 Funcionalidades principales

- Registro de usuarios
- Autenticación con JWT
- Autorización por roles (`ADMIN`, `USER`)
- Obtener perfil del usuario autenticado
- CRUD de usuarios con restricciones según rol
- Documentación automática con Swagger

## 📄 Endpoints principales

| Método | Endpoint           | Descripción                        | Rol requerido |
|--------|--------------------|------------------------------------|----------------|
| POST   | `/auth/register`   | Registrar nuevo usuario            | Público        |
| POST   | `/auth/login`      | Autenticar usuario y obtener token | Público        |
| GET    | `/usuarios/perfil` | Ver perfil del usuario autenticado | ADMIN o USER   |
| GET    | `/usuarios`        | Listar todos los usuarios          | Solo ADMIN     |

> Para más endpoints, consultar Swagger en: `http://localhost:8080/swagger-ui.html`

## 🧪 Pruebas unitarias

- Se incluye una prueba para `UsuarioController` que valida la creación de un usuario.
- Se utilizó `MockMvc` y `@WebMvcTest` deshabilitando filtros de seguridad para pruebas controladas.

## 🚀 Cómo ejecutar

1. Clona el repositorio:
   ```bash
   git clone https://github.com/AndresBN-666/gestion-usuarios-api.git
    cd gestion-usuarios-api
```

