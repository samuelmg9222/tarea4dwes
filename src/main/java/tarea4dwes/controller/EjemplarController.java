package tarea4dwes.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
public class EjemplarController {
	
	@Autowired
	ServiciosMensaje servmensaje;
	
	@Autowired
	ServiciosPlanta servplant;
	
	@Autowired
	ServiciosEjemplar servejemplar;

	@Autowired
	ServiciosPersona servpersona;
	

	@Autowired
	ServiciosCredencial servcredencial;
	
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
	    public String registrarEjemplar(@ModelAttribute Ejemplar ejemplar, Authentication authentication, RedirectAttributes redirectAttributes) {
	        try {
	            // Obtener el username desde Authentication en vez de HttpSession
	            String username = authentication != null ? authentication.getName() : null;

	            if (username == null) {
	                redirectAttributes.addFlashAttribute("message", "Debes iniciar sesión antes de registrar un ejemplar.");
	                return "redirect:/login"; 
	            }

	            // Obtener las credenciales de la base de datos usando el username
	            Credenciales credencial = servcredencial.obtenerCredencialPorUsername(username);
	            
	            if (credencial == null) {
	                redirectAttributes.addFlashAttribute("message", "Credenciales no encontradas.");
	                return "redirect:/login";
	            }

	            // Obtener la persona asociada a las credenciales
	            Persona persona = credencial.getPersona(); 
	            if (persona == null) {
	                redirectAttributes.addFlashAttribute("message", "No se encontró la persona asociada.");
	                return "redirect:/login";  
	            }

	            // Generar el nombre del ejemplar
	            String name = servejemplar.generarNombreEjemplar(ejemplar.getPlanta().getCodigo());
	            ejemplar.setNombre(name);
ejemplar.setDisponible(true);
	            String nombrePlanta = ejemplar.getPlanta().getNombrecomun();
	            LocalDateTime fechaH = LocalDateTime.now();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	            String fechaformated = fechaH.format(formatter);

	            // Crear el mensaje con la persona asociada
	            Mensaje mensaje = new Mensaje(fechaH, servmensaje.generarMensaje(persona.getId(), fechaformated), persona, ejemplar);

	            int resultado = servejemplar.verificarInsercion(ejemplar.getPlanta().getCodigo());
	            switch (resultado) {
	                case 1:
	                    servejemplar.insertarEjempalr(ejemplar);
	                    servmensaje.insertarMensaje(mensaje);
	                    redirectAttributes.addFlashAttribute("message", "Ejemplar de " + nombrePlanta.toLowerCase() + " registrado con éxito");
	                    return "redirect:/nuevoejemplar"; 

	                case -2:
	                    redirectAttributes.addFlashAttribute("message", "Error al generar el nombre del ejemplar");
	                    return "redirect:/nuevoejemplar"; 

	                default:
	                    redirectAttributes.addFlashAttribute("message", "Error desconocido al intentar insertar el ejemplar");
	                    return "redirect:/nuevoejemplar"; 
	            }

	        } catch (Exception e) {
	            redirectAttributes.addFlashAttribute("message", "Hubo un error al registrar el ejemplar.");
	            return "redirect:/nuevoejemplar"; 
	        }
	    }

	    @GetMapping("/crearmensaje")
	    public String crearmensajeEjemplar(Model model) {

	        List<Ejemplar> ejemplares = servejemplar.verEjemplares();
	        model.addAttribute("ejemplares", ejemplares);
	        return "crearmensaje";
	    }

	  
	    @GetMapping("/formulariocrearmensaje/{id}")
	    public String formulariocrearmensaje(@PathVariable("id") Long idejemplar, Model model) {
	       Ejemplar ejemplar = servejemplar.obtenerEjempalrPorId(idejemplar).orElse(null);
	        if (ejemplar!=null) {
	            model.addAttribute("ejemplar", ejemplar);
	            return "formulariocrearmensaje";
	        } else {
	            model.addAttribute("error", "Ejemplar no encontrado");
	            return "redirect:/crearmensaje"; 
	        }
	    }

	    @PostMapping("/crearmensaje/{id}")
	    public String crearmensaje(@PathVariable("id") Long id, 
	                                @ModelAttribute Ejemplar ejemplar, 
	                                @RequestParam("mensajeTexto") String mensajeTexto, 
	                                BindingResult result, 
	                                Model model, 
	                                Authentication authentication) {

	        // Obtener el nombre de usuario de la sesión
	    	   String username = authentication != null ? authentication.getName() : null;

	            if (username == null) {
	             
	                return "redirect:/login"; 
	            }

	            // Obtener las credenciales de la base de datos usando el username
	            Credenciales credencial = servcredencial.obtenerCredencialPorUsername(username);
	            
	            if (credencial == null) {
	            
	                return "redirect:/login";
	            }

	            // Obtener la persona asociada a las credenciales
	            Persona persona = credencial.getPersona(); 
	            if (persona == null) {
	            
	                return "redirect:/login";  
	            }

	        // Obtener el ejemplar usando el id
	        Ejemplar ejemplar2 = servejemplar.obtenerEjempalrPorId(id).orElse(null);

	        if (ejemplar2 != null) {
	            LocalDateTime fechaH = LocalDateTime.now();
	            
	            // Verificar si el mensaje cumple con las validaciones
	            int verificarmensaje = servmensaje.verificarMensajeIntroducido(mensajeTexto);
	            
	            switch (verificarmensaje) {
	                case -1:
	                    model.addAttribute("error", "Error: El mensaje no puede quedar vacío.");
	                    return "formulariocrearmensaje";
	                    
	                case -2:
	                    model.addAttribute("error", "Error: El mensaje debe de tener entre 5 y 100 caracteres.");
	                    return "formulariocrearmensaje";
	                    
	                case -3:
	                    model.addAttribute("error", "Error: El mensaje solo puede tener números, letras mayúsculas y minúsculas sin tildes y ,.:@/");
	                    return "formulariocrearmensaje";
	                    
	                case 1:
	                    // Obtener las credenciales usando el nombre de usuario
	                    Credenciales credenciales = servcredencial.obtenerCredencialPorUsername(username);
	                    
	                    if (credenciales == null) {
	                        model.addAttribute("error", "No se encontraron credenciales para el usuario.");
	                        return "formulariocrearmensaje";  // Si no se encuentran credenciales, mostrar error
	                    }

	                 
	                    
	                    if (persona == null) {
	                        model.addAttribute("error", "No se encontró la persona asociada a las credenciales.");
	                        return "formulariocrearmensaje";  // Si no hay persona, mostrar error
	                    }

	                    // Crear el mensaje
	                    Mensaje mensaje = new Mensaje();
	                    mensaje.setMensaje(mensajeTexto);
	                    mensaje.setEjemplar(ejemplar2);
	                    mensaje.setPersona(persona);  // Usar la persona asociada al usuario actual
	                    mensaje.setFechahora(fechaH);

	                    // Insertar el mensaje en la base de datos
	                    servmensaje.insertarMensaje(mensaje);

	                    model.addAttribute("success", "Mensaje creado correctamente");
	                    break;
	            }
	        } else {
	            model.addAttribute("error", "El ejemplar no existe.");
	        }

	        return "formulariocrearmensaje";
	    }

	    
	 
	  
	    
	    
	    

	    @GetMapping("/filtrartipoplanta")
		public String filtrartipoplanta(Model model) {
		 List<Planta> plantas = servplant.verPlantas();
	     model.addAttribute("plantas", plantas);
		return "filtrartipoplanta";
		
	}
	    @PostMapping("/filtrartipoplantamostrar")
	    public String filtrartipoplantamostrar(@RequestParam List<Long> ids, Model model) {

	        if (ids == null || ids.isEmpty()) {
	            model.addAttribute("error", "Debe seleccionar al menos una planta.");
	            return "filtrartipoplanta"; 
	        }

	        List<Ejemplar> todosEjemplares = new ArrayList<>();

	        for (Long id : ids) {
	            Planta planta = servplant.obtenerPlantaPorId(id).orElse(null);
	            if (planta != null) {
	                List<Ejemplar> ejemplaresDePlanta = servejemplar.verEjemplaresPorCodigoPlanta(planta.getCodigo());
	                todosEjemplares.addAll(ejemplaresDePlanta);
	            }
	        }

	        model.addAttribute("ejemplaresdeplanta", todosEjemplares);
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
	    @GetMapping("/filtrarmensajesporplanta")
		public String filtrarmensajeporplanta(Model model) {
		 List<Planta> plantas = servplant.verPlantas();
	     model.addAttribute("plantas", plantas);
		return "filtrarmensajesporplanta";
		
	}
	    @GetMapping("/filtrarmensajeporplantamostrar/{id}")
	    public String filtrarmensajeporplantamostrar(@PathVariable Long id, Model model) {

	        if (id == null) {
	            model.addAttribute("error", "ID de planta inválido.");
	            return "filtrarmensajesporplanta"; 
	        }


	        Planta planta = servplant.obtenerPlantaPorId(id).orElse(null);

	        if (planta == null) {
	            model.addAttribute("error", "No se encontró la planta con el ID proporcionado.");
	            return "filtrarmensajesporplanta"; 
	        }

	        List<Mensaje> mensajesdePlanta = servmensaje.filtrarMensajePorPlanta(planta);
	        model.addAttribute("mensajes", mensajesdePlanta);

	        return "filtrarmensajeporplantamostrar"; 
	    }
	    @GetMapping("/filtrarmensajesporfecha")
	    public String filtrarMensajesPorFecha() {
	    
	        return "filtrarmensajesporfecha";  
	    }

	    @GetMapping("/filtrarmensajesporfechamostrar")
	    public String filtrarMensajesPorFechaMostrar(
	            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
	            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin,
	            Model model) {

	        if (fechaInicio == null || fechaFin == null || fechaInicio.isAfter(fechaFin)) {
	            model.addAttribute("error", "Rango de fechas inválido.");
	            return "filtrarmensajesporfecha";  
	        }


	        fechaInicio = fechaInicio.truncatedTo(ChronoUnit.MINUTES);
	        fechaFin = fechaFin.truncatedTo(ChronoUnit.MINUTES);


	        List<Mensaje> mensajes = servmensaje.filtrarMensajeRangoFechas(fechaInicio, fechaFin);
	        model.addAttribute("mensajes", mensajes);

	        return "filtrarmensajesporfechamostrar";  
	    }
	
}