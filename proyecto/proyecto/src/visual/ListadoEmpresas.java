package visual;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import logico.Bolsa;
import logico.Empresa;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ListadoEmpresas extends JDialog {

    private DefaultTableModel model;
    private JTable table;
    private Bolsa miBolsa;

    public ListadoEmpresas(Frame parent, Bolsa bolsa) {
        super(parent, "Listado de Empresas", true);
        this.miBolsa = bolsa;

        setSize(800, 400);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());

        String[] headers = { "Nombre", "RNC", "Área", "Contacto", "Provincia" };
        model = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  
            }
        };
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);

        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrar);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        cargarEmpresas(miBolsa.getMisEmpresas());

        btnModificar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String rnc = (String) model.getValueAt(selectedRow, 1);
                Empresa emp = miBolsa.buscarEmpresaByCode(rnc);
                if (emp != null) {
                    RegEmpresa reg = new RegEmpresa(miBolsa, emp);
                    reg.setModal(true);
                    reg.setVisible(true);
                    cargarEmpresas(miBolsa.getMisEmpresas());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una empresa para modificar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String rnc = (String) model.getValueAt(selectedRow, 1);
                int confirm = JOptionPane.showOptionDialog(
                    this,
                    "¿Está seguro que desea eliminar esta empresa?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Sí", "No"},
                    "No"
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    Empresa emp = miBolsa.buscarEmpresaByCode(rnc);
                    if (emp != null) {
                        miBolsa.getMisEmpresas().remove(emp);
                        cargarEmpresas(miBolsa.getMisEmpresas());
                        JOptionPane.showMessageDialog(this, "Empresa eliminada correctamente.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una empresa para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnCerrar.addActionListener(e -> dispose());
    }

    private void cargarEmpresas(ArrayList<Empresa> empresas) {
        model.setRowCount(0);
        for (Empresa emp : empresas) {
            Object[] row = new Object[5];
            row[0] = emp.getNombre();
            row[1] = emp.getRNC();
            row[2] = emp.getArea();
            row[3] = emp.getContacto();
            row[4] = emp.getProvincia();
            model.addRow(row);
        }
    }
}