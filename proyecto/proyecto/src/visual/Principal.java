package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import logico.Bolsa;  
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
         
        miBolsa = new Bolsa();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1017, 688);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("Registro");
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem = new JMenuItem("Registrar Empresa");
        mntmNewMenuItem.addActionListener(e -> {
            RegEmpresa rgEmp = new RegEmpresa(null);  
            rgEmp.setModal(true);
            rgEmp.setVisible(true);
        });
        mnNewMenu.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Registrar Usuario", null);
        mntmNewMenuItem_1.addActionListener(e -> {
            RegEmpleado rg = new RegEmpleado(null);   
            rg.setModal(true);
            rg.setVisible(true);
        });
        mnNewMenu.add(mntmNewMenuItem_1);

        JMenu mnNewMenu_1 = new JMenu("Listados");
        menuBar.add(mnNewMenu_1);

        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Listado de Empresas");
        mntmNewMenuItem_2.addActionListener(e -> {
            ListadoEmpresas list = new ListadoEmpresas();   
            list.setModal(true);
            list.setVisible(true);
        });
        mnNewMenu_1.add(mntmNewMenuItem_2);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Listado de Usuarios");
        mntmNewMenuItem_3.addActionListener(e -> {
            ListadoUsuario lsUsuario = new ListadoUsuario();
            lsUsuario.setModal(true);
            lsUsuario.setVisible(true);
        });
        mnNewMenu_1.add(mntmNewMenuItem_3);
        
        JMenu mnSolicitudes = new JMenu("Solicitudes");
        menuBar.add(mnSolicitudes);
        
        JMenuItem mntmRegsolempleo = new JMenuItem("RegSolEmpleo");
        mntmRegsolempleo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		RegSolEmpleo RgSolEmp = new RegSolEmpleo(miBolsa);
        		RgSolEmp.setModal(true);
        		RgSolEmp.setVisible(true);
        		
        	}
        });
        mnSolicitudes.add(mntmRegsolempleo);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
    }
}
