package logico;
import java.util.ArrayList;

public class Empresa{
	
	private String nombre;
	private String RNC;
	private String area;
	private String contacto;
	private String provincia;
	private ArrayList<Vacante>solicitudes;
	
	public Empresa(String nombre, String RNC, String area, String contacto, String provincia, ArrayList<Vacante> solicitudes) {
		this.nombre = nombre;
		this.RNC =RNC;
		this.area = area;
		this.contacto  = contacto;
		this.provincia = provincia;
		this.solicitudes = solicitudes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRNC() {
		return RNC;
	}

	public void setRNC(String rNC) {
		RNC = rNC;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public ArrayList<Vacante> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(ArrayList<Vacante> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	
	 public void removeSolicitud(Vacante aux) {
	    	solicitudes.remove(aux);
	    }


}
