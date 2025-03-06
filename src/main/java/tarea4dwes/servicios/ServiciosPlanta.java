package tarea4dwes.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tarea4dwes.modelo.Planta;
import tarea4dwes.repositories.PlantaRepository;


@Service
public class ServiciosPlanta {

	@Autowired
	PlantaRepository plantarepos;
	
	
	public void insertarPlanta(Planta p) {
		plantarepos.saveAndFlush(p);
	}
	public int validarInsercion(Planta p) {
		
			if(plantarepos.existeCodigo(p))
			return -1;
		
			if(p.getNombrecomun().length()>30||p.getNombrecomun().length()<3)
		
			return -2;
			if(!p.getNombrecomun().matches("^[a-zA-Z ]+$"))
			
			return -3;
			if(p.getNombrecientifico().length()>30||p.getNombrecientifico().length()<3)
			
			return -4;
			if(!p.getNombrecientifico().matches("^[a-zA-Z ]+$"))	
			
			return -5;
			if(!p.getCodigo().matches("^[a-zA-Z ]+$"))
			{
			return -6;
			}
		return 0;
		
	}
	public boolean validarPl(Planta p) {
		if(plantarepos.existeCodigo(p)) return true;
	
	return false;
	}
	
	  public List<Planta> verPlantas() {
	        return plantarepos.findAll();
	    }
	  public int verificarModificacion(Planta p, List<Planta> pl) {

			

		  if (!p.getNombrecomun().matches("[a-zA-Z\\s]{3,100}"))
 {

				return -2;
			}

		  if (!p.getNombrecientifico().matches("[a-zA-Z\\s]{3,100}"))
 {

				return -3;
			}
		    Optional<Planta> plantaAntiguaOptional = plantarepos.findById(p.getId());
		    

		    if (!plantaAntiguaOptional.isPresent()) {
		        return -5; 
		    }


		    Planta plantaAntigua = plantaAntiguaOptional.get();

		
		    if (p.getNombrecientifico().equals(plantaAntigua.getNombrecientifico()) &&
		        p.getNombrecomun().equals(plantaAntigua.getNombrecomun())) {
		        return -4; 
		    }

			return 1;
		}

	  
	  public void modificarplanta(Planta planta) {
		    Optional<Planta> plantaExistente = plantarepos.findById(planta.getId());

		    if (plantaExistente.isPresent()) {
		        Planta p = plantaExistente.get();
		        p.setNombrecomun(planta.getNombrecomun());
		        p.setNombrecientifico(planta.getNombrecientifico());
		        plantarepos.saveAndFlush(p);
		    } else {
		        throw new RuntimeException("Planta no encontrada con ID: " + planta.getId());
		    }
		}

	  
	  
	  
	  public int procesarCodigo(String codigo,ArrayList<String> cods) {
			
		    if (cods.contains(codigo)) {
		        
		        return 1;
		    } else if (plantarepos.existsByCodigo(codigo)) {
		    	cods.add(codigo);
		      
		     
		    	 
		    }else {
		    	
		    	return 2;
		}
			return 3;
		    
		}
	  
	  
	  public boolean existeCodigoPlanta(String c) {
		if(plantarepos.findByCodigo(c)==null)  return false;
		  
		else return true;
	  }
	public Optional<Planta> obtenerPlantaPorId(Long id) {
		 return plantarepos.findById(id);
	}
	  
	  
	}

		  
	
	  
