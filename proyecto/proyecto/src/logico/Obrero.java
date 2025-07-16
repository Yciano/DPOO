package logico;

import java.util.ArrayList;

public class Obrero extends Empleado {

    private ArrayList<String> misHabilidades;
    private int aniosExperiencia;

    

    public Obrero(String nombre, String apellido, int edad, String cedula, String contacto, String sexo,
			float SalarioDeseado, String provincia, String tipoTrabajo, boolean estado, boolean dispuestoMud,
			boolean licencia, boolean TieneVeh, ArrayList<String> misHabilidades, int aniosExperiencia) {
		super(nombre, apellido, edad, cedula, contacto, sexo, SalarioDeseado, provincia, tipoTrabajo, estado,
				dispuestoMud, licencia, TieneVeh);
		this.misHabilidades = misHabilidades;
		this.aniosExperiencia = aniosExperiencia;
	}

	public ArrayList<String> getMisHabilidades() {
        return misHabilidades;
    }

    public void setMisHabilidades(ArrayList<String> misHabilidades) {
        this.misHabilidades = misHabilidades;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }
    
   
}
