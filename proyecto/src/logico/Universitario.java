package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Universitario extends Usuario implements Serializable {

    private String carrera;
    private String area;

    public Universitario(String nombre, String apellido, int edad, String cedula, String contacto, String sexo,
			float SalarioDeseado, String provincia, String tipoTrabajo, boolean estado, boolean dispuestoMud,
			boolean licencia, boolean TieneVeh, String carrera,String area) {
		super(nombre, apellido, edad, cedula, contacto, sexo, SalarioDeseado, provincia, tipoTrabajo, estado,
				dispuestoMud, licencia, TieneVeh);
		this.carrera = carrera;
		this.area = area;
	}

	public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
    
}
