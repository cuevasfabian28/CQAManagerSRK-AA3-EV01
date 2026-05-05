package com.srk.cqa_manager_srk.config;

import com.srk.cqa_manager_srk.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuracion de seguridad del sistema CQA Manager SRK.
 * Define que rutas son publicas y cuales requieren login.
 * Equivalente al LoginServlet.java y LogoutServlet.java
 * del proyecto anterior pero mucho mas completo y seguro.
 *
 * @author Fabian Cuevas
 * Evidencia GA7-220501096-AA3-EV01
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Define las reglas de seguridad de la aplicacion.
     * Configura login, logout y control de acceso por roles.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Rutas publicas - no requieren login
            		.requestMatchers(
            			    "/auth/login",
            			    "/auth/hash",
            			    "/css/**",
            			    "/js/**",
            			    "/images/**"
            			).permitAll()
                // Liberaciones solo para SUPERVISOR y ADMIN
                .requestMatchers("/liberacion/**")
                    .hasAnyRole("SUPERVISOR", "ADMIN")
                // Resto de rutas requieren autenticacion
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                // Pagina del formulario de login
                .loginPage("/auth/login")
                // URL que procesa el formulario POST
                .loginProcessingUrl("/auth/login")
                // Redirige al dashboard al iniciar sesion
                .defaultSuccessUrl("/dashboard", true)
                // Redirige con error si falla el login
                .failureUrl("/auth/login?error=true")
                // Nombre del campo email en el formulario
                .usernameParameter("email")
                // Nombre del campo password en el formulario
                .passwordParameter("password")
                .permitAll()
            )
            .logout(logout -> logout
                // URL para cerrar sesion
                .logoutUrl("/auth/logout")
                // Redirige al login al cerrar sesion
                .logoutSuccessUrl("/auth/login?logout=true")
                // Invalida la sesion al cerrar
                .invalidateHttpSession(true)
                // Elimina la cookie de sesion
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }

    /**
     * Proveedor de autenticacion.
     * Conecta Spring Security con la base de datos
     * usando el UsuarioService y BCrypt.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Encriptador de passwords con BCrypt.
     * Mas seguro que almacenar passwords en texto plano.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}