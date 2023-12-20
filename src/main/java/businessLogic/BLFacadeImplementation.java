package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import org.hibernate.Session;

import HibernateUtil.HibernateUtil;
import configuration.ConfigXML;
import dataAccess.DataAccessHibernate;
import dataAccess.DataAccessHibernateImplementation;
import dominio.Evento;
import dominio.Pregunta;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class BLFacadeImplementation {
	   private DataAccessHibernate dbManager;


	    public BLFacadeImplementation() {

	    	dbManager = new DataAccessHibernate();
	    }

	    public BLFacadeImplementation(DataAccessHibernateImplementation da) {
	    	 
	    	 dbManager = (DataAccessHibernate) da;
	    }

	    public Pregunta createQuestion(Evento event, String question, float betMinimum)
	            throws EventFinished, QuestionAlreadyExist {
	    	try {
	            if (new Date().compareTo(event.getEventDate()) > 0) {
	                throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
	            }

	            // El m�todo createQuestion deber�a manejar la sesi�n internamente
	            Pregunta qry = dbManager.createQuestion(event, question, betMinimum);

	            return qry;
	        } catch (Exception e) {
	            e.printStackTrace();
	            // Manejar la excepci�n seg�n tus necesidades.
	            return null; // O lanzar otra excepci�n seg�n el flujo de tu aplicaci�n.
	        }
	    	
	    }
	    

	    public List<Evento> getEvents(Date date) {
	        return dbManager.getEvents(date);
	    }
	    


	    public List<Date> getEventsMonth(Date date) {
	    
	        return dbManager.getEventsMonth(date);
	    }
	    

	    
	    //@WebMethod
	    public void initializeBD() {
	    	
	        dbManager.initializeDB();

	    }

/*	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		/*if (c.getDataBaseOpenMode().equals("initialize")) {
			
		    dbManager=new DataAccessInterface(new ObjectDbDAOManager());
			dbManager.initializeDB();
			dbManager.close();
			}
		
	}*/
	
  /*  public BLFacadeImplementation(DataAccessInterface da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.emptyDatabase();
			da.open();
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}*/
    
}
