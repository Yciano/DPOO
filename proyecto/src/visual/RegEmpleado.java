package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Bolsa;
import logico.Obrero;
import logico.Session;
import logico.TecnicoSuperior;
import logico.Universitario;
import logico.Usuario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import java.awt.CardLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class RegEmpleado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtCedula;
	private JTextField txtCorreo;
	private JComboBox cbxSexo;
	private JSpinner spnEdad;
	private JComboBox cbxProvincia;
	private JComboBox cbxTrabajo;
	private JComboBox cbxEstado;
	private JRadioButton rdbtnSiMudarse;
	private JRadioButton rdbtnNoMudarse;
	private JSpinner spnExpObre;
	private JRadioButton rdbtnSiLicencia;
	private JRadioButton rdbtnNoLicencia;
	private JRadioButton rdbtnSiVehiculo;
	private JRadioButton rdbtnNoVehiculo;
	private JComboBox cbxTipoTrabajador;
	private JComboBox cbxCarrera;
	private JPanel panelSeleccion;
	private JPanel panelObrero;
	private JPanel panelEstudiante;
	private JPanel panelTecnico;
	private JComboBox cbxTecnico;
	private JSpinner spnExpTec;
	private JPanel panelBlanco;
	private JLabel lblFotoPreview;
	private JButton btnSubirImagen;
	private String rutaImagenSeleccionada = null;
	private final String IMAGEN_DEFAULT = "src/Default.jpg";
	private static Object[] row;
	private static DefaultTableModel modelo;
	private String selected = null;
	private ArrayList<String> misHabilidades;

	
	private static Usuario update = null;
	private JComboBox cbxArea;
	private JTable table;


	public static void main(String[] args) {
		try {
			RegEmpleado dialog = new RegEmpleado(update);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public RegEmpleado(Usuario usr) {
		this.update = usr;
		if(update == null) {
			setTitle("Registro de Usuarios");
		}else
		{
			setTitle("Modificar Usuarios");
		}		setResizable(false);
		setBounds(100, 100, 934, 870);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(0, 0, 928, 800);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(181, 23, 747, 85);
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JLabel lblNewLabel = new JLabel("REGISTRO DE USUARIOS");
					lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
					lblNewLabel.setBounds(190, 13, 313, 59);
					panel_1.add(lblNewLabel);
				}
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Nombre");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblNewLabel_1.setBounds(194, 141, 90, 16);
				panel.add(lblNewLabel_1);
			}
			
			txtNombre = new JTextField();
			txtNombre.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if(txtNombre.getText().equals("Ingrese su nombre")) {
						txtNombre.setText("");
						txtNombre.setForeground(Color.black);
					}
					
					if(txtApellidos.getText().isEmpty()) {
						txtApellidos.setText("Ingrese sus apellidos");
						txtApellidos.setForeground(Color.gray);

					}
					if(txtCedula.getText().isEmpty()) {
						txtCedula.setText("###-#######-#");
						txtCedula.setForeground(Color.gray);

					}
					if(txtCorreo.getText().isEmpty()) {
						txtCorreo.setText("Ingrese su correo");
						txtCorreo.setForeground(Color.gray);
					}
					
					
				}
			});
			txtNombre.setForeground(Color.LIGHT_GRAY);
			txtNombre.setText("Ingrese su nombre");
			txtNombre.setBorder(null);
			txtNombre.setBounds(194, 167, 203, 22);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			{
				JSeparator separator = new JSeparator();
				separator.setForeground(new Color(0, 0, 128));
				separator.setBounds(194, 189, 240, 2);
				panel.add(separator);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Apellidos");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblNewLabel_2.setBounds(548, 138, 90, 22);
				panel.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Edad");
				lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblNewLabel_3.setBounds(435, 318, 56, 16);
				panel.add(lblNewLabel_3);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("C\u00E9dula");
				lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblNewLabel_4.setBounds(194, 223, 71, 16);
				panel.add(lblNewLabel_4);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("Correo");
				lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblNewLabel_5.setBounds(548, 224, 56, 16);
				panel.add(lblNewLabel_5);
			}
			{
				JLabel lblNewLabel_6 = new JLabel("Sexo");
				lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblNewLabel_6.setBounds(194, 318, 56, 16);
				panel.add(lblNewLabel_6);
			}
			{
				JLabel lblNewLabel_7 = new JLabel("Provincia");
				lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblNewLabel_7.setBounds(632, 318, 77, 16);
				panel.add(lblNewLabel_7);
			}
			{
				JLabel lblNewLabel_8 = new JLabel("Tipo de trabajo");
				lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblNewLabel_8.setBounds(194, 390, 137, 24);
				panel.add(lblNewLabel_8);
			}
			{
				JLabel lblNewLabel_9 = new JLabel("Estado");
				lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblNewLabel_9.setBounds(435, 390, 76, 16);
				panel.add(lblNewLabel_9);
			}
			{
				JLabel lblNewLabel_10 = new JLabel("\u00BFEstaria dispuesto a mudarse?");
				lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblNewLabel_10.setBounds(632, 497, 227, 16);
				panel.add(lblNewLabel_10);
			}
			{
				JLabel lblNewLabel_11 = new JLabel("\u00BFPosee vehiculo?");
				lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblNewLabel_11.setBounds(435, 497, 159, 16);
				panel.add(lblNewLabel_11);
			}
			{
				JLabel lblNewLabel_12 = new JLabel("\u00BFTiene licencia?");
				lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblNewLabel_12.setBounds(194, 497, 125, 16);
				panel.add(lblNewLabel_12);
			}
			lblFotoPreview = new JLabel();
			lblFotoPreview.setBounds(20, 200, 140, 140); 
			lblFotoPreview.setBorder(new LineBorder(new Color(128, 128, 128), 5, true));
			lblFotoPreview.setIcon(escalarImagen(IMAGEN_DEFAULT));
			panel.add(lblFotoPreview);

			btnSubirImagen = new JButton("Subir imagen");
			btnSubirImagen.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnSubirImagen.setBounds(30, 350, 120, 25);
			panel.add(btnSubirImagen);

			btnSubirImagen.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        JFileChooser selector = new JFileChooser();
			        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes JPG y PNG", "jpg", "png");
			        selector.setFileFilter(filtro);
			        int resultado = selector.showOpenDialog(null);
			        if (resultado == JFileChooser.APPROVE_OPTION) {
			            File archivoSeleccionado = selector.getSelectedFile();
			            rutaImagenSeleccionada = archivoSeleccionado.getAbsolutePath();
			            lblFotoPreview.setIcon(escalarImagen(rutaImagenSeleccionada));
			        }
			    }
			});

			{
				txtApellidos = new JTextField();
				txtApellidos.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if(txtApellidos.getText().equals("Ingrese sus apellidos")) {
							txtApellidos.setText("");
							txtApellidos.setForeground(Color.black);
						}
						
						if(txtNombre.getText().isEmpty()) {
							txtNombre.setText("Ingrese su nombre");
							txtNombre.setForeground(Color.gray);

						}
						if(txtCedula.getText().isEmpty()) {
							txtCedula.setText("###-#######-#");
							txtCedula.setForeground(Color.gray);

						}
						if(txtCorreo.getText().isEmpty()) {
							txtCorreo.setText("Ingrese su correo");
							txtCorreo.setForeground(Color.gray);
						}
					}
				});
				txtApellidos.setText("Ingrese sus apellidos");
				txtApellidos.setForeground(Color.LIGHT_GRAY);
				txtApellidos.setColumns(10);
				txtApellidos.setBorder(null);
				txtApellidos.setBounds(548, 167, 203, 22);
				panel.add(txtApellidos);
			}
			{
				JSeparator separator = new JSeparator();
				separator.setForeground(new Color(0, 0, 128));
				separator.setBounds(548, 189, 240, 2);
				panel.add(separator);
			}
			{
				txtCedula = new JTextField();
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
				txtCedula.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if(txtCedula.getText().equals("###-#######-#")) {
							txtCedula.setText("");
							txtCedula.setForeground(Color.black);
						}
						
						if(txtNombre.getText().isEmpty()) {
							txtNombre.setText("Ingrese su nombre");
							txtNombre.setForeground(Color.gray);

						}
						if(txtApellidos.getText().isEmpty()) {
							txtApellidos.setText("Ingrese sus apellidos");
							txtApellidos.setForeground(Color.gray);

						}
						if(txtCorreo.getText().isEmpty()) {
							txtCorreo.setText("Ingrese su correo");
							txtCorreo.setForeground(Color.gray);
						}
					}
				});
				txtCedula.setFont(new Font("Tahoma", Font.PLAIN, 13));
				txtCedula.setText("###-#######-#");
				txtCedula.setForeground(Color.LIGHT_GRAY);
				txtCedula.setColumns(10);
				txtCedula.setBorder(null);
				txtCedula.setBounds(194, 252, 203, 22);
				panel.add(txtCedula);
			}
			{
				JSeparator separator = new JSeparator();
				separator.setForeground(new Color(0, 0, 128));
				separator.setBounds(194, 274, 240, 2);
				panel.add(separator);
			}
			{
				txtCorreo = new JTextField();
				txtCorreo.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if(txtCorreo.getText().equals("Ingrese su correo")) {
							txtCorreo.setText("");
							txtCorreo.setForeground(Color.black);
						}
						
						if(txtNombre.getText().isEmpty()) {
							txtNombre.setText("Ingrese su nombre");
							txtNombre.setForeground(Color.gray);

						}
						if(txtApellidos.getText().isEmpty()) {
							txtApellidos.setText("Ingrese sus apellidos");
							txtApellidos.setForeground(Color.gray);

						}
						if(txtCedula.getText().isEmpty()) {
							txtCedula.setText("###-#######-#");
							txtCedula.setForeground(Color.gray);
						}
					}
				});
				txtCorreo.setText("Ingrese su correo");
				txtCorreo.setForeground(Color.LIGHT_GRAY);
				txtCorreo.setColumns(10);
				txtCorreo.setBorder(null);
				txtCorreo.setBounds(548, 252, 203, 22);
				panel.add(txtCorreo);
			}
			{
				JSeparator separator = new JSeparator();
				separator.setForeground(new Color(0, 0, 128));
				separator.setBounds(548, 274, 240, 2);
				panel.add(separator);
			}
			
			cbxSexo = new JComboBox();
			cbxSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
			cbxSexo.setForeground(Color.BLACK);
			cbxSexo.setBackground(Color.WHITE);
			cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "F", "M"}));
			cbxSexo.setBounds(234, 316, 119, 22);
			panel.add(cbxSexo);
			
			cbxProvincia = new JComboBox();
			cbxProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
			cbxProvincia.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Azua", "Bahoruco", "Barahona", "Dajab\u00F3n", "Distrito Nacional", "Duarte", "El\u00EDas Pi\u00F1a", "El Seibo", "Espaillat", "Hato Mayor", "Hermanas Mirabal", "Independencia", "La Altagracia", "La Romana", "La Vega", "Mar\u00EDa Trinidad S\u00E1nchez", "Monse\u00F1or Nouel", "Monte Cristi", "Monte Plata", "Pedernales", "Peravia", "Puerto Plata", "Saman\u00E1", "San Crist\u00F3bal", "San Jos\u00E9 de Ocoa", "San Juan", "San Pedro de Macor\u00EDs", "S\u00E1nchez Ram\u00EDrez", "Santiago", "Santiago Rodr\u00EDguez", "Valverde"}));
			cbxProvincia.setBackground(Color.WHITE);
			cbxProvincia.setBounds(711, 316, 148, 22);
			panel.add(cbxProvincia);
			
			spnEdad = new JSpinner();
			spnEdad.setModel(new SpinnerNumberModel(16, 16, 70, 1));
			spnEdad.setBounds(491, 316, 56, 22);
			panel.add(spnEdad);
			{
				cbxTrabajo = new JComboBox();
				cbxTrabajo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				cbxTrabajo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Tiempo completo", "A medio tiempo", "Freelance"}));
				cbxTrabajo.setBackground(Color.WHITE);
				cbxTrabajo.setBounds(194, 427, 137, 22);
				panel.add(cbxTrabajo);
			}
			{
				cbxEstado = new JComboBox();
				cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Contratado", "Desempleado"}));
				cbxEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
				cbxEstado.setBackground(Color.WHITE);
				cbxEstado.setBounds(435, 427, 119, 22);
				panel.add(cbxEstado);
			}
			
			rdbtnSiMudarse = new JRadioButton("Si");
			rdbtnSiMudarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnNoMudarse.setSelected(false);
				}
			});
			rdbtnSiMudarse.setFont(new Font("Tahoma", Font.PLAIN, 15));
			rdbtnSiMudarse.setBackground(Color.WHITE);
			rdbtnSiMudarse.setBounds(653, 523, 56, 25);
			panel.add(rdbtnSiMudarse);
			
			rdbtnNoMudarse = new JRadioButton("No");
			rdbtnNoMudarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnSiMudarse.setSelected(false);
				}
			});
			rdbtnNoMudarse.setFont(new Font("Tahoma", Font.PLAIN, 15));
			rdbtnNoMudarse.setBackground(Color.WHITE);
			rdbtnNoMudarse.setBounds(748, 523, 65, 25);
			panel.add(rdbtnNoMudarse);
			
			rdbtnSiLicencia = new JRadioButton("Si");
			rdbtnSiLicencia.setFont(new Font("Tahoma", Font.PLAIN, 15));
			rdbtnSiLicencia.setBackground(Color.WHITE);
			rdbtnSiLicencia.setBounds(194, 523, 56, 25);
			panel.add(rdbtnSiLicencia);

			rdbtnNoLicencia = new JRadioButton("No");
			rdbtnNoLicencia.setFont(new Font("Tahoma", Font.PLAIN, 15));
			rdbtnNoLicencia.setBackground(Color.WHITE);
			rdbtnNoLicencia.setBounds(254, 523, 65, 25);
			panel.add(rdbtnNoLicencia);

			rdbtnSiLicencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnNoLicencia.setSelected(false);
				}
			});
			rdbtnNoLicencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnSiLicencia.setSelected(false);
				}
			});

			rdbtnSiVehiculo = new JRadioButton("Si");
			rdbtnSiVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			rdbtnSiVehiculo.setBackground(Color.WHITE);
			rdbtnSiVehiculo.setBounds(435, 523, 56, 25);
			panel.add(rdbtnSiVehiculo);

			rdbtnNoVehiculo = new JRadioButton("No");
			rdbtnNoVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 15));
			rdbtnNoVehiculo.setBackground(Color.WHITE);
			rdbtnNoVehiculo.setBounds(495, 523, 65, 25);
			panel.add(rdbtnNoVehiculo);
			
			JLabel lblNewLabel_13 = new JLabel("Tipo de trabajador");
			lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel_13.setBounds(632, 386, 135, 25);
			panel.add(lblNewLabel_13);
			
			cbxTipoTrabajador = new JComboBox();
			cbxTipoTrabajador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(cbxTipoTrabajador.getSelectedIndex() == 0) {
						panelBlanco.setVisible(true);
						panelEstudiante.setVisible(false);
						panelObrero.setVisible(false);
						panelTecnico.setVisible(false);
					}
					
				else if(cbxTipoTrabajador.getSelectedIndex() == 1) {
						panelEstudiante.setVisible(true);
						panelObrero.setVisible(false);
						panelTecnico.setVisible(false);
						panelBlanco.setVisible(false);

					}
					else if(cbxTipoTrabajador.getSelectedIndex() == 2) {
						panelEstudiante.setVisible(false);
						panelObrero.setVisible(true);
						panelTecnico.setVisible(false);
						panelBlanco.setVisible(false);

					}
					else if(cbxTipoTrabajador.getSelectedIndex() == 3) {
						panelEstudiante.setVisible(false);
						panelObrero.setVisible(false);
						panelTecnico.setVisible(true);
						panelBlanco.setVisible(false);

					}
				}
			});
			cbxTipoTrabajador.setBackground(Color.WHITE);
			cbxTipoTrabajador.setFont(new Font("Tahoma", Font.PLAIN, 14));
			cbxTipoTrabajador.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Universitario", "Obrero", "T\u00E9cnico Superior"}));
			cbxTipoTrabajador.setBounds(632, 428, 135, 22);
			panel.add(cbxTipoTrabajador);
			{
				panelSeleccion = new JPanel();
				panelSeleccion.setBounds(189, 563, 705, 217);
				panel.add(panelSeleccion);
				panelSeleccion.setLayout(new CardLayout(0, 0));
				{
					panelBlanco = new JPanel();
					panelBlanco.setBackground(Color.WHITE);
					panelSeleccion.add(panelBlanco, "name_223586367772000");
					panelBlanco.setLayout(null);
					{
						JLabel lblNewLabel_19 = new JLabel("SELECCIONE TIPO DE TRABAJADOR");
						lblNewLabel_19.setFont(new Font("Tahoma", Font.BOLD, 18));
						lblNewLabel_19.setBounds(183, 40, 344, 41);
						panelBlanco.add(lblNewLabel_19);
					}
				}
				
				{
					
					final JButton btnModificar = new JButton("Modificar");
					final JButton btnEliminar = new JButton("Eliminar");
					final JButton btnAgregarGuardar = new JButton("Agregar a la lista");
					
					final int[] filaModificando = {-1}; 
					final boolean[] modoModificar = {false};

					panelObrero = new JPanel();
					panelObrero.setBackground(Color.WHITE);
					panelSeleccion.add(panelObrero, "name_214559917214100");
					panelObrero.setLayout(null);

					{
					    JLabel lblNewLabel_14 = new JLabel("Habilidades");
					    lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 16));
					    lblNewLabel_14.setBounds(12, 36, 91, 16);
					    panelObrero.add(lblNewLabel_14);
					}
					{
					    JLabel lblNewLabel_15 = new JLabel("Años de experiencia");
					    lblNewLabel_15.setFont(new Font("Tahoma", Font.PLAIN, 16));
					    lblNewLabel_15.setBounds(12, 100, 176, 16);
					    panelObrero.add(lblNewLabel_15);
					}

					spnExpObre = new JSpinner();
					spnExpObre.setModel(new SpinnerNumberModel(0, 0, null, 1));
					spnExpObre.setBounds(12, 129, 137, 22);
					panelObrero.add(spnExpObre);

					JComboBox<String> comboBox = new JComboBox<>();
					comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
					    "<Seleccione>", "Construcción general", "Albañilería", "Pintura y acabados", "Soldadura",
					    "Instalaciones sanitarias", "Limpieza y desinfección", "Manejo de herramientas",
					    "Carga y descarga de materiales", "Jardinería"
					}));
					comboBox.setBounds(12, 65, 169, 22);
					panelObrero.add(comboBox);

					JPanel panelHabilidad = new JPanel();
					panelHabilidad.setForeground(Color.WHITE);
					panelHabilidad.setBackground(Color.WHITE);
					panelHabilidad.setBounds(298, 36, 395, 152);
					panelObrero.add(panelHabilidad);
					panelHabilidad.setLayout(new BorderLayout(0, 0));

					JScrollPane scrollPane = new JScrollPane();
					panelHabilidad.add(scrollPane, BorderLayout.CENTER);

					table = new JTable();
					table.getTableHeader().setReorderingAllowed(false);
					table.setBackground(Color.WHITE);

					modelo = new DefaultTableModel() {
					    public boolean isCellEditable(int row, int column) {
					        return false;  
					    }
					};

					String[] header = { "Habilidad", "Años de experiencia" };
					modelo.setColumnIdentifiers(header);
					table.setModel(modelo);
					scrollPane.setViewportView(table);

					btnAgregarGuardar.setBounds(22, 182, 146, 25);
					panelObrero.add(btnAgregarGuardar);

					JLabel lblNewLabel_21 = new JLabel("Lista de habilidades");
					lblNewLabel_21.setFont(new Font("Tahoma", Font.BOLD, 14));
					lblNewLabel_21.setBounds(430, 13, 137, 16);
					panelObrero.add(lblNewLabel_21);

					btnModificar.setBounds(512, 190, 90, 25);
					btnEliminar.setBounds(605, 190, 90, 25);
					panelObrero.add(btnModificar);
					panelObrero.add(btnEliminar);

					btnModificar.setEnabled(false);
					btnEliminar.setEnabled(false);

					table.getSelectionModel().addListSelectionListener(e -> {
					    if (!e.getValueIsAdjusting()) {
					        int fila = table.getSelectedRow();
					        boolean filaSeleccionada = fila >= 0;
					        btnModificar.setEnabled(filaSeleccionada);
					        btnEliminar.setEnabled(filaSeleccionada);
					        if (!filaSeleccionada) {
					            modoModificar[0] = false;
					            filaModificando[0] = -1;
					            btnAgregarGuardar.setText("Agregar a la lista");
					        }
					    }
					});

					btnEliminar.addActionListener(e -> {
					    int fila = table.getSelectedRow();
					    if (fila >= 0) {
					        modelo.removeRow(fila);

					        if (modoModificar[0] && fila == filaModificando[0]) {
					            modoModificar[0] = false;
					            filaModificando[0] = -1;
					            btnAgregarGuardar.setText("Agregar a la lista");
					        }
					        btnModificar.setEnabled(false);
					        btnEliminar.setEnabled(false);
					    }
					});

					btnModificar.addActionListener(e -> {
					    int fila = table.getSelectedRow();
					    if (fila >= 0) {
					        String habilidad = (String) modelo.getValueAt(fila, 0);
					        int anios = (int) modelo.getValueAt(fila, 1);

					        comboBox.setSelectedItem(habilidad);
					        spnExpObre.setValue(anios);
					        modoModificar[0] = true;
					        filaModificando[0] = fila;
					        btnAgregarGuardar.setText("Guardar cambios");
					    }
					});

					btnAgregarGuardar.addActionListener(new ActionListener() {
					    public void actionPerformed(ActionEvent e) {
					        String habilidad = (String) comboBox.getSelectedItem();
					        int anios = (Integer) spnExpObre.getValue();

					        if (habilidad == null || habilidad.equals("<Seleccione>")) {
					            JOptionPane.showMessageDialog(panelObrero, "Por favor seleccione una habilidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
					            return;
					        }

					        if (anios <= 0) {
					            JOptionPane.showMessageDialog(panelObrero, "Ingrese un número válido de años de experiencia.", "Error", JOptionPane.ERROR_MESSAGE);
					            return;
					        }

					        boolean habilidadRepetida = false;
					        for (int i = 0; i < modelo.getRowCount(); i++) {
					            String habEnTabla = (String) modelo.getValueAt(i, 0);
					            if (habEnTabla.equalsIgnoreCase(habilidad)) {
					                if (modoModificar[0] && i == filaModificando[0]) {
					                    continue;
					                }
					                habilidadRepetida = true;
					                break;
					            }
					        }

					        if (habilidadRepetida) {
					            JOptionPane.showMessageDialog(panelObrero, "Ya ha incluido esa habilidad en su registro.", "Advertencia", JOptionPane.WARNING_MESSAGE);
					            return;
					        }

					        if (modoModificar[0]) {
					            if (filaModificando[0] >= 0 && filaModificando[0] < modelo.getRowCount()) {
					                modelo.setValueAt(habilidad, filaModificando[0], 0);
					                modelo.setValueAt(anios, filaModificando[0], 1);
					            }
					            modoModificar[0] = false;
					            filaModificando[0] = -1;
					            btnModificar.setEnabled(false);
					            btnEliminar.setEnabled(false);
					            table.clearSelection();
					            btnAgregarGuardar.setText("Agregar a la lista");
					        } else {
					            modelo.addRow(new Object[] { habilidad, anios });
					        }
					        comboBox.setSelectedIndex(0);
					        spnExpObre.setValue(0);
					    }
					});

				{
					panelEstudiante = new JPanel();
					panelEstudiante.setBackground(Color.WHITE);
					panelSeleccion.add(panelEstudiante, "name_214684784105100");
					panelEstudiante.setLayout(null);
					
					JLabel lblNewLabel_16 = new JLabel("Carrera");
					lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 16));
					lblNewLabel_16.setBounds(356, 61, 75, 16);
					panelEstudiante.add(lblNewLabel_16);
					
					cbxCarrera = new JComboBox();
					cbxCarrera.setEnabled(false);
					cbxCarrera.setFont(new Font("Tahoma", Font.PLAIN, 14));
					cbxCarrera.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Administraci\u00F3n de Empresas", "Arquitectura", "Comunicaci\u00F3n Social", "Contabilidad", "Derecho", "Ingenier\u00EDa Agroindustrial", "Ingenier\u00EDa Ambiental", "Ingenier\u00EDa Civil", "Ingenier\u00EDa Electr\u00F3nica", "Ingenier\u00EDa en Computaci\u00F3n", "Ingenier\u00EDa en Telecomunicaciones", "Ingenier\u00EDa Industrial", "Ingenier\u00EDa Mec\u00E1nica", "Medicina", "Mercadeo", "Odontolog\u00EDa", "Psicolog\u00EDa Cl\u00EDnica ", "Relaciones Internacionales"}));
					cbxCarrera.setBounds(425, 58, 238, 22);
					panelEstudiante.add(cbxCarrera);
					
					JLabel lblNewLabel_20 = new JLabel("\u00C1rea acad\u00E9mica: ");
					lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 16));
					lblNewLabel_20.setBounds(12, 61, 128, 17);
					panelEstudiante.add(lblNewLabel_20);
					
					cbxArea = new JComboBox();
					cbxArea.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						filtroPorArea(cbxArea.getSelectedIndex());
						}
						
					});
					cbxArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
					cbxArea.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Ciencias Agropecuarias", "Finanzas", "Salud", "Tecnolog\u00EDa", "Otros"}));
					cbxArea.setBounds(141, 59, 167, 22);
					panelEstudiante.add(cbxArea);
				}
				{
					panelTecnico = new JPanel();
					panelTecnico.setBackground(Color.WHITE);
					panelSeleccion.add(panelTecnico, "name_214687980656800");
					panelTecnico.setLayout(null);
					
					JLabel lblNewLabel_17 = new JLabel("T\u00E9cnico");
					lblNewLabel_17.setFont(new Font("Tahoma", Font.PLAIN, 16));
					lblNewLabel_17.setBounds(12, 50, 56, 16);
					panelTecnico.add(lblNewLabel_17);
					
					JLabel lblNewLabel_18 = new JLabel("A\u00F1os de experiencia");
					lblNewLabel_18.setFont(new Font("Tahoma", Font.PLAIN, 16));
					lblNewLabel_18.setBounds(395, 42, 166, 32);
					panelTecnico.add(lblNewLabel_18);
					
					cbxTecnico = new JComboBox();
					cbxTecnico.setBackground(Color.WHITE);
					cbxTecnico.setFont(new Font("Tahoma", Font.PLAIN, 14));
					cbxTecnico.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "T\u00E9cnico Superior en Inform\u00E1tica", "T\u00E9cnico Superior en Contabilidad ", "T\u00E9cnico Superior en Enfermer\u00EDa", "T\u00E9cnico Superior en Gastronom\u00EDa ", "T\u00E9cnico Superior en Dise\u00F1o Gr\u00E1fico"}));
					cbxTecnico.setBounds(78, 47, 263, 22);
					panelTecnico.add(cbxTecnico);
					
					spnExpTec = new JSpinner();
					spnExpTec.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
					spnExpTec.setBounds(558, 47, 93, 22);
					panelTecnico.add(spnExpTec);
				}
				
			}
			
			JSeparator separator = new JSeparator();
			separator.setBackground(new Color(0, 102, 153));
			separator.setBounds(194, 557, 722, 2);
			panel.add(separator);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(0, 102, 153));
				panel_1.setBounds(0, 0, 182, 780);
				panel.add(panel_1);
			}

			rdbtnSiVehiculo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnNoVehiculo.setSelected(false);
				}
			});
			rdbtnNoVehiculo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnSiVehiculo.setSelected(false);
				}
			});

	
			SwingUtilities.invokeLater(() -> getContentPane().requestFocusInWindow());

		
			txtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
			    @Override
			    public void focusGained(java.awt.event.FocusEvent e) {
			        if (txtNombre.getText().equals("Ingrese su nombre")) {
			            txtNombre.setText("");
			            txtNombre.setForeground(Color.black);
			        }
			    }
			});

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				if(update != null) {
					btnRegistrar.setText("Modificar");
				}
				
				btnRegistrar.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        if (validarCampos()) {
				            if (update == null) {
				                Usuario usr = null;
				                int edad = new Integer(spnEdad.getValue().toString());
				                int aniosExp = 0;
				                boolean estado = false;
				                if (cbxEstado.getSelectedIndex() == 1) {
				                    estado = true;
				                } else {
				                    estado = false;
				                }

				                if (cbxTipoTrabajador.getSelectedIndex() == 1) {
				                    usr = new Universitario(txtNombre.getText(), txtApellidos.getText(), edad, txtCedula.getText(),
				                            txtCorreo.getText(), cbxSexo.getSelectedItem().toString(), (float) 0,
				                            cbxProvincia.getSelectedItem().toString(), cbxTrabajo.getSelectedItem().toString(), estado,
				                            rdbtnSiMudarse.isSelected(), rdbtnSiLicencia.isSelected(), rdbtnSiVehiculo.isSelected(),
				                            cbxCarrera.getSelectedItem().toString(), cbxArea.getSelectedItem().toString());
				                } else if (cbxTipoTrabajador.getSelectedIndex() == 2) {
				                    aniosExp = 0;
				                    for (int i = 0; i < modelo.getRowCount(); i++) {
				                        Object valor = modelo.getValueAt(i, 1); 
				                        if (valor instanceof Integer) {
				                            aniosExp += (Integer) valor;
				                        } else if (valor instanceof String) {
				                            try {
				                                aniosExp += Integer.parseInt((String) valor);
				                            } catch (NumberFormatException ex) {
				                         
				                            }
				                        }
				                    }
				                    
				                 
				                    misHabilidades = new ArrayList<>();
				                    for (int i = 0; i < modelo.getRowCount(); i++) {
				                        String habilidad = (String) modelo.getValueAt(i, 0); 
				                        misHabilidades.add(habilidad);
				                    }
				                    usr = new Obrero(txtNombre.getText(), txtApellidos.getText(), edad, txtCedula.getText(),
				                            txtCorreo.getText(), cbxSexo.getSelectedItem().toString(), (float) 0,
				                            cbxProvincia.getSelectedItem().toString(), cbxTrabajo.getSelectedItem().toString(), estado,
				                            rdbtnSiMudarse.isSelected(), rdbtnSiLicencia.isSelected(), rdbtnSiVehiculo.isSelected(),
				                            misHabilidades, aniosExp);
				                } else if (cbxTipoTrabajador.getSelectedIndex() == 3) {
				                    aniosExp = new Integer(spnExpTec.getValue().toString());
				                    usr = new TecnicoSuperior(txtNombre.getText(), txtApellidos.getText(), edad, txtCedula.getText(),
				                            txtCorreo.getText(), cbxSexo.getSelectedItem().toString(), (float) 0,
				                            cbxProvincia.getSelectedItem().toString(), cbxTrabajo.getSelectedItem().toString(), estado,
				                            rdbtnSiMudarse.isSelected(), rdbtnSiLicencia.isSelected(), rdbtnSiVehiculo.isSelected(),
				                            cbxTecnico.getSelectedItem().toString(), aniosExp);
				                }

				                if (rutaImagenSeleccionada != null && !rutaImagenSeleccionada.trim().isEmpty()) {
				                    try {
				                        File carpetaDestino = new File("imagenesUsuarios");
				                        if (!carpetaDestino.exists()) {
				                            carpetaDestino.mkdir();
				                        }

				                        String nombreArchivo = new File(rutaImagenSeleccionada).getName();
				                        String rutaDestino = "imagenesUsuarios" + File.separator + nombreArchivo;

				                        Files.copy(Paths.get(rutaImagenSeleccionada), Paths.get(rutaDestino),
				                                StandardCopyOption.REPLACE_EXISTING);

				                        usr.setRutaImagen(rutaDestino);
				                    } catch (IOException e1) {
				                        e1.printStackTrace();
				                        JOptionPane.showMessageDialog(null, "Error al guardar la imagen.", "Error",
				                                JOptionPane.ERROR_MESSAGE);
				                        usr.setRutaImagen(IMAGEN_DEFAULT);
				                    }
				                } else {
				                    usr.setRutaImagen(IMAGEN_DEFAULT);
				                }

				                boolean realizado = Bolsa.getInstance().registrarEmpleado(usr);
				                if (realizado) {
				                    try {
				                        Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
				                    } catch (IOException e1) {
				                        e1.printStackTrace();
				                    }
				                    JOptionPane.showMessageDialog(null, "Registro satisfactorio", "Información",
				                            JOptionPane.INFORMATION_MESSAGE);
				                    clean();
				                } else {
				                    JOptionPane.showMessageDialog(null, "Este usuario ya existe.", "Error",
				                            JOptionPane.ERROR_MESSAGE);
				                }
				            } else {

				                update.setNombre(txtNombre.getText());
				                update.setApellido(txtApellidos.getText());
				                update.setContacto(txtCorreo.getText());
				                update.setSexo(cbxSexo.getSelectedItem().toString());
				                update.setEdad(new Integer(spnEdad.getValue().toString()));
				                update.setProvincia(cbxProvincia.getSelectedItem().toString());
				                update.setTipoTrabajo(cbxTrabajo.getSelectedItem().toString());
				                boolean estado = false;
				                if (cbxEstado.getSelectedIndex() == 1) {
				                    estado = true;
				                }
				                update.setEstado(estado);
				                update.setLicencia(rdbtnSiLicencia.isSelected());
				                update.setTieneVeh(rdbtnSiVehiculo.isSelected());
				                update.setdispuestoMud(rdbtnSiMudarse.isSelected());
				                update.setRutaImagen(rutaImagenSeleccionada != null ? rutaImagenSeleccionada : IMAGEN_DEFAULT);

				                if (update instanceof Universitario) {
				                    Universitario uni = (Universitario) update;
				                    uni.setCarrera(cbxCarrera.getSelectedItem().toString());
				                } else if (update instanceof TecnicoSuperior) {
				                    TecnicoSuperior tc = (TecnicoSuperior) update;
				                    tc.setTecnico(cbxTecnico.getSelectedItem().toString());
				                    tc.setAniosExperiencia(new Integer(spnExpTec.getValue().toString()));
				                } else if (update instanceof Obrero) {
				                    Obrero ob = (Obrero) update;
				                    int aniosExp = 0;
				                    for (int i = 0; i < modelo.getRowCount(); i++) {
				                        Object valor = modelo.getValueAt(i, 1);
				                        if (valor instanceof Integer) {
				                            aniosExp += (Integer) valor;
				                        } else if (valor instanceof String) {
				                            try {
				                                aniosExp += Integer.parseInt((String) valor);
				                            } catch (NumberFormatException ex) {
				                                
				                            }
				                        }
				                    }
				                    ob.setAniosExperiencia(aniosExp);
				                }
				                if (rutaImagenSeleccionada != null && !rutaImagenSeleccionada.trim().isEmpty()) {
				                    try {
				                        File carpetaDestino = new File("imagenesUsuarios");
				                        if (!carpetaDestino.exists()) {
				                            carpetaDestino.mkdir();
				                        }

				                        String nombreArchivo = new File(rutaImagenSeleccionada).getName();
				                        String rutaDestino = "imagenesUsuarios" + File.separator + nombreArchivo;

				                        Files.copy(Paths.get(rutaImagenSeleccionada), Paths.get(rutaDestino),
				                                StandardCopyOption.REPLACE_EXISTING);

				                        update.setRutaImagen(rutaDestino);
				                    } catch (IOException e1) {
				                        e1.printStackTrace();
				                        JOptionPane.showMessageDialog(null, "Error al guardar la nueva imagen.", "Error",
				                                JOptionPane.ERROR_MESSAGE);
				                        update.setRutaImagen(IMAGEN_DEFAULT);
				                    }
				                } else {
				                    update.setRutaImagen(IMAGEN_DEFAULT);
				                }

				                Bolsa.getInstance().modificarUsuario(update);

				                try {
				                    Bolsa.getInstance().guardarDatosEnArchivo("respaldo.dat");
				                } catch (IOException e1) {
				                    e1.printStackTrace();
				                }
				                JOptionPane.showMessageDialog(null, "Modificación exitosa", "Información",
				                        JOptionPane.INFORMATION_MESSAGE);
				                ListadoUsuario.loadUsuario(0);
				                dispose();

				            }

				        }

				    }
				});

				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
			loadUsers();

		}
		
		}
	}
	
	private boolean validarCampos() {
		boolean aux = true;
		if (txtCedula.getText().isEmpty() || txtNombre.getText().isEmpty() ||txtApellidos.getText().isEmpty() || txtCorreo.getText().isEmpty() || cbxSexo.getSelectedIndex() == 0 || cbxProvincia.getSelectedIndex() == 0 || cbxEstado.getSelectedIndex() == 0 || cbxTipoTrabajador.getSelectedIndex() == 0 || cbxTrabajo.getSelectedIndex() == 0 || (rdbtnSiLicencia.isSelected() == false && rdbtnNoLicencia.isSelected() == false) || (rdbtnSiMudarse.isSelected() == false && rdbtnNoMudarse.isSelected() == false) || (rdbtnSiVehiculo.isSelected() == false && rdbtnNoVehiculo.isSelected() == false)) {
			JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos antes de continuar.", "Error",
					JOptionPane.ERROR_MESSAGE);
			aux = false;
		}
		else if ((panelEstudiante.isVisible() && cbxCarrera.getSelectedIndex() == 0) || (panelTecnico.isVisible() && cbxTecnico.getSelectedIndex() == 0)) {
			JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos antes de continuar.", "Error",
					JOptionPane.ERROR_MESSAGE);
			aux = false;
		}
		else if (ValidarPalabra(txtNombre.getText())|| ValidarPalabra(txtApellidos.getText())) {
			JOptionPane.showMessageDialog(null, "Nombre y apellidos no deben de contener números.", "Error",
					JOptionPane.ERROR_MESSAGE);
			aux = false;
		}
		else if(ValidarCedula(txtCedula.getText())== false ) {
			JOptionPane.showMessageDialog(null, "Cédula debe de cumplir con el formato ###-#######-#.", "Error de formato",
					JOptionPane.ERROR_MESSAGE);
			aux = false;
		}
		return aux;
	}
	
	
	private void loadUsers() {
		if(update != null) {
			txtCedula.setForeground(Color.black);
			txtNombre.setForeground(Color.black);
			txtApellidos.setForeground(Color.black);
			txtCorreo.setForeground(Color.black);
			txtNombre.setText(update.getNombre());
			txtApellidos.setText(update.getApellido());
			txtCedula.setText(update.getCedula());
			txtCorreo.setText(update.getContacto());
			cbxSexo.setSelectedItem(update.getSexo());
			spnEdad.setValue(update.getEdad());
			cbxProvincia.setSelectedItem(update.getProvincia());
			cbxTrabajo.setSelectedItem(update.getTipoTrabajo());

			if(update.isEstado()) {
				cbxEstado.setSelectedIndex(1);
			}else {
				cbxEstado.setSelectedIndex(2);
			}
			
			if(update instanceof Universitario) {
				cbxTipoTrabajador.setSelectedIndex(1);
				cbxCarrera.setSelectedItem(((Universitario) update).getCarrera());
			}
			else if(update instanceof TecnicoSuperior) {
				cbxTipoTrabajador.setSelectedIndex(3);
				cbxTecnico.setSelectedItem(((TecnicoSuperior) update).getTecnico());
				spnExpTec.setValue(((TecnicoSuperior) update).getAniosExperiencia());
			}
			else if(update instanceof Obrero) {
			    cbxTipoTrabajador.setSelectedIndex(2);
			    Obrero ob = (Obrero) update; 
			    misHabilidades = ob.getMisHabilidades(); 
			    spnExpObre.setValue(ob.getAniosExperiencia());
			    modelo.setRowCount(0);
			    if (misHabilidades != null) {
			        for (String h : misHabilidades) {
			            modelo.addRow(new Object[] { h, 0 });
			        }
			    }
			}

			if (update.getRutaImagen() != null) {
			    lblFotoPreview.setIcon(escalarImagen(update.getRutaImagen()));
			    rutaImagenSeleccionada = update.getRutaImagen();
			}

			
			if(update.isLicencia())
				rdbtnSiLicencia.setSelected(true);
			else
				rdbtnNoLicencia.setSelected(true);

			if(update.isDispuestoMud())
				rdbtnSiMudarse.setSelected(true);
			else
				rdbtnNoMudarse.setSelected(true);
			
			if(update.isTieneVeh())
				rdbtnSiVehiculo.setSelected(true);
			else
				rdbtnNoVehiculo.setSelected(true);

			
		}
	}
	
	private boolean ValidarPalabra(String text) {
		boolean aux= false;
		int i = 0;
		while(!aux && i < text.length()) {
			char letra = text.charAt(i);
			if(letra >= '0' && letra <= '9') {
				aux=true;
			}
			i++;
		}
		return aux;
	}
	
	private boolean ValidarCedula(String text) {
		boolean aux = false;
		aux = text.matches("\\d{3}-\\d{7}-\\d{1}");
		return aux;
	}
	
	private boolean ValidarDuplicado(String text) {
		boolean aux = false;
		return aux;
	}
	
	private boolean ValidarCorreo(String text) {
		boolean aux = false;
		
		return aux;
	}
	
	private void clean() {
		txtNombre.setText("Ingrese su nombre");
		txtApellidos.setText("Ingrese sus apellidos");
		txtCedula.setText("###-#######-#");
		txtCorreo.setText("Ingrese su correo");
		cbxCarrera.setSelectedIndex(0);
		cbxEstado.setSelectedIndex(0);
		cbxProvincia.setSelectedIndex(0);
		cbxSexo.setSelectedIndex(0);
		cbxTipoTrabajador.setSelectedIndex(0);
		cbxTrabajo.setSelectedIndex(0);
		cbxTecnico.setSelectedIndex(0);
		spnEdad.setValue(16);
		spnExpObre.setValue(0);
		spnExpTec.setValue(0);
		rdbtnNoLicencia.setSelected(false);
		rdbtnSiLicencia.setSelected(false);
		rdbtnNoMudarse.setSelected(false);
		rdbtnSiMudarse.setSelected(false);
		rdbtnNoVehiculo.setSelected(false);
		rdbtnSiVehiculo.setSelected(false);
		panelBlanco.setVisible(true);
		panelEstudiante.setVisible(false);
		panelObrero.setVisible(false);
		panelTecnico.setVisible(false);	
		
		lblFotoPreview.setIcon(escalarImagen(IMAGEN_DEFAULT));
		rutaImagenSeleccionada = null;

		
		}
	
	
	public void filtroPorArea(int num) {


		switch (num) {
		case 1:
			String[] carerrasAgro= {"<Seleccione>", "Ingenier\u00EDa Agroindustrial", "Ingenier\u00EDa Ambiental",  "Veterinaria "};
			cbxCarrera.setModel(new DefaultComboBoxModel<>(carerrasAgro));
			cbxCarrera.setSelectedIndex(0);
			cbxCarrera.setEnabled(true);

			break;
		
		case 2:
			String[] carerrasFinanzas= {"<Seleccione>",  "Administraci\u00F3n de Empresas", "Contabilidad",  "Mercadeo", "Relaciones Internacionales"};
			cbxCarrera.setModel(new DefaultComboBoxModel<>(carerrasFinanzas));
			cbxCarrera.setSelectedIndex(0);
			cbxCarrera.setEnabled(true);

			break;
			
		case 3:
			String[] carerrasSalud= {"<Seleccione>", "Enfermeria",  "Medicina", "Odontolog\u00EDa", "Psicolog\u00EDa Cl\u00EDnica "};
			cbxCarrera.setModel(new DefaultComboBoxModel<>(carerrasSalud));
			cbxCarrera.setSelectedIndex(0);
			cbxCarrera.setEnabled(true);

			break;
			
		case 4:
			String[] carerrasTecnologia= {"<Seleccione>", "Ingenier\u00EDa Electr\u00F3nica", "Ingenier\u00EDa en Computaci\u00F3n", "Ingenier\u00EDa en Telecomunicaciones",  "Ingenier\u00EDa Mec\u00E1nica"};
			cbxCarrera.setModel(new DefaultComboBoxModel<>(carerrasTecnologia));
			cbxCarrera.setSelectedIndex(0);
			cbxCarrera.setEnabled(true);

			
			break;
			
		case 5:
			String[] carerrasOtras= {"<Seleccione>", "Arquitectura", "Comunicaci\u00F3n Social", "Ingenier\u00EDa Industrial", "Derecho",  "Ingenier\u00EDa Civil"};
			cbxCarrera.setModel(new DefaultComboBoxModel<>(carerrasOtras));
			cbxCarrera.setSelectedIndex(0);
			cbxCarrera.setEnabled(true);

			
			break;

		default:
			
			cbxCarrera.setEnabled(false);
			cbxCarrera.setSelectedIndex(0);

			break;
		}
		
		
		
	}
	
	private ImageIcon escalarImagen(String ruta) {
	    ImageIcon original = new ImageIcon(ruta);
	    Image escalada = original.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
	    return new ImageIcon(escalada);
	}
}
