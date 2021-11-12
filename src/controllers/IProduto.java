package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;

import models.Produto;

public interface IProduto {

	public JTable GetProdutos(String parameters) throws SQLException;
	public ArrayList<String> GetMarcasExistentes() throws SQLException; // Popular ComboBox
	public ArrayList<String> GetTipoExistentes() throws SQLException;   // Popular ComboBox
	public boolean CriarProduto(Produto prod) throws SQLException;
	
	public ArrayList<String> GetMetodosPagamentos() throws SQLException;
	
}
