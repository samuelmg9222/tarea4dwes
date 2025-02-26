package tarea4dwes.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tarea4dwes.modelo.Cliente;
import tarea4dwes.repositories.ClienteRepository;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Service
public class ServiciosCliente {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	ServiciosCredencial servcredenciales;
	public int validarCliente(Cliente cliente) {
	    if (cliente.getNombre() == null || cliente.getNombre().length() < 3 || cliente.getNombre().length() > 30) {
	        return -2; 
	    }
	    if (!cliente.getNombre().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
	        return -3; 
	    }
	    if (cliente.getFechanac() == null) {
	        return -4; 
	    }
	    if (Period.between(cliente.getFechanac(), LocalDate.now()).getYears() < 18) {
	        return -5; 
	    }
	    if (cliente.getNif() == null || cliente.getNif().length() != 10) {
	        return -6;
	    }
	    if (cliente.getDireccion() == null || cliente.getDireccion().isEmpty()) {
	        return -7;
	    }
	    if (cliente.getEmail() == null || !cliente.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
	        return -8; 
	    }
	    if (cliente.getTelefono() == null || !cliente.getTelefono().matches("\\d{9,15}")) {
	        return -9;
	    }
	    if(servcredenciales.exiteUser(cliente.getCredenciales().getUsuario())==0) {
	    	return -10;
	    }
	    if(existeTel(cliente.getTelefono())==0) {
	    	return -11;
	    }
	    if(existeEmail(cliente.getEmail())==0) {
	    	return -12;
	    }
	    if(existeNif(cliente.getNif())==0) {
	    	return -13;
	    }
	  
	    return 1; 
	}


	public void insertarcliente(Cliente cliente) {
		clienteRepository.saveAndFlush(cliente);
		
	}
	
	public int existeEmail(String email){
		List<Cliente> Todos=FindAll();
		for(Cliente c: Todos) {
			if(c.getEmail().toLowerCase().equals(email.toLowerCase())) {
				return 0;
			}
		}
		
		return 1;
		
	}
	
	public int existeTel(String tel){
		List<Cliente> Todos=FindAll();
		for(Cliente c: Todos) {
			if(c.getTelefono().toLowerCase().equals(tel.toLowerCase())) {
				return 0;
			}
		}
		
		return 1;
		
	}public int existeNif(String nif){
		List<Cliente> Todos=FindAll();
		for(Cliente c: Todos) {
			if(c.getNif().toLowerCase().equals(nif.toLowerCase())) {
				return 0;
			}
		}
		
		return 1;
		
	}
	
	public List<Cliente> FindAll(){
		return clienteRepository.findAll();
	}

}
