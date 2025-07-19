package visual;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RegSolEmpleo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> cbxTipoTrabajo;
	private JComboBox<String> cbxTipoUsuario;
	private JComboBox<String> cbxSexo;
	private JComboBox<String> cbxProvincia;
	private JSpinner spnEdad;
	private JSpinner spnExperiencia;
	private JRadioButton rdbtnVehSi, rdbtnVehNo, rdbtnMudSi, rdbtnMudNo;

	private JTextField txtCedula, txtFecha;
	private JTextField txtCarrera, txtTecnico, txtHabilidades;
	private JSpinner spnExpTecnico;

	private JPanel panelTipo;
	private CardLayout cardLayout;
	private JSlider sliderSalario;
	private JLabel lblSalarioValor;

	public RegSolEmpleo() {
		setTitle("Registro de Solicitud de Empleo");
		setResizable(false);
		setBounds(100, 100, 900, 650);
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

		JLabel lblTitulo = new JLabel("REGISTRO DE SOLICITUD DE EMPLEO");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setBounds(150, 13, 450, 59);
		panelTitulo.add(lblTitulo);

		JLabel lblCedula = new JLabel("Cédula");
		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCedula.setBounds(194, 130, 120, 20);
		panel.add(lblCedula);

		txtCedula = new JTextField();
		txtCedula.setBounds(194, 155, 203, 22);
		panel.add(txtCedula);

		JLabel lblFecha = new JLabel("Fecha (dd/mm/yyyy)");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFecha.setBounds(548, 130, 160, 20);
		panel.add(lblFecha);

		txtFecha = new JTextField("dd/mm/yyyy");
		txtFecha.setForeground(Color.LIGHT_GRAY);
		txtFecha.setBounds(548, 155, 203, 22);
		panel.add(txtFecha);
		txtFecha.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if (txtFecha.getText().equals("dd/mm/yyyy")) {
					txtFecha.setText("");
					txtFecha.setForeground(Color.BLACK);
				}
			}

			public void focusLost(FocusEvent e) {
				if (txtFecha.getText().isEmpty()) {
					txtFecha.setText("dd/mm/yyyy");
					txtFecha.setForeground(Color.LIGHT_GRAY);
				}
			}
		});

		JLabel lblTipoTrabajo = new JLabel("Tipo de Trabajo");
		lblTipoTrabajo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTipoTrabajo.setBounds(194, 200, 140, 20);
		panel.add(lblTipoTrabajo);

		cbxTipoTrabajo = new JComboBox<>();
		cbxTipoTrabajo.setBounds(194, 225, 203, 22);
		panel.add(cbxTipoTrabajo);

		JLabel lblTipoUsuario = new JLabel("Tipo de Usuario");
		lblTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTipoUsuario.setBounds(548, 200, 150, 20);
		panel.add(lblTipoUsuario);

		cbxTipoUsuario = new JComboBox<String>();
		cbxTipoUsuario.setModel(new DefaultComboBoxModel<>(new String[] {
		    "<Seleccione>", "Universitario", "Tecnico", "Obrero"
		}));
		cbxTipoUsuario.setBounds(548, 225, 203, 22);
		panel.add(cbxTipoUsuario);
 

		panelTipo = new JPanel();
		cardLayout = new CardLayout();
		panelTipo.setLayout(cardLayout);
		panelTipo.setBounds(194, 260, 557, 80);
		panel.add(panelTipo);

		// Panel Universitario
		JPanel panelUni = new JPanel(null);
		panelUni.setBackground(Color.WHITE);
		JLabel lblCarrera = new JLabel("Carrera:");
		lblCarrera.setBounds(10, 10, 100, 20);
		txtCarrera = new JTextField();
		txtCarrera.setBounds(110, 10, 300, 22);
		panelUni.add(lblCarrera);
		panelUni.add(txtCarrera);
		panelTipo.add(panelUni, "Universitario");

		// Panel Técnico
		JPanel panelTec = new JPanel(null);
		panelTec.setBackground(Color.WHITE);
		JLabel lblTec = new JLabel("Área Técnica:");
		lblTec.setBounds(10, 10, 100, 20);
		txtTecnico = new JTextField();
		txtTecnico.setBounds(110, 10, 200, 22);
		JLabel lblExpTec = new JLabel("Años de Exp:");
		lblExpTec.setBounds(320, 10, 100, 20);
		spnExpTecnico = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
		spnExpTecnico.setBounds(420, 10, 50, 22);
		panelTec.add(lblTec);
		panelTec.add(txtTecnico);
		panelTec.add(lblExpTec);
		panelTec.add(spnExpTecnico);
		panelTipo.add(panelTec, "Tecnico");

		// Panel Obrero
		JPanel panelObr = new JPanel(null);
		panelObr.setBackground(Color.WHITE);
		JLabel lblHab = new JLabel("Habilidades:");
		lblHab.setBounds(10, 10, 100, 20);
		txtHabilidades = new JTextField();
		txtHabilidades.setBounds(110, 10, 400, 22);
		panelObr.add(lblHab);
		panelObr.add(txtHabilidades);
		panelTipo.add(panelObr, "Obrero");

		// Listener de cambio de tipo de usuario
		cbxTipoUsuario.addActionListener(e -> {
			String sel = (String) cbxTipoUsuario.getSelectedItem();
			if (sel != null && !sel.equals("<Seleccione>")) {
				cardLayout.show(panelTipo, sel);
			}
		});

		// Slider de salario
		JLabel lblSalario = new JLabel("Salario Esperado (RD$):");
		lblSalario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSalario.setBounds(194, 360, 200, 20);
		panel.add(lblSalario);

		sliderSalario = new JSlider(15000, 100000, 20000);
		sliderSalario.setBounds(194, 390, 400, 40);
		sliderSalario.setMajorTickSpacing(15000);
		sliderSalario.setPaintTicks(true);
		sliderSalario.setPaintLabels(true);
		panel.add(sliderSalario);

		lblSalarioValor = new JLabel("RD$ 20,000");
		lblSalarioValor.setBounds(610, 390, 100, 30);
		panel.add(lblSalarioValor);

		sliderSalario.addChangeListener(e -> {
			int val = sliderSalario.getValue();
			lblSalarioValor.setText("RD$ " + val);
		});

		// Botonera inferior
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnRegistrar = new JButton("Registrar");
		buttonPane.add(btnRegistrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(e -> dispose());
		buttonPane.add(btnCancelar);
	}
}
