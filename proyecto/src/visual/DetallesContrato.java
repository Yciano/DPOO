package visual;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import logico.Contrato;

public class DetallesContrato extends JDialog {

    public DetallesContrato(Contrato contrato) {
        setTitle("Detalles del Contrato");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel content = new JPanel(new BorderLayout(15, 15));
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        content.setBackground(Color.WHITE);
        setContentPane(content);

        JLabel lblTitle = new JLabel("Información del Contrato", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        content.add(lblTitle, BorderLayout.NORTH);

        JPanel details = new JPanel(new GridBagLayout());
        details.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String[][] fields = {
            {"ID Contrato:", contrato.getId()},
            {"Usuario:", contrato.getUser().getNombre() + " " + contrato.getUser().getApellido()},
            {"Vacante:", contrato.getVacante().getPosicion()},
            {"Solicitud:", contrato.getSolicitud().getId()},
            {"Fecha:", sdf.format(contrato.getFecha())},
            {"Estado:", contrato.isEstado() ? "Activo" : "Finalizado"}
        };
        int row = 0;
        for (String[] f : fields) {
            gbc.gridy = row;
            gbc.gridx = 0;
            JLabel lblField = new JLabel(f[0]);
            lblField.setFont(new Font("Tahoma", Font.BOLD, 14));
            details.add(lblField, gbc);

            gbc.gridx = 1;
            JLabel lblValue = new JLabel(f[1]);
            lblValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
            details.add(lblValue, gbc);
            row++;
        }

        JScrollPane scroll = new JScrollPane(details);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        content.add(scroll, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(Color.WHITE);
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnCerrar.addActionListener(e -> dispose());
        btnPanel.add(btnCerrar);
        content.add(btnPanel, BorderLayout.SOUTH);
    }
}
