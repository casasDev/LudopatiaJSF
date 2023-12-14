package principal;

import modelo.HibernateUtil;
import modelo.dominio.Usuario;
import modelo.dominio.EventoLogin;
import modelo.dominio.Maquina;
import modelo.dominio.Persona;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.*;

public class DataAcsessEventos {
	
	public DataAcsessEventos (){}
	public Usuario createAndStoreUsuario(String nombre, String password,
	String tipo) {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	session.beginTransaction();
	Usuario u = new Usuario();
	u.setNombre(nombre);
	u.setPassword(password);
	u.setTipo(tipo);
	session.save(u);
	session.getTransaction().commit();
	return u; }
	public EventoLogin createAndStoreEventoLogin(String usuario,
			boolean login, Date fecha) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
			session.beginTransaction();
			EventoLogin e = new EventoLogin();
			try {
			e.setUsuario((Usuario)session.get(Usuario.class, usuario));
			e.setLogin(login);
			//if (fecha!=null) 
				e.setFecha(fecha);
			//else throw new Exception("Falta la fecha");
			session.save(e);
			session.getTransaction().commit();
			}
			catch (org.hibernate.PropertyValueException ex)
			{System.out.println("Error: falta la fecha ");
			e=null;
			session.getTransaction().rollback();
			e=null;}
			catch (Exception ex)
			{System.out.println("Error: el usuario no existe: "+ex.toString());
			e=null;}
			return e;
			}

	public static void main(String[] args) {
//	DataAcsessEventos e = new DataAcsessEventos ();
//	System.out.println("Creación de eventos:"); //
//	e.createAndStoreUsuario ("Ane", "125", "alumno");
//	e.createAndStoreEventoLogin("Ane",true, new Date());
//	e.createAndStoreEventoLogin("Ane",false, new Date());
//	e.createAndStoreUsuario("Kepa", "126", "alumno");
//	e.createAndStoreEventoLogin("Kepa",true, new Date());
//	e.createAndStoreEventoLogin("Kepa",false, new Date());
//	List <Usuario> us=e.getUsuarios();
//	System.out.println("Usuarios:" + us);
//	List <EventoLogin> lg=e.getEventosLogin();
//	System.out.println("EventosLogin: "+lg);
//	Usuario usua=lg.get(0).getUsuario();
//	// lg.get(0) se obtiene el usuario a partir del evento: .getUsuario()
//	System.out.println(usua);
//	List <EventoLogin> lg1=e.getEventosLoginv1(usua.getNombre());
//	System.out.println("Eventos Login de: " + usua.getNombre()+": "+lg1);
//	List <EventoLogin> lg2=e.getEventosLoginv2(usua.getNombre());
//	System.out.println("Eventos Login de: " + usua.getNombre()+": "+lg2);
//	List <EventoLogin> lg3=e.getEventosLoginv3(usua.getNombre());
//	System.out.println("Eventos Login de: " + usua.getNombre()+": "+lg3);
//	System.out.println(usua.getEventos());
//	
//	EventoLogin lg4 = e.createAndStoreEventoLogin("Ane",true, null);
//	System.out.println(lg4);
//	
//	e.createAndStoreUsuario("Nekane", "127", "alumno");
//	e.createAndStoreEventoLogin("Nekane",true, new Date());
//	System.out.println(e.getEventosLogin());
//	boolean res=e.deleteUsuario ("Nekane");
//	System.out.println(e.getEventosLogin());
//	
//	usua= e.createAndStoreUsuarioConEventoLogin ("Peru","128","alumno",true,new Date());
//	System.out.println("=> " + e.getUsuarios());
//	System.out.println("=> " + e.getEventosLogin());
//	System.out.println("=> Usuario: " +usua+" Sus eventos de login: "+usua.getEventos());
//	
//	
//	System.out.println("=> Usuario: " + usua); // Será Peru
//	System.out.println("1=>"+usua.getEventos()); // el usuarion o tendrá eventos de login
//	usua = e.getUsuario("Peru");
//	System.out.println("2=>"+/*erab*/usua.getEventos()); // ahora sí, se han traído de la base de datos!
//	
//	ESTE NO SE SI DEBERIA SER COMENT POR SEACASO SI
//	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//	session.beginTransaction();
//	System.out.println("5 => get(EventoLogin.class,1L) => ");
//	EventoLogin lg5=(EventoLogin)session.get(EventoLogin.class,1L);
//	System.out.println("5 => getUsuario().getTipo() => ");
//	System.out.println(lg5.getUsuario().getTipo());
//	session.getTransaction().commit();
		
		DataAcsessEventos e = new DataAcsessEventos();
		// Comenta todo el código del método main (se ha modificado la clase Usuario)
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Usuario u1=new Usuario();
		u1.setNombre("Kepa");
		u1.setPassword("125");
		u1.setTipo("alumno");
		Usuario u2=new Usuario();
		u2.setNombre("Nerea");
		u2.setPassword("126");
		u2.setTipo("alumno");
		Maquina m1=new Maquina();
		m1.setCodigo(1);
		m1.setNombre("Casa");
		Maquina m2=new Maquina();
		m2.setCodigo(2);
		m2.setNombre("Trabajo");
		Set<Maquina> ms=new HashSet<Maquina>();
		ms.add(m1);
		ms.add(m2);
		Set<Usuario> us=new HashSet<Usuario>();
		us.add(u1);
		us.add(u2);
		// u1.setMaquinas(ms);
		// u2.setMaquinas(ms);
		m1.setUsuarios(us);
		m2.setUsuarios(us);
		session.save(u1);
		session.save(u2);
		session.save(m1);
		session.save(m2);
		session.getTransaction().commit();
		
		
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Usuario u3 = new Usuario();
		u3.setNombre("Koldo");
		u3.setPassword("125");
		u3.setTipo("alumno");
		Persona p1 = new Persona();
		p1.setTelefono("943112233");
		u3.setPersona(p1);
		p1.setUsuario(u3);
		session.persist(u3);
		session.persist(p1);
		session.getTransaction().commit();
		System.out.println("P:<"+u3.getPersona()+"> U:<"+ u3.getPersona().getUsuario()+">");
	
	}
	
	public List<EventoLogin> getEventosLogin() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from EventoLogin").list();
		System.out.println("getEventosLogin() : "+result);
		session.getTransaction().commit();
		return result;
		}
		public List<Usuario> getUsuarios() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from Usuario").list();
		session.getTransaction().commit();
		return result;
		}
		public List<EventoLogin> getEventosLoginv1(String nombreUsuario) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query q = session.createQuery("select lg from EventoLogin lg inner join lg.usuario u where u.nombre= :nombreUsuario");
			q.setParameter("nombreUsuario", nombreUsuario);
			List result=q.list();
			session.getTransaction().commit();
			return result;
			}
		public List<EventoLogin> getEventosLoginv2(String nombreUsuario) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query q =
			session.createQuery("from EventoLogin lg where lg.usuario.nombre= :nombreUsuario");
			q.setParameter("nombreUsuario", nombreUsuario);
			List result=q.list();
			session.getTransaction().commit();
			return result;
			
			}
		public List<EventoLogin> getEventosLoginv3(String nombreUsuario) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Criteria c =session.createCriteria(EventoLogin.class)
			.createCriteria("usuario")
			.add(Restrictions.eq("nombre",nombreUsuario));
			List<EventoLogin> result=c.list();
			session.getTransaction().commit();
			return result;
		}
		
		public boolean deleteUsuario(String usuario) {
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				try {
					Usuario u=(Usuario)session.get(Usuario.class, usuario);
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
		
		public Usuario createAndStoreUsuarioConEventoLogin
		(String nombre, String password, String tipo, boolean login, Date fecha)
		{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Usuario u = new Usuario();
		u.setNombre(nombre);
		u.setPassword(password);
		u.setTipo(tipo);
		EventoLogin lg = new EventoLogin();
		lg.setUsuario(u);
		lg.setLogin(login);
		lg.setFecha(fecha);
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
		
		public Usuario getUsuario(String usuario) {
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query q = session.createQuery("select lg.usuario from EventoLogin lg where nombre=:usuario");
			q.setParameter("usuario", usuario);
			List result=q.list();
			session.getTransaction().commit();
			return (Usuario)result.get(0);
		
		}
		
}
