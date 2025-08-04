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

    private JTable table;
    private DefaultTableModel modelo;
    private JComboBox<String> cbxProvincia;
    private Empresa selected = null;

    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnDetalles;
    private JButton cancelButton;

    public ListadoEmpresas() {
        setTitle("Listado de Empresas");
        setResizable(false);
        setBounds(100, 100, 933, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(mainPanel, BorderLayout.CENTER);
 
        JPanel filtroPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblFiltro = new JLabel("Filtrar por Provincia:");
        lblFiltro.setFont(new Font("Tahoma", Font.PLAIN, 16));
        filtroPanel.add(lblFiltro);

        cbxProvincia = new JComboBox<>();
        cbxProvincia.setModel(new DefaultComboBoxModel<>(new String[] {
            "<Seleccione>", "Azua", "Bahoruco", "Barahona", "Dajabón", "Distrito Nacional",
            "Duarte", "Elías Piña", "El Seibo", "Espaillat", "Hato Mayor", "Hermanas Mirabal",
            "Independencia", "La Altagracia", "La Romana", "La Vega", "María Trinidad Sánchez",
            "Monseñor Nouel", "Monte Cristi", "Monte Plata", "Pedernales", "Peravia",
            "Puerto Plata", "Samaná", "San Cristóbal", "San José de Ocoa", "San Juan",
            "San Pedro de Macorís", "Sánchez Ramírez", "Santiago", "Santiago Rodríguez", "Valverde"
        }));
        cbxProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
        filtroPanel.add(cbxProvincia);

        mainPanel.add(filtroPanel, BorderLayout.NORTH);
 
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.setColumnIdentifiers(new String[] { "Nombre", "RNC", "Área", "Contacto", "Provincia" });
        modelo.setColumnCount(5);  

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
        buttonPane.add(btnEliminar);

        btnDetalles = new JButton("Detalles");
        btnDetalles.setEnabled(false);
        buttonPane.add(btnDetalles);

        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);
 
        cbxProvincia.addActionListener(e -> aplicarFiltro());
 
        loadEmpresas(null);
 
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(200);
        columnModel.getColumn(4).setPreferredWidth(150);
    }

    private void aplicarFiltro() {
        String provincia = (String) cbxProvincia.getSelectedItem();
        if (provincia == null || provincia.equals("<Seleccione>")) {
            loadEmpresas(null);
        } else {
            loadEmpresas(provincia);
        }
    }

    private void loadEmpresas(String provinciaFiltro) {
        modelo.setRowCount(0);
        ArrayList<Empresa> lista = Bolsa.getInstance().getMisEmpresas();

        for (Empresa emp : lista) {
            boolean coincideProvincia = (provinciaFiltro == null) || emp.getProvincia().equalsIgnoreCase(provinciaFiltro);

            if (coincideProvincia) {
                modelo.addRow(new Object[] {
                    emp.getNombre(),
                    emp.getRNC(),
                    emp.getArea(),
                    emp.getContacto(),
                    emp.getProvincia()
                });
            }
        }
    }
}
