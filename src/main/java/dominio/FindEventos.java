package dominio;


import java.util.Date;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class FindEventos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //El SGBD genera automaticamente el id para las tuplas, ya que sino tendriamos que hacerlo manualmente
	private Long id;
	private String descripcion;
	@Column(nullable = false)
	private Date fecha;
	
	public FindEventos() {}
	
	public Long getId() {
	return id; }
	
	public void setId(Long id) {
	this.id = id; }
	
	public String getDescripcion() {
	return descripcion; }
	
	public void setDescripcion(String descripcion) {
	this.descripcion = descripcion; }
	
	public Date getFecha() {
	return fecha; }
	
	public void setFecha(Date fecha) {
	this.fecha = fecha; }
	
	public String toString() { // EventoLogin
		return id+"/"+descripcion+"/"+fecha;
	}

}
