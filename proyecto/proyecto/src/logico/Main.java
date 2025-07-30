package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Main implements Serializable {

	public static void main(String[] args) {
		
        Universitario u1 = new Universitario("Ana", "L�pez", 24, "001", "809-111-1111", "Femenino",
                25000f, "Santiago", "Remoto", true, false, false, false, "Ingenier�a");
        u1.setMatch(3);

        Universitario u2 = new Universitario("Carlos", "Mart�nez", 27, "002", "809-222-2222", "Masculino",
                30000f, "Santo Domingo", "Presencial", true, true, true, true, "Psicolog�a");
        u2.setMatch(7);

        Universitario u3 = new Universitario("Elena", "Su�rez", 22, "003", "809-333-3333", "Femenino",
                20000f, "La Vega", "H�brido", false, false, false, false, "Derecho");
        u3.setMatch(5);

        Universitario u4 = new Universitario("Luis", "G�mez", 29, "004", "809-444-4444", "Masculino",
                27000f, "Puerto Plata", "Presencial", true, true, true, true, "Medicina");
        u4.setMatch(1);

        Universitario u5 = new Universitario("Paola", "Ram�rez", 26, "005", "809-555-5555", "Femenino",
                26000f, "San Crist�bal", "Remoto", true, true, false, true, "Administraci�n");
        u5.setMatch(4);

        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(u1);
        usuarios.add(u2);
        usuarios.add(u3);
        usuarios.add(u4);
        usuarios.add(u5);

        // Mostrar antes de ordenar
        System.out.println(" Antes de ordenar por match:");
        imprimirUsuarios(usuarios);

        OrdenarMatchBsort(usuarios);

        // Mostrar despu�s de ordenar
        System.out.println("\n Despu�s de ordenar por match (de mayor a menor):");
        imprimirUsuarios(usuarios);
    }

    public static void imprimirUsuarios(ArrayList<Usuario> usuarios) {
        for (Usuario u : usuarios) {
            System.out.println(u.getNombre() + " " + u.getApellido() + " - Match: " + u.getMatch());
        }
    }

    public static void OrdenarMatchBsort(ArrayList<Usuario> users) {
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

}
