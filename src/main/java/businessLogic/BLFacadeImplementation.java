package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import org.hibernate.Session;

import HibernateUtil.HibernateUtil;
import configuration.ConfigXML;
import dataAccess.DataAccess;
import dataAccess.DataAccessHibernate;
import dominio.Eventos;
import dominio.Questions;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class BLFacadeImplementation {
	   private DataAccess dbManager;

	    public BLFacadeImplementation() {
	       /* System.out.println("Creating BLFacadeImplementation instance");
	        ConfigXML c = ConfigXML.getInstance();

	        if (c.getDataBaseOpenMode().equals("initialize")) {
	            // Initialize Hibernate and MariaDB here if needed
	            // Example: HibernateUtil.initialize();
	            // Example: MariaDBUtil.initialize();
	            // ...

	            dbManager = new DataAccess(); // Assuming you have a DataAccessInterface class for Hibernate
	            dbManager.open();
	            dbManager.initializeDB(); // Initialize database using Hibernate
	            dbManager.close();
	        }*/
	    	Session ses  = HibernateUtil.getSessionFactory().getCurrentSession();
	    }

	    public BLFacadeImplementation(DataAccessHibernate da) {
	       /* System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
	        ConfigXML c = ConfigXML.getInstance();

	        if (c.getDataBaseOpenMode().equals("initialize")) {
	            da.emptyDatabase(); // Implement this method in your DataAccessInterface for cleanup
	            da.open();
	            da.initializeDB(); // Initialize database using Hibernate
	            da.close();
	        }
	        dbManager = (DataAccess) da;*/
	    	Session ses  = HibernateUtil.getSessionFactory().getCurrentSession();
	    }

	    //@WebMethod
	    public Questions createQuestion(Eventos event, String question, float betMinimum)
	            throws EventFinished, QuestionAlreadyExist {
	        dbManager.open();
	        Questions qry = null;

	        if (new Date().compareTo(event.getEventDate()) > 0)
	            throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

	        qry = dbManager.createQuestion(event, question, betMinimum);

	        dbManager.close();

	        return qry;
	    }
	    


	   // @WebMethod
	    public List<Eventos> getEvents(Date date) {
	        dbManager.open();
	        List<Eventos> events = dbManager.getEvents(date);
	        dbManager.close();
	        return events;
	    }
	    

	   // @WebMethod
	    public List<Date> getEventsMonth(Date date) {
	        dbManager.open();
	        List<Date> dates = dbManager.getEventsMonth(date);
	        dbManager.close();
	        return dates;
	    }
	    
	    public void close() {
	        dbManager.close();
	    }
	    
	    //@WebMethod
	    public void initializeBD() {
	        dbManager.open();
	        dbManager.initializeDB();
	        dbManager.close();
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
