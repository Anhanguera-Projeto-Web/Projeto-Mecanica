package views;
import javax.swing.JOptionPane;

import controllers.DBConnection;
import models.Usuario;

public class Main {

	public static Usuario currentUser;
	
	public static void main(String[] args) {

	 if(!new DBConnection().checkConnection()) {
		 JOptionPane.showMessageDialog(null,"Banco de Dados parece estar desligado ou com acesso indevido.", "Erro ao inicializar o aplicativo.", JOptionPane.ERROR_MESSAGE);
		 System.exit(0);
	 }else{
		 currentUser = new Usuario();
		 new frm_login();
	 }
	 
	}
}
