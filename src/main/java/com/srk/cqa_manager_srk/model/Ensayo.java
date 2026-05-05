package com.srk.cqa_manager_srk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Entidad Ensayo.
 * Registro principal de cada ensayo de laboratorio.
 * Es la entidad central del modulo de control de calidad.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Entity
@Table(name = "ensayo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ensayo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ensayo")
    private Integer idEnsayo;

    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_material", nullable = false)
    private Material material;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_ensayo", nullable = false)
    private TipoEnsayo tipoEnsayo;

    @OneToMany(mappedBy = "ensayo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResultadoEnsayo> resultados;
}