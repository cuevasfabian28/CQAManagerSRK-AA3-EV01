package com.srk.cqa_manager_srk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * Entidad Material.
 * Catalogo de materiales mineros sometidos a ensayos
 * de control de calidad en laboratorio.
 * Ejemplos: Roca, Relave, Concreto, Suelo granular.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Entity
@Table(name = "material")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material {

    /** Clave primaria autoincremental */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material")
    private Integer idMaterial;

    /** Nombre del material (ej: Roca andesitica, Relave filtrado) */
    @NotBlank(message = "El nombre del material es obligatorio")
    @Size(max = 100)
    @Column(name = "nombre_material", nullable = false, length = 100)
    private String nombreMaterial;

    /** Tipo de material (ej: mineral, esteril, relaves, concreto) */
    @Column(name = "tipo", length = 50)
    private String tipo;

    /** Descripcion tecnica del material */
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;
}