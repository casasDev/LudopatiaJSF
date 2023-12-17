package businessLogic;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import dataAccess.DataAccessInterface;
import dominio.Eventos;
import dominio.Questions;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class BLFacadeImplementation {
	   private DataAccess dbManager;

	    public BLFacadeImplementation() {
	        System.out.println("Creating BLFacadeImplementation instance");
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
	        }
	    }

	    public BLFacadeImplementation(DataAccessInterface da) {
	        System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
	        ConfigXML c = ConfigXML.getInstance();

	        if (c.getDataBaseOpenMode().equals("initialize")) {
	            da.emptyDatabase(); // Implement this method in your DataAccessInterface for cleanup
	            da.open();
	            da.initializeDB(); // Initialize database using Hibernate
	            da.close();
	        }
	        dbManager = (DataAccess) da;
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
	    public Vector<Eventos> getEvents(Date date) {
	        dbManager.open();
	        Vector<Eventos> events = dbManager.getEvents(date);
	        dbManager.close();
	        return events;
	    }
	    

	   // @WebMethod
	    public Vector<Date> getEventsMonth(Date date) {
	        dbManager.open();
	        Vector<Date> dates = dbManager.getEventsMonth(date);
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
