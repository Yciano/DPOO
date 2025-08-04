package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;

import logico.Bolsa;
import logico.Session;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

    private JPanel contentPane;
    private Bolsa miBolsa;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());

             
                Principal frame = new Principal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Principal() {
        miBolsa = Bolsa.getInstance();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setTitle("Bolsa Laboral JOBMATCH - Sesi\u00F3n: " + logico.Session.tipoUsuario);
        contentPane = new JPanel() {
            private Image imagen = new ImageIcon(getClass().getResource("/fondo5.png")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagen != null) {
                    g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("Registro");
        mnNewMenu.setIcon(new ImageIcon(Principal.class.getResource("/images/reportes.png")));
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem = new JMenuItem("Registrar Empresa");
        mntmNewMenuItem.setIcon(new ImageIcon(Principal.class.getResource("/images/cliente.png")));
        mntmNewMenuItem.addActionListener(e -> {
            RegEmpresa rgEmp = new RegEmpresa(null);
            rgEmp.setModal(true);
            rgEmp.setVisible(true);
        });
        mnNewMenu.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Registrar Usuario", new ImageIcon(Principal.class.getResource("/images/usuario.png")));
        mntmNewMenuItem_1.addActionListener(e -> {
            RegEmpleado rg = new RegEmpleado(null);
            rg.setModal(true);
            rg.setVisible(true);
        });
        mnNewMenu.add(mntmNewMenuItem_1);

        JMenu mnSolicitudes = new JMenu("Solicitudes");
        mnSolicitudes.setIcon(new ImageIcon(Principal.class.getResource("/images/SOLIS_1.png")));
        menuBar.add(mnSolicitudes);

        JMenuItem mntmRegsolempleo = new JMenuItem("Solicitud De Empleo");
        mntmRegsolempleo.setIcon(new ImageIcon(Principal.class.getResource("/images/reporte1.png")));
        mntmRegsolempleo.addActionListener(e -> {
            RegSolEmpleo RgSolEmp = new RegSolEmpleo();
            RgSolEmp.setModal(true);
            RgSolEmp.setVisible(true);
        });
        mnSolicitudes.add(mntmRegsolempleo);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("Registrar vacante");
        mntmNewMenuItem_5.setIcon(new ImageIcon(Principal.class.getResource("/images/categorias.png")));
        mntmNewMenuItem_5.addActionListener(e -> {
            RegVacante aux = new RegVacante();
            aux.setModal(true);
            aux.setVisible(true);
        });
        mnSolicitudes.add(mntmNewMenuItem_5);

        JMenu mnNewMenu_1 = new JMenu("Listados");
        mnNewMenu_1.setIcon(new ImageIcon(Principal.class.getResource("/images/nuevo-producto.png")));
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Listado de Empresas");
        mntmNewMenuItem_2.setIcon(new ImageIcon(Principal.class.getResource("/images/historial1.png")));
        mntmNewMenuItem_2.addActionListener(e -> {
            ListadoEmpresas list = new ListadoEmpresas();
            list.setModal(true);
            list.setVisible(true);
        });
        mnNewMenu_1.add(mntmNewMenuItem_2);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listado de Usuarios");
        mntmNewMenuItem_3.setIcon(new ImageIcon(Principal.class.getResource("/images/nuevo-cliente.png")));
        mntmNewMenuItem_3.addActionListener(e -> {
            ListadoUsuario lsUsuario = new ListadoUsuario();
            lsUsuario.setModal(true);
            lsUsuario.setVisible(true);
        });
        mnNewMenu_1.add(mntmNewMenuItem_3);

        JMenuItem mntmListadoSolicitudes = new JMenuItem("Listado de Solicitudes");
        mntmListadoSolicitudes.setIcon(new ImageIcon(Principal.class.getResource("/images/anadir.png")));
        mntmListadoSolicitudes.addActionListener(e -> {
            ListadoSolicitudes listSol = new ListadoSolicitudes();
            listSol.setModal(true);
            listSol.setVisible(true);
        });
        mnNewMenu_1.add(mntmListadoSolicitudes);

        JMenuItem mntmNewMenuItem_7 = new JMenuItem("Listado de Contratos");
        mntmNewMenuItem_7.setIcon(new ImageIcon(Principal.class.getResource("/images/AFGAWAG (1).png")));
        mntmNewMenuItem_7.addActionListener(e -> {
            ListaContratos aux = new ListaContratos();
            aux.setModal(true);
            aux.setVisible(true);
        });
        mnNewMenu_1.add(mntmNewMenuItem_7);

        JMenuItem mntmNewMenuItem_8 = new JMenuItem("Listado de Vacantes");
        mntmNewMenuItem_8.setIcon(new ImageIcon(Principal.class.getResource("/images/vacantef (1).png")));
        mntmNewMenuItem_8.addActionListener(e -> {
            ListadoVacantes aux = new ListadoVacantes(0);
            aux.setModal(true);
            aux.setVisible(true);
        });
        mnNewMenu_1.add(mntmNewMenuItem_8);
 
        JMenu mnNewMenu_2 = new JMenu("Buscar");
        mnNewMenu_2.setIcon(new ImageIcon(Principal.class.getResource("/images/lupaf (1).png")));
        menuBar.add(mnNewMenu_2);

        JMenuItem mntmNewMenuItem_4 = new JMenuItem("Contratar");
        mntmNewMenuItem_4.setIcon(new ImageIcon(Principal.class.getResource("/images/contrATF (1).png")));
        mntmNewMenuItem_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Match aux = new Match(0);
                aux.setModal(true);
                aux.setVisible(true);
            }
        });
        mnNewMenu_2.add(mntmNewMenuItem_4);

        JMenu mnNewMenu_3 = new JMenu("Respaldo");
        mnNewMenu_3.setIcon(new ImageIcon(Principal.class.getResource("/images/configuraciones.png")));
        menuBar.add(mnNewMenu_3);

        JMenuItem mntmNewMenuItem_6 = new JMenuItem("Realizar respaldo");
        mntmNewMenuItem_6.setIcon(new ImageIcon(Principal.class.getResource("/images/BACKUP (1).png")));
        mntmNewMenuItem_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Respaldo dialogo = new Respaldo();
                dialogo.setVisible(true);
            }
        });
        mnNewMenu_3.add(mntmNewMenuItem_6);
 
        if (Session.tipoUsuario.equals(Session.USER)) {
            mnNewMenu_2.setEnabled(false);
            mnNewMenu_3.setEnabled(false);
        }


        JPanel panel = new JPanel();
        panel.setOpaque(false);  
        panel.setLayout(null);
        contentPane.add(panel, BorderLayout.CENTER);
    }
}
