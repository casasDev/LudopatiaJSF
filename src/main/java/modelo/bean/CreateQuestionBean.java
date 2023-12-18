package modelo.bean;

import java.util.Date; 

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import antlr.debug.Event;
import businessLogic.BLFacade;
import gui.MainGUI;
@ManagedBean
public class CreateQuestionBean {
	private Date fecha;

	public CreateQuestionBean() {
		this.fecha = new Date();
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date d) {
		this.fecha = d;
	}

	public void onDateSelect(SelectEvent e) {
		System.out.println(e.getObject());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Events: " + fecha));
	}
}
