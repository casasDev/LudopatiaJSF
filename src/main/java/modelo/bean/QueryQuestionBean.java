package modelo.bean;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

public class QueryQuestionBean {

	private Date fecha;
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public void onDateSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
		new FacesMessage("Fecha escogida: "+event.getObject()));
	}
	
	public QueryQuestionBean() {
		
		
	}
	
	
	
	
	/*public void listener(AjaxBehaviorEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
		new FacesMessage("El tipo del usuario:"+tipo.getCodigo()+"/"+tipo.getTipoUsu()));

	}*/
	
	//public void onEventSelect(SelectEvent event) {
		//this.tipo=(TipoUsuario)event.getObject();
		//FacesContext.getCurrentInstance().addMessage("miForm:mensajes",
		//new FacesMessage("El tipo del usuario (tabla):"+tipo.getCodigo()+"/"+tipo.getTipoUsu()));}

	
}
