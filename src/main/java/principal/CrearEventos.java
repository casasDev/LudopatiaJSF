package principal;

import HibernateUtil.HibernateUtil;
import dominio.Evento;
import dominio.FindEventos;
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
		
		e.createAndStoreQuestionsSinEvento("�Ganara Abraham?", 25);
		e.createAndStoreQuestionsSinEvento("�Ganara Ramon?", 25);
		e.createAndStoreQuestionsSinEvento("�Se aman?", 25);
		
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
		// e.createAndStoreEventoLogin(1L,"Pepe ha hecho login correctamente", new
		// Date());
		// e.createAndStoreEventoLogin(2L,"Nerea ha intentado hacer login", new Date());
		// e.createAndStoreEventoLogin(3L,"Kepa ha hecho login correctamente", new
		// Date());
		// Las comentan para que no de un error de clave primaria
		// e.createAndStoreEventoLogin("Pepe ha hecho login correctamente", new Date());
		// e.createAndStoreEventoLogin("Nerea ha intentado hacer login", new Date());
		// e.createAndStoreEventoLogin("Kepa ha hecho login correctamente", new Date());
		/*e.createAndStoreUsuario("Ane", "125", "alumno");
		e.createAndStoreEventoLogin("Ane", true, new Date());
		e.createAndStoreEventoLogin("Ane", false, new Date());
		System.out.println("Lista de eventos:");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from EventoLogin").list();
		for (int i = 0; i < result.size(); i++) {
			EventoLogin ev = (EventoLogin) result.get(i);
			System.out.println("Id: " + ev.getId() + " Descripción: " + ev.getDescripcion() + " Fecha: "
					+ ev.getFecha() + " Login: " + ev.isLogin());
		}*/	
	
		//session.getTransaction().commit();
		/*
		 * List eventos = e.listaEventos(); for (int i = 0; i < eventos.size(); i++) {
		 * EventoLogin ev = (EventoLogin) eventos.get(i); System.out.println("Id: " +
		 * ev.getId() + " Descripcion: " + ev.getDescripcion() + " Fecha: " +
		 * ev.getFecha()); }
		 */

		// No se donde va esto
		/*
		 * System.out.println("======================");
		 * System.out.println("Ciclo de vida de los objetos:");
		 * System.out.println("======================"); Usuario u = new Usuario();
		 * u.setNombre("Nerea"); u.setPassword("1234"); u.setTipo("profesor");
		 * System.out.println("new => TRANSIENT");
		 * e.printObjMemDB("Nerea está en memoria, pero no en la BC ",u); session =
		 * HibernateUtil.getSessionFactory().getCurrentSession();
		 * session.beginTransaction(); session.save(u);
		 * System.out.println("save => PERSISTENT"); e.
		 * printObjMemDB("Nerea todavía no está en la BD porque no se ha hecho commit"
		 * ,u); u.setPassword("1235");
		 * e.printObjMemDB("Se ha ejecutado u.setPassword(\"1235\") pero no commit. Por
		 * tanto, Nerea todavía no está en la BD.",u);
		 * session.getTransaction().commit();
		 * System.out.println("close (commit) => DETACHED");
		 * e.printObjMemDB("Se ha hecho commit, se los cambios de han hecho en la BD ",u
		 * ); u.setPassword("1236");
		 * e.printObjMemDB("Se ha ejecutado u.setPassword(\"1236\"), pero el objeto no
		 * está conectado con la BD (detached)",u); try { session =
		 * HibernateUtil.getSessionFactory().getCurrentSession();
		 * session.beginTransaction(); session.save(u); // poniendo save => ERROR
		 * session.getTransaction().commit(); } catch (Exception ex) {
		 * System.out.println("save => ERROR: como el objeto está 'detached', save
		 * intenta meter el mismo objeto de nuevo y saltará un error de clave
		 * primaria."); session.getTransaction().rollback();} session =
		 * HibernateUtil.getSessionFactory().getCurrentSession();
		 * session.beginTransaction(); session.saveOrUpdate(u);
		 * System.out.println("saveOrUpdate => PERSISTENT"); e.printObjMemDB("ahora el
		 * objeto es persistente, pero para poder ver la nueva contraseña, habrá que
		 * hacer commit",u); session.save(u); System.out.println("save => PERSISTENT: el
		 * objeto está conectado a la base de datos, no se crea uno nuevo, si no que se
		 * actualiza, por tanto, no habrá error"); session.getTransaction().commit();
		 * System.out.println("close (commit) => DETACHED"); e.
		 * printObjMemDB("\tSe ha hecho commit, se ve la nueva contraseña en la base de datos"
		 * ,u); Laboratorio 4.2. – Hibernate 23 u.setPassword("1237");
		 * e.printObjMemDB("Se ha ejecutado u.setPasahitza(\"1237\"), pero el objeto no
		 * está conectado a la base de datos (detached)",u); session =
		 * HibernateUtil.getSessionFactory().getCurrentSession();
		 * session.beginTransaction(); u=(Usuario)session.get(Usuario.class,
		 * u.getNombre()); System.out.println("get => PERSISTENT"); e.printObjMemDB("Se
		 * ha vuelto a recuperar el usuario de la base de datos, por tanto, se ha
		 * perdido el cambio de la contraseña",u); session.delete(u);
		 * System.out.println("delete => TRANSIENT"); e.
		 * printObjMemDB("Hasta que se haga commit no se borrará el objeto de la base de datos "
		 * ,u); session.getTransaction().commit();
		 * System.out.println("close (commit) => DETACHED"); e.
		 * printObjMemDB("Se ha hecho commit, no está en la base de datos pero sí en memoria. "
		 * ,u);
		 */
	}

}
