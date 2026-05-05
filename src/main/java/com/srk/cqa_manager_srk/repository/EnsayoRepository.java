package com.srk.cqa_manager_srk.repository;

import com.srk.cqa_manager_srk.model.Ensayo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio de Ensayo.
 * Equivalente al EnsayoDAO.java del proyecto anterior.
 * Incluye consultas personalizadas para reportes.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Repository
public interface EnsayoRepository extends JpaRepository<Ensayo, Integer> {

    /** Lista ensayos de un usuario especifico ordenados por fecha */
    List<Ensayo> findByUsuarioIdUsuarioOrderByFechaDesc(Integer idUsuario);

    /** Busca ensayos en un rango de fechas para reportes */
    @Query("SELECT e FROM Ensayo e WHERE e.fecha BETWEEN :inicio AND :fin ORDER BY e.fecha DESC")
    List<Ensayo> findByRangoFechas(@Param("inicio") LocalDate inicio, 
                                   @Param("fin") LocalDate fin);

    /** Ensayos de un material especifico */
    List<Ensayo> findByMaterialIdMaterialOrderByFechaDesc(Integer idMaterial);

    /** Cuenta ensayos por tipo para estadisticas del dashboard */
    @Query("SELECT e.tipoEnsayo.nombre, COUNT(e) FROM Ensayo e GROUP BY e.tipoEnsayo.nombre")
    List<Object[]> countByTipoEnsayo();
}