package principal;

import HibernateUtil.HibernateUtil;

import dominio.FindEventos;

import org.hibernate.Query;
import org.hibernate.Session;
import java.util.*;

public class CrearEventos {

	
	public CrearEventos() {}
	
	private void createAndStoreEventoLogin(Long id, String descripcion, Date fecha) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		FindEventos e = new FindEventos();
		e.setId(id);
		e.setDescripcion(descripcion);
		e.setFecha(fecha);
		session.save(e);
		session.getTransaction().commit();
		}
		private List listaEventos() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from EventoLogin").list();
		session.getTransaction().commit();
		return result;
		}
		//Mismo que el de arriba pero sobrecargado
		private void createAndStoreEventoLogin(String descripcion, Date fecha) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			FindEventos e = new FindEventos();
			e.setDescripcion(descripcion);
			e.setFecha(fecha);
			session.save(e);
			session.getTransaction().commit();
			}
		
		private void createAndStoreUsuario(String nombre, String password, String tipo) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Usuario u = new Usuario();
			u.setNombre(nombre);
			u.setPassword(password);
			u.setTipo(tipo);
			session.persist(u);
			session.getTransaction().commit();
			}
			private void createAndStoreEventoLogin(String usuario, boolean login, Date fecha) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query q= session.createQuery("from Usuario where nombre= :usuario");
			q.setParameter("usuario", usuario);
			List result=q.list();
			FindEventos e = new FindEventos();
			try {
			e.setUsuario((Usuario)result.get(0));
			}
			catch (Exception ex)
			{System.out.println("Error al crear instancia de EventoLogin: no existe usuario"
			+ex.toString());}
			e.setLogin(login);
			e.setFecha(fecha);
			session.save(e);
			session.getTransaction().commit();
			}
		
			public void printObjMemBD(String desc, Usuario u) {
				System.out.print("\tMem:<"+u+"> DB:<"+VerEventoUsandoJDBC.getUsuariosJDBC(u)+"> =>");
				System.out.println(desc); 		
			}
		
		
		public static void main(String[] args) {
			CrearEventos e = new CrearEventos();
			System.out.println("Creación de eventos:");
			//e.createAndStoreEventoLogin(1L,"Pepe ha hecho login correctamente", new Date());
			//e.createAndStoreEventoLogin(2L,"Nerea ha intentado hacer login", new Date());
			//e.createAndStoreEventoLogin(3L,"Kepa ha hecho login correctamente", new Date());
			//Las comentan para que no de un error de clave primaria 
			//e.createAndStoreEventoLogin("Pepe ha hecho login correctamente", new Date());
			//e.createAndStoreEventoLogin("Nerea ha intentado hacer login", new Date());
			//e.createAndStoreEventoLogin("Kepa ha hecho login correctamente", new Date());
			e.createAndStoreUsuario("Ane", "125", "alumno");
			e.createAndStoreEventoLogin ("Ane",true, new Date());
			e.createAndStoreEventoLogin ("Ane",false, new Date());
			System.out.println("Lista de eventos:");
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			List result = session.createQuery("from EventoLogin").list();
			for (int i = 0; i < result.size(); i++) {
			EventoLogin ev = (EventoLogin) result.get(i);
			System.out.println("Id: " + ev.getId() + " Descripción: " +
			ev.getDescripcion() + " Fecha: " + ev.getFecha()+ " Login: " + ev.isLogin());
			}
			session.getTransaction().commit();
			/*List eventos = e.listaEventos();
			for (int i = 0; i < eventos.size(); i++) {
			EventoLogin ev = (EventoLogin) eventos.get(i);
			System.out.println("Id: " + ev.getId() + " Descripcion: "
			+ ev.getDescripcion() + " Fecha: " + ev.getFecha());
			}*/
			
			//No se donde va esto
			/*System.out.println("======================");
			System.out.println("Ciclo de vida de los objetos:");
			System.out.println("======================");
			Usuario u = new Usuario();
			u.setNombre("Nerea");
			u.setPassword("1234");
			u.setTipo("profesor");
			System.out.println("new => TRANSIENT");
			e.printObjMemDB("Nerea está en memoria, pero no en la BC ",u);
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(u);
			System.out.println("save => PERSISTENT");
			e.printObjMemDB("Nerea todavía no está en la BD porque no se ha hecho commit",u);
			u.setPassword("1235");
			e.printObjMemDB("Se ha ejecutado u.setPassword(\"1235\") pero no commit. Por tanto, Nerea
			todavía no está en la BD.",u);
			session.getTransaction().commit();
			System.out.println("close (commit) => DETACHED");
			e.printObjMemDB("Se ha hecho commit, se los cambios de han hecho en la BD ",u);
			u.setPassword("1236");
			e.printObjMemDB("Se ha ejecutado u.setPassword(\"1236\"), pero el objeto no está conectado con
			la BD (detached)",u);
			try { session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(u); // poniendo save => ERROR
			session.getTransaction().commit();
			} catch (Exception ex) {
			System.out.println("save => ERROR: como el objeto está 'detached', save intenta meter el
			mismo objeto de nuevo y saltará un error de clave primaria.");
			session.getTransaction().rollback();}
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(u);
			System.out.println("saveOrUpdate => PERSISTENT");
			e.printObjMemDB("ahora el objeto es persistente, pero para poder ver la nueva contraseña, habrá
			que hacer commit",u);
			session.save(u);
			System.out.println("save => PERSISTENT: el objeto está conectado a la base de datos, no se crea
			uno nuevo, si no que se actualiza, por tanto, no habrá error");
			session.getTransaction().commit();
			System.out.println("close (commit) => DETACHED");
			e.printObjMemDB("\tSe ha hecho commit, se ve la nueva contraseña en la base de datos",u);
			Laboratorio 4.2. – Hibernate
			23
			u.setPassword("1237");
			e.printObjMemDB("Se ha ejecutado u.setPasahitza(\"1237\"), pero el objeto no está conectado a la
			base de datos (detached)",u);
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			u=(Usuario)session.get(Usuario.class, u.getNombre());
			System.out.println("get => PERSISTENT");
			e.printObjMemDB("Se ha vuelto a recuperar el usuario de la base de datos, por tanto, se ha
			perdido el cambio de la contraseña",u);
			session.delete(u);
			System.out.println("delete => TRANSIENT");
			e.printObjMemDB("Hasta que se haga commit no se borrará el objeto de la base de datos ",u);
			session.getTransaction().commit();
			System.out.println("close (commit) => DETACHED");
			e.printObjMemDB("Se ha hecho commit, no está en la base de datos pero sí en memoria. ",u);*/
		}
	
}
