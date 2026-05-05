package com.srk.cqa_manager_srk.controller;

import com.srk.cqa_manager_srk.model.Liberacion;
import com.srk.cqa_manager_srk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador del Dashboard principal.
 * Muestra el resumen general del sistema CQA Manager SRK
 * con estadisticas de ensayos y liberaciones.
 * Equivalente al SupervisionServlet.java anterior.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Controller
public class DashboardController {

    @Autowired
    private EnsayoService ensayoService;

    @Autowired
    private LiberacionService liberacionService;

    @Autowired
    private TopografiaService topografiaService;

    /**
     * Muestra el dashboard principal con estadisticas.
     * Metodo GET - carga datos para mostrar en la vista.
     *
     * @param model objeto para enviar datos a la vista
     * @return nombre de la plantilla HTML a mostrar
     */
    @GetMapping("/dashboard")
    public String mostrarDashboard(Model model) {

        // Total de ensayos registrados
        model.addAttribute("totalEnsayos",
            ensayoService.listarTodos().size());

        // Total de levantamientos topograficos
        model.addAttribute("totalTopografias",
            topografiaService.listarTodos().size());

        // Liberaciones pendientes de aprobacion
        model.addAttribute("liberacionesPendientes",
            liberacionService.contarPendientes());

        // Liberaciones aprobadas
        model.addAttribute("liberacionesAprobadas",
            liberacionService.listarPorEstado(
                Liberacion.Estado.APROBADO).size());

        // Ultimos 5 ensayos para tabla resumen
        model.addAttribute("ultimosEnsayos",
            ensayoService.listarTodos()
                .stream()
                .limit(5)
                .toList());

        // Retorna la plantilla templates/dashboard.html
        return "dashboard";
    }
}