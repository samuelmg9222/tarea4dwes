package tarea4dwes.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Table(name="mensajes")
public class Mensaje implements Serializable{
private static final long serialVersionUID =1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private LocalDateTime fechahora;
	@Column
	private String mensaje;
	
	  @ManyToOne
<<<<<<< HEAD
	  @JoinColumn(name = "idpersona", nullable = false)
=======
	    @JoinColumn(name = "idpersona", nullable = false)
>>>>>>> e89eb3d70de556070897c64d97c6f454d4ce03db
	    private Persona persona;
	  @ManyToOne
	    @JoinColumn(name = "idejemplar", nullable = false)
	    private Ejemplar ejemplar;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getFechahora() {
		return fechahora;
	}
	public void setFechahora(LocalDateTime fechahora) {
		this.fechahora = fechahora;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Ejemplar getEjemplar() {
		return ejemplar;
	}
	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}
	public Mensaje() {
		super();
	}
	public Mensaje(LocalDateTime fechahora, String mensaje, Persona persona, Ejemplar ejemplar) {
		super();
		this.fechahora = fechahora;
		this.mensaje = mensaje;
		this.persona = persona;
		this.ejemplar = ejemplar;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ejemplar, fechahora, mensaje, persona);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensaje other = (Mensaje) obj;
		return Objects.equals(ejemplar, other.ejemplar) && Objects.equals(fechahora, other.fechahora)
				&& Objects.equals(mensaje, other.mensaje) && Objects.equals(persona, other.persona);
	}
	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", fechahora=" + fechahora + ", mensaje=" + mensaje + ", idpersona=" + persona.getId()
				+ ", idejemplar=" + ejemplar.getId() + "]";
	}
	
	
}
