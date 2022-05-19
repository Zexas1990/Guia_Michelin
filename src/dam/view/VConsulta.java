package dam.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VConsulta extends JPanel {
	
	static final int ALTO = VMenu.ALTO - 10;
	static final int ANCHO = VMenu.ANCHO -10;
	public static final String BTN_CONSULTAR = "Consultar";
	public static final String [] DISTINCION  = {"TODAS", "1 estrella", "2 estrellas", "3 estrellas", "4 estrellas", "5 estrellas"};
	public static final String [] REGION = {"TODAS", "Andalucia", "Aragon", "Asturias", "Islas Baleares", "Cantabria", "Islas Canarias",
											"Castilla - La Mancha", "Castilla y Leon", "Cataluña", "Galicia", "Extremadura", "Madrid", "Murcia",
											"Navarra", "Pais Vasco", "La Rioja", "Comunidad Valenciana"};
	
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JButton btnConsultar;
	private JTable tableRestaurantes;
	
	public VConsulta() {
		init();
	}

	private void init() {
		setLayout(null);
		setSize(ANCHO,ALTO);
		
		JLabel lblConsultaRestaurantes = new JLabel("Consulta de Restaurantes");
		lblConsultaRestaurantes.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblConsultaRestaurantes.setBounds(42, 61, 276, 19);
		add(lblConsultaRestaurantes);
		
		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFiltro.setBounds(76, 108, 56, 13);
		add(lblFiltro);
		
		JLabel lblRegion = new JLabel("Regiónn:");
		lblRegion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRegion.setBounds(119, 131, 61, 13);
		add(lblRegion);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(REGION));
		comboBox.setBounds(201, 129, 197, 21);
		add(comboBox);
		
		JLabel lblDistincion = new JLabel("Distinción:");
		lblDistincion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDistincion.setBounds(466, 133, 91, 13);
		add(lblDistincion);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(DISTINCION));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_1.setBounds(575, 129, 137, 21);
		add(comboBox_1);
		
		btnConsultar = new JButton(BTN_CONSULTAR);
		btnConsultar.setForeground(Color.BLACK);
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConsultar.setBounds(622, 182, 146, 32);
		add(btnConsultar);
		
		JLabel lblListado = new JLabel("Listado de restaurantes:");
		lblListado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblListado.setBounds(76, 194, 197, 13);
		add(lblListado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 243, 774, 370);
		add(scrollPane);
		
		tableRestaurantes = new JTable();
		scrollPane.setViewportView(tableRestaurantes);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.BLACK);
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLimpiar.setBounds(622, 634, 146, 32);
		add(btnLimpiar);
		centrarVentana();
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	
	private void centrarVentana() {
		setPreferredSize(new Dimension(ANCHO, ALTO));      
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();                   
		Dimension ventana = this.getPreferredSize();                      
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
			
	}
}
