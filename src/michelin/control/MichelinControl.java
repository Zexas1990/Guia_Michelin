package michelin.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import dam.view.VConsulta;
import dam.view.VMenu;
import dam.view.VRegistro;
import michelin.model.Restaurante;
import michelin.persistencia.RestaurantesPersistencia;

public class MichelinControl implements ActionListener {
	
	private VMenu vMenu;
	private VRegistro vReg;
	private VConsulta vCon;
	private RestaurantesPersistencia rp;
	
	
	
	public MichelinControl(VMenu vMenu, VRegistro vReg, VConsulta vCon) {
		this.vMenu = vMenu;
		this.vReg = vReg;
		this.vCon = vCon;
		this.rp = new RestaurantesPersistencia();
	}



	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource()instanceof JMenuItem) {
			if(ev.getActionCommand().equals(VMenu.MN_SALIR)) {
				System.exit(0);
				
			}else if(ev.getActionCommand().equals(VMenu.MN_CONSULTA)) {
				vMenu.cargarPanel(vCon);
				
			}else if(ev.getActionCommand().equals(VMenu.MN_MODIFICACION)) {
				
				
			}else if(ev.getActionCommand().equals(VMenu.MN_REGISTRO)) {
				vMenu.cargarPanel(vReg);
				
				
			}
			
		}if(ev.getActionCommand().equals(vReg.BTN_GUARDAR)) {
			realizarResgistro();
		}else if(ev.getActionCommand().equals(vReg.BTN_LIMPIAR)) {
			vReg.limpiarDatos();
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
