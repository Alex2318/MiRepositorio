package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorListener;


import Juego.Jugador;

import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JuegoPrincipiante extends JPanel {

	//Definimos variables
	
	//ContentPane
	//private JPanel contentPane;
	//Player1 de la clase jugador (Le hemos dado el paso a través de la ventana Login con vJuego.setJugador(player1))
	private Jugador player1;
	//Etiqueta bienvenida creada en el proyecto 05
	private JLabel LabelBienvenida;
	//Etiquetas para meter imágenes de dados
	private JLabel LabelDado_12;
	private JLabel LabelDado_3_1,LabelDado_3_2,LabelDado_3_3;
	private JLabel LabelDado_6_1,LabelDado_6_2;
	//Etiqueta para sacar puntuación obtenida
	private JLabel LabelPuntuacion;
	//Etiqueta donde saldrá la confirmación de que la operación está bien hecha
	private JLabel LabelResultado; 
	//Etiqueta para sacar el número de aciertos seguidos.
	private JLabel LabelAciertosSeguidos;
	//JText dónde haremos las operaciones del juego
	private JTextField JTextOperacion;
	//Etiquetas suma y resta
	private JLabel LabelSuma, LabelResta;
	//Botón Mathdice
	private JButton ButtonMathdice;
	//Botón para resetear el juego
	private JButton btnReset;
	//Label interrogante
	private JLabel LabelInterrogante;
	//Ventana de instrucciones que abriremos con un listener en LabelInterrogante
	private Instrucciones vIns;

	//Diferentes imágenes
    private ImageIcon dado3_1 = null;
    private ImageIcon dado3_2 = null;
    private ImageIcon dado3_3 = null;
    private ImageIcon dado6_1 = null;
    private ImageIcon dado6_2 = null;
    private ImageIcon dado12 = null;
    private ImageIcon dado_gris=new ImageIcon (getClass().getResource("/Imagenes/dadogris.png"));
    
    //Arrays de los distintos dados para posteriormente sacar el valor 
    private int [] valor_3caras=new int [3];
    private int [] valor_6caras=new int [2];
    
    //Valor del dado de doce caras, producido por la función Math.random
    private int valor12 = (int) (Math.round(Math.random() *(1-12)+12));
    //(Math.random() *(mínimo-máximo)+máximo)
    
	//Variable integer para hacer de semáforo entre el dado y el símbolo
	private int tocaDado=0;
	
	//Variable String para acumular la operación que vamos haciendo
	private String operacion="";
	
	//Variable int para controlar el número de símbolos que introducimos (no puede haber más de 4)
	private int nSimbolos=0;
	
	
	
	/*
	 * +ESTAS DOS VARIABLES (resultado y esSuma) SON UNA ALTERNATIVA QUE SE ME HA OCURRIDO POSTERIORMENTE
	 * A HACERME CON EL SCRIPT PARA TRANSFORMAR EL STRING operacion A UN INTEGER Y COMPARARLO CON EL
	 * NÚMERO valor12 A LA HORA DE VER SI HEMOS ACERTADO.
	 * +DE ESTA FORMA MUCHO MÁS SENCILLA SE VAN ACUMULANDO LOS DADOS QUE VAMOS MARCANDO EN UNA VARIABLE
	 * TIPO int Y EN BASE A LO QUE SE HA MARCADO ANTERIORMENTE SE REALIZA CON UN CONDICIONAL if UNA SUMA
	 * O UNA RESTA. 
	 * +PARA NO SATURAR LA APLICACIÓN EN VEZ DE COMPARAR resultado CON valor12 LE HE DICHO QUE SAQUE
	 * POR CONSOLA EL resultado Y ASÍ CERCIORARNOS DE LO INTRODUCIDO
	 */
	private int resultado=0;
	private boolean esSuma=true;
    //Constructor ventana
	public JuegoPrincipiante() {
		
		//setFont(new Font("Modern No. 20", Font.PLAIN, 13));
		//setIconImage(Toolkit.getDefaultToolkit().getImage(JuegoPrincipiante.class.getResource("/Imagenes/dado.png")));
		//setTitle("MATH DICE");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 720);
		
	
		//JPanel
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		//contentPane.
		setLayout(null);
		
		//Etiqueta de bienvenida 
		LabelBienvenida = new JLabel("New Label");
		LabelBienvenida.setFont(new Font("Modern No. 20", Font.PLAIN, 20));
		LabelBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		LabelBienvenida.setBounds(10, 11, 964, 32);
		//contentPane.
		add(LabelBienvenida);
		
		//Etiqueta de dado de 12 caras
		LabelDado_12 = new JLabel("");
		LabelDado_12.setBounds(159, 75, 173, 173);
		//contentPane.
		add(LabelDado_12);
		
		//Etiqueta primer dado 3 caras
		LabelDado_3_1 = new JLabel("New label");
		LabelDado_3_1.setBounds(10, 259, 150, 150);
		//contentPane.
		add(LabelDado_3_1);

		LabelDado_3_1.addMouseListener(new miBotonDado());
		
		//Etiqueta segundo dado 3 caras
		LabelDado_3_2 = new JLabel("New label");
		LabelDado_3_2.setBounds(170, 259, 150, 150);
		//contentPane.
		add(LabelDado_3_2);

		LabelDado_3_2.addMouseListener(new miBotonDado());
		
		//Etiqueta tercer dado 3 caras
		LabelDado_3_3 = new JLabel("New label");
		LabelDado_3_3.setBounds(330, 259, 150, 150);
		//contentPane.
		add(LabelDado_3_3);

		LabelDado_3_3.addMouseListener(new miBotonDado());
		
		//Etiqueta primer dado 6 caras
		LabelDado_6_1 = new JLabel("New label");
		LabelDado_6_1.setBounds(90, 420, 150, 150);
		//contentPane.
		add(LabelDado_6_1);
		  
		LabelDado_6_1.addMouseListener(new miBotonDado());
		
		
		//Etiqueta segundo dado 6 caras
		LabelDado_6_2 = new JLabel("New label");
		LabelDado_6_2.setBounds(251, 420, 150, 150);
		//contentPane.
		add(LabelDado_6_2);

		LabelDado_6_2.addMouseListener(new miBotonDado());
		
		
		//Etiqueta puntuación total
		LabelPuntuacion = new JLabel("");
		LabelPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		LabelPuntuacion.setFont(new Font("Modern No. 20", Font.PLAIN, 20));
		LabelPuntuacion.setBounds(535, 471, 410, 40);
		//contentPane.
		add(LabelPuntuacion);
		
		//Etiqueta símbolo suma
		LabelSuma = new JLabel("New label");
		LabelSuma.setIcon(new ImageIcon(JuegoPrincipiante.class.getResource("/Imagenes/suma.png")));
		LabelSuma.setBounds(535, 54, 150, 150);
		//contentPane.
		add(LabelSuma);
		LabelSuma.addMouseListener(
				new MouseAdapter(){
					@Override
				public void mousePressed(MouseEvent arg0){
						if (tocaDado==1&&nSimbolos<4){
						JTextOperacion.setText(operacion=operacion+String.valueOf(" + "));
						tocaDado=0;
						nSimbolos++;//Suma uno a la variable nSimbolos para contabilizarlos y que no se llegue a más de 4
						esSuma=true;
						}
					}
				}
		);
		
		
		//Etiqueta símbolo resta
		LabelResta = new JLabel("New label");
		LabelResta.setIcon(new ImageIcon(JuegoPrincipiante.class.getResource("/Imagenes/resta.png")));
		LabelResta.setBounds(795, 54, 150, 150);
		//contentPane.
		add(LabelResta);
		LabelResta.addMouseListener(
				new MouseAdapter(){
					@Override
				public void mousePressed(MouseEvent arg0){
						if (tocaDado==1&&nSimbolos<4){
						JTextOperacion.setText(operacion=operacion+String.valueOf(" - "));
						tocaDado=0;
						nSimbolos++;//Suma uno a la variable nSimbolos para contabilizarlos y que no se llegue a más de 4
						esSuma=false;
						}
					}
				}
		);
		
		//JTextField dónde recogeremos tanto los números como los símbolos de las operaciones
		JTextOperacion = new JTextField();
		JTextOperacion.setFont(new Font("Modern No. 20", Font.PLAIN, 30));
		JTextOperacion.setBackground(new Color(255, 255, 153));
		JTextOperacion.setForeground(Color.BLACK);
		JTextOperacion.setEditable(false);
		JTextOperacion.setBounds(534, 215, 411, 57);
		//contentPane.
		add(JTextOperacion);
		JTextOperacion.setColumns(10);
		JTextOperacion.setText(operacion);
		
		//Botón de comprobación MATHDICE
		ButtonMathdice = new JButton("MATHDICE");
		ButtonMathdice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (resultado==valor12){
					LabelResultado.setText("Eres una máquina");//Texto de confirmación
					player1.setPuntos(player1.getPuntos()+2);//Añadimos 5 puntos a los puntos de player1
					LabelPuntuacion.setText("Puntuación total: "+player1.getPuntos()+" puntos.");//Sacamos por la etiqueta los puntos que lleva acumulados player1
					player1.setSeguidos(player1.getSeguidos()+1);
					if(player1.getSeguidos()>1)
					LabelAciertosSeguidos.setText("Enhorabuena, llevas "+player1.getSeguidos()+" aciertos seguidos.");
					ButtonMathdice.setEnabled(false);//Deshabilitamos botón mathdice
					btnReset.setEnabled(true);//Habilitamos botón reset
					System.out.println(player1.getPuntos());
					
				}else{
					LabelResultado.setText("Sigue buscando");
					player1.setSeguidos(0);
					LabelAciertosSeguidos.setText("");
					btnReset.setEnabled(true);//Habilitamos botón reset
					ButtonMathdice.setEnabled(false);//Deshabilitamos botón mathdice
					System.out.println(player1.getPuntos());
					}
				}

		
			});
		ButtonMathdice.setFont(new Font("Modern No. 20", Font.PLAIN, 20));
		ButtonMathdice.setBounds(535, 283, 411, 57);
		//contentPane.
		add(ButtonMathdice);
		
		//Botón para volver a jugar
		btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Reseteamos todos los valores que intervienen en la ventana como al inicio
				valor12 = (int) (Math.round(Math.random() *(1-12)+12));//Vuelve a generar un valor para el dado12
				AsignarValor();//Se generan valores para los dados de tres y seis caras
				AsignarImagen();//Se sacan las respectivas imágenes en las etiquetas
				nSimbolos=0;//Se resetean semáforos
				tocaDado=0;
				operacion="";//Se resetea la operación
				JTextOperacion.setText(operacion);
				btnReset.setEnabled(false);//Se dejan los botones como al principio
				ButtonMathdice.setEnabled(true);
				//LabelDado_3_1.setEnabled(true);
				//LabelDado_3_2.setEnabled(true);
				//LabelDado_3_3.setEnabled(true);
				//LabelDado_6_1.setEnabled(true);
				//LabelDado_6_2.setEnabled(true);
				LabelDado_3_1.addMouseListener(new miBotonDado());
				LabelDado_3_2.addMouseListener(new miBotonDado());
				LabelDado_3_3.addMouseListener(new miBotonDado());
				LabelDado_6_1.addMouseListener(new miBotonDado());
				LabelDado_6_2.addMouseListener(new miBotonDado());
				esSuma=true;
				resultado=0;
			}
		
		});
		btnReset.setEnabled(false);
		btnReset.setFont(new Font("Modern No. 20", Font.PLAIN, 20));
		btnReset.setBounds(535, 351, 411, 57);
		//contentPane.
		add(btnReset);
		
		//Etiqueta para sacar resultado obtenido
		LabelResultado = new JLabel("");
		LabelResultado.setHorizontalAlignment(SwingConstants.CENTER);
		LabelResultado.setFont(new Font("Modern No. 20", Font.PLAIN, 22));
		LabelResultado.setBounds(596, 420, 301, 40);
		//contentPane.
		add(LabelResultado);
		
		//Instancia de la clase Instrucciones para crear ventana de instrucciones
		vIns=new Instrucciones ();
		
		//Etiqueta acceso a ventana instrucciones
		LabelInterrogante = new JLabel("New label");
		LabelInterrogante.setBounds(0, 0, 58, 57);
		//contentPane.
		add(LabelInterrogante);
		LabelInterrogante.setIcon(new ImageIcon(JuegoPrincipiante.class.getResource("/Imagenes/interrogante.png")));
		
		
		//ETIQUETA DONDE SALDRÁ SI SE DA EL CASO EL NÚMERO DE ACIERTOS SEGUIDOS QUE LLEVAMOS
		LabelAciertosSeguidos = new JLabel("");
		LabelAciertosSeguidos.setHorizontalAlignment(SwingConstants.CENTER);
		LabelAciertosSeguidos.setFont(new Font("Modern No. 20", Font.PLAIN, 20));
		LabelAciertosSeguidos.setBounds(535, 530, 410, 40);
		//contentPane.
		add(LabelAciertosSeguidos);
		
		//Listener para acceder a la ventana de instrucciones (vIns)
		LabelInterrogante.addMouseListener(
				new MouseAdapter(){
					@Override
				public void mousePressed(MouseEvent arg0){
						vIns.setVisible(true);
					}
				}
		);
		//Ejecución del método InicarArrays
		AsignarValor();
		
		//Ejecución del método SacarImagen
		AsignarImagen();
	
		trucarImpar();

	}
	//IMPLEMENTACIÓN DE INNER CLASS PARA OPTIMIZAR CÓDIGO
	private class miBotonDado implements MouseListener {

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

			if (tocaDado==0 && esSuma){
				JLabel b=(JLabel)arg0.getSource();
				JTextOperacion.setText(operacion=operacion+(b.getName()));
				resultado=resultado+Integer.parseInt(b.getName());
				b.setIcon(dado_gris);
				tocaDado=1;
				//b.setEnabled(false);
				b.removeMouseListener(this);
				//System.out.println(resultado);
			}else if (tocaDado==0 && esSuma==false){
				JLabel b=(JLabel)arg0.getSource();
				JTextOperacion.setText(operacion=operacion+(b.getName()));
				resultado=resultado-Integer.parseInt(b.getName());
				b.setIcon(dado_gris);
				tocaDado=1;
				//b.setEnabled(false);
				b.removeMouseListener(this);
				//System.out.println(resultado);
				}
		}	
	}

	//Setter de puntos y nombres del objeto player1 de la clase Jugador
	public void setJugador (Jugador player1) {
		this.player1=player1;
		LabelBienvenida.setText("Hola "+player1.getNombre()+" bienvenid@ a Math Dice.");
		
	}
	
	//Metodo a través de arrays sacar valor diferentes dados
	public void AsignarValor(){
		
		for(int i=0;i<valor_3caras.length;i++){
			valor_3caras[i]= (int) (Math.round(Math.random() *(1-3)+3));
		}
		for(int i=0;i<valor_6caras.length;i++){
			valor_6caras[i]= (int) (Math.round(Math.random() *(1-6)+6));
		}
		/*+ A TRAVÉS DE UN setName LE DOY EL VALOR A CADA IMAGEN QUE HEMOS SACADO CON EL RANDOM
		 * 
		 */
		LabelDado_3_1.setName(String.valueOf(valor_3caras[0]));
		LabelDado_3_2.setName(String.valueOf(valor_3caras[1]));
		LabelDado_3_3.setName(String.valueOf(valor_3caras[2]));
		LabelDado_6_1.setName(String.valueOf(valor_6caras[0]));
		LabelDado_6_2.setName(String.valueOf(valor_6caras[1]));
	}
	
	//Método para en el caso de que valor12 sea impar y los 4 primeros dados pares, el quinto sea impar
	public void trucarImpar(){
		if (valor12%2 != 0 && valor_3caras[0]%2==0 && valor_3caras[1]%2==0 && valor_3caras[2]%2==0 && 
				valor_6caras[0]%2==0 ){

					    if(valor_6caras[1]%2 ==0)
					    {
					          if(valor_6caras[1]==6) {
					        	  valor_6caras[1] = valor_6caras[1] - 1;  
					          }else{
					        	  valor_6caras[1] = valor_6caras[1] +1;
					          }
					    }
		}
	}
	
	//Inicio del método para asignar una imagen a cada valor
	public void AsignarImagen(){
		
		//Primer dado de tres caras
        if (valor_3caras[0] == 1) {
            dado3_1 = new ImageIcon(getClass().getResource("/Imagenes/dado1_3.png"));
        } else if (valor_3caras[0] == 2) {
            dado3_1 = new ImageIcon(getClass().getResource("/Imagenes/dado2_3.png"));
        } else if (valor_3caras[0] == 3) {
            dado3_1 = new ImageIcon(getClass().getResource("/Imagenes/dado3_3.png"));
        } 
        LabelDado_3_1.setIcon(dado3_1);
        
        //Segundo dado de tres caras
        if (valor_3caras[1] == 1) {
            dado3_2 = new ImageIcon(getClass().getResource("/Imagenes/dado1_3.png"));
        } else if (valor_3caras[1] == 2) {
            dado3_2 = new ImageIcon(getClass().getResource("/Imagenes/dado2_3.png"));
        } else if (valor_3caras[1] == 3) {
            dado3_2 = new ImageIcon(getClass().getResource("/Imagenes/dado3_3.png"));
        } 
        LabelDado_3_2.setIcon(dado3_2);
        
        //Tercer dado de tres caras
        if (valor_3caras[2] == 1) {
            dado3_3 = new ImageIcon(getClass().getResource("/Imagenes/dado1_3.png"));
        } else if (valor_3caras[2] == 2) {
            dado3_3 = new ImageIcon(getClass().getResource("/Imagenes/dado2_3.png"));
        } else if (valor_3caras[2] == 3) {
            dado3_3 = new ImageIcon(getClass().getResource("/Imagenes/dado3_3.png"));
        } 
        LabelDado_3_3.setIcon(dado3_3);
        
        //Primer dado de seis caras
        if (valor_6caras[0] == 1) {
            dado6_1 = new ImageIcon(getClass().getResource("/Imagenes/dado1_6.png"));
        } else if (valor_6caras[0] == 2) {
        	dado6_1 = new ImageIcon(getClass().getResource("/Imagenes/dado2_6.png"));
        } else if (valor_6caras[0] == 3) {
        	dado6_1 = new ImageIcon(getClass().getResource("/Imagenes/dado3_6.png"));
        } else if (valor_6caras[0] == 4) {
        	dado6_1 = new ImageIcon(getClass().getResource("/Imagenes/dado4_6.png"));
        } else if (valor_6caras[0] == 5) {
        	dado6_1 = new ImageIcon(getClass().getResource("/Imagenes/dado5_6.png"));
        } else if (valor_6caras[0] == 6) {
        	dado6_1 = new ImageIcon(getClass().getResource("/Imagenes/dado6_6.png"));
        } 
        LabelDado_6_1.setIcon(dado6_1);
        
        //Segundo dado de seis caras
        if (valor_6caras[1] == 1) {
            dado6_2 = new ImageIcon(getClass().getResource("/Imagenes/dado1_6.png"));
        } else if (valor_6caras[1] == 2) {
        	dado6_2 = new ImageIcon(getClass().getResource("/Imagenes/dado2_6.png"));
        } else if (valor_6caras[1] == 3) {
        	dado6_2 = new ImageIcon(getClass().getResource("/Imagenes/dado3_6.png"));
        } else if (valor_6caras[1] == 4) {
        	dado6_2 = new ImageIcon(getClass().getResource("/Imagenes/dado4_6.png"));
        } else if (valor_6caras[1] == 5) {
        	dado6_2 = new ImageIcon(getClass().getResource("/Imagenes/dado5_6.png"));
        } else if (valor_6caras[1] == 6) {
        	dado6_2 = new ImageIcon(getClass().getResource("/Imagenes/dado6_6.png"));
        } 
        LabelDado_6_2.setIcon(dado6_2);
        
        //Dado de doce caras
        if (valor12 == 1) {
            dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_1.png"));
        } else if (valor12 == 2) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_2.png"));
        } else if (valor12 == 3) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_3.png"));
        } else if (valor12 == 4) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_4.png"));
        } else if (valor12 == 5) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_5.png"));
        } else if (valor12 == 6) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_6.png"));
        } else if (valor12 == 7){
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_7.png"));
        } else if (valor12 == 8) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_8.png"));
        } else if (valor12 == 9) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_9.png"));
        } else if (valor12 == 10) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_10.png"));
        } else if (valor12 == 11) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_11.png"));
        } else if (valor12 == 12) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_12.png"));
        } 
        LabelDado_12.setIcon(dado12);
        
	}
}
