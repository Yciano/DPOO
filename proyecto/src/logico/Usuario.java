package logico;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Usuario implements Serializable{
	
	protected String nombre;
	protected String apellido;
	protected int edad;
	protected String cedula;
	protected String contacto;
	protected String sexo;
	protected String provincia;
	protected String tipoTrabajo;
	protected boolean estado;
	protected boolean dispuestoMud;
	protected boolean licencia;
	protected boolean TieneVeh;
	protected int match;
	protected ArrayList<Solicitud>solicitudes;
	private String rutaImagen;
	
	public Usuario(String nombre, String apellido, int edad, String cedula, String contacto, String sexo, float SalarioDeseado, String provincia, String tipoTrabajo, boolean estado, boolean dispuestoMud, boolean licencia, boolean TieneVeh) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.cedula = cedula;
		this.contacto = contacto;
		this.sexo = sexo;
		this.provincia = provincia;
		this.tipoTrabajo = tipoTrabajo;
		this.estado = estado;
		this.dispuestoMud = dispuestoMud;
		this.licencia = licencia;
		this.TieneVeh = TieneVeh;
		this.solicitudes = new ArrayList<Solicitud>();
		this.rutaImagen = null;
		
	}

	public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public int getEdad() {
			return edad;
		}

		public void setEdad(int edad) {
			this.edad = edad;
		}

		public String getCedula() {
			return cedula;
		}

		public void setCedula(String cedula) {
			this.cedula = cedula;
		}

		public String getContacto() {
			return contacto;
		}

		public void setContacto(String contacto) {
			this.contacto = contacto;
		}

		public String getSexo() {
			return sexo;
		}

		public void setSexo(String sexo) {
			this.sexo = sexo;
		}

		public String getProvincia() {
			return provincia;
		}

		public void setProvincia(String provincia) {
			this.provincia = provincia;
		}

		public String getTipoTrabajo() {
			return tipoTrabajo;
		}

		public void setTipoTrabajo(String tipoTrabajo) {
			this.tipoTrabajo = tipoTrabajo;
		}

		public boolean isEstado() {
			return estado;
		}

		public void setEstado(boolean estado) {
			this.estado = estado;
		}

		public boolean isDispuestoMud() {
		    return dispuestoMud;
		}

		public void setdispuestoMud(boolean dispuestoMud) {
			this.dispuestoMud = dispuestoMud;
		}

		public boolean isLicencia() {
			return licencia;
		}

		
		public void setLicencia(boolean licencia) {
			this.licencia = licencia;
		}

		public boolean isTieneVeh() {
			return TieneVeh;
		}

		public void setTieneVeh(boolean tieneVeh) {
			TieneVeh = tieneVeh;
		}

		public int getMatch() {
			return match;
		}

		public void setMatch(int match) {
			this.match = match;
		}

		public ArrayList<Solicitud> getSolicitudes() {
			return solicitudes;
		}

		public void setSolicitudes(ArrayList<Solicitud> solicitudes) {
			this.solicitudes = solicitudes;
		}
		
		public void removeSolicitud(Solicitud aux) {
		    solicitudes.remove(aux);
		}
		public String getRutaImagen() {
			return rutaImagen;
		}

		public void setRutaImagen(String rutaImagen) {
			this.rutaImagen = rutaImagen;
		}


}
