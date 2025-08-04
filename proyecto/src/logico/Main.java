package logico;

import java.io.Serializable;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;

import visual.Login;
import visual.Principal;

public class Main implements Serializable {

    public static void main(String[] args) {
        try {
            
            UIManager.setLookAndFeel(new FlatLightLaf());

            Bolsa.cargarDatosDesdeArchivo("respaldo.dat");
            //System.out.println("Servidor conectado con éxito.");
        } catch (Exception e) {
            System.out.println("No se pudo conectar al servidor. Se iniciará vacío.");
            e.printStackTrace();
        }

        Login login = new Login();
        login.setVisible(true);
    }
}