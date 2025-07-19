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
	private JSpinner spnEdad;
	private JSpinner spnExperiencia;
	private JComboBox<String> cbxProvincia;
	private JRadioButton rdbtnVehSi, rdbtnVehNo, rdbtnMudSi, rdbtnMudNo;

	private JPanel panelTipo;
	private CardLayout cardLayout;
	private JTextField txtCarrera, txtTecnico, txtHabilidades;
	private JSpinner spnExpTecnico;

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

		JLabel lblFecha = new JLabel("Fecha (dd/mm/yyyy)");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFecha.setBounds(548, 130, 160, 20);
		panel.add(lblFecha);

		JLabel lblTipoTrabajo = new JLabel("Tipo de Trabajo");
		lblTipoTrabajo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTipoTrabajo.setBounds(194, 200, 140, 20);
		panel.add(lblTipoTrabajo);

		JLabel lblTipoUsuario = new JLabel("Tipo de Usuario");
		lblTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTipoUsuario.setBounds(548, 200, 150, 20);
		panel.add(lblTipoUsuario);

		panelTipo = new JPanel();
		cardLayout = new CardLayout();
		panelTipo.setLayout(cardLayout);
		panelTipo.setBounds(194, 260, 557, 80);
		panel.add(panelTipo);

		cbxTipoUsuario.addActionListener(e -> {
			String sel = (String) cbxTipoUsuario.getSelectedItem();
			if (sel != null && !sel.equals("<Seleccione>")) {
				cardLayout.show(panelTipo, sel);
			}
		});

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
