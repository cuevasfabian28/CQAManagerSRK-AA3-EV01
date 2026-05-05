package com.srk.cqa_manager_srk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

/**
 * Entidad ResultadoEnsayo.
 * Almacena cada parametro medido dentro de un ensayo.
 * Ejemplo: Densidad seca = 1.850 g/cm3 - CUMPLE.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Entity
@Table(name = "resultado_ensayo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoEnsayo {

    /** Clave primaria autoincremental */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultado")
    private Integer idResultado;

    /** Nombre del parametro medido */
    @NotBlank(message = "El parametro es obligatorio")
    @Size(max = 100)
    @Column(name = "parametro", nullable = false, length = 100)
    private String parametro;

    /** Valor numerico obtenido en la medicion */
    @NotNull(message = "El valor es obligatorio")
    @Column(name = "valor", nullable = false, precision = 10, scale = 3)
    private BigDecimal valor;

    /** Unidad de medida (ej: g/cm3, %, kPa, mm) */
    @Column(name = "unidad", length = 20)
    private String unidad;

    /** true = cumple el estandar, false = no cumple */
    @Column(name = "cumple")
    private Boolean cumple;

    /** Ensayo al que pertenece este resultado */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ensayo", nullable = false)
    private Ensayo ensayo;
}