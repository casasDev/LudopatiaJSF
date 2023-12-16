package businessLogic;

<<<<<<< HEAD
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
	        dbManager = da;
	    }

	    @WebMethod
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

	    @WebMethod
	    public Vector<Eventos> getEvents(Date date) {
	        dbManager.open();
	        Vector<Eventos> events = dbManager.getEvents(date);
	        dbManager.close();
	        return events;
	    }

	    @WebMethod
	    public Vector<Date> getEventsMonth(Date date) {
	        dbManager.open();
	        Vector<Date> dates = dbManager.getEventsMonth(date);
	        dbManager.close();
	        return dates;
	    }

	    public void close() {
	        dbManager.close();
	    }

	    @WebMethod
	    public void initializeBD() {
	        dbManager.open();
	        dbManager.initializeDB();
	        dbManager.close();
	    }
=======
import configuration.ConfigXML;
import dataAccess.DataAccessInterface;

public class BLFacadeImplementation {
		
	DataAccessInterface dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		/*if (c.getDataBaseOpenMode().equals("initialize")) {
			
		    dbManager=new DataAccessInterface(new ObjectDbDAOManager());
			dbManager.initializeDB();
			dbManager.close();
			}
		*/
	}
	
    public BLFacadeImplementation(DataAccessInterface da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.emptyDatabase();
			da.open();
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		dbManager.open();
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open();
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open();
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		//DataAccess dB4oManager=new DataAccess(false);

		//dB4oManager.close();
		dbManager.close();


	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
>>>>>>> branch 'master' of https://github.com/casasDev/LudopatiaJSF.git
	
}
