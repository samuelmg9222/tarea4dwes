package tarea4dwes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import tarea4dwes.modelo.Cliente;
import tarea4dwes.modelo.Credenciales;
import tarea4dwes.modelo.Planta;
import tarea4dwes.servicios.ServiciosCliente;
import tarea4dwes.servicios.ServiciosCredencial;
import tarea4dwes.servicios.ServiciosPlanta;

@Controller
public class ClienteController {
	@Autowired
	ServiciosPlanta servplant;
	@Autowired
	ServiciosCredencial servcredencial;
	@Autowired
	ServiciosCliente servcliente;
	@GetMapping("/nuevocliente")
    public String mostrarFormulario(Model model,Authentication authentication) {
        model.addAttribute("cliente", new Cliente());
        return "nuevocliente";
    }

	@PostMapping("/registrar-cliente")
	public String crearCliente(@ModelAttribute Cliente cliente, Model model) {
	    int validacion = servcliente.validarCliente(cliente);

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
