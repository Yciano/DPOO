package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logico.Bolsa;
import logico.Contrato;
import logico.Empresa;
import logico.Usuario;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ListaContratos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private static DefaultTableModel modelo;
	private Contrato selected = null;
	private JButton btnCancelar;
	private JTextField txtRNC;
	private JButton btnBuscar;
	private JButton btnFinalizar;
	private JButton btnDetalles;
	

	
	public static void main(String[] args) {
		try {
			ListaContratos dialog = new ListaContratos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public ListaContratos() {
		setBounds(100, 100, 1001, 666);
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
			panel_1.setBounds(12, 87, 949, 474);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_1.add(scrollPane, BorderLayout.CENTER);
			
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int index = table.getSelectedRow();
					if (index >= 0) {
						selected = Bolsa.getInstance()
						.buscarContratoById(table.getValueAt(index, 0).toString());
						btnDetalles.setEnabled(true);
						btnFinalizar.setEnabled(true);
					}
				}
				
				
			});
			
			modelo = new DefaultTableModel() {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};

			String[] header = { "Identificador", "Usuario", "Puesto", "Fecha de contrato" };
			modelo.setColumnIdentifiers(header);
			table.setModel(modelo);
			scrollPane.setViewportView(table);
			{
				JLabel lblNewLabel = new JLabel("Filtrar por RNC:");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblNewLabel.setBounds(12, 40, 122, 34);
				panel.add(lblNewLabel);
			}
			{
				txtRNC = new JTextField();
				txtRNC.setForeground(Color.LIGHT_GRAY);
				txtRNC.setText("###-#####-#");
				txtRNC.addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent e) {
						 if (txtRNC.getText().replaceAll("[^\\d]", "").isEmpty()) {
			                    txtRNC.setText("###-#####-#");
			                    txtRNC.setForeground(Color.LIGHT_GRAY);
			                }
						
					}
					@Override
					public void focusGained(FocusEvent e) {
						 if (txtRNC.getText().equals("###-#####-#")) {
			                    txtRNC.setText("");
			                    txtRNC.setForeground(Color.BLACK);
			                }
					}
				});
				txtRNC.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						
						 char c = e.getKeyChar();
			                if (!Character.isDigit(c)) {
			                    e.consume();
			                    return;
			                }

			                String digits = txtRNC.getText().replaceAll("[^\\d]", "");
			                if (digits.length() >= 9) {
			                    e.consume();
			                    return;
			                }

			                SwingUtilities.invokeLater(() -> {
			                    String raw = txtRNC.getText().replaceAll("[^\\d]", "");
			                    StringBuilder formatted = new StringBuilder();
			                    for (int i = 0; i < raw.length(); i++) {
			                        formatted.append(raw.charAt(i));
			                        if (i == 2 || i == 7) {
			                            formatted.append("-");
			                        }
			                    }
			                    txtRNC.setText(formatted.toString());
			                });
					}
				});
				txtRNC.setBounds(136, 47, 155, 22);
				panel.add(txtRNC);
				txtRNC.setColumns(10);
			}
			{
				btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(validarCampos()) {
							loadContrato(txtRNC.getText());
							
						}
					}
				});
				btnBuscar.setBounds(305, 46, 97, 25);
				panel.add(btnBuscar);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnDetalles = new JButton("Detalles");
				btnDetalles.setEnabled(false);
				btnDetalles.addActionListener(e -> {
				    if (selected != null) {
				        DetallesContrato detalle = new DetallesContrato(selected);
				        detalle.setModal(true);
				        detalle.setVisible(true);
				    }
				});

				{
					btnFinalizar = new JButton("Finalizar contrato");
					btnFinalizar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (selected != null) {
								btnDetalles.setEnabled(true);
								btnFinalizar.setEnabled(true);


								int option = JOptionPane.showConfirmDialog(null,
										"¿Esta seguro que desea finalizar el contrato con identificador: "
												+ selected.getId() + "?",
										"Finalizar contrato", JOptionPane.WARNING_MESSAGE);
								if (option == JOptionPane.OK_OPTION) {
									Bolsa.getInstance().finalizarContrato(selected.getId());
									loadContrato(null);

									try {
										Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
									} catch (IOException e1) {
										e1.printStackTrace();
									}

									loadContrato(null);
									JOptionPane.showMessageDialog(null, "Contrato finalizado exitosamente.",
											"Información", JOptionPane.INFORMATION_MESSAGE);
									table.clearSelection();
									btnFinalizar.setEnabled(false);
									btnDetalles.setEnabled(false);
								}
								else {
									table.clearSelection();
									btnFinalizar.setEnabled(false);
									btnDetalles.setEnabled(false);
								}
							
						}
						
							btnFinalizar.setEnabled(false);

						}
					});
					btnFinalizar.setEnabled(false);
					buttonPane.add(btnFinalizar);
				}
				btnDetalles.setActionCommand("OK");
				buttonPane.add(btnDetalles);
				getRootPane().setDefaultButton(btnDetalles);
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
		}
		loadContrato(null);
	}
	
	public static void loadContrato(String RNC) {
		modelo.setRowCount(0);
		row = new Object[table.getColumnCount()];
		
		if(RNC == null) {
			for (Contrato aux : Bolsa.getInstance().getMisContratos()) {
				if(aux.isEstado()) {
					row[0] = aux.getId();
					row[1] = aux.getUser().getNombre();
					row[2] = aux.getVacante().getPosicion();
					row[3] = aux.getFecha();
					modelo.addRow(row);
				}
				
			}
			
		}else {
			for (Contrato aux : Bolsa.getInstance().getMisContratos()) {
				if(aux.getVacante().getIDCompania().equalsIgnoreCase(RNC)) {
					row[0] = aux.getId();
					row[1] = aux.getUser().getNombre();
					row[2] = aux.getVacante().getPosicion();
					row[3] = aux.getFecha();
					modelo.addRow(row);
				}
				
			}
			
		}
			

		table.setModel(modelo);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(200);
		columnModel.getColumn(1).setPreferredWidth(250);
		columnModel.getColumn(2).setPreferredWidth(250);
		columnModel.getColumn(3).setPreferredWidth(245);
	

	}
	
	private boolean validarCampos() {
    	boolean aux = true;
    	Empresa emp = Bolsa.getInstance().buscarEmpresaByCode(txtRNC.getText());

    
    	if(txtRNC.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Debe de ingresar un RNC.", "Error", JOptionPane.ERROR_MESSAGE);
            aux = false;
    	}else if (emp == null) {
            JOptionPane.showMessageDialog(null, "Esta empresa no existe", "Error", JOptionPane.ERROR_MESSAGE);
            aux = false;
        }

        if (emp != null && emp.getMisContratos().size() == 0) {
            JOptionPane.showMessageDialog(null, "Esta empresa no posee contratos registrados.", "Error", JOptionPane.ERROR_MESSAGE);
            aux = false;
        }
        
    	
    	return aux;
    	
    	
    } 
	
}
