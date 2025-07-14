package logico;

import java.util.ArrayList;

public class Universitario extends Empleado {

    private String carrera;

    public Universitario(String nombre, String apellido, int edad, String cedula, String sexo, float salarioDeseado,
                        String provincia, String tipoTrabajo, boolean estado, boolean dispuestoMud,
                        boolean licencia, boolean tieneVeh, String carrera) {
        super(nombre, apellido, edad, cedula, sexo, salarioDeseado, provincia, tipoTrabajo, estado,
              dispuestoMud, licencia, tieneVeh);
        this.carrera = carrera;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
