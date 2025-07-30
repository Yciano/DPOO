package logico;

import java.io.Serializable;

public class Solicitud implements Serializable {
	private String id;
	private String fecha;
	private int salario;
	private String tipoTrabajo;

	private Usuario user;
	private Vacante vacante;

	public Solicitud(String id, String fecha, int salario, String tipoTrabajo, Usuario user) {
		this.id = id;
		this.fecha = fecha;
		this.salario = salario;
		this.tipoTrabajo = tipoTrabajo;
		this.user = user;
		this.vacante = null;
	}

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	public String getFecha() { return fecha; }
	public void setFecha(String fecha) { this.fecha = fecha; }

	public int getSalario() { return salario; }
	public void setSalario(int salario) { this.salario = salario; }

	public String getTipoTrabajo() { return tipoTrabajo; }
	public void setTipoTrabajo(String tipoTrabajo) { this.tipoTrabajo = tipoTrabajo; }

	public Usuario getUser() { return user; }
	public void setUser(Usuario user) { this.user = user; }

	public Vacante getVacante() { return vacante; }
	public void setVacante(Vacante vacante) { this.vacante = vacante; }
}
