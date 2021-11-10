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
	public JTable GetProdutos(String parameters) throws SQLException {
		
		this.conn = new DBConnection().getConnection();
		String sql = "SELECT * FROM `view_retorna_produtos`";
		
		sql = sql + parameters;
		
		sql = (sql.replace("\n","")) + ";";
		
		System.out.println(sql);
		
		try {
			
				PreparedStatement stmt = this.conn.prepareStatement(sql);
				
				String [] columns = {"ID", "Cód. Tipo Produto", "Tipo de Produto","Cód. Marca","Marca", "Descrição","Preço", "Estoque" ,"Disponível", "Ação"};
				DefaultTableModel modeloTabela = new DefaultTableModel(null,columns);

				ResultSet res = stmt.executeQuery(sql);
				
				
				if(res != null) {
					while(res.next()) {
						
						modeloTabela.addRow(new String[] {
								Integer.toString(res.getInt(1)), // ID
								res.getString(2),
								res.getString(3), 							// Tipo Produto
								res.getString(4),
								res.getString(5), 							// Marca
								res.getString(6), 							// Descricao
								"R$ "+Double.toString(res.getDouble(7)), 	// Valor
								Integer.toString(res.getInt(8)),			// Estoque
								(res.getBoolean(9)) ? "✔" : "❌"
										
								
						});
					}
				}
				res.close();
				stmt.close();
				
				JTable tabelafinal = new JTable() {
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				tabelafinal.setModel(modeloTabela);
				return tabelafinal;
					
		}catch(SQLException err) {
			throw new RuntimeException(err);
		}finally {
			this.conn.close();
		}
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

