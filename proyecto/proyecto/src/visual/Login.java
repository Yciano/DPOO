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
        setTitle("Iniciar Sesión");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JPanel panelSide = new JPanel();
        panelSide.setBackground(new Color(0, 102, 153));
        panelSide.setBounds(0, 0, 200, 560);
        contentPanel.add(panelSide);

        JLabel panelMain = new JLabel(new ImageIcon(getClass().getResource("/jobmatch.jpg")));
        panelMain.setBounds(212, 0, 680, 560);
        panelMain.setLayout(null);
        contentPanel.add(panelMain);


        JLabel lblTitulo = new JLabel("INICIAR SESIÓN", SwingConstants.CENTER);
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
        txtClave.setEchoChar((char)0);
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

        JPanel buttonPane = new JPanel();
        buttonPane.setBackground(Color.WHITE);
        buttonPane.setBounds(100, 410, 480, 60);
        buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        panelMain.add(buttonPane);

        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setPreferredSize(new Dimension(180, 40));
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
        buttonPane.add(btnLogin);
        getRootPane().setDefaultButton(btnLogin);

        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.setPreferredSize(new Dimension(180, 40));
        btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 18));
        buttonPane.add(btnRegistro);

        addFocusListeners();

        btnLogin.addActionListener(e -> autenticar());

        btnRegistro.addActionListener(e -> {
            dispose();
            System.exit(0);
        });
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
                    txtClave.setEchoChar((char)0);
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
