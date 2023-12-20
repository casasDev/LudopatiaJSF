package dataAccess;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.TypedQuery;
import javax.persistence.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import HibernateUtil.HibernateUtil;
import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Event;
import domain.Question;
import dominio.Evento;
import dominio.Pregunta;
import exceptions.QuestionAlreadyExist;

public class DataAccessHibernate implements DataAccessHibernateImplementation {

	//protected static SessionFactory sessionFactory;

    //ConfigXML c = ConfigXML.getInstance();

    public DataAccessHibernate(boolean initializeMode) {
    	initializeDB();
    }

	public DataAccessHibernate()  {	
		 new DataAccessHibernate(false);
		 //initializeDB();
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

            Evento ev1 = new Evento( "Atlético-Athletic", UtilDate.newDate(year, month, 17));
            Evento ev2 = new Evento( "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
            Evento ev3=new Evento( "Getafe-Celta", UtilDate.newDate(year,month,17));
            Evento ev4=new Evento( "Alavés-Deportivo", UtilDate.newDate(year,month,17));
            Evento ev5=new Evento( "Español-Villareal", UtilDate.newDate(year,month,17));
            Evento ev6=new Evento( "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
            Evento ev7=new Evento( "Malaga-Valencia", UtilDate.newDate(year,month,17));
            Evento ev8=new Evento( "Girona-Leganés", UtilDate.newDate(year,month,17));
            Evento ev9=new Evento( "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
            Evento ev10=new Evento( "Betis-Real Madrid", UtilDate.newDate(year,month,17));
            
            Evento ev11=new Evento( "Atletico-Athletic", UtilDate.newDate(year,month,1));
            Evento ev12=new Evento( "Eibar-Barcelona", UtilDate.newDate(year,month,1));
            Evento ev13=new Evento( "Getafe-Celta", UtilDate.newDate(year,month,1));
            Evento ev14=new Evento( "Alavés-Deportivo", UtilDate.newDate(year,month,1));
            Evento ev15=new Evento( "Español-Villareal", UtilDate.newDate(year,month,1));
            Evento ev16=new Evento( "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
            
            Evento ev17=new Evento( "Málaga-Valencia", UtilDate.newDate(year,month,28));
            Evento ev18=new Evento( "Girona-Leganés", UtilDate.newDate(year,month,28));
            Evento ev19=new Evento( "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
            Evento ev20=new Evento( "Betis-Real Madrid", UtilDate.newDate(year,month,28));

            Pregunta q1=ev1.addQuestion("¿Quién ganará el partido?",1);
            Pregunta q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
            
            Pregunta q3=ev11.addQuestion("¿Quién ganará el partido?",1);
            Pregunta q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
            
            Pregunta q5=ev17.addQuestion("¿Quién ganará el partido?",1);
            Pregunta q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);

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
            //session.close();

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
	
	public Pregunta createQuestion(Evento event, String question, float betMinimum) throws QuestionAlreadyExist {
	  

	    try {
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();

	        Evento ev = (Evento) session.get(Evento.class, event.getEventDate());

	        if (ev.DoesQuestionExists(question))
	            throw new QuestionAlreadyExist(
	                    ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

	        //
	        Pregunta q = ev.addQuestion(question, betMinimum);
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

	public List<Evento> getEvents(Date date) {
		
	    List<Evento> res = new ArrayList<Evento>();

	    try {
	    	
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();
	        Query query =   session.createQuery("FROM Evento WHERE eventDate = :date"/*, Eventos.class*/);
	        query.setParameter("date", date);
	        List<Evento> events = query.list();
	        
	        for (Evento ev : events) {
	            System.out.println(ev.toString());
	            res.add(ev);
	        }

	        session.close();
	    } 
	    
	    catch (Exception e) {
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

	public List<Date> getEventsMonth(Date date) {
	    List<Date> res = new ArrayList<Date>();

	    try {
	    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	session.beginTransaction();

	        Date firstDayMonthDate = UtilDate.firstDayMonth(date);
	        Date lastDayMonthDate = UtilDate.lastDayMonth(date);

	        Query query =session.createQuery("SELECT DISTINCT ev.eventDate FROM Evento ev WHERE ev.eventDate BETWEEN :firstDay AND :lastDay"/*,Date.class*/);
	        query.setParameter("firstDay", firstDayMonthDate);
	        query.setParameter("lastDay", lastDayMonthDate);
	        List<Date> dates = query.list();

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
	
public boolean existQuestion(Evento event, String question) {

    try {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.beginTransaction();
        Evento ev = (Evento) session.get(Evento.class, event.getEventDate());
        session.close(); // Cerrar la sesión después de obtener el objeto Event

        return ev != null && ev.DoesQuestionExists(question);
    } catch (Exception e) {
        e.printStackTrace();
        // Manejar la excepción según tus necesidades
        return false;
    }
}
		
}
