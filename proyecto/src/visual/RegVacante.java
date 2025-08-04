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
import logico.Vacante;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JTextPane;

public class RegVacante extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtIdentificador;
    private JTextField txtRNC;
    private JTextField txtFecha;
    private JComboBox<String> cbxTipoTrabajo;
    private JComboBox<String> cbxTipoEmpleado;
    private JComboBox<String> cbxSexo;
    private JSpinner spnEdad;
    private JComboBox<String> cbxPrioridad;
    private JRadioButton rdbtnVehSi, rdbtnVehNo, rdbtnMudarseSi, rdbtnMudarseNo;

    private JPanel panelRequisitos;
    private CardLayout cardLayout;
    private JSpinner spnExpTecnico;
    private JSpinner spnExpObrero;
    private JTextField txtPosicion;
    private JTextField txtDescripcion;

    private static String idCompania;
    private static String fechaStr;
    private static String tipoTrabajo;
    private static String tipoEmpleado;
    private static String sexo;
    private static int edad;
    private static int experiencia;
    private static String prioridad;
    private static boolean veh;
    private static boolean mudarse;
    private static String carrera = "", tecnico = "";
    private static ArrayList<String> habilidades = new ArrayList<>();
    private JPanel panelUni;
    private JPanel panelTec;
    private JPanel panelObr;
    private String areaUni = "";
    private JComboBox<String> cbxCarrera;
    private JComboBox<String> cbxTecnico;
    private JComboBox<String> cbxHabilidadObrero;
	private Vacante vacanteModificada;
	JComboBox<String> cbxArea;

    public static void main(String[] args) {
        try {
            RegVacante dialog = new RegVacante();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RegVacante() {
        setTitle("Registro de Solicitudes");
        setResizable(false);
        setBounds(100, 100, 1013, 880);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 13, 1007, 797);
        contentPanel.add(panel);
        panel.setLayout(null);

        JPanel panelAzul = new JPanel();
        panelAzul.setBackground(new Color(0, 102, 153));
        panelAzul.setBounds(0, 0, 182, 797);
        panel.add(panelAzul);

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBounds(181, 23, 713, 85);
        panel.add(panelTitulo);
        panelTitulo.setLayout(null);

        JLabel lblTitulo = new JLabel("REGISTRO DE VACANTES");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitulo.setBounds(190, 13, 321, 59);
        panelTitulo.add(lblTitulo);

        JLabel lblIdentificador = new JLabel("Identificador");
        lblIdentificador.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblIdentificador.setBounds(194, 130, 120, 20);
        panel.add(lblIdentificador);

        txtIdentificador = new JTextField("");
        txtIdentificador.setEditable(false);
        txtIdentificador.setForeground(Color.GRAY);
        txtIdentificador.setBounds(194, 155, 203, 22);
        String identificador = Bolsa.genCodVacante();
        txtIdentificador.setText(identificador);
        panel.add(txtIdentificador);

        JLabel lblIDCompania = new JLabel("RNC");
        lblIDCompania.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblIDCompania.setBounds(194, 200, 120, 20);
        panel.add(lblIDCompania);

        txtRNC = new JTextField("###-#####-#");
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
        txtRNC.setForeground(Color.LIGHT_GRAY);
        txtRNC.setBounds(194, 225, 203, 22);
        txtRNC.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txtRNC.getText().equals("###-#####-#")) {
                    txtRNC.setText("");
                    txtRNC.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtRNC.getText().replaceAll("[^\\d]", "").isEmpty()) {
                    txtRNC.setText("###-#####-#");
                    txtRNC.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
        panel.add(txtRNC);

        JLabel lblFecha = new JLabel("Fecha (dd/mm/yyyy)");
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFecha.setBounds(626, 130, 180, 20);
        panel.add(lblFecha);

        txtFecha = new JTextField("dd/mm/yyyy");
        txtFecha.setEditable(false);
        txtFecha.setForeground(Color.LIGHT_GRAY);
        txtFecha.setBounds(626, 155, 203, 22);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = formato.format(new Date());
        txtFecha.setText(fechaFormateada);
        panel.add(txtFecha);

        JLabel lblTipoTrabajo = new JLabel("Tipo de Trabajo");
        lblTipoTrabajo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTipoTrabajo.setBounds(194, 276, 140, 20);
        panel.add(lblTipoTrabajo);

        cbxTipoTrabajo = new JComboBox<>();
        cbxTipoTrabajo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbxTipoTrabajo.setBackground(Color.WHITE);
        cbxTipoTrabajo.setModel(
                new DefaultComboBoxModel<>(new String[] { "<Seleccione>", "Tiempo Completo", "Parcial", "FreeLancer" }));
        cbxTipoTrabajo.setBounds(194, 301, 203, 22);
        panel.add(cbxTipoTrabajo);

        JLabel lblTipoEmpleado = new JLabel("Tipo de Empleado");
        lblTipoEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTipoEmpleado.setBounds(626, 276, 150, 20);
        panel.add(lblTipoEmpleado);

        cbxTipoEmpleado = new JComboBox<>();
        cbxTipoEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbxTipoEmpleado.setBackground(Color.WHITE);
        cbxTipoEmpleado.setModel(
                new DefaultComboBoxModel<>(new String[] { "<Seleccione>", "Universitario", "Tecnico", "Obrero" }));
        cbxTipoEmpleado.setBounds(626, 301, 203, 22);
        panel.add(cbxTipoEmpleado);

        cardLayout = new CardLayout();
        panelRequisitos = new JPanel(cardLayout);
        panelRequisitos.setBounds(194, 336, 649, 129);
        panelRequisitos.setBackground(Color.WHITE);
        panel.add(panelRequisitos);

        panelUni = new JPanel(null);
        panelUni.setBackground(Color.WHITE);
        JLabel lblCarrera = new JLabel("Carrera:");
        lblCarrera.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCarrera.setBounds(333, 43, 100, 20);
        panelUni.add(lblCarrera);

        panelTec = new JPanel(null);
        panelTec.setBackground(Color.WHITE);
        JLabel lblTecnico = new JLabel("Área Técnica:");
        lblTecnico.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTecnico.setBounds(10, 24, 100, 20);
        panelTec.add(lblTecnico);
        JLabel lblExpTec = new JLabel("Años de Experiencia:");
        lblExpTec.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblExpTec.setBounds(438, 24, 162, 20);
        spnExpTecnico = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        spnExpTecnico.setBounds(438, 57, 162, 22);
        panelTec.add(lblExpTec);
        panelTec.add(spnExpTecnico);

        panelObr = new JPanel(null);
        panelObr.setBackground(Color.WHITE);
        JLabel lblHabilidades = new JLabel("Habilidades:");
        lblHabilidades.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblHabilidades.setBounds(10, 24, 100, 20);
        panelObr.add(lblHabilidades);
        JLabel lblExpObrero = new JLabel("Años de Experiencia:");
        lblExpObrero.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblExpObrero.setBounds(438, 24, 162, 20);
        panelObr.add(lblExpObrero);
        spnExpObrero = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        spnExpObrero.setBounds(438, 57, 162, 22);
        panelObr.add(spnExpObrero);
        cbxHabilidadObrero = new JComboBox<>();
        cbxHabilidadObrero.setModel(new DefaultComboBoxModel<>(new String[] {
                "<Seleccione>", 
                "Construcción general", 
                "Albañilería", 
                "Pintura y acabados", 
                "Soldadura",
                "Instalaciones sanitarias", 
                "Limpieza y desinfección", 
                "Manejo de herramientas",
                "Carga y descarga de materiales", 
                "Jardinería"
        }));
        cbxHabilidadObrero.setBounds(10, 57, 220, 22);
        panelObr.add(cbxHabilidadObrero);

        JPanel panelBlanco = new JPanel(null);
        panelBlanco.setBackground(Color.WHITE);
        JLabel lblMensaje = new JLabel("SELECCIONE TIPO DE EMPLEADO");
        lblMensaje.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblMensaje.setBounds(173, 39, 322, 30);
        panelBlanco.add(lblMensaje);
        panelRequisitos.add(panelBlanco, "Blanco");

        panelRequisitos.add(panelUni, "Universitario");

        cbxCarrera = new JComboBox<>();
        cbxCarrera.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbxCarrera.setEnabled(false);
        cbxCarrera.setModel(new DefaultComboBoxModel<>(new String[] { "<Seleccione>", "Administración de Empresas",
                "Arquitectura", "Comunicación Social", "Contabilidad", "Derecho", "Ingeniería Agroindustrial",
                "Ingeniería Ambiental", "Ingeniería Civil", "Ingeniería Electrónica",
                "Ingeniería en Computación", "Ingeniería en Telecomunicaciones",
                "Ingeniería Industrial", "Ingeniería Mecánica", "Medicina", "Mercadeo",
                "Odontología", "Psicología Clínica ", "Relaciones Internacionales" }));
        cbxCarrera.setToolTipText("");
        cbxCarrera.setBounds(426, 42, 177, 22);
        panelUni.add(cbxCarrera);

        JLabel lblNewLabel_2 = new JLabel("Área académica:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(12, 45, 125, 16);
        panelUni.add(lblNewLabel_2);

        cbxArea = new JComboBox<>();
        cbxArea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = cbxArea.getSelectedIndex();
                filtroPorArea(selectedIndex);
                if (selectedIndex > 0) {
                    areaUni = cbxArea.getSelectedItem().toString();
                } else {
                    areaUni = "";
                }
            }
        });
        
        cbxArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbxArea.setModel(new DefaultComboBoxModel<>(new String[] {"<Seleccione>", "Ciencias Agropecuarias", "Finanzas", "Salud", "Tecnología", "Otros"}));
        cbxArea.setBounds(132, 42, 177, 22);
        panelUni.add(cbxArea);
        panelRequisitos.add(panelTec, "Tecnico");

        cbxTecnico = new JComboBox<>();
        cbxTecnico.setModel(new DefaultComboBoxModel<>(new String[] { "<Seleccione>",
                "Técnico Superior en Informática", "Técnico Superior en Contabilidad ",
                "Técnico Superior en Enfermería", "Técnico Superior en Gastronomía ",
                "Técnico Superior en Diseño Gráfico" }));
        cbxTecnico.setBounds(10, 57, 187, 22);
        panelTec.add(cbxTecnico);
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
        lblSexo.setBounds(231, 478, 50, 16);
        panel.add(lblSexo);
        cbxSexo = new JComboBox<>();
        cbxSexo.addItem("<Seleccione>");
        cbxSexo.addItem("M");
        cbxSexo.addItem("F");

        cbxSexo.setBackground(Color.WHITE);
        cbxSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbxSexo.setBounds(194, 507, 116, 22);
        panel.add(cbxSexo);

        JLabel lblEdad = new JLabel("Edad mínima");
        lblEdad.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEdad.setBounds(347, 478, 100, 16);
        panel.add(lblEdad);
        spnEdad = new JSpinner(new SpinnerNumberModel(18, 16, 70, 1));
        spnEdad.setFont(new Font("Tahoma", Font.PLAIN, 13));
        spnEdad.setBounds(347, 508, 100, 22);
        panel.add(spnEdad);

        JLabel lblVehiculo = new JLabel("¿Requiere Vehículo?");
        lblVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblVehiculo.setBounds(497, 469, 150, 35);
        panel.add(lblVehiculo);
        rdbtnVehSi = new JRadioButton("Sí");
        rdbtnVehSi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        rdbtnVehSi.setBackground(Color.WHITE);
        rdbtnVehSi.setBounds(497, 508, 50, 20);
        rdbtnVehNo = new JRadioButton("No");
        rdbtnVehNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        rdbtnVehNo.setBackground(Color.WHITE);
        rdbtnVehNo.setBounds(583, 508, 50, 20);
        panel.add(rdbtnVehSi);
        panel.add(rdbtnVehNo);

        rdbtnVehSi.addActionListener(e -> rdbtnVehNo.setSelected(false));
        rdbtnVehNo.addActionListener(e -> rdbtnVehSi.setSelected(false));

        JLabel lblMudarse = new JLabel("¿Dispuesto a mudarse?");
        lblMudarse.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblMudarse.setBounds(700, 470, 182, 33);
        panel.add(lblMudarse);
        rdbtnMudarseSi = new JRadioButton("Sí");
        rdbtnMudarseSi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        rdbtnMudarseSi.setBackground(Color.WHITE);
        rdbtnMudarseSi.setBounds(710, 508, 50, 20);
        rdbtnMudarseNo = new JRadioButton("No");
        rdbtnMudarseNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        rdbtnMudarseNo.setBackground(Color.WHITE);
        rdbtnMudarseNo.setBounds(793, 508, 50, 20);
        panel.add(rdbtnMudarseSi);
        panel.add(rdbtnMudarseNo);

        rdbtnMudarseSi.addActionListener(e -> rdbtnMudarseNo.setSelected(false));
        rdbtnMudarseNo.addActionListener(e -> rdbtnMudarseSi.setSelected(false));

        JLabel lblPrioridad = new JLabel("Prioridad");
        lblPrioridad.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPrioridad.setBounds(206, 745, 100, 16);
        panel.add(lblPrioridad);
        cbxPrioridad = new JComboBox<>();
        cbxPrioridad.setModel(new DefaultComboBoxModel<>(new String[] {
            "<Seleccione>", "Tipo de empleado", "Carrera/técnico/habilidades", "Sexo", "Edad", "Vehiculo", "Mudarse", "Experiencia"
        }));
        cbxPrioridad.setBackground(Color.WHITE);
        cbxPrioridad.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbxPrioridad.setBounds(291, 742, 273, 22);
        panel.add(cbxPrioridad);

        JLabel lblNewLabel = new JLabel("Nombre de la posición");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(628, 202, 201, 16);
        panel.add(lblNewLabel);

        txtPosicion = new JTextField();
        txtPosicion.setBounds(626, 225, 203, 22);
        panel.add(txtPosicion);
        txtPosicion.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Breve descripción del puesto");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(425, 583, 211, 20);
        panel.add(lblNewLabel_1);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(208, 616, 635, 98);
        panel.add(txtDescripcion);
        txtDescripcion.setColumns(10);

        SwingUtilities.invokeLater(() -> getContentPane().requestFocusInWindow());

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setActionCommand("OK");
        buttonPane.add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {
            if (validarCampos()) {
                habilidades.clear();

                if (tipoEmpleado.equals("Universitario")) {
                    carrera = cbxCarrera.getSelectedIndex() != 0 ? cbxCarrera.getSelectedItem().toString() : "";
                    tecnico = "";
                    experiencia = 0;
                } else if (tipoEmpleado.equals("Tecnico")) {
                    carrera = "";
                    tecnico = cbxTecnico.getSelectedIndex() != 0 ? cbxTecnico.getSelectedItem().toString() : "";
                    experiencia = (int) spnExpTecnico.getValue();
                } else if (tipoEmpleado.equals("Obrero")) {
                    carrera = "";
                    tecnico = "";
                    if (cbxHabilidadObrero.getSelectedIndex() != 0) {
                        habilidades.add(cbxHabilidadObrero.getSelectedItem().toString());
                    }
                    experiencia = (int) spnExpObrero.getValue();
                }

                Requisito requisito = new Requisito(
                	    tipoTrabajo,
                	    tipoEmpleado,
                	    carrera,
                	    areaUni,                
                	    tecnico,
                	    habilidades,
                	    sexo,
                	    experiencia,
                	    edad,
                	    veh,
                	    mudarse,
                	    prioridad
                	);


                if (vacanteModificada == null) {
                    boolean exito = Bolsa.getInstance().registrarVacante(txtIdentificador.getText(), idCompania, requisito,
                            txtPosicion.getText(), txtDescripcion.getText(), cbxPrioridad.getSelectedItem().toString());

                    if (exito) {
                        try {
                            Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(this, "Solicitud registrada exitosamente.");
                        clean();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error: no se pudo registrar. El RNC no se encuentra registrado.",
                                "Error en registro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    vacanteModificada.setIDCompania(idCompania);
                    vacanteModificada.setRequisito(requisito);
                    vacanteModificada.setPosicion(txtPosicion.getText());
                    vacanteModificada.setDescripcion(txtDescripcion.getText());
                    vacanteModificada.setPrioridad(cbxPrioridad.getSelectedItem().toString());

                    try {
                        Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(this, "Solicitud modificada exitosamente.");
                    dispose();
                }
            }
        });


        getRootPane().setDefaultButton(btnRegistrar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnCancelar.setActionCommand("Cancel");
        buttonPane.add(btnCancelar);
    }

    private boolean validarCampos() {
        boolean aux = true;
        idCompania = txtRNC.getText().trim();
        fechaStr = txtFecha.getText().trim();
        tipoTrabajo = (String) cbxTipoTrabajo.getSelectedItem();
        tipoEmpleado = (String) cbxTipoEmpleado.getSelectedItem();
        sexo = (String) cbxSexo.getSelectedItem();
        edad = (int) spnEdad.getValue();
        prioridad = (String) cbxPrioridad.getSelectedItem();
        veh = rdbtnVehSi.isSelected();
        mudarse = rdbtnMudarseSi.isSelected();

        if (idCompania.isEmpty() || idCompania.equals("###-#####-#") || tipoTrabajo.equals("<Seleccione>")
                || tipoEmpleado.equals("<Seleccione>") || sexo.equals("<Seleccione>")
                || prioridad.equals("<Seleccione>") || txtPosicion.getText().isEmpty() || txtDescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            aux = false;
        }

        if (panelUni.isVisible() && cbxCarrera.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una carrera.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            aux = false;
        } else if (panelTec.isVisible() && cbxTecnico.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un área técnica.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            aux = false;
        } else if (panelObr.isVisible() && cbxHabilidadObrero.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una habilidad.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            aux = false;
        }

        Date fecha;
        try {
            fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaStr);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use dd/mm/yyyy", "Error",
                    JOptionPane.ERROR_MESSAGE);
            aux = false;
        }

        return aux;
    }

    private void clean() {
        txtIdentificador.setText(Bolsa.genCodVacante());
        txtRNC.setText("");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = formato.format(new Date());
        txtFecha.setText(fechaFormateada);
        cbxTipoTrabajo.setSelectedIndex(0);
        cbxTipoEmpleado.setSelectedIndex(0);
        cbxSexo.setSelectedIndex(0);
        spnEdad.setValue(16);
        cbxPrioridad.setSelectedIndex(0);
        rdbtnVehSi.setSelected(false);
        rdbtnVehNo.setSelected(false);
        rdbtnMudarseSi.setSelected(false);
        rdbtnMudarseNo.setSelected(false);
        spnExpTecnico.setValue(0);
        spnExpObrero.setValue(0);
        cbxHabilidadObrero.setSelectedIndex(0);
        txtPosicion.setText("");
        txtDescripcion.setText("");
        cbxCarrera.setSelectedIndex(0);
        cbxTecnico.setSelectedIndex(0);
        habilidades.clear();
    }

    public void filtroPorArea(int num) {
        switch (num) {
            case 1:
                String[] carrerasAgro = {"<Seleccione>", "Ingeniería Agroindustrial", "Ingeniería Ambiental", "Veterinaria"};
                cbxCarrera.setModel(new DefaultComboBoxModel<>(carrerasAgro));
                cbxCarrera.setSelectedIndex(0);
                cbxCarrera.setEnabled(true);
                break;

            case 2:
                String[] carrerasFinanzas = {"<Seleccione>", "Administración de Empresas", "Contabilidad", "Mercadeo", "Relaciones Internacionales"};
                cbxCarrera.setModel(new DefaultComboBoxModel<>(carrerasFinanzas));
                cbxCarrera.setSelectedIndex(0);
                cbxCarrera.setEnabled(true);
                break;

            case 3:
                String[] carrerasSalud = {"<Seleccione>", "Enfermería", "Medicina", "Odontología", "Psicología Clínica"};
                cbxCarrera.setModel(new DefaultComboBoxModel<>(carrerasSalud));
                cbxCarrera.setSelectedIndex(0);
                cbxCarrera.setEnabled(true);
                break;

            case 4:
                String[] carrerasTecnologia = {"<Seleccione>", "Ingeniería Electrónica", "Ingeniería en Computación", "Ingeniería en Telecomunicaciones", "Ingeniería Mecánica"};
                cbxCarrera.setModel(new DefaultComboBoxModel<>(carrerasTecnologia));
                cbxCarrera.setSelectedIndex(0);
                cbxCarrera.setEnabled(true);
                break;

            case 5:
                String[] carrerasOtras = {"<Seleccione>", "Arquitectura", "Comunicación Social", "Ingeniería Industrial", "Derecho", "Ingeniería Civil"};
                cbxCarrera.setModel(new DefaultComboBoxModel<>(carrerasOtras));
                cbxCarrera.setSelectedIndex(0);
                cbxCarrera.setEnabled(true);
                break;

            default:
                cbxCarrera.setEnabled(false);
                cbxCarrera.setSelectedIndex(0);
                break;
        }
    }
    
    
    public RegVacante(Vacante vacante) {
        this();
        this.vacanteModificada = vacante;

        txtIdentificador.setText(vacante.getIdentificador());
        txtIdentificador.setEnabled(false);
        txtRNC.setText(vacante.getIDCompania());
        txtFecha.setText(new SimpleDateFormat("dd/MM/yyyy").format(vacante.getFecha()));
        cbxPrioridad.setSelectedItem(vacante.getPrioridad());
        txtPosicion.setText(vacante.getPosicion());
        txtDescripcion.setText(vacante.getDescripcion());

        cbxTipoTrabajo.setSelectedItem(vacante.getRequisito().getTipoTrabajo());
        cbxTipoEmpleado.setSelectedItem(vacante.getRequisito().getTipoEmpleado());

        String tipoEmp = vacante.getRequisito().getTipoEmpleado();
        if ("Universitario".equals(tipoEmp)) {
            panelUni.setVisible(true);

            int indexArea = buscarIndiceCombo(cbxArea, vacante.getRequisito().getAreaUni());
            filtroPorArea(indexArea);

            cbxArea.setSelectedIndex(indexArea);
            cbxCarrera.setSelectedItem(vacante.getRequisito().getCarrera());

        } else if ("Tecnico".equals(tipoEmp)) {
            panelTec.setVisible(true);
            cbxTecnico.setSelectedItem(vacante.getRequisito().getTecnico());
            spnExpTecnico.setValue(vacante.getRequisito().getAniosExperiencia());
        } else if ("Obrero".equals(tipoEmp)) {
            panelObr.setVisible(true);
            if (!vacante.getRequisito().getMisHabilidades().isEmpty()) {
                cbxHabilidadObrero.setSelectedItem(vacante.getRequisito().getMisHabilidades().get(0));
            }
            spnExpObrero.setValue(vacante.getRequisito().getAniosExperiencia());
        }

        spnEdad.setValue(vacante.getRequisito().getEdad());
        cbxSexo.setSelectedItem(vacante.getRequisito().getSexo());
        rdbtnVehSi.setSelected(vacante.getRequisito().isVeh());
        rdbtnVehNo.setSelected(!vacante.getRequisito().isVeh());
        rdbtnMudarseSi.setSelected(vacante.getRequisito().isFueraCity());
        rdbtnMudarseNo.setSelected(!vacante.getRequisito().isFueraCity());
    }

    private int buscarIndiceCombo(JComboBox<String> combo, String texto) {
        if (texto == null) return 0;
        texto = texto.trim();
        for (int i = 0; i < combo.getItemCount(); i++) {
            String item = combo.getItemAt(i);
            if (item != null && item.trim().equalsIgnoreCase(texto)) {
                return i;
            }
        }
        return 0; 
    }

}