package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;

public interface IProduto {

	public JTable GetProdutos();
	public ArrayList<String> GetMarcasExistentes() throws SQLException; // Popular ComboBox
	public ArrayList<String> GetTipoExistentes() throws SQLException;   // Popular ComboBox
}
