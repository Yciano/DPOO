package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Obrero;
import logico.TecnicoSuperior;
import logico.Universitario;
import logico.Usuario;

public class DetallesUsuario extends JDialog {

    public DetallesUsuario(Usuario usuario) {
        setTitle("Detalles del Usuario");
        setBounds(100, 100, 720, 440);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(null);
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JLabel lblFoto = new JLabel();
        lblFoto.setBounds(30, 30, 150, 150);
        lblFoto.setHorizontalAlignment(JLabel.CENTER);
        lblFoto.setVerticalAlignment(JLabel.CENTER);
        lblFoto.setOpaque(true);
        lblFoto.setBackground(Color.LIGHT_GRAY);
        lblFoto.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
  
        String ruta = usuario.getRutaImagen();
        ImageIcon icon;

        if (ruta != null && !ruta.trim().isEmpty() && new File(ruta).exists()) {
            icon = new ImageIcon(ruta);
        } else {
            icon = new ImageIcon("src/Defaultt.png");
        }

        Image scaled = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        lblFoto.setIcon(new ImageIcon(scaled));

        contentPanel.add(lblFoto);

        JLabel lblNombre = new JLabel(usuario.getNombre() + " " + usuario.getApellido());
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblNombre.setBounds(200, 30, 480, 30);
        contentPanel.add(lblNombre);

        JPanel datos = new JPanel(new GridLayout(0, 2, 15, 8));
        datos.setBackground(Color.WHITE);
        datos.setBounds(200, 70, 480, 220);

        datos.add(new JLabel("Cédula: " + usuario.getCedula()));
        datos.add(new JLabel("Edad: " + usuario.getEdad()));
        datos.add(new JLabel("Sexo: " + usuario.getSexo()));
        datos.add(new JLabel("Contacto: " + usuario.getContacto()));
        datos.add(new JLabel("Tipo de trabajo: " + usuario.getTipoTrabajo()));
        datos.add(new JLabel("Provincia: " + usuario.getProvincia()));
        datos.add(new JLabel("¿Tiene licencia?: " + (usuario.isLicencia() ? "Sí" : "No")));
        datos.add(new JLabel("¿Tiene vehículo?: " + (usuario.isTieneVeh() ? "Sí" : "No")));
        datos.add(new JLabel("¿Dispuesto a mudarse?: " + (usuario.isDispuestoMud() ? "Sí" : "No")));
        datos.add(new JLabel("¿Empleado?: " + (usuario.isEstado() ? "Sí" : "No")));

        if (usuario instanceof Universitario) {
            datos.add(new JLabel("Carrera universitaria: " + ((Universitario) usuario).getCarrera()));
        } else if (usuario instanceof TecnicoSuperior) {
            datos.add(new JLabel("Área técnica: " + ((TecnicoSuperior) usuario).getTecnico()));
            datos.add(new JLabel("Años de experiencia: " + ((TecnicoSuperior) usuario).getAniosExperiencia()));
        }else if (usuario instanceof Obrero) {
            datos.add(new JLabel("Años de experiencia: " + ((Obrero) usuario).getAniosExperiencia()));

            ArrayList<String> habilidades = ((Obrero) usuario).getMisHabilidades();
            StringBuilder sb = new StringBuilder("<html>Habilidades:<br>");
            if (habilidades != null && !habilidades.isEmpty()) {
                for (String hab : habilidades) {
                    sb.append("• ").append(hab).append("<br>");
                }
            } else {
                sb.append("No especificadas<br>");
            }
            sb.append("</html>");

            JLabel lblHabilidades = new JLabel(sb.toString());
            datos.add(lblHabilidades);
        }




        contentPanel.add(datos);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnCerrar.setBounds(300, 310, 120, 30);
        btnCerrar.addActionListener(e -> dispose());
        contentPanel.add(btnCerrar);
    }
}
