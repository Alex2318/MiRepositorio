package gestionficherosapp;

import gestionficheros.MainGUI;	//Importamos paquete

public class GestionFicherosApp {

	public static void main(String[] args) {
		GestionFicherosImpl getFicherosImpl = new GestionFicherosImpl();	//Creamos instancia de GestionFicherosImpl
		new MainGUI(getFicherosImpl).setVisible(true);	//Nueva interfaz gráfica

	}

}
