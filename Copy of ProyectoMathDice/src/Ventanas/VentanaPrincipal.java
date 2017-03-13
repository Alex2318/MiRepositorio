package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Juego.Jugador;

import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private Jugador player1;
	
	//OBJETOS JPANEL QUE SE AÑADIRÁN A ESTA CLASE
	//SE AÑADEN A TRAVÉS DE UN MANAGER CARD LAYOUT
	private JuegoPrincipiante vPrincipiante=new JuegoPrincipiante();
	private JuegoExperto vExperto=new JuegoExperto();
	private Perfil2 vPerfil=new Perfil2();
	private Puntuaciones vPuntuaciones=new Puntuaciones();
	
	//IDENTIFICADORES
	final static String VENTANAPRINCIPIANTE = "Ventana Principiante";
	final static String VENTANAEXPERTO = "Ventana Experto";
	final static String VENTANAPERFIL = "Ventana Perfil";
	final static String VENTANAPUNTUACIONES = "Ventana Puntuaciones";
	

	
	public VentanaPrincipal() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 720);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnVentanas = new JMenu("Ventanas");
		menuBar.add(mnVentanas);
		
		JMenuItem mntmModoPrincipiante = new JMenuItem("Modo principiante");
		mnVentanas.add(mntmModoPrincipiante);
		
		JMenuItem mntmModoExperto = new JMenuItem("Modo experto");
		mnVentanas.add(mntmModoExperto);
		
		JMenuItem mntmPerfil = new JMenuItem("Perfil");
		mnVentanas.add(mntmPerfil);
		
		JMenuItem mntmPuntuaciones = new JMenuItem("Puntuaciones");
		mnVentanas.add(mntmPuntuaciones);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		mntmModoPrincipiante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    CardLayout cl = (CardLayout)(contentPane.getLayout());
			    cl.show(contentPane,VENTANAPRINCIPIANTE);
			    vPrincipiante.setJugador(player1);
			}
		});
		
		mntmModoExperto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    CardLayout cl = (CardLayout)(contentPane.getLayout());
			    cl.show(contentPane,VENTANAEXPERTO);
			    vExperto.setJugador(player1);
			}
		});
		
		mntmPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    CardLayout cl = (CardLayout)(contentPane.getLayout());
			    cl.show(contentPane,VENTANAPERFIL);
			    vPerfil.setPuntosText(player1.getPuntos());

			}
		});
		
		mntmPuntuaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    CardLayout cl = (CardLayout)(contentPane.getLayout());
			    cl.show(contentPane,VENTANAPUNTUACIONES);
			}
		});
		
		
		contentPane.add(vPrincipiante, VENTANAPRINCIPIANTE);
		contentPane.add(vExperto, VENTANAEXPERTO);
		contentPane.add(vPerfil, VENTANAPERFIL);
		contentPane.add(vPuntuaciones, VENTANAPUNTUACIONES);
	}
	
	//Setter de puntos y nombres del objeto player1 de la clase Jugador
		public void setJugador (Jugador player1) {
				this.player1=player1;	
				
				
				vPrincipiante.setJugador(player1);
				vExperto.setJugador(player1);
				vPerfil.setJugador(player1);
		}

}
