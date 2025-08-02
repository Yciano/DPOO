package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private int puerto;
    private static final String CARPETA_DESTINO = "respaldo_recibido.dat";

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    public void iniciar() {
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto + "...");

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado desde: " + cliente.getInetAddress());

                recibirArchivo(cliente);
                cliente.close();
                System.out.println("Archivo recibido y conexión cerrada.");
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    private void recibirArchivo(Socket cliente) throws IOException {
        try (
            DataInputStream dis = new DataInputStream(cliente.getInputStream());
            FileOutputStream fos = new FileOutputStream(CARPETA_DESTINO)
        ) {
            long tamanioArchivo = dis.readLong();  

            byte[] buffer = new byte[4096];
            int leido;
            long recibido = 0;

            while (recibido < tamanioArchivo && (leido = dis.read(buffer)) != -1) {
                fos.write(buffer, 0, leido);
                recibido += leido;
            }
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor(5000);
        servidor.iniciar();
    }
}
