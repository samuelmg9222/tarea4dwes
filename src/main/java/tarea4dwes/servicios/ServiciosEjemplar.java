package tarea4dwes.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tarea4dwes.modelo.Ejemplar;
import tarea4dwes.modelo.Planta;
import tarea4dwes.repositories.EjemplarRepository;
import tarea4dwes.repositories.PlantaRepository;




@Service
public class ServiciosEjemplar {

	
	@Autowired
	EjemplarRepository ejemplarrepos;
	@Autowired
	PlantaRepository plantarepos;

	
	
	public void insertarEjempalr(Ejemplar e) {
		ejemplarrepos.saveAndFlush(e);
	}
	
	
	public String generarNombreEjemplar(String codigo) {
	     Planta planta = plantarepos.findByCodigo(codigo);
	     long contador = ejemplarrepos.countByNombreStartingWith(planta.getCodigo());
	     return planta.getCodigo() + "_" + (contador + 1);
	}
	  public int verificarInsercion(String codigo, List<Planta> pl, int i) {

			if (i < 1 || (i - 1) >= pl.size()) {
				return -1;
			}

			if (generarNombreEjemplar(codigo)==null) {

				return -2;
			}


			return 1;
		}
	  
	  public List<Ejemplar> verEjemplares() {
return ejemplarrepos.findAll();
	


}
	  public List<Ejemplar> verEjemplaresPorCodigoPlanta(String codigoPlanta) {
		    List<Ejemplar> ejemplaresFiltrados = new ArrayList<>();
		    List<Ejemplar> todosEjemplares = ejemplarrepos.findAll(); 
		    for (Ejemplar ejemplar : todosEjemplares) {
		        if (ejemplar.getPlanta().getCodigo().equals(codigoPlanta)) {
		            ejemplaresFiltrados.add(ejemplar);
		        }
		    }
		    return ejemplaresFiltrados;
		}
	  public boolean existeEjemplar(Long i) {
		 if(ejemplarrepos.findById(i)==null)return false;
		 else return true;
		  
	  }
	  
	  }
