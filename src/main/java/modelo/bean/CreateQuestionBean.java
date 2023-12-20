package modelo.bean;

import java.text.SimpleDateFormat;
import dominio.Evento;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import businessLogic.BLFacade;
@ManagedBean
public class CreateQuestionBean {
	private Date fecha;
	private List<Evento> eventos;
	private List<SelectItem> elementos;
	//private String elementoSeleccionado;
	public CreateQuestionBean() {
		this.eventos = new ArrayList<Evento>();
		this.elementos = new ArrayList<SelectItem>();
		this.elementos.add(new SelectItem("Tu puta madre"));
	}
	public Date getFecha() {
		return this.fecha;
	}
	public List<SelectItem> getElementos(){
		return elementos;
	}
	public List<Evento> getEventos(){
		return this.eventos;
	}
	public void setFecha(Date d) {
		this.fecha = d;
	}

	public void onDateSelect(SelectEvent e) {
		this.setFecha((Date)e.getObject());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Events: " + new SimpleDateFormat("dd/MM/YYYY").format(e.getObject())));
		this.elementos.add(new SelectItem(new Random().nextInt()));
	}
}
