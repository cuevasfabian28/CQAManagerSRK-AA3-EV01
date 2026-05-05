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
 * Controlador de Ensayos.
 * Maneja todas las peticiones HTTP del modulo
 * de laboratorio del CQA Manager SRK.
 * Equivalente al EnsayoServlet.java anterior.
 *
 * Usa metodos GET para mostrar vistas y
 * metodos POST para procesar formularios.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Controller
@RequestMapping("/ensayo")
public class EnsayoController {

    @Autowired
    private EnsayoService ensayoService;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Lista todos los ensayos registrados.
     * GET /ensayo/lista
     */
    @GetMapping("/lista")
    public String listarEnsayos(Model model) {
        model.addAttribute("ensayos", ensayoService.listarTodos());
        model.addAttribute("titulo", "Lista de Ensayos");
        return "ensayo/lista";
    }

    /**
     * Muestra el formulario para crear un nuevo ensayo.
     * GET /ensayo/nuevo
     */
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        // Envia objeto vacio para que Thymeleaf lo llene
        model.addAttribute("ensayo", new Ensayo());
        // Carga listas para los selectores del formulario
        model.addAttribute("materiales", 
            ensayoService.listarMateriales());
        model.addAttribute("tiposEnsayo", 
            ensayoService.listarTiposEnsayo());
        model.addAttribute("usuarios", 
            usuarioService.listarTodos());
        model.addAttribute("titulo", "Nuevo Ensayo");
        return "ensayo/formulario";
    }

    /**
     * Procesa el formulario y guarda el nuevo ensayo.
     * POST /ensayo/guardar
     */
    @PostMapping("/guardar")
    public String guardarEnsayo(
            @ModelAttribute Ensayo ensayo,
            RedirectAttributes redirectAttributes) {
        try {
            // Asigna la fecha actual si no viene del formulario
            if (ensayo.getFecha() == null) {
                ensayo.setFecha(LocalDate.now());
            }
            ensayoService.guardar(ensayo);
            redirectAttributes.addFlashAttribute("mensaje",
                "Ensayo guardado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                "Error al guardar el ensayo: " + e.getMessage());
        }
        return "redirect:/ensayo/lista";
    }

    /**
     * Muestra el formulario para editar un ensayo existente.
     * GET /ensayo/editar/{id}
     */
    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Integer id, Model model) {
        ensayoService.buscarPorId(id).ifPresent(ensayo -> {
            model.addAttribute("ensayo", ensayo);
            model.addAttribute("materiales", 
                ensayoService.listarMateriales());
            model.addAttribute("tiposEnsayo", 
                ensayoService.listarTiposEnsayo());
            model.addAttribute("usuarios", 
                usuarioService.listarTodos());
        });
        model.addAttribute("titulo", "Editar Ensayo");
        return "ensayo/formulario";
    }

    /**
     * Elimina un ensayo por su ID.
     * GET /ensayo/eliminar/{id}
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarEnsayo(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {
        try {
            ensayoService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje",
                "Ensayo eliminado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error",
                "No se puede eliminar este ensayo.");
        }
        return "redirect:/ensayo/lista";
    }

    /**
     * Muestra el detalle de un ensayo especifico.
     * GET /ensayo/detalle/{id}
     */
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Integer id, Model model) {
        ensayoService.buscarPorId(id).ifPresent(ensayo ->
            model.addAttribute("ensayo", ensayo));
        return "ensayo/detalle";
    }
}