package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import logico.Bolsa;
import logico.Session;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
        setBounds(100, 100, 1194, 761);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnRegistro = new JMenu("Registro");
        menuBar.add(mnRegistro);

        JMenuItem itemRegistrarEmpresa = new JMenuItem("Registrar Empresa");
        itemRegistrarEmpresa.addActionListener(e -> {
            RegEmpresa rgEmp = new RegEmpresa(null);
            rgEmp.setModal(true);
            rgEmp.setVisible(true);
        });
        mnRegistro.add(itemRegistrarEmpresa);

        JMenuItem itemRegistrarUsuario = new JMenuItem("Registrar Usuario");
        itemRegistrarUsuario.addActionListener(e -> {
            RegEmpleado rg = new RegEmpleado(null);
            rg.setModal(true);
            rg.setVisible(true);
        });
        mnRegistro.add(itemRegistrarUsuario);

        JMenu mnListados = new JMenu("Listados");
        menuBar.add(mnListados);

        JMenuItem itemListadoEmpresas = new JMenuItem("Listado de Empresas");
        itemListadoEmpresas.addActionListener(e -> {
            ListadoEmpresas list = new ListadoEmpresas();
            list.setModal(true);
            list.setVisible(true);
        });
        mnListados.add(itemListadoEmpresas);

        JMenuItem itemListadoUsuarios = new JMenuItem("Listado de Usuarios");
        itemListadoUsuarios.addActionListener(e -> {
            ListadoUsuario lsUsuario = new ListadoUsuario();
            lsUsuario.setModal(true);
            lsUsuario.setVisible(true);
        });
        mnListados.add(itemListadoUsuarios);

        JMenuItem itemListadoSolicitudes = new JMenuItem("Listado de Solicitudes");
        itemListadoSolicitudes.addActionListener(e -> {
            ListadoSolicitudes listSol = new ListadoSolicitudes();
            listSol.setModal(true);
            listSol.setVisible(true);
        });
        mnListados.add(itemListadoSolicitudes);

        JMenu mnSolicitudes = new JMenu("Solicitudes");
        menuBar.add(mnSolicitudes);

        JMenuItem itemSolicitudEmpleo = new JMenuItem("Solicitud De Empleo");
        itemSolicitudEmpleo.addActionListener(e -> {
            RegSolEmpleo RgSolEmp = new RegSolEmpleo(Bolsa.getInstance());
            RgSolEmp.setModal(true);
            RgSolEmp.setVisible(true);
        });
        mnSolicitudes.add(itemSolicitudEmpleo);

        JMenuItem itemRegistrarVacante = new JMenuItem("Registrar vacante");
        itemRegistrarVacante.addActionListener(e -> {
            RegVacante aux = new RegVacante();
            aux.setModal(true);
            aux.setVisible(true);
        });
        mnSolicitudes.add(itemRegistrarVacante);

        JMenu menuBuscar = new JMenu("Buscar");
        menuBar.add(menuBuscar);

        JMenuItem itemContratar = new JMenuItem("Contratar");
        itemContratar.addActionListener(e -> {
            Match aux = new Match();
            aux.setModal(true);
            aux.setVisible(true);
        });
        menuBuscar.add(itemContratar);

        JMenu menuRespaldo = new JMenu("Respaldo");
        menuBar.add(menuRespaldo);

        JMenuItem itemRespaldo = new JMenuItem("Realizar respaldo");
        itemRespaldo.addActionListener(e -> {
            Respaldo dialogo = new Respaldo();
            dialogo.setVisible(true);
        });
        menuRespaldo.add(itemRespaldo);
 
        if (Session.tipoUsuario.equals(Session.USER)) {
            menuBuscar.setVisible(false);
            menuRespaldo.setVisible(false);
        } 
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
    }
}
