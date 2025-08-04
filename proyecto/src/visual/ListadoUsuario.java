package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logico.Bolsa;
import logico.Session;
import logico.Usuario;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ListadoUsuario extends JDialog {

    private static JTable table;
    private static DefaultTableModel modelo;
    private JComboBox cbxFiltro;
    private Usuario selected = null;

    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnDetalles;
    private JButton cancelButton;

    public ListadoUsuario() {
        setTitle("Listado de Usuarios");
        setResizable(false);
        setBounds(100, 100, 933, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        JPanel filtroPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblFiltro = new JLabel("Filtrar por:");
        lblFiltro.setFont(new Font("Tahoma", Font.PLAIN, 16));
        filtroPanel.add(lblFiltro);

        cbxFiltro = new JComboBox();
        cbxFiltro.setModel(new DefaultComboBoxModel(new String[] { "<Seleccione>", "Empleados", "Desempleados" }));
        cbxFiltro.setFont(new Font("Tahoma", Font.PLAIN, 14));
        filtroPanel.add(cbxFiltro);

        mainPanel.add(filtroPanel, BorderLayout.NORTH);

        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que las celdas no sean editables
            }
        };

        modelo.setColumnIdentifiers(new String[] {
            "Cédula", "Nombre", "Apellidos", "Contacto", "Sexo", "Tipo de trabajo"
        });

        table = new JTable(modelo);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnModificar = new JButton("Modificar");
        btnModificar.setEnabled(false);
        buttonPane.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false);
        // Deshabilitar para usuario tipo USER desde inicio
        if (Session.USER.equalsIgnoreCase(Session.tipoUsuario)) {
            btnEliminar.setEnabled(false);
        }
        buttonPane.add(btnEliminar);

        btnDetalles = new JButton("Detalles");
        btnDetalles.setEnabled(false);
        buttonPane.add(btnDetalles);

        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.getSelectedRow();
                if (index >= 0) {
                    selected = Bolsa.getInstance().buscarEmpleadoByCedula(
                            table.getValueAt(index, 0).toString());

                    btnModificar.setEnabled(true);
                    btnDetalles.setEnabled(true);

                    if (!Session.USER.equalsIgnoreCase(Session.tipoUsuario)) {
                        btnEliminar.setEnabled(true);
                    } else {
                        btnEliminar.setEnabled(false);
                    }
                }
            }
        });

        btnModificar.addActionListener(e -> {
            if (selected != null) {
                RegEmpleado update = new RegEmpleado(selected);
                update.setModal(true);
                update.setVisible(true);

                try {
                    Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                loadUsuario(0);
                table.clearSelection();
                resetButtons();
                selected = null;
            }
        });

        btnEliminar.addActionListener(e -> {
            if (selected != null) {
                int option = JOptionPane.showConfirmDialog(this,
                        "¿Está seguro que desea eliminar el usuario con cédula: " + selected.getCedula() + "?",
                        "Eliminar", JOptionPane.WARNING_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    Bolsa.getInstance().removeUser(selected.getCedula());

                    try {
                        Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    loadUsuario(0);
                    JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente.",
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                    table.clearSelection();
                    resetButtons();
                    selected = null;
                }
            }
        });

        btnDetalles.addActionListener(e -> {
            if (selected != null) {
                DetallesUsuario detalle = new DetallesUsuario(selected);
                detalle.setModal(true);
                detalle.setVisible(true);
            }
        });

        cbxFiltro.addActionListener(e -> loadUsuario(cbxFiltro.getSelectedIndex()));

        loadUsuario(0);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(180);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(150);
    }

    private void resetButtons() {
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnDetalles.setEnabled(false);
    }

    public static void loadUsuario(int seleccion) {
        if (modelo == null || table == null) return;

        modelo.setRowCount(0);
        Object[] row = new Object[table.getColumnCount()];

        switch (seleccion) {
            case 0:
                for (Usuario aux : Bolsa.getInstance().getMisUsers()) {
                    row[0] = aux.getCedula();
                    row[1] = aux.getNombre();
                    row[2] = aux.getApellido();
                    row[3] = aux.getContacto();
                    row[4] = aux.getSexo();
                    row[5] = aux.getTipoTrabajo();
                    modelo.addRow(row);
                }
                break;
            case 1:
                for (Usuario aux : Bolsa.getInstance().getMisUsers()) {
                    if (aux.isEstado()) {
                        row[0] = aux.getCedula();
                        row[1] = aux.getNombre();
                        row[2] = aux.getApellido();
                        row[3] = aux.getContacto();
                        row[4] = aux.getSexo();
                        row[5] = aux.getTipoTrabajo();
                        modelo.addRow(row);
                    }
                }
                break;
            case 2:
                for (Usuario aux : Bolsa.getInstance().getMisUsers()) {
                    if (!aux.isEstado()) {
                        row[0] = aux.getCedula();
                        row[1] = aux.getNombre();
                        row[2] = aux.getApellido();
                        row[3] = aux.getContacto();
                        row[4] = aux.getSexo();
                        row[5] = aux.getTipoTrabajo();
                        modelo.addRow(row);
                    }
                }
                break;
        }

        table.setModel(modelo);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(180);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(150);
    }
}
