package Principal;

import Vista.Instrucciones;
import Vista.JuegoExperto;
import Vista.JuegoPrincipiante;
import Vista.Login;
import Vista.VentanaPrincipal;

public class Principal {

	public static void main(String[] args) {
		
	

		//Ventanas a utilizar (hacemos visible vLogin para que sea la primera ventana que sale al ejecutar la aplicación)

	    //JuegoPrincipiante vJuego=new JuegoPrincipiante();
		
		//JuegoExperto vExperto=new JuegoExperto();
		
		VentanaPrincipal vPrincipal=new VentanaPrincipal();

		Login vLogin=new Login(vPrincipal);
		vLogin.setVisible(true);
		

		


	}

}
