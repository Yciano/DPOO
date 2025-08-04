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
	private ArrayList<Contrato> misContratos;
	private static Bolsa bolsa = null;
	private static int contadorSolicitudes = 0;
	private static int contadorVacantes = 0;
	private static int contadorContrato = 0;


	public Bolsa() {
		this.misUsers =  new ArrayList<Usuario>();
		this.misEmpresas = new ArrayList<Empresa>();
		this.misVacantes = new ArrayList<Vacante>();
		this.misSolicitudes = new ArrayList<Solicitud>();
		this.misContratos = new ArrayList<Contrato>();
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
	
	public ArrayList<Contrato> getMisContratos() {
		return misContratos;
	}

	public void setMisContratos(ArrayList<Contrato> misContratos) {
		this.misContratos = misContratos;
	}



	public boolean registrarVacante(String identificador, String IDcompania, Requisito requisito, String posicion, String descripcion, String prioridad)
	{
		boolean realizado = false;
		Empresa aux = buscarEmpresaByCode(IDcompania);
		if(aux != null){
			Vacante vac = new Vacante(identificador, IDcompania,requisito,posicion,descripcion, prioridad);
			genCodVacante();
			contadorVacantes++;

			misVacantes.add(vac);       
			aux.getVacantes().add(vac);
			realizado = true;
		} 
		return realizado;
	}

	public void match(){
		
		for(Vacante vacante: misVacantes) {
			ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
			int contSoli = 0;
			if(vacante.getMisSolicitudes() != null) {
				for(int i = 0; i < vacante.getMisSolicitudes().size();i++){
					
					Usuario user = vacante.getMisSolicitudes().get(contSoli).getUser();
						int cheq = 0;
						int totalCrit = 0;
						totalCrit++; 
						if(vacante.getRequisito().getTipoTrabajo().equalsIgnoreCase(user.getTipoTrabajo())){
							cheq++;
							cheq += obtenerPrioridad(vacante, 1);
						}
						if(vacante.getRequisito().getTipoEmpleado().equalsIgnoreCase("Tecnico superior") && user instanceof TecnicoSuperior){
							cheq++;
							totalCrit++;
							totalCrit++; 
							if(vacante.getRequisito().getTecnico().equalsIgnoreCase(((TecnicoSuperior) user).getTecnico())) {
								cheq++;
								cheq += obtenerPrioridad(vacante, 2);
							
							}
							
						}
						else if(vacante.getRequisito().getTipoEmpleado().equalsIgnoreCase("Obrero") && user instanceof Obrero){
							cheq++;
							int tamanio = vacante.getRequisito().getMisHabilidades().size();
							totalCrit++; 
							if(tamanio > 0) {
								totalCrit++; 

								for(int j = 0; j < ((Obrero)user).getMisHabilidades().size(); j++) {
									if(vacante.getRequisito().getMisHabilidades().get(0).equalsIgnoreCase(((Obrero)user).getMisHabilidades().get(j))) {
										cheq++;
										cheq += obtenerPrioridad(vacante, 2);

									}
								}
							}
							
						}
						else if(vacante.getRequisito().getTipoEmpleado().equalsIgnoreCase("Universitario") && user instanceof Universitario){
							cheq++;
							totalCrit++; 
							totalCrit++; 
							if(vacante.getRequisito().getCarrera().equalsIgnoreCase(((Universitario) user).getCarrera())) {
								cheq++;
								cheq += obtenerPrioridad(vacante, 1);
								totalCrit++; 
							}
							
						}

						if(vacante.getRequisito().getSexo().equalsIgnoreCase(user.getSexo())){
							cheq++;
							cheq += obtenerPrioridad(vacante, 3);
						}
						totalCrit++; 

						if(vacante.getRequisito().isVeh()) {
							totalCrit++;
							if( user.isTieneVeh()){
							cheq++;
							cheq += obtenerPrioridad(vacante, 4);
							}
						}

						if(vacante.getRequisito().isFueraCity()) {
							totalCrit++;
							if(user.isDispuestoMud()){
							cheq++;
							cheq += obtenerPrioridad(vacante, 5);
							}
			
						}
						if( user instanceof Obrero) {
							totalCrit++; 
							if(((Obrero)user).getAniosExperiencia() >= vacante.getRequisito().getAniosExperiencia()){
								cheq++;
								cheq += obtenerPrioridad(vacante, 6);
							}
						}
				
						else if( user instanceof TecnicoSuperior) {
							totalCrit++; 
							if(((TecnicoSuperior)user).getAniosExperiencia() > vacante.getRequisito().getAniosExperiencia()){
								cheq++;
								cheq += obtenerPrioridad(vacante, 6);
							}
						}
							
					

						if(user.getEdad() >= vacante.getRequisito().getEdad() ) {
							cheq++;
							cheq += obtenerPrioridad(vacante, 7);

						}
						totalCrit++; 
						
						
						if (totalCrit > 0){
							int auxMatch = (cheq * 100) / totalCrit;
							int match = ((auxMatch + 5) / 10) * 10;
							if(match >= 0) {
								user.setMatch(match);
								usuarios.add(user);
							}
							
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

	private int obtenerPrioridad(Vacante aux, int cumple) {
		int cantidad = 0;
		
		 if(aux.getPrioridad().equalsIgnoreCase("Tipo de trabajo")&& cumple == 1) {
			cantidad = 2;
		}
		 else if(aux.getPrioridad().equalsIgnoreCase("Tipo de empleado") && cumple == 2) {
			cantidad = 2;
		}
		
		else if(aux.getPrioridad().equalsIgnoreCase("Sexo")&& cumple == 3) {
			cantidad = 2;
		}else if(aux.getPrioridad().equalsIgnoreCase("Vehiculo")&& cumple == 4) {
			cantidad = 2;
		}else if(aux.getPrioridad().equalsIgnoreCase("Mudarse")&& cumple == 5) {
			cantidad = 2;
		}else if(aux.getPrioridad().equalsIgnoreCase("Edad")&& cumple == 6) {
			cantidad = 2;
		}else if(aux.getPrioridad().equalsIgnoreCase("Experiencia")&& cumple == 7) {
			cantidad = 2;
		}
		
		
		return cantidad;
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
	

	public boolean registrarContrato(Usuario user, Vacante vacante, Date fecha) {
		boolean aux = false;
		if(misUsers.contains(user) && misVacantes.contains(vacante)) {
			Solicitud sol = buscarSolicitudByVacante(user, vacante);
			if(sol != null) {
				Contrato cont = new Contrato(genCodContrato(),user, vacante, sol,fecha);
				user.setEstado(true);
				Empresa emp = buscarEmpresaByCode(vacante.getIDCompania());
				emp.registrarContrato(cont);
				misContratos.add(cont);
				removerSolicitud(user,vacante);
				suspenderSolis(user);
				vacante.setEstado(false);
				aux = true;
			}
			
		}
		
		return aux;
	}
	
	private void removerSolicitud(Usuario user, Vacante vacante) {
		Solicitud sol = buscarSolicitudByVacante(user,vacante);
		if(sol != null) {
			user.removeSolicitud(sol);
			for(int i = 0; i < misSolicitudes.size(); i++) {
				if(misSolicitudes.get(i).equals(sol)) {
					misSolicitudes.remove(i);
				}
			}

		}
		
	}

	

	private Solicitud buscarSolicitudByVacante(Usuario user, Vacante vacante) {
		Solicitud aux=null;
		boolean encontrado = false;
		int i=0;
		while (!encontrado && i < user.getSolicitudes().size()) {
			if(user.getSolicitudes().get(i).getVacante().getIdentificador().equalsIgnoreCase(vacante.getIdentificador())){
				aux = user.getSolicitudes().get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
		
	}



	public Contrato buscarContratoById(String id) {
		Contrato aux=null;
		boolean encontrado = false;
		int i=0;
		while (!encontrado && i < misContratos.size()) {
			if(misContratos.get(i).getId().equalsIgnoreCase(id)){
				aux = misContratos.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
		
	}
	
	public void finalizarContrato(String id) {
		Contrato aux = buscarContratoById(id);
		if(aux != null ) {
			aux.setEstado(false);
			aux.getUser().setEstado(false);
			activarSolis(aux.getUser());
		}
	}
	
	
	private void suspenderSolis(Usuario user) {
		for(int i = user.getSolicitudes().size() - 1; i > 0; i--) {
			user.getSolicitudes().get(i).setEstado(false);
		}
	}
	
	private void activarSolis(Usuario user) {
		for(int i = user.getSolicitudes().size() - 1; i > 0; i--) {
			user.getSolicitudes().get(i).setEstado(true);
		}
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
            contadorVacantes = loadedBolsa.getMisVacantes().size();
            contadorContrato = loadedBolsa.getMisContratos().size();
            return loadedBolsa;
        }
    }

	public static String generarCodigoSolicitudActual() {
		String cod = String.format("SOL-%02d", contadorSolicitudes + 1);
		contadorSolicitudes++;
		return cod;
	}
	
	public static String genCodVacante() {
		String cod = String.format("VAC-%02d", contadorVacantes+1);
		return cod;
	}
	
	public static String genCodContrato() {
		String cod = String.format("CO-%02d", contadorContrato + 1);
		contadorContrato++;
		return cod;
	}

}