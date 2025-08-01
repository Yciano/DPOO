package logico;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Bolsa implements Serializable{

	private ArrayList<Usuario> misUsers;
	private ArrayList<Empresa> misEmpresas;
	private ArrayList<Vacante> misVacantes;
	private ArrayList<Solicitud> misSolicitudes;
	private static Bolsa bolsa = null;
	private static int contadorSolicitudes = 0;
	private static int contadorVacantes = 0;

	public Bolsa() {
		this.misUsers =  new ArrayList<Usuario>();
		this.misEmpresas = new ArrayList<Empresa>();
		this.misVacantes = new ArrayList<Vacante>();
		this.misSolicitudes = new ArrayList<Solicitud>();
	}

	

	public static Bolsa getInstance(){
		if(bolsa == null){
			bolsa = new Bolsa();
		}
		return bolsa;
	} 

	public ArrayList<Usuario> getMisUsers() {
		return misUsers;
	}

	public void setMisUsers(ArrayList<Usuario> misUsers) {
		this.misUsers = misUsers;
	}

	public ArrayList<Empresa> getMisEmpresas() {
		return misEmpresas;
	}

	public void setMisEmpresas(ArrayList<Empresa> misEmpresas) {
		this.misEmpresas = misEmpresas;
	}

	public ArrayList<Vacante> getMisVacantes() {
		return misVacantes;
	}

	public void setMisVacantes(ArrayList<Vacante> misVacantes) {
		this.misVacantes = misVacantes;
	}

	public ArrayList<Solicitud> getMisSolicitudes() {
		return misSolicitudes;
	}

	public void setMisSolicitudes(ArrayList<Solicitud> misSolicitudes) {
		this.misSolicitudes = misSolicitudes;
	}

	public boolean registrarVacante(String identificador, String IDcompania, Requisito requisito, String posicion, String descripcion)
	{
		boolean realizado = false;
		Empresa aux = buscarEmpresaByCode(IDcompania);
		if(aux != null){
			Vacante vac = new Vacante(identificador, IDcompania,requisito,posicion,descripcion);
			misVacantes.add(vac);       
			aux.getVacantes().add(vac);
			realizado = true;
		} 
		return realizado;
	}

	public void match(){
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		for(Vacante vacante: misVacantes) {
			int contSoli = 0;
			if(vacante.getMisSolicitudes() != null) {
				for(int i = 0; i < vacante.getMisSolicitudes().size();i++){
					
					Usuario user = vacante.getMisSolicitudes().get(contSoli).getUser();
						int cheq = 0;
						if(vacante.getRequisito().getTipoTrabajo().equalsIgnoreCase(user.getTipoTrabajo())){
							cheq++;
						}
						if(vacante.getRequisito().getTipoEmpleado().equalsIgnoreCase("Tecnico superior") && user instanceof TecnicoSuperior){
							cheq++;
							
							if(vacante.getRequisito().getTecnico().equalsIgnoreCase(((TecnicoSuperior) user).getTecnico())) {
								cheq++;
							}
							
						}
						else if(vacante.getRequisito().getTipoEmpleado().equalsIgnoreCase("Obrero") && user instanceof Obrero){
							cheq++;
						}
						else if(vacante.getRequisito().getTipoEmpleado().equalsIgnoreCase("Universitario") && user instanceof Universitario){
							cheq++;
							
							if(vacante.getRequisito().getCarrera().equalsIgnoreCase(((Universitario) user).getCarrera())) {
								cheq++;
							}
							
						}
						if(vacante.getRequisito().getSexo().equalsIgnoreCase(user.getSexo())){
							cheq++;
						}
						if(vacante.getRequisito().isVeh() && user.isTieneVeh()){
							cheq++;
						}
						if(vacante.getRequisito().isFueraCity() && user.isDispuestoMud()){
							cheq++;
						}
						if( user instanceof Obrero && ((Obrero)user).getAniosExperiencia() >= vacante.getRequisito().getAniosExperiencia()){
							cheq++;
						}
						else if( user instanceof TecnicoSuperior && ((TecnicoSuperior)user).getAniosExperiencia() > vacante.getRequisito().getAniosExperiencia()){
							cheq++;
						}
						
						if(vacante.getRequisito().getEdad() >= user.getEdad()) {
							cheq++;
						}

						if (cheq > 0){
							user.setMatch(cheq);
							usuarios.add(user);
						}
					
					
					contSoli++;

				}
				OrdenarMatchBsort(usuarios);
				ArrayList<Solicitud> solis = new ArrayList<Solicitud>();
				for(int i = 0; i < usuarios.size(); i++) {
					Usuario auxUser = usuarios.get(i);
					for(int k = 0; k < auxUser.getSolicitudes().size(); k++ ) {
						Solicitud aux = auxUser.getSolicitudes().get(k);
						if(aux.getVacante().equals(vacante)) {
							aux.setMatch(usuarios.get(i).getMatch());
							solis.add(aux);
						}
					}

				usuarios.get(i).setMatch(0);
			}
				
				vacante.setMisSolicitudes(solis);
				solis = null;
			}
		}

	}

	public void OrdenarMatchBsort(ArrayList<Usuario> users) {
		for (int i = 0; i < users.size() - 1; i++) {
			for (int j = 0; j < users.size() - i - 1; j++) {
				if (users.get(j).getMatch() < users.get(j + 1).getMatch()) {
					Usuario temp = users.get(j);
					users.set(j, users.get(j + 1));
					users.set(j + 1, temp);
				}
			}
		}
	}

	public Empresa buscarEmpresaByCode(String IDcompania){
		Empresa aux=null;
		boolean encontrado = false;
		int i=0;
		while (!encontrado && i < misEmpresas.size()) {
			if(misEmpresas.get(i).getRNC().equalsIgnoreCase(IDcompania)){
				aux = misEmpresas.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}


	public Usuario buscarEmpleadoByCedula(String cedula){
		Usuario aux = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < misUsers.size()){
			if(misUsers.get(i).getCedula().equalsIgnoreCase(cedula)){
				aux = misUsers.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

	public boolean registrarEmpleado(Usuario nuevoUsuario){
		boolean aux = false;
		if(buscarEmpleadoByCedula(nuevoUsuario.getCedula()) == null) {
			aux = true;
			misUsers.add(nuevoUsuario);	
		}
		return aux;
	}

	public boolean registrarEmpresa(Empresa nuevaEmpresa){
		boolean aux = false;
		if(buscarEmpresaByCode(nuevaEmpresa.getRNC()) == null) {
			aux = true;
			misEmpresas.add(nuevaEmpresa);
		}
		return aux;
	}

	public boolean correoExiste(String correo) {
		for (Empresa emp : misEmpresas) {
			String contacto = emp.getContacto();
			if (contacto != null && contacto.equalsIgnoreCase(correo)) {
				return true;
			}
		}
		return false;
	}

	public void removeUser(String cedula) {
		Usuario aux = buscarEmpleadoByCedula(cedula);
		Vacante vac = null;
		for(int i = aux.getSolicitudes().size() - 1; i >= 0 ; i--) {
			vac = aux.getSolicitudes().get(i).getVacante();
			vac.removeSolicitud(aux.getSolicitudes().get(i));
			aux.removeSolicitud(aux.getSolicitudes().get(i));
		}
		misUsers.remove(aux);
	}

	public void removeVacante(String identificador) {
		Vacante aux = buscarVacanteByID(identificador);
		for(int i = aux.getMisSolicitudes().size() - 1; i >= 0 ; i--) {
			aux.removeSolicitud(aux.getMisSolicitudes().get(i));
		}
		misVacantes.remove(aux);
	}

	public boolean removeEmpresa(String RNC) {
		Empresa aux = buscarEmpresaByCode(RNC);
		boolean eliminado = false; 
		if (aux != null) {
			if (aux.getVacantes() != null) {
				for (int i = aux.getVacantes().size() - 1; i >= 0; i--) {
					aux.removeVacantes(aux.getVacantes().get(i));
				}
			}
			eliminado = misEmpresas.remove(aux); 
		}
		return eliminado;
	}

	public void modificarUsuario(Usuario update) {
		int index = buscarUsuarioByIndex(update.getCedula());
		misUsers.set(index, update);
	}

	private int buscarUsuarioByIndex(String cedula) {
		int index = -1;
		boolean encontrado = false;
		int i = 0;
		while(!encontrado && i < misUsers.size()) {
			if(misUsers.get(i).getCedula().equalsIgnoreCase(cedula)) {
				index = i;
				encontrado = true;
			}
			i++;
		}
		return index;
	}

	public Vacante buscarVacanteByID(String idVacante) {
		for (Vacante vac : misVacantes) {
			if (vac.getIdentificador().equalsIgnoreCase(idVacante)) {
				return vac;
			}
		}
		return null;
	}


	public Solicitud buscarSolicitudByID(String id) {
		for (Solicitud sol : misSolicitudes) {
			if (sol.getId().equalsIgnoreCase(id)) {
				return sol;
			}
		}
		return null;
	}

	public boolean aplicarAVacante(String cedulaUsuario, String idVacante, int salarioEsperado) {
		boolean aux = false;
		Usuario usuario = buscarEmpleadoByCedula(cedulaUsuario);
		Vacante vacante = buscarVacanteByID(idVacante);

		if (usuario != null && vacante != null) {
			String idSolicitud = generarCodigoSolicitudActual();
			Solicitud nuevaSolicitud = new Solicitud(idSolicitud, salarioEsperado, usuario, vacante);
			misSolicitudes.add(nuevaSolicitud);
			usuario.getSolicitudes().add(nuevaSolicitud);
			vacante.getMisSolicitudes().add(nuevaSolicitud);
			aux = true;
		}
		return aux;
	}
	
	
	
	public void guardarDatosEnArchivo(String archivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(this);
        }
    }
    
    public static Bolsa cargarDatosDesdeArchivo(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Bolsa loadedBolsa = (Bolsa) ois.readObject();
            bolsa = loadedBolsa;
            contadorSolicitudes = loadedBolsa.getMisSolicitudes().size();
            return loadedBolsa;
        }
    }

	public static String generarCodigoSolicitudActual() {
		String cod = String.format("SOL-%02d", contadorSolicitudes + 1);
		contadorSolicitudes++;
		return cod;
	}
	
	public static String genCodVacante() {
		String cod = String.format("VAC-%02d", contadorVacantes + 1);
		contadorVacantes++;
		return cod;
	}

}