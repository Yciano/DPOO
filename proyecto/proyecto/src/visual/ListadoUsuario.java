package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logico.Bolsa;
import logico.Empresa;
import logico.Usuario;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ListadoUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private static DefaultTableModel modelo;
	private Usuario selected = null;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton cancelButton;
	private JButton btnDetalles;
	
	
	public static void main(String[] args) {
		try {
			ListadoUsuario dialog = new ListadoUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public ListadoUsuario() {
		setTitle("Listado de Usuarios");
		setResizable(false);
		setBounds(100, 100, 933, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(12, 207, 893, 288);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_1.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();

				table.addMouseListener(new MouseAdapter() {
					
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();
						if (index >= 0) {
							selected = Bolsa.getInstance()
							.buscarEmpleadoByCedula(table.getValueAt(index, 0).toString());
							btnEliminar.setEnabled(true);
							btnModificar.setEnabled(true);
							btnDetalles.setEnabled(true);
						}
					}
				});
				modelo = new DefaultTableModel() {
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};

				String[] header = { "Cédula", "Nombre", "Apellidos", "Contacto", "Sexo", "Tipo de trabajo" };
				modelo.setColumnIdentifiers(header);
				table.setModel(modelo);
			}
			
			
			scrollPane.setViewportView(table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegEmpleado update = new RegEmpleado(selected);
						update.setModal(true);
						update.setVisible(true);

						try {
							Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
						} catch (IOException ex) {
							ex.printStackTrace();
						}

						loadUsuario();
						table.clearSelection();
						btnModificar.setEnabled(false);
						btnEliminar.setEnabled(false);
						btnDetalles.setEnabled(false);
						selected = null;
					}
				});
				btnModificar.setEnabled(false);
				buttonPane.add(btnModificar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (selected != null) {
								btnEliminar.setEnabled(true);

								int option = JOptionPane.showConfirmDialog(null,
										"¿Esta seguro que desea eliminar el usuario con cédula: "
												+ selected.getCedula() + "?",
										"Eliminar", JOptionPane.WARNING_MESSAGE);
								if (option == JOptionPane.OK_OPTION) {
									Bolsa.getInstance().removeUser(selected.getCedula());

									try {
										Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
									} catch (IOException e1) {
										e1.printStackTrace();
									}

									loadUsuario();
									JOptionPane.showMessageDialog(null, "Publicación eliminada exitosamente.",
											"Información", JOptionPane.INFORMATION_MESSAGE);
									table.clearSelection();
									btnModificar.setEnabled(false);
									btnDetalles.setEnabled(false);
								}
								else {
									table.clearSelection();
									btnModificar.setEnabled(false);
									btnDetalles.setEnabled(false);
								}
							
						}
						
						btnEliminar.setEnabled(false);

					}
				});
				btnEliminar.setEnabled(false);
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
			}
			btnDetalles = new JButton("Detalles");
			btnDetalles.setEnabled(false);
			btnDetalles.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (selected != null) {
						DetallesUsuario detalle = new DetallesUsuario(selected);
						detalle.setModal(true);
						detalle.setVisible(true);
					}
				}
			});
			buttonPane.add(btnDetalles);

			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			loadUsuario();
		}
	}
	
	public static void loadUsuario() {
		modelo.setRowCount(0);
		row = new Object[table.getColumnCount()];
		for (Usuario aux : Bolsa.getInstance().getMisUsers()) {
			row[0] = aux.getCedula();
			row[1] = aux.getNombre();
			row[2] = aux.getApellido();
			row[3] = aux.getContacto();
			row[4] = aux.getSexo();
			row[5] = aux.getTipoTrabajo();
			modelo.addRow(row);
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
