package com.srk.cqa_manager_srk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Entidad Estructura.
 * Representa estructuras fisicas del proyecto minero
 * controladas por el sistema CQA.
 * Ejemplos: Dique de relave, Talud norte, Rampa principal.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Entity
@Table(name = "estructura")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estructura {

    /** Clave primaria autoincremental */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estructura")
    private Integer idEstructura;

    /** Nombre de la estructura minera */
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    @Column(name = "nombre_estructura", nullable = false, length = 100)
    private String nombreEstructura;

    /** Tipo de estructura (ej: Dique, Talud, Rampa, Botadero) */
    @Column(name = "tipo", length = 50)
    private String tipo;
}