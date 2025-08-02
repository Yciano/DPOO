package logico;

import java.util.Date;

public class Contrato {

	private String id;
	private Usuario user;
	private Vacante vacante;
	private Solicitud solicitud;
	private Date fecha;
	private boolean estado; //True: Activo --- False: Finalizado
	public Contrato(String id,Usuario user, Vacante vacante, Solicitud solicitud, Date fecha) {
		super();
		this.id = id;
		this.user = user;
		this.vacante = vacante;
		this.solicitud = solicitud;
		this.fecha = fecha;
		this.estado = true;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Solicitud getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}
	
	
}
