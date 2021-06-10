package in.stack.eStore.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	Connection connection;
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException ex) {
			
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoredb","root","root");
		} catch (SQLException ex) {
			
		}
		return connection;
		
	}
}
