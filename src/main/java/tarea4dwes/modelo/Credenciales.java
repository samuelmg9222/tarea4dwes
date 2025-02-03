package tarea4dwes.modelo;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="credenciales")
public class Credenciales implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "usuario", unique = true)
	    private String usuario;

	    @Column(name = "password")
	    private String password;

	    @OneToOne
	    
	    @JoinColumn(name = "idpersona") 
	    private Persona persona;
	    
	    
	    
	    
		public Persona getPersona() {
			return persona;
		}

		public void setPersona(Persona persona) {
			this.persona = persona;
		}

		public Credenciales() {
			super();
		}

	

		public Credenciales(String usuario, String password, Persona persona) {
			super();
			
			this.usuario = usuario;
			this.password = password;
			this.persona = persona;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id, password, persona, usuario);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Credenciales other = (Credenciales) obj;
			return Objects.equals(id, other.id) && Objects.equals(password, other.password)
					&& Objects.equals(persona, other.persona) && Objects.equals(usuario, other.usuario);
		}

		@Override
		public String toString() {
			return "Credenciales [id=" + id + ", usuario=" + usuario + ", password=" + password + ", persona=" + persona
					+ "]";
		}

		

}
