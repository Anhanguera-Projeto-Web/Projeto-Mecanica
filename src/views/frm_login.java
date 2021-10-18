package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controllers.UsuarioDAO;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frm_login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frm_login window = new frm_login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frm_login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
				
				boolean podeLogar = userdao.LogIn(textField.getText(), String.valueOf(passwordField.getPassword()));
				
				if(!podeLogar)
					JOptionPane.showMessageDialog(null,"Ok", "Pode logar", JOptionPane.DEFAULT_OPTION);
				else
					JOptionPane.showMessageDialog(null,"Not Ok", "Proibido", JOptionPane.ERROR_MESSAGE);
					
				
			}
		});
		btnNewButton.setBounds(60, 146, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
