package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import org.apache.commons.codec.digest.DigestUtils;

import Controlador.Encriptar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Modelo.Conexion;
import Modelo.Jugador;
import Modelo.JugadorDB;


import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;

import javax.swing.Box;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Login1 extends JFrame {
	
	

	private JPanel contentPane;

	//OBJETOS DEL CONTENTPANE
	private JLabel lblBienvenida;
	private JLabel lblBienvenida2;
	private JTextField JTextNombre;
	private JTextField JTexUser;
	private JPasswordField JPassword;
	private JButton Boton;
	private Component verticalStrut_3, verticalStrut_4,
	verticalStrut_5, verticalStrut_6;
	private JTextArea textArea;

	//Manejadores de la base de datos
	private Conexion db;
	private JugadorDB jdb;
	private Connection conexion; //Conexión
	private boolean connected =false; //Conexión con éxito
	private boolean existe=false;

	private Jugador player1=new Jugador();
	
	private Login1 referencia;//Creamos referencia sobre Login.
	private VentanaPrincipal vPrincipal;
	private Login login;
	private JButton botonIngreso;
	private Component verticalStrut_7;
	
	//ATRIBUTO QUE SERVIRÁ PARA ENCRIPTAR EL PASSWORD
	private String encriptado;
	
	//JCOmbobox de usuarios
	private JComboBox comboBox;
	private int numero_de_items; //numero de elementos en el ComboBox
	
	private Component verticalStrut;

	public Login1(VentanaPrincipal vPrinc, Login log) {
		
		//Metemos aquí la referencia para que lo que siga lo haga sobre Login.
		referencia=this;
		vPrincipal=vPrinc;
		login=log;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		lblBienvenida = new JLabel("Bienvenido a MathDice");
		lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblBienvenida.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		contentPane.add(lblBienvenida);
		
		verticalStrut_5 = Box.createVerticalStrut(10);
		contentPane.add(verticalStrut_5);
		
		lblBienvenida2 = new JLabel("Introduzca su Nombre, pulse intro, b\u00FAsquesa e introduzca su Password");
		lblBienvenida2.setFont(new Font("Modern No. 20", Font.PLAIN, 14));
		lblBienvenida2.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(lblBienvenida2);
		
		verticalStrut = Box.createVerticalStrut(10);
		contentPane.add(verticalStrut);
		
		JTextNombre = new JTextField();
		JTextNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.removeAllItems();
				Conectar();
				jdb.buscarJugador(JTextNombre.getText(),comboBox); //Buscamos usuario y cerramos conexion
				numero_de_items=comboBox.getItemCount(); //Número de ítems en el ComboBox
				if (numero_de_items==0){
					textArea.setText("No encontrado ningún usuario con ese nombre");
				}
			}
		});
		contentPane.add(JTextNombre);
		JTextNombre.setColumns(10);
		JTextNombre.setMinimumSize(new Dimension (400, 25));
		JTextNombre.setPreferredSize(new Dimension(400, 25));
		JTextNombre.setMaximumSize(new Dimension(600, 50));
		
		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblUser.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(lblUser);
		
		comboBox = new JComboBox();
		contentPane.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//Por defecto mostrará en pantalla la primera coincidencia con el
			//nombre encontrado en la BBDD
			//Seleccionando sobre el desplegable del JComboBox se podrá cambiar si hay varias coincidencias
			
			Jugador j=(Jugador)comboBox.getSelectedItem();
			
			
		}
	}
	);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(lblPassword);
		
		verticalStrut_3 = Box.createVerticalStrut(10);
		contentPane.add(verticalStrut_3);
		
		JPassword = new JPasswordField();
		contentPane.add(JPassword);
		JPassword.setColumns(10);
		JPassword.setMinimumSize(new Dimension (400, 25));
		JPassword.setPreferredSize(new Dimension(400, 25));
		JPassword.setMaximumSize(new Dimension(600, 50));
		
		verticalStrut_4 = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut_4);
		
		Boton = new JButton("A JUGAR");
		Boton.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		Boton.addActionListener(new ActionListener() {
			private String encriptado;

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				Conectar();
				
				encriptado = Encriptar.EncriptarPassword(JPassword.getText());
				Jugador j=(Jugador)comboBox.getSelectedItem();
				jdb.buscarJugador(j.getUser(), encriptado); //Buscamos usuario y cerramos conexion
				

					
					
					if (j.getUser()==null){
						login.setVisible(true);
						referencia.dispose();
						
					}else{
						
						
						
						player1.setNombre(j.getNombre());
						player1.setApellidos(j.getApellidos());
						player1.setEdad(j.getEdad());
						player1.setUser(j.getUser());
						player1.setId(j.getId());
						player1.setPuntos(j.getPuntos());
						player1.setPassword(encriptado);
						
						vPrincipal.setVisible(true);
						referencia.dispose();
						vPrincipal.setJugador(player1);
						}
					}
			}
		);
		Boton.setAlignmentX(0.5f);
		Boton.setBounds(180, 20, 0, 0);
		contentPane.add(Boton);
		Boton.setMinimumSize(new Dimension (300, 25));
		Boton.setPreferredSize(new Dimension(300, 25));
		Boton.setMaximumSize(new Dimension(300, 50));
		
		verticalStrut_6 = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut_6);
		
		botonIngreso = new JButton("CREAR NUEVO JUGADOR");
		botonIngreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login.setVisible(true);
				referencia.dispose();
			}
		});
		botonIngreso.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		botonIngreso.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonIngreso.setBounds(180, 20, 0, 0);
		contentPane.add(botonIngreso);
		botonIngreso.setMinimumSize(new Dimension (300, 25));
		botonIngreso.setPreferredSize(new Dimension(300, 25));
		botonIngreso.setMaximumSize(new Dimension(300, 50));
		
		verticalStrut_7 = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut_7);
		
		textArea = new JTextArea();
		contentPane.add(textArea);	
		
	}
	
	//Conectar con la base de datos
	private void Conectar(){
		//Conexión con la BBDD
		//Creamos nuestro objeto para el manejo de la base de datos
		try{
			db=Conexion.getInstance("localhost","jugadores","root","");
			//Establecemos la conexion
			connected=db.connectDB();
			//Asignamos con el getter la conexion establecida
			conexion=db.getConexion();
			//Pasamos la conexión a un nuevo objeto JugadorDB para insertar datos.
			jdb=new JugadorDB(conexion);
		}catch(Exception e){
			textArea.setText( " Debe haber algún problema con la BBDD o con la conexión.");	
		}
	}

}
