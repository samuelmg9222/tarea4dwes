package tarea4dwes.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;

import tarea4dwes.modelo.Credenciales;
import tarea4dwes.servicios.ServiciosCredencial;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	 @Autowired
	    private ServiciosCredencial servcredencial;  
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index", "/verplantas", "/images/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            String role = authentication.getAuthorities().iterator().next().getAuthority();
                            switch (role) {
                                case "ROLE_ADMIN":
                                    response.sendRedirect("/admin");
                                    break;
                                case "ROLE_PERSONAL":
                                    response.sendRedirect("/gestiondeejemplares"); 
                                    break;
                                case "ROLE_CLIENTE":
                                    response.sendRedirect("/cliente"); 
                                    break;
                                default:
                                    response.sendRedirect("/default");
                                    break;
                            }
                        })
                )

                .logout(logout -> logout
                        .logoutSuccessHandler((request, response, authentication) -> {
                            request.getSession().invalidate(); // Invalida la sesión
                            response.sendRedirect("/login?logout"); // Redirige después de cerrar sesión
                        })
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .maximumSessions(1) // Limitar a una sesión por usuario
                        .sessionRegistry(sessionRegistry()) // Registro de sesiones
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList<>();


        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        users.add(admin);


        List<Credenciales> credList = servcredencial.CredencialesFindAll();
        if (!credList.isEmpty()) {
            credList.remove(0);
        }


        for (Credenciales cred : credList) {
            UserDetails user;
            if (cred.getPersona() != null) {
                user = User.withUsername(cred.getUsuario())
                        .password(passwordEncoder().encode(cred.getPassword()))
                        .roles("PERSONAL")
                        .build();
            } else {
                user = User.withUsername(cred.getUsuario())
                        .password(passwordEncoder().encode(cred.getPassword()))
                        .roles("CLIENTE")
                        .build();
            }
            users.add(user);
        }

        return new InMemoryUserDetailsManager(users);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usa BCrypt para encriptar contraseñas
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl(); // Implementación del registro de sesiones
    }
}
