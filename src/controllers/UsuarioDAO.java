package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Usuario;


public class UsuarioDAO implements IUsuario {

	private Connection conn;

	public UsuarioDAO() {}
	
	
	@Override
	public boolean AuthUser(String email, String passwd) throws SQLException {
		this.conn = new DBConnection().getConnection();
		String sql = "SELECT fun_autentica_usuario(?, ?) as resultado_login;";
		
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
				
				stmt.setString(1, email);
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
		}finally {
			this.conn.close();
		}
	}


	@Override
	public Usuario GetInfoUser(String email) throws SQLException {
		this.conn = new DBConnection().getConnection();
		String sql = "CALL sp_getinfo_user_by_mail(?);";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			
			stmt.setString(1,email);
			
			ResultSet res = stmt.executeQuery();
			Usuario user = new Usuario();
			
			while(res.next()) {
				user.setCpf(res.getString(1));
				user.setNome(res.getString(2));
				user.setEmail(res.getString(3));
				user.setNivel(res.getInt(4));
				user.setEndereco(res.getString(5));
			}
			
			return user;
		}catch(SQLException err) {
			throw new RuntimeException(err);
		}finally {
			this.conn.close();
		}
	}
	
	
}