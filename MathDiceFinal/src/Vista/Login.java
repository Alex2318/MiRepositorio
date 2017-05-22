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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	
	

	private JPanel contentPane;

	private JLabel lblBienvenida;
	private JLabel lblBienvenida2;
	private JTextField JTextUser;
	private JPasswordField JPassword;
	private JButton Boton;
	private Component verticalStrut, verticalStrut_1, verticalStrut_2, verticalStrut_3, verticalStrut_4,
	verticalStrut_5, verticalStrut_6;
	private JTextArea textArea;

	//Manejadores de la base de datos
	private Conexion db;
	private JugadorDB jdb;
	private Connection conexion; //Conexión
	private boolean connected =false; //Conexión con éxito
	private boolean existe=false;

	private Jugador player1=new Jugador();
	
	private Login referencia;//Creamos referencia sobre Login.
	private VentanaPrincipal vPrincipal;
	private SignUp login;
	private JButton botonIngreso;
	
	private Component verticalStrut_7;//ESTE COMPONENTE ES EL QUE EJERCE DE SEPARADOR EN EL BOXLAYOUT
	
	private String encriptado;//ES EL PASSWORD YA ENCRIPTADO
	
	
	public Login(VentanaPrincipal vPrinc, SignUp log) {
		
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
		
		lblBienvenida2 = new JLabel("Introduzca su User y su Password");
		lblBienvenida2.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		lblBienvenida2.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(lblBienvenida2);
		
		verticalStrut = Box.createVerticalStrut(30);
		contentPane.add(verticalStrut);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		lblUser.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(lblUser);
		
		verticalStrut_1 = Box.createVerticalStrut(10);
		contentPane.add(verticalStrut_1);
		
		JTextUser = new JTextField();
		contentPane.add(JTextUser);
		JTextUser.setColumns(10);
		JTextUser.setMinimumSize(new Dimension (400, 25));/*MÉTODOS PARA ESTABLECER EL TAMAÑO MÍNIMO...*****/
		JTextUser.setPreferredSize(new Dimension(400, 25));/*...PREFERIDO...********************************/
		JTextUser.setMaximumSize(new Dimension(600, 50));/*Y MÁXIMO DE UN ELEMENTO EN UN BOX LAYOUT*********/
		
		verticalStrut_2 = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut_2);
		
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
		Boton.addMouseListener(new botonJugar());//HACEMOS LA LLAMADA A LA INNER CLASS
		Boton.setAlignmentX(0.5f);
		Boton.setBounds(180, 20, 0, 0);
		contentPane.add(Boton);
		Boton.setMinimumSize(new Dimension (300, 25));
		Boton.setPreferredSize(new Dimension(300, 25));
		Boton.setMaximumSize(new Dimension(300, 50));
		
		verticalStrut_6 = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut_6);
		
		botonIngreso = new JButton("CREAR NUEVO JUGADOR");
		botonIngreso.addMouseListener(new botonIngresar());//HACEMOS LA LLAMADA A LA INNER CLASS
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
	
	private class botonIngresar implements MouseListener {

		//Formato que tiene que aparecer en una inner class de un mouseListener
		@Override
		public void mouseClicked(MouseEvent arg0) {}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
		//MousseListener que vamos a utilizar
		@Override
		public void mousePressed(MouseEvent arg0) {
			login.setVisible(true);
			referencia.dispose();
			}
				
		}
	
	private class botonJugar implements MouseListener {

		//Formato que tiene que aparecer en una inner class de un mouseListener
		@Override
		public void mouseClicked(MouseEvent arg0) {}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
		//MousseListener que vamos a utilizar
		@Override
		public void mousePressed(MouseEvent arg0) {
			Conectar();
			encriptado = Encriptar.EncriptarPassword(JPassword.getText());
			Jugador j=jdb.buscarJugador(JTextUser.getText(), encriptado); //Buscamos usuario y cerramos conexion
		
			//SI EL USUARIO Y EL PASSWORD NO COINCIDE CON NINGUNO DE LA BBDD...
				if (j.getNombre()==null){
					textArea.setText("User o password incorrectos. \nIntrodúzcalos de nuevo o cree un nuevo jugador.");
				}else{	
					player1.setNombre(j.getNombre());
					player1.setApellidos(j.getApellidos());
					player1.setEdad(j.getEdad());
					player1.setUser(j.getUser());
					player1.setId(j.getId());
					player1.setPuntos(j.getPuntos());
					player1.setRecordAciertos(j.getRecordAciertos());
					player1.setPassword(encriptado);
					
					vPrincipal.setVisible(true);
					referencia.dispose();
					vPrincipal.setJugador(player1);
					}
			
			}
				
		}

}
