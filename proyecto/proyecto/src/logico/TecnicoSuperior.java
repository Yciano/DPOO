package logico;

import java.util.ArrayList;

public class TecnicoSuperior extends Empleado {
	
	private String tecnico;
	private int aniosExperiencia;
	
  public TecnicoSuperior(String nombre, String apellido, int edad, String cedula, String sexo, float salarioDeseado,
                          String provincia, String tipoTrabajo, boolean estado, boolean dispuestoMud,
                          boolean licencia, boolean tieneVeh, String tecnico, int aniosExperiencia) {
         super(nombre, apellido, edad, cedula, sexo, salarioDeseado ,provincia, tipoTrabajo, estado,
              dispuestoMud, licencia, tieneVeh);
        this.tecnico = tecnico;
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }
}
