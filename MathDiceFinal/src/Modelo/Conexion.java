package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	// DATOS DE LA CONEXION
		static final String CONTROLADOR_MYSQL= "com.mysql.jdbc.Driver";
		
		//DATOS DE LA BBDD
		private String host; //host donde est� la base de datos
		private String bbdd; //nombre de la base de datos
		private String user; //nombre usuario para acceder base de datos
		private String pass; //password de usuario
		private String url;  //proporcionar� al m�todo DriverManager la direcci�n de conexi�n
		                     //en la forma adeduaca
		
		//Conexion
		private Connection conexion = null;
		
		private static Conexion instance = null;// maneja la conexi�n
		
		
		//Constructor (le pasaremos los datos necesarios para la conexi�n)
		/*    1. PARA IMPLEMENTAR EL PATR�N SINGLETON LO PRIMERO QUE HACEMOS ES PASAR A PRIVATE 
		 * EL CONSTRUCTOR DE LA CLASE CONEXION*/
		private Conexion(String HOST,String BBDD,String USER,String PASS) {
			this.host=HOST;
			this.bbdd=BBDD;
			this.user=USER;
			this.pass=PASS;
			this.url="jdbc:mysql://"+this.host+"/"+this.bbdd;
		}
		
		/*SIGUIENDO CON EL SINGLETON CREAMOS UN M�TODO GETTER STATIC PARA QUE EN CASO DE QUE NO HAYA 
		 *NINGUNA INSTANCIA DE LA CLASE CONEXION (INSTANCE=NULL), CREE UNA NUEVA.
		 *DE ESTA FORMA NOS ASEGURAMOS DE QUE NO VA A HABER M�S DE UNA CONEXI�N ABIERTA.
		 */
		public static Conexion getInstance(String HOST,String BBDD,String USER,String PASS){
			if (instance==null){
				instance=  new Conexion(HOST,BBDD,USER,PASS);
			}else{
				System.out.println("Ya existe una conexi�n");
			}
			return instance;
		}
		
		//M�todo para conectarse con la BBDD: true si ha conseguido conectarse
		public boolean connectDB(){
			try{
				//Lo primero es cargar el controlador MySQL el cual autom�ticamente se registra
				Class.forName(CONTROLADOR_MYSQL);
				//Conectarnos a la BBDD
				conexion = DriverManager.getConnection(this.url,this.user,this.pass);
			}
			catch( SQLException excepcionSql ) //No encuentra la Base de Datos
			{
				excepcionSql.printStackTrace();
				return false;
	        }
			catch( ClassNotFoundException noEncontroClase) //No encuentra el driver para la conexi�n
			{
				noEncontroClase.printStackTrace();
				return false;
			}
			return true;
		}
		
		public Connection getConexion(){
			return this.conexion;
		}

}
