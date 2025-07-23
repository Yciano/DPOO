package visual;

import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import logico.Bolsa;
import logico.Usuario;
import logico.Universitario;
import logico.TecnicoSuperior;
import logico.Obrero;
import logico.Solicitud;

public class RegSolEmpleo extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JComboBox<String> cbxTipoTrabajo;
    private JComboBox<String> cbxTipoUsuario;
    private JTextField txtIdentificador; 
    private JTextField txtCedula, txtFecha;
    private JTextField txtNombre, txtApellido, txtEdad;
    private JTextField txtCarrera, txtTecnico, txtHabilidades;
    private JSpinner spnExpTecnico;

    private JPanel panelTipo;
    private CardLayout cardLayout;
    private JSlider sliderSalario;
    private JLabel lblSalarioValor;

    private Bolsa bolsa;

    private final int[] valoresFijos = {15000, 30000, 45000, 60000, 75000, 90000, 100000};
    private static final Pattern CEDULA_PATTERN = Pattern.compile("\\d{3}-\\d{7}-\\d{1}");
    
   

    public RegSolEmpleo(Bolsa bolsa) {
        this.bolsa = bolsa;

        setTitle("Registro de Solicitud de Empleo");
        setResizable(false);
        setBounds(100, 100, 900, 700);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 894, 660);
        contentPanel.add(panel);
        panel.setLayout(null);

        JPanel panelAzul = new JPanel();
        panelAzul.setBackground(new Color(0, 102, 153));
        panelAzul.setBounds(0, 0, 182, 662);
        panel.add(panelAzul);

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBounds(181, 23, 713, 85);
        panel.add(panelTitulo);
        panelTitulo.setLayout(null);

        JLabel lblTitulo = new JLabel("REGISTRO DE SOLICITUD DE EMPLEO");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitulo.setBounds(150, 13, 450, 59);
        panelTitulo.add(lblTitulo);
        
        JLabel lblIdentificador = new JLabel("Identificador");
        lblIdentificador.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblIdentificador.setBounds(194, 130, 120, 20);
        panel.add(lblIdentificador);

        txtIdentificador = new JTextField();
        txtIdentificador.setEditable(false);
        txtIdentificador.setBounds(194, 155, 140, 22);
        panel.add(txtIdentificador);

        JLabel lblCedula = new JLabel("Cédula");
        lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCedula.setBounds(548, 130, 120, 20);
        panel.add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(548, 155, 140, 22);
        panel.add(txtCedula);

        txtCedula.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
            
                if (!Character.isDigit(c)) {
                    e.consume();
                    return;
                }

                String digits = txtCedula.getText().replaceAll("[^\\d]", "");

                if (digits.length() >= 11) {
                    e.consume();
                    return;
                }

                SwingUtilities.invokeLater(() -> {
                    String raw = txtCedula.getText().replaceAll("[^\\d]", "");
                    StringBuilder formatted = new StringBuilder();

                    for (int i = 0; i < raw.length(); i++) {
                        formatted.append(raw.charAt(i));
                        if (i == 2 || i == 9) {
                            formatted.append("-");
                        }
                    }

                    txtCedula.setText(formatted.toString());
                });
            }
        });

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(692, 155, 80, 22);
        panel.add(btnBuscar);

        btnBuscar.addActionListener(e -> {
            String cedula = txtCedula.getText().trim();
            if (!validarCedula(cedula)) {
                JOptionPane.showMessageDialog(this, "Formato de cédula inválido. Use ###-#######-#.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            cargarDatosPorCedula(cedula);
        });

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNombre.setBounds(194, 190, 120, 20);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(194, 215, 203, 22);
        txtNombre.setEditable(false);
        panel.add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido");
        lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblApellido.setBounds(548, 190, 120, 20);
        panel.add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(548, 215, 203, 22);
        txtApellido.setEditable(false);
        panel.add(txtApellido);

        JLabel lblEdad = new JLabel("Edad");
        lblEdad.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEdad.setBounds(194, 250, 120, 20);
        panel.add(lblEdad);

        txtEdad = new JTextField();
        txtEdad.setBounds(194, 275, 80, 22);
        txtEdad.setEditable(false);
        panel.add(txtEdad);

        JLabel lblFecha = new JLabel("Fecha (dd/mm/yyyy)");
        lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFecha.setBounds(548, 250, 160, 20);
        panel.add(lblFecha);

        txtFecha = new JTextField("dd/mm/yyyy");
        txtFecha.setForeground(Color.LIGHT_GRAY);
        txtFecha.setBounds(548, 275, 203, 22);
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
        lblTipoTrabajo.setBounds(194, 310, 140, 20);
        panel.add(lblTipoTrabajo);

        cbxTipoTrabajo = new JComboBox<>();
        cbxTipoTrabajo.setModel(new DefaultComboBoxModel<>(new String[] {
            "<Seleccione>", "Tiempo completo", "Medio tiempo", "Freelance"
        }));
        cbxTipoTrabajo.setBounds(194, 335, 203, 22);
        panel.add(cbxTipoTrabajo);

        JLabel lblTipoUsuario = new JLabel("Tipo de Usuario");
        lblTipoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTipoUsuario.setBounds(548, 310, 150, 20);
        panel.add(lblTipoUsuario);

        cbxTipoUsuario = new JComboBox<>();
        cbxTipoUsuario.setModel(new DefaultComboBoxModel<>(new String[] {
            "<Seleccione>", "Universitario", "Tecnico", "Obrero"
        }));
        cbxTipoUsuario.setBounds(548, 335, 203, 22);
        panel.add(cbxTipoUsuario);

        panelTipo = new JPanel();
        cardLayout = new CardLayout();
        panelTipo.setLayout(cardLayout);
        panelTipo.setBounds(194, 370, 557, 80);
        panel.add(panelTipo);

        JPanel panelUni = new JPanel(null);
        panelUni.setBackground(Color.WHITE);
        JLabel lblCarrera = new JLabel("Carrera:");
        lblCarrera.setBounds(10, 10, 100, 20);
        txtCarrera = new JTextField();
        txtCarrera.setBounds(110, 10, 300, 22);
        txtCarrera.setEditable(false);
        panelUni.add(lblCarrera);
        panelUni.add(txtCarrera);
        panelTipo.add(panelUni, "Universitario");

        JPanel panelTec = new JPanel(null);
        panelTec.setBackground(Color.WHITE);
        JLabel lblTec = new JLabel("Área Técnica:");
        lblTec.setBounds(10, 10, 100, 20);
        txtTecnico = new JTextField();
        txtTecnico.setBounds(110, 10, 200, 22);
        txtTecnico.setEditable(false);
        JLabel lblExpTec = new JLabel("Años de Exp:");
        lblExpTec.setBounds(320, 10, 100, 20);
        spnExpTecnico = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        spnExpTecnico.setBounds(420, 10, 50, 22);
        spnExpTecnico.setEnabled(false);
        panelTec.add(lblTec);
        panelTec.add(txtTecnico);
        panelTec.add(lblExpTec);
        panelTec.add(spnExpTecnico);
        panelTipo.add(panelTec, "Tecnico");

        JPanel panelObr = new JPanel(null);
        panelObr.setBackground(Color.WHITE);
        JLabel lblHab = new JLabel("Habilidades:");
        lblHab.setBounds(10, 10, 100, 20);
        txtHabilidades = new JTextField();
        txtHabilidades.setBounds(110, 10, 400, 22);
        txtHabilidades.setEditable(false);
        panelObr.add(lblHab);
        panelObr.add(txtHabilidades);
        panelTipo.add(panelObr, "Obrero");

        cbxTipoUsuario.addActionListener(e -> {
            String sel = (String) cbxTipoUsuario.getSelectedItem();
            if (sel != null && !sel.equals("<Seleccione>")) {
                cardLayout.show(panelTipo, sel);
            }
        });

        JLabel lblSalario = new JLabel("Salario Esperado (RD$):");
        lblSalario.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSalario.setBounds(194, 460, 200, 20);
        panel.add(lblSalario);

        sliderSalario = new JSlider(15000, 100000, 20000);
        sliderSalario.setBackground(Color.WHITE);
        sliderSalario.setBounds(194, 490, 400, 63);
        sliderSalario.setMajorTickSpacing(15000);
        sliderSalario.setPaintTicks(true);
        sliderSalario.setPaintLabels(true);
        panel.add(sliderSalario);

        lblSalarioValor = new JLabel("RD$ 15,000");
        lblSalarioValor.setBounds(610, 490, 100, 30);
        panel.add(lblSalarioValor);

        sliderSalario.addChangeListener(e -> {
            int val = sliderSalario.getValue();
            int valCercano = obtenerValorCercano(val);
            if (val != valCercano) {
                sliderSalario.setValue(valCercano);
            }
            lblSalarioValor.setText("RD$ " + valCercano);
        });

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String identificador = txtIdentificador.getText().trim();
                String cedula = txtCedula.getText().trim();
                String fecha = txtFecha.getText().trim();
                String tipoTrabajo = (String) cbxTipoTrabajo.getSelectedItem();
                int salario = sliderSalario.getValue();

                if (identificador.isEmpty()) {
                    JOptionPane.showMessageDialog(RegSolEmpleo.this, "Ingrese un identificador para la solicitud.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!validarCedula(cedula)) {
                    JOptionPane.showMessageDialog(RegSolEmpleo.this, "Formato de cédula inválido. Use ###-#######-#.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (fecha.isEmpty() || fecha.equals("dd/mm/yyyy")) {
                    JOptionPane.showMessageDialog(RegSolEmpleo.this, "Ingrese una fecha válida.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (tipoTrabajo == null || tipoTrabajo.equals("<Seleccione>")) {
                    JOptionPane.showMessageDialog(RegSolEmpleo.this, "Seleccione un tipo de trabajo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Usuario usuario = bolsa.buscarEmpleadoByCedula(cedula);
                if (usuario == null) {
                    JOptionPane.showMessageDialog(RegSolEmpleo.this, "Empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Solicitud nuevaSolicitud = new Solicitud(identificador, fecha, salario, tipoTrabajo, usuario);
                nuevaSolicitud.setVacante(null);
                bolsa.getMisSolicitudes().add(nuevaSolicitud);
                JOptionPane.showMessageDialog(RegSolEmpleo.this, "Solicitud registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        });

        buttonPane.add(btnRegistrar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        buttonPane.add(btnCancelar);
    }

    private boolean validarCedula(String cedula) {
        return CEDULA_PATTERN.matcher(cedula).matches();
    }

    private int obtenerValorCercano(int val) {
        int cercano = valoresFijos[0];
        int diffMin = Math.abs(val - valoresFijos[0]);
        for (int v : valoresFijos) {
            int diff = Math.abs(val - v);
            if (diff < diffMin) {
                diffMin = diff;
                cercano = v;
            }
        }
        return cercano;
    }

    private void cargarDatosPorCedula(String cedula) {
        Usuario usuario = bolsa.buscarEmpleadoByCedula(cedula);
        if (usuario != null) {
            txtNombre.setText(usuario.getNombre());
            txtApellido.setText(usuario.getApellido());
            txtEdad.setText(String.valueOf(usuario.getEdad()));

            if (usuario.getTipoTrabajo() != null) {
                cbxTipoTrabajo.setSelectedItem(usuario.getTipoTrabajo());
            } else {
                cbxTipoTrabajo.setSelectedIndex(0);
            }

            if (usuario instanceof Universitario) {
                cbxTipoUsuario.setSelectedItem("Universitario");
                cardLayout.show(panelTipo, "Universitario");

                Universitario u = (Universitario) usuario;
                txtCarrera.setText(u.getCarrera());
                txtTecnico.setText("");
                spnExpTecnico.setValue(0);
                txtHabilidades.setText("");
            } else if (usuario instanceof TecnicoSuperior) {
                cbxTipoUsuario.setSelectedItem("Tecnico");
                cardLayout.show(panelTipo, "Tecnico");

                TecnicoSuperior t = (TecnicoSuperior) usuario;
                txtCarrera.setText("");
                txtTecnico.setText(t.getTecnico());
                spnExpTecnico.setValue(t.getAniosExperiencia());
                txtHabilidades.setText("");
            } else if (usuario instanceof Obrero) {
                cbxTipoUsuario.setSelectedItem("Obrero");
                cardLayout.show(panelTipo, "Obrero");

                Obrero o = (Obrero) usuario;
                txtCarrera.setText("");
                txtTecnico.setText("");
                spnExpTecnico.setValue(0);
                txtHabilidades.setText(o.getMisHabilidades() != null ? String.join(", ", o.getMisHabilidades()) : "");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Empleado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            txtNombre.setText("");
            txtApellido.setText("");
            txtEdad.setText("");
            cbxTipoUsuario.setSelectedIndex(0);
            cbxTipoTrabajo.setSelectedIndex(0);
            cardLayout.show(panelTipo, "Universitario");
            txtCarrera.setText("");
            txtTecnico.setText("");
            spnExpTecnico.setValue(0);
            txtHabilidades.setText("");
        }
    }
}
