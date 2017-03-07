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

public class JuegoExperto extends JPanel {

	//Definimos variables
	
	//ContentPane
	//private JPanel contentPane;
	//Player1 de la clase jugador
	private Jugador player1;
	//Etiqueta bienvenida creada en el proyecto 05
	private JLabel LabelBienvenidaExp;
	//Etiquetas para meter imágenes de dados
	private JLabel LabelDado_12_1,LabelDado_12_2;
	private JLabel LabelDado_3_1,LabelDado_3_2,LabelDado_3_3;
	private JLabel LabelDado_6_1,LabelDado_6_2,LabelDado_6_3;
	//Etiqueta para sacar puntuación obtenida
	private JLabel LabelPuntuacion;
	//Etiqueta donde saldrá la confirmación de que la operación está bien hecha
	private JLabel LabelResultado; 
	//Etiqueta para sacar el número de aciertos seguidos.
	private JLabel LabelAciertosSeguidos;
	//JText dónde haremos las operaciones del juego
	private JTextField JTextOperacion;
	//Botónes operaciones
	private JButton botonSuma, botonResta, botonProducto, botonDivision, botonParentesis, botonParentesisCierre;
	//Botón Mathdice
	private JButton ButtonMathdice;
	//Botón para resetear el juego
	private JButton btnReset;
	//Etiqueta interrogante
	private JLabel LabelInterrogante;
	//Ventana de instrucciones que abriremos con un listener en LabelInterrogante
	private InstruccionesExperto vInsE;
	//Etiqueta objetivo
	private JLabel LabelObjetivo;

	//Diferentes imágenes
    private ImageIcon dado3_1 = null;
    private ImageIcon dado3_2 = null;
    private ImageIcon dado3_3 = null;
    private ImageIcon dado6_1 = null;
    private ImageIcon dado6_2 = null;
    private ImageIcon dado6_3 = null;
    private ImageIcon dado12 = null;
    private ImageIcon dado12_2= null;
    private ImageIcon dado_gris=new ImageIcon (getClass().getResource("/Imagenes/dadogris.png"));
    
    //Arrays de los distintos dados para posteriorment sacar el valor 
    private int [] valor_3caras=new int [3];
    private int [] valor_6caras=new int [3];
    
    //Valor del dado de doce caras, producido por la función Math.random
    private int valor12_1 = (int) (Math.round(Math.random() *(1-12)+12));
    private int valor12_2 = (int) (Math.round(Math.random() *(1-12)+12));
    //(Math.random() *(mínimo-máximo)+máximo)
    
	
	//Variable objetivo que la incializamos a cero para posteriormente pedirle que sea el resultado del producto de los valores de los dados de doce caras
	private int objetivo=0;

	//Variable integer para hacer de semáforo entre el dado y el símbolo
	private int tocaDado=0;
	
	//Semáforo paréntesis
	private boolean parentesisCerrado=true;
	
	//Variable String para acumular la operación que vamos haciendo
	private String operacion="";
	
	//Variable int para controlar el número de símbolos que introducimos (no puede haber más de 5)
	private int nSimbolos=0;
	
	
    //Constructor ventana
	public JuegoExperto() {
		
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
		LabelBienvenidaExp = new JLabel("New Label");
		LabelBienvenidaExp.setFont(new Font("Modern No. 20", Font.PLAIN, 20));
		LabelBienvenidaExp.setHorizontalAlignment(SwingConstants.CENTER);
		LabelBienvenidaExp.setBounds(10, 11, 964, 32);
		add(LabelBienvenidaExp);
		
		//Etiqueta de primer dado de 12 caras
		LabelDado_12_1 = new JLabel("");
		LabelDado_12_1.setBounds(228, 75, 173, 173);
		add(LabelDado_12_1);
		
		//Etiqueta de segundo dado de 12 caras
		LabelDado_12_2 = new JLabel("");
		LabelDado_12_2.setBounds(10, 75, 173, 173);
		add(LabelDado_12_2);
		
		//Etiqueta para sacar por pantalla el objetivo
		LabelObjetivo = new JLabel("New Label");
		LabelObjetivo.setHorizontalAlignment(SwingConstants.CENTER);
		LabelObjetivo.setFont(new Font("Modern No. 20", Font.BOLD, 27));
		LabelObjetivo.setBounds(10, 259, 439, 89);
		add(LabelObjetivo);
		
		//Etiqueta primer dado 3 caras
		LabelDado_3_1 = new JLabel("New label");
		LabelDado_3_1.setBounds(10, 359, 150, 150);
		add(LabelDado_3_1);
		LabelDado_3_1.addMouseListener(new miBotonDado());
		
		//Etiqueta segundo dado 3 caras
		LabelDado_3_2 = new JLabel("New label");
		LabelDado_3_2.setBounds(170, 359, 150, 150);
		add(LabelDado_3_2);
		LabelDado_3_2.addMouseListener(new miBotonDado());
		
		//Etiqueta tercer dado 3 caras
		LabelDado_3_3 = new JLabel("New label");
		LabelDado_3_3.setBounds(330, 359, 150, 150);
		add(LabelDado_3_3);
		LabelDado_3_3.addMouseListener(new miBotonDado());
		
		//Etiqueta primer dado 6 caras
		LabelDado_6_1 = new JLabel("New label");
		LabelDado_6_1.setBounds(10, 520, 150, 150);
		add(LabelDado_6_1);
		LabelDado_6_1.addMouseListener(new miBotonDado());
		
		//Etiqueta segundo dado 6 caras
		LabelDado_6_2 = new JLabel("New label");
		LabelDado_6_2.setBounds(170, 520, 150, 150);
		add(LabelDado_6_2);
		LabelDado_6_2.addMouseListener(new miBotonDado());
		
		//Etiqueta tercer dado 6 caras
		LabelDado_6_3 = new JLabel("New label");
		LabelDado_6_3.setBounds(330, 520, 150, 150);
		add(LabelDado_6_3);
		LabelDado_6_3.addMouseListener(new miBotonDado());
		
		//Botón para sumar en la operación
		botonSuma = new JButton("+");
		botonSuma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tocaDado==1&&nSimbolos<5){
					JTextOperacion.setText(operacion=operacion+String.valueOf(" + "));
					tocaDado=0;
					nSimbolos++;//Suma uno a la variable nSimbolos para contabilizarlos y que no se llegue a más de 4
					}
			}
		});
		botonSuma.setFont(new Font("Modern No. 20", Font.BOLD, 30));
		botonSuma.setBounds(531, 55, 80, 80);
		add(botonSuma);

		//Botón para restar en la operación
		botonResta = new JButton("-");
		botonResta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tocaDado==1&&nSimbolos<5){
					JTextOperacion.setText(operacion=operacion+String.valueOf(" - "));
					tocaDado=0;
					nSimbolos++;//Suma uno a la variable nSimbolos para contabilizarlos y que no se llegue a más de 4
					}
			}
		});
		botonResta.setFont(new Font("Modern No. 20", Font.BOLD, 30));
		botonResta.setBounds(707, 54, 80, 80);
		add(botonResta);
		
		//Botón para multiplicar en la operación
		botonProducto = new JButton("*");
		botonProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tocaDado==1&&nSimbolos<5){
					JTextOperacion.setText(operacion=operacion+String.valueOf(" * "));
					tocaDado=0;
					nSimbolos++;//Suma uno a la variable nSimbolos para contabilizarlos y que no se llegue a más de 4
					}
			}
		});
		botonProducto.setFont(new Font("Modern No. 20", Font.BOLD, 30));
		botonProducto.setBounds(865, 54, 80, 80);
		add(botonProducto);
		
		//Botón para dividir en la operación
		botonDivision = new JButton("/");
		botonDivision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tocaDado==1&&nSimbolos<5){
					JTextOperacion.setText(operacion=operacion+String.valueOf(" / "));
					tocaDado=0;
					nSimbolos++;//Suma uno a la variable nSimbolos para contabilizarlos y que no se llegue a más de 4
					}
			}
		});
		botonDivision.setFont(new Font("Modern No. 20", Font.BOLD, 30));
		botonDivision.setBounds(531, 158, 80, 80);
		add(botonDivision);
		
		//Botón para abrir un paréntesis en la operación
		botonParentesis = new JButton("(");
		botonParentesis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (parentesisCerrado==true && tocaDado==0){
					JTextOperacion.setText(operacion=operacion+String.valueOf(" ( "));
					parentesisCerrado=false;
					tocaDado=0;
					}
					}
		});
		botonParentesis.setFont(new Font("Modern No. 20", Font.BOLD, 30));
		botonParentesis.setBounds(707, 157, 80, 80);
		add(botonParentesis);

		//Botón para cerrar un paréntesis en la operación
		botonParentesisCierre = new JButton(")");
		botonParentesisCierre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (tocaDado==1 && parentesisCerrado==false ){
					JTextOperacion.setText(operacion=operacion+String.valueOf(" ) "));
					parentesisCerrado=true;
					tocaDado=1;
					}
					}
		});
		botonParentesisCierre.setFont(new Font("Modern No. 20", Font.BOLD, 30));
		botonParentesisCierre.setBounds(865, 157, 80, 80);
		add(botonParentesisCierre);
		

		//Etiqueta puntuación total
		LabelPuntuacion = new JLabel("");
		LabelPuntuacion.setHorizontalAlignment(SwingConstants.CENTER);
		LabelPuntuacion.setFont(new Font("Modern No. 20", Font.PLAIN, 20));
		LabelPuntuacion.setBounds(535, 520, 410, 40);
		add(LabelPuntuacion);
		
		//JTextField dónde recogeremos tanto los números como los símbolos de las operaciones
		JTextOperacion = new JTextField();
		JTextOperacion.setFont(new Font("Modern No. 20", Font.PLAIN, 30));
		JTextOperacion.setBackground(new Color(255, 255, 153));
		JTextOperacion.setForeground(Color.BLACK);
		JTextOperacion.setEditable(false);
		JTextOperacion.setBounds(534, 248, 411, 57);
		add(JTextOperacion);
		JTextOperacion.setColumns(10);
		JTextOperacion.setText(operacion);
		
		//Botón de comprobación MATHDICE
		ButtonMathdice = new JButton("MATHDICE");
		ButtonMathdice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
						//Método para transformar String de una operación en un valor int
						    ScriptEngineManager mgr = new ScriptEngineManager();
						    ScriptEngine engine = mgr.getEngineByName("JavaScript");
						    try {
								int i = ((Integer) (engine.eval(operacion))).intValue();//El objeto generado por las clases importadas lo pasamos a un int
								if (i==objetivo){
									LabelResultado.setText("Eres una máquina");//Texto de confirmación
									player1.setPuntos(player1.getPuntos()+5);//Añadimos 5 puntos a los puntos de player1
									LabelPuntuacion.setText("Puntuación total: "+player1.getPuntos()+" puntos.");//Sacamos por la etiqueta los puntos que lleva acumulados player1
									ButtonMathdice.setEnabled(false);//Deshabilitamos botón mathdice
									btnReset.setEnabled(true);//Habilitamos botón reset
									player1.setSeguidos(player1.getSeguidos()+1);
									if(player1.getSeguidos()>1)
									LabelAciertosSeguidos.setText("Enhorabuena, llevas "+player1.getSeguidos()+" aciertos seguidos.");
								}else if  (i>objetivo && i<objetivo+(objetivo*0.10) || i<objetivo && i>objetivo-(objetivo*0.10)){
									LabelResultado.setText("Casiiiiiiiiii");//Texto de confirmación
									player1.setPuntos(player1.getPuntos()+3);//Añadimos 5 puntos a los puntos de player1
									LabelPuntuacion.setText("Puntuación total: "+player1.getPuntos()+" puntos.");//Sacamos por la etiqueta los puntos que lleva acumulados player1
									//ButtonMathdice.setEnabled(false);//Deshabilitamos botón mathdice
									//btnReset.setEnabled(true);//Habilitamos botón reset
									player1.setSeguidos(0);
									LabelAciertosSeguidos.setText("");
								}else{
									LabelResultado.setText("Sigue buscando");
									btnReset.setEnabled(true);//Habilitamos botón reset
									ButtonMathdice.setEnabled(false);//Deshabilitamos botón mathdice
									player1.setSeguidos(0);
									LabelAciertosSeguidos.setText("");
								}
							} catch (ScriptException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								LabelResultado.setText("Error al introducir datos");
								
							}finally{
								btnReset.setEnabled(true);//Habilitamos botón reset
								ButtonMathdice.setEnabled(false);//Deshabilitamos botón mathdice
							}
					}
					});
		ButtonMathdice.setFont(new Font("Modern No. 20", Font.PLAIN, 20));
		ButtonMathdice.setBounds(535, 329, 411, 57);
		add(ButtonMathdice);
		
		
		//Botón para volver a jugar
		btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Reseteamos todos los valores que intervienen en la ventana como al inicio
				valor12_1 = (int) (Math.round(Math.random() *(1-12)+12));//Vuelve a generar un valor para el dado12
				valor12_2 = (int) (Math.round(Math.random() *(1-12)+12));//Vuelve a generar un valor para el dado12_2
				AsignarValor();//Se generan valores para los dados de tres y seis caras
				AsignarImagen();//Se sacan las respectivas imágenes en las etiquetas
				sacarObjetivo();
				nSimbolos=0;//Se resetean semáforos
				tocaDado=0;
				operacion="";//Se resetea la operación
				JTextOperacion.setText(operacion);
				//LabelDado_3_1.setEnabled(true);
				//LabelDado_3_2.setEnabled(true);
				//LabelDado_3_3.setEnabled(true);
				//LabelDado_6_1.setEnabled(true);
				//LabelDado_6_2.setEnabled(true);
				//LabelDado_6_3.setEnabled(true);
				LabelDado_3_1.addMouseListener(new miBotonDado());
				LabelDado_3_2.addMouseListener(new miBotonDado());
				LabelDado_3_3.addMouseListener(new miBotonDado());
				LabelDado_6_1.addMouseListener(new miBotonDado());
				LabelDado_6_2.addMouseListener(new miBotonDado());
				LabelDado_6_3.addMouseListener(new miBotonDado());
				btnReset.setEnabled(false);//Se dejan los botones como al principio
				ButtonMathdice.setEnabled(true);
				LabelResultado.setText("");
				
			}
		});
		btnReset.setEnabled(false);
		btnReset.setFont(new Font("Modern No. 20", Font.PLAIN, 20));
		btnReset.setBounds(535, 407, 411, 57);
		add(btnReset);
		
		//Etiqueta para sacar resultado obtenido
		LabelResultado = new JLabel("");
		LabelResultado.setHorizontalAlignment(SwingConstants.CENTER);
		LabelResultado.setFont(new Font("Modern No. 20", Font.PLAIN, 22));
		LabelResultado.setBounds(593, 479, 301, 40);
		add(LabelResultado);
		
		LabelAciertosSeguidos = new JLabel("");
		LabelAciertosSeguidos.setHorizontalAlignment(SwingConstants.CENTER);
		LabelAciertosSeguidos.setFont(new Font("Modern No. 20", Font.PLAIN, 20));
		LabelAciertosSeguidos.setBounds(535, 579, 410, 40);
		add(LabelAciertosSeguidos);
		
		//Instancia de la clase Instrucciones para crear ventana de instrucciones
		vInsE=new InstruccionesExperto ();
		
		//Etiqueta acceso a ventana instrucciones
		LabelInterrogante = new JLabel("New label");
		LabelInterrogante.setBounds(0, 0, 58, 57);
		add(LabelInterrogante);
		LabelInterrogante.setIcon(new ImageIcon(JuegoPrincipiante.class.getResource("/Imagenes/interrogante.png")));
		//Listener para acceder a la ventana de instrucciones del modo experto (vInsE)
		LabelInterrogante.addMouseListener(
				new MouseAdapter(){
					@Override
				public void mousePressed(MouseEvent arg0){
						vInsE.setVisible(true);
					}
				}
		);
		//Ejecución del método InicarArrays
		AsignarValor();
		
		//Ejecución del método SacarImagen
		AsignarImagen();
		
		//Ejecución del método sacarObjetivo
		sacarObjetivo();

		
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
			
			//MouseListener que vamos a utilizar
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (tocaDado==0){
					JLabel b=(JLabel)arg0.getSource();
					JTextOperacion.setText(operacion=operacion+(b.getName()));
					b.setIcon(dado_gris);
					tocaDado=1;
					//b.setEnabled(false);
					b.removeMouseListener(this);
				}
			}	
		}
	//Setter de puntos y nombres del objeto player1 de la clase Jugador
	public void setJugador (Jugador player1) {
			this.player1=player1;
			LabelBienvenidaExp.setText("Hola "+player1.getNombre()+" bienvenid@ a Math Dice.");
				
	}


	//Método para multiplicar los valores de los dos dados de doce caras y asignar objetivo
	public void sacarObjetivo(){
		objetivo=valor12_1*valor12_2;
		LabelObjetivo.setText("Objetivo = "+objetivo);
		
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
		LabelDado_6_3.setName(String.valueOf(valor_6caras[2]));
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
        
        //Tercer dado de seis caras
        if (valor_6caras[2] == 1) {
            dado6_3 = new ImageIcon(getClass().getResource("/Imagenes/dado1_6.png"));
        } else if (valor_6caras[2] == 2) {
        	dado6_3 = new ImageIcon(getClass().getResource("/Imagenes/dado2_6.png"));
        } else if (valor_6caras[2] == 3) {
        	dado6_3 = new ImageIcon(getClass().getResource("/Imagenes/dado3_6.png"));
        } else if (valor_6caras[2] == 4) {
        	dado6_3 = new ImageIcon(getClass().getResource("/Imagenes/dado4_6.png"));
        } else if (valor_6caras[2] == 5) {
        	dado6_3 = new ImageIcon(getClass().getResource("/Imagenes/dado5_6.png"));
        } else if (valor_6caras[2] == 6) {
        	dado6_3 = new ImageIcon(getClass().getResource("/Imagenes/dado6_6.png"));
        } 
        LabelDado_6_3.setIcon(dado6_3);
        
        //Dado de doce caras
        if (valor12_1 == 1) {
            dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_1.png"));
        } else if (valor12_1 == 2) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_2.png"));
        } else if (valor12_1 == 3) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_3.png"));
        } else if (valor12_1 == 4) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_4.png"));
        } else if (valor12_1 == 5) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_5.png"));
        } else if (valor12_1 == 6) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_6.png"));
        } else if (valor12_1 == 7){
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_7.png"));
        } else if (valor12_1 == 8) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_8.png"));
        } else if (valor12_1 == 9) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_9.png"));
        } else if (valor12_1 == 10) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_10.png"));
        } else if (valor12_1 == 11) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_11.png"));
        } else if (valor12_1 == 12) {
        	dado12 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_12.png"));
        } 
        LabelDado_12_1.setIcon(dado12);
        
        //Dado de doce caras
        if (valor12_2 == 1) {
            dado12_2 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_1.png"));
        } else if (valor12_2 == 2) {
        	dado12_2 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_2.png"));
        } else if (valor12_2 == 3) {
        	dado12_2 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_3.png"));
        } else if (valor12_2 == 4) {
        	dado12_2 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_4.png"));
        } else if (valor12_2 == 5) {
        	dado12_2 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_5.png"));
        } else if (valor12_2 == 6) {
        	dado12_2 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_6.png"));
        } else if (valor12_2 == 7){
        	dado12_2 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_7.png"));
        } else if (valor12_2 == 8) {
        	dado12_2 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_8.png"));
        } else if (valor12_2 == 9) {
        	dado12_2 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_9.png"));
        } else if (valor12_2 == 10) {
        	dado12_2 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_10.png"));
        } else if (valor12_2 == 11) {
        	dado12_2 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_11.png"));
        } else if (valor12_2 == 12) {
        	dado12_2 = new ImageIcon(getClass().getResource("/Imagenes/dadodoce_12.png"));
        } 
        LabelDado_12_2.setIcon(dado12_2);
	}
}
