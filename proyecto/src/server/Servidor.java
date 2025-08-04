package server;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servidor {

    public static void main(String[] args) {
        ServerSocket sfd = null;

        try {
            sfd = new ServerSocket(7000);
            System.out.println("Servidor escuchando en el puerto 7000...");
        } catch (IOException ioe) {
            System.out.println("Comunicación rechazada: " + ioe);
            System.exit(1);
        }

        File carpetaRespaldos = new File("respaldos");
        if (!carpetaRespaldos.exists()) {
            if (carpetaRespaldos.mkdir()) {
                System.out.println("Carpeta 'respaldos' creada.");
            } else {
                System.out.println("No se pudo crear la carpeta 'respaldos'.");
                System.exit(1);
            }
        }

        while (true) {
            try {
                Socket nsfd = sfd.accept();
                System.out.println("Conexión aceptada de: " + nsfd.getInetAddress());

                DataInputStream entrada = new DataInputStream(nsfd.getInputStream());

                String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
                File respaldo = new File(carpetaRespaldos, "respaldo_" + timestamp + ".dat");

                DataOutputStream escritor = new DataOutputStream(new FileOutputStream(respaldo));

                int unByte;
                try {
                    while ((unByte = entrada.read()) != -1) {
                        escritor.write(unByte);
                    }
                    System.out.println("Respaldo guardado en: " + respaldo.getPath());
                } catch (IOException e) {
                    System.out.println("Error durante la lectura/escritura: " + e.getMessage());
                } finally {
                    entrada.close();
                    escritor.close();
                    nsfd.close();
                    System.out.println("Conexión cerrada.");
                }

            } catch (IOException ioe) {
                System.out.println("Error: " + ioe.getMessage());
            }
        }
    }
}
