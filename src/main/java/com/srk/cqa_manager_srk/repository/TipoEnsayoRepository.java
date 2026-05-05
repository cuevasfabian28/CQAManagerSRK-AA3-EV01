package com.srk.cqa_manager_srk.repository;

import com.srk.cqa_manager_srk.model.TipoEnsayo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio de TipoEnsayo.
 * Gestiona el catalogo de tipos de pruebas
 * realizadas en el laboratorio de calidad.
 * Ejemplos: Granulometria, Proctor, CBR.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Repository
public interface TipoEnsayoRepository extends JpaRepository<TipoEnsayo, Integer> {

    /** Verifica si ya existe un tipo de ensayo con ese nombre */
    boolean existsByNombreIgnoreCase(String nombre);
}