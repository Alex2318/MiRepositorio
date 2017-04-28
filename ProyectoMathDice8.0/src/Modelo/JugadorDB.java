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
		private String User;
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
		public void insertarUsuario(String Nombre,String Apellidos,String User,int Edad){

			try{
				orden = conexion.createStatement();
			    String sql = "INSERT INTO jugador (Nombre,Apellidos,User,Edad) " +
			                   "VALUES ('"+Nombre+"', '"+Apellidos+"', '"+User+"', "+Edad+")";
			    orden.executeUpdate(sql);
			    System.out.println("Jugador registrado con exito");


			   }catch(SQLException se){
				      //Se produce un error con la consulta
				      se.printStackTrace();
			   }catch(Exception e){
				      //Se produce cualquier otro error
				      e.printStackTrace();
			   }
		}
	
		/*
		 * MÉTODO GETTER PARA QUE NOS DEVUELVA UN VALOR QUE SERÁ A LA POSTRE LA ÚLTIMA ID INTRODUCIDA EN NUESTRA BBDD
		 * PARA RESCATAR DICHA ID LO HAREMOS A TRAVÉS DE UNA SENTENCIA SELECT.
		 * APROVECHAMOS EL OBJETO DE LA CLASE CONEXIÓN (conexion) Y EL OBJETO DE LA CLASE STATEMENT (orden) ANTERIORES.
		 */
		public int devolverID() {
			try{
				
				//EJECUTAR SQL
				/*YA QUE LA ÚLTIMA ID INTRODICIDA ES LA MÁS ALTA (ESTÁ CON AUTOINCREMENTADO) Y LO QUE QUEREMOS ES LA ÚLTIMA
				 * ID, LE PEDIMOS LA ORDEN CON SELECT CON LA ESPECIFICACIÓN DE MAX(id)
				 */
				ResultSet miResultSet=orden.executeQuery("SELECT MAX(id) FROM jugador");
				System.out.println("Paso 3");

				//RECORRER RESULTSET
				/*EN EL miResultset.getString(x), x PUEDE SER EL NOMBRE DEL CAMPO O EL NUMERO DE COLUMNA,
				EN ESTE CASO SERÍAN 3, 0 Y 4 RESPECTIVAMENTE*/
				while (miResultSet.next()){
					System.out.println(miResultSet.getInt(1));
					id = miResultSet.getInt(1);
				}	
			}catch(Exception e){//EN EL CASO DE QUE NO CONECTE...
				System.out.println("NO CONECTAAA!!!");//...DEVUELVE MENSAJE POR CONSOLA
				e.printStackTrace();
			}finally{
		      //Cerramos los recursos
		      try{
		         if(orden!=null)
		        	 orden.close();
		         	System.out.println("Cerrado el statement");
		      }catch(SQLException se){
		    	  se.printStackTrace();
		      }
		      try{
		         if(conexion!=null)
		        	 conexion.close();
		         	System.out.println("Cerrada la conexión");

		      	 }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		}//end try
			return id;//FINALMENTE DEVUELVE EL INT QUE LE PEDIMOS AL MÉTODO, QUE NO ES MÁS QUE EL ID RESCATADO
		}
}
		
