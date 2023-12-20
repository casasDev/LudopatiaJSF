package dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // El SGBD genera automaticamente el id para las tuplas, ya que
														// sino tendriamos que hacerlo manualmente
	private Long id;
	private String descripcion;
	@Column(nullable = false)
	private Date fecha;
	@ManyToOne(targetEntity = Usuario.class, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@Fetch(value = FetchMode.JOIN)
	private Usuario usuario;
	private boolean login;

	public Login() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
		if (login)
			this.descripcion = usuario.getNombre() + " ha hecho login correctamente";
		else
			this.descripcion = usuario.getNombre() + " ha intentado hacer login";
	}

	public String toString() { // EventoLogin
		return id + "/" + descripcion + "/" + fecha + "/" + usuario + "/" + login;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		long result = 1;
		result = prime * result + id;
		return (int) result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (id != other.id)
			return false;
		return true;
	}


	
	
	
}
