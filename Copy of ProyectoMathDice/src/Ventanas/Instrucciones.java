package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Toolkit;
import java.awt.Color;

public class Instrucciones extends JFrame {

	private JPanel contentPane;
	private JTextPane txtpnInstruccionesDelJuego;
	


	public Instrucciones() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Instrucciones.class.getResource("/Imagenes/dado.png")));
		setTitle("MATH DICE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Cambiamos el EXIT_ON_CLOSE por el DISPOSE_ON_CLOSE para que solo se cierre la ventana Instrucciones al darle al botón cerrar, y no toda la aplicación.
		setBounds(100, 100, 450, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtpnInstruccionesDelJuego = new JTextPane();
		txtpnInstruccionesDelJuego.setBackground(Color.WHITE);
		txtpnInstruccionesDelJuego.setForeground(Color.BLACK);
		txtpnInstruccionesDelJuego.setFont(new Font("Modern No. 20", Font.PLAIN, 20));
		txtpnInstruccionesDelJuego.setText("INSTRUCCIONES DEL JUEGO:\r\n\r\n-El objetivo del juego es lograr llegar al n\u00FAmero que se muestra en el dado de doce caras (dado octogonal azul) sumando y/o restando tres dados de tres caras (dados de color rojo) y dos dados de seis caras (dado cuadrado azul).\r\n\r\n-Cuando creas que has llegado a la soluci\u00F3n, pulsa el bot\u00F3n MathDice.\r\n\r\n-Aciertes o falles puedes seguir jugando apretando el bot\u00F3n Reset");
		txtpnInstruccionesDelJuego.setEnabled(false);
		txtpnInstruccionesDelJuego.setEditable(false);
		txtpnInstruccionesDelJuego.setBounds(0, 0, 434, 301);
		contentPane.add(txtpnInstruccionesDelJuego);
	}
}
