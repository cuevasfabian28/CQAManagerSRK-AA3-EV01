package com.srk.cqa_manager_srk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador de Autenticacion.
 * Maneja las peticiones HTTP de login y logout.
 * Equivalente al LoginServlet.java y LogoutServlet.java
 * del proyecto anterior.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    /**
     * Muestra el formulario de login.
     * Metodo GET - solo muestra la pagina.
     * Spring Security maneja el POST automaticamente.
     *
     * @param error indica si hubo error de autenticacion
     * @param logout indica si el usuario cerro sesion
     * @param model objeto para enviar datos a la vista
     * @return nombre de la plantilla HTML a mostrar
     */
    @GetMapping("/login")
    public String mostrarLogin(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {

        // Si hubo error de credenciales muestra mensaje
        if (error != null) {
            model.addAttribute("error", 
                "Email o contraseña incorrectos. Intente de nuevo.");
        }

        // Si cerro sesion muestra mensaje de confirmacion
        if (logout != null) {
            model.addAttribute("mensaje", 
                "Ha cerrado sesion exitosamente.");
        }

        // Retorna la plantilla templates/auth/login.html
        return "auth/login";
    }
    
    /**
     * Endpoint temporal para generar hash BCrypt.
     * ELIMINAR despues de obtener el hash correcto.
     */
    @GetMapping("/hash")
    @org.springframework.web.bind.annotation.ResponseBody
    public String generarHash() {
        org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder = 
            new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
        String hash = encoder.encode("fabian123");
        return hash;
    }
}

