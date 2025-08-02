package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logico.Bolsa;
import logico.Empresa;
import logico.Usuario;
import logico.Vacante;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Pendiente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable tableEmp;
	private static JTable tableVacantes;
	private static JTable tableCandi;
	private static Object[] rowEmp;
	private static Object[] rowVac;
	private static Object[] rowSol;
	private static DefaultTableModel modeloEmp;
	private static DefaultTableModel modeloVac;
	private static DefaultTableModel modeloSol;
	public Empresa selectedEmp;
	public Vacante selectedVac;
	public Usuario selectedUser;
	private JButton btnContratar;
	private JButton btnCancelar;
	private JTextField txtRnc;
	private JButton btnBuscar;
	private JButton btnDetalles;
	private JButton btnCv;

	
	public static void main(String[] args) {
		try {
			Pendiente dialog = new Pendiente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pendiente() {
		setBounds(100, 100, 1223, 1000);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel panelEmp = new JPanel();
			panelEmp.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelEmp.setBounds(12, 24, 1171, 352);
			panel.add(panelEmp);
			panelEmp.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(12, 73, 1124, 266);
				panelEmp.add(panel_1);
				panel_1.setLayout(new BorderLayout(0, 0));
				
					JScrollPane scrollPane = new JScrollPane();
					panel_1.add(scrollPane, BorderLayout.CENTER);{
					tableEmp = new JTable();
					tableEmp.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int index = tableEmp.getSelectedRow();
							if (index >= 0) {
								selectedEmp = Bolsa.getInstance()
										.buscarEmpresaByCode(tableEmp.getValueAt(index, 0).toString());
								if(selectedEmp != null)	
									loadVacante(selectedEmp);
							}
						}
					});
					modeloEmp = new DefaultTableModel() {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					String[] header = { "Nombre", "RNC", "Área", "Contacto", "Provincia" };
					modeloEmp.setColumnIdentifiers(header);
					tableEmp.setModel(modeloEmp);
					scrollPane.setViewportView(tableEmp);
					}				
			}
			
			JLabel lblNewLabel = new JLabel("Buscar por RNC: ");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel.setBounds(12, 29, 118, 16);
			panelEmp.add(lblNewLabel);
			
			txtRnc = new JTextField();
			txtRnc.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					
					char c = e.getKeyChar();
	                if (!Character.isDigit(c)) {
	                    e.consume();
	                    return;
	                }

	                String digits = txtRnc.getText().replaceAll("[^\\d]", "");
	                if (digits.length() >= 9) {
	                    e.consume();
	                    return;
	                }

	                SwingUtilities.invokeLater(() -> {
	                    String raw = txtRnc.getText().replaceAll("[^\\d]", "");
	                    StringBuilder formatted = new StringBuilder();
	                    for (int i = 0; i < raw.length(); i++) {
	                        formatted.append(raw.charAt(i));
	                        if (i == 2 || i == 7) {
	                            formatted.append("-");
	                        }
	                    }
	                    txtRnc.setText(formatted.toString());
	                });
				}
			});
			txtRnc.setBounds(140, 26, 166, 22);
			panelEmp.add(txtRnc);
			txtRnc.setColumns(10);
			
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(validarCampos(txtRnc.getText())) {
						loadEmpresas(txtRnc.getText());
					}
				}
			});
			btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnBuscar.setBounds(327, 26, 97, 25);
			panelEmp.add(btnBuscar);
			
			JPanel panelVacante = new JPanel();
			panelVacante.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelVacante.setBounds(12, 441, 541, 439);
			panel.add(panelVacante);
			panelVacante.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(12, 13, 517, 370);
			panelVacante.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_1.add(scrollPane, BorderLayout.CENTER);
			
			tableVacantes = new JTable();
			tableVacantes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = tableVacantes.getSelectedRow();
					if (index >= 0) {
						selectedVac = Bolsa.getInstance()
								.buscarVacanteByID(tableVacantes.getValueAt(index, 0).toString());
						btnDetalles.setEnabled(true);
						/*if(selectedUser!= null)	
							loadUsuario(selectedVac);*/
					}
				}
			});
			
			modeloVac = new DefaultTableModel() {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			String[] header = { "Identificador", "Tipo de trabajo", "Tipo de empleado"};
			modeloVac.setColumnIdentifiers(header);
			tableVacantes.setModel(modeloVac);
			scrollPane.setViewportView(tableVacantes);
			
			btnDetalles = new JButton("Detalles");
			btnDetalles.setEnabled(false);
			btnDetalles.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnDetalles.setBounds(386, 401, 143, 25);
			panelVacante.add(btnDetalles);
			
			JPanel panelUser = new JPanel();
			panelUser.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelUser.setBounds(627, 441, 556, 439);
			panel.add(panelUser);
			panelUser.setLayout(null);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(12, 13, 532, 365);
			panelUser.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_1 = new JScrollPane();
			panel_2.add(scrollPane_1, BorderLayout.CENTER);
			
			tableCandi = new JTable();
			tableCandi.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = tableCandi.getSelectedRow();
					if (index >= 0) {
						selectedUser = Bolsa.getInstance()
								.buscarEmpleadoByCedula(tableCandi.getValueAt(index, 0).toString());
						btnContratar.setEnabled(true);
						btnCv.setEnabled(true);
					}
					
				}
			});
			
			modeloSol = new DefaultTableModel() {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			String[] header3 = { "Cédula","Nombre", "Tipo de empleado", "Match"};
			modeloSol.setColumnIdentifiers(header3);
			tableCandi.setModel(modeloSol);
			scrollPane_1.setViewportView(tableCandi);
			
			btnCv = new JButton("Ver CV");
			btnCv.setEnabled(false);
			btnCv.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnCv.setBounds(401, 401, 143, 25);
			panelUser.add(btnCv);
			
			JLabel lblNewLabel_1 = new JLabel("VACANTES");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel_1.setBounds(211, 405, 113, 16);
			panel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("CANDIDATOS");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel_2.setBounds(826, 407, 151, 16);
			panel.add(lblNewLabel_2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnContratar = new JButton("Contratar");
				btnContratar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				btnContratar.setEnabled(false);
				btnContratar.setActionCommand("OK");
				buttonPane.add(btnContratar);
				getRootPane().setDefaultButton(btnContratar);
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
			loadEmpresas(null);
		}
	}
	
	
	
	public static void loadEmpresas(String rnc) {
		modeloEmp.setRowCount(0);
		rowEmp = new Object[tableEmp.getColumnCount()];
		
		if(rnc != null) {
			Empresa aux =  Bolsa.getInstance().buscarEmpresaByCode(rnc);
			rowEmp[0] = aux.getNombre();
			rowEmp[1] = aux.getRNC();
			rowEmp[2] = aux.getArea();
			rowEmp[3] = aux.getContacto();
			rowEmp[4] = aux.getProvincia();
		}
		ArrayList<Empresa> lista = Bolsa.getInstance().getMisEmpresas();
		for (Empresa emp : lista) {
			rowEmp[0] = emp.getNombre();
			rowEmp[1] = emp.getRNC();
			rowEmp[2] = emp.getArea();
			rowEmp[3] = emp.getContacto();
			rowEmp[4] = emp.getProvincia();
			modeloEmp.addRow(rowEmp);
		}

		tableEmp.setModel(modeloEmp);
		tableEmp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableEmp.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = tableEmp.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(200);
		columnModel.getColumn(1).setPreferredWidth(200);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(200);
	}
	
	
	/*public static void loadUsuario(Vacante seleccionado) {
		modeloSol.setRowCount(0);
		rowSol = new Object[tableCandi.getColumnCount()];
		ArrayList<Usuario> candidatos = Bolsa.getInstance().match();
		for (int i = 0; i < candidatos.size();i++) {
				rowSol[0] = candidatos.get(i).getCedula();
				rowSol[1] = candidatos.get(i).getNombre();
				rowSol[2] = candidatos.get(i).getTipoTrabajo();
				rowSol[3] = candidatos.get(i).getMatch() + "%";
				modeloSol.addRow(rowSol);	
			
		}

		tableCandi.setModel(modeloSol);
		tableCandi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableCandi.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = tableCandi.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(150);
		columnModel.getColumn(3).setPreferredWidth(180);
		columnModel.getColumn(4).setPreferredWidth(150);
		columnModel.getColumn(5).setPreferredWidth(150);

	}*/
	
	public static void loadVacante(Empresa seleccionado) {
		modeloVac.setRowCount(0);
		rowVac = new Object[tableVacantes.getColumnCount()];
		for (Vacante aux : Bolsa.getInstance().getMisVacantes()) {
			if(aux.getIDCompania().equalsIgnoreCase(seleccionado.getRNC())) {
				rowVac[0] = aux.getIdentificador();
				rowVac[1] = aux.getRequisito().getTipoTrabajo();
				rowVac[2] = aux.getRequisito().getTipoEmpleado();
				if(aux.isEstado()) {
					rowVac[3] = "Activa";
				}else
				{
					rowVac[3] = "Pendiente";
				}
				modeloVac.addRow(rowVac);
			}
			
		}

		tableVacantes.setModel(modeloVac);
		tableVacantes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableVacantes.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = tableVacantes.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(150);
		columnModel.getColumn(3).setPreferredWidth(180);
		

	}
	
	private boolean validarCampos(String Rnc) {
		boolean aux = true;
		if (txtRnc.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos antes de continuar.", "Error",
					JOptionPane.ERROR_MESSAGE);
			aux = false;
		}
		else if (Bolsa.getInstance().buscarEmpresaByCode(Rnc) == null) {
			JOptionPane.showMessageDialog(null, "Esta empresa no se encuentra registrada.", "Error",
					JOptionPane.ERROR_MESSAGE);
			aux = false;
		
		
	}
		return aux;
	}
	
}
	
	
