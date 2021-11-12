package views;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import controllers.ProdutoDAO;
import models.Produto;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.DefaultComboBoxModel;

public class frm_checkout extends Main {

	private JFrame frmCheckout;
	private JTable table_result ;
	
	private JScrollPane scrollPane_Result;
	
	private JPanel panel_results;
	private JPanel panel_info_checkout;
	private JButton btn_limpa_carrinho;
	private JButton btn_adiciona_carrinho;
	private JComboBox comboBox_metodo_pagamento;
	private JLabel lbl_precofinal_valor;
	private JFormattedTextField txtField_Cpf_cliente;

	
	public frm_checkout() throws ParseException {
		
		ProdutoDAO produtodao = new ProdutoDAO();
		
		try {
			table_result = getProdutosDoCarrinho();
			
			ArrayList<String> metodo_pag = produtodao.GetMetodosPagamentos();
			comboBox_metodo_pagamento = new JComboBox(metodo_pag.toArray());


			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		initialize();
		
	}

	private void initialize() throws ParseException {
		frmCheckout = new JFrame();
		frmCheckout.setResizable(false);
		frmCheckout.getContentPane().setBackground(new Color(139, 69, 19));
		frmCheckout.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					new views.frm_main_menu();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		frmCheckout.setIconImage(Toolkit.getDefaultToolkit().getImage(frm_checkout.class.getResource("/assets/icon-32.png")));
		frmCheckout.setTitle("Projeto Mec\u00E2nica - Checkout");
		frmCheckout.setBounds(100, 100, 463, 600);
		frmCheckout.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCheckout.getContentPane().setLayout(null);
		
		panel_info_checkout = new JPanel();
		panel_info_checkout.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.ORANGE, Color.PINK));
		panel_info_checkout.setBounds(10, 11, 431, 116);
		frmCheckout.getContentPane().add(panel_info_checkout);
		panel_info_checkout.setLayout(null);
		
		JLabel lbl_preco_final = new JLabel("Pre\u00E7o Final: ");
		lbl_preco_final.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_preco_final.setBounds(6, 23, 133, 14);
		panel_info_checkout.add(lbl_preco_final);
		
		JLabel lbl_cpf = new JLabel("CPF (Opcional): ");
		lbl_cpf.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_cpf.setBounds(6, 48, 133, 27);
		panel_info_checkout.add(lbl_cpf);
		
		JLabel lbl_metodo_pagamento = new JLabel("M\u00E9todo de Pagamento: ");
		lbl_metodo_pagamento.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_metodo_pagamento.setBounds(6, 86, 133, 14);
		panel_info_checkout.add(lbl_metodo_pagamento);
		
	    //DecimalFormat amountFormat = new DecimalFormat("#.00");
		
		btn_adiciona_carrinho = new JButton("Finalizar");
		btn_adiciona_carrinho.setFont(new Font("Arial", Font.PLAIN, 14));
		btn_adiciona_carrinho.setBounds(329, 63, 92, 41);
		panel_info_checkout.add(btn_adiciona_carrinho);
		if( Main.currentUser.getCarrinho().getLista_de_produtos().size() == 0) {
			btn_adiciona_carrinho.setEnabled(false);
			btn_adiciona_carrinho.setText("Vazio");
		}
		
		btn_limpa_carrinho = new JButton("Limpar");
		btn_limpa_carrinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Main.currentUser.getCarrinho().limparCarrinho();
				JOptionPane.showMessageDialog(frmCheckout, "Carrinho limpo.", "Projeto Mecânica", JOptionPane.INFORMATION_MESSAGE);
				
				table_result = getProdutosDoCarrinho(); // Irá retornar uma JTable vazia, sem items.
				table_result.revalidate();
				table_result.repaint();
				
			}
		});
		btn_limpa_carrinho.setFont(new Font("Arial", Font.PLAIN, 14));
		btn_limpa_carrinho.setBounds(329, 10, 92, 41);
		panel_info_checkout.add(btn_limpa_carrinho);
		
		
		comboBox_metodo_pagamento.setBounds(149, 82, 170, 22);
		panel_info_checkout.add(comboBox_metodo_pagamento);
		
		lbl_precofinal_valor = new JLabel("");
		lbl_precofinal_valor.setText("R$ "+ getValorTotalCarrinho());
		lbl_precofinal_valor.setBounds(149, 23, 170, 14);
		panel_info_checkout.add(lbl_precofinal_valor);
		
		txtField_Cpf_cliente = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtField_Cpf_cliente.setBounds(149, 51, 170, 20);
		panel_info_checkout.add(txtField_Cpf_cliente);
		
		panel_results = new JPanel();
		panel_results.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.ORANGE, Color.PINK));
		panel_results.setBounds(10, 138, 431, 412);
		frmCheckout.getContentPane().add(panel_results);
		panel_results.setLayout(null);
		
		
		scrollPane_Result = new JScrollPane();
		scrollPane_Result.setBounds(0, 0, 432, 412);
		panel_results.add(scrollPane_Result);
		
		table_result.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table_result.getTableHeader().setReorderingAllowed(false);
		
	
		scrollPane_Result.setViewportView(table_result);
		frmCheckout.setVisible(true);
	
		
	}
	
	private JTable getProdutosDoCarrinho() {
		
		String[] columns = {"Produto", "Preço", "Marca", "Tipo"};
		DefaultTableModel modeloTabela = new DefaultTableModel(null,columns);

		for(Produto prod : Main.currentUser.getCarrinho().getLista_de_produtos()) {
			modeloTabela.addRow(new String[] {
					prod.getDescricao(),
					String.valueOf(prod.getPreco()),
					prod.getMarca(),
					prod.getTipo_produto()
			});
		}
		
		JTable tabelafinal = new JTable();
		tabelafinal.setModel(modeloTabela);
		return tabelafinal;
	}
	
	private String getValorTotalCarrinho() {
		double valor = 0.0;
		for(Produto prod : Main.currentUser.getCarrinho().getLista_de_produtos()) {
			valor = valor + prod.getPreco();
	
		}
		return String.valueOf(valor);
	}
}
