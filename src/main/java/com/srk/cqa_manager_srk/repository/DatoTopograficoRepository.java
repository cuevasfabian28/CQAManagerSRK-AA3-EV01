package com.srk.cqa_manager_srk.repository;

import com.srk.cqa_manager_srk.model.DatoTopografico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio de DatoTopografico.
 * Equivalente al TopografiaDAO.java del proyecto anterior.
 * Gestiona los levantamientos topograficos del proyecto.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Repository
public interface DatoTopograficoRepository extends JpaRepository<DatoTopografico, Integer> {

    /** Lista levantamientos de una estructura especifica */
    List<DatoTopografico> findByEstructuraIdEstructuraOrderByFechaDesc(Integer idEstructura);

    /** Levantamientos realizados por un usuario */
    List<DatoTopografico> findByUsuarioIdUsuarioOrderByFechaDesc(Integer idUsuario);

    /** Levantamientos en rango de fechas para reportes */
    @Query("SELECT d FROM DatoTopografico d WHERE d.fecha BETWEEN :inicio AND :fin ORDER BY d.fecha DESC")
    List<DatoTopografico> findByRangoFechas(@Param("inicio") LocalDate inicio,
                                            @Param("fin") LocalDate fin);
}