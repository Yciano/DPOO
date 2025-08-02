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
import logico.Empresa;
import logico.Session;
import logico.Usuario;
import logico.Vacante;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoVacantes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private static DefaultTableModel modelo;
	public  Vacante selected = null;
	private JTextField txtRnc;
	private JComboBox cbxArea;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private int modo = 0;

	//AUN NO ESTA TERMINADO
	
	
	
	
	
	
	
	public static void main(String[] args) {
		try {
			ListadoVacantes dialog = new ListadoVacantes(0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Mode = 0 (Mostrar listado normal)
	//Mode = 1 (Mostrar listado para seleccionar)
	public ListadoVacantes(int mode) {
		modo = mode;
		setTitle("Listado de Vacantes");
		setResizable(false);
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 125, 877, 388);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane);
				{
					table = new JTable();

					table.addMouseListener(new MouseAdapter() {
						
						public void mouseClicked(MouseEvent e) {
							int index = table.getSelectedRow();
							if (index >= 0) {
								selected = Bolsa.getInstance()
								.buscarVacanteByID(table.getValueAt(index, 0).toString());
								if (!Session.tipoUsuario.equals(Session.USER)) {
					                btnEliminar.setEnabled(true);
					                btnModificar.setEnabled(true);
					            }
							}
						}
					});
					modelo = new DefaultTableModel() {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};

					String[] header = { "Identificador", "Tipo de trabajo", "Tipo de empleado", "Estado"};
					modelo.setColumnIdentifiers(header);
					table.setModel(modelo);
					scrollPane.setViewportView(table);
				}
			}
		}
		
		JLabel lblNewLabel = new JLabel("RNC de Empresa:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(12, 62, 130, 16);
		contentPanel.add(lblNewLabel);
		
		txtRnc = new JTextField();
		txtRnc.setBounds(148, 60, 130, 22);
		contentPanel.add(txtRnc);
		txtRnc.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Area: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(623, 63, 56, 16);
		contentPanel.add(lblNewLabel_1);
		
		cbxArea = new JComboBox();
		cbxArea.setEnabled(false);
		cbxArea.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>"}));
		cbxArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbxArea.setBounds(674, 61, 161, 22);
		contentPanel.add(cbxArea);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnModificar = new JButton("Modificar");
			if(modo == 1) {
				btnModificar.setVisible(false);
			}
			btnModificar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			buttonPane.add(btnModificar);
			{
				btnEliminar = new JButton("Eliminar");
				if(modo == 1) {
					btnEliminar.setText("Seleccionar");
				}
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected != null) {
							btnEliminar.setEnabled(true);
							if(modo == 0) {
							int option = JOptionPane.showConfirmDialog(null,
									"¿Esta seguro que desea eliminar la vacante con identificador: "
											+ selected.getIdentificador() + "?",
									"Eliminar", JOptionPane.WARNING_MESSAGE);
							if (option == JOptionPane.OK_OPTION) {
								Bolsa.getInstance().removeUser(selected.getIdentificador());
								loadVacante(0);
								JOptionPane.showMessageDialog(null, "Vacante eliminada exitosamente.",
										"Información", JOptionPane.INFORMATION_MESSAGE);
								table.clearSelection();
								btnModificar.setEnabled(false);
							} else {
								table.clearSelection();
								btnModificar.setEnabled(false);
							}
							}
							else {
								dispose();
								
							}
					}
					
					btnEliminar.setEnabled(false);
					}
				});
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
			loadVacante(0);

		}
	}
	
	public static void loadVacante(int selection) {
		modelo.setRowCount(0);
		row = new Object[table.getColumnCount()];
		for (Vacante aux : Bolsa.getInstance().getMisVacantes()) {
			row[0] = aux.getIdentificador();
			row[1] = aux.getRequisito().getTipoTrabajo();
			row[2] = aux.getRequisito().getTipoEmpleado();
			if(aux.isEstado()) {
				row[3] = "Activa";
			}else
			{
				row[3] = "Pendiente";
			}
			modelo.addRow(row);
		}

		table.setModel(modelo);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(200);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(180);
		

	}
	
	
}
