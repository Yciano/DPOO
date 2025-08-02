package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import logico.Bolsa;
import java.awt.Color;

public class Respaldo extends JDialog {

    private final JPanel contentPanel = new JPanel();

    public Respaldo() {
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(0, 102, 153));
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.WEST);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton crearButton = new JButton("Crear Respaldo");
        crearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarRespaldo();
                dispose();
            }
        });
        buttonPane.add(crearButton);

        JButton restaurarButton = new JButton("Restaurar Respaldo");
        restaurarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restaurarRespaldo();
                dispose();
            }
        });
        buttonPane.add(restaurarButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
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
                JOptionPane.showMessageDialog(this, "Respaldo guardado exitosamente en: " + archivoDestino, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar el respaldo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(this, "Respaldo restaurado exitosamente desde: " + archivoOrigen, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Error al restaurar el respaldo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
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