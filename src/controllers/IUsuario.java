package controllers;
import java.sql.SQLException;

import javax.swing.JTable;

import models.Usuario;

public interface IUsuario {

	// Autenticação
	public boolean AuthUser(String email, String passwd) throws SQLException;
	public Usuario GetInfoUser(String email) throws SQLException;
	
	// Tela principal
	public JTable GetProdutosAEsgotar() throws SQLException;
}
