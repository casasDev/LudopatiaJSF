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
public class Usuario {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idUsu;
	private String nombre; 
	private String pass;
	
	@OneToMany(targetEntity = Login.class, mappedBy = "usuario", cascade = { CascadeType.REMOVE,
			CascadeType.PERSIST }, fetch = FetchType.EAGER)
	private List<Login> logeos;
	

	public Usuario() {
	}
	
	public Usuario(String pNomb, String pPass) {
		this.nombre=pNomb;
		this.pass=pPass;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPassword() {
		return pass;
	}

	public void setPassword(String password) {
		this.pass = password;
	}

	public String toString() { // Usuario
		return nombre + "/" + pass ;
	}

	public void setEventos(List<Login> eventos) {
		this.logeos = eventos;
	}

	public List<Login> getEventos() {
		// TODO Auto-generated method stub
		return logeos;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		long result = 1;
		result = prime * result + idUsu;
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
		Usuario other = (Usuario) obj;
		if (idUsu != other.idUsu)
			return false;
		return true;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return idUsu;
	}
	
	
	
}
