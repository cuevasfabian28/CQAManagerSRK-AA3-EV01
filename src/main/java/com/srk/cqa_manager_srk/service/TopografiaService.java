package com.srk.cqa_manager_srk.service;

import com.srk.cqa_manager_srk.model.*;
import com.srk.cqa_manager_srk.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de Topografia.
 * Contiene la logica de negocio del modulo
 * de levantamientos topograficos del proyecto minero.
 * Equivalente al TopografiaServlet.java anterior.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Service
@Transactional
public class TopografiaService {

    @Autowired
    private DatoTopograficoRepository datoTopograficoRepository;

    @Autowired
    private EstructuraRepository estructuraRepository;

    /** Lista todos los levantamientos topograficos */
    @Transactional(readOnly = true)
    public List<DatoTopografico> listarTodos() {
        return datoTopograficoRepository.findAll();
    }

    /** Busca un levantamiento por su ID */
    @Transactional(readOnly = true)
    public Optional<DatoTopografico> buscarPorId(Integer id) {
        return datoTopograficoRepository.findById(id);
    }

    /** Guarda o actualiza un levantamiento topografico */
    public DatoTopografico guardar(DatoTopografico dato) {
        if (dato.getFecha() == null) {
            dato.setFecha(LocalDate.now());
        }
        return datoTopograficoRepository.save(dato);
    }

    /** Elimina un levantamiento por su ID */
    public void eliminar(Integer id) {
        datoTopograficoRepository.deleteById(id);
    }

    /** Lista levantamientos de una estructura especifica */
    @Transactional(readOnly = true)
    public List<DatoTopografico> listarPorEstructura(Integer idEstructura) {
        return datoTopograficoRepository
               .findByEstructuraIdEstructuraOrderByFechaDesc(idEstructura);
    }

    /** Lista levantamientos de un usuario especifico */
    @Transactional(readOnly = true)
    public List<DatoTopografico> listarPorUsuario(Integer idUsuario) {
        return datoTopograficoRepository
               .findByUsuarioIdUsuarioOrderByFechaDesc(idUsuario);
    }

    /** Busca levantamientos en un rango de fechas */
    @Transactional(readOnly = true)
    public List<DatoTopografico> buscarPorFechas(LocalDate inicio, LocalDate fin) {
        return datoTopograficoRepository.findByRangoFechas(inicio, fin);
    }

    /** Lista todas las estructuras para formularios */
    @Transactional(readOnly = true)
    public List<Estructura> listarEstructuras() {
        return estructuraRepository.findAll();
    }
}