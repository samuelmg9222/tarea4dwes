package tarea4dwes.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import tarea4dwes.modelo.Persona;
import tarea4dwes.repositories.CredencialRepository;
import tarea4dwes.repositories.PersonaRepository;




@Service
public class ServiciosPersona {
@Autowired
PersonaRepository personarepos;
@Autowired
CredencialRepository credencialrepos;

public List<Persona> verPersonas(){
	return personarepos.findAll();
}


public boolean existePersona(Long i) {
	 if(personarepos.findById(i)==null)return false;
	 else return true;
	  
 }
public int verificarPersona(String nombre,String email,String usuario,String password) {
	if(!nombre.matches("^[A-Za-z]+([ -][A-Za-zÀ-ÿ]+)*$")) {
		return -1;
	}
	if(!email.matches("^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$")) {
		return -2;
	}
	if(!usuario.matches("^[A-Za-z0-9]{3,}$")) {
		return -3;
	}
	if(!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
		return -4;
	}
	 if(credencialrepos.existsByUsuario(usuario)) 
         	{
         	  
         	  return -5;
          }
	 if(personarepos.existsByEmail(email)) {
	         	 
	         	  return -6;
	          }
	
	return 1;
}
public boolean insertarPersona(Persona pers) {
    return personarepos.saveAndFlush(pers) != null;
}
public String obtenerNombrePorId(Long id) {
    return personarepos.findNombreById(id);
}


public Optional<Persona> obtenerPersonaPorId(Long i) {
	Optional<Persona> perosna=personarepos.findById(i);
	return perosna;

}

}

