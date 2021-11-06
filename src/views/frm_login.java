package views;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controllers.UsuarioDAO;
import models.Usuario;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class frm_login extends Main{

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	
	
	public frm_login() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(60, 82, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(60, 115, 86, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				UsuarioDAO userdao = new UsuarioDAO();
				
				boolean NaopodeLogar = false;
				try {
					NaopodeLogar = userdao.AuthUser(textField.getText(), String.valueOf(passwordField.getPassword()));
					if(NaopodeLogar) {
						JOptionPane.showMessageDialog(null,"Endereço de e-mail ou senha incorretos.", "Erro ao se autenticar.", JOptionPane.ERROR_MESSAGE);
						return;
					}else {
						start_main_menu(userdao.GetInfoUser(textField.getText()));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}		
			}
		});
		btnNewButton.setBounds(60, 146, 89, 23);
		frame.getContentPane().add(btnNewButton);
		this.frame.setVisible(true);
	}
	
	private void start_main_menu(Usuario user) {
		this.frame.dispose();
		Main.currentUser = user;
		new frm_main_menu();
	}
}
