package com.srk.cqa_manager_srk.repository;

import com.srk.cqa_manager_srk.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio de Usuario.
 * Equivalente al UsuarioDAO.java del proyecto anterior.
 * Spring genera automaticamente los metodos CRUD
 * sin necesidad de escribir SQL manual.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /** Busca usuario por email para el login */
    Optional<Usuario> findByEmail(String email);

    /** Verifica si ya existe un email registrado */
    boolean existsByEmail(String email);

    /** Busca usuarios por rol del sistema */
    List<Usuario> findByRol(String rol);
}