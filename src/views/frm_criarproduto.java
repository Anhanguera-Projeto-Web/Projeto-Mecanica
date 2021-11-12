package views;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class frm_criarproduto {

	private JFrame frmProdutos;
	
	private JTextField txtField_nome_produto;
	
	private JComboBox comboBox_Marcas;
	private JComboBox comboBox_Tipos;
	
	private JFormattedTextField txtField_preco;
	private JFormattedTextField txtField_estoque;

	public frm_criarproduto() {
		
			ProdutoDAO produtodao = new ProdutoDAO();
		
		try {
			ArrayList<String> marcas = produtodao.GetMarcasExistentes();
			ArrayList<String> tipos = produtodao.GetTipoExistentes();
			comboBox_Marcas = new JComboBox(marcas.toArray());
				comboBox_Marcas.removeItemAt((comboBox_Marcas.getItemCount() - 1));
			comboBox_Tipos = new JComboBox(tipos.toArray());
				comboBox_Tipos.removeItemAt((comboBox_Tipos.getItemCount() - 1));
	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		initialize();
		
	}

	private void initialize() {
		frmProdutos = new JFrame();
		frmProdutos.setResizable(false);
		frmProdutos.getContentPane().setBackground(new Color(139, 69, 19));
		frmProdutos.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					new views.frm_main_menu();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		frmProdutos.setIconImage(Toolkit.getDefaultToolkit().getImage(frm_produtos.class.getResource("/assets/icon-32.png")));
		frmProdutos.setTitle("Projeto Mec\u00E2nica - Criar Produto");
		frmProdutos.setBounds(100, 100, 509, 233);
		frmProdutos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProdutos.getContentPane().setLayout(null);
		
		JPanel panel_ficha_criar_produto = new JPanel();
		panel_ficha_criar_produto.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.ORANGE, Color.PINK));
		panel_ficha_criar_produto.setBounds(10, 11, 477, 172);
		frmProdutos.getContentPane().add(panel_ficha_criar_produto);
		panel_ficha_criar_produto.setLayout(null);
		
		txtField_nome_produto = new JTextField();
		txtField_nome_produto.setBounds(149, 11, 212, 20);
		panel_ficha_criar_produto.add(txtField_nome_produto);
		txtField_nome_produto.setColumns(10);
		
		JLabel lbl_nomeproduto = new JLabel("Nome do produto:");
		lbl_nomeproduto.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_nomeproduto.setBounds(6, 14, 133, 14);
		panel_ficha_criar_produto.add(lbl_nomeproduto);
		
		JLabel lbl_marca = new JLabel("Marca do produto: ");
		lbl_marca.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_marca.setBounds(6, 39, 133, 27);
		panel_ficha_criar_produto.add(lbl_marca);
		
		JLabel lbl_tipo = new JLabel("Tipo do produto: ");
		lbl_tipo.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_tipo.setBounds(6, 77, 133, 14);
		panel_ficha_criar_produto.add(lbl_tipo);
		
		
		comboBox_Marcas.setBounds(149, 41, 212, 22);
		panel_ficha_criar_produto.add(comboBox_Marcas);
		
	
		comboBox_Tipos.setBounds(149, 73, 212, 22);
		panel_ficha_criar_produto.add(comboBox_Tipos);
		
	   
		JButton btn_criar_produto = new JButton("Criar");
		btn_criar_produto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Produto prod = new Produto();
				prod.setMarca(comboBox_Marcas.getSelectedItem().toString());
				prod.setTipo_produto(comboBox_Tipos.getSelectedItem().toString());
				prod.setPreco(Double.parseDouble(txtField_preco.getText()));
				prod.setEstoque(Integer.parseInt(txtField_estoque.getText()));
				prod.setDescricao(txtField_nome_produto.getText());
				
				
				if(prod.getDescricao().equals("")) {
					JOptionPane.showMessageDialog(null,"Está faltando o nome do produto.", "Projeto Mecânica", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(prod.getEstoque() < 0) {
					JOptionPane.showMessageDialog(null,"Estoque deve ser maior ou igual a zero.", "Projeto Mecânica", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(prod.getPreco() <= 0) {
					JOptionPane.showMessageDialog(null,"Preço deve ser superior a zero.", "Projeto Mecânica", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				prod.setDisponivel(prod.getEstoque() > 0 ? true : false);
				
				ProdutoDAO dao = new ProdutoDAO();
				boolean res = false;
				try {
					res = dao.CriarProduto(prod);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				if(res)
					JOptionPane.showMessageDialog(null, "Sucesso ao criar produto.", "Projeto Mêcanica", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null,"Houve um erro ao criar produto.", "Projeto Mecânica", JOptionPane.ERROR_MESSAGE);
				
				prod = null;
				return;

			}
		});
		btn_criar_produto.setFont(new Font("Arial", Font.BOLD, 14));
		btn_criar_produto.setBounds(371, 102, 89, 41);
		panel_ficha_criar_produto.add(btn_criar_produto);
		
		JButton btn_resetar = new JButton("Resetar");
		btn_resetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				for(Component component : panel_ficha_criar_produto.getComponents()) {
					
					if(component instanceof JTextField)
						((JTextField) component).setText("");
					
					if(component instanceof JFormattedTextField) 
						((JFormattedTextField) component).setText("");
					
				}
				
			}
		});
		btn_resetar.setFont(new Font("Arial", Font.PLAIN, 14));
		btn_resetar.setBounds(371, 39, 89, 41);
		panel_ficha_criar_produto.add(btn_resetar);
		
		JLabel lbl_preco = new JLabel("Pre\u00E7o: ");
		lbl_preco.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_preco.setBounds(6, 102, 133, 14);
		panel_ficha_criar_produto.add(lbl_preco);
		
		JLabel lbl_estoque = new JLabel("Estoque: ");
		lbl_estoque.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_estoque.setBounds(6, 127, 133, 14);
		panel_ficha_criar_produto.add(lbl_estoque);
		
		DecimalFormat amountFormat = new DecimalFormat("####");
			amountFormat.setMinimumIntegerDigits(0);
			amountFormat.setMaximumIntegerDigits(Integer.MAX_VALUE);
		txtField_estoque = new JFormattedTextField(amountFormat);
		
		txtField_estoque.setBounds(149, 124, 212, 20);
		panel_ficha_criar_produto.add(txtField_estoque);
		
		amountFormat = new DecimalFormat("#.00");
		
		txtField_preco = new JFormattedTextField(amountFormat);
		txtField_preco.setBounds(149, 99, 212, 20);
		panel_ficha_criar_produto.add(txtField_preco);
		
		
		frmProdutos.setVisible(true);
		
	
	}
}

