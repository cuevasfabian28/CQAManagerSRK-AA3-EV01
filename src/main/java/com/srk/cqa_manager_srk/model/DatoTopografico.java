package com.srk.cqa_manager_srk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Entidad DatoTopografico.
 * Registro de levantamientos topograficos realizados
 * sobre estructuras del proyecto minero.
 * Contiene area, volumen y puntos medidos en campo.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Entity
@Table(name = "datos_topograficos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatoTopografico {

    /** Clave primaria autoincremental */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dato_topografico")
    private Integer idDatoTopografico;

    /** Fecha del levantamiento topografico */
    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    /** Area medida en metros cuadrados */
    @Column(name = "area", precision = 12, scale = 2)
    private BigDecimal area;

    /** Volumen calculado en metros cubicos */
    @Column(name = "volumen", precision = 12, scale = 2)
    private BigDecimal volumen;

    /** Estructura sobre la que se realizo el levantamiento */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estructura", nullable = false)
    private Estructura estructura;

    /** Topografo responsable del levantamiento */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    /** Puntos topograficos del levantamiento */
    @OneToMany(mappedBy = "datoTopografico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PuntoTopografico> puntos;
}