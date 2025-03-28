package tarea4dwes.modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "ejemplares")  
public class Ejemplar implements Serializable{

	private static final long serialVersionUID =1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="idplanta")
	private Planta planta;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="idejemplar")
	private List<Mensaje> mensjaes=new LinkedList <Mensaje>();
	@Column
	private Boolean disponible;
	
	@ManyToOne
	@JoinColumn(name = "idPedido", nullable=true)
	private Pedido pedido;

	
	
	
	public Ejemplar() {
		super();
	}

	public Ejemplar(Long id, String nombre, Planta planta, List<Mensaje> mensjaes, boolean disponible, Pedido pedido) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.planta = planta;
		this.mensjaes = mensjaes;
		this.disponible = disponible;
		this.pedido = pedido;
	}

	public List<Mensaje> getMensjaes() {
		return mensjaes;
	}

	public void setMensjaes(List<Mensaje> mensjaes) {
		this.mensjaes = mensjaes;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Ejemplar(Long id, String nombre, Planta planta) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.planta = planta;
	}
	public Ejemplar(String nombre, Planta planta) {
        this.nombre = nombre;
        this.planta = planta;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Planta getPlanta() {
		return planta;
	}

	public void setPlanta(Planta planta) {
		this.planta = planta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, mensjaes, nombre, planta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ejemplar other = (Ejemplar) obj;
		return Objects.equals(id, other.id) && Objects.equals(mensjaes, other.mensjaes)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(planta, other.planta);
	}

	@Override
	public String toString() {
		return "Ejemplar [id=" + id + ", nombre=" + nombre + ", planta=" + planta + ", mensjaes=" + mensjaes
				+ ", disponible=" + disponible + ", pedido=" + pedido + "]";
	}


	
	
	
}
