package tarea4dwes.servicios;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tarea4dwes.modelo.Cliente;
import tarea4dwes.modelo.Pedido;
import tarea4dwes.repositories.PedidoRepository;

@Service
public class ServiciosPedido {
@Autowired
PedidoRepository pedidorepos;
	
	public void guardarPedido(Pedido pedido) {
		pedidorepos.saveAndFlush(pedido);
		
	}
	
=======
import org.springframework.stereotype.Service;

@Service
public class ServiciosPedido {

>>>>>>> e89eb3d70de556070897c64d97c6f454d4ce03db
}
