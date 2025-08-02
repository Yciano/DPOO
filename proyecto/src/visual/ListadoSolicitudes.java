package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logico.Bolsa;
import logico.Session;
import logico.Solicitud;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ListadoSolicitudes extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private static JTable table;
    private static DefaultTableModel modelo;
    private Solicitud selected = null;
    private JButton btnEliminar;
    private JButton btnCancelar;

    public ListadoSolicitudes() {
        setTitle("Listado de Solicitudes");
        setResizable(false);
        setBounds(100, 100, 900, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        modelo = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] header = { "ID", "Fecha", "Salario Esperado", "Tipo Trabajo", "Empleado" };
        modelo.setColumnIdentifiers(header);
        table.setModel(modelo);

        scrollPane.setViewportView(table);

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selected != null) {
                    int option = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro que desea eliminar la solicitud: " + selected.getId() + "?",
                        "Eliminar", JOptionPane.WARNING_MESSAGE);
                    if (option == JOptionPane.OK_OPTION) {
                        Bolsa.getInstance().getMisSolicitudes().remove(selected);
                        
                        try {
                    		Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
                    	} catch (IOException ex) {
                    		ex.printStackTrace();
                    	}
                        
                        cargarSolicitudes();
                        JOptionPane.showMessageDialog(null, "Solicitud eliminada.", "Información", JOptionPane.INFORMATION_MESSAGE);
                        selected = null;
                        btnEliminar.setEnabled(false);
                    }
                }
            }
        });
        buttonPane.add(btnEliminar);

        btnCancelar = new JButton("Cerrar");
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);

        table.getSelectionModel().addListSelectionListener(e -> {
            int index = table.getSelectedRow();
            if (index >= 0) {
                String id = (String) modelo.getValueAt(index, 0);
                selected = Bolsa.getInstance().buscarSolicitudByID(id);
                if (!Session.tipoUsuario.equals(Session.USER)) {
	                btnEliminar.setEnabled(true); 
	            }              
            }
        });

        cargarSolicitudes();
    }

    private void cargarSolicitudes() {
        modelo.setRowCount(0);
        for (Solicitud sol : Bolsa.getInstance().getMisSolicitudes()) {
            Object[] fila = new Object[5];
            fila[0] = sol.getId();
            fila[1] = sol.getFecha();
            fila[2] = "RD$ " + sol.getSalario();
            fila[3] = (sol.getVacante() != null) ? sol.getVacante().getIdentificador() : "Sin asignar";
            fila[4] = sol.getUser().getNombre() + " " + sol.getUser().getApellido();
            modelo.addRow(fila);
        }
    }


    public static void main(String[] args) {
        try {
            ListadoSolicitudes dialog = new ListadoSolicitudes();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
