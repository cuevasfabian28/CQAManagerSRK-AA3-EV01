package com.srk.cqa_manager_srk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Entidad TipoEnsayo.
 * Catalogo de tipos de pruebas realizadas en laboratorio.
 * Ejemplos: Granulometria, Proctor Modificado, CBR,
 * Densidad In-Situ, Limites de Atterberg.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Entity
@Table(name = "tipo_ensayo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoEnsayo {

    /** Clave primaria autoincremental */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_ensayo")
    private Integer idTipoEnsayo;

    /** Nombre del tipo de ensayo */
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /** Descripcion del procedimiento del ensayo */
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
}