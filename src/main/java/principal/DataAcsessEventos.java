package principal;

import HibernateUtil.HibernateUtil;
import dominio.*;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.*;

public class DataAcsessEventos {
	
	public DataAcsessEventos (){}
	
	/*public Usuario createAndStoreUsuario(String nombre, String password,
	String tipo) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	Usuario u = new Usuario();
	u.setNombre(nombre);
	u.setPassword(password);
	u.setTipo(tipo);
	session.save(u);
	session.getTransaction().commit();
	return u; }*/
	
	private Eventos createAndStoreEventoSinPregunta(String descripcion, Date fecha) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Eventos e = new Eventos();
		e.setDescription(descripcion);
		e.setEventDate(fecha);
		session.save(e);
		session.getTransaction().commit();
		return e;
	}
	
	public Questions createQuestionWithEvent(String preg, float bet, Eventos ev) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Questions q = new Questions();

		q.setQuestion(preg);
		q.setBetMinimum(bet);
		q.setEvent(ev);
		
		session.save(q);
		session.getTransaction().commit();
		return q;
		
	}
	
	/*public List<Eventos> getEventosDep(){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from Eventos").list();
		System.out.println("getEventosDep() : "+result);
		session.getTransaction().commit();
		return result;
		
	}*/
	
	

	public static void main(String[] args) {
		
		
		DataAcsessEventos e = new DataAcsessEventos ();
		System.out.println("Creación de eventos:"); //
		e.createQuestionWithEvent("queso", 2, new Eventos());
		e.createAndStoreEventoSinPregunta("Alaves vs Baskonia",new Date());
		e.createAndStoreEventoSinPregunta("Patata vs Patata",new Date());
		e.createQuestionWithEvent("maincra", 2, new Eventos());
		e.createAndStoreEventoSinPregunta("Maikel",new Date());
		e.createAndStoreEventoSinPregunta("Kakson",new Date());
		
		

	
	}
	
	public List<Eventos> getEventosLogin() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from EventoLogin").list();
		System.out.println("getEventosLogin() : "+result);
		session.getTransaction().commit();
		return result;
		}
	

		public List<Questions> getUsuarios() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from Usuario").list();
		session.getTransaction().commit();
		return result;
		}
		public List<Eventos> getEventosLoginv1(String nombreUsuario) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query q = session.createQuery("select lg from EventoLogin lg inner join lg.usuario u where u.nombre= :nombreUsuario");
			q.setParameter("nombreUsuario", nombreUsuario);
			List result=q.list();
			session.getTransaction().commit();
			return result;
			}
		public List<Eventos> getEventosLoginv2(String nombreUsuario) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query q =
			session.createQuery("from EventoLogin lg where lg.usuario.nombre= :nombreUsuario");
			q.setParameter("nombreUsuario", nombreUsuario);
			List result=q.list();
			session.getTransaction().commit();
			return result;
			
			}
		public List<Eventos> getEventosLoginv3(String nombreUsuario) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Criteria c =session.createCriteria(Eventos.class)
			.createCriteria("usuario")
			.add(Restrictions.eq("nombre",nombreUsuario));
			List<Eventos> result=c.list();
			session.getTransaction().commit();
			return result;
		}
		
		public boolean deleteUsuario(String usuario) {
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				try {
					Questions u=(Questions)session.get(Questions.class, usuario);
					//Query q = session.createQuery("delete from EventoLogin where usuario = :usua");
					//q.setParameter("usua", u);
					//q.executeUpdate();
					session.delete(u);
					session.getTransaction().commit();
				}
				catch (Exception ex)
				{ System.out.println("Error: "+ex.toString());
				return false;
				}
				return true;
		}
		
		public Questions createAndStoreUsuarioConEventoLogin
		(String nombre, String password, String tipo, boolean login, Date fecha)
		{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Questions u = new Questions();
		u.setQuestion(nombre);
		u.setBetMinimum(54);
		u.setResult("Perdio messi");
		Eventos lg = new Eventos();
		lg.setDescription("ANKARA MESSI");
		lg.setEventDate(new Date());
		lg.setQuestions(u);
		//HashSet es = new HashSet();
		//es.add(lg);
		//u.setEventos(es);
		//HashSet es = new HashSet();
		//es.add(lg);
		//u.setEventos(es);
		session.save(u);
		session.save(lg);
		session.getTransaction().commit();
		return u;
		}
		
		public Questions getUsuario(String usuario) {
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query q = session.createQuery("select lg.usuario from EventoLogin lg where nombre=:usuario");
			q.setParameter("usuario", usuario);
			List result=q.list();
			session.getTransaction().commit();
			return (Questions)result.get(0);
		
		}
		
}
