package tarea4dwes.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
<<<<<<< HEAD
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private ServiciosCredencial servcredencial;  

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index", "/verplantas", "/images/**", "/nuevocliente", "/registrar-cliente").permitAll()
                .requestMatchers("/cliente").hasAnyRole("CLIENTE")
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
                            response.sendRedirect("/login");
                            break;
                    }
                })
            )
            .logout(logout -> logout
                .logoutSuccessHandler((request, response, authentication) -> {
                    request.getSession().invalidate();
                    response.sendRedirect("/login?logout"); 
                })
                .permitAll()
            )
            .sessionManagement(session -> session
                .maximumSessions(1) 
                .sessionRegistry(sessionRegistry())
            );

        return http.build();
    }
=======

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	 @Autowired
	    private ServiciosCredencial servcredencial;  
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		    http
		      
	         .authorizeHttpRequests(auth -> auth
	             .requestMatchers("/", "/index", "/verplantas", "/images/**", "/nuevocliente", "/registrar-cliente").permitAll()
	             .requestMatchers("/cliente").hasAnyRole("CLIENTE")
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
	                         response.sendRedirect("/login");
	                         break;
	                 }
	             })
	         )
	         .logout(logout -> logout
	             .logoutSuccessHandler((request, response, authentication) -> {
	                 request.getSession().invalidate();
	                 response.sendRedirect("/login?logout"); 
	             })
	             .permitAll()
	         )
	         .sessionManagement(session -> session
	             .maximumSessions(1) 
	             .sessionRegistry(sessionRegistry())
	         );

	     return http.build();
	 }
>>>>>>> e89eb3d70de556070897c64d97c6f454d4ce03db

    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList<>();

<<<<<<< HEAD
=======

>>>>>>> e89eb3d70de556070897c64d97c6f454d4ce03db
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        users.add(admin);

<<<<<<< HEAD
=======

>>>>>>> e89eb3d70de556070897c64d97c6f454d4ce03db
        List<Credenciales> credList = servcredencial.CredencialesFindAll();
        if (!credList.isEmpty()) {
            credList.remove(0);
        }

<<<<<<< HEAD
        for (Credenciales cred : credList) {
            UserDetails user;
            if (cred.getCliente()==null) {
=======

        for (Credenciales cred : credList) {
            UserDetails user;
            if (cred.getPersona() != null) {
>>>>>>> e89eb3d70de556070897c64d97c6f454d4ce03db
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

<<<<<<< HEAD
=======

>>>>>>> e89eb3d70de556070897c64d97c6f454d4ce03db
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usa BCrypt para encriptar contraseñas
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl(); // Implementación del registro de sesiones
    }
}
