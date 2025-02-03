package tarea4dwes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController {
/*
    @Autowired
    private ServiciosCredencial servcredencial;  // Servicio para autenticar al usuario

    // Mostrar el formulario de login
    @GetMapping("/login")
    public String mostrarLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Credenciales incorrectas. Intenta de nuevo.");
        }
        return "login"; // Retorna la vista del formulario de login (login.html)
    }

    // Procesar el login
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username, 
                                @RequestParam String password, 
                                RedirectAttributes redirectAttributes) {
        // Validar las credenciales
        int result = servcredencial.registro(username, password);

        // Dependiendo del resultado del login
        if (result == -1) {
            // Si el resultado es -1, las credenciales son incorrectas
            redirectAttributes.addFlashAttribute("error", "Credenciales incorrectas.");
            return "redirect:/login";  // Redirige al login con un error
        } else if (result == 0) {
            // Si el resultado es 0, es el usuario admin
            return "redirect:/admin";  // Redirige al admin
        } else if (result == 1) {
            // Si el resultado es 1, es el usuario personal
            return "redirect:/personal";  // Redirige a la página personal
        }
        // Si no hay coincidencias, vuelve a intentar el login
        return "redirect:/login";
    }
    */
}

