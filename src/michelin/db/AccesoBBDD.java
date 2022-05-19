package michelin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoBBDD {
	private String driver;
	private String url;
	
	public AccesoBBDD() {
		driver ="org.sqlite.JDBC";
		url = "jdbc:sqlite:db/Guia_Michelin.db";
	}
	
	public Connection getConexion() throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url);
		
		return con;
	}
	
}
