package com.srk.cqa_manager_srk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

/**
 * Entidad PuntoTopografico.
 * Coordenada individual registrada durante un
 * levantamiento topografico en campo.
 * Contiene coordenadas Norte, Este y Elevacion (3D).
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Entity
@Table(name = "punto_topografico")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PuntoTopografico {

    /** Clave primaria autoincremental */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_punto")
    private Integer idPunto;

    /** Coordenada Norte (Y) en metros */
    @NotNull(message = "La coordenada Norte es obligatoria")
    @Column(name = "norte", nullable = false, precision = 13, scale = 2)
    private BigDecimal norte;

    /** Coordenada Este (X) en metros */
    @NotNull(message = "La coordenada Este es obligatoria")
    @Column(name = "este", nullable = false, precision = 13, scale = 2)
    private BigDecimal este;

    /** Elevacion (Z) sobre el nivel del mar en metros */
    @NotNull(message = "La elevacion es obligatoria")
    @Column(name = "elevacion", nullable = false, precision = 13, scale = 2)
    private BigDecimal elevacion;

    /** Descripcion del punto (ej: Esquina NE dique) */
    @Column(name = "descripcion", length = 100)
    private String descripcion;

    /** Levantamiento al que pertenece este punto */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dato_topografico", nullable = false)
    private DatoTopografico datoTopografico;
}