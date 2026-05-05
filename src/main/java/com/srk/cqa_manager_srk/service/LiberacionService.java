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
 * Servicio de Liberacion.
 * Contiene la logica de negocio del modulo
 * de supervision y aprobacion de calidad.
 * Equivalente al LiberacionServlet.java anterior.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Service
@Transactional
public class LiberacionService {

    @Autowired
    private LiberacionRepository liberacionRepository;

    @Autowired
    private EstructuraRepository estructuraRepository;

    /** Lista todas las liberaciones registradas */
    @Transactional(readOnly = true)
    public List<Liberacion> listarTodas() {
        return liberacionRepository.findAll();
    }

    /** Busca una liberacion por su ID */
    @Transactional(readOnly = true)
    public Optional<Liberacion> buscarPorId(Integer id) {
        return liberacionRepository.findById(id);
    }

    /** Guarda o actualiza una liberacion */
    public Liberacion guardar(Liberacion liberacion) {
        if (liberacion.getFecha() == null) {
            liberacion.setFecha(LocalDate.now());
        }
        if (liberacion.getEstado() == null) {
            liberacion.setEstado(Liberacion.Estado.PENDIENTE);
        }
        return liberacionRepository.save(liberacion);
    }

    /** Aprueba una liberacion por su ID */
    public void aprobar(Integer id, String observaciones) {
        liberacionRepository.findById(id).ifPresent(lib -> {
            lib.setEstado(Liberacion.Estado.APROBADO);
            lib.setObservaciones(observaciones);
            liberacionRepository.save(lib);
        });
    }

    /** Rechaza una liberacion por su ID */
    public void rechazar(Integer id, String observaciones) {
        liberacionRepository.findById(id).ifPresent(lib -> {
            lib.setEstado(Liberacion.Estado.RECHAZADO);
            lib.setObservaciones(observaciones);
            liberacionRepository.save(lib);
        });
    }

    /** Elimina una liberacion por su ID */
    public void eliminar(Integer id) {
        liberacionRepository.deleteById(id);
    }

    /** Lista liberaciones por estado */
    @Transactional(readOnly = true)
    public List<Liberacion> listarPorEstado(Liberacion.Estado estado) {
        return liberacionRepository.findByEstadoOrderByFechaDesc(estado);
    }

    /** Cuenta liberaciones pendientes para el dashboard */
    @Transactional(readOnly = true)
    public long contarPendientes() {
        return liberacionRepository.countByEstado(Liberacion.Estado.PENDIENTE);
    }

    /** Lista todas las estructuras para el formulario */
    @Transactional(readOnly = true)
    public List<Estructura> listarEstructuras() {
        return estructuraRepository.findAll();
    }

    /** Guarda una estructura */
    public Estructura guardarEstructura(Estructura estructura) {
        return estructuraRepository.save(estructura);
    }
}