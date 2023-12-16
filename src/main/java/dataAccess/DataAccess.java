package dataAccess;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Event;
import domain.Question;
import exceptions.QuestionAlreadyExist;

public class DataAccess implements DataAccessInterface {

	protected static SessionFactory sessionFactory;

    ConfigXML c = ConfigXML.getInstance();

    public DataAccess(boolean initializeMode) {
        System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
                + " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

        open();

        /* if (initializeMode)
            initializeDB();
        */
    }

	public DataAccess()  {	
		 new DataAccess(false);
	}
	
	@Override
	public void initializeDB() {

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Calendar today = Calendar.getInstance();

            int month = today.get(Calendar.MONTH);
            month += 1;
            int year = today.get(Calendar.YEAR);
            if (month == 12) {
                month = 0;
                year += 1;
            }

            Event ev1 = new Event(1, "Atlético-Athletic", UtilDate.newDate(year, month, 17));
            Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
            // ... (rest of the events)

            Question q1 = ev1.addQuestion("Who will win the match?", 1);
            Question q2 = ev1.addQuestion("Who will score first?", 2);
            // ... (rest of the questions)

            session.persist(q1);
            session.persist(q2);
            // ... (persist other questions)

            session.persist(ev1);
            session.persist(ev2);
            // ... (persist other events)

            session.getTransaction().commit();
            session.close();

            System.out.println("Db initialized");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	//MODIFICAR
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		System.out.println(db+" "+event);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
	}
	
	public Question createQuestion2(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
	    System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
	            + betMinimum);

	    try {
	        Session session = sessionFactory.openSession();
	        session.beginTransaction();

	        Event ev = (Event) session.get(Event.class, event.getEventNumber());

	        if (ev.DoesQuestionExists(question))
	            throw new QuestionAlreadyExist(
	                    ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

	        Question q = ev.addQuestion(question, betMinimum);
	        session.persist(q);
	        session.persist(ev);

	        session.getTransaction().commit();
	        session.close();

	        return q;
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Manejar la excepción según tus necesidades
	        return null;
	    }
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	//MODIFICAR
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	public Vector<Event> getEvents2(Date date) {
	    System.out.println(">> DataAccess: getEvents");
	    Vector<Event> res = new Vector<Event>();

	    try {
	        Session session = sessionFactory.openSession();
	        TypedQuery<Event> query = session.createQuery("FROM Event WHERE eventDate = :date", Event.class);
	        query.setParameter("date", date);
	        List<Event> events = query.getResultList();

	        for (Event ev : events) {
	            System.out.println(ev.toString());
	            res.add(ev);
	        }

	        session.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Manejar la excepción según tus necesidades
	    }

	    return res;
	}
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	//MODIFICAR
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	
	
	public Vector<Date> getEventsMonth2(Date date) {
	    System.out.println(">> DataAccess: getEventsMonth");
	    Vector<Date> res = new Vector<Date>();

	    try {
	        Session session = sessionFactory.openSession();

	        Date firstDayMonthDate = UtilDate.firstDayMonth(date);
	        Date lastDayMonthDate = UtilDate.lastDayMonth(date);

	        TypedQuery<Date> query = session.createQuery(
	                "SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN :firstDay AND :lastDay",
	                Date.class);
	        query.setParameter("firstDay", firstDayMonthDate);
	        query.setParameter("lastDay", lastDayMonthDate);
	        List<Date> dates = query.getResultList();

	        for (Date d : dates) {
	            System.out.println(d.toString());
	            res.add(d);
	        }

	        session.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Manejar la excepción según tus necesidades
	    }

	    return res;
	}
	
	
//MODIFICAR
public boolean existQuestion(Event event, String question) {
	System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
	Event ev = db.find(Event.class, event.getEventNumber());
	return ev.DoesQuestionExists(question);
	
}

public boolean existQuestion2(Event event, String question) {
    System.out.println(">> DataAccess: existQuestion=> event= " + event + " question= " + question);

    try {
        Session session = sessionFactory.openSession();
        Event ev = (Event) session.get(Event.class, event.getEventNumber());
        session.close(); // Cerrar la sesión después de obtener el objeto Event

        return ev != null && ev.DoesQuestionExists(question);
    } catch (Exception e) {
        e.printStackTrace();
        // Manejar la excepción según tus necesidades
        return false;
    }
}

@Override
public void open() {
	/*System.out.println("Opening DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
    + " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());
	
	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
	
	try {
	sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	} catch (Exception e) {
	e.printStackTrace();
	StandardServiceRegistryBuilder.destroy(registry);
}*/ //No entiendo revisar lab
	
}

@Override
public void close() {
	if (sessionFactory != null) {
        sessionFactory.close();
        System.out.println("DataBase closed");
    }
	
}

@Override
public void emptyDatabase() {
	// TODO Auto-generated method stub
	
}



		
}
