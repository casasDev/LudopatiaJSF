package principal;
import java.sql.*;
import java.util.Date;



public class VerEventoUsandoJDBC {

	public static void main(String[] args) {
		Connection c;
		Statement s;
		ResultSet rs;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mariadb://localhost/eventosProyecto","root","admin");
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM EVENTOS");
			System.out.println("EVENTOS (EVENTNUMBER, DESCRIPTION, EVENTDATE");
			while (rs.next()){
				Long id = rs.getLong("EVENTNUMBER");
				String des = rs.getString("DESCRIPTION");
				Date fecha = rs.getDate("EVENTDATE");
				System.out.println(id+" / "+des+" / "+fecha);
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
		s = c.prepareStatement("SELECT * FROM EVENTOS WHERE eventDate=?");
		s.setString(1, "queso");
		rs = s.executeQuery();
		if (rs.next())
		return (String)(rs.getString("NOMBRE")+"/"+rs.getString("PASSWORD")+"/"+rs.getString("TIPO"));
		} catch (Exception ex) {ex.printStackTrace();}
		return "--/--/--"; }
	}


