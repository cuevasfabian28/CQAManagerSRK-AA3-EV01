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
 * Servicio de Ensayos.
 * Contiene la logica de negocio del modulo
 * de laboratorio del CQA Manager SRK.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Service
@Transactional
public class EnsayoService {

    @Autowired
    private EnsayoRepository ensayoRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private TipoEnsayoRepository tipoEnsayoRepository;

    // --- ENSAYOS ---

    /** Lista todos los ensayos registrados */
    @Transactional(readOnly = true)
    public List<Ensayo> listarTodos() {
        return ensayoRepository.findAll();
    }

    /** Busca un ensayo por su ID */
    @Transactional(readOnly = true)
    public Optional<Ensayo> buscarPorId(Integer id) {
        return ensayoRepository.findById(id);
    }

    /** Guarda o actualiza un ensayo */
    public Ensayo guardar(Ensayo ensayo) {
        if (ensayo.getFecha() == null) {
            ensayo.setFecha(LocalDate.now());
        }
        return ensayoRepository.save(ensayo);
    }

    /** Elimina un ensayo por su ID */
    public void eliminar(Integer id) {
        ensayoRepository.deleteById(id);
    }

    /** Lista ensayos de un usuario especifico */
    @Transactional(readOnly = true)
    public List<Ensayo> listarPorUsuario(Integer idUsuario) {
        return ensayoRepository.findByUsuarioIdUsuarioOrderByFechaDesc(idUsuario);
    }

    /** Busca ensayos en un rango de fechas */
    @Transactional(readOnly = true)
    public List<Ensayo> buscarPorFechas(LocalDate inicio, LocalDate fin) {
        return ensayoRepository.findByRangoFechas(inicio, fin);
    }

    /** Estadisticas de ensayos por tipo para el dashboard */
    @Transactional(readOnly = true)
    public List<Object[]> estadisticasPorTipo() {
        return ensayoRepository.countByTipoEnsayo();
    }

    // --- MATERIALES ---

    /** Lista todos los materiales */
    @Transactional(readOnly = true)
    public List<Material> listarMateriales() {
        return materialRepository.findAll();
    }

    /** Guarda un material */
    public Material guardarMaterial(Material material) {
        return materialRepository.save(material);
    }

    /** Elimina un material */
    public void eliminarMaterial(Integer id) {
        materialRepository.deleteById(id);
    }

    // --- TIPOS DE ENSAYO ---

    /** Lista todos los tipos de ensayo */
    @Transactional(readOnly = true)
    public List<TipoEnsayo> listarTiposEnsayo() {
        return tipoEnsayoRepository.findAll();
    }

    /** Guarda un tipo de ensayo */
    public TipoEnsayo guardarTipoEnsayo(TipoEnsayo tipoEnsayo) {
        return tipoEnsayoRepository.save(tipoEnsayo);
    }
}