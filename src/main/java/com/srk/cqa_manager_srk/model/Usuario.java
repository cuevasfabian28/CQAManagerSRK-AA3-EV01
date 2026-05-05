package com.srk.cqa_manager_srk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Entidad Usuario.
 * Representa los empleados del sistema CQA Manager SRK.
 * Mapeada a la tabla 'usuario' de la base de datos.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    /** Clave primaria autoincremental */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    /** Nombre completo del empleado */
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "Maximo 100 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /** Cargo dentro de la empresa minera */
    @NotBlank(message = "El cargo es obligatorio")
    @Size(max = 50, message = "Maximo 50 caracteres")
    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;

    /** Correo electronico - usado como usuario de login */
    @Email(message = "Formato de email invalido")
    @Column(name = "email", unique = true, length = 100)
    private String email;

    /** Contrasena encriptada con BCrypt */
    @Column(name = "password", length = 255)
    private String password;

    /** Rol para control de acceso: ADMIN, SUPERVISOR, LABORATORISTA, TOPOGRAFO */
    @Column(name = "rol", length = 30)
    private String rol = "LABORATORISTA";
}