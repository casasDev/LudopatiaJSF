package modelo.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.jdo.annotations.Queries;

import businessLogic.BLFacade;
import configuration.UtilDate;

import businessLogic.*;

import org.primefaces.event.SelectEvent;

import domain.Event;
import dominio.Evento;
import dominio.Pregunta;

public class QueryQuestionBean {

	private Date fecha;

	private List<Evento> eventos;

	private List<Pregunta> preguntas;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Evento> getEventos() {

		return eventos;

	}

	public List<Pregunta> getPreguntas() {

		return preguntas;

	}

	public void onDateSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha escogida: " + event.getObject()));
		//cargarEventos()
	}

	public QueryQuestionBean() {
		this.eventos = new ArrayList<Evento>();
		eventos.add(new Evento("Tu puta madre",new Date()));
	}

	//private void cargarEventos() {
		/*
		 * if (fecha != null) { Calendar calendar = Calendar.getInstance();
		 * calendar.setTime(fecha);
		 * 
		 * // Llama al servicio para obtener eventos de la base de datos eventos =
		 * eventService.getEventsByDate(calendar.getTime()); } else { // Manejo de
		 * error, la fecha es nula FacesContext.getCurrentInstance().addMessage(null,
		 * new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
		 * "La fecha seleccionada es nula.")); }
		 */
	}

	/*
	 * public void listener(AjaxBehaviorEvent event) {
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage("El tipo del usuario:"+tipo.getCodigo()+"/"+tipo.getTipoUsu()));
	 * 
	 * }
	 */

	// public void onEventSelect(SelectEvent event) {
	// this.tipo=(TipoUsuario)event.getObject();
	// FacesContext.getCurrentInstance().addMessage("miForm:mensajes",
	// new FacesMessage("El tipo del usuario
	// (tabla):"+tipo.getCodigo()+"/"+tipo.getTipoUsu()));}

//}
