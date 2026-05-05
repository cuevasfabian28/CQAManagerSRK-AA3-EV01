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
 * Controlador de Topografia.
 * Maneja todas las peticiones HTTP del modulo
 * de levantamientos topograficos del proyecto minero.
 * Equivalente al TopografiaServlet.java anterior.
 *
 * Usa metodos GET para mostrar vistas y
 * metodos POST para procesar formularios.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Controller
@RequestMapping("/topografia")
public class TopografiaController {

    @Autowired
    private TopografiaService topografiaService;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Lista todos los levantamientos topograficos.
     * GET /topografia/lista
     */
    @GetMapping("/lista")
    public String listarTopografias(Model model) {
        model.addAttribute("topografias",
            topografiaService.listarTodos());
        model.addAttribute("titulo", "Levantamientos Topograficos");
        return "topografia/lista";
    }

    /**
     * Muestra formulario para nuevo levantamiento.
     * GET /topografia/nuevo
     */
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("dato", new DatoTopografico());
        model.addAttribute("estructuras",
            topografiaService.listarEstructuras());
        model.addAttribute("usuarios",
            usuarioService.listarTodos());
        model.addAttribute("titulo", "Nuevo Levantamiento Topografico");
        return "topografia/formulario";
    }

    /**
     * Procesa el formulario y guarda el levantamiento.
     * POST /topografia/guardar
     */
    @PostMapping("/guardar")
    public String guardarTopografia(
            @ModelAttribute DatoTopografico dato,
            RedirectAttributes redirectAttributes) {
        try {
            if (dato.getFecha() == null) {
                dato.setFecha(LocalDate.now());
            }
            topografiaService.guardar(dato);
            redirectAttributes.addFlashAttribute("mensaje",
                "Levantamiento topografico guardado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                "Error al guardar el levantamiento: " + e.getMessage());
        }
        return "redirect:/topografia/lista";
    }

    /**
     * Muestra formulario para editar un levantamiento.
     * GET /topografia/editar/{id}
     */
    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Integer id, Model model) {
        topografiaService.buscarPorId(id).ifPresent(dato -> {
            model.addAttribute("dato", dato);
            model.addAttribute("estructuras",
                topografiaService.listarEstructuras());
            model.addAttribute("usuarios",
                usuarioService.listarTodos());
        });
        model.addAttribute("titulo", "Editar Levantamiento");
        return "topografia/formulario";
    }

    /**
     * Elimina un levantamiento por su ID.
     * GET /topografia/eliminar/{id}
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarTopografia(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {
        try {
            topografiaService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje",
                "Levantamiento eliminado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                "No se puede eliminar este levantamiento.");
        }
        return "redirect:/topografia/lista";
    }

    /**
     * Muestra el detalle de un levantamiento especifico.
     * GET /topografia/detalle/{id}
     */
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Integer id, Model model) {
        topografiaService.buscarPorId(id).ifPresent(dato ->
            model.addAttribute("dato", dato));
        return "topografia/detalle";
    }
}