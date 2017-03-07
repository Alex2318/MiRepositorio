package Juego;

public class Jugador {
	
	//Atributos de la clase jugador
	private String nombre;
	private String apellidos;
	private int puntos;
	private int edad;
	private int id;
	//Variable que acumula el número de aciertos seguidos.
	private int aciertosSeguidos;
	
	//Getters y setters de los atributos de la clase
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getSeguidos() {
		return aciertosSeguidos;
	}

	public void setSeguidos(int aciertosSeguidos) {
		this.aciertosSeguidos = aciertosSeguidos;
	}
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Jugador() {
		// TODO Auto-generated constructor stub
	}
	

	//Métodos de comprobación
	
	//Método para comprobar que lo que se ingresa en Edad es un número
	public boolean isNumeric(String cadena){//Si hay una cadena de caracteres...
		try{
			Integer.parseInt(cadena);//...lo convierte a un int...
			return true;//...y te devuelve true
		}catch (NumberFormatException nfe){//Si hay una excepción...
			return false;//...te devuelve false.
		}
	}
	
	//Método para comprobar que no se esté dejando un espacio en blanco.
	public boolean enBlanco(String cad)
	{
	for(int x=0; x<cad.length(); x++)//Bucle para revisar caracteres...
	if(cad.charAt(x) !=' ')//si hay un carater por lo menos que sea diferente a un espacio en blanco...
		return false;//...devuelve un false...
							//...en caso contrario...
		return true;//...devuelve un true.
	
	}

	
}
