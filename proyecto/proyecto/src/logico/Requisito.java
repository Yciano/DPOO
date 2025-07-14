package logico;

import java.util.ArrayList;

public class Requisito {
    
    private String tipoTrabajo;
    private String tipoEmpleado;
    private String Carrera;
    private String Tecnico;
    private ArrayList<String> misHabilidades;
    private String sexo;
    private int aniosExperiencia;
    private int edad;
    private boolean Veh;
    private boolean fueraCity;
    private String prioridad;

      public Requisito(String tipoTrabajo, String tipoEmpleado, String carrera, String tecnico,
                     ArrayList<String> misHabilidades, String sexo, int aniosExperiencia, int edad,
                     boolean veh, boolean fueraCity, String prioridad) {
        this.tipoTrabajo = tipoTrabajo;
        this.tipoEmpleado = tipoEmpleado;
        this.Carrera = carrera;
        this.Tecnico = tecnico;
        this.misHabilidades = misHabilidades;
        this.sexo = sexo;
        this.aniosExperiencia = aniosExperiencia;
        this.edad = edad;
        this.Veh = veh;
        this.fueraCity = fueraCity;
        this.prioridad = prioridad;
    }

	public String getTipoTrabajo() {
		return tipoTrabajo;
	}

	public void setTipoTrabajo(String tipoTrabajo) {
		this.tipoTrabajo = tipoTrabajo;
	}

	public String getTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(String tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	public String getCarrera() {
		return Carrera;
	}

	public void setCarrera(String carrera) {
		Carrera = carrera;
	}

	public String getTecnico() {
		return Tecnico;
	}

	public void setTecnico(String tecnico) {
		Tecnico = tecnico;
	}

	public ArrayList<String> getMisHabilidades() {
		return misHabilidades;
	}

	public void setMisHabilidades(ArrayList<String> misHabilidades) {
		this.misHabilidades = misHabilidades;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getAniosExperiencia() {
		return aniosExperiencia;
	}

	public void setAniosExperiencia(int aniosExperiencia) {
		this.aniosExperiencia = aniosExperiencia;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public boolean isVeh() {
		return Veh;
	}

	public void setVeh(boolean veh) {
		this.Veh = veh;
	}

	public boolean isFueraCity() {
		return fueraCity;
	}

	public void setFueraCity(boolean fueraCity) {
		this.fueraCity = fueraCity;
	}

   public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}


}
