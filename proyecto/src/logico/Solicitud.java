package logico;

import java.io.Serializable;
import java.util.Date;

public class Solicitud implements Serializable {
	private String id;
	private Date fecha;
	private int salario;
	private Usuario user;
	private Vacante vacante;
	private int match;
	private boolean estado;

	public Solicitud(String id, int salario, Usuario user, Vacante vacante) {
		this.id = id;
		this.fecha = new Date();
		this.salario = salario;
		this.user = user;
		this.vacante = vacante;
		this.estado = true;
	}

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	public Date getFecha() { return fecha; }
	public void setFecha(Date fecha) { this.fecha = fecha; }

	public int getSalario() { return salario; }
	public void setSalario(int salario) { this.salario = salario; }


	public Usuario getUser() { return user; }
	public void setUser(String IdUser) { this.user = user; }

	public Vacante getVacante() { return vacante; }
	public void setVacante(Vacante vacante) { this.vacante = vacante; }

	public int getMatch() {
		return match;
	}

	public void setMatch(int match) {
		this.match = match;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	
	
}
