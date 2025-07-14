package logico;

import java.util.ArrayList;

public abstract class Empleado {
	
	private String nombre;
	private String apellido;
	private int edad;
	private String cedula;
	private String contacto;
	private String sexo;
	private float SalarioDeseado;
	private String provincia;
	private String tipoTrabajo;
	private boolean estado;
	private boolean dispuestoMud;
	private boolean licencia;
	private boolean TieneVeh;
	private int match;
	private ArrayList<Solicitud>solicitudes;
	
	public Empleado(String nombre, String apellido, int edad, String cedula, String sexo, float SalarioDeseado, String provincia, String tipoTrabajo, boolean estado, boolean dispuestoMud, boolean licencia, boolean TieneVeh) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.cedula = cedula;
		this.sexo = sexo;
		this.SalarioDeseado = SalarioDeseado;
		this.provincia = provincia;
		this.tipoTrabajo = tipoTrabajo;
		this.estado = estado;
		this.dispuestoMud = dispuestoMud;
		this.licencia = licencia;
		this.TieneVeh = TieneVeh;
		
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

		public int match() {
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

}
