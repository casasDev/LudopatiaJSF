package principal;
import java.sql.*;
import java.util.Date;

import modelo.dominio.Usuario;

public class VerEventoUsandoJDBC {

	public static void main(String[] args) {
		Connection c;
		Statement s;
		ResultSet rs;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://localhost/eventosProy","root","admin");
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM FINDQUESTION");
			System.out.println("FINDQUESTION (EVENTO, FECHA");
			while (rs.next()){
				Evento eve = rs.getEvento("EVENTO");
				Date fecha = rs.getDate("FECHA");
				System.out.println(id+" / "+descripcion+" / "+fecha);
			}
		} 
		catch (Exception e) {e.printStackTrace();} 
		
	}
	public static String getEventoJDBC(Date d) {
		Connection c;
		PreparedStatement s;
		ResultSet rs;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mysql://localhost/eventos", "root", "admin");
		s = c.prepareStatement("SELECT * FROM USUARIO WHERE nombre=?");
		s.setString(1, "queso");
		rs = s.executeQuery();
		if (rs.next())
		return (String)(rs.getString("NOMBRE")+"/"+rs.getString("PASSWORD")+"/"+rs.getString("TIPO"));
		} catch (Exception ex) {ex.printStackTrace();}
		return "--/--/--"; }
	}


