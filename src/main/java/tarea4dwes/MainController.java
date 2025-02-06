package tarea4dwes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
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
    private ServiciosEjemplar servejemplar; 
    @Autowired
    private ServiciosMensaje servmensaje; 
    @Autowired
    private ServiciosPersona servpersona;  

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
    

 @GetMapping("/listaejemplares")
 public String verMensjaesEjempalr(Model model) {
     List<Ejemplar> ejemplares = servejemplar.verEjemplares();
     model.addAttribute("ejemplares", ejemplares);
     return "listaejemplares"; 
 }

 @GetMapping("/listaejemplares/{id}")
 public String vermensajes(@PathVariable("id") Long id, Model model) {
     Optional<Ejemplar> ejemplar = servejemplar.obtenerEjempalrPorId(id);
     if (ejemplar.isPresent()) {
         model.addAttribute("ejemplar", ejemplar.get());
         return "vermensajesporejemplar";
     }
     model.addAttribute("error", "Planta no encontrada");
     return "redirect:/listaejemplares"; 
 }
    
 @GetMapping("/vermensajesporejemplar/{id}")
 public String findMessagesByEjemplar(@PathVariable("id") Long id, Model model) {
     Optional<Ejemplar> ejemplar = servejemplar.obtenerEjempalrPorId(id);
     if (ejemplar.isPresent()) {
         List<Mensaje> mensajes = servmensaje.filtrarMensajePorEjemplar(ejemplar.get());
         model.addAttribute("mensajes", mensajes);
         model.addAttribute("ejemplar", ejemplar.get());
         return "vermensajesporejemplar";
     }
     model.addAttribute("error", "Ejemplar no encontrado");
     return "redirect:/listaejemplares";
 }
    
    @GetMapping("/nuevoejemplar")
    	public String nuevoEjemplar(Model model) {
    	 List<Planta> plantas = servplant.verPlantas();
         model.addAttribute("plantas", plantas);
    	return "nuevoejemplar";
    	
    }
    
    @PostMapping("/nuevoejemplar")
    public String registrarEjemplar(@ModelAttribute Ejemplar ejemplar, RedirectAttributes redirectAttributes) {
        try {
        	String name=servejemplar.generarNombreEjemplar(ejemplar.getPlanta().getCodigo());
        	ejemplar.setNombre(name);
         
           
            String nombrePlanta = ejemplar.getPlanta().getNombrecomun();
            LocalDateTime fechaH = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		    String fechaformated= fechaH.format(formatter);
		    Long admin =1l;
		   Persona per= servpersona.obtenerPersonaPorId(admin).orElse(null);
		   Mensaje mensaje =new Mensaje(fechaH,servmensaje.generarMensaje(admin, fechaformated),per,ejemplar);
		int resultado =servejemplar.verificarInsercion(ejemplar.getPlanta().getCodigo());
		    switch (resultado) {
			case 1:
				 servejemplar.insertarEjempalr(ejemplar);
					servmensaje.insertarMensaje(mensaje);
				 redirectAttributes.addFlashAttribute("message", "Ejemplar de "+nombrePlanta.toLowerCase()+" registrado con éxito");
		            return "redirect:/nuevoejemplar"; 
			
			
			 
			case -2:
				redirectAttributes.addFlashAttribute("message","Error al generar el nombre del ejemplar");
				 return "redirect:/nuevoejemplar"; 
			
			
			default:
				redirectAttributes.addFlashAttribute("message","Error desconocido al intentar insertar el ejemplar");
				return "redirect:/nuevoejemplar"; 
			}
           
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Hubo un error al registrar el ejemplar.");
            return "redirect:/nuevoejemplar"; 
        }
    }
    @GetMapping("/crearmensaje")
    public String crearmensajeEjemplar(Model model) {
        // Obtener todos los ejemplares
        List<Ejemplar> ejemplares = servejemplar.verEjemplares();
        model.addAttribute("ejemplares", ejemplares);
        return "crearmensaje"; // Vista para crear un nuevo mensaje
    }

    @GetMapping("/crearmensaje/{id}")
    public String crearmensajes(@PathVariable("id") Long id, Model model) {
        // Obtener el ejemplar con el id especificado
        Optional<Ejemplar> ejemplar = servejemplar.obtenerEjempalrPorId(id);
        if (ejemplar.isPresent()) {
            model.addAttribute("ejemplar", ejemplar.get()); // Pasar ejemplar al modelo
            return "formulariocrearmensaje"; // Mostrar el formulario para crear mensaje
        }
        model.addAttribute("error", "Ejemplar no encontrado");
        return "redirect:/crearmensaje"; // En caso de error, redirigir a la página de ejemplares
    }

    @GetMapping("/formulariocrearmensaje")
    public String mostrarForm(Model model) {
        model.addAttribute("mensaje", new Mensaje()); // Crear nuevo objeto mensaje
        return "formulariocrearmensaje"; // Formulario para crear mensaje
    }

    @PostMapping("/formulariocrearmensaje")
    public String registrarMensaje(@ModelAttribute Mensaje mensaje, 
                                   @RequestParam("ejemplar.id") Long ejemplarId, 
                                   BindingResult result, 
                                   Model model) {

        // Si hay errores de validación, se regresa al formulario
        if (result.hasErrors()) {
            return "formulariocrearmensaje";
        }

        // Validación del mensaje
        int validacion = servmensaje.verificarMensajeIntroducido(mensaje.getMensaje());
        switch (validacion) {
            case -1:
                model.addAttribute("error", "El mensaje no puede estar vacío.");
                return "formulariocrearmensaje"; 
            case -2:
                model.addAttribute("error", "El mensaje debe tener entre 5 y 100 caracteres.");
                return "formulariocrearmensaje"; 
            case -3:
                model.addAttribute("error", "El mensaje solo puede contener letras, números y los siguientes caracteres especiales: , . / : @");
                return "formulariocrearmensaje"; 
            case 1:
                Optional<Ejemplar> ejemplar = servejemplar.obtenerEjempalrPorId(ejemplarId);
                if (ejemplar.isPresent()) {
                    mensaje.setEjemplar(ejemplar.get());

                    // Establecemos la persona (puedes buscarla en tu base de datos)
                    Persona p = new Persona();
                    mensaje.setPersona(p);
                    mensaje.setFechahora(LocalDateTime.now());

                    servmensaje.insertarMensaje(mensaje);
                    model.addAttribute("success", "El mensaje se ha registrado correctamente.");
                    return "formulariocrearmensaje"; 
                } else {
                    model.addAttribute("error", "Ejemplar no encontrado.");
                    return "formulariocrearmensaje";
                }
            default:
                model.addAttribute("error", "Ha ocurrido un error inesperado");
                return "formulariocrearmensaje"; 
        }
    }
    @GetMapping("/nuevapersona")
    public String mostrarFormularioPersona(Model model) {
    
        model.addAttribute("persona", new Persona());
        model.addAttribute("credenciales", new Credenciales());
        return "nuevapersona";
    }

    @PostMapping("/registrar-persona")
    public String registrarPersona( Persona persona,Credenciales credenciales, BindingResult result, Model model) {
     
        if (result.hasErrors()) {
            
            return "nuevapersona";
        }

        int validacion = servpersona.verificarPersona(persona.getNombre(), persona.getEmail(), credenciales.getUsuario(), credenciales.getPassword());
        	
        		switch (validacion) {
                
                case -1:
                	 model.addAttribute("error", "Solo puede contener letras sin tildes, con espacios o guiones permitidos en nombres compuestos, y debe tener entre 2 y 50 caracteres.");
                     return "nuevapersona"; 
                case -2:
                	 model.addAttribute("error", "El formato del correo electrónico es incorrecto; asegúrate de que tenga un usuario válido, un '@' y un dominio correcto (ej. usuario@example.com)..");
                     return "nuevapersona"; 
                case -3:
                	 model.addAttribute("error", "Debe tener entre 3 y 20 caracteres, solo letras, números o guiones bajos, sin espacios ni caracteres especiales.");
                     return "nuevapersona"; 
				case -4:
                	 model.addAttribute("error", "Solo se permiten contraseñas con letras y números, con un mínimo de 8 caracteres");
                     return "nuevapersona"; 
				case -5:   
                    model.addAttribute("error", "Ese nombre de usuario ya existe, intentalo de nuevo con otro");
                    return "nuevapersona"; 
                    
                case -6:   
                	 model.addAttribute("error", "Ese email ya existe,intentalo de nuevo con otro");
                	 return "nuevapersona"; 
                default:
                	model.addAttribute("success", "Persona insertada correctamente");
                    servpersona.insertarPersona(persona);
                    servcredencial.insertarCredenciales(credenciales);
                    return "nuevapersona"; 
                }
                
}
    @GetMapping("/filtrartipoplanta")
	public String filtrartipoplanta(Model model) {
	 List<Planta> plantas = servplant.verPlantas();
     model.addAttribute("plantas", plantas);
	return "filtrartipoplanta";
	
}
    @GetMapping("/filtrartipoplantamostrar/{id}")
    public String filtrartipoplantamostrar(@PathVariable Long id, Model model) {

        if (id == null) {
            model.addAttribute("error", "ID de planta inválido.");
            return "filtrartipoplanta"; 
        }


        Planta planta = servplant.obtenerPlantaPorId(id).orElse(null);

        if (planta == null) {
            model.addAttribute("error", "No se encontró la planta con el ID proporcionado.");
            return "filtrartipoplanta"; 
        }

        List<Ejemplar> ejemplaresDePlanta = servejemplar.verEjemplaresPorCodigoPlanta(planta.getCodigo());
        model.addAttribute("ejemplaresdeplanta", ejemplaresDePlanta);

        return "filtrartipoplantamostrar"; 
    }

    @GetMapping("/filtrarporpersona")
	public String filtrarporpersona(Model model) {
	 List<Persona> personas = servpersona.verPersonas();
     model.addAttribute("personas", personas);
	return "filtrarporpersona";
	
}
    @GetMapping("/filtrarporpersonamostrar/{id}")
    public String filtrarporpersonamostrar(@PathVariable Long id, Model model) {

        if (id == null) {
            model.addAttribute("error", "ID de persona inválido.");
            return "filtrarporpersona"; 
        }


        Persona persona=servpersona.obtenerPersonaPorId(id).orElse(null);

        if (persona == null) {
            model.addAttribute("error", "No se encontró la persona con el ID proporcionado.");
            return "filtrarporpersona"; 
        }

        List<Mensaje> MensajePersona = servmensaje.filtrarMensajePorPersona(persona);
        model.addAttribute("mensajesdepersona", MensajePersona);

        return "filtrarporpersonamostrar"; 
    }
    @GetMapping("/filtrarmensajeporplanta")
	public String filtrarmensajeporplanta(Model model) {
	 List<Persona> personas = servpersona.verPersonas();
     model.addAttribute("personas", personas);
	return "filtrarmensajeporplanta";
	
}
    @GetMapping("/filtrarporpersonamostrar/{id}")
    public String filtrarporpersonamostrar(@PathVariable Long id, Model model) {

        if (id == null) {
            model.addAttribute("error", "ID de persona inválido.");
            return "filtrarmensajeporplanta"; 
        }


        Persona persona=servpersona.obtenerPersonaPorId(id).orElse(null);

        if (persona == null) {
            model.addAttribute("error", "No se encontró la persona con el ID proporcionado.");
            return "filtrarmensajeporplanta"; 
        }

        List<Mensaje> MensajePersona = servmensaje.filtrarMensajePorPersona(persona);
        model.addAttribute("mensajesdepersona", MensajePersona);

        return "filtrarporpersonamostrar"; 
    }
}