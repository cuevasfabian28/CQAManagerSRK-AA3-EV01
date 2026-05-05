package com.srk.cqa_manager_srk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

/**
 * Entidad Liberacion.
 * Proceso de aprobacion de calidad de una estructura
 * minera. Puede estar PENDIENTE, APROBADA o RECHAZADA
 * segun los ensayos y topografia realizados.
 * Es el modulo de supervision del sistema CQA.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Entity
@Table(name = "liberacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Liberacion {

    /** Estados posibles de una liberacion de calidad */
    public enum Estado {
        APROBADO, RECHAZADO, PENDIENTE
    }

    /** Clave primaria autoincremental */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_liberacion")
    private Integer idLiberacion;

    /** Fecha de la solicitud de liberacion */
    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    /** Estado actual de la liberacion */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private Estado estado = Estado.PENDIENTE;

    /** Observaciones del supervisor */
    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    /** Estructura que se esta liberando */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estructura", nullable = false)
    private Estructura estructura;

    /** Supervisor responsable de la liberacion */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}