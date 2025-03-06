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
	
	public int existeEmail(String email) {
	    List<Cliente> todos = FindAll();

	    for (Cliente c : todos) {
	        if (c.getEmail() != null && email != null && c.getEmail().equalsIgnoreCase(email)) {
	            return 0; // El email ya existe
	        }
	    }
	    return 1; // No se encontró el email
	}

	public int existeTel(String tel) {
	    List<Cliente> todos = FindAll();

	    for (Cliente c : todos) {
	        if (c.getTelefono() != null && tel != null && c.getTelefono().equalsIgnoreCase(tel)) {
	            return 0; // El teléfono ya existe
	        }
	    }
	    return 1; // No se encontró el teléfono
	}

	public int existeNif(String nif) {
	    List<Cliente> todos = FindAll();

	    for (Cliente c : todos) {
	        if (c.getNif() != null && nif != null && c.getNif().equalsIgnoreCase(nif)) {
	            return 0; // El NIF ya existe
	        }
	    }
	    return 1; // No se encontró el NIF
	}

	
	public List<Cliente> FindAll(){
		return clienteRepository.findAll();
	}

}
