package modelo.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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

@ManagedBean
public class QueryQuestionBean {
	
	BLFacadeImplementation bl = new BLFacadeImplementation();


	private Date fecha;

	private List<Evento> eventos;

	private List<Pregunta> preguntas;
	
	private Evento eventoSel;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	public Evento getEventoSel() {
		return eventoSel;
	}
	
	public void setEventoSel(Evento ev) {
		
		eventoSel = ev;
		
	}
	
	
	public List<Evento> getEventos() {

		return eventos;

	}

	public List<Pregunta> getPreguntas() {

		return preguntas;

	}
	
	public void setPreguntas(List<Pregunta> preguntas) {
	    this.preguntas = preguntas;
	}

	public void onDateSelect(SelectEvent event) {
		//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fecha escogida: " + event.getObject()));
		//cargarEventos()
		
		Date fechaSelec = (Date) event.getObject();
		
		eventos = bl.getEvents(fechaSelec);
		
		if (eventos.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No hay eventos para la fecha: " + event.getObject()));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Eventos para la fecha " + event.getObject() + ": " + eventos));
        }
	}

	public QueryQuestionBean() {
		this.eventos = new ArrayList<Evento>();
		this.preguntas = new ArrayList<Pregunta>();
		
	}
	
	public void onEventSelect(/*SelectEvent*/ Evento event) {
		
		//eventoSel = (Evento) event.getObject();
		eventoSel = event;
		//System.out.println(eventoSel.getQuestions());
		//System.out.println(eventoSel);
		preguntas = bl.getPreguntasEvento(eventoSel);
		
		if (preguntas.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No hay preguntas para el evento: " + event));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Preguntas para el evento " + eventoSel + ": " + preguntas.size()));
        }
		
		
	}
	
	
	
	}

	/*private void cargarEventos() {
		
		 if (fecha != null) { Calendar calendar = Calendar.getInstance();
		  calendar.setTime(fecha);
		  eventService.getEventsByDate(calendar.getTime()); 
		  } 
		 else {
		 FacesContext.getCurrentInstance().addMessage(null,
		  new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
		  "La fecha seleccionada es nula.")); }
		 
	}*/

	/*
	 * public void listener(AjaxBehaviorEvent event) {
	 * FacesContext.getCurrentInstance().addMessage(null, new
	 * FacesMessage("El tipo del usuario:"+tipo.getCodigo()+"/"+tipo.getTipoUsu()));
	 * 
	 * }
	 */

//}
