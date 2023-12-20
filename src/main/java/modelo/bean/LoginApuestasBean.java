package modelo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;


import org.primefaces.event.SelectEvent;

import businessLogic.BLFacadeImplementation;
import dominio.Usuario;

public class LoginApuestasBean {
	private String nombre;
	private String password;
	
	private static List<Usuario> usu=new ArrayList<Usuario>();

	private BLFacadeImplementation u = new BLFacadeImplementation();
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String comprobar(String nom, String pass) {
		if (!u.DoesUserExist(nom, pass)){
			
		FacesContext.getCurrentInstance().addMessage(null,
			new FacesMessage("Error: Usuario no existe"));
				return null;
		}
		else {
			
			//DAR ACCESO A MENU DE APUESTAS
			return "ok";
			
		}
	
		
	}
	

	
}
