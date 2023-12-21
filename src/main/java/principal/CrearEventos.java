 package principal;

import HibernateUtil.HibernateUtil; 
import dominio.Evento;
import dominio.Pregunta;

import org.hibernate.Query;
import org.hibernate.Session;
import java.util.*;

public class CrearEventos {

	public CrearEventos() {
	}

	//PREGUNTAR POR ERROR DE CREACION
	
	private void createAndStoreEventoSinPregunta(String descripcion, Date fecha) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Evento e = new Evento();
		e.setDescription(descripcion);
		e.setEventDate(fecha);
		session.save(e);
		session.getTransaction().commit();
		
	}
	
	private void createAndStoreEventoConPregunta(String descripcion, Date fecha,Pregunta q) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Evento e = new Evento();
		e.setDescription(descripcion);
		e.setEventDate(fecha);
		session.getTransaction().commit();
		e.addQuestions(q);
		session.getTransaction().commit();
		session.save(e);
		session.getTransaction().commit();
		
	}
	

	private List listaEventos() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from Eventos").list();
		session.getTransaction().commit();
		return result;
	}
	
	private List listaEventosPorFecha(Date fecha) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from Eventos where eventDate= :fecha").list();
		session.getTransaction().commit();
		return result;
	}

	private void createAndStoreQuestionsSinEvento(String pre, float bet) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Pregunta q = new Pregunta();
		q.setQuestion(pre);
		q.setBetMinimum(bet);
		session.persist(q);
		session.getTransaction().commit();
	}
	
	
	private void createAndStoreQuestionsConEvento(String pre, float bet, Evento ev) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Pregunta q = new Pregunta();
		q.setQuestion(pre);
		q.setBetMinimum(bet);
		q.setEvent(ev);
		session.persist(q);
		session.getTransaction().commit();
	}

	private List getPreguntasPorEvento(Evento ev) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query q = session.createQuery("from Questions where events= :ev");
		q.setParameter("events", ev);
		List result = q.list();
		Evento e = new Evento();
		session.getTransaction().commit();
		return result;
		
	}
	
	
	//PARA VER EL CICLO DE VIDA DE LOS OBJETOS
	/*public void printObjMemBD(String desc, Eventos e) {
		System.out.print("\tMem:<" + e + "> DB:<" + VerEventoUsandoJDBC.getEventosJDBC(e) + "> =>");
		System.out.println(desc);
	}*/

	public static void main(String[] args) {
		CrearEventos e = new CrearEventos();
		System.out.println("Creacion de eventos:");
		
		e.createAndStoreQuestionsSinEvento("¿Ganara Abraham?", 25);
		e.createAndStoreQuestionsSinEvento("¿Ganara Ramon?", 25);
		e.createAndStoreQuestionsSinEvento("¿Se aman?", 25);
		
		e.createAndStoreEventoSinPregunta("Barsa-Madrid", new Date());
		e.createAndStoreEventoSinPregunta("Albacete-Murcia", new Date());
		e.createAndStoreEventoSinPregunta("Abraham-Ramon", new Date());
		
		Pregunta q2= new Pregunta("Nose", 12);
		Pregunta q3= new Pregunta("Es lo mismo", 12);
		
		e.createAndStoreEventoConPregunta("Nose", new Date(), q2);
		e.createAndStoreEventoConPregunta("Queso vs Chesse", new Date(), q3);
		
		Evento ev1 = new Evento("Mantequilla con pan", new Date());
		Evento ev2 = new Evento("Pan con mantequilla", new Date());
		
		e.createAndStoreQuestionsConEvento("Porque no margarina?", 12, ev1);
		e.createAndStoreQuestionsConEvento("Puede ser integral?", 12, ev2);
		
		System.out.println("Listado de eventos:");
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List eventos = e.listaEventos();
		for (int i = 0; i < eventos.size(); i++) {
			Evento ev = (Evento) eventos.get(i);
			System.out.println("Numero evento: " + ev.getId() + " Descripcion: "
			+ ev.getDescription() + " Fecha: " + ev.getEventDate());
			List preguntas = e.getPreguntasPorEvento(ev);
			for(int j=0; i< preguntas.size();j++) {
				Pregunta q = (Pregunta) preguntas.get(i);
				System.out.println("Numero pregunta: " + q.getId() + " Pregunta: "
					+ q.getQuestion() + " Apuesta: " + q.getBetMinimum()+"Evento asociado: "+q.getEvent());
			}

		}
		
		session.getTransaction().commit();
	}

}
