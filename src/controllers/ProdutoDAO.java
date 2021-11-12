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
								(res.getBoolean(9)) ? "SIM" : "NAO"
										
								
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

	@Override
	public boolean CriarProduto(Produto prod) throws SQLException {
		this.conn = new DBConnection().getConnection();
		String sql = "CALL sp_criar_produto(?,?,?,?, ?);";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
				
				PreparedStatement stmt_get_tipoid = this.conn.prepareStatement("SELECT produto_tipoid FROM produto_tipo WHERE definicao = ?");
				PreparedStatement stmt_get_marcaid = this.conn.prepareStatement("SELECT produto_marcaid FROM produto_marca WHERE definicao = ?");
				
				int tipoid = 0;
				int marcaid = 0;
				
				stmt_get_tipoid.setString(1, prod.getTipo_produto());
				ResultSet res = stmt_get_tipoid.executeQuery();
				if(res!=null) {
					while(res.next()) {
						tipoid = res.getInt("produto_tipoid");
					}
				}
				stmt_get_tipoid.close();
				
				
				stmt_get_marcaid.setString(1, prod.getMarca());
				res = stmt_get_marcaid.executeQuery();
				if(res!=null) {
					while(res.next()) {
						marcaid = res.getInt("produto_marcaid");
					}
				}
				stmt_get_marcaid.close();
				
				stmt.setString(1, prod.getDescricao());
				stmt.setInt(2, tipoid);
				stmt.setInt(3, marcaid);
				stmt.setDouble(4, prod.getPreco());
				stmt.setInt(5, prod.getEstoque());
				

				stmt.executeQuery();
				
				stmt.close();
				
				return true;
					
		}catch(SQLException err) {
			throw new RuntimeException(err);
		}finally {
			conn.close();
		}
	}

	@Override
	public ArrayList<String> GetMetodosPagamentos() throws SQLException {
		this.conn = new DBConnection().getConnection();
		String sql = "SELECT definicao FROM metodo_pagamento;";
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
				
			
				ResultSet res = stmt.executeQuery();
				if(res == null) return null;

				ArrayList<String> marcas = new ArrayList<String>();
					
				while(res.next()) {
					marcas.add(res.getString("definicao"));
				}
				stmt.close();
				
				return marcas;
					
		}catch(SQLException err) {
			throw new RuntimeException(err);
		}finally {
			conn.close();
		
		}
	}
}