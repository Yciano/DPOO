package logico;

import java.util.Date;

public class Solicitud {
	
	private String identificador;
	private String IDcompania;
	private Date fecha;
	private Requisito requisito;
	private boolean estado;
	 
	public Solicitud(String identificador, String IDcompania, Date fecha, Requisito requisito,
			boolean estado) {
		this.identificador = identificador;
		this.IDcompania = IDcompania;
		this.fecha = fecha;
		this.requisito = requisito;
		this.estado = estado;
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

}
