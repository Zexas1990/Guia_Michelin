package dam.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import michelin.control.MichelinControl;
import michelin.model.Restaurante;

public class VConsulta extends JPanel {
	
	static final int ALTO = VMenu.ALTO - 10;
	static final int ANCHO = VMenu.ANCHO -10;
	public static final String BTN_CONSULTAR = "Consultar";
	public static final String [] DISTINCION  = {"TODAS", "1 estrella", "2 estrellas", "3 estrellas"};
	public static final String REGIONES = "TODAS";
	
	private DefaultTableModel tModel; //nos sirve para configurar la tabla
	private DefaultComboBoxModel<String> cmbModel;
	private JComboBox cmbRegion;
	private JComboBox cmbDistincion;
	private JButton btnConsultar;
	private JTable tableRestaurantes;
	private static final String NOMBRE = "NOMBRE";
	private static final String CIUDAD = "CIUDAD";
	private static final String DISTINCION_2 = "DISTINCIÓN";
	private static final String COCINA = "COCINA";
	private static final String PRECIO = "PRECIO";
	private JScrollPane scrollPane;
	
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
		
		JLabel lblRegion = new JLabel("Región:");
		lblRegion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRegion.setBounds(116, 131, 64, 13);
		
		add(lblRegion);
		
		cmbRegion = new JComboBox();
		cmbRegion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbModel = new DefaultComboBoxModel<String>();
		cmbRegion.setModel(cmbModel);
		cmbRegion.setBounds(201, 129, 197, 21);
		add(cmbRegion);
		
		JLabel lblDistincion = new JLabel("Distinción:");
		lblDistincion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDistincion.setBounds(466, 133, 91, 13);
		
		add(lblDistincion);
		
		cmbDistincion = new JComboBox();
		cmbDistincion.setModel(new DefaultComboBoxModel(DISTINCION));
		cmbDistincion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbDistincion.setBounds(575, 129, 137, 21);
		add(cmbDistincion);
		
		btnConsultar = new JButton(BTN_CONSULTAR);
		btnConsultar.setForeground(Color.BLACK);
		btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConsultar.setBounds(622, 182, 146, 32);
		add(btnConsultar);
		
		JLabel lblListado = new JLabel("Listado de restaurantes:");
		lblListado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblListado.setBounds(76, 194, 197, 13);
		add(lblListado);
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(42, 243, 774, 370);
		add(scrollPane);
		
		tableRestaurantes = new JTable();
		scrollPane.setViewportView(tableRestaurantes);
		configurarTabla();
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.BLACK);
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLimpiar.setBounds(622, 634, 146, 32);
		add(btnLimpiar);
		centrarVentana();
	}
	
	private void configurarTabla() {
		tModel = new DefaultTableModel() {  //Iniciamos table model
			public boolean isCellEditable(int row, int colum) {
				return false; //porque no queremos celdas editables
			}
		};
		
			tableRestaurantes.setModel(tModel);
		//Establecemos ahora el nombre de las columnas con constantes:
			tModel.addColumn(NOMBRE); //Ser la posicion 0 en la columna
			tModel.addColumn(CIUDAD); //Seria la posicion 1 en la columna
			tModel.addColumn(DISTINCION_2); //Seria la posicion 2 en la columna
			tModel.addColumn(COCINA); //Seria la posicion 3 en la columna
			tModel.addColumn(PRECIO); //Seria la posicion 4 en la columna
			
		//Ponemos el ancho al campo de la columna:
			tableRestaurantes.getColumn(NOMBRE).setPreferredWidth(75);
			tableRestaurantes.getColumn(CIUDAD).setPreferredWidth(75);
			tableRestaurantes.getColumn(DISTINCION_2).setPreferredWidth(75);
			tableRestaurantes.getColumn(COCINA).setPreferredWidth(75);
			tableRestaurantes.getColumn(PRECIO).setPreferredWidth(75);
			
	}
	
	public void filtrarTabla(ArrayList<Restaurante> listaRestaurantes) {
		
		tModel.getDataVector().clear();
		
		Object[] row  = new Object[5];
		
		for (Restaurante res : listaRestaurantes) {
			
			row [0] = res.getNombre();
			row [1] = res.getCiudad();
			if(res.getDistincion() ==1) {
				row [2] = "*";
				
			}else if(res.getDistincion() == 2) {
				row [2] = "**";
				
			}else {
				row [2] = "***";
				
			}
			
			row[3] = res.getCocina();
			row [4] = res.getPrecio_min() + "€" + " - " + res.getPrecio_max() + "€";
			
			tModel.addRow(row);
			
		}
	}

	public void hacerVisible(boolean b) {
		
		scrollPane.setVisible(b);
	}
	
	private void centrarVentana() {
		setPreferredSize(new Dimension(ANCHO, ALTO));      
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();                   
		Dimension ventana = this.getPreferredSize();                      
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
			
	}
	
	public void fillCmbRegion(ArrayList<String> listaRegiones) {
		cmbModel.removeAllElements();
		cmbModel.addElement(REGIONES);
		cmbModel.addAll(listaRegiones);
	}
	
	public JComboBox<String> getCmbRegion() {
		
		return cmbRegion;
	}
	
	public JComboBox<String> getCmbDistincion(){
		
		return cmbDistincion;
	}
	
	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(this, error,
				"Error de datos", JOptionPane.ERROR_MESSAGE);
		
	}
	
	
	public void mostrarInformacion(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje,
				"Informacion de operación", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public void setControlador(MichelinControl control) {
		btnConsultar.addActionListener(control);
	
		
	}
}
