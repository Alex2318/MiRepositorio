package Ventanas;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import Juego.Jugador;

import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Perfil extends JPanel {
	private JTextField nombreTextField;
	private JTextField apellidosTextField;
	private JTextField edadTextField;
	private JTextField puntosTextField;
	private JTextField comentariosTextField;
	private JLabel labelImagen, labelMiperfil, labelNombre, labellApellidos, labelEdad, labelPuntos, labelComentarios;

	private JButton botonCambios;
	
	private Jugador player1;
	
	public Perfil() {
		setBounds(100, 100, 1000, 720);
		setLayout(null);
		
		labelImagen = new JLabel("New label");
		labelImagen.setBounds(42, 104, 303, 248);
		add(labelImagen);
		labelImagen.setIcon(new ImageIcon(JuegoPrincipiante.class.getResource("/Imagenes/smiley.jpg")));
		
		labelMiperfil = new JLabel("MI PERFIL");
		labelMiperfil.setFont(new Font("Modern No. 20", Font.PLAIN, 25));
		labelMiperfil.setBounds(454, 37, 145, 28);
		add(labelMiperfil);
		
		labelNombre = new JLabel("Nombre");
		labelNombre.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		labelNombre.setBounds(392, 104, 66, 20);
		add(labelNombre);
		
		nombreTextField = new JTextField();
		nombreTextField.setBounds(488, 104, 444, 20);
		add(nombreTextField);
		nombreTextField.setColumns(10);
		
		
		
		labellApellidos = new JLabel("Apellidos");
		labellApellidos.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		labellApellidos.setBounds(392, 169, 78, 20);
		add(labellApellidos);
		
		apellidosTextField = new JTextField();
		apellidosTextField.setBounds(488, 170, 444, 20);
		add(apellidosTextField);
		apellidosTextField.setColumns(10);
		
		
		labelEdad = new JLabel("Edad");
		labelEdad.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		labelEdad.setBounds(392, 227, 66, 20);
		add(labelEdad);
		
		edadTextField = new JTextField();
		edadTextField.setBounds(488, 228, 86, 20);
		add(edadTextField);
		edadTextField.setColumns(10);
		
		
		labelPuntos = new JLabel("Puntos");
		labelPuntos.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		labelPuntos.setBounds(392, 286, 66, 18);
		add(labelPuntos);
		
		puntosTextField = new JTextField();
		puntosTextField.setEditable(false);
		puntosTextField.setColumns(10);
		puntosTextField.setBounds(488, 286, 86, 20);
		add(puntosTextField);
		
		
		labelComentarios = new JLabel("Comentarios");
		labelComentarios.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		labelComentarios.setBounds(392, 347, 102, 28);
		add(labelComentarios);
		
		comentariosTextField = new JTextField();
		comentariosTextField.setBounds(488, 347, 414, 97);
		add(comentariosTextField);
		comentariosTextField.setColumns(10);
		
		botonCambios = new JButton("Guardar cambios");
		botonCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//puntosTextField.setText(String.valueOf(player1.getPuntos()));
				player1.setNombre(nombreTextField.getText());
				player1.setApellidos(apellidosTextField.getText());
				player1.setEdad(Integer.parseInt(edadTextField.getText()));
			}
		});
		botonCambios.setFont(new Font("Modern No. 20", Font.PLAIN, 18));
		botonCambios.setBounds(392, 477, 510, 62);
		add(botonCambios);
	}
	
	//Setter de puntos y nombres del objeto player1 de la clase Jugador
		public void setJugador (Jugador player1) {
			this.player1=player1;
			nombreTextField.setText(player1.getNombre());
			apellidosTextField.setText(player1.getApellidos());
			edadTextField.setText(String.valueOf(player1.getEdad()));
			puntosTextField.setText(String.valueOf(player1.getPuntos()));
			
		}
		
		public void setPuntosText (int puntos){
			puntosTextField.setText(String.valueOf(player1.getPuntos()));
		}
}
