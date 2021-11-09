package views;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controllers.ProdutoDAO;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class frm_produtos {

	private JFrame frmProdutos;
	private JTextField txtField_ProcuraNome;
	private JTable table_result;
	
	private JComboBox comboBox_Marcas;
	private JComboBox comboBox_Tipos;

	public frm_produtos() {
		
		ProdutoDAO produtodao = new ProdutoDAO();
		
		try {
		
			ArrayList<String> marcas = produtodao.GetMarcasExistentes();
			ArrayList<String> tipos = produtodao.GetTipoExistentes();
			comboBox_Marcas = new JComboBox(marcas.toArray());
			comboBox_Tipos = new JComboBox(tipos.toArray());
	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		initialize();
		
	}

	private void initialize() {
		frmProdutos = new JFrame();
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
		frmProdutos.setTitle("Projeto Mec\u00E2nica - Ver Produtos");
		frmProdutos.setBounds(100, 100, 900, 600);
		frmProdutos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProdutos.getContentPane().setLayout(null);
		
		JPanel panel_filters = new JPanel();
		panel_filters.setBounds(10, 11, 864, 116);
		frmProdutos.getContentPane().add(panel_filters);
		panel_filters.setLayout(null);
		
		txtField_ProcuraNome = new JTextField();
		txtField_ProcuraNome.setBounds(149, 11, 219, 20);
		panel_filters.add(txtField_ProcuraNome);
		txtField_ProcuraNome.setColumns(10);
		
		JLabel lbl_pesquisapornome = new JLabel("Pesquisar por nome: ");
		lbl_pesquisapornome.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_pesquisapornome.setBounds(6, 14, 133, 14);
		panel_filters.add(lbl_pesquisapornome);
		
		JLabel lbl_pesquisapormarca = new JLabel("Pesquisar por marca: ");
		lbl_pesquisapormarca.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_pesquisapormarca.setBounds(6, 39, 133, 27);
		panel_filters.add(lbl_pesquisapormarca);
		
		JLabel lbl_pesquisaportipo = new JLabel("Pesquisar por tipo: ");
		lbl_pesquisaportipo.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_pesquisaportipo.setBounds(6, 77, 133, 14);
		panel_filters.add(lbl_pesquisaportipo);
		
		JLabel lbl_pesquisaporprecomin = new JLabel("Pre\u00E7o M\u00EDnimo: ");
		lbl_pesquisaporprecomin.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_pesquisaporprecomin.setBounds(405, 14, 113, 14);
		panel_filters.add(lbl_pesquisaporprecomin);
		
		JLabel lbl_pesquisaporprecomax = new JLabel("Pre\u00E7o M\u00E1ximo: ");
		lbl_pesquisaporprecomax.setHorizontalAlignment(SwingConstants.TRAILING);
		lbl_pesquisaporprecomax.setBounds(405, 45, 113, 14);
		panel_filters.add(lbl_pesquisaporprecomax);
		
		JCheckBox chckbx_considera_disponiveis = new JCheckBox("Considerar Indispon\u00EDveis?");
		chckbx_considera_disponiveis.setVerticalAlignment(SwingConstants.BOTTOM);
		chckbx_considera_disponiveis.setHorizontalAlignment(SwingConstants.TRAILING);
		chckbx_considera_disponiveis.setBounds(563, 82, 196, 23);
		panel_filters.add(chckbx_considera_disponiveis);
		
		JButton btn_applyFilter = new JButton("Procurar");
		btn_applyFilter.setBounds(765, 64, 89, 41);
		panel_filters.add(btn_applyFilter);
		
		
		comboBox_Marcas.setBounds(149, 41, 219, 22);
		panel_filters.add(comboBox_Marcas);
		
	
		comboBox_Tipos.setBounds(149, 73, 219, 22);
		panel_filters.add(comboBox_Tipos);
		
		JFormattedTextField txtField_PrecoMin = new JFormattedTextField();
		txtField_PrecoMin.setBounds(522, 11, 89, 20);
		panel_filters.add(txtField_PrecoMin);
		
		JFormattedTextField txtField_PrecoMax = new JFormattedTextField();
		txtField_PrecoMax.setBounds(522, 42, 89, 20);
		panel_filters.add(txtField_PrecoMax);
		
		JPanel panel_results = new JPanel();
		panel_results.setBounds(10, 138, 864, 412);
		frmProdutos.getContentPane().add(panel_results);
		panel_results.setLayout(null);
		
		JScrollPane scrollPane_Result = new JScrollPane();
		scrollPane_Result.setBounds(0, 0, 1, 1);
		panel_results.add(scrollPane_Result);
		
		table_result = new JTable();
		scrollPane_Result.setViewportView(table_result);
		frmProdutos.setVisible(true);
	}
}
