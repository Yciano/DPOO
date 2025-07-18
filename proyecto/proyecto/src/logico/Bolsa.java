package logico;

import java.util.ArrayList;
import java.util.Date;

public class Bolsa {
	
	private ArrayList<Usuario> misUsers;
	private ArrayList<Empresa> misEmpresas;
	private ArrayList<Vacante> misVacantes;
	private ArrayList<Solicitud> misSolicitudes;
	private static Bolsa bolsa = null;

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
	
	public boolean registrarSolictud(String identificador, String IDcompania, Date fecha, Requisito requisito, boolean estado)
	{
		boolean realizado = false;
		Empresa aux = buscarEmpresaByCode(IDcompania);
		if(aux != null){
			Vacante sol = new Vacante(identificador, IDcompania, fecha, requisito, estado);
			misVacantes.add(sol);       
			aux.getSolicitudes().add(sol);
			realizado = true;
		} 
		return realizado;
	}

	public ArrayList<Usuario> match(Vacante sol){
		ArrayList<Usuario> empleados = new ArrayList<Usuario>();
		for(Usuario aux: misUsers){
			int cheq = 0;
			if(aux.getTipoTrabajo().equalsIgnoreCase(sol.getRequisito().getTipoTrabajo())){
				cheq++;
			}
			if(sol.getRequisito().getTipoEmpleado().equalsIgnoreCase("Tecnico superior") && aux instanceof TecnicoSuperior){
				cheq++;
			}
			else if(sol.getRequisito().getTipoEmpleado().equalsIgnoreCase("Obrero") && aux instanceof Obrero){
				cheq++;
			}
			else if(sol.getRequisito().getTipoEmpleado().equalsIgnoreCase("Universitario") && aux instanceof Universitario){
				cheq++;
			}
			if(aux.getSexo().equalsIgnoreCase(sol.getRequisito().getSexo())){
				cheq++;
			}
			if(aux.isTieneVeh() && sol.getRequisito().isVeh()){
				cheq++;
			}
			if(aux.isDispuestoMud() && sol.getRequisito().isFueraCity()){
				cheq++;
			}
			if( aux instanceof Obrero && ((Obrero)aux).getAniosExperiencia() > sol.getRequisito().getAniosExperiencia()){
				cheq++;
			}
			else if( aux instanceof TecnicoSuperior && ((TecnicoSuperior)aux).getAniosExperiencia() > sol.getRequisito().getAniosExperiencia()){
				cheq++;
			}

			if (cheq > 0){
				aux.setMatch(cheq);
				empleados.add(aux);
			}


		}
		
		OrdenarMatchBsort(empleados);

		return empleados;
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
			for(int i = aux.getSolicitudes().size() - 1; i >= 0 ; i--) {
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
		        if (aux.getSolicitudes() != null) {
		            for (int i = aux.getSolicitudes().size() - 1; i >= 0; i--) {
		                aux.removeSolicitud(aux.getSolicitudes().get(i));
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

		
		public boolean aplicarAVacante(String cedulaUsuario, String idVacante) {
		    Usuario usuario = buscarEmpleadoByCedula(cedulaUsuario);
		    Vacante vacante = buscarVacanteByID(idVacante);

		    if (usuario != null && vacante != null) {
		        Solicitud nuevaSolicitud = new Solicitud(usuario, vacante);
		        misSolicitudes.add(nuevaSolicitud);
		        usuario.getSolicitudes().add(vacante);
		        return true;
		    }
		    return false;
		}

	
}




