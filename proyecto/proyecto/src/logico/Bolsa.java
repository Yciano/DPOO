package logico;

import java.util.ArrayList;
import java.util.Date;

public class Bolsa {
	
	private ArrayList<Empleado> misEmpleados;
	private ArrayList<Empresa> misEmpresas;
	private ArrayList<Solicitud> misSolicitudes;
	private static Bolsa bolsa = null;

	public Bolsa() {
		this.misEmpleados =  new ArrayList<Empleado>();
		this.misEmpresas = new ArrayList<Empresa>();
		this.misSolicitudes = new ArrayList<Solicitud>();
	}
	
	public static Bolsa getInstance(){
		if(bolsa == null){
			bolsa = new Bolsa();
		}
		return bolsa;
	} 

	public ArrayList<Empleado> getMisEmpleados() {
		return misEmpleados;
	}

	public void setMisEmpleados(ArrayList<Empleado> misEmpleados) {
		this.misEmpleados = misEmpleados;
	}

	public ArrayList<Empresa> getMisEmpresas() {
		return misEmpresas;
	}

	public void setMisEmpresas(ArrayList<Empresa> misEmpresas) {
		this.misEmpresas = misEmpresas;
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
			Solicitud sol = new Solicitud(identificador, IDcompania, fecha, requisito, estado);
			misSolicitudes.add(sol);       
			aux.getSolicitudes().add(sol);
			realizado = true;
		} 
		return realizado;
	}

	public ArrayList<Empleado> match(Solicitud sol){
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		for(Empleado aux: misEmpleados){
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

		return empleados;
	}


	public void OrdenarMatchBsort(ArrayList<Empleado> empleados) {
		for (int i = 0; i < empleados.size() - 1; i++) {
			for (int j = 0; j < empleados.size() - i - 1; j++) {
				if (empleados.get(j).match() < empleados.get(j + 1).match()) {
					Empleado temp = empleados.get(j);
					empleados.set(j, empleados.get(j + 1));
					empleados.set(j + 1, temp);
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

	public Empleado buscarEmpleadoByCedula(String cedula){
		Empleado aux = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < misEmpleados.size()){
			if(misEmpleados.get(i).getCedula().equalsIgnoreCase(cedula)){
				aux = misEmpleados.get(i);
				encontrado = true;
			}
			i++;
		}
		return aux;
	}

		public boolean registrarEmpleado(Empleado nuevoEmpleado){
		boolean aux = false;
		if(buscarEmpleadoByCedula(nuevoEmpleado.getCedula()) == null) {
			aux = true;
			misEmpleados.add(nuevoEmpleado);	
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
		        if (emp.getContacto().equalsIgnoreCase(correo)) {
		            return true;
		        }
		    }
		    return false;
		}
	
}




