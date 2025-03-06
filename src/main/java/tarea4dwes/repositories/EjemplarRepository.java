package tarea4dwes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tarea4dwes.modelo.Ejemplar;
import tarea4dwes.modelo.Planta;




@Repository
public interface EjemplarRepository extends JpaRepository <Ejemplar,Long> {

	int countByNombreStartingWith(String nombreComun);
	   List<Ejemplar> findByPlanta(Planta planta);

}