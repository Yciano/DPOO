package logico;
import java.io.Serializable;
import java.util.ArrayList;

public class Empresa implements Serializable{
	
	private String nombre;
	private String RNC;
	private String area;
	private String contacto;
	private String provincia;
	private ArrayList<Vacante>vacantes;
	private ArrayList<Contrato> misContratos;
	
	public Empresa(String nombre, String RNC, String area, String contacto, String provincia) {
		this.nombre = nombre;
		this.RNC =RNC;
		this.area = area;
		this.contacto  = contacto;
		this.provincia = provincia;
		this.vacantes = new ArrayList<Vacante>();
		this.misContratos = new ArrayList<Contrato	>();
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

	public ArrayList<Vacante> getVacantes() {
		return vacantes;
	}

	public void setVacantes(ArrayList<Vacante> solicitudes) {
		this.vacantes = solicitudes;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	
	 public void removeVacantes(Vacante aux) {
		 	vacantes.remove(aux);
	    }


	public ArrayList<Contrato> getMisContratos() {
		return misContratos;
	}

	public void setMisContratos(ArrayList<Contrato> misContratos) {
		this.misContratos = misContratos;
	}

	public void registrarContrato(Contrato aux) {
		misContratos.add(aux);
	}
	public void removerContrato(Usuario aux) {
		for(int i = misContratos.size() - 1; i > 0; i--) {
			if(misContratos.get(i).equals(aux)) {
				misContratos.remove(i);
			}
		}
	}
}
