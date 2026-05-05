package com.srk.cqa_manager_srk.service;

import com.srk.cqa_manager_srk.model.Usuario;
import com.srk.cqa_manager_srk.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de Usuario.
 * Maneja la logica de negocio de usuarios y tambien
 * implementa UserDetailsService para que Spring Security
 * pueda autenticar usuarios desde la base de datos.
 * Equivalente al LoginServlet.java y UsuarioDAO.java anterior.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Service
@Transactional
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Metodo requerido por Spring Security.
     * Busca el usuario en la BD por email para autenticacion.
     * @param email correo del usuario que intenta iniciar sesion
     * @return objeto UserDetails con credenciales del usuario
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca el usuario en la base de datos
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(
                "Usuario no encontrado: " + email));

        // Construye el objeto de seguridad de Spring
        return User.builder()
        	    .username(usuario.getEmail())
        	    .password(usuario.getPassword())
        	    .authorities("ROLE_" + usuario.getRol())
        	    .build();
    }

    /** Lista todos los usuarios del sistema */
    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    /** Busca un usuario por su ID */
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    /** Busca un usuario por su email */
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    /** Guarda un nuevo usuario encriptando su password */
    public Usuario guardar(Usuario usuario) {
        // Encripta la password antes de guardar en BD
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    /** Elimina un usuario por su ID */
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    /** Verifica si un email ya esta registrado */
    @Transactional(readOnly = true)
    public boolean existeEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    /** Lista usuarios por rol */
    @Transactional(readOnly = true)
    public List<Usuario> listarPorRol(String rol) {
        return usuarioRepository.findByRol(rol);
    }
}