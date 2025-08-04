package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logico.Bolsa;
import logico.Usuario;
import logico.Vacante;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class Contratar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnAtras;
	private JButton btnCancelar;
	private static Object[] row;
	private static DefaultTableModel modelo;
    private Usuario selected = null;
    private Vacante buscar = null;
    private JTextField txtCedula1;
    private JTextField txtEdad1;
    private JTextField txtSexo1;
    private JTextField txtContacto1;
    private JTextField txtTipoTrabajo1;
    private JTextField txtProvincia1;
    private JTextField txtLicencia1;
    private JTextField txtVehiculo1;
    private JTextField txtMudarse1;
    private JTextField txtEmpleado1;
    private JTextField txtCedula2;
    private JTextField txtEdad2;
    private JTextField txtSexo2;
    private JTextField txtContacto2;
    private JTextField txtTipoTrabajo2;
    private JTextField txtProvincia2;
    private JTextField txtLicencia2;
    private JTextField txtVehiculo2;
    private JTextField txtMudarse2;
    private JTextField txtEmpleado2;
    private JTextField txtCedula3;
    private JTextField txtEdad3;
    private JTextField txtSexo3;
    private JTextField txtContacto3;
    private JTextField txtTipoTrabajo3;
    private JTextField txtProvincia3;
    private JTextField txtLicencia3;
    private JTextField txtVehiculo3;
    private JTextField txtMudarse3;
    private JTextField txtEmpleado3;
    private static Vacante aux = null;
    ArrayList<Usuario> users;
    private JButton btnContratar1;
    private JButton btnContratar2;
    private JButton btnContratar3;
    private JLabel lblfoto1;
    private JLabel lblfoto2;
    private JLabel lblfoto3;

	
	public static void main(String[] args) {
		try {
			Contratar dialog = new Contratar(aux);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public Contratar(Vacante vac) {
		aux = vac;
		setBounds(100, 100, 1279, 800);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel panelCand1 = new JPanel();
			panelCand1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelCand1.setBounds(12, 13, 397, 682);
			panel.add(panelCand1);
			panelCand1.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Candidato 1");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblNewLabel.setBounds(133, 13, 106, 29);
			panelCand1.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Ced\u00FAla:");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_1.setBounds(12, 246, 56, 16);
			panelCand1.add(lblNewLabel_1);
			
			lblfoto1 = new JLabel("");
			lblfoto1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			lblfoto1.setBackground(Color.WHITE);
			lblfoto1.setBounds(133, 76, 106, 103);
			panelCand1.add(lblfoto1);
			
			
			JLabel NombreYApellido1 = new JLabel("No existe candidato");
			users = listaDeUsers(aux);
			if(users.size() >= 1) {
				NombreYApellido1.setText(users.get(0).getNombre() + " " + users.get(0).getApellido());
			}
			NombreYApellido1.setFont(new Font("Tahoma", Font.BOLD, 18));
			NombreYApellido1.setBounds(94, 192, 187, 22);
			panelCand1.add(NombreYApellido1);
			
			txtCedula1 = new JTextField();
			txtCedula1.setEditable(false);
			txtCedula1.setBounds(12, 275, 109, 22);
			panelCand1.add(txtCedula1);
			txtCedula1.setColumns(10);
			
			JLabel lblNewLabel_3 = new JLabel("Edad:");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_3.setBounds(150, 246, 56, 16);
			panelCand1.add(lblNewLabel_3);
			
			txtEdad1 = new JTextField();
			txtEdad1.setEditable(false);
			txtEdad1.setBounds(150, 275, 73, 22);
			panelCand1.add(txtEdad1);
			txtEdad1.setColumns(10);
			
			JLabel lblNewLabel_4 = new JLabel("Sexo:");
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_4.setBounds(255, 246, 47, 16);
			panelCand1.add(lblNewLabel_4);
			
			txtSexo1 = new JTextField();
			txtSexo1.setEditable(false);
			txtSexo1.setBounds(256, 275, 73, 22);
			panelCand1.add(txtSexo1);
			txtSexo1.setColumns(10);
			
			JLabel lblNewLabel_5 = new JLabel("Contacto:");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_5.setBounds(20, 340, 80, 15);
			panelCand1.add(lblNewLabel_5);
			
			txtContacto1 = new JTextField();
			txtContacto1.setEditable(false);
			txtContacto1.setBounds(98, 337, 228, 22);
			panelCand1.add(txtContacto1);
			txtContacto1.setColumns(10);
			
			JLabel lblNewLabel_6 = new JLabel("Tipo de trabajo:");
			lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_6.setBounds(12, 388, 123, 29);
			panelCand1.add(lblNewLabel_6);
			
			txtTipoTrabajo1 = new JTextField();
			txtTipoTrabajo1.setEditable(false);
			txtTipoTrabajo1.setBounds(12, 431, 106, 22);
			panelCand1.add(txtTipoTrabajo1);
			txtTipoTrabajo1.setColumns(10);
			
			JLabel lblNewLabel_7 = new JLabel("Provinicia: ");
			lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_7.setBounds(228, 394, 67, 16);
			panelCand1.add(lblNewLabel_7);
			
			txtProvincia1 = new JTextField();
			txtProvincia1.setEditable(false);
			txtProvincia1.setBounds(228, 431, 116, 22);
			panelCand1.add(txtProvincia1);
			txtProvincia1.setColumns(10);
			
			JLabel lblNewLabel_8 = new JLabel("\u00BFTiene licencia?");
			lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_8.setBounds(12, 484, 106, 16);
			panelCand1.add(lblNewLabel_8);
			
			JLabel lblNewLabel_9 = new JLabel("\u00BFTiene vehiculo?");
			lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_9.setBounds(228, 484, 106, 16);
			panelCand1.add(lblNewLabel_9);
			
			JLabel lblNewLabel_10 = new JLabel("\u00BFDispuesto a mudarse?");
			lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_10.setBounds(12, 572, 151, 16);
			panelCand1.add(lblNewLabel_10);
			
			JLabel lblNewLabel_11 = new JLabel("\u00BFEmpleado?");
			lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_11.setBounds(228, 573, 106, 15);
			panelCand1.add(lblNewLabel_11);
			
			txtLicencia1 = new JTextField();
			txtLicencia1.setEditable(false);
			txtLicencia1.setBounds(12, 513, 94, 22);
			panelCand1.add(txtLicencia1);
			txtLicencia1.setColumns(10);
			
			txtVehiculo1 = new JTextField();
			txtVehiculo1.setEditable(false);
			txtVehiculo1.setBounds(228, 513, 116, 22);
			panelCand1.add(txtVehiculo1);
			txtVehiculo1.setColumns(10);
			
			txtMudarse1 = new JTextField();
			txtMudarse1.setEditable(false);
			txtMudarse1.setBounds(12, 601, 116, 22);
			panelCand1.add(txtMudarse1);
			txtMudarse1.setColumns(10);
			
			txtEmpleado1 = new JTextField();
			txtEmpleado1.setEditable(false);
			txtEmpleado1.setColumns(10);
			txtEmpleado1.setBounds(228, 601, 116, 22);
			panelCand1.add(txtEmpleado1);
			
			btnContratar1 = new JButton("Contratar");
			btnContratar1.setEnabled(false);
			btnContratar1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 int option = JOptionPane.showConfirmDialog(null,
		                        "¿Está seguro que desea contratar al usuario con cédula: " + users.get(0).getCedula()+ "?",
		                        "Contrato", JOptionPane.WARNING_MESSAGE);
		                    if (option == JOptionPane.OK_OPTION) {
		                    	boolean retorno = Bolsa.getInstance().registrarContrato(users.get(0), vac, new Date());
		    					if(retorno) {
		    	                	JOptionPane.showMessageDialog(null, "Usuario contratado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		    	                	try {
										Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
									} catch (IOException e1) {
										e1.printStackTrace();
									}
		    	                	dispose();
		    	                	Match aux = new Match(1);
		    	                	aux.setModal(true);
		    	                	aux.setVisible(true);
		    	                	
		    					}
		    					else {
		    			            JOptionPane.showMessageDialog(null, "Error en la operación.", "Error", JOptionPane.ERROR_MESSAGE);

		    					}
		                    }else {
		                    	dispose();
		                    }
					
				}
			});
			btnContratar1.setBounds(288, 644, 97, 25);
			panelCand1.add(btnContratar1);
			
			JPanel panelCand2 = new JPanel();
			panelCand2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelCand2.setBounds(421, 13, 397, 682);
			panel.add(panelCand2);
			panelCand2.setLayout(null);
			
			JLabel lblCandidato = new JLabel("Candidato 2");
			lblCandidato.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblCandidato.setBounds(144, 13, 106, 29);
			panelCand2.add(lblCandidato);
			
			lblfoto2 = new JLabel("");
			lblfoto2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			lblfoto2.setBackground(Color.WHITE);
			lblfoto2.setBounds(150, 83, 106, 103);
			panelCand2.add(lblfoto2);
			
			
			JLabel NombreYApellido2 = new JLabel("No existe candidato");
			if(users.size() >= 2) {
				NombreYApellido2.setText(users.get(1).getNombre() + " " + users.get(1).getApellido());
				}
			NombreYApellido2.setFont(new Font("Tahoma", Font.BOLD, 18));
			NombreYApellido2.setBounds(121, 199, 191, 22);
			panelCand2.add(NombreYApellido2);
			
			JLabel label_2 = new JLabel("Ced\u00FAla:");
			label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_2.setBounds(29, 253, 56, 16);
			panelCand2.add(label_2);
			
			txtCedula2 = new JTextField();
			txtCedula2.setEditable(false);
			txtCedula2.setColumns(10);
			txtCedula2.setBounds(29, 282, 106, 22);
			panelCand2.add(txtCedula2);
			
			JLabel label_3 = new JLabel("Edad:");
			label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_3.setBounds(161, 253, 56, 16);
			panelCand2.add(label_3);
			
			txtEdad2 = new JTextField();
			txtEdad2.setEditable(false);
			txtEdad2.setColumns(10);
			txtEdad2.setBounds(160, 282, 73, 22);
			panelCand2.add(txtEdad2);
			
			JLabel label_4 = new JLabel("Sexo:");
			label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_4.setBounds(272, 253, 47, 16);
			panelCand2.add(label_4);
			
			txtSexo2 = new JTextField();
			txtSexo2.setEditable(false);
			txtSexo2.setColumns(10);
			txtSexo2.setBounds(282, 282, 73, 22);
			panelCand2.add(txtSexo2);
			
			JLabel label_5 = new JLabel("Contacto:");
			label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_5.setBounds(37, 347, 80, 15);
			panelCand2.add(label_5);
			
			txtContacto2 = new JTextField();
			txtContacto2.setEditable(false);
			txtContacto2.setColumns(10);
			txtContacto2.setBounds(115, 344, 228, 22);
			panelCand2.add(txtContacto2);
			
			JLabel label_6 = new JLabel("Tipo de trabajo:");
			label_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_6.setBounds(29, 395, 123, 29);
			panelCand2.add(label_6);
			
			txtTipoTrabajo2 = new JTextField();
			txtTipoTrabajo2.setEditable(false);
			txtTipoTrabajo2.setColumns(10);
			txtTipoTrabajo2.setBounds(29, 438, 106, 22);
			panelCand2.add(txtTipoTrabajo2);
			
			JLabel label_7 = new JLabel("Provinicia: ");
			label_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_7.setBounds(245, 401, 67, 16);
			panelCand2.add(label_7);
			
			txtProvincia2 = new JTextField();
			txtProvincia2.setEditable(false);
			txtProvincia2.setColumns(10);
			txtProvincia2.setBounds(245, 438, 116, 22);
			panelCand2.add(txtProvincia2);
			
			JLabel label_8 = new JLabel("\u00BFTiene licencia?");
			label_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_8.setBounds(29, 491, 106, 16);
			panelCand2.add(label_8);
			
			txtLicencia2 = new JTextField();
			txtLicencia2.setEditable(false);
			txtLicencia2.setColumns(10);
			txtLicencia2.setBounds(29, 520, 94, 22);
			panelCand2.add(txtLicencia2);
			
			JLabel label_9 = new JLabel("\u00BFTiene vehiculo?");
			label_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_9.setBounds(245, 491, 106, 16);
			panelCand2.add(label_9);
			
			txtVehiculo2 = new JTextField();
			txtVehiculo2.setEditable(false);
			txtVehiculo2.setColumns(10);
			txtVehiculo2.setBounds(245, 520, 116, 22);
			panelCand2.add(txtVehiculo2);
			
			JLabel label_10 = new JLabel("\u00BFDispuesto a mudarse?");
			label_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_10.setBounds(29, 579, 151, 16);
			panelCand2.add(label_10);
			
			txtMudarse2 = new JTextField();
			txtMudarse2.setEditable(false);
			txtMudarse2.setColumns(10);
			txtMudarse2.setBounds(29, 608, 116, 22);
			panelCand2.add(txtMudarse2);
			
			JLabel label_11 = new JLabel("\u00BFEmpleado?");
			label_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_11.setBounds(245, 580, 106, 15);
			panelCand2.add(label_11);
			
			txtEmpleado2 = new JTextField();
			txtEmpleado2.setEditable(false);
			txtEmpleado2.setColumns(10);
			txtEmpleado2.setBounds(245, 608, 116, 22);
			panelCand2.add(txtEmpleado2);
			
			btnContratar2 = new JButton("Contratar");
			btnContratar2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int option = JOptionPane.showConfirmDialog(null,
	                        "¿Está seguro que desea contratar al usuario con cédula: " + users.get(1).getCedula()+ "?",
	                        "Contrato", JOptionPane.WARNING_MESSAGE);
	                    if (option == JOptionPane.OK_OPTION) {
	                    	boolean retorno = Bolsa.getInstance().registrarContrato(users.get(1), vac, new Date());
	    					if(retorno) {
	    	                	JOptionPane.showMessageDialog(null, "Usuario contratado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	    	                	try {
									Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
								} catch (IOException e1) {
									e1.printStackTrace();
								}
	    	                	dispose();
	    	                	Match aux = new Match(1);
	    	                	aux.setModal(true);
	    	                	aux.setVisible(true);
	    					}
	    					else {
	    			            JOptionPane.showMessageDialog(null, "Error en la operación.", "Error", JOptionPane.ERROR_MESSAGE);

	    					}
	                    }else {
	                    	dispose();
	                    }
				}
			});
			btnContratar2.setEnabled(false);
			btnContratar2.setBounds(288, 644, 97, 25);
			panelCand2.add(btnContratar2);
			
			JPanel panelCand3 = new JPanel();
			panelCand3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelCand3.setBounds(830, 13, 397, 682);
			panel.add(panelCand3);
			panelCand3.setLayout(null);
			
			JLabel lblCandidato_1 = new JLabel("Candidato 3");
			lblCandidato_1.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblCandidato_1.setBounds(147, 13, 106, 29);
			panelCand3.add(lblCandidato_1);
			
			lblfoto3 = new JLabel("");
			lblfoto3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			lblfoto3.setBackground(Color.WHITE);
			lblfoto3.setBounds(148, 85, 106, 103);
			panelCand3.add(lblfoto3);
			
			
				JLabel NombreYApellido3 = new JLabel("No existe candidato");
				if(users.size() >= 3) {
					 NombreYApellido3.setText(users.get(2).getNombre() + " " + users.get(2).getApellido());
					}
			NombreYApellido3.setFont(new Font("Tahoma", Font.BOLD, 18));
			NombreYApellido3.setBounds(123, 201, 187, 22);
			panelCand3.add(NombreYApellido3);
			
			JLabel label_14 = new JLabel("Ced\u00FAla:");
			label_14.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_14.setBounds(27, 255, 56, 22);
			panelCand3.add(label_14);
			
			txtCedula3 = new JTextField();
			txtCedula3.setEditable(false);
			txtCedula3.setColumns(10);
			txtCedula3.setBounds(27, 284, 106, 22);
			panelCand3.add(txtCedula3);
			
			JLabel label_15 = new JLabel("Edad:");
			label_15.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_15.setBounds(157, 255, 56, 16);
			panelCand3.add(label_15);
			
			txtEdad3 = new JTextField();
			txtEdad3.setEditable(false);
			txtEdad3.setColumns(10);
			txtEdad3.setBounds(158, 284, 73, 22);
			panelCand3.add(txtEdad3);
			
			JLabel label_16 = new JLabel("Sexo:");
			label_16.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_16.setBounds(270, 255, 47, 16);
			panelCand3.add(label_16);
			
			txtSexo3 = new JTextField();
			txtSexo3.setEditable(false);
			txtSexo3.setColumns(10);
			txtSexo3.setBounds(271, 284, 73, 22);
			panelCand3.add(txtSexo3);
			
			JLabel label_17 = new JLabel("Contacto:");
			label_17.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_17.setBounds(35, 349, 80, 15);
			panelCand3.add(label_17);
			
			txtContacto3 = new JTextField();
			txtContacto3.setEditable(false);
			txtContacto3.setColumns(10);
			txtContacto3.setBounds(113, 346, 228, 22);
			panelCand3.add(txtContacto3);
			
			JLabel label_18 = new JLabel("Tipo de trabajo:");
			label_18.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_18.setBounds(27, 397, 123, 29);
			panelCand3.add(label_18);
			
			txtTipoTrabajo3 = new JTextField();
			txtTipoTrabajo3.setEditable(false);
			txtTipoTrabajo3.setColumns(10);
			txtTipoTrabajo3.setBounds(27, 440, 106, 22);
			panelCand3.add(txtTipoTrabajo3);
			
			JLabel label_19 = new JLabel("Provinicia: ");
			label_19.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_19.setBounds(243, 403, 67, 16);
			panelCand3.add(label_19);
			
			txtProvincia3 = new JTextField();
			txtProvincia3.setEditable(false);
			txtProvincia3.setColumns(10);
			txtProvincia3.setBounds(243, 440, 116, 22);
			panelCand3.add(txtProvincia3);
			
			JLabel label_20 = new JLabel("\u00BFTiene licencia?");
			label_20.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_20.setBounds(27, 493, 106, 16);
			panelCand3.add(label_20);
			
			txtLicencia3 = new JTextField();
			txtLicencia3.setEditable(false);
			txtLicencia3.setColumns(10);
			txtLicencia3.setBounds(27, 522, 94, 22);
			panelCand3.add(txtLicencia3);
			
			JLabel label_21 = new JLabel("\u00BFTiene vehiculo?");
			label_21.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_21.setBounds(243, 493, 106, 16);
			panelCand3.add(label_21);
			
			txtVehiculo3 = new JTextField();
			txtVehiculo3.setEditable(false);
			txtVehiculo3.setColumns(10);
			txtVehiculo3.setBounds(243, 522, 116, 22);
			panelCand3.add(txtVehiculo3);
			
			JLabel label_22 = new JLabel("\u00BFDispuesto a mudarse?");
			label_22.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_22.setBounds(27, 581, 151, 16);
			panelCand3.add(label_22);
			
			txtMudarse3 = new JTextField();
			txtMudarse3.setEditable(false);
			txtMudarse3.setColumns(10);
			txtMudarse3.setBounds(27, 610, 116, 22);
			panelCand3.add(txtMudarse3);
			
			JLabel label_23 = new JLabel("\u00BFEmpleado?");
			label_23.setFont(new Font("Tahoma", Font.PLAIN, 14));
			label_23.setBounds(243, 582, 106, 15);
			panelCand3.add(label_23);
			
			txtEmpleado3 = new JTextField();
			txtEmpleado3.setEditable(false);
			txtEmpleado3.setColumns(10);
			txtEmpleado3.setBounds(243, 610, 116, 22);
			panelCand3.add(txtEmpleado3);
			
			btnContratar3 = new JButton("Contratar");
			btnContratar3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int option = JOptionPane.showConfirmDialog(null,
	                        "¿Está seguro que desea contratar al usuario con cédula: " + users.get(3).getCedula()+ "?",
	                        "Contrato", JOptionPane.WARNING_MESSAGE);
	                    if (option == JOptionPane.OK_OPTION) {
	                    	boolean retorno = Bolsa.getInstance().registrarContrato(users.get(3), vac, new Date());
	    					if(retorno) {
	    	                	JOptionPane.showMessageDialog(null, "Usuario contratado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	    	                	try {
									Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
								} catch (IOException e1) {
									e1.printStackTrace();
								}
	    	                	dispose();
	    	                	Match aux = new Match(1);
	    	                	aux.setModal(true);
	    	                	aux.setVisible(true);
	    					}
	    					else {
	    			            JOptionPane.showMessageDialog(null, "Error en la operación.", "Error", JOptionPane.ERROR_MESSAGE);

	    					}
	                    }else {
	                    	dispose();
	                    }
				}
			});
			btnContratar3.setEnabled(false);
			btnContratar3.setBounds(288, 644, 97, 25);
			panelCand3.add(btnContratar3);
			{
			
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnAtras = new JButton("Atr\u00E1s");
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					Bolsa.getInstance().match();
					Match aux = new Match(1);
					aux.setModal(true);
					aux.setVisible(true);
					
				}
			});
			buttonPane.add(btnAtras);
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
			loadUser(users);
		}
	}
	
	
	
	private void loadUser(ArrayList<Usuario> user) {
		if(user != null && user.size() > 0) {
			txtCedula1.setText(user.get(0).getCedula());
			ImageIcon foto1 = cargarImagenUsuario(user.get(0).getCedula());
			lblfoto1.setIcon(foto1 != null ? foto1 : new ImageIcon("Defaultt.png"));
			txtContacto1.setText(user.get(0).getContacto());
			txtEdad1.setText(String.valueOf(user.get(0).getEdad()));
			txtTipoTrabajo1.setText(user.get(0).getTipoTrabajo());
			txtProvincia1.setText(user.get(0).getProvincia());
			txtSexo1.setText(user.get(0).getSexo());
			btnContratar1.setEnabled(true);
			if(user.get(0).isLicencia()) {
				txtLicencia1.setText("Si");
			}else
				txtLicencia1.setText("No");
			
			if(user.get(0).isTieneVeh()) {
				txtVehiculo1.setText("Si");
			}else {
				txtVehiculo1.setText("No");
			}
			
			if(user.get(0).isDispuestoMud()) {
				txtMudarse1.setText("Si");
			}else {
				txtMudarse1.setText("No");
			}
			
			if(user.get(0).isEstado()) {
				txtEmpleado1.setText("Si");
			}else {
				txtEmpleado1.setText("Desempleado");

			}
			
			
			
			}
		
		if(user != null && user.size() > 1) {
			txtCedula2.setText(user.get(1).getCedula());
			ImageIcon foto2 = cargarImagenUsuario(user.get(1).getCedula());
			lblfoto2.setIcon(foto2 != null ? foto2 : new ImageIcon("Defaultt.png"));
			txtContacto2.setText(user.get(1).getContacto());
			txtEdad2.setText(String.valueOf(user.get(1).getEdad()));
			txtTipoTrabajo2.setText(user.get(1).getTipoTrabajo());
			txtProvincia2.setText(user.get(1).getProvincia());
			txtSexo2.setText(user.get(1).getSexo());
			btnContratar2.setEnabled(true);
			
			if(user.get(1).isLicencia()) {
				txtLicencia2.setText("Si");
			}else
				txtLicencia2.setText("No");
			
			if(user.get(1).isTieneVeh()) {
				txtVehiculo2.setText("Si");
			}else {
				txtVehiculo2.setText("No");
			}
			
			if(user.get(1).isDispuestoMud()) {
				txtMudarse2.setText("Si");
			}else {
				txtMudarse2.setText("No");
			}
			
			if(user.get(1).isEstado()) {
				txtEmpleado2.setText("Si");
			}else {
				txtEmpleado2.setText("Desempleado");

			}
			
			
			}
		
		if(user != null && user.size() > 2) {
			txtCedula3.setText(user.get(2).getCedula());
			ImageIcon foto3 = cargarImagenUsuario(user.get(2).getCedula());
			lblfoto3.setIcon(foto3 != null ? foto3 : new ImageIcon("Defaultt.png"));
			txtContacto3.setText(user.get(2).getContacto());
			txtEdad3.setText(String.valueOf(user.get(2).getEdad()));
			txtTipoTrabajo3.setText(user.get(2).getTipoTrabajo());
			txtProvincia3.setText(user.get(2).getProvincia());
			txtSexo3.setText(user.get(2).getSexo());
			btnContratar3.setEnabled(true);
			if(user.get(2).isLicencia()) {
				txtLicencia3.setText("Si");
			}else
				txtLicencia3.setText("No");
			
			if(user.get(2).isTieneVeh()) {
				txtVehiculo3.setText("Si");
			}else {
				txtVehiculo3.setText("No");
			}
			
			if(user.get(2).isDispuestoMud()) {
				txtMudarse3.setText("Si");
			}else {
				txtMudarse3.setText("No");
			}
			
			if(user.get(2).isEstado()) {
				txtEmpleado3.setText("Si");
			}else {
				txtEmpleado3.setText("Desempleado");

			}
			
			
			}
		
			
		
	}
	
	private ArrayList<Usuario> listaDeUsers(Vacante aux){
		ArrayList<Usuario> user = new ArrayList<Usuario>();
		if(aux != null && aux.getMisSolicitudes() != null) {
		if(aux.getMisSolicitudes().size() > 0) {
			for(int i = 0; i < aux.getMisSolicitudes().size(); i++) {
				user.add(aux.getMisSolicitudes().get(i).getUser());
			}
		}
		}
		
		
		return user;
		
	}
	
	private ImageIcon cargarImagenUsuario(String cedula) {
	    String[] extensiones = {".png", ".jpg", ".jpeg", ".gif"};
	    for (String ext : extensiones) {
	        String ruta = "imagenesusuarios/" + cedula + ext;
	        File archivo = new File(ruta);
	        if (archivo.exists()) {
	            ImageIcon icon = new ImageIcon(ruta);
	            Image img = icon.getImage().getScaledInstance(106, 103, Image.SCALE_SMOOTH);
	            return new ImageIcon(img);
	        }
	    }

	    ImageIcon icon = new ImageIcon("Defaultt.png");
	    Image img = icon.getImage().getScaledInstance(106, 103, Image.SCALE_SMOOTH);
	    return new ImageIcon(img);
	}


	
	
}
