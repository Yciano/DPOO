package visual;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import logico.Bolsa;
import logico.Requisito;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.SwingUtilities;

public class RegSolEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdentificador;
	private JTextField txtIDCompania;
	private JTextField txtFecha;
	private JComboBox<String> cbxTipoTrabajo;
	private JComboBox<String> cbxTipoEmpleado;
	private JComboBox<String> cbxSexo;
	private JSpinner spnEdad;
	private JSpinner spnExperiencia;
	private JComboBox<String> cbxPrioridad;
	private JRadioButton rdbtnVehSi, rdbtnVehNo, rdbtnMudarseSi, rdbtnMudarseNo;

	private JPanel panelRequisitos;
	private CardLayout cardLayout;
	private JTextField txtCarrera, txtTecnico, txtHabilidades;
	private JSpinner spnExpTecnico;

	public static void main(String[] args) {
		try {
			RegSolEmpresa dialog = new RegSolEmpresa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RegSolEmpresa() {
		setTitle("Registro de Solicitudes");
		setResizable(false);
		setBounds(100, 100, 900, 660);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 894, 590);
		contentPanel.add(panel);
		panel.setLayout(null);

		JPanel panelAzul = new JPanel();
		panelAzul.setBackground(new Color(0, 102, 153));
		panelAzul.setBounds(0, 0, 182, 592);
		panel.add(panelAzul);

		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(181, 23, 713, 85);
		panel.add(panelTitulo);
		panelTitulo.setLayout(null);

		JLabel lblTitulo = new JLabel("REGISTRO DE SOLICITUDES");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitulo.setBounds(190, 13, 321, 59);
		panelTitulo.add(lblTitulo);

		JLabel lblIdentificador = new JLabel("Identificador");
		lblIdentificador.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIdentificador.setBounds(194, 130, 120, 20);
		panel.add(lblIdentificador);

		txtIdentificador = new JTextField("Ingrese un ID para la solicitud");
		txtIdentificador.setForeground(Color.LIGHT_GRAY);
		txtIdentificador.setBounds(194, 155, 203, 22);
		txtIdentificador.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (txtIdentificador.getText().isEmpty()) {
					txtIdentificador.setText("Ingrese un ID para la solicitud");
					txtIdentificador.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		panel.add(txtIdentificador);

		JLabel lblIDCompania = new JLabel("ID Compañía");
		lblIDCompania.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIDCompania.setBounds(548, 130, 120, 20);
		panel.add(lblIDCompania);

		txtIDCompania = new JTextField("Ingrese ID de la compañía");
		txtIDCompania.setForeground(Color.LIGHT_GRAY);
		txtIDCompania.setBounds(548, 155, 203, 22);
		txtIDCompania.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				if (txtIDCompania.getText().equals("Ingrese ID de la compañía")) {
					txtIDCompania.setText("");
					txtIDCompania.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (txtIDCompania.getText().isEmpty()) {
					txtIDCompania.setText("Ingrese ID de la compañía");
					txtIDCompania.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		panel.add(txtIDCompania);

		JLabel lblFecha = new JLabel("Fecha (dd/mm/yyyy)");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFecha.setBounds(194, 200, 180, 20);
		panel.add(lblFecha);

		txtFecha = new JTextField("dd/mm/yyyy");
		txtFecha.setForeground(Color.LIGHT_GRAY);
		txtFecha.setBounds(194, 225, 203, 22);
		txtFecha.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				if (txtFecha.getText().equals("dd/mm/yyyy")) {
					txtFecha.setText("");
					txtFecha.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (txtFecha.getText().isEmpty()) {
					txtFecha.setText("dd/mm/yyyy");
					txtFecha.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		panel.add(txtFecha);

		JLabel lblTipoTrabajo = new JLabel("Tipo de Trabajo");
		lblTipoTrabajo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTipoTrabajo.setBounds(548, 200, 140, 20);
		panel.add(lblTipoTrabajo);

		cbxTipoTrabajo = new JComboBox();
		cbxTipoTrabajo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxTipoTrabajo.setBackground(Color.WHITE);
		cbxTipoTrabajo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Tiempo Completo", "Parcial", "FreeLancer"}));
		cbxTipoTrabajo.setBounds(548, 225, 203, 22);
		panel.add(cbxTipoTrabajo);

		JLabel lblTipoEmpleado = new JLabel("Tipo de Empleado");
		lblTipoEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTipoEmpleado.setBounds(194, 276, 150, 20);
		panel.add(lblTipoEmpleado);

		cbxTipoEmpleado = new JComboBox();
		cbxTipoEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxTipoEmpleado.setBackground(Color.WHITE);
		cbxTipoEmpleado.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Universitario", "Tecnico", "Obrero"}));
		cbxTipoEmpleado.setBounds(194, 301, 203, 22);
		panel.add(cbxTipoEmpleado);

		cardLayout = new CardLayout();
		panelRequisitos = new JPanel(cardLayout);
		panelRequisitos.setBounds(194, 330, 557, 70);
		panelRequisitos.setBackground(Color.WHITE);
		panel.add(panelRequisitos);

		JPanel panelUni = new JPanel(null);
		panelUni.setBackground(Color.WHITE);
		JLabel lblCarrera = new JLabel("Carrera:");
		lblCarrera.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCarrera.setBounds(59, 26, 100, 20);
		txtCarrera = new JTextField("Ej: Ing. en Ciencias de Computación");
		txtCarrera.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCarrera.setForeground(Color.LIGHT_GRAY);
		txtCarrera.setBounds(139, 26, 340, 22);
		txtCarrera.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				if (txtCarrera.getText().equals("Ej: Ing. en Ciencias de Computación")) {
					txtCarrera.setText("");
					txtCarrera.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (txtCarrera.getText().isEmpty()) {
					txtCarrera.setText("Ej: Ing. en Ciencias de Computación");
					txtCarrera.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		panelUni.add(lblCarrera);
		panelUni.add(txtCarrera);

		JPanel panelTec = new JPanel(null);
		panelTec.setBackground(Color.WHITE);
		JLabel lblTecnico = new JLabel("Área Técnica:");
		lblTecnico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTecnico.setBounds(0, 24, 100, 20);
		txtTecnico = new JTextField("Ej: Electricidad");
		txtTecnico.setForeground(Color.LIGHT_GRAY);
		txtTecnico.setBounds(89, 25, 200, 22);
		txtTecnico.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				if (txtTecnico.getText().equals("Ej: Electricidad")) {
					txtTecnico.setText("");
					txtTecnico.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (txtTecnico.getText().isEmpty()) {
					txtTecnico.setText("Ej: Electricidad");
					txtTecnico.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		panelTec.add(lblTecnico);
		panelTec.add(txtTecnico);
		JLabel lblExpTec = new JLabel("Años de Experiencia:");
		lblExpTec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblExpTec.setBounds(320, 24, 130, 20);
		spnExpTecnico = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
		spnExpTecnico.setBounds(460, 24, 50, 22);
		panelTec.add(lblExpTec);
		panelTec.add(spnExpTecnico);

		JPanel panelObr = new JPanel(null);
		panelObr.setBackground(Color.WHITE);
		JLabel lblHabilidades = new JLabel("Habilidades (hasta que el profe nos diga):");
		lblHabilidades.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHabilidades.setBounds(0, 11, 277, 20);
		txtHabilidades = new JTextField("Ej: Plomería, Jardinería, Electricidad");
		txtHabilidades.setForeground(Color.LIGHT_GRAY);
		txtHabilidades.setBounds(0, 36, 500, 22);
		txtHabilidades.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				if (txtHabilidades.getText().equals("Ej: Plomería, Jardinería, Electricidad")) {
					txtHabilidades.setText("");
					txtHabilidades.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (txtHabilidades.getText().isEmpty()) {
					txtHabilidades.setText("Ej: Plomería, Jardinería, Electricidad");
					txtHabilidades.setForeground(Color.LIGHT_GRAY);
				}
			}
		});
		panelObr.add(lblHabilidades);
		panelObr.add(txtHabilidades);
		
		JPanel panelBlanco = new JPanel(null);
		panelBlanco.setBackground(Color.WHITE);
		JLabel lblMensaje = new JLabel("SELECCIONE TIPO DE EMPLEADO");
		lblMensaje.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMensaje.setBounds(130, 24, 400, 30);
		panelBlanco.add(lblMensaje);
		panelRequisitos.add(panelBlanco, "Blanco");


		panelRequisitos.add(panelUni, "Universitario");
		panelRequisitos.add(panelTec, "Tecnico");
		panelRequisitos.add(panelObr, "Obrero");

		cbxTipoEmpleado.addActionListener(e -> {
			String seleccion = (String) cbxTipoEmpleado.getSelectedItem();
			if (seleccion != null) {
				if (seleccion.equals("<Seleccione>")) {
					cardLayout.show(panelRequisitos, "Blanco");
				} else {
					cardLayout.show(panelRequisitos, seleccion);
				}
			}
		});


		cbxTipoEmpleado.setSelectedIndex(0);
		cardLayout.show(panelRequisitos, "Blanco");


		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSexo.setBounds(202, 432, 80, 16);
		panel.add(lblSexo);
		cbxSexo = new JComboBox(new String[] {"<Seleccione>", "M", "F"});
		cbxSexo.setBackground(Color.WHITE);
		cbxSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxSexo.setBounds(253, 429, 116, 22);
		panel.add(cbxSexo);

		JLabel lblEdad = new JLabel("Edad mínima");
		lblEdad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEdad.setBounds(408, 431, 100, 16);
		panel.add(lblEdad);
		spnEdad = new JSpinner(new SpinnerNumberModel(18, 16, 70, 1));
		spnEdad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		spnEdad.setBounds(508, 429, 50, 22);
		panel.add(spnEdad);

		JLabel lblExp = new JLabel("Años de Experiencia");
		lblExp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblExp.setBounds(595, 432, 150, 16);
		panel.add(lblExp);
		spnExperiencia = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
		spnExperiencia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		spnExperiencia.setBounds(753, 430, 50, 22);
		panel.add(spnExperiencia);

		JLabel lblVehiculo = new JLabel("¿Requiere Vehículo?");
		lblVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVehiculo.setBounds(247, 488, 150, 16);
		panel.add(lblVehiculo);
		rdbtnVehSi = new JRadioButton("Sí");
		rdbtnVehSi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnVehSi.setBackground(Color.WHITE);
		rdbtnVehSi.setBounds(257, 515, 50, 20);
		rdbtnVehNo = new JRadioButton("No");
		rdbtnVehNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnVehNo.setBackground(Color.WHITE);
		rdbtnVehNo.setBounds(317, 515, 50, 20);
		panel.add(rdbtnVehSi);
		panel.add(rdbtnVehNo);

		rdbtnVehSi.addActionListener(e -> rdbtnVehNo.setSelected(false));
		rdbtnVehNo.addActionListener(e -> rdbtnVehSi.setSelected(false));

		JLabel lblMudarse = new JLabel("¿Dispuesto a mudarse?");
		lblMudarse.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMudarse.setBounds(548, 488, 182, 16);
		panel.add(lblMudarse);
		rdbtnMudarseSi = new JRadioButton("Sí");
		rdbtnMudarseSi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnMudarseSi.setBackground(Color.WHITE);
		rdbtnMudarseSi.setBounds(578, 515, 50, 20);
		rdbtnMudarseNo = new JRadioButton("No");
		rdbtnMudarseNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnMudarseNo.setBackground(Color.WHITE);
		rdbtnMudarseNo.setBounds(638, 515, 50, 20);
		panel.add(rdbtnMudarseSi);
		panel.add(rdbtnMudarseNo);

		rdbtnMudarseSi.addActionListener(e -> rdbtnMudarseNo.setSelected(false));
		rdbtnMudarseNo.addActionListener(e -> rdbtnMudarseSi.setSelected(false));

		JLabel lblPrioridad = new JLabel("Prioridad");
		lblPrioridad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrioridad.setBounds(548, 278, 100, 16);
		panel.add(lblPrioridad);
		cbxPrioridad = new JComboBox(new String[] {"<Seleccione>", "Alta", "Media", "Baja"});
		cbxPrioridad.setBackground(Color.WHITE);
		cbxPrioridad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxPrioridad.setBounds(548, 301, 203, 22);
		panel.add(cbxPrioridad);

		
		SwingUtilities.invokeLater(() -> getContentPane().requestFocusInWindow());

		
		txtIdentificador.addFocusListener(new java.awt.event.FocusAdapter() {
		    @Override
		    public void focusGained(java.awt.event.FocusEvent e) {
		        if (txtIdentificador.getText().equals("Ingrese un ID para la solicitud")) {
		        	txtIdentificador.setText("");
		        	txtIdentificador.setForeground(Color.black);
		        }
		    }
		});

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setActionCommand("OK");
		buttonPane.add(btnRegistrar);
		
		btnRegistrar.addActionListener(e -> {
		    String id = txtIdentificador.getText().trim();
		    String idCompania = txtIDCompania.getText().trim();
		    String fechaStr = txtFecha.getText().trim();
		    String tipoTrabajo = (String) cbxTipoTrabajo.getSelectedItem();
		    String tipoEmpleado = (String) cbxTipoEmpleado.getSelectedItem();
		    String sexo = (String) cbxSexo.getSelectedItem();
		    int edad = (int) spnEdad.getValue();
		    int experiencia = (int) spnExperiencia.getValue();
		    String prioridad = (String) cbxPrioridad.getSelectedItem();
		    boolean veh = rdbtnVehSi.isSelected();
		    boolean mudarse = rdbtnMudarseSi.isSelected();

		    if (id.isEmpty() || id.equals("Ingrese un ID para la solicitud") ||
		        idCompania.isEmpty() || idCompania.equals("Ingrese ID de la compañía") ||
		        fechaStr.isEmpty() || fechaStr.equals("dd/mm/yyyy") ||
		        tipoTrabajo.equals("<Seleccione>") || tipoEmpleado.equals("<Seleccione>") ||
		        sexo.equals("<Seleccione>") || prioridad.equals("<Seleccione>")) {
		        JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    Date fecha;
		    try {
		        fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaStr);
		    } catch (ParseException ex) {
		        JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use dd/mm/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    String carrera = "", tecnico = "";
		    ArrayList<String> habilidades = new ArrayList<>();

		    switch (tipoEmpleado) {
		        case "Universitario":
		            carrera = txtCarrera.getText().trim();
		            if (carrera.isEmpty() || carrera.equals("Ej: Ing. en Ciencias de Computación")) {
		                JOptionPane.showMessageDialog(this, "Ingrese una carrera válida.", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		            break;
		        case "Tecnico":
		            tecnico = txtTecnico.getText().trim();
		            experiencia = (int) spnExpTecnico.getValue();
		            if (tecnico.isEmpty() || tecnico.equals("Ej: Electricidad")) {
		                JOptionPane.showMessageDialog(this, "Ingrese un área técnica válida.", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		            break;
		        case "Obrero":
		            String habText = txtHabilidades.getText().trim();
		            if (habText.isEmpty() || habText.equals("Ej: Plomería, Jardinería, Electricidad")) {
		                JOptionPane.showMessageDialog(this, "Ingrese al menos una habilidad.", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		            for (String h : habText.split(",")) {
		                habilidades.add(h.trim());
		            }
		            break;
		    }

		    Requisito requisito = new Requisito(
		        tipoTrabajo,
		        tipoEmpleado,
		        carrera,
		        tecnico,
		        habilidades,
		        sexo,
		        experiencia,
		        edad,
		        veh,
		        mudarse,
		        prioridad
		    );

		    boolean exito = Bolsa.getInstance().registrarSolictud(id, idCompania, fecha, requisito, true);

		    if (exito) {
		        JOptionPane.showMessageDialog(this, "Solicitud registrada exitosamente.");
		        dispose(); 
		    } else {
		        JOptionPane.showMessageDialog(this, "Error: no se pudo registrar. Verifique el ID de la compañía.", "Error", JOptionPane.ERROR_MESSAGE);
		    }
		});

		getRootPane().setDefaultButton(btnRegistrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setActionCommand("Cancel");
		buttonPane.add(btnCancelar);
	}
}
