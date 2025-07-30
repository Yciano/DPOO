package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class TecnicoSuperior extends Usuario implements Serializable {
	
	private String tecnico;
	private int aniosExperiencia;

    public TecnicoSuperior(String nombre, String apellido, int edad, String cedula, String contacto, String sexo,
			float SalarioDeseado, String provincia, String tipoTrabajo, boolean estado, boolean dispuestoMud,
			boolean licencia, boolean TieneVeh, String tecnico, int aniosExperiencia) {
		super(nombre, apellido, edad, cedula, contacto, sexo, SalarioDeseado, provincia, tipoTrabajo, estado,
				dispuestoMud, licencia, TieneVeh);
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
