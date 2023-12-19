package dataAccess;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import HibernateUtil.HibernateUtil;
import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Event;
import domain.Question;
import dominio.Eventos;
import dominio.Questions;
import exceptions.QuestionAlreadyExist;

public class DataAccess implements DataAccessHibernate {

	//protected static SessionFactory sessionFactory;

    //ConfigXML c = ConfigXML.getInstance();

    public DataAccess(boolean initializeMode) {
     

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
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            Calendar today = Calendar.getInstance();

            int month = today.get(Calendar.MONTH);
            month += 1;
            int year = today.get(Calendar.YEAR);
            if (month == 12) {
                month = 0;
                year += 1;
            }

            Eventos ev1 = new Eventos( "Atlético-Athletic", UtilDate.newDate(year, month, 17));
            Eventos ev2 = new Eventos( "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
            Eventos ev3=new Eventos( "Getafe-Celta", UtilDate.newDate(year,month,17));
            Eventos ev4=new Eventos( "Alavés-Deportivo", UtilDate.newDate(year,month,17));
            Eventos ev5=new Eventos( "Español-Villareal", UtilDate.newDate(year,month,17));
            Eventos ev6=new Eventos( "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
            Eventos ev7=new Eventos( "Malaga-Valencia", UtilDate.newDate(year,month,17));
            Eventos ev8=new Eventos( "Girona-Leganés", UtilDate.newDate(year,month,17));
            Eventos ev9=new Eventos( "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
            Eventos ev10=new Eventos( "Betis-Real Madrid", UtilDate.newDate(year,month,17));
            Eventos ev11=new Eventos( "Atletico-Athletic", UtilDate.newDate(year,month,1));
            Eventos ev12=new Eventos( "Eibar-Barcelona", UtilDate.newDate(year,month,1));
            Eventos ev13=new Eventos( "Getafe-Celta", UtilDate.newDate(year,month,1));
            Eventos ev14=new Eventos( "Alavés-Deportivo", UtilDate.newDate(year,month,1));
            Eventos ev15=new Eventos( "Español-Villareal", UtilDate.newDate(year,month,1));
            Eventos ev16=new Eventos( "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
            Eventos ev17=new Eventos( "Málaga-Valencia", UtilDate.newDate(year,month,28));
            Eventos ev18=new Eventos( "Girona-Leganés", UtilDate.newDate(year,month,28));
            Eventos ev19=new Eventos( "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
            Eventos ev20=new Eventos( "Betis-Real Madrid", UtilDate.newDate(year,month,28));

            Questions q1=ev1.addQuestion("¿Quién ganará el partido?",1);
            Questions q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
            Questions q3=ev11.addQuestion("¿Quién ganará el partido?",1);
            Questions q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
            Questions q5=ev17.addQuestion("¿Quién ganará el partido?",1);
            Questions q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);

            session.persist(q1);
            session.persist(q2);
            session.persist(q3);
            session.persist(q4);
            session.persist(q5);
            session.persist(q6);

            session.persist(ev1);
            session.persist(ev2);
            session.persist(ev3);
            session.persist(ev4);
            session.persist(ev5);
            session.persist(ev6);
            session.persist(ev7);
            session.persist(ev8);
            session.persist(ev9);
            session.persist(ev10);
            session.persist(ev11);
            session.persist(ev12);
            session.persist(ev13);
            session.persist(ev14);
            session.persist(ev15);
            session.persist(ev15);
            session.persist(ev16);
            session.persist(ev17);
            session.persist(ev18);
            session.persist(ev19);
            session.persist(ev20);
            
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
	/*public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		System.out.println(session+" "+event);
		
			Event ev = session.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
	}*/
	
	public Questions createQuestion(Eventos event, String question, float betMinimum) throws QuestionAlreadyExist {
	  

	    try {
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();

	        Eventos ev = (Eventos) session.get(Eventos.class, event.getEventDate());

	        if (ev.DoesQuestionExists(question))
	            throw new QuestionAlreadyExist(
	                    ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

	        Questions q = ev.addQuestion(question, betMinimum);
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
/*	public Vector<Event> getEvents(Date date) {
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
	}*/
	
	public List<Eventos> getEvents(Date date) {
	    System.out.println(">> DataAccess: getEvents");
	    List<Eventos> res = new ArrayList<Eventos>();

	    try {
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();
	        TypedQuery<Eventos> query = (TypedQuery<Eventos>) session.createQuery("FROM Eventos WHERE eventDate = :date"/*, Eventos.class*/);
	        query.setParameter("date", date);
	        List<Eventos> events = query.getResultList();

	        for (Eventos ev : events) {
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
/*	public Vector<Date> getEventsMonth(Date date) {
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
	}*/
	
	
	public Vector<Date> getEventsMonth(Date date) {
	    System.out.println(">> DataAccess: getEventsMonth");
	    Vector<Date> res = new Vector<Date>();

	    try {
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();

	        Date firstDayMonthDate = UtilDate.firstDayMonth(date);
	        Date lastDayMonthDate = UtilDate.lastDayMonth(date);

	        TypedQuery<Date> query = (TypedQuery<Date>) session.createQuery(
	                "SELECT DISTINCT ev.eventDate FROM Eventos ev WHERE ev.eventDate BETWEEN :firstDay AND :lastDay"/*,Date.class*/);
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
/*public boolean existQuestion(Event event, String question) {
	System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
	Event ev = db.find(Event.class, event.getEventNumber());
	return ev.DoesQuestionExists(question);
	
}*/

public boolean existQuestion(Eventos event, String question) {
    System.out.println(">> DataAccess: existQuestion=> event= " + event + " question= " + question);

    try {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
        Eventos ev = (Eventos) session.get(Eventos.class, event.getEventDate());
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
	/*Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	if (session != null) {
		session.close();
        System.out.println("DataBase closed");
    }*/
	
}

@Override
public void emptyDatabase() {
	// TODO Auto-generated method stub
	
}



		
}
