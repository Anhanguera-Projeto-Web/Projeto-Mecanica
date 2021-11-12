package views;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.SystemColor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controllers.UsuarioDAO;

import javax.swing.JScrollPane;
import javax.swing.JProgressBar;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import java.awt.Toolkit;

public class frm_main_menu extends Main {

	private JFrame frmProjetoMecanica;

	private JTable tabela_shortage_produtos;
	
	

	public frm_main_menu() throws SQLException {
		initialize();
	}


	private void initialize() throws SQLException {
		frmProjetoMecanica = new JFrame();
		frmProjetoMecanica.setIconImage(Toolkit.getDefaultToolkit().getImage(frm_main_menu.class.getResource("/assets/icon-32.png")));
		frmProjetoMecanica.getContentPane().setBackground((Main.currentUser.getNivel() == 1) ? new Color(25, 25, 112) : new Color(0,0, 112));
		frmProjetoMecanica.setResizable(false);
		frmProjetoMecanica.setTitle("Projeto Mec\u00E2nica");
	
		frmProjetoMecanica.setBounds(100, 100, 1022, 522);
		frmProjetoMecanica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProjetoMecanica.getContentPane().setLayout(null);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(SystemColor.activeCaption);
		mainPanel.setBounds(10, 33, 985, 413);
		frmProjetoMecanica.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		JPanel panel_lastSoldProducts = new JPanel();
		panel_lastSoldProducts.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 64, 64), new Color(64, 64, 64)), "\u00DAltimos produtos Vendidos", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		panel_lastSoldProducts.setBounds(663, 11, 312, 391);
		mainPanel.add(panel_lastSoldProducts);
		
		JPanel panel_LastSoldsByUser = new JPanel();
		panel_LastSoldsByUser.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(64, 64, 64), new Color(64, 64, 64)), "Seus \u00FAltimos pedidos vendidos", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_LastSoldsByUser.setBounds(332, 11, 321, 391);
		mainPanel.add(panel_LastSoldsByUser);
		
		JPanel panel_shortage_products = new JPanel();
		panel_shortage_products.setBackground(Color.WHITE);
		panel_shortage_products.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(64, 64, 64), new Color(64, 64, 64)), "Produtos a esgotar", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_shortage_products.setBounds(10, 11, 312, 391);
		mainPanel.add(panel_shortage_products);
		panel_shortage_products.setLayout(null);
		

		
		JScrollPane scrollPane_ShortageProducts = new JScrollPane();
		scrollPane_ShortageProducts.setBounds(10, 21, 292, 359);
		panel_shortage_products.add(scrollPane_ShortageProducts);
		
		tabela_shortage_produtos = carregaProdutosAEsgotar();
		tabela_shortage_produtos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tabela_shortage_produtos.getTableHeader().setReorderingAllowed(false);
		scrollPane_ShortageProducts.setViewportView(tabela_shortage_produtos);
		

		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1016, 22);
		frmProjetoMecanica.getContentPane().add(menuBar);
		
		JMenu mnOperacoes = new JMenu("Produtos");
		menuBar.add(mnOperacoes);
		
		JMenuItem mntm_verprodutos = new JMenuItem("Ver Produtos");
		mntm_verprodutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				frmProjetoMecanica.dispose();
				new frm_produtos();
			}
		});
		mntm_verprodutos.setHorizontalAlignment(SwingConstants.CENTER);
		mntm_verprodutos.setFont(new Font("Rubik", Font.PLAIN, 12));
		mnOperacoes.add(mntm_verprodutos);
		
		
		if(Main.currentUser.getNivel() == 2) {
			JMenuItem mntm_criarProduto = new JMenuItem("Criar Produto");
			mntm_criarProduto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					frmProjetoMecanica.dispose();
					new frm_criarproduto();
				}
			});
			mntm_criarProduto.setFont(new Font("Rubik", Font.PLAIN, 12));
			mnOperacoes.add(mntm_criarProduto);
		}
		
		
		
		JMenuItem mntm_vercarrinho = new JMenuItem("Ver Carrinho");
		mntm_vercarrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProjetoMecanica.dispose();
				try {
					new frm_checkout();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntm_vercarrinho.setFont(new Font("Rubik", Font.PLAIN, 12));
		mnOperacoes.add(mntm_vercarrinho);
		
		JMenu mnProdutos = new JMenu("Opera\u00E7\u00F5es");
		menuBar.add(mnProdutos);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("New menu item");
		mnProdutos.add(mntmNewMenuItem_3);
		
		if(Main.currentUser.getNivel() == 2) {
			JMenu mnRelatorios = new JMenu("Relatórios");
			menuBar.add(mnRelatorios);
			
			JMenuItem mntm_gerarRelatorio = new JMenuItem("Gerar Relat\u00F3rio");
			mntm_gerarRelatorio.setHorizontalAlignment(SwingConstants.CENTER);
			mnRelatorios.add(mntm_gerarRelatorio);
			
		}
		
		JMenu mnOpcoes = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnOpcoes);
		
		JMenuItem mntmSairItem = new JMenuItem("Sair");
		mntmSairItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int yes = JOptionPane.showConfirmDialog(frmProjetoMecanica, "Tens certeza que quer sair do aplicativo?", "Confirmar Saída", JOptionPane.YES_NO_OPTION);
				if(yes == JOptionPane.YES_OPTION) 
					System.exit(0);
				
			}
		});
		mnOpcoes.add(mntmSairItem);
		
		
		JPanel panel_footer = new JPanel();
		panel_footer.setBackground(SystemColor.controlShadow);
		panel_footer.setBounds(0, 457, 1016, 35);
		frmProjetoMecanica.getContentPane().add(panel_footer);
		panel_footer.setLayout(null);
		
		JLabel lbl_footer_welcome_msg = new JLabel("Bem-vindo %s");
		lbl_footer_welcome_msg.setText(String.format("Bem-vindo %s",Main.currentUser.getNome()));
		lbl_footer_welcome_msg.setBounds(10, -1, 513, 30);
		lbl_footer_welcome_msg.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_footer_welcome_msg.setFont(new Font("Arial", Font.PLAIN, 14));
		panel_footer.add(lbl_footer_welcome_msg);
		
		JLabel lbl_footer_datetime = new JLabel("dd-MM-YYYY hh:mm:ss");
		lbl_footer_datetime.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_footer_datetime.setFont(new Font("Arial", Font.PLAIN, 14));
		lbl_footer_datetime.setBounds(798, 5, 197, 19);
		panel_footer.add(lbl_footer_datetime);
		frmProjetoMecanica.setVisible(true);
		
	
		frmProjetoMecanica.addWindowListener(new WindowAdapter() {
			
			public void windowOpened(WindowEvent e) {
				lbl_footer_datetime.setText(getDatetime());
				javax.swing.Timer timer = new javax.swing.Timer(1000, new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						lbl_footer_datetime.setText(getDatetime());
					}
				});
				timer.start();
			}
		});
		
	}
	private JTable carregaProdutosAEsgotar() throws SQLException {
		return new UsuarioDAO().GetProdutosAEsgotar();
	};
	
	private String getDatetime() {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
	}
}
