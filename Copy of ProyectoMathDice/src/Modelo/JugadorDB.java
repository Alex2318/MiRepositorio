package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JugadorDB {
	//Campos de la tabla usuario
		private int id;
		private String Nombre;
		private String Apellidos;
		private String Edad;
		private String Puntos;
		private Jugador player1=new Jugador();
		
		//Conexion a la base de datos
		private Connection conexion;
		//Objeto para ejecutar una orden sobre la base de datos
		private Statement orden = null;
		
		//Constructor: recoge la conexión establecida para la base de datos
		public JugadorDB(Connection c) {
			this.conexion=c;
		}
		
		//Método que permite insertar un usuario en la base de datos
		//La BBDD se llama usuarios, y la tabla correspondiente usuario
		public void insertarUsuario(String Nombre,String Apellidos,int Edad){

			try{
				orden = conexion.createStatement();
			    String sql = "INSERT INTO jugador (Nombre,Apellidos,Edad) " +
			                   "VALUES ('"+Nombre+"', '"+Apellidos+"', "+Edad+")";
			    orden.executeUpdate(sql);
			    System.out.println("Jugador registrado con exito");


			   }catch(SQLException se){
				      //Se produce un error con la consulta
				      se.printStackTrace();
			   }catch(Exception e){
				      //Se produce cualquier otro error
				      e.printStackTrace();
			   }finally{
				      //Cerramos los recursos
				      try{
				         if(orden!=null)
				        	 conexion.close();
				      }catch(SQLException se){
				    	  se.printStackTrace();
				      }
				      try{
				         if(conexion!=null)
				        	 conexion.close();
				      	 }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
				}//end try
		}
		
		/*
		 * MÉTODO GETTER PARA QUE NOS DEVUELVA UN VALOR QUE SERÁ A LA POSTRE LA ÚLTIMA ID INTRODUCIDA EN NUESTRA BBDD
		 * PARA RESCATAR DICHA ID LO HAREMOS A TRAVÉS DE UNA SENTENCIA SELECT.
		 * A DIFERENCIA DEL ANTERIOR HE UTILIZADO ESTE MÉTODO DE ACCESO A LA BBDD EN EL CUAL EN ESCASAS LÍNEAS HE CREADO UNA
		 * CLASE PARA LA CÓNEXIÓN, UN STATEMENT Y HE EJECUTADO UNA SENTENCIA CON SU CORRESPONDIENTE RESULTSET AL TRATARSE
		 * DE UNA CONSULTA.
		 */
		public int devolverID() {
			try{
				
				//1.CREAR CONEXIÓN.
				Connection miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/jugadores", "root", "");
				System.out.println("Conectado con éxito");
				
				//2.CREAR OBJETO STATEMENT
				Statement miStatement=miConexion.createStatement();
				System.out.println("Creado Statement");

				//3.EJECUTAR SQL
				ResultSet miResultSet=miStatement.executeQuery("SELECT MAX(id) FROM jugador");
				System.out.println("Paso 3");

				//4.RECORRER RESULTSET
				//EN EL miResultset.getString(x), x PUEDE SER EL NOMBRE DEL CAMPO O EL NUMERO DE COLUMNA, EN ESTE CASO SERÍAN 3, 0 Y 4 RESPECTIVAMENTE
				while (miResultSet.next()){
					System.out.println(miResultSet.getInt(1));
					id = miResultSet.getInt(1);
				}

				
			}catch(Exception e){
				System.out.println("NO CONECTAAA!!!");
				e.printStackTrace();
			}
			return id;
		}
}
		
