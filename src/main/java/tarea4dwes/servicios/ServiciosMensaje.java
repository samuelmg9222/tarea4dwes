package tarea4dwes.servicios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import tarea4dwes.modelo.Ejemplar;
import tarea4dwes.modelo.Mensaje;
import tarea4dwes.modelo.Persona;
import tarea4dwes.modelo.Planta;
import tarea4dwes.repositories.EjemplarRepository;
import tarea4dwes.repositories.MensajeRepository;
import tarea4dwes.repositories.PersonaRepository;


@Service
public class ServiciosMensaje {
	@Autowired
	MensajeRepository mensajerepos;
	@Autowired
	PersonaRepository personarepos;
	@Autowired
	EjemplarRepository ejemplarrepos;
	
	public String generarMensaje(Long id,String hoy) {
		Optional<Persona> optionalPersona = personarepos.findById(id);
		  
		        Persona per = optionalPersona.get();  
		        Long personaId = per.getId();  
		        return "Fecha: " + hoy+"  Persona: "+personaId;
		    
		  
    }
	public boolean insertarMensaje(Mensaje m) {
		return mensajerepos.saveAndFlush(m) != null;
	        
	        
	    
	}
	public List<Mensaje> filtrarMensajePorEjemplar(Ejemplar ej){
		return mensajerepos.findByEjemplar(ej);
	}
	public List<Mensaje> filtrarMensajePorPersona(Persona p){
		return mensajerepos.findByPersona(p);
	}
	
	public List<Mensaje> filtrarMensajePorPlanta(Planta planta) {
	    List<Ejemplar> ejemplares = ejemplarrepos.findByPlanta(planta); 
	    return mensajerepos.findByEjemplarIn(ejemplares); 
	}
	
	public int verificarfecha(LocalDate fechaInicio,LocalDate fechaFin,LocalDate fechaActual) {
		if (fechaInicio.isAfter(fechaFin)) {
	      
	        return -1;
	    }
		
	    
	    if (fechaInicio.isAfter(fechaActual)) {
	        
	        return -2; 
	    }
		return 1;
	}
	
	public List<Mensaje> filtrarMensajeRangoFechas(LocalDateTime inicio,LocalDateTime fin){
		return mensajerepos.findByFechahoraBetween(inicio,fin);
	}

	public int verificarMensajeIntroducido(String m) {
	    if (m == null || m.trim().isEmpty()) {
	        return -1; 
	    }

	   
	    if (m.length() < 5 || m.length() > 100) {
	        return -2; 
	    }

	    if (!m.matches("[a-zA-Z0-9 ,./:@]*")) {
	        return -3; 
	    }

	    return 1; 
	}





	
}
