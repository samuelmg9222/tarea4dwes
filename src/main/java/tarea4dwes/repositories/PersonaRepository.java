package tarea4dwes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tarea4dwes.modelo.Persona;



@Repository
public interface PersonaRepository extends JpaRepository <Persona,Long>{
	 boolean existsByEmail(String email);
	 @Query("SELECT p.nombre FROM Persona p WHERE p.id = :id")
	   String findNombreById(Long id);
	 String findEmailById(Long id);
}
