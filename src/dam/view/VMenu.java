package dam.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import michelin.control.MichelinControl;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class VMenu extends JFrame {
	public static final String MN_CONSULTA = "Consulta Restaurantes";
	public static final String MN_REGISTRO = "Registro Restaurantes";
	public static final String MN_MODIFICACION = "Modificacion Restaurantes";
	public static final String MN_SALIR = "Salir";
	
	static final int ALTO = 700;
	static final int ANCHO = 860;
	private JMenuItem mntmConsulta;
	private JMenuItem mntmRegistro;
	private JMenuItem mntmModificacion;
	private JMenuItem mntmSalir;
	private JScrollPane scrpContenedor;
	
	public VMenu() {
		init();
	}

	private void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Menu-Principal");
		
		setSize(ANCHO,ALTO);
		centrarVentana();
		
		crearMenu();
		
		
	}

	private void crearMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMantenimiento = new JMenu("Mantenimiento Restaurante");
		menuBar.add(mnMantenimiento);
		
		mntmConsulta = new JMenuItem(MN_CONSULTA);
		mnMantenimiento.add(mntmConsulta);
		
		mntmRegistro = new JMenuItem(MN_REGISTRO);
		mnMantenimiento.add(mntmRegistro);
		
		mntmModificacion = new JMenuItem(MN_MODIFICACION);
		mnMantenimiento.add(mntmModificacion);
		
		mntmSalir = new JMenuItem(MN_SALIR);
		menuBar.add(mntmSalir);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		scrpContenedor = new JScrollPane();
		getContentPane().add(scrpContenedor, BorderLayout.CENTER);
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

	public void setControlador(MichelinControl control) {
		mntmConsulta.addActionListener(control);
		mntmModificacion.addActionListener(control);
		mntmRegistro.addActionListener(control);
		mntmSalir.addActionListener(control);
		
	}
	
	public void cargarPanel(JPanel panel) {
		scrpContenedor.setViewportView(panel);
	}
}
