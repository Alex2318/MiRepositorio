
class Ejercicio1 implements Runnable  {
	
	/*Clase Ejercicio1 que implementa la interfaz Runnable.
	 * Al constructor se le pasa un string, se crea un hilo de la clase Thread, posteriormente se le pone un nombre con el
	 * método .setName, dándole el nombre del String que le hemos pasado por parámetro y finalmente se inicializa con 
	 * el método .start() invocando al run()
	 * */
	Ejercicio1(String nombre)  {
		
		Thread t = new Thread(this);
		t.setName(nombre); 
		t.start();
    }
	
	/*En el run hacemos lo mismo que en el caso anterior, 10 iteraciones sacando por pantalla el nombre con una dilación de 500
	 * milisegundos
	 */
	public void run() {
		for (int i=0; i<10;i++){
			System.out.println("soy el hilo "+Thread.currentThread().getName()+" y estoy en la iteración "+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//Mensaje para saber cuando termina cada hilo
		System.out.println("Finaliza el "+Thread.currentThread().getName());
}
    	
   /*En el main simplemente instanciamos dos variable de la clase Runnable con su correspondiente llamada al constructor*/
    public static void main (String args [])  {
		//Hacemos este System.out.print para que antes del start escriba por pantalla "Bienvenidos", concatenado con el primer mensaje
		System.out.print("Bienvenido, ");
		
		//Instancia del primer hilo
    	Runnable hilo = new Ejercicio1("hilo1");
    	
    	//Hacemos este pequeño sleep para asegurarnos de que se intercalan los hilos
    			try {
    				Thread.sleep(50);
    			} catch (InterruptedException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    			
    	//Instancia del segundo hilo
    	Runnable hilo2 = new Ejercicio1("hilo2");
    	
		//Dormimos al hilo principal durante el tiempo que dura el run
		try  {
			Thread.sleep(10000);
		}  catch (InterruptedException e)  {
		   	System.out.println("Interrumpido");
		}
		System.out.println("main terminado");
    	
    }
    
}

