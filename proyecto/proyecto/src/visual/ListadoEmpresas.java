package visual;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logico.Bolsa;
import logico.Empresa;
import logico.Session;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class ListadoEmpresas extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private static JTable table;
    private static Object[] row;
    private static DefaultTableModel modelo;
    private Empresa selected = null;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton cancelButton;

    public ListadoEmpresas() {
        setTitle("Listado de Empresas");
        setResizable(false);
        setBounds(100, 100, 900, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPanel.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(12, 207, 860, 300);
        panel.add(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panel_1.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = table.getSelectedRow();
                if (index >= 0) {
                    selected = Bolsa.getInstance().buscarEmpresaByCode(table.getValueAt(index, 1).toString());
                    if (Session.tipoUsuario.equals(Session.ADMIN)) {
                        btnEliminar.setEnabled(true);
                        btnModificar.setEnabled(true);
                    } else {
                        btnEliminar.setEnabled(false);
                        btnModificar.setEnabled(false);
                    }
                }
            }
        });
        modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] header = { "Nombre", "RNC", "Área", "Contacto", "Provincia" };
        modelo.setColumnIdentifiers(header);
        table.setModel(modelo);
        scrollPane.setViewportView(table);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnModificar = new JButton("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(e -> {
            if (selected != null) {
                RegEmpresa reg = new RegEmpresa(selected);
                reg.setModal(true);
                reg.setVisible(true);

                try {
                    Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                loadEmpresas();
                table.clearSelection();
                selected = null;
                btnModificar.setEnabled(false);
                btnEliminar.setEnabled(false);

            }
        });
        buttonPane.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(e -> {
            if (selected != null) {
                String[] options = { "Eliminar", "Cancelar" };
                int confirm = JOptionPane.showOptionDialog(this,
                        "¿Está seguro que desea eliminar la empresa: " + selected.getNombre() + "?",
                        "Eliminar Empresa",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options[1]);

                if (confirm == 0) {
                    Bolsa.getInstance().removeEmpresa(selected.getRNC());

                    try {
                        Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(this, "Empresa eliminada exitosamente.");
                    loadEmpresas();
                    table.clearSelection();
                    btnModificar.setEnabled(false);
                    btnEliminar.setEnabled(false);
                    selected = null;
                }

            }
        });
        buttonPane.add(btnEliminar);
        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        loadEmpresas();
        if (Session.tipoUsuario.equals(Session.USER)) {
            btnModificar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }
    }

    public static void loadEmpresas() {
        modelo.setRowCount(0);
        row = new Object[5];
        ArrayList<Empresa> lista = Bolsa.getInstance().getMisEmpresas();
        for (Empresa emp : lista) {
            row[0] = emp.getNombre();
            row[1] = emp.getRNC();
            row[2] = emp.getArea();
            row[3] = emp.getContacto();
            row[4] = emp.getProvincia();
            modelo.addRow(row);
        }

        table.setModel(modelo);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(200);
        columnModel.getColumn(4).setPreferredWidth(150);
    }
}
