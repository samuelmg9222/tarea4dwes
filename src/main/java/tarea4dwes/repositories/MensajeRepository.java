package tarea4dwes.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tarea4dwes.modelo.Ejemplar;
import tarea4dwes.modelo.Mensaje;
import tarea4dwes.modelo.Persona;



@Repository
public interface MensajeRepository extends JpaRepository <Mensaje,Long>{
	List<Mensaje> findByEjemplar(Ejemplar ejemplar);
	List<Mensaje> findByPersona(Persona persona);
	 List<Mensaje> findByEjemplarIn(List<Ejemplar> ejemplares);
	  List<Mensaje> findByFechahoraBetween(LocalDateTime start, LocalDateTime end);
}
