package com.srk.cqa_manager_srk.repository;

import com.srk.cqa_manager_srk.model.Liberacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio de Liberacion.
 * Equivalente al LiberacionDAO.java del proyecto anterior.
 * Gestiona el flujo de aprobacion de calidad
 * de estructuras mineras.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Repository
public interface LiberacionRepository extends JpaRepository<Liberacion, Integer> {

    /** Lista liberaciones por estado (PENDIENTE, APROBADO, RECHAZADO) */
    List<Liberacion> findByEstadoOrderByFechaDesc(Liberacion.Estado estado);

    /** Liberaciones de una estructura especifica */
    List<Liberacion> findByEstructuraIdEstructuraOrderByFechaDesc(Integer idEstructura);

    /** Cuenta liberaciones pendientes para el dashboard */
    long countByEstado(Liberacion.Estado estado);

    /** Liberaciones gestionadas por un supervisor */
    List<Liberacion> findByUsuarioIdUsuarioOrderByFechaDesc(Integer idUsuario);
}