package tarea4dwes.controller;

<<<<<<< HEAD
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
=======
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
>>>>>>> e89eb3d70de556070897c64d97c6f454d4ce03db
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.transaction.Transactional;
import tarea4dwes.modelo.Cliente;
import tarea4dwes.modelo.Credenciales;
import tarea4dwes.modelo.Ejemplar;
import tarea4dwes.modelo.Mensaje;
import tarea4dwes.modelo.Pedido;
import tarea4dwes.modelo.Persona;
import tarea4dwes.modelo.Planta;
import tarea4dwes.servicios.ServiciosCliente;
import tarea4dwes.servicios.ServiciosCredencial;
import tarea4dwes.servicios.ServiciosEjemplar;
import tarea4dwes.servicios.ServiciosMensaje;
import tarea4dwes.servicios.ServiciosPedido;
import tarea4dwes.servicios.ServiciosPersona;
import tarea4dwes.servicios.ServiciosPlanta;

@SessionAttributes("carrito")
=======
import org.springframework.web.bind.annotation.PostMapping;

import tarea4dwes.modelo.Cliente;
import tarea4dwes.modelo.Credenciales;
import tarea4dwes.modelo.Planta;
import tarea4dwes.servicios.ServiciosCliente;
import tarea4dwes.servicios.ServiciosCredencial;
import tarea4dwes.servicios.ServiciosPlanta;

>>>>>>> e89eb3d70de556070897c64d97c6f454d4ce03db
@Controller
public class ClienteController {
	@Autowired
	ServiciosPlanta servplant;
	@Autowired
<<<<<<< HEAD
	ServiciosPedido servpedido;
	@Autowired
	ServiciosMensaje servmensaje;
	@Autowired
	ServiciosPersona servpersona;
	@Autowired
	ServiciosCredencial servcredencial;
	@Autowired
	ServiciosEjemplar servejemplar;
	@Autowired
=======
	ServiciosCredencial servcredencial;
	@Autowired
>>>>>>> e89eb3d70de556070897c64d97c6f454d4ce03db
	ServiciosCliente servcliente;
	@GetMapping("/nuevocliente")
    public String mostrarFormulario(Model model,Authentication authentication) {
        model.addAttribute("cliente", new Cliente());
        return "nuevocliente";
    }

	@PostMapping("/registrar-cliente")
	public String crearCliente(@ModelAttribute Cliente cliente, Model model) {
	    int validacion = servcliente.validarCliente(cliente);

<<<<<<< HEAD
	    System.out.println("Resultado de validación: " + validacion);

	    if (validacion < 0) {
	        String mensajeError = switch (validacion) {
	            case -2 -> "El nombre debe tener entre 3 y 30 caracteres.";
	            case -3 -> "El nombre solo puede contener letras y espacios.";
	            case -4 -> "La fecha de nacimiento no puede estar vacía.";
	            case -5 -> "Debes ser mayor de 18 años.";
	            case -6 -> "El NIF debe tener exactamente 10 caracteres.";
	            case -7 -> "La dirección no puede estar vacía.";
	            case -8 -> "El email no es válido.";
	            case -9 -> "El teléfono debe contener entre 9 y 15 dígitos.";
	            case -10 -> "Ya hay alguien con ese nombre de usuario";
	            case -11 -> "Ya hay alguien con ese teléfono";
	            case -12 -> "Ya hay alguien con ese email";
	            case -13 -> "Ya hay alguien con ese NIF.";
	            default -> "Error desconocido";
	        };
	        model.addAttribute("error", mensajeError);
	        return "nuevocliente";
	    }

	    try {
	        // Convierte a minúsculas y asigna los valores correctamente
	        cliente.setNombre(cliente.getNombre().toLowerCase());
	        cliente.setEmail(cliente.getEmail().toLowerCase());
	        cliente.getCredenciales().setUsuario(cliente.getCredenciales().getUsuario().toLowerCase());
	        
	        servcliente.insertarcliente(cliente);

	        Credenciales cr = cliente.getCredenciales();
	        cr.setUsuario(cr.getUsuario().toLowerCase());

	        cr.setCliente(cliente);
	        servcredencial.insertarCredenciales(cr);

	        Persona per = new Persona();
	        per.setEmail(cliente.getEmail());
	        per.setCredencial(cr);
	        per.setNombre(cliente.getNombre());
	        servpersona.insertarPersona(per);
	        cr.setPersona(per);
	        servcredencial.insertarCredenciales(cr);

	        return "redirect:/login";
	    } catch (Exception e) {
	        model.addAttribute("error", "Ocurrió un error al registrar el cliente.");
	        return "nuevocliente";
	    }
	}

	
	@GetMapping("/cliente")
	public String mostrarPlantas(Model model) {
	    List<Planta> plantas = servplant.verPlantas(); 
	    List<Map<String, Object>> plantasConStock = new ArrayList<>();

	    for (Planta planta : plantas) {
	        int numEjemplaresDisponibles = 0;

	        for (Ejemplar ejemplar : planta.getEjemplares()) {
	            if (ejemplar.isDisponible()) {
	                numEjemplaresDisponibles++;
	            }
	        }

	        
	        Map<String, Object> plantaInfo = new HashMap<>();
	        plantaInfo.put("planta", planta);
	        plantaInfo.put("stockDisponible", numEjemplaresDisponibles);

	       
	        plantasConStock.add(plantaInfo);
	    }

	    model.addAttribute("plantasConStock", plantasConStock);
	   
	    return "cliente";
	}
	
	
	@ModelAttribute("carrito")
    public List<Planta> crearCarrito() {
        return new ArrayList<>(); 
    }

	@GetMapping("/agregarcarrito/{id}")
	public String agregarAlCarrito(@PathVariable("id") Long plantaId,
	                                @ModelAttribute("carrito") List<Ejemplar> carrito,
	                                RedirectAttributes redirectAttributes) {

	    Optional<Planta> optionalPlanta = servplant.obtenerPlantaPorId(plantaId);

	    if (optionalPlanta.isPresent()) {
	        Planta planta = optionalPlanta.get();

	       
	        Ejemplar ejemplarDisponible = planta.getEjemplares().stream()
	            .filter(Ejemplar::isDisponible)
	            .findFirst()
	            .orElse(null);

	        if (ejemplarDisponible != null) {
	            ejemplarDisponible.setDisponible(false);
	            servejemplar.insertarEjempalr(ejemplarDisponible); 

	        
	            carrito.add(ejemplarDisponible);

	            redirectAttributes.addFlashAttribute("mensaje", "Ejemplar agregado al carrito exitosamente.");
	        } else {
	            redirectAttributes.addFlashAttribute("mensajeError", "No hay más ejemplares disponibles de esta planta.");
	        }
	    } else {
	        redirectAttributes.addFlashAttribute("mensajeError", "Planta no encontrada.");
	    }



	    return "redirect:/cliente";  
	}



	
	@GetMapping("/carrito")
	public String mostrarCarrito(@ModelAttribute("carrito") List<Ejemplar> carrito, Model model) {
	    if (carrito.isEmpty()) {
	        model.addAttribute("mensajeError", "Tu carrito está vacío.");
	    } else {
	        model.addAttribute("carrito", carrito);
	    }
	    return "carrito"; 
	}

	@PostMapping("/confirmarPedido")
	public String confirmarPedido(@ModelAttribute("carrito") List<Ejemplar> carrito,
	                              RedirectAttributes redirectAttributes,
	                              Authentication authentication) {

	    String username = authentication != null ? authentication.getName() : null;

	    if (username == null) {
	        redirectAttributes.addFlashAttribute("mensajeError", "Debes iniciar sesión para realizar un pedido.");
	        return "redirect:/login";  
	    }

	    Credenciales credenciales = servcredencial.obtenerCredencialPorUsername(username);
	    Cliente cliente = credenciales.getCliente();

	    if (carrito.isEmpty()) {
	        redirectAttributes.addFlashAttribute("mensajeError", "No puedes confirmar un carrito vacío.");
	        return "redirect:/carrito";
	    }

	    try {
	    	
	        Pedido pedido = new Pedido();
	        pedido.setFecha(LocalDate.now());
	        pedido.setCliente(cliente);
	        servpedido.guardarPedido(pedido);


	        for (Ejemplar ejemplar : carrito) {
	            ejemplar.setDisponible(false);
	            ejemplar.setPedido(pedido);
	            servejemplar.insertarEjempalr(ejemplar); 

	    
	            Mensaje mensaje = new Mensaje();
	            mensaje.setMensaje("Pedido realizado por cliente con id: " + cliente.getId());
	            mensaje.setEjemplar(ejemplar);
	            mensaje.setPersona(cliente.getCredenciales().getPersona());
	            mensaje.setFechahora(LocalDateTime.now());
	            servmensaje.insertarMensaje(mensaje);
	        }

	        pedido.setEjemplares(carrito);
	        servpedido.guardarPedido(pedido);
	        carrito.clear();  

	        redirectAttributes.addFlashAttribute("mensaje", "Tu pedido ha sido confirmado con éxito.");
	        return "redirect:/cliente";

	    } catch (Exception e) {
	        e.printStackTrace();
	        redirectAttributes.addFlashAttribute("mensajeError", "Ocurrió un error al confirmar el pedido. Inténtalo de nuevo.");
	        return "redirect:/cliente";
	    }
	}

	@PostMapping("/eliminarDelCarrito")
	public String eliminarDelCarrito(@RequestParam("idEjemplar") Long ejemplarId,
	                                 @ModelAttribute("carrito") List<Ejemplar> carrito,
	                                 RedirectAttributes redirectAttributes) {

	    for (int i = 0; i < carrito.size(); i++) {
	        if (carrito.get(i).getId().equals(ejemplarId)) {
	            carrito.remove(i);
	            redirectAttributes.addFlashAttribute("mensaje", "Ejemplar eliminado del carrito.");
	            return "redirect:/carrito"; 
	        }
	    }

	    redirectAttributes.addFlashAttribute("mensajeError", "No se encontró el ejemplar en el carrito.");
	    return "redirect:/carrito";
	}

}

=======
	    switch (validacion) {
	        case -2:
	            model.addAttribute("error", "El nombre debe tener entre 3 y 30 caracteres.");
	            return "nuevocliente";
	        case -3:
	            model.addAttribute("error", "El nombre solo puede contener letras y espacios.");
	            return "nuevocliente";
	        case -4:
	            model.addAttribute("error", "La fecha de nacimiento no puede estar vacía.");
	            return "nuevocliente";
	        case -5:
	            model.addAttribute("error", "Debes ser mayor de 18 años.");
	            return "nuevocliente";
	        case -6:
	            model.addAttribute("error", "El NIF debe tener exactamente 10 caracteres.");
	            return "nuevocliente";
	        case -7:
	            model.addAttribute("error", "La dirección no puede estar vacía.");
	            return "nuevocliente";
	        case -8:
	            model.addAttribute("error", "El email no es válido.");
	            return "nuevocliente";
	        case -9:
	            model.addAttribute("error", "El teléfono debe contener entre 9 y 15 dígitos.");
	            return "nuevocliente";
	        case -10:
	            model.addAttribute("error", "Ya hay alguien con ese nombre de usuario");
	            return "nuevocliente";
	        case -11:
	            model.addAttribute("error", "Ya hay alguien con ese telefono");
	            return "nuevocliente";
	        case -12:
	            model.addAttribute("error", "Ya hay alguien con ese email");
	            return "nuevocliente";
	        case -13:
	            model.addAttribute("error", "Ya hay alguien con ese nif.");
	            return "nuevocliente";
	        case 1:
	        	Credenciales cr =new Credenciales();
	        	cr=cliente.getCredenciales();
	        	cr.setCliente(cliente);
	        	
	            servcliente.insertarcliente(cliente);
	            servcredencial.insertarCredenciales(cliente.getCredenciales());
	            return "redirect:/index";
	        default:
	        	return "index";
	    }
	}
	
	 @GetMapping("/cliente")
	    public String mostrarPlantas(Model model) {
	       
			List<Planta> plantas = servplant.verPlantas();
	        model.addAttribute("plantas", plantas);
	        return "cliente";
	    }

}
>>>>>>> e89eb3d70de556070897c64d97c6f454d4ce03db
