package Principal;

import Vista.Instrucciones;
import Vista.JuegoExperto;
import Vista.JuegoPrincipiante;
import Vista.Login1;
import Vista.Login;
import Vista.VentanaPrincipal;

public class Principal {

	public static void main(String[] args) {
		
	

		//Ventanas a utilizar (hacemos visible vLogin para que sea la primera ventana que sale al ejecutar la aplicación)

	    //JuegoPrincipiante vJuego=new JuegoPrincipiante();
		
		//JuegoExperto vExperto=new JuegoExperto();
		
		VentanaPrincipal vPrincipal=new VentanaPrincipal();
		Login login=new Login(vPrincipal);

		Login1 vLogin=new Login1(vPrincipal, login);
		vLogin.setVisible(true);
		

		


	}

}
