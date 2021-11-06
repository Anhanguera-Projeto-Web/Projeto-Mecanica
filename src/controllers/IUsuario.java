package controllers;
import java.sql.SQLException;

import models.Usuario;

public interface IUsuario {

	public boolean AuthUser(String email, String passwd) throws SQLException;
	public Usuario GetInfoUser(String email) throws SQLException;
	
}
