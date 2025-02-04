package tarea4dwes;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import tarea4dwes.modelo.Planta;
import tarea4dwes.servicios.ServiciosCredencial;
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

  
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username, 
                                @RequestParam String password, 
                                RedirectAttributes redirectAttributes) {
      
        int result = servcredencial.registro(username, password);

      
        if (result == -1) {
           
            redirectAttributes.addFlashAttribute("error", "Credenciales incorrectas.");
            return "redirect:/login";  
        } else if (result == 0) {
            
            return "admin";  
        } else if (result == 1) {
           
            return "gestiondeplantas";  
        }
       
        return "redirect:/login";
    }
    @Autowired
    private ServiciosPlanta servplant;
    @GetMapping("/verplantas")
    public String mostrarPlantas(Model model) {
        List<Planta> plantas = servplant.verPlantas();
        model.addAttribute("plantas", plantas);
        return "verplantas";
    }
    
    
    @GetMapping("/gestiondeplantas")
    public String gestionDePlantas() {
   
        return "gestiondeplantas";  
    }

  
    @GetMapping("/gestiondeejemplares")
    public String gestionDeEjemplares() {
       
        return "gestiondeejemplares";  
    }


    @GetMapping("/gestiondepersonal")
    public String gestionDePersonal() {
        
        return "gestiondepersonal";  
    }
    

    @GetMapping("/nuevaplanta")
    public String mostrarFormulario(Model model) {
    
        model.addAttribute("planta", new Planta());
        return "nuevaplanta";
    }

    @PostMapping("/registrar-planta")
    public String registrarPlanta( Planta planta, BindingResult result, Model model) {
     
        if (result.hasErrors()) {
            
            return "nuevaplanta";
        }

       
        int validacion = servplant.validarInsercion(planta);
        
        
        switch (validacion) {
            case -1:
                model.addAttribute("error", "El código de la planta ya existe.");
                return "nuevaplanta"; 
            case -2:
                model.addAttribute("error", "El nombre común debe tener entre 3 y 30 caracteres.");
                return "nuevaplanta"; 
            case -3:
                model.addAttribute("error", "El nombre común solo puede contener letras y espacios.");
                return "nuevaplanta"; 
            case -4:
                model.addAttribute("error", "El nombre científico debe tener entre 3 y 30 caracteres.");
                return "nuevaplanta"; 
            case -5:
                model.addAttribute("error", "El nombre científico solo puede contener letras y espacios.");
                return "nuevaplanta";
            case -6:
                model.addAttribute("error", "El código solo puede contener letras.");
                return "nuevaplanta"; 
            default:
            	model.addAttribute("success", "Planta insertada correctamente");
                servplant.insertarPlanta(planta);
                return "nuevaplanta"; 
                
        }
    }
    @GetMapping("/modificarplanta")
    public String listarPlantas(Model model) {
        List<Planta> plantas = servplant.verPlantas();
        model.addAttribute("plantas", plantas);
        return "modificarplanta"; // Vista con la lista de plantas
    }

 
    @GetMapping("/edicionplanta/{id}")
    public String editarPlanta(@PathVariable("id") Long id, Model model) {
        Optional<Planta> planta = servplant.obtenerPlantaPorId(id);
        if (planta.isPresent()) {
            model.addAttribute("planta", planta.get());
            return "edicionplanta";
        }
        model.addAttribute("error", "Planta no encontrada");
        return "redirect:/modificarplanta"; 
    }

    // Modificar los datos de la planta
    @PostMapping("/modificarplanta/{id}")
    public String modificarPlanta(@PathVariable("id") Long id,@ModelAttribute Planta planta, BindingResult result, Model model) {
    	 List<Planta> plantas=servplant.verPlantas();
    	 planta.setId(id);
    	   int validacion = servplant.verificarModificacion(planta, plantas);
           
           
           switch (validacion) {
          
               case -2:
                   model.addAttribute("error", "Error: El nombre comun debe de tener entre 3 y 100 caracteres, solo letras y espacios.");
                   return "edicionplanta"; 
               case -3:
                   model.addAttribute("error", "Error: El nombre cientifico debe de tener entre 3 y 100 caracteres, solo letras y espacios.");
                   return "edicionplanta"; 
               case -4:
                   model.addAttribute("error", "Debe de modificar almenos un campo, no dejarlos iguales.");
                   return "edicionplanta"; 
               case 1:
            		model.addAttribute("success", "Planta modificada correctamente");
            		servplant.modificarplanta(planta);
                    return "edicionplanta";
               default:
               
                   return "edicionplanta"; 
                   
           }
       }
        
    
}
