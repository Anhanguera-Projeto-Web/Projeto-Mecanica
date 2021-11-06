
package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
	
	public DBConnection() {}
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_mecanica",  "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	};
	
	public boolean checkConnection() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto_mecanica",  "root", "root");
			return !conn.isClosed();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
			
		}
	}
}
