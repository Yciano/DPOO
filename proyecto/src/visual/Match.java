package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logico.Bolsa;
import logico.Empresa;
import logico.Solicitud;
import logico.Usuario;
import logico.Vacante;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Match extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable tableVac;
	private static JTable tableMatch;
	private static Object[] row1;
	private static Object[] row2;
	private static DefaultTableModel modelo1;
	private static DefaultTableModel modelo2;
    private Vacante selected1 = null;
    private Vacante selected2 = null;
	private JTextField txtRNC;
	private JButton btnMatch;
	private JButton btnSeleccionar;
	private JButton btnCancelar;
	private JButton btnDetalles;

	
	public static void main(String[] args) {
		try {
			Match dialog = new Match();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Match() {
		setBounds(100, 100, 1248, 1000);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 131, 1196, 297);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		tableVac = new JTable();
		tableVac.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableVac.getSelectedRow();
				if (index >= 0) {
					selected1 = Bolsa.getInstance()
					.buscarVacanteByID(tableVac.getValueAt(index, 0).toString());
					loadTableMatch(selected1);
					btnDetalles.setEnabled(true);
				}
				
			}
		});
		modelo1 = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String[] header = { "Identiificador", "ID Compañia","Posicion", "Fecha", "Provincia"};
		modelo1.setColumnIdentifiers(header);
		tableVac.setModel(modelo1);
		scrollPane.setViewportView(tableVac);
		
		JLabel lblNewLabel = new JLabel("VACANTES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setBounds(466, 25, 150, 32);
		panel.add(lblNewLabel);
		
		btnMatch = new JButton("Match");
		btnMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bolsa.getInstance().match();
				loadTableMatch(null);
                JOptionPane.showMessageDialog(null, "Emparejamiento actualizado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		btnMatch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMatch.setBounds(1046, 86, 162, 32);
		panel.add(btnMatch);
		
		JLabel lblNewLabel_1 = new JLabel("Filtrar por RNC:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(12, 86, 126, 16);
		panel.add(lblNewLabel_1);
		
		txtRNC = new JTextField();
		txtRNC.setBounds(150, 83, 126, 22);
		panel.add(txtRNC);
		txtRNC.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 564, 1196, 309);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);
		
		tableMatch = new JTable();
		tableMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableMatch.getSelectedRow();
				if (index >= 0) {
					selected2 = Bolsa.getInstance()
					.buscarVacanteByID(tableVac.getValueAt(index, 0).toString());
					btnSeleccionar
					.setEnabled(true);
					
				}
				
			}
		});
		modelo2 = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		String[] header2 = { "Identiificador", "Candidato 1", "Candidato 2", "Candidato 3"};
		modelo2.setColumnIdentifiers(header2);
		tableMatch.setModel(modelo2);
		scrollPane_1.setViewportView(tableMatch);
		
		JLabel lblNewLabel_2 = new JLabel("POSIBLES CANDIDATOS");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_2.setBounds(432, 501, 279, 32);
		panel.add(lblNewLabel_2);
		
		btnDetalles = new JButton("Ver m\u00E1s");
		btnDetalles.setBounds(1111, 441, 97, 25);
		panel.add(btnDetalles);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSeleccionar = new JButton("Seleccionar");
				btnSeleccionar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						Contratar aux = new Contratar(selected2);
						aux.setModal(true);
						aux.setVisible(true);
					}
				});
				btnSeleccionar.setEnabled(false);
				btnSeleccionar.setActionCommand("OK");
				buttonPane.add(btnSeleccionar);
				getRootPane().setDefaultButton(btnSeleccionar);
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
			
			loadTableVacante();
		}
	}
	public static void loadTableVacante() {
		modelo1.setRowCount(0);
		for (Vacante aux : Bolsa.getInstance().getMisVacantes()) {
			if(aux.isEstado()) {
				row1 = new Object[tableVac.getColumnCount()];
				row1[0] = aux.getIdentificador();
				row1[1] = aux.getIDCompania();
				row1[2] = aux.getPosicion();
				row1[3] = aux.getFecha();
				Empresa emp = Bolsa.getInstance().buscarEmpresaByCode(aux.getIDCompania());
				if(emp != null)
					row1[4] = emp.getProvincia();
				modelo1.addRow(row1);
			}
			
		}

		tableVac.setModel(modelo1);
		tableVac.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableVac.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = tableVac.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(250);
		columnModel.getColumn(1).setPreferredWidth(250);
		columnModel.getColumn(2).setPreferredWidth(300);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(193);

	}
	
	public static void loadTableMatch(Vacante vac) {
		modelo2.setRowCount(0);
		row2 = new Object[tableMatch.getColumnCount()];
		Bolsa.getInstance().match();
		
		if(vac != null) {
			row2 = new Object[tableMatch.getColumnCount()];
			row2[0] = vac.getIdentificador();
			if(vac.getMisSolicitudes().size() >  0 && vac.getMisSolicitudes().get(0) != null) {
				String cod = vac.getMisSolicitudes().get(0).getMatch() *10+ "%";
				row2[1] = vac.getMisSolicitudes().get(0).getUser().getNombre() + "- " + cod;


			}else {

				row2[1] = "Sin candidatos";
				row2[2] = "Sin candidatos";
				row2[3] = "Sin candidatos";
			}
			
			if(vac.getMisSolicitudes().size() >  1 && vac.getMisSolicitudes().get(1) != null) {
				String cod = vac.getMisSolicitudes().get(1).getMatch() *10+ "%";
				row2[2] = vac.getMisSolicitudes().get(1).getUser().getNombre() + "- " + cod;


			}else {

				row2[2] = "Sin candidatos";
				row2[3] = "Sin candidatos";
			}
			
			if(vac.getMisSolicitudes().size() >  2 && vac.getMisSolicitudes().get(2) != null) {
				String cod = vac.getMisSolicitudes().get(2).getMatch() *10+ "%";
				row2[3] = vac.getMisSolicitudes().get(2).getUser().getNombre() + "- " + cod;


			}else
				row2[3] = "Sin candidatos";

			modelo2.addRow(row2);

		}
		else {
		
		for (Vacante aux : Bolsa.getInstance().getMisVacantes()) {
			row2 = new Object[tableMatch.getColumnCount()];
			row2[0] = aux.getIdentificador();
			if(aux.isEstado()) {
				if(aux.getMisSolicitudes().size() >  0 && aux.getMisSolicitudes().get(0) != null && aux.getMisSolicitudes().get(0).getUser().isEstado()) {
					String cod = aux.getMisSolicitudes().get(0).getMatch() *10+ "%";
					row2[1] = aux.getMisSolicitudes().get(0).getUser().getNombre() + "- " + cod;

					
				}else {
					row2[1] = "Sin candidatos";
					row2[2] = "Sin candidatos";
					row2[3] = "Sin candidatos";

				}
				
				if(aux.getMisSolicitudes().size() >  1 && aux.getMisSolicitudes().get(1) != null&& aux.getMisSolicitudes().get(1).getUser().isEstado()) {
					String cod = aux.getMisSolicitudes().get(1).getMatch() *10+ "%";
					row2[2] = aux.getMisSolicitudes().get(1).getUser().getNombre() + "- " + cod;


				}else {
					row2[2] = "Sin candidatos";
					row2[3] = "Sin candidatos";
				}
				
				if(aux.getMisSolicitudes().size() >  2 && aux.getMisSolicitudes().get(2) != null&& aux.getMisSolicitudes().get(2).getUser().isEstado()) {
					String cod = aux.getMisSolicitudes().get(2).getMatch() *10+ "%";
					row2[3] = aux.getMisSolicitudes().get(2).getUser().getNombre() + "- " + cod;

				}else
					row2[3] = "Sin candidatos";

				
				modelo2.addRow(row2);
			}
			
		}
		
		}
		

		tableMatch.setModel(modelo2);
		tableMatch.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableMatch.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = tableMatch.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(293);
		columnModel.getColumn(1).setPreferredWidth(300);
		columnModel.getColumn(2).setPreferredWidth(300);
		columnModel.getColumn(3).setPreferredWidth(300);

	}
}
