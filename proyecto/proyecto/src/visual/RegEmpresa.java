package visual;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import logico.Bolsa;
import logico.Empresa;

public class RegEmpresa extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtNombre;
    private JTextField txtRNC;
    private JTextField txtCorreo;
    private JComboBox<String> cbxProvincia;
    private JComboBox<String> cbxArea;
    
    private Empresa empresaExistente;

    public RegEmpresa(Empresa empresaEditar) {
        this.empresaExistente = empresaEditar;

        if (empresaExistente == null) {
            setTitle("Registro de Empresas");
        } else {
            setTitle("Modificar Empresa");
        }

        setBounds(100, 100, 900, 660);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(41, -25, 894, 590);
        contentPanel.add(panel);
        panel.setLayout(null);

        JPanel panelEncabezado = new JPanel();
        panelEncabezado.setBounds(181, 23, 713, 85);
        panel.add(panelEncabezado);
        panelEncabezado.setLayout(null);

        JLabel lblTitulo = new JLabel("REGISTRO DE EMPRESAS");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitulo.setBounds(190, 13, 350, 59);
        panelEncabezado.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNombre.setBounds(194, 141, 90, 16);
        panel.add(lblNombre);

        txtNombre = new JTextField("Ingrese el nombre");
        txtNombre.setForeground(Color.LIGHT_GRAY);
        txtNombre.setBorder(null);
        txtNombre.setBounds(194, 167, 203, 22);
        panel.add(txtNombre);

        JLabel lblRNC = new JLabel("RNC");
        lblRNC.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblRNC.setBounds(548, 138, 90, 22);
        panel.add(lblRNC);

        txtRNC = new JTextField("###-#####-#");
        txtRNC.setForeground(Color.LIGHT_GRAY);
        txtRNC.setBorder(null);
        txtRNC.setBounds(548, 167, 203, 22);
        panel.add(txtRNC);

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

        JLabel lblArea = new JLabel("Área");
        lblArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblArea.setBounds(194, 223, 90, 16);
        panel.add(lblArea);

        cbxArea = new JComboBox<>();
        cbxArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbxArea.setModel(new DefaultComboBoxModel<>(new String[] {
            "<Seleccione>", "Salud", "Tecnología", "Finanzas y Economía", "Idiomas",
            "Gastronomía", "Servicios", "Educación", "Construcción"
        }));
        cbxArea.setBackground(Color.WHITE);
        cbxArea.setBounds(194, 252, 203, 22);
        panel.add(cbxArea);

        JLabel lblProvincia = new JLabel("Provincia");
        lblProvincia.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblProvincia.setBounds(194, 311, 90, 16);
        panel.add(lblProvincia);

        cbxProvincia = new JComboBox<>();
        cbxProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbxProvincia.setModel(new DefaultComboBoxModel<>(new String[] {
            "<Seleccione>", "Azua", "Bahoruco", "Barahona", "Dajabón", "Distrito Nacional",
            "Duarte", "Elías Piña", "El Seibo", "Espaillat", "Hato Mayor", "Hermanas Mirabal",
            "Independencia", "La Altagracia", "La Romana", "La Vega", "María Trinidad Sánchez",
            "Monseñor Nouel", "Monte Cristi", "Monte Plata", "Pedernales", "Peravia",
            "Puerto Plata", "Samaná", "San Cristóbal", "San José de Ocoa", "San Juan",
            "San Pedro de Macorís", "Sánchez Ramírez", "Santiago", "Santiago Rodríguez", "Valverde"
        }));
        cbxProvincia.setBackground(Color.WHITE);
        cbxProvincia.setBounds(194, 340, 203, 22);
        panel.add(cbxProvincia);

        JSeparator separatorNombre = new JSeparator();
        separatorNombre.setForeground(new Color(0, 0, 128));
        separatorNombre.setBounds(194, 189, 240, 2);
        panel.add(separatorNombre);

        JSeparator separatorRNC = new JSeparator();
        separatorRNC.setForeground(new Color(0, 0, 128));
        separatorRNC.setBounds(548, 189, 240, 2);
        panel.add(separatorRNC);

        JLabel lblContacto = new JLabel("Contacto");
        lblContacto.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblContacto.setBounds(548, 224, 90, 22);
        panel.add(lblContacto);

        txtCorreo = new JTextField("example@domain.com");
        txtCorreo.setForeground(Color.LIGHT_GRAY);
        txtCorreo.setBorder(null);
        txtCorreo.setBounds(548, 253, 240, 22);
        panel.add(txtCorreo);

        JSeparator separatorContact = new JSeparator();
        separatorContact.setForeground(new Color(0, 0, 128));
        separatorContact.setBounds(548, 275, 240, 2);
        panel.add(separatorContact);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0, 102, 153));
        panel_1.setBounds(0, 0, 182, 592);
        panel.add(panel_1);

        addFocusListeners();

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        getContentPane().add(buttonPane, BorderLayout.SOUTH);

        JButton okButton = new JButton(empresaExistente == null ? "Registrar" : "Guardar Cambios");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dispose());
        buttonPane.add(cancelButton);

        okButton.addActionListener(e -> registrarEmpresa());
        if (empresaExistente != null) {
            cargarDatosEmpresa();
            txtRNC.setEnabled(false);
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                txtNombre.requestFocusInWindow();
            }
        });
    }

    private void addFocusListeners() {
        txtNombre.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtNombre.getText().equals("Ingrese el nombre")) {
                    txtNombre.setText("");
                    txtNombre.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtNombre.getText().trim().isEmpty()) {
                    txtNombre.setText("Ingrese el nombre");
                    txtNombre.setForeground(Color.LIGHT_GRAY);
                }
            }
        });

        txtRNC.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtRNC.getText().equals("###-#####-#")) {
                    txtRNC.setText("");
                    txtRNC.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtRNC.getText().replaceAll("[^\\d]", "").isEmpty()) {
                    txtRNC.setText("###-#####-#");
                    txtRNC.setForeground(Color.LIGHT_GRAY);
                }
            }
        });

        txtCorreo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtCorreo.getText().equals("example@domain.com")) {
                    txtCorreo.setText("");
                    txtCorreo.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtCorreo.getText().trim().isEmpty()) {
                    txtCorreo.setText("example@domain.com");
                    txtCorreo.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }

    private void cargarDatosEmpresa() {
        txtNombre.setText(empresaExistente.getNombre());
        txtNombre.setForeground(Color.BLACK);

        txtRNC.setText(empresaExistente.getRNC());
        txtRNC.setForeground(Color.GRAY);

        txtCorreo.setText(empresaExistente.getContacto());
        txtCorreo.setForeground(Color.BLACK);

        cbxArea.setSelectedItem(empresaExistente.getArea());
        cbxProvincia.setSelectedItem(empresaExistente.getProvincia());
    }

    private void registrarEmpresa() {
        String nombre = txtNombre.getText().trim();
        String rnc = txtRNC.getText().trim();
        String correo = txtCorreo.getText().trim();
        String area = (String) cbxArea.getSelectedItem();
        String provincia = (String) cbxProvincia.getSelectedItem();

        if (nombre.isEmpty() || nombre.equals("Ingrese el nombre") ||
            rnc.isEmpty() || rnc.equals("###-#####-#") ||
            correo.isEmpty() || correo.equals("example@domain.com") ||
            area.equals("<Seleccione>") || provincia.equals("<Seleccione>")) {

            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente.",
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!rnc.matches("\\d{3}-\\d{5}-\\d")) {
            JOptionPane.showMessageDialog(this, "El RNC debe tener el formato ###-#####-#.",
                                          "Formato inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            JOptionPane.showMessageDialog(this, "Por favor, introduzca un correo electrónico válido.",
                                          "Correo inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (empresaExistente == null) {
            if (Bolsa.getInstance().correoExiste(correo)) {
                JOptionPane.showMessageDialog(this, "Este correo ya está registrado.",
                                              "Correo duplicado", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            String contactoExistente = empresaExistente.getContacto();
            if (contactoExistente == null || !contactoExistente.equalsIgnoreCase(correo)) {
                if (Bolsa.getInstance().correoExiste(correo)) {
                    JOptionPane.showMessageDialog(this, "Este correo ya está registrado.",
                                                  "Correo duplicado", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }

        if (empresaExistente == null) {
            Empresa nuevaEmpresa = new Empresa(nombre, rnc, area, correo, provincia);
            boolean exito = Bolsa.getInstance().registrarEmpresa(nuevaEmpresa);
            if (exito) {
                try {
                    Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                JOptionPane.showMessageDialog(this, "Empresa registrada exitosamente.",
                                              "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(this, "El RNC ya está registrado.",
                                              "Error de Registro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            empresaExistente.setNombre(nombre);
            empresaExistente.setArea(area);
            empresaExistente.setContacto(correo);
            empresaExistente.setProvincia(provincia);

            try {
                Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            JOptionPane.showMessageDialog(this, "Empresa actualizada exitosamente.",
                                          "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }

    }

    private void limpiarFormulario() {
        txtNombre.setText("Ingrese el nombre");
        txtNombre.setForeground(Color.LIGHT_GRAY);

        txtRNC.setText("###-#####-#");
        txtRNC.setForeground(Color.LIGHT_GRAY);
        txtRNC.setEnabled(true);

        txtCorreo.setText("example@domain.com");
        txtCorreo.setForeground(Color.LIGHT_GRAY);

        cbxArea.setSelectedIndex(0);
        cbxProvincia.setSelectedIndex(0);

        txtNombre.requestFocusInWindow();
    }
}
