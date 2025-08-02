package logico;

import java.io.Serializable;
import java.util.ArrayList;

import visual.Principal;

public class Main implements Serializable {

	public static void main(String[] args) {
		
		try {
	        Bolsa.cargarDatosDesdeArchivo("respaldo.dat");
	        System.out.println("Servidor conectado con �xito.");
	    } catch (Exception e) {
	        System.out.println("No se pudo conectar al servidor. Se iniciar� vac�o.");
	    }

	    Principal principal = new Principal();
	    principal.setVisible(true);
		
       

  
	}
}
