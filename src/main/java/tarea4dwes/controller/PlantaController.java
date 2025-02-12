package tarea4dwes.controller;

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

import tarea4dwes.modelo.Planta;
import tarea4dwes.servicios.ServiciosPlanta;

@Controller 
public class PlantaController {
	
	@Autowired
	ServiciosPlanta servplant;
	
	 @GetMapping("/verplantas")
	    public String mostrarPlantas(Model model) {
	        List<Planta> plantas = servplant.verPlantas();
	        model.addAttribute("plantas", plantas);
	        return "verplantas";
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
	    @GetMapping("/modificarplantapersonal")
	    public String modificarplantapersonal(Model model) {
	        List<Planta> plantas = servplant.verPlantas();
	        model.addAttribute("plantas", plantas);
	        return "modificarplantapersonal"; // Vista con la lista de plantas
	    }

	 
	    @GetMapping("/edicionplantapersonal/{id}")
	    public String edicionplantapersonal(@PathVariable("id") Long id, Model model) {
	        Optional<Planta> planta = servplant.obtenerPlantaPorId(id);
	        if (planta.isPresent()) {
	            model.addAttribute("planta", planta.get());
	            return "edicionplantapersonal";
	        }
	        model.addAttribute("error", "Planta no encontrada");
	        return "redirect:/modificarplantapersonal"; 
	    }

	    // Modificar los datos de la planta
	    @PostMapping("/modificarplantapersonal/{id}")
	    public String modificarplantapersonal(@PathVariable("id") Long id,@ModelAttribute Planta planta, BindingResult result, Model model) {
	    	 List<Planta> plantas=servplant.verPlantas();
	    	 planta.setId(id);
	    	   int validacion = servplant.verificarModificacion(planta, plantas);
	           
	           
	           switch (validacion) {
	          
	               case -2:
	                   model.addAttribute("error", "Error: El nombre comun debe de tener entre 3 y 100 caracteres, solo letras y espacios.");
	                   return "edicionplantapersonal"; 
	               case -3:
	                   model.addAttribute("error", "Error: El nombre cientifico debe de tener entre 3 y 100 caracteres, solo letras y espacios.");
	                   return "edicionplantapersonal"; 
	               case -4:
	                   model.addAttribute("error", "Debe de modificar almenos un campo, no dejarlos iguales.");
	                   return "edicionplantapersonal"; 
	               case 1:
	            		model.addAttribute("success", "Planta modificada correctamente");
	            		servplant.modificarplanta(planta);
	                    return "edicionplantapersonal";
	               default:
	               
	                   return "edicionplantapersonal"; 
	                   
	           }
	       }
}
