package modelo.bean;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import antlr.debug.Event;
import businessLogic.BLFacade;
import gui.MainGUI;

public class CreateQuestionBean {
	private Date fecha;

	public CreateQuestionBean() {}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date d) {
		this.fecha = d;
	}

	public void onDateSelect(SelectEvent e) {
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Events: " + e.getObject()));
	}
}
