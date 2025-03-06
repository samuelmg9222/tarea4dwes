package tarea4dwes.servicios;

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
	
}
