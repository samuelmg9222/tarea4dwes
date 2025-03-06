package tarea4dwes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tarea4dwes.modelo.Planta;



@Repository
public interface PlantaRepository extends JpaRepository <Planta,Long> {

	default boolean existeCodigo(Planta p) {
		List<Planta> listaplantas = findAll();
		for(Planta aux:listaplantas) {
			if(p.getCodigo().equals(aux.getCodigo()))
				return true;
		}
		
		return false;
	}

	Planta findByCodigo(String codigo);

	boolean existsByCodigo(String codigo);

	

	

	



	
	
	
	
}
                                   