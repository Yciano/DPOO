package logico;

import java.util.Date;

public class Contrato {

	private Usuario user;
	private Vacante vacante;
	private Date fecha;
	public Contrato(Usuario user, Vacante vacante, Date fecha) {
		super();
		this.user = user;
		this.vacante = vacante;
		this.fecha = fecha;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public Vacante getVacante() {
		return vacante;
	}
	public void setVacante(Vacante vacante) {
		this.vacante = vacante;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
}
