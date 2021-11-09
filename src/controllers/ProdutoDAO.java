package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Produto;
public class ProdutoDAO implements IProduto {

	private Connection conn;

	public ProdutoDAO() {}
	
	@Override
	public JTable GetProdutos() {
		return new JTable();
		/*this.conn = new DBConnection().getConnection();
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
		}*/
	}

	@Override
	public ArrayList<String> GetMarcasExistentes() throws SQLException {
		this.conn = new DBConnection().getConnection();
		String sql = "SELECT definicao FROM produto_marca;";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
				
			
				ResultSet res = stmt.executeQuery();
				if(res == null) return null;

				ArrayList<String> marcas = new ArrayList<String>();
					
				while(res.next()) {
					marcas.add(res.getString("definicao"));
				}
				marcas.add("Todos");
				stmt.close();
				
				return marcas;
					
		}catch(SQLException err) {
			throw new RuntimeException(err);
		}finally {
			conn.close();
		}
		
		
		
	}

	@Override
	public ArrayList<String> GetTipoExistentes() throws SQLException{
		this.conn = new DBConnection().getConnection();
		String sql = "SELECT definicao FROM produto_tipo;";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
				
			
				ResultSet res = stmt.executeQuery();
				if(res == null) return null;

				ArrayList<String> tipo = new ArrayList<String>();
					
				while(res.next()) {
					tipo.add(res.getString("definicao"));
				}
				tipo.add("Todos");
				
				stmt.close();
				
				return tipo;
					
		}catch(SQLException err) {
			throw new RuntimeException(err);
		}finally {
			conn.close();
		}
		
	}

}

