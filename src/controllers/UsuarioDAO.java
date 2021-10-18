package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import models.Usuario;
import controllers.DBConnection;

public class UsuarioDAO implements IUsuario {

	private Connection conn;

	public UsuarioDAO() {
		this.conn = new DBConnection().getConnection();
	}
	
	@Override
	public boolean LogIn(String mail, String passwd) {
		String sql = "SELECT fun_autentica_usuario(?, ?) as resultado_login;";
		
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
				
				stmt.setString(1, mail);
				stmt.setString(2, passwd);
				
				ResultSet res = stmt.executeQuery();
				if(res == null) return false;

				int cod_ret = 0;
				
							
				while(res.next()) {
					cod_ret = res.getInt("resultado_login");
					
				}
				
				stmt.close();
				
				return (cod_ret == 0) ? true : false;
					
		}catch(SQLException err) {
			throw new RuntimeException(err);
		}
	}
		

}