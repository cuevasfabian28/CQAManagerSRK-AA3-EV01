package com.srk.cqa_manager_srk.controller;

import com.srk.cqa_manager_srk.model.*;
import com.srk.cqa_manager_srk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;

/**
 * Controlador de Liberacion.
 * Maneja todas las peticiones HTTP del modulo
 * de supervision y aprobacion de calidad de
 * estructuras mineras.
 * Equivalente al SupervisionServlet.java anterior.
 *
 * Usa metodos GET para mostrar vistas y
 * metodos POST para procesar formularios.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Controller
@RequestMapping("/liberacion")
public class LiberacionController {

    @Autowired
    private LiberacionService liberacionService;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Lista todas las liberaciones registradas.
     * GET /liberacion/lista
     */
    @GetMapping("/lista")
    public String listarLiberaciones(Model model) {
        model.addAttribute("liberaciones",
            liberacionService.listarTodas());
        model.addAttribute("pendientes",
            liberacionService.listarPorEstado(
                Liberacion.Estado.PENDIENTE).size());
        model.addAttribute("titulo", "Liberaciones de Calidad");
        return "liberacion/lista";
    }

    /**
     * Muestra formulario para nueva liberacion.
     * GET /liberacion/nuevo
     */
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("liberacion", new Liberacion());
        model.addAttribute("estructuras",
            liberacionService.listarEstructuras());
        model.addAttribute("usuarios",
            usuarioService.listarTodos());
        model.addAttribute("estados", Liberacion.Estado.values());
        model.addAttribute("titulo", "Nueva Liberacion");
        return "liberacion/formulario";
    }

    /**
     * Procesa el formulario y guarda la liberacion.
     * POST /liberacion/guardar
     */
    @PostMapping("/guardar")
    public String guardarLiberacion(
            @ModelAttribute Liberacion liberacion,
            RedirectAttributes redirectAttributes) {
        try {
            if (liberacion.getFecha() == null) {
                liberacion.setFecha(LocalDate.now());
            }
            liberacionService.guardar(liberacion);
            redirectAttributes.addFlashAttribute("mensaje",
                "Liberacion guardada exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                "Error al guardar la liberacion: " + e.getMessage());
        }
        return "redirect:/liberacion/lista";
    }

    /**
     * Aprueba una liberacion por su ID.
     * POST /liberacion/aprobar/{id}
     */
    @PostMapping("/aprobar/{id}")
    public String aprobarLiberacion(
            @PathVariable Integer id,
            @RequestParam String observaciones,
            RedirectAttributes redirectAttributes) {
        try {
            liberacionService.aprobar(id, observaciones);
            redirectAttributes.addFlashAttribute("mensaje",
                "Liberacion APROBADA exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                "Error al aprobar la liberacion.");
        }
        return "redirect:/liberacion/lista";
    }

    /**
     * Rechaza una liberacion por su ID.
     * POST /liberacion/rechazar/{id}
     */
    @PostMapping("/rechazar/{id}")
    public String rechazarLiberacion(
            @PathVariable Integer id,
            @RequestParam String observaciones,
            RedirectAttributes redirectAttributes) {
        try {
            liberacionService.rechazar(id, observaciones);
            redirectAttributes.addFlashAttribute("mensaje",
                "Liberacion RECHAZADA.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                "Error al rechazar la liberacion.");
        }
        return "redirect:/liberacion/lista";
    }

    /**
     * Elimina una liberacion por su ID.
     * GET /liberacion/eliminar/{id}
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarLiberacion(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {
        try {
            liberacionService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje",
                "Liberacion eliminada exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                "No se puede eliminar esta liberacion.");
        }
        return "redirect:/liberacion/lista";
    }

    /**
     * Muestra el detalle de una liberacion.
     * GET /liberacion/detalle/{id}
     */
    @GetMapping("/detalle/{id}")
    public String verDetalle(
            @PathVariable Integer id, Model model) {
        liberacionService.buscarPorId(id).ifPresent(lib ->
            model.addAttribute("liberacion", lib));
        return "liberacion/detalle";
    }
}