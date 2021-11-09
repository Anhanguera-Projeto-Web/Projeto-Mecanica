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
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class frm_login extends Main{

	private JFrame frmProjetoMecnica;
	private JTextField txtField_email;
	private JPasswordField passFld_senha;
	
	
	public frm_login() {
		initialize();
	}

	
	private void initialize() {
		frmProjetoMecnica = new JFrame();
		frmProjetoMecnica.setIconImage(Toolkit.getDefaultToolkit().getImage(frm_login.class.getResource("/assets/icon-32.png")));	
		frmProjetoMecnica.getContentPane().setBackground(new Color(160, 82, 45));
		frmProjetoMecnica.setTitle("Projeto Mec\u00E2nica - Anhanguera");
		frmProjetoMecnica.setBounds(100, 100, 589, 220);
		frmProjetoMecnica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProjetoMecnica.getContentPane().setLayout(null);
		
		JPanel panel_imagemprograma = new JPanel();
		panel_imagemprograma.setBounds(10, 11, 180, 159);
		frmProjetoMecnica.getContentPane().add(panel_imagemprograma);
		panel_imagemprograma.setLayout(null);
		
		JLabel lbl_imagemprograma = new JLabel("");
		lbl_imagemprograma.setIcon(new ImageIcon(frm_login.class.getResource("/assets/icon-128.png")));
		lbl_imagemprograma.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_imagemprograma.setBounds(10, 11, 160, 137);
		panel_imagemprograma.add(lbl_imagemprograma);
		
		JPanel panel_login = new JPanel();
		panel_login.setBounds(200, 11, 363, 159);
		frmProjetoMecnica.getContentPane().add(panel_login);
		panel_login.setLayout(null);
		
		txtField_email = new JTextField("admin@admin.com");
		txtField_email.setBounds(73, 70, 280, 20);
		panel_login.add(txtField_email);
		txtField_email.setColumns(10);
		
		passFld_senha = new JPasswordField("admin");
		passFld_senha.setBounds(73, 94, 280, 20);
		panel_login.add(passFld_senha);
		
		JButton btn_doLogin = new JButton("Entrar");
		btn_doLogin.setBounds(264, 125, 89, 23);
		panel_login.add(btn_doLogin);
		panel_login.getRootPane().setDefaultButton(btn_doLogin);
		
		JLabel lbl_email = new JLabel("E-mail: ");
		lbl_email.setFont(new Font("Arial", Font.PLAIN, 11));
		lbl_email.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_email.setBounds(10, 73, 57, 14);
		panel_login.add(lbl_email);
		
		JLabel lbl_titulo = new JLabel("Projeto Mec\u00E2nica");
		lbl_titulo.setFont(new Font("Arial", Font.BOLD, 33));
		lbl_titulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_titulo.setBounds(37, 11, 316, 48);
		panel_login.add(lbl_titulo);
		
		JLabel lbl_senha = new JLabel("Senha: ");
		lbl_senha.setFont(new Font("Arial", Font.PLAIN, 11));
		lbl_senha.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_senha.setBounds(10, 97, 57, 14);
		panel_login.add(lbl_senha);
		btn_doLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doLogin();		
			}
		});
		this.frmProjetoMecnica.setVisible(true);
	}
	
	private void doLogin() {

		UsuarioDAO userdao = new UsuarioDAO();
		
		boolean NaopodeLogar = false;
		try {
			NaopodeLogar = userdao.AuthUser(txtField_email.getText(), String.valueOf(passFld_senha.getPassword()));
			if(NaopodeLogar) {
				JOptionPane.showMessageDialog(null,"Endereço de e-mail ou senha incorretos.", "Erro ao se autenticar.", JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				start_main_menu(userdao.GetInfoUser(txtField_email.getText()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void start_main_menu(Usuario user) throws SQLException {
		this.frmProjetoMecnica.dispose();
		Main.currentUser = user;
		new frm_main_menu();
	}
}
