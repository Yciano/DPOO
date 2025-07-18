package logico;

import java.util.Date;

public class Solicitud {
	Usuario user;
	Vacante vacante;
	
	
	public Solicitud(Usuario user, Vacante vacante) {
		this.user = user;
		this.vacante = vacante;
	
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
	 
}
