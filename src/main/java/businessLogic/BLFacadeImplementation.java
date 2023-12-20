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
	   private Session ses;

	    public BLFacadeImplementation() {

	    	 ses  = HibernateUtil.getSessionFactory().getCurrentSession();
	    	dbManager = new DataAccessHibernate();
	    }

	    public BLFacadeImplementation(DataAccessHibernateImplementation da) {
	    	 ses  = HibernateUtil.getSessionFactory().getCurrentSession();
	    	 dbManager = new DataAccessHibernate();
	    }

	    public Pregunta createQuestion(Evento event, String question, float betMinimum)
	            throws EventFinished, QuestionAlreadyExist {
	    	ses.beginTransaction();
	        
	        Pregunta qry = null;

	        if (new Date().compareTo(event.getEventDate()) > 0)
	            throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

	        qry = dbManager.createQuestion(event, question, betMinimum);

	        ses.getTransaction().commit();
	        ses.close();
	        return qry;
	    }
	    

	    public List<Evento> getEvents(Date date) {
	    	ses.beginTransaction();

	        List<Evento> events = dbManager.getEvents(date);
	        
	        ses.close();
	        return events;
	    }
	    


	    public List<Date> getEventsMonth(Date date) {
	    	ses.beginTransaction();
	        List<Date> dates = dbManager.getEventsMonth(date);
	        ses.close();
	        return dates;
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
