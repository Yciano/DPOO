package visual;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import logico.Vacante;
import logico.Requisito;

public class DetallesVacante extends JDialog {

    public DetallesVacante(Vacante vacante) {
        setTitle("Detalles de la Vacante");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        JPanel content = new JPanel(new BorderLayout(15, 15));
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        content.setBackground(Color.WHITE);
        setContentPane(content);

        JLabel title = new JLabel("Informaci�n de la Vacante", SwingConstants.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 22));
        content.add(title, BorderLayout.NORTH);

        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Requisito req = vacante.getRequisito();
        String[][] fields = {
            {"Identificador:", vacante.getIdentificador()},
            {"RNC Empresa:", vacante.getIDCompania()},
            {"Fecha publicaci�n:", sdf.format(vacante.getFecha())},
            {"Estado:", vacante.isEstado() ? "Activa" : "Pendiente"},
            {"Tipo de trabajo:", req.getTipoTrabajo()},
            {"Tipo de empleado:", req.getTipoEmpleado()}
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

        if ("Universitario".equals(req.getTipoEmpleado())) {
            addField(detailsPanel, gbc, row++, "�rea acad�mica:", req.getAreaUni());
            addField(detailsPanel, gbc, row++, "Carrera:", req.getCarrera());
        } else if ("Tecnico".equals(req.getTipoEmpleado())) {
            addField(detailsPanel, gbc, row++, "�rea t�cnica:", req.getTecnico());
            addField(detailsPanel, gbc, row++, "Experiencia (a�os):", String.valueOf(req.getAniosExperiencia()));
        } else if ("Obrero".equals(req.getTipoEmpleado())) {
            // Mostrar campo Habilidades, incluso si no hay ninguna
            String habs = (req.getMisHabilidades() == null || req.getMisHabilidades().isEmpty())
                         ? "Ninguna"
                         : String.join(", ", req.getMisHabilidades());
            addField(detailsPanel, gbc, row++, "Habilidades:", habs);
            // Experiencia en a�os
            addField(detailsPanel, gbc, row++, "Experiencia (a�os):", String.valueOf(req.getAniosExperiencia()));
        }

        addField(detailsPanel, gbc, row++, "Edad m�nima:", String.valueOf(req.getEdad()));
        addField(detailsPanel, gbc, row++, "Sexo requiere:", req.getSexo());
        addField(detailsPanel, gbc, row++, "Veh�culo:", req.isVeh() ? "S�" : "No");
        addField(detailsPanel, gbc, row++, "Mudarse:", req.isFueraCity() ? "S�" : "No");
        addField(detailsPanel, gbc, row++, "Prioridad:", vacante.getPrioridad());
        addField(detailsPanel, gbc, row++, "Solicitudes:", String.valueOf(vacante.getMisSolicitudes().size()));

        JScrollPane scroll = new JScrollPane(detailsPanel);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        content.add(scroll, BorderLayout.CENTER);

        JPanel descPanel = new JPanel(new BorderLayout(5,5));
        descPanel.setBackground(Color.WHITE);
        JLabel descLabel = new JLabel("Descripci�n:");
        descLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        JTextArea descArea = new JTextArea(vacante.getDescripcion());
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setEditable(false);
        descArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JScrollPane descScroll = new JScrollPane(descArea);
        descScroll.setPreferredSize(new Dimension(0, 100));
        descPanel.add(descLabel, BorderLayout.NORTH);
        descPanel.add(descScroll, BorderLayout.CENTER);
        content.add(descPanel, BorderLayout.SOUTH);
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int row, String name, String value) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel lblName = new JLabel(name);
        lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblName, gbc);
        gbc.gridx = 1;
        JLabel lblVal = new JLabel(value);
        lblVal.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(lblVal, gbc);
    }
}
