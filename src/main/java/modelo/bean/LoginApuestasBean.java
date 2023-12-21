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
	private boolean estaHaciendoLogin;
	
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

	public String comprobar() {
		
	    System.out.println("Entrando en el método comprobar con nombre: " + nombre + " y contraseña: " + password);
		
		if (!u.DoesUserExist(nombre, password)){
		
			System.out.println("NO EXISTE EL USU");
		FacesContext.getCurrentInstance().addMessage(null,
			new FacesMessage("Error: Usuario no existe"));
				return null;
		}
		else {
			
			System.out.println("EXISTE EL USU");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El usuario de nombre " + nombre + " existe"));
            
            u.crearEventoLogin(nombre,password,true);
            
			return "ok";
			
		}
	
		
	}
	
	public String registrar() {
		
		/*if (!u.DoesUserExist(nombre, password)){
			
			System.out.println("No existe el usuario, lo registramos");
			
			u.registrarUsuarioNuevo(nombre, password);
		FacesContext.getCurrentInstance().addMessage(null,
			new FacesMessage("Error: Usuario no existe"));
		
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El usuario de nombre " + nombre + " existe"));

				return null;
		}
		else {
			
			System.out.println("EXISTE EL USU");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El usuario de nombre " + nombre + " existe"));
            
            u.crearEventoLogin(nombre,password,true);
            
			return "ok";
			
		}*/
		
		//System.out.println("No existe el usuario, lo registramos");
		
		//u.registrarUsuarioNuevo(nombre, password);
		
		
		//if(u.DoesUserExist(nombre, password)) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El usuario de nombre " + nombre + " ha sido registrado"));

		//return "Usuario registrado";
		
		    // Recargamos el usuario recién registrado
		    if (u.DoesUserExist(nombre, password)) {
		        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El usuario de nombre:" + nombre + " ya existe"));
		        return "Usuario ya registrado";
		    }
		    else {
		    	
		    	u.registrarUsuarioNuevo(nombre, password);
		        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El usuario de nombre:" + nombre + " ha sido registrado"));

		    	return "Usuario registrado con exito";
		    	
		    }
	
		
		
	}
	

	
}
