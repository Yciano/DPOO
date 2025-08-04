package visual;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import logico.Bolsa;

public class Respaldo extends JDialog {

    private final JPanel contentPanel;

    public Respaldo() {
        setTitle("Gestión de Respaldo");
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        contentPanel = new JPanel() {
            private Image imagen = new ImageIcon(getClass().getResource("/jobmatchh.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton crearButton = new JButton("Crear Respaldo");
        crearButton.addActionListener((ActionEvent e) -> {
            realizarRespaldo();
            dispose();
        });
        buttonPane.add(crearButton);

        JButton restaurarButton = new JButton("Restaurar Respaldo");
        restaurarButton.addActionListener((ActionEvent e) -> {
            restaurarRespaldo();
            dispose();
        });
        buttonPane.add(restaurarButton);

        JButton enviarServidorButton = new JButton("Enviar al Servidor");
        enviarServidorButton.addActionListener((ActionEvent e) -> {
            enviarRespaldoAlServidor();
            dispose();
        });
        buttonPane.add(enviarServidorButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener((ActionEvent e) -> dispose());
        buttonPane.add(cancelButton);
    }

    private void realizarRespaldo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Respaldo");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de respaldo (*.dat)", "dat"));
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String archivoDestino = fileChooser.getSelectedFile().getAbsolutePath();
            if (!archivoDestino.toLowerCase().endsWith(".dat")) {
                archivoDestino += ".dat";
            }
            try {
                Bolsa bolsa = Bolsa.getInstance();
                bolsa.guardarDatosEnArchivo(archivoDestino);
                JOptionPane.showMessageDialog(this, "Respaldo guardado exitosamente en:\n" + archivoDestino, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar el respaldo:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void restaurarRespaldo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Restaurar Respaldo");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de respaldo (*.dat)", "dat"));
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String archivoOrigen = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                Bolsa.cargarDatosDesdeArchivo(archivoOrigen);
                JOptionPane.showMessageDialog(this, "Respaldo restaurado exitosamente desde:\n" + archivoOrigen, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Error al restaurar el respaldo:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void enviarRespaldoAlServidor() {
        File archivo = new File("bolsa_laboral.dat");
        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(this, "El archivo 'bolsa_laboral.dat' no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (
            Socket socket = new Socket("127.0.0.1", 7000);
            DataInputStream entradaArchivo = new DataInputStream(new FileInputStream(archivo));
            DataOutputStream salidaSocket = new DataOutputStream(socket.getOutputStream());
        ) {
            int unByte;
            while ((unByte = entradaArchivo.read()) != -1) {
                salidaSocket.write(unByte);
            }
            JOptionPane.showMessageDialog(this, "Respaldo enviado exitosamente al servidor.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Archivo enviado al servidor correctamente.");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al enviar al servidor:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Respaldo dialog = new Respaldo();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
