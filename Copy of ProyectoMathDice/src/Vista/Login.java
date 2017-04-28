package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Conexion;

import Modelo.Jugador;
import Modelo.JugadorDB;


import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import javax.swing.JInternalFrame;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField jtxtIntroduzcaSuUser;
	private JTextField JTextNombre;
	private JTextField JTextApellidos;
	private JTextField JTextEdad;
	private JTextField JTextUser;
	private JButton botonBuscar;	
	private JButton botonAJugar;
	private JTextArea JTextComentarios;
	
	private Login referencia;//Creamos referencia sobre Login.
	private VentanaPrincipal vPrincipal;
	private Jugador player1=new Jugador();
	
	//Manejadores de la base de datos
		private Conexion db;
		private JugadorDB jdb;
		private Connection conexion; //Conexión
		private boolean connected =false; //Conexión con éxito
		private boolean existe=false;
		private JButton botonIngresarJugador;
		private JLabel lblId;
		private JTextField JTextId;
	
	public Login(VentanaPrincipal vPrinc) {
		
		//Metemos aquí la referencia para que lo que siga lo haga sobre Login.
		referencia=this;
		vPrincipal=vPrinc;
		
		//Inicalizamos player1.
		player1.setNombre("");
		player1.setApellidos("");
		player1.setEdad(0);
		player1.setUser("");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{178, 247};
		gbl_contentPane.rowHeights = new int[]{33, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 62, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblLogIn = new JLabel("LOG IN");
		lblLogIn.setFont(new Font("Modern No. 20", Font.PLAIN, 25));
		GridBagConstraints gbc_lblLogIn = new GridBagConstraints();
		gbc_lblLogIn.gridwidth = 2;
		gbc_lblLogIn.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogIn.fill = GridBagConstraints.VERTICAL;
		gbc_lblLogIn.gridx = 0;
		gbc_lblLogIn.gridy = 0;
		contentPane.add(lblLogIn, gbc_lblLogIn);
		
		
		jtxtIntroduzcaSuUser = new JTextField();
		
		jtxtIntroduzcaSuUser.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		jtxtIntroduzcaSuUser.setText("Introduzca su USER...");
		GridBagConstraints gbc_txtIntroduzcaSuUser = new GridBagConstraints();
		gbc_txtIntroduzcaSuUser.insets = new Insets(0, 0, 5, 0);
		gbc_txtIntroduzcaSuUser.gridwidth = 2;
		gbc_txtIntroduzcaSuUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIntroduzcaSuUser.gridx = 0;
		gbc_txtIntroduzcaSuUser.gridy = 1;
		contentPane.add(jtxtIntroduzcaSuUser, gbc_txtIntroduzcaSuUser);
		jtxtIntroduzcaSuUser.setColumns(10);
		jtxtIntroduzcaSuUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jtxtIntroduzcaSuUser.setText("");
			}
		});
	


		
		
		botonBuscar = new JButton("BUSCAR");
		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conectar();
				Jugador j=jdb.buscarJugador(jtxtIntroduzcaSuUser.getText()); //Buscamos usuario y cerramos conexion
				
				JTextComentarios.setText("");	
					JTextNombre.setText(j.getNombre());           //Sacamos datos por ventana
					JTextApellidos.setText(j.getApellidos());
					JTextEdad.setText(String.valueOf(j.getEdad()));
					JTextUser.setText(j.getUser());
					JTextId.setText(String.valueOf(j.getId()));
					player1.setPuntos(j.getPuntos());
					
					botonAJugar.setEnabled(true);
					if (j.getNombre()==null){
						JTextComentarios.setText("No existe");			
					}else{
						
					}
				
			}
		});
		botonBuscar.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		GridBagConstraints gbc_botonIngresarJugador = new GridBagConstraints();
		gbc_botonIngresarJugador.insets = new Insets(0, 0, 5, 0);
		gbc_botonIngresarJugador.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonIngresarJugador.gridwidth = 2;
		gbc_botonIngresarJugador.gridx = 0;
		gbc_botonIngresarJugador.gridy = 2;
		contentPane.add(botonBuscar, gbc_botonIngresarJugador);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 4;
		contentPane.add(lblNombre, gbc_lblNombre);
		
		JTextNombre = new JTextField();
		GridBagConstraints gbc_JTextNombre = new GridBagConstraints();
		gbc_JTextNombre.insets = new Insets(0, 0, 5, 0);
		gbc_JTextNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_JTextNombre.gridx = 1;
		gbc_JTextNombre.gridy = 4;
		contentPane.add(JTextNombre, gbc_JTextNombre);
		JTextNombre.setColumns(10);
		
		
		
		JLabel lblNewLabel = new JLabel("APELLIDOS");
		lblNewLabel.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 6;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JTextApellidos = new JTextField();
		GridBagConstraints gbc_JTextApellidos = new GridBagConstraints();
		gbc_JTextApellidos.insets = new Insets(0, 0, 5, 0);
		gbc_JTextApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_JTextApellidos.gridx = 1;
		gbc_JTextApellidos.gridy = 6;
		contentPane.add(JTextApellidos, gbc_JTextApellidos);
		JTextApellidos.setColumns(10);
		
		JLabel lblEdad = new JLabel("EDAD");
		lblEdad.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		GridBagConstraints gbc_lblEdad = new GridBagConstraints();
		gbc_lblEdad.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdad.gridx = 0;
		gbc_lblEdad.gridy = 8;
		contentPane.add(lblEdad, gbc_lblEdad);
		
		JTextEdad = new JTextField();
		GridBagConstraints gbc_JTextEdad = new GridBagConstraints();
		gbc_JTextEdad.insets = new Insets(0, 0, 5, 0);
		gbc_JTextEdad.fill = GridBagConstraints.HORIZONTAL;
		gbc_JTextEdad.gridx = 1;
		gbc_JTextEdad.gridy = 8;
		contentPane.add(JTextEdad, gbc_JTextEdad);
		JTextEdad.setColumns(10);
		
		JLabel lblUser = new JLabel("USER");
		lblUser.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblUser.gridx = 0;
		gbc_lblUser.gridy = 10;
		contentPane.add(lblUser, gbc_lblUser);
		
		JTextUser = new JTextField();
		GridBagConstraints gbc_JTextUser = new GridBagConstraints();
		gbc_JTextUser.insets = new Insets(0, 0, 5, 0);
		gbc_JTextUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_JTextUser.gridx = 1;
		gbc_JTextUser.gridy = 10;
		contentPane.add(JTextUser, gbc_JTextUser);
		JTextUser.setColumns(10);
		/*botonAJugar.addMouseListener(new botonJugar());*/
		
		botonAJugar = new JButton("A JUGAAAAAR!!!");
		botonAJugar.setEnabled(false);
		botonAJugar.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		GridBagConstraints gbc_botonAJugar = new GridBagConstraints();
		gbc_botonAJugar.insets = new Insets(0, 0, 5, 0);
		gbc_botonAJugar.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonAJugar.gridwidth = 2;
		gbc_botonAJugar.gridx = 0;
		gbc_botonAJugar.gridy = 15;
		botonAJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	player1.setNombre(JTextNombre.getText());
				
				//Guardar apellidos.
				player1.setApellidos(JTextApellidos.getText());
				
				player1.setUser(JTextUser.getText());
				player1.setId(Integer.parseInt(JTextId.getText()));
				//Guardar edad.
				if (player1.isNumeric(JTextEdad.getText())==true)//Si el método creado en la clase jugador nos devuelve un true...
					player1.setEdad(Integer.parseInt(JTextEdad.getText()));//...introducimos la edad como int al player1 
					else//De la otra forma...
						player1.setEdad(999);//...la edad se rellena como 999 y eso nos valdrá para aclarar futuras comprobaciones.
				

				//Dependiendo de los campos de texto vamos a mostrar un mensaje de confirmación o error por el JTextCampo.
				if (player1.getEdad()==999){
					JTextComentarios.setText("Edad erronea. Vuelva a rellenarla y pulse A JUGAAAAAAAAAR!!!");
				}else if (player1.enBlanco(player1.getNombre())){
					JTextComentarios.setText("Falta rellenar el nombre. Rellenelo y pulse A JUGAAAAAAAAAR!!!");
				}else if (player1.enBlanco(player1.getApellidos())){
					JTextComentarios.setText("Falta rellenar los apellidos. Rellenelos y pulse A JUGAAAAAAAAAR!!!");
				}else if (player1.enBlanco(player1.getUser())){
					JTextComentarios.setText("Falta rellenar el user. Rellenelo y pulse A JUGAAAAAAAAAR!!!");
				}else{	
				vPrincipal.setVisible(true);
				referencia.dispose();
				vPrincipal.setJugador(player1);
				}
			}
		});
		botonIngresarJugador = new JButton("INGRESAR NUEVO JUGADOR");
		botonIngresarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				/*jdb.cerrarBuscarJugador();*/
				Conectar();
				//Guardar nombre.
				player1.setNombre(JTextNombre.getText());
				
				//Guardar apellidos.
				player1.setApellidos(JTextApellidos.getText());
				
				player1.setUser(JTextUser.getText());
				//Guardar edad.
				if (player1.isNumeric(JTextEdad.getText())==true)//Si el método creado en la clase jugador nos devuelve un true...
					player1.setEdad(Integer.parseInt(JTextEdad.getText()));//...introducimos la edad como int al player1 
					else//De la otra forma...
						player1.setEdad(999);//...la edad se rellena como 999 y eso nos valdrá para aclarar futuras comprobaciones.
				

				//Dependiendo de los campos de texto vamos a mostrar un mensaje de confirmación o error por el JTextCampo.
				if (player1.getEdad()==999){
					JTextComentarios.setText("Edad erronea. Vuelva a rellenarla y pulse A JUGAAAAAAAAAR!!!");
				}else if (player1.enBlanco(player1.getNombre())){
					JTextComentarios.setText("Falta rellenar el nombre. Rellenelo y pulse A JUGAAAAAAAAAR!!!");
				}else if (player1.enBlanco(player1.getApellidos())){
					JTextComentarios.setText("Falta rellenar los apellidos. Rellenelos y pulse A JUGAAAAAAAAAR!!!");
				}else if (player1.enBlanco(player1.getUser())){
					JTextComentarios.setText("Falta rellenar el user. Rellenelo y pulse A JUGAAAAAAAAAR!!!");
				}else{	
					//REGISTRO DE USUARIO EN BBDD
					jdb.insertarUsuario(JTextNombre.getText(), JTextApellidos.getText(), JTextUser.getText(), Integer.valueOf(JTextEdad.getText()));
					player1.setId(jdb.devolverID());
				
					if (db.connectDB()){//MÉTODO BOLEANO QUE DEVUELVE TRUE SI SE HA CONECTADO BIEN A LA BBDD
						JTextComentarios.setText("Creado nuevo jugador: "+player1.getNombre()+" "+player1.getApellidos()+" de "+player1.getEdad()+" años.\nConectado con éxito a la BBDD. Registrado con éxito con el número "+player1.getId()+".");	
					}else{
						JTextComentarios.setText("Creado nuevo jugador: "+player1.getNombre()+" "+player1.getApellidos()+" de "+player1.getEdad()+" años.\nError al conectar con la BBDD");
					}
					botonAJugar.setEnabled(true);
					/*vPrincipal.setVisible(true);
					//referencia.dispose();
					vPrincipal.setJugador(player1);*/
				}
			}
		});
		botonIngresarJugador.addMouseListener(new botonJugar());
		
		lblId = new JLabel("ID");
		lblId.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 12;
		contentPane.add(lblId, gbc_lblId);
		
		JTextId = new JTextField();
		JTextId.setEditable(false);
		JTextId.setColumns(10);
		GridBagConstraints gbc_JTextId = new GridBagConstraints();
		gbc_JTextId.insets = new Insets(0, 0, 5, 0);
		gbc_JTextId.fill = GridBagConstraints.HORIZONTAL;
		gbc_JTextId.gridx = 1;
		gbc_JTextId.gridy = 12;
		contentPane.add(JTextId, gbc_JTextId);
		
		
		botonIngresarJugador.setFont(new Font("Modern No. 20", Font.PLAIN, 15));
		GridBagConstraints gbc_botonNuevoJugador = new GridBagConstraints();
		gbc_botonNuevoJugador.fill = GridBagConstraints.HORIZONTAL;
		gbc_botonNuevoJugador.gridwidth = 2;
		gbc_botonNuevoJugador.insets = new Insets(0, 0, 5, 0);
		gbc_botonNuevoJugador.gridx = 0;
		gbc_botonNuevoJugador.gridy = 14;
		contentPane.add(botonIngresarJugador, gbc_botonNuevoJugador);
		contentPane.add(botonAJugar, gbc_botonAJugar);
		
		JTextComentarios = new JTextArea();
		GridBagConstraints gbc_JTextComentarios = new GridBagConstraints();
		gbc_JTextComentarios.gridheight = 2;
		gbc_JTextComentarios.gridwidth = 2;
		gbc_JTextComentarios.fill = GridBagConstraints.BOTH;
		gbc_JTextComentarios.gridx = 0;
		gbc_JTextComentarios.gridy = 16;
		contentPane.add(JTextComentarios, gbc_JTextComentarios);
		
	/*	//Creamos nuestro objeto para el manejo de la base de datos
			db=new Conexion("localhost","jugadores","root","");
		//Establecemos la conexion
			connected=db.connectDB();
		//Asignamos con el getter la conexion establecida
			conexion=db.getConexion();
		//Pasamos la conexión a un nuevo objeto UsuariosDB para insertar datos.
			jdb=new JugadorDB(conexion); */
	}
	
	//Conectar con la base de datos
	private void Conectar(){
		//Conexión con la BBDD
		//Creamos nuestro objeto para el manejo de la base de datos
		try{
			db=new Conexion("localhost","jugadores","root","");
			//Establecemos la conexion
			connected=db.connectDB();
			//Asignamos con el getter la conexion establecida
			conexion=db.getConexion();
			//Pasamos la conexión a un nuevo objeto JugadorDB para insertar datos.
			jdb=new JugadorDB(conexion);}
		catch(Exception e)
		{
			JTextComentarios.setText( " Debe haber algún problema con la BBDD o con la conexión.");	
		}
	}
	

			



			//IMPLEMENTACIÓN DE INNER CLASS PARA OPTIMIZAR CÓDIGO
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
					/*jdb.cerrarBuscarJugador();*/
					Conectar();
					//Guardar nombre.
					player1.setNombre(JTextNombre.getText());
					
					//Guardar apellidos.
					player1.setApellidos(JTextApellidos.getText());
					
					player1.setUser(JTextUser.getText());
					//Guardar edad.
					if (player1.isNumeric(JTextEdad.getText())==true)//Si el método creado en la clase jugador nos devuelve un true...
						player1.setEdad(Integer.parseInt(JTextEdad.getText()));//...introducimos la edad como int al player1 
						else//De la otra forma...
							player1.setEdad(999);//...la edad se rellena como 999 y eso nos valdrá para aclarar futuras comprobaciones.
					

					//Dependiendo de los campos de texto vamos a mostrar un mensaje de confirmación o error por el JTextCampo.
					if (player1.getEdad()==999){
						JTextComentarios.setText("Edad erronea. Vuelva a rellenarla y pulse A JUGAAAAAAAAAR!!!");
					}else if (player1.enBlanco(player1.getNombre())){
						JTextComentarios.setText("Falta rellenar el nombre. Rellenelo y pulse A JUGAAAAAAAAAR!!!");
					}else if (player1.enBlanco(player1.getApellidos())){
						JTextComentarios.setText("Falta rellenar los apellidos. Rellenelos y pulse A JUGAAAAAAAAAR!!!");
					}else if (player1.enBlanco(player1.getUser())){
						JTextComentarios.setText("Falta rellenar el user. Rellenelo y pulse A JUGAAAAAAAAAR!!!");
					}else{	
						//REGISTRO DE USUARIO EN BBDD
						jdb.insertarUsuario(JTextNombre.getText(), JTextApellidos.getText(), JTextUser.getText(), Integer.valueOf(JTextEdad.getText()));
						player1.setId(jdb.devolverID());
					
						if (db.connectDB()){//MÉTODO BOLEANO QUE DEVUELVE TRUE SI SE HA CONECTADO BIEN A LA BBDD
							JTextComentarios.setText("Creado nuevo jugador: "+player1.getNombre()+" "+player1.getApellidos()+" de "+player1.getEdad()+" años.\nConectado con éxito a la BBDD. Registrado con éxito con el número "+player1.getId()+".");	
						}else{
							JTextComentarios.setText("Creado nuevo jugador: "+player1.getNombre()+" "+player1.getApellidos()+" de "+player1.getEdad()+" años.\nError al conectar con la BBDD");
						}
						botonAJugar.setEnabled(true);
						/*vPrincipal.setVisible(true);
						//referencia.dispose();
						vPrincipal.setJugador(player1);*/
					}
						
				}	
			}
			public void noConecta(){
				JTextComentarios.setText("No se encuentra el user en la BBDD");			}
				}

