package dam.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import michelin.control.MichelinControl;
import michelin.model.Restaurante;

public class VRegistro extends JPanel {
	
	static final int ALTO = VMenu.ALTO - 150;
	static final int ANCHO = VMenu.ANCHO -10;
	public static final String BTN_GUARDAR = "Guardar datos";
	public static final String BTN_LIMPIAR = "Limpiar datos";
	
	
	private JTextField txtNombre;
	private JTextField txtCiudad;
	private JTextField txtDireccion;
	private JTextField txtPrecioMinimo;
	private JTextField txtPrecioMaximo;
	private JTextField txtTelefono;
	private JTextField txtWeb;
	private JComboBox cmbxCocina;
	private JComboBox cmbxRegion;
	private JSpinner spnDistincion;
	private JButton btnGuardar;
	private JButton btnLimpiarDatos;
	
	public VRegistro() {
		init();
	}

	private void init() {
		setLayout(null);
		setSize(ANCHO,ALTO);
		centrarVentana();
		
		JLabel lblConsultaRestaurantes = new JLabel("Registrar Restaurante");
		lblConsultaRestaurantes.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblConsultaRestaurantes.setBounds(42, 61, 276, 19);
		add(lblConsultaRestaurantes);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombre.setBounds(76, 150, 76, 13);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(162, 149, 296, 19);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblCocina = new JLabel("Cocina:");
		lblCocina.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCocina.setBounds(524, 152, 76, 13);
		add(lblCocina);
		
		cmbxCocina = new JComboBox();
		cmbxCocina.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbxCocina.setModel(new DefaultComboBoxModel(new String[] {"Creativa", "Moderna", "Tradicional", "Regional", "Fusi\u00F3n"}));
		cmbxCocina.setBounds(626, 148, 114, 20);
		add(cmbxCocina);
		
		JLabel lblRegion = new JLabel("Regi\u00F3n:");
		lblRegion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRegion.setBounds(76, 193, 76, 13);
		add(lblRegion);
		
		cmbxRegion = new JComboBox();
		cmbxRegion.setModel(new DefaultComboBoxModel(new String[] {"Andaluc\u00EDa", "Arag\u00F3n", "Asturias", "Islas Baleares", "Cantabria", "Islas Canarias", "Castilla - La Mancha", "Castilla y Le\u00F3n", "Catalu\u00F1a", "Galicia", "Extremadura", "Madrid", "Murcia", "Navarra", "Pa\u00EDs Vasco", "La Rioja", "Comunidad Valenciana"}));
		cmbxRegion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cmbxRegion.setBounds(162, 191, 249, 20);
		add(cmbxRegion);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCiudad.setBounds(462, 195, 76, 13);
		add(lblCiudad);
		
		txtCiudad = new JTextField();
		txtCiudad.setBounds(548, 192, 192, 19);
		add(txtCiudad);
		txtCiudad.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDireccin.setBounds(76, 252, 76, 13);
		add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(162, 251, 530, 19);
		add(txtDireccion);
		
		JLabel lblDistincin = new JLabel("Distinci\u00F3n:");
		lblDistincin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDistincin.setBounds(76, 303, 87, 13);
		add(lblDistincin);
		
		spnDistincion = new JSpinner();
		spnDistincion.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		spnDistincion.setToolTipText("");
		spnDistincion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spnDistincion.setBounds(162, 302, 44, 19);
		add(spnDistincion);
		
		JLabel lblPrecioMnimo = new JLabel("Precio m\u00EDnimo:");
		lblPrecioMnimo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecioMnimo.setBounds(310, 300, 114, 13);
		add(lblPrecioMnimo);
		
		txtPrecioMinimo = new JTextField();
		txtPrecioMinimo.setColumns(10);
		txtPrecioMinimo.setBounds(434, 297, 76, 19);
		add(txtPrecioMinimo);
		
		JLabel lblPrecioMaximo = new JLabel("Precio maximo:");
		lblPrecioMaximo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrecioMaximo.setBounds(540, 300, 114, 13);
		add(lblPrecioMaximo);
		
		txtPrecioMaximo = new JTextField();
		txtPrecioMaximo.setColumns(10);
		txtPrecioMaximo.setBounds(664, 297, 76, 19);
		add(txtPrecioMaximo);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelefono.setBounds(76, 360, 76, 13);
		add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setText("+34");
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(162, 359, 114, 19);
		add(txtTelefono);
		
		JLabel lblWeb = new JLabel("Web:");
		lblWeb.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWeb.setBounds(365, 362, 57, 13);
		add(lblWeb);
		
		txtWeb = new JTextField();
		txtWeb.setColumns(10);
		txtWeb.setBounds(417, 359, 296, 19);
		add(txtWeb);
		
		btnGuardar = new JButton(BTN_GUARDAR);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGuardar.setBounds(177, 444, 141, 30);
		add(btnGuardar);
		
		btnLimpiarDatos = new JButton(BTN_LIMPIAR);
		btnLimpiarDatos.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLimpiarDatos.setBounds(437, 444, 141, 30);
		add(btnLimpiarDatos);
		
		
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
	
	public Restaurante obtenerRestaurante() {
		
		Restaurante restaurante = null;
		
		String nomRest = txtNombre.getText();
		
		if(nomRest.isEmpty()) {
			mostrarError("Debe introducir el nombre del restaurante");
		}else {
			String ciudad = txtCiudad.getText();
			
			if(ciudad.isEmpty()) {
				mostrarError("Debe introducir el nombre de la ciudad");
				
			}else {
				String dire = txtDireccion.getText();
				
				if(dire.isEmpty()) {
					mostrarError("Debe introducir la dirección");
					
				}else {
					//Revisamos que los valores de los campos precio Maximo y Minimo son numericos
					double valMax = 0;
					double valMin = 0;
					String svalMax = txtPrecioMaximo.getText();
					
					if(svalMax.isEmpty()) {
						mostrarError("El Valor Maximo no puede quedar en blanco");
					}else {
						
						boolean numerico = true;
						
						for (int i = 0; i < svalMax.length(); i++) {
							if(!Character.isDigit(svalMax.charAt(i))) {
								numerico = false;
							}
						}
						if(numerico == false) {
							mostrarError("El precio maximo debe ser un valor numerico");
						}else {
							valMax = Double.parseDouble(svalMax);
						}	
						String svalMin = txtPrecioMaximo.getText();
						
						if(svalMin.isEmpty()) {
							mostrarError("El precio minimo no puede quedar en blanco");
						}else {
							
							boolean numerico2 = true;
							
							for (int i = 0; i < svalMin.length(); i++) {
								if(!Character.isDigit(svalMin.charAt(i))) {
									numerico2 = false;
								}
							}
							if(numerico2 == false) {
								mostrarError("El precio minimo debe ser un valor numerico");
							}else {
								valMin = Double.parseDouble(svalMin);
							}
						}
						
						if(valMin > valMax) {
							mostrarError("El precio minimo dno puede ser superior al precio maximo");
						}else {
							
							String cocina = (String)cmbxCocina.getSelectedItem();
							String region = (String)cmbxRegion.getSelectedItem();
							int distincion = (int) spnDistincion.getValue();
							//telefono y web
							String telfono = txtTelefono.getText();
							String web = txtWeb.getText();
							
							restaurante = new Restaurante(nomRest, region, ciudad, distincion, dire, valMin, valMax, cocina, telfono, web);
							
						}
							
					}
				}
		
				
			}
		
		}	
		
		
		return restaurante;
		
	}
		
	
	
	

	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(this, error,
				"Error de datos", JOptionPane.ERROR_MESSAGE);
		
	}
	
	
	public void mostrarInformacion(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje,
				"Informacion de operación", JOptionPane.INFORMATION_MESSAGE);
		
	}

	public void limpiarDatos() {
		txtNombre.setText("");
		txtCiudad.setText("");
		txtDireccion.setText("");
		txtPrecioMinimo.setText("");
		txtPrecioMaximo.setText("");
		txtTelefono.setText("");
		txtWeb.setText("");
	
	}

	public void setControlador(MichelinControl control) {
		btnGuardar.addActionListener(control);
		btnLimpiarDatos.addActionListener(control);
		
	}

	
}
