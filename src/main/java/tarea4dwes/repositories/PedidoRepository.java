package tarea4dwes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tarea4dwes.modelo.Credenciales;
import tarea4dwes.modelo.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository <Pedido,Long>{

}
