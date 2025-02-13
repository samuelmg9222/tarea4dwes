package tarea4dwes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import tarea4dwes.modelo.Credenciales;
import tarea4dwes.modelo.Persona;
import tarea4dwes.modelo.Planta;
import tarea4dwes.servicios.ServiciosCredencial;
import tarea4dwes.servicios.ServiciosPersona;

@Controller
public class PersonaController {
	
	@Autowired
	ServiciosPersona servpersona;
	@Autowired
	ServiciosCredencial servcredencial;
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
	                	 model.addAttribute("error", "El nombre solo puede contener letras sin tildes, con espacios o guiones permitidos en nombres compuestos, y debe tener entre 2 y 50 caracteres.");
	                     return "nuevapersona"; 
	                case -2:
	                	 model.addAttribute("error", "El formato del correo electrónico es incorrecto; asegúrate de que tenga un usuario válido, un '@' y un dominio correcto , y sin espacios(ej. usuario@example.com)..");
	                     return "nuevapersona"; 
	                case -3:
	                	 model.addAttribute("error", "El nombre de usuario debe tener entre 3 y 20 caracteres, solo letras, números o guiones bajos, sin espacios ni caracteres especiales.");
	                     return "nuevapersona"; 
					case -4:
	                	 model.addAttribute("error", "Solo se permiten contraseñas con letras y números, con un mínimo de 8 caracteres");
	                     return "nuevapersona"; 
					case -5:   
	                    model.addAttribute("error", "Ese nombre de usuario ya existe, intentalo de nuevo con otro");
	                    return "nuevapersona"; 
	                    
	                case -6:   
	                	 model.addAttribute("error", "Ese email ya existe, intentalo de nuevo con otro");
	                	 return "nuevapersona"; 
	                default:
	                	model.addAttribute("success", "Persona insertada correctamente");
	                    servpersona.insertarPersona(persona);
	                    credenciales.setPersona(persona);
	                    servcredencial.insertarCredenciales(credenciales);
	                    return "nuevapersona"; 
	                }
	                
	}
	    
}
