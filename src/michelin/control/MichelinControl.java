package michelin.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import dam.view.VConsulta;
import dam.view.VMenu;
import dam.view.VRegistro;
import michelin.model.Restaurante;
import michelin.persistencia.RestaurantesPersistencia;

public class MichelinControl implements ActionListener {
	
	private static ArrayList<Restaurante> listaRest;
	VMenu vMenu;
	VRegistro vReg;
	VConsulta vCon;
	RestaurantesPersistencia rp;
	
	
	
	public MichelinControl(VMenu vMenu, VRegistro vReg, VConsulta vCon) {
		this.vMenu = vMenu;
		this.vReg = vReg;
		this.vCon = vCon;
		this.rp = new RestaurantesPersistencia();
		listaRest = new ArrayList<Restaurante>();
		
	}



	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource()instanceof JMenuItem) {
			if(ev.getActionCommand().equals(VMenu.MN_SALIR)) {
				
				int option = JOptionPane.showConfirmDialog(vMenu, "¿Estas seguro que deseas salir?", "Confirmar Salida", 
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				
			}else if(ev.getActionCommand().equals(VMenu.MN_CONSULTA)) {
				
				vCon.fillCmbRegion(rp.requestRegiones());
				vMenu.cargarPanel(vCon);
				vCon.hacerVisible(false);
				
			}else if(ev.getActionCommand().equals(VMenu.MN_MODIFICACION)) {
				
				
			}else if(ev.getActionCommand().equals(VMenu.MN_REGISTRO)) {
				vMenu.cargarPanel(vReg);
				
				
			}
			
		}if(ev.getActionCommand().equals(vReg.BTN_GUARDAR)) {
			realizarResgistro();
		}else if(ev.getActionCommand().equals(vReg.BTN_LIMPIAR)) {
			vReg.limpiarDatos();
		}
		
		
		if(ev.getActionCommand().equals(vCon.BTN_CONSULTAR)) {
			System.out.println("CLICK 01");
			
			if (vCon.getCmbRegion().getSelectedIndex() == 0 && vCon.getCmbDistincion().getSelectedIndex() == 0) {
				
				listaRest = rp.verRest();
				
			}else if(vCon.getCmbRegion().getSelectedIndex() == 1) {
				
				listaRest = rp.filterTable(vCon.getCmbDistincion().getSelectedIndex(), (String) vCon.getCmbRegion().getSelectedItem());
				
			}else if(vCon.getCmbDistincion().getSelectedIndex() == 1) {
				
				listaRest = rp.filterTable(vCon.getCmbDistincion().getSelectedIndex(), (String) vCon.getCmbRegion().getSelectedItem());
				
			}else {
				
				listaRest = rp.filterTable(vCon.getCmbDistincion().getSelectedIndex(), (String) vCon.getCmbRegion().getSelectedItem());
				
			}
			
			if(listaRest.isEmpty()) {
				vCon.mostrarError("No se han encontrado datos para el filtro introducido");
				vCon.hacerVisible(false);
				
			}else {
				vCon.filtrarTabla(listaRest);
				vCon.hacerVisible(true);
			}
			
		}

	}



	private void realizarResgistro() {
		Restaurante restaurante = vReg.obtenerRestaurante();
		
		
		if(restaurante != null) {
			//Comprobar que el restaurante existe en la BBDD
			String id = rp.consultarIdPornombre(restaurante.getNombre());
			if(id != null) {
				vReg.mostrarError("El restaurante ya existe en la BBDD");
			}else {
				int res = rp.registrarRestaurante(restaurante);
						
				if(res ==1) {
					vReg.mostrarInformacion("El registro se ha rehalizado correctamente");
					vReg.limpiarDatos();
				}else {
					vReg.mostrarError("No se ha podido realizar el registro");
				}
			}
		}
		
		
		
	}



}
