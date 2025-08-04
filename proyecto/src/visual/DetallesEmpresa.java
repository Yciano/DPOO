package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import logico.Empresa;

public class DetallesEmpresa extends JDialog {

    public DetallesEmpresa(Empresa empresa) {
        setTitle("Detalles de la Empresa");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);

        JPanel content = new JPanel(new BorderLayout(15, 15));
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        content.setBackground(Color.WHITE);
        setContentPane(content);

        JLabel lblTitle = new JLabel("Información de la Empresa", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
        content.add(lblTitle, BorderLayout.NORTH);

        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        String[][] fields = {
            {"Nombre:", empresa.getNombre()},
            {"RNC:", empresa.getRNC()},
            {"Área:", empresa.getArea()},
            {"Provincia:", empresa.getProvincia()},
            {"Contacto:", empresa.getContacto()},
            {"Vacantes registradas:", String.valueOf(empresa.getVacantes().size())},
            {"Contratos realizados:", String.valueOf(empresa.getMisContratos().size())}
        };
        int row = 0;
        for (String[] f : fields) {
            gbc.gridx = 0;
            gbc.gridy = row;
            JLabel lblField = new JLabel(f[0]);
            lblField.setFont(new Font("Tahoma", Font.BOLD, 14));
            detailsPanel.add(lblField, gbc);

            gbc.gridx = 1;
            JLabel lblValue = new JLabel(f[1]);
            lblValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
            detailsPanel.add(lblValue, gbc);
            row++;
        }

        JScrollPane scroll = new JScrollPane(detailsPanel);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        scroll.setBackground(Color.WHITE);
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
