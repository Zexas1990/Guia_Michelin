package michelin.main;

import java.awt.EventQueue;

import dam.view.VConsulta;
import dam.view.VMenu;
import dam.view.VRegistro;
import michelin.control.MichelinControl;

public class InicioMichelin {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VMenu vMenu = new VMenu();
				VConsulta vCon = new VConsulta();
				VRegistro vReg = new VRegistro();
				
				MichelinControl control = new MichelinControl(vMenu, vReg, vCon);
				
				vMenu.setControlador(control);
				vCon.setControlador(control);
				vReg.setControlador(control);
				
				vMenu.hacerVisible();
				
			}
		});

	}

}
