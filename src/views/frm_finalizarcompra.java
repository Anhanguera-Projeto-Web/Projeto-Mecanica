package views;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

public class frm_finalizarcompra {

	private JFrame frmProdutos;
	private JTextField txtField_ProcuraNome;
	private JTable table_result ;
	
	private JComboBox comboBox_Marcas;
	private JComboBox comboBox_Tipos;
	
	private JScrollPane scrollPane_Result;
	
	private JFormattedTextField txtField_PrecoMin;
	private JFormattedTextField txtField_PrecoMax;
	
	private JCheckBox chckbx_considera_apenas_disponiveis;
	
	private JPanel panel_results;

	public frm_finalizarcompra() {
		
		ProdutoDAO produtodao = new ProdutoDAO();
		
		try {
			table_result = produtodao.GetProdutos("");
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
		frmProdutos.setTitle("Projeto Mec\u00E2nica - Fechar Compra");
		frmProdutos.setBounds(100, 100, 900, 600);
		frmProdutos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProdutos.getContentPane().setLayout(null);
		
		JPanel panel_filters = new JPanel();
		panel_filters.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.ORANGE, Color.PINK));
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
		
		chckbx_considera_apenas_disponiveis = new JCheckBox("Considerar apenas Dispon\u00EDveis");
		chckbx_considera_apenas_disponiveis.setVerticalAlignment(SwingConstants.BOTTOM);
		chckbx_considera_apenas_disponiveis.setHorizontalAlignment(SwingConstants.TRAILING);
		chckbx_considera_apenas_disponiveis.setBounds(522, 82, 237, 23);
		panel_filters.add(chckbx_considera_apenas_disponiveis);
		
		JButton btn_applyFilter = new JButton("Procurar");
		btn_applyFilter.setBounds(765, 64, 89, 41);
		panel_filters.add(btn_applyFilter);
		
		
		comboBox_Marcas.setBounds(149, 41, 219, 22);
		panel_filters.add(comboBox_Marcas);
		
	
		comboBox_Tipos.setBounds(149, 73, 219, 22);
		panel_filters.add(comboBox_Tipos);
		
	    DecimalFormat amountFormat = new DecimalFormat("#.00");
	    txtField_PrecoMin = new JFormattedTextField(amountFormat);
		
		txtField_PrecoMin.setBounds(522, 11, 89, 20);
		panel_filters.add(txtField_PrecoMin);
		
		txtField_PrecoMax = new JFormattedTextField(amountFormat);
		txtField_PrecoMax.setBounds(522, 42, 89, 20);
		panel_filters.add(txtField_PrecoMax);
		
		JButton btn_adiciona_carrinho = new JButton("Adicionar ao Carrinho");
		btn_adiciona_carrinho.setFont(new Font("Arial", Font.PLAIN, 14));
		btn_adiciona_carrinho.setBounds(674, 11, 180, 41);
		panel_filters.add(btn_adiciona_carrinho);
		
		panel_results = new JPanel();
		panel_results.setBorder(new EtchedBorder(EtchedBorder.RAISED, Color.ORANGE, Color.PINK));
		panel_results.setBounds(10, 138, 864, 412);
		frmProdutos.getContentPane().add(panel_results);
		panel_results.setLayout(null);
		
		
		scrollPane_Result = new JScrollPane();
		scrollPane_Result.setBounds(0, 0, panel_results.getSize().width, panel_results.getSize().height);
		panel_results.add(scrollPane_Result);
		
		table_result.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table_result.getTableHeader().setReorderingAllowed(false);
		
	
		scrollPane_Result.setViewportView(table_result);
		frmProdutos.setVisible(true);
		
		btn_applyFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshResultadoFinal();
			}
		});
		
	}
	
	private void refreshResultadoFinal() {
		boolean temPesquisa = (this.txtField_ProcuraNome.getText().equals("")) ? false : true;
		boolean temMarca = (this.comboBox_Marcas.getSelectedItem().toString().equals("Todos")) ? false : true;
		boolean temTipo = (this.comboBox_Tipos.getSelectedItem().toString().equals("Todos")) ? false : true;
		boolean considerarApenasDisponivel = (this.chckbx_considera_apenas_disponiveis.isSelected()) ? true : false;
		boolean temprecoMin = (this.txtField_PrecoMin.getText().equals(""))? false : true;
		boolean temprecoMax = (this.txtField_PrecoMax.getText().equals(""))? false : true;
		
		StringBuffer parameters = new StringBuffer();
		parameters.append(" WHERE (1=1) \n");
		
		if(temPesquisa)
			parameters.append(" AND `descricao` LIKE '%"+this.txtField_ProcuraNome.getText()+"%'\n");
		
		if(temMarca)
			parameters.append(" AND `marca` = '"+this.comboBox_Marcas.getSelectedItem().toString()+"'\n");
		
		if(temTipo)
			parameters.append(" AND `tipo_produto` = '"+this.comboBox_Tipos.getSelectedItem().toString()+"'\n");
		
		if(considerarApenasDisponivel)
			parameters.append(" AND `disponivel` = 1\n");
		
		if(temprecoMin)
			parameters.append(" AND `valor` >= "+this.txtField_PrecoMin.getText()+"\n");
		
		if(temprecoMax)
			parameters.append(" AND `valor` <= "+this.txtField_PrecoMax.getText()+"\n");
		
		try {
						
			table_result = new ProdutoDAO().GetProdutos(parameters.toString());
			table_result.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			table_result.getTableHeader().setReorderingAllowed(false);
			scrollPane_Result.setViewportView(table_result);
			
			table_result.revalidate();
			table_result.repaint();
			
			//panel_results.repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
