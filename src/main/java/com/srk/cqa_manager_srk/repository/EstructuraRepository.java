package com.srk.cqa_manager_srk.repository;

import com.srk.cqa_manager_srk.model.Estructura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio de Estructura.
 * Gestiona las estructuras fisicas del proyecto
 * minero controladas por el sistema CQA.
 * Ejemplos: Dique de relave, Talud norte, Rampa.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Repository
public interface EstructuraRepository extends JpaRepository<Estructura, Integer> {

    /** Busca estructuras por tipo (Dique, Talud, Rampa, Botadero) */
    List<Estructura> findByTipo(String tipo);

    /** Busca por nombre ignorando mayusculas */
    List<Estructura> findByNombreEstructuraContainingIgnoreCase(String nombre);
}