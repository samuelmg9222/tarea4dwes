package tarea4dwes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tarea4dwes.modelo.Credenciales;




@Repository
public interface CredencialRepository extends JpaRepository <Credenciales,Long>{

	Credenciales findByUsuario(String p);
	boolean existsByUsuario(String usuario);
}
