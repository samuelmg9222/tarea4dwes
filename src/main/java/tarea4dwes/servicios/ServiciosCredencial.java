package tarea4dwes.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tarea4dwes.modelo.Credenciales;
import tarea4dwes.repositories.CredencialRepository;


@Service
public class ServiciosCredencial {

	
	@Autowired
	CredencialRepository credencialesRepository;
	
	
	
	  public Credenciales encontrarCredenciales(String p) {
	        return credencialesRepository.findByUsuario(p);
		
		
		
	}
	
	   public int registro(String usuario, String contraseña){
	        Credenciales cred =credencialesRepository.findByUsuario(usuario);
	        
	        if (cred == null) {
	            return -1;
	        }
	        if (usuario.toLowerCase().equals("admin") && contraseña.equals("admin")){
	            return 0;
	        }

	        if (usuario.equalsIgnoreCase(cred.getUsuario()) && contraseña.equals(cred.getPassword())){
	            return 1;
	        }
	        return -1;
	    }
	   
	   public boolean insertarCredenciales(Credenciales cr) {
		    return credencialesRepository.saveAndFlush(cr) != null;
		}
}
