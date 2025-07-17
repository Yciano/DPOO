package logico;

import java.util.Date;

public class SolicitudUsuario {
	Usuario user;
	VacanteEmpresa vacante;
	
	
	public SolicitudUsuario(Usuario user, VacanteEmpresa vacante) {
		this.user = user;
		this.vacante = vacante;
	
	}
	
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public VacanteEmpresa getVacante() {
		return vacante;
	}
	public void setVacante(VacanteEmpresa vacante) {
		this.vacante = vacante;
	}
	 
}
