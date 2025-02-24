package tarea4dwes.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import tarea4dwes.modelo.Credenciales;
import tarea4dwes.modelo.Ejemplar;
import tarea4dwes.modelo.Mensaje;
import tarea4dwes.modelo.Persona;
import tarea4dwes.modelo.Planta;
import tarea4dwes.servicios.ServiciosCredencial;
import tarea4dwes.servicios.ServiciosEjemplar;
import tarea4dwes.servicios.ServiciosMensaje;
import tarea4dwes.servicios.ServiciosPersona;
import tarea4dwes.servicios.ServiciosPlanta;
   
@Controller
public class MainController {

    @GetMapping("/")
    public String mostrarPagina(Model model) {
        model.addAttribute("titulo", "VIVERO");
        model.addAttribute("autor", "Vivero de Samuel Muñiz González");
        model.addAttribute("mensaje", "Cuidamos de tus plantas, para que ellas cuiden de ti.");
        model.addAttribute("anio", Year.now().getValue());
        model.addAttribute("contacto", "Contacto: contacto@viverosamuel.com");

        return "index"; 
    }

    @GetMapping("/login")
    public String mostrarLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Credenciales incorrectas. Intenta de nuevo.");
        }
        return "login"; 
    }

    @Autowired
    private ServiciosCredencial servcredencial;  

  

    
    @GetMapping("/gestiondeplantas")
    public String gestionDePlantas() {
   
        return "gestiondeplantas";  
    }

    @GetMapping("/gestiondeplantaspersonal")
    public String gestiondeplantaspersonal() {
   
        return "gestiondeplantaspersonal";  
    }

    @GetMapping("/gestiondeejemplares")
    public String gestionDeEjemplares(Authentication authentication, Model model) {
        // Obtiene el nombre de usuario directamente desde el objeto Authentication
        String username = authentication.getName(); // Obtiene el nombre de usuario del objeto de autenticación

        // Si no hay usuario autenticado, redirige al login
        if (username == null) {
            return "redirect:/login"; 
        }

        // Obtiene las credenciales del usuario a través del servicio
        Credenciales cr = servcredencial.obtenerCredencialPorUsername(username);
        
        // Verifica que las credenciales existan y que el usuario tenga asignada una persona
        if (cr == null || cr.getPersona() == null) {
            return "redirect:/login"; // Si no tiene asignada una persona, redirige al login
        }

        // Agrega el nombre de usuario al modelo para su uso en la vista
        model.addAttribute("username", cr.getUsuario());

        // Obtiene los roles del usuario
        List<String> roles = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

        // Agrega los roles al modelo para su uso en la vista
        model.addAttribute("roles", roles);

        // Devuelve la vista de gestión de ejemplares
        return "gestiondeejemplares";  
    }


    @GetMapping("/gestiondepersonal")
    public String gestionDePersonal() {
        
        return "gestiondepersonal";  
    }
    

    @GetMapping("/admin")
    public String mostrarAdminPage(HttpSession session, Model model) {
        // Accede al username de la sesión
        String username = (String) session.getAttribute("username");
        
        // Puedes agregarlo al modelo para pasarlo a la vista
        model.addAttribute("username", username);
        
        return "admin";  // Redirige a la página admin
    }

    @GetMapping("/index")
    public String index() {
        
        return "index";  
    }
    

    
    
    

}