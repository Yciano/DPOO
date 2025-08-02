package visual;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import logico.Session;

public class Login extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txtUsuario;
    private JPasswordField txtClave;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login dialog = new Login();
                dialog.setModal(true);
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Javier\\git\\DPOO1\\proyecto\\src\\jobbmatcheo.png"));
        setTitle("JOBMATCH");
        setSize(900, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setPreferredSize(new Dimension(160, 40));
        btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnCerrar.setBounds(502, 400, 160, 40);
        btnCerrar.addActionListener(e -> dispose());
        contentPanel.add(btnCerrar);

        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setPreferredSize(new Dimension(160, 40));
        btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnIniciarSesion.setBounds(322, 400, 160, 40);
        btnIniciarSesion.addActionListener(e -> autenticar());
        contentPanel.add(btnIniciarSesion);

        JPanel panelSide = new JPanel();
        panelSide.setBackground(new Color(0, 102, 153));
        panelSide.setBounds(0, 0, 200, 560);
        contentPanel.add(panelSide);

        JLabel panelMain = new JLabel(new ImageIcon(getClass().getResource("/jobmatchh.jpg")));
        panelMain.setBounds(200, 0, 692, 560);
        panelMain.setLayout(null);
        contentPanel.add(panelMain);

        JLabel lblTitulo = new JLabel("", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 36));
        lblTitulo.setBounds(0, 40, 680, 50);
        panelMain.add(lblTitulo);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblUsuario.setBounds(100, 140, 120, 30);
        panelMain.add(lblUsuario);

        txtUsuario = new JTextField("Ingrese usuario");
        txtUsuario.setForeground(Color.LIGHT_GRAY);
        txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtUsuario.setBorder(null);
        txtUsuario.setBounds(100, 180, 480, 40);
        panelMain.add(txtUsuario);

        JSeparator sepUsuario = new JSeparator();
        sepUsuario.setForeground(new Color(0, 0, 128));
        sepUsuario.setBounds(100, 222, 480, 3);
        panelMain.add(sepUsuario);

        JLabel lblClave = new JLabel("Contraseña");
        lblClave.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblClave.setBounds(100, 260, 150, 30);
        panelMain.add(lblClave);

        txtClave = new JPasswordField();
        txtClave.setHorizontalAlignment(SwingConstants.LEFT);
        txtClave.setEchoChar((char) 0);
        txtClave.setText("********");
        txtClave.setForeground(Color.LIGHT_GRAY);
        txtClave.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtClave.setBorder(null);
        txtClave.setBounds(100, 300, 480, 40);
        panelMain.add(txtClave);

        JSeparator sepClave = new JSeparator();
        sepClave.setForeground(new Color(0, 0, 128));
        sepClave.setBounds(100, 342, 480, 3);
        panelMain.add(sepClave);

        addFocusListeners();
    }

    private void addFocusListeners() {
        txtUsuario.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtUsuario.getText().equals("Ingrese usuario")) {
                    txtUsuario.setText("");
                    txtUsuario.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtUsuario.getText().trim().isEmpty()) {
                    txtUsuario.setText("Ingrese usuario");
                    txtUsuario.setForeground(Color.LIGHT_GRAY);
                }
            }
        });

        txtClave.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String pass = new String(txtClave.getPassword());
                if (pass.equals("********")) {
                    txtClave.setText("");
                    txtClave.setEchoChar('•');
                    txtClave.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String pass = new String(txtClave.getPassword());
                if (pass.trim().isEmpty()) {
                    txtClave.setEchoChar((char) 0);
                    txtClave.setText("********");
                    txtClave.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }

    private void autenticar() {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword()).trim();

        if (usuario.isEmpty() || usuario.equals("Ingrese usuario") ||
            clave.isEmpty() || clave.equals("********")) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (usuario.equals("admin") && clave.equals("1234")) {
            Session.tipoUsuario = "ADMIN";
            JOptionPane.showMessageDialog(this, "Login exitoso como ADMIN.");
            dispose();
            Principal principal = new Principal();
            principal.setVisible(true);
        } else if (usuario.equals("user") && clave.equals("user1234")) {
            Session.tipoUsuario = "USER";
            JOptionPane.showMessageDialog(this, "Login exitoso como USER.");
            dispose();
            Principal principal = new Principal();
            principal.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
