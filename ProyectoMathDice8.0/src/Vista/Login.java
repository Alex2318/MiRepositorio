package Vista;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Conexion;

import Modelo.Jugador;
import Modelo.JugadorDB;


import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JTextArea;

/*
 * *Ventana donde a trav�s de la clase Login de tipo JFrame presentamos la ventana que aparecer� cuando ejecutemos
 *  la aplicaci�n. 
 * *En ella se presentan distintas etiquetas, cajas de texto para que el usuario introduzca sus datos y de esta forma
 *  crear un nuevo jugador. Este jugador tendr� las caracter�sticas definidas en la clase Jugador, en el paquete Juego.
 * *A la hora de introducir los datos y pasar a la siguente ventan hemos introducido dos metodolog�as diferentes.
 * *La primera es la de hacerlo a trav�s del bot�n, al darle se ejecutar�n las acciones para determinar si los datos
 *  est�n bien introducidos y de ser as� se crear� el jugador y se dar� paso a la siguiente ventana Juego, cerrando la
 *  presente.
 * *La otra opci�n que tambi�n hemos elegido para hacer m�s r�pido el login es la de implementar todas las acciones
 *  anteriores en un listener al apretar intro en la caja donde se introduce la edad (la �ltima de arriba abajo).
 */
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField JTextEdad;
	private JTextField JTextApellidos;
	private JTextField JTextNombre;
	private JTextArea JTextCampo;
	private Login referencia;//Creamos referencia sobre Login.
	private VentanaPrincipal vPrincipal;
	private Jugador player1=new Jugador();
	private JLabel lblUser;
	private JTextField JTextUser;
	
    //Manejadores de la base de datos
	private Conexion db;
	private JugadorDB jdb;
	private Connection conexion; //Conexi�n
	private boolean connected =false; //Conexi�n con �xito
	
	
	//Constructor de la ventana Login
	public Login(VentanaPrincipal vP) {
		
		//Metemos aqu� la referencia para que lo que siga lo haga sobre Login.
		referencia=this;
		vPrincipal=vP;
		
		//Inicalizamos player1.
		player1.setNombre("");
		player1.setApellidos("");
		player1.setEdad(0);
		player1.setUser("");
		
		
		//Propiedades de la ventana y del contentPane	
		setForeground(SystemColor.activeCaption);
		setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		setIconImage(Toolkit.getDefaultToolkit().getImage(JuegoPrincipiante.class.getResource("/Imagenes/dado.png")));
		setTitle("Math Dice");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Etiqueta t�tulo.
		JLabel lblIntroduzcaLosDatos = new JLabel("Introduzca los datos del jugador");
		lblIntroduzcaLosDatos.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		lblIntroduzcaLosDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduzcaLosDatos.setBounds(90, 10, 237, 45);
		contentPane.add(lblIntroduzcaLosDatos);
		
		//Etiqueta nombre.
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		lblNombre.setBounds(10, 65, 86, 20);
		contentPane.add(lblNombre);
		
		//Etiqueta apellidos.
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		lblApellidos.setBounds(10, 120, 86, 20);
		contentPane.add(lblApellidos);
		
		//Etiqueta edad.
		JLabel lblEdad = new JLabel("Edad ");
		lblEdad.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		lblEdad.setBounds(10, 175, 86, 20);
		contentPane.add(lblEdad);
		
		//Etiqueta user
		lblUser = new JLabel("User");
		lblUser.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		lblUser.setBounds(10, 223, 86, 20);
		contentPane.add(lblUser);
		
		//Campo de texto nombre.
		JTextNombre = new JTextField();
		JTextNombre.setBounds(90, 66, 334, 20);
		contentPane.add(JTextNombre);
		JTextNombre.setColumns(10);
	
		//Campo de texto apellidos.
		JTextApellidos = new JTextField();
		JTextApellidos.setBounds(90, 121, 334, 20);
		contentPane.add(JTextApellidos);
		JTextApellidos.setColumns(10);
		
		//Campo de texto edad.
		JTextEdad = new JTextField();
		JTextEdad.setBounds(90, 176, 86, 20);
		contentPane.add(JTextEdad);
		JTextEdad.setColumns(10);
		
		//CAMPO DE TEXTO USER
		JTextUser = new JTextField();
		JTextUser.setColumns(10);
		JTextUser.setBounds(90, 224, 334, 20);
		contentPane.add(JTextUser);

		//Campo de texto confirmaci�n.
		JTextCampo = new JTextArea();
		JTextCampo.setText("Introduce los datos y elige nivel");
		JTextCampo.setBounds(10, 303, 414, 97);
		contentPane.add(JTextCampo);
		JTextCampo.setColumns(10);
		

		//Bot�n confirmaci�n.
		JButton BotonPrincipiante = new JButton("A JUGAAAAAAAAAR!!!");
		//En un Listener metemos todas las acciones
		BotonPrincipiante.addMouseListener(new botonJugar());
		BotonPrincipiante.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		BotonPrincipiante.setBounds(10, 254, 417, 38);
		contentPane.add(BotonPrincipiante);	
		
		
				
		
		//Creamos nuestro objeto para el manejo de la base de datos
		db=new Conexion("localhost","jugadores","root","");
		//Establecemos la conexion
		connected=db.connectDB();
		//Asignamos con el getter la conexion establecida
		conexion=db.getConexion();
		//Pasamos la conexi�n a un nuevo objeto UsuariosDB para insertar datos.
		jdb=new JugadorDB(conexion); 
	}
	
	//IMPLEMENTACI�N DE INNER CLASS PARA OPTIMIZAR C�DIGO
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

				//Guardar nombre.
				player1.setNombre(JTextNombre.getText());
				
				//Guardar apellidos.
				player1.setApellidos(JTextApellidos.getText());
				
				player1.setUser(JTextUser.getText());
				//Guardar edad.
				if (player1.isNumeric(JTextEdad.getText())==true)//Si el m�todo creado en la clase jugador nos devuelve un true...
					player1.setEdad(Integer.parseInt(JTextEdad.getText()));//...introducimos la edad como int al player1 
					else//De la otra forma...
						player1.setEdad(999);//...la edad se rellena como 999 y eso nos valdr� para aclarar futuras comprobaciones.
				

				//Dependiendo de los campos de texto vamos a mostrar un mensaje de confirmaci�n o error por el JTextCampo.
				if (player1.getEdad()==999){
					JTextCampo.setText("Edad erronea. Vuelva a rellenarla y pulse A JUGAAAAAAAAAR!!!");
				}else if (player1.enBlanco(player1.getNombre())){
					JTextCampo.setText("Falta rellenar el nombre. Rellenelo y pulse A JUGAAAAAAAAAR!!!");
				}else if (player1.enBlanco(player1.getApellidos())){
					JTextCampo.setText("Falta rellenar los apellidos. Rellenelos y pulse A JUGAAAAAAAAAR!!!");
				}else if (player1.enBlanco(player1.getUser())){
					JTextCampo.setText("Falta rellenar el user. Rellenelo y pulse A JUGAAAAAAAAAR!!!");
				}else{	
					//REGISTRO DE USUARIO EN BBDD
					jdb.insertarUsuario(JTextNombre.getText(), JTextApellidos.getText(), JTextUser.getText(), Integer.valueOf(JTextEdad.getText()));
					player1.setId(jdb.devolverID());
				
					if (db.connectDB()){//M�TODO BOLEANO QUE DEVUELVE TRUE SI SE HA CONECTADO BIEN A LA BBDD
						JTextCampo.setText("Creado nuevo jugador: "+player1.getNombre()+" "+player1.getApellidos()+" de "+player1.getEdad()+" a�os.\nConectado con �xito a la BBDD. Registrado con �xito con el n�mero "+player1.getId()+".");	
					}else{
						JTextCampo.setText("Creado nuevo jugador: "+player1.getNombre()+" "+player1.getApellidos()+" de "+player1.getEdad()+" a�os.\nError al conectar con la BBDD");
					}
					
					vPrincipal.setVisible(true);
					//referencia.dispose();
					vPrincipal.setJugador(player1);
					System.out.println(player1.getUser());
					
				}
					
			}	
		}
}
