package Vista;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import Modelo.Conexion;
import Modelo.Jugador;
import Modelo.JugadorDB;

import java.awt.Font;
import javax.swing.DropMode;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class Perfil2 extends JPanel {
	private JTextField CajaNombre;
	private JTextField CajaApellidos;
	private JTextField CajaEdad;
	private JTextField CajaPuntos;
	private JTextField CajaUser;
	private JTextArea CajaComentarios;

	private JLabel LabelMiPerfil;
	private JLabel LabelImagen;
	private JLabel LabelNombre;
	private JLabel LabelApellidos;
	private JLabel LabelEdad;
	private JLabel LabelPuntos;
	private JLabel LabelComentarios;
	private JLabel LabelUser;
	
	private JButton BotonGuardar;
	private Jugador player1;

    //Manejadores de la base de datos
	private Conexion db;
	private JugadorDB jdb;
	private Connection conexion; //Conexión
	private boolean connected =false; //Conexión con éxito
	
	public Perfil2() {
		
		setBounds(100, 100, 1000, 720);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{61, 63, 76, 207, 236};
		gridBagLayout.rowHeights = new int[]{39, 0, 40, 55, 55, 57, 61, 59, 56, 48, 33, 0, -34, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 4.9E-324, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		LabelMiPerfil = new JLabel("MI PERFIL");
		LabelMiPerfil.setForeground(new Color(165, 42, 42));
		LabelMiPerfil.setFont(new Font("Modern No. 20", Font.PLAIN, 28));
		GridBagConstraints gbc_LabelMiPerfil = new GridBagConstraints();
		gbc_LabelMiPerfil.gridwidth = 8;
		gbc_LabelMiPerfil.insets = new Insets(0, 0, 5, 0);
		gbc_LabelMiPerfil.gridx = 0;
		gbc_LabelMiPerfil.gridy = 1;
		add(LabelMiPerfil, gbc_LabelMiPerfil);
		
		LabelImagen = new JLabel("");
		GridBagConstraints gbc_LabelImagen = new GridBagConstraints();
		gbc_LabelImagen.anchor = GridBagConstraints.WEST;
		gbc_LabelImagen.gridwidth = 4;
		LabelImagen.setBounds(42, 104, 303, 248);
		LabelImagen.setIcon(new ImageIcon(JuegoPrincipiante.class.getResource("/Imagenes/smiley.jpg")));
		gbc_LabelImagen.insets = new Insets(0, 0, 5, 5);
		gbc_LabelImagen.gridheight = 6;
		gbc_LabelImagen.gridx = 1;
		gbc_LabelImagen.gridy = 3;
		add(LabelImagen, gbc_LabelImagen);
		
		LabelNombre = new JLabel("Nombre");
		LabelNombre.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		GridBagConstraints gbc_LabelNombre = new GridBagConstraints();
		gbc_LabelNombre.weightx = 0.15;
		gbc_LabelNombre.insets = new Insets(0, 0, 5, 5);
		gbc_LabelNombre.gridx = 4;
		gbc_LabelNombre.gridy = 3;
		add(LabelNombre, gbc_LabelNombre);
		
		LabelApellidos = new JLabel("Apellidos");
		LabelApellidos.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		GridBagConstraints gbc_LabelApellidos = new GridBagConstraints();
		gbc_LabelApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_LabelApellidos.gridx = 4;
		gbc_LabelApellidos.gridy = 4;
		add(LabelApellidos, gbc_LabelApellidos);
		
		LabelPuntos = new JLabel("Puntos");
		LabelPuntos.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		GridBagConstraints gbc_LabelPuntos = new GridBagConstraints();
		gbc_LabelPuntos.insets = new Insets(0, 0, 5, 5);
		gbc_LabelPuntos.gridx = 4;
		gbc_LabelPuntos.gridy = 7;
		add(LabelPuntos, gbc_LabelPuntos);
		
		LabelEdad = new JLabel("Edad");
		LabelEdad.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		GridBagConstraints gbc_LabelEdad = new GridBagConstraints();
		gbc_LabelEdad.insets = new Insets(0, 0, 5, 5);
		gbc_LabelEdad.gridx = 4;
		gbc_LabelEdad.gridy = 6;
		add(LabelEdad, gbc_LabelEdad);
		
		LabelUser = new JLabel("User");
		LabelUser.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		GridBagConstraints gbc_LabelUser = new GridBagConstraints();
		gbc_LabelUser.insets = new Insets(0, 0, 5, 5);
		gbc_LabelUser.gridx = 4;
		gbc_LabelUser.gridy = 5;
		add(LabelUser, gbc_LabelUser);
		
		LabelComentarios = new JLabel("Comentarios");
		LabelComentarios.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		GridBagConstraints gbc_LabelComentarios = new GridBagConstraints();
		gbc_LabelComentarios.insets = new Insets(0, 0, 5, 5);
		gbc_LabelComentarios.gridx = 4;
		gbc_LabelComentarios.gridy = 8;
		add(LabelComentarios, gbc_LabelComentarios);
		
		CajaComentarios = new JTextArea();
		GridBagConstraints gbc_CajaComentarios = new GridBagConstraints();
		gbc_CajaComentarios.gridwidth = 2;
		gbc_CajaComentarios.gridheight = 2;
		gbc_CajaComentarios.fill = GridBagConstraints.BOTH;
		gbc_CajaComentarios.insets = new Insets(0, 0, 5, 5);
		gbc_CajaComentarios.gridx = 5;
		gbc_CajaComentarios.gridy = 8;
		add(CajaComentarios, gbc_CajaComentarios);
		CajaComentarios.setColumns(10);
		
		CajaNombre = new JTextField();
		GridBagConstraints gbc_CajaNombre = new GridBagConstraints();
		gbc_CajaNombre.weightx = 0.5;
		gbc_CajaNombre.gridwidth = 2;
		gbc_CajaNombre.insets = new Insets(0, 0, 5, 5);
		gbc_CajaNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_CajaNombre.gridx = 5;
		gbc_CajaNombre.gridy = 3;
		add(CajaNombre, gbc_CajaNombre);
		CajaNombre.setColumns(10);
		
		CajaApellidos = new JTextField();
		GridBagConstraints gbc_CajaApellidos = new GridBagConstraints();
		gbc_CajaApellidos.weightx = 0.5;
		gbc_CajaApellidos.gridwidth = 2;
		gbc_CajaApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_CajaApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_CajaApellidos.gridx = 5;
		gbc_CajaApellidos.gridy = 4;
		add(CajaApellidos, gbc_CajaApellidos);
		CajaApellidos.setColumns(10);
		
		CajaEdad = new JTextField();
		GridBagConstraints gbc_CajaEdad = new GridBagConstraints();
		gbc_CajaEdad.gridwidth = 2;
		gbc_CajaEdad.weightx = 0.03;
		gbc_CajaEdad.insets = new Insets(0, 0, 5, 5);
		gbc_CajaEdad.fill = GridBagConstraints.HORIZONTAL;
		gbc_CajaEdad.gridx = 5;
		gbc_CajaEdad.gridy = 6;
		add(CajaEdad, gbc_CajaEdad);
		CajaEdad.setColumns(10);
		
		CajaUser = new JTextField();
		GridBagConstraints gbc_CajaUser = new GridBagConstraints();
		gbc_CajaUser.gridwidth = 2;
		gbc_CajaUser.weightx = 0.03;
		gbc_CajaUser.insets = new Insets(0, 0, 5, 5);
		gbc_CajaUser.fill = GridBagConstraints.HORIZONTAL;
		gbc_CajaUser.gridx = 5;
		gbc_CajaUser.gridy = 5;
		add(CajaUser, gbc_CajaUser);
		CajaUser.setColumns(10);
		
		CajaPuntos = new JTextField();
		CajaPuntos.setEditable(false);
		CajaPuntos.setText("");
		GridBagConstraints gbc_CajaPuntos = new GridBagConstraints();
		gbc_CajaPuntos.gridwidth = 2;
		gbc_CajaPuntos.insets = new Insets(0, 0, 5, 5);
		gbc_CajaPuntos.fill = GridBagConstraints.HORIZONTAL;
		gbc_CajaPuntos.gridx = 5;
		gbc_CajaPuntos.gridy = 7;
		add(CajaPuntos, gbc_CajaPuntos);
		CajaPuntos.setColumns(10);
		
		BotonGuardar = new JButton("Guardar cambios");
		BotonGuardar.addMouseListener(new miBotonGuardar());
		BotonGuardar.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		GridBagConstraints gbc_BotonGuardar = new GridBagConstraints();
		gbc_BotonGuardar.fill = GridBagConstraints.BOTH;
		gbc_BotonGuardar.gridheight = 3;
		gbc_BotonGuardar.gridwidth = 3;
		gbc_BotonGuardar.insets = new Insets(0, 0, 5, 5);
		gbc_BotonGuardar.gridx = 4;
		gbc_BotonGuardar.gridy = 11;
		add(BotonGuardar, gbc_BotonGuardar);
		


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
			jdb=new JugadorDB(conexion);}
		catch(Exception e)
		{
			CajaComentarios.setText("Debe haber algún problema con la BBDD. Vuelve a intentarlo");
		}
	}
	
	
	//INNER CLASS PARA EL BOTÓN GUARDAR CAMBIOS
	private class miBotonGuardar implements MouseListener {
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
		public void mousePressed(MouseEvent e) {
			
			System.out.println(CajaUser.getText());
			System.out.println(player1.getUser());
			if(CajaUser.getText().equals(player1.getUser())){
				Conectar();
				player1.setNombre(CajaNombre.getText());
				player1.setApellidos(CajaApellidos.getText());
				player1.setEdad(Integer.parseInt(CajaEdad.getText()));
				CajaComentarios.setText("Cambios guardados con éxito para el jugador "+player1.getNombre()+" "+player1.getApellidos()+" de "+player1.getEdad()+" años.");
				jdb.actualizarJugador(player1);
			}else{
				Conectar();
			Jugador j=jdb.buscarJugador(CajaUser.getText(), player1.getPassword());
			if(j.getUser()!=null){
				CajaComentarios.setText("El user introducido ya existe.");
				CajaUser.setText(player1.getUser());
			}else{
			Conectar();
			player1.setNombre(CajaNombre.getText());
			player1.setApellidos(CajaApellidos.getText());
			player1.setEdad(Integer.parseInt(CajaEdad.getText()));
			player1.setUser(CajaUser.getText());
			CajaComentarios.setText("Cambios guardados con éxito para el jugador "+player1.getNombre()+" "+player1.getApellidos()+" de "+player1.getEdad()+" años.");
			jdb.actualizarJugador(player1);
		}}}

		}	
	
	
	//Setter de puntos y nombres del objeto player1 de la clase Jugador
	public void setJugador (Jugador player1) {
		this.player1=player1;
		CajaNombre.setText(player1.getNombre());
		CajaApellidos.setText(player1.getApellidos());
		CajaEdad.setText(String.valueOf(player1.getEdad()));
		CajaPuntos.setText(String.valueOf(player1.getPuntos()));
		CajaUser.setText(player1.getUser());
	}
	

}
