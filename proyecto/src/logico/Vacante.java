package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Vacante implements Serializable{
	
	private String identificador;
	private String IDcompania;
	private String posicion;
	private String descripcion;
	private Date fecha;
	private Requisito requisito;
	private boolean estado;
	private ArrayList<Solicitud> misSolicitudes;
	 
	public Vacante(String identificador, String IDcompania, Requisito requisito, String posicion, String descripcion) {
		this.identificador = identificador;
		this.IDcompania = IDcompania;
		this.fecha = new Date();
		this.requisito = requisito;
		this.estado = estado;
		this.posicion = posicion;
		this.descripcion = descripcion;
		this.misSolicitudes = new ArrayList<Solicitud>();
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getIDCompania() {
		return IDcompania;
	}

	public void setIDCompania(String IDcompania) {
		this.IDcompania = IDcompania;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Requisito getRequisito() {
		return requisito;
	}

	public void setRequisito(Requisito requisito) {
		this.requisito = requisito;
	}


	public ArrayList<Solicitud> getMisSolicitudes() {
		return misSolicitudes;
	}

	public void setMisSolicitudes(ArrayList<Solicitud> misSolicitudes) {
		this.misSolicitudes = misSolicitudes;
	}
	

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void removeSolicitud(Solicitud solicitud) {
		misSolicitudes.remove(solicitud);
	}

	public void agregarSolicitudes(Solicitud solicitud) {
		misSolicitudes.add(solicitud);

		
	}
	
}
