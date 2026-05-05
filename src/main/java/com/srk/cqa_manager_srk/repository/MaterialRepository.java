package com.srk.cqa_manager_srk.repository;

import com.srk.cqa_manager_srk.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio de Material.
 * Gestiona el catalogo de materiales mineros
 * sometidos a ensayos de control de calidad.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

    /** Busca materiales por tipo */
    List<Material> findByTipo(String tipo);

    /** Busca por nombre ignorando mayusculas */
    List<Material> findByNombreMaterialContainingIgnoreCase(String nombre);
}