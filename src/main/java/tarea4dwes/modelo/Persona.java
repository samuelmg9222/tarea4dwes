package tarea4dwes.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="personas")
public class Persona implements Serializable {
	private static final long serialVersionUID =1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String nombre;
	@Column
	private String email;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="idpersona")
	private List<Mensaje> mensjaes=new LinkedList<Mensaje>();
	 
	  @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
	    private Credenciales credencial;

	

	 
	 
	 
	public Persona() {
		super();
	}

	public Persona(String nombre, String email) {
		super();
		this.nombre = nombre;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Mensaje> getMensjaes() {
		return mensjaes;
	}

	public void setMensjaes(List<Mensaje> mensjaes) {
		this.mensjaes = mensjaes;
	}

	public Credenciales getCredencial() {
		return credencial;
	}

	public void setCredencial(Credenciales credencial) {
		this.credencial = credencial;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(email, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", email=" + email + "]";
	}
	 

}
	
	