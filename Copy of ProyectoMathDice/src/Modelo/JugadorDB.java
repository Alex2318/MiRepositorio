package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Vista.Login;

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
		private ResultSet rs;
		
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
		//Método que permite buscar un usuario en la base de datos
				public Jugador buscarJugador(String user){
					
					Jugador j=new Jugador();
					try{
					  orden = conexion.createStatement();
				      String sql = "SELECT id,nombre, apellidos, edad, user, puntos FROM jugador WHERE user='"+user+"'";
				      rs = orden.executeQuery(sql);
				      
				      //Cogemos los usuarios y recorremos la BBDD mientras haya datos
				      while(rs.next()){
				    	  j.setId(rs.getInt("id"));
					      j.setNombre(rs.getString("nombre"));
					      j.setApellidos(rs.getString("apellidos"));
					      j.setEdad(rs.getInt("edad"));
					      j.setUser(rs.getString("user"));
					      j.setPuntos(rs.getInt("puntos"));
					      //Cogera todos los usuarios que coincidan en nombre, pero solo devolverá el último encontrado
					      //porqué va reescribiendo u
					      System.out.println(j.getId()+" "+j.getNombre()+" "+j.getApellidos()+" "+j.getEdad()+" "+j.getUser()+"\n");
					      
				      }
				      
				      
				      
				      return j;
					}catch(SQLException se){
						      //Se produce un error con la consulta
						      se.printStackTrace();
						      return j;
					}catch(Exception e){
						      //Se produce cualquier otro error
						      e.printStackTrace();
						      return j;
					}finally{
					      //Cerramos los recursos
					      try{
					         if(orden!=null)
					        	 conexion.close();
					      }catch(SQLException se){
					      }
					      try{
					         if(conexion!=null)
					        	 conexion.close();
					      	 }catch(SQLException se){
					         se.printStackTrace();
					      	 }
					      try{
						         if(rs!=null)
						        	 rs.close();
						      	 }catch(SQLException se){
						         se.printStackTrace();
						      	 }//end finally try
					}
					}
			
				      
				     
				      //Debemos cerrar la conexion
				    
	
		public void insertarPuntos(Jugador player1 ){
			
			try{
				orden = conexion.createStatement();
			    String sql = "UPDATE jugador SET puntos = "+player1.getPuntos()+" WHERE id= "+player1.getId()+"";
			    orden.executeUpdate(sql);
			    System.out.println("Actualizado: "+player1.toString()+"\n");

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
		}
		
		public void actualizarJugador(Jugador player1){
			try{
			  orden = conexion.createStatement();
			// create the java mysql update preparedstatement
			// Creamos la sentencia "tipo" que queremos ejecutar
		      String sql = "UPDATE jugador " +
	                       "SET nombre = ?"+
	                       ",apellidos = ?"+
	                       ",puntos = ? "+
	                       ",edad = ? "+
		    		       "WHERE id = "+player1.getId();
		      PreparedStatement preparedStmt = conexion.prepareStatement(sql);
		      
		      //Asignamos valores concretos a ?
		      preparedStmt.setString(1, player1.getNombre());
		      preparedStmt.setString(2, player1.getApellidos());
		      preparedStmt.setInt(3, player1.getPuntos());
		      preparedStmt.setInt(4, player1.getEdad());
		 
		      // Se ejecuta la consulta y el update
		      
		      preparedStmt.executeUpdate();
		      
		      //Comprobación por monitor
		      System.out.println(sql+"\n");
		      System.out.println("Actualizado: "+player1.toString()+"\n");
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
			      }
			      try{
			         if(conexion!=null)
			        	 conexion.close();
			      	 }catch(SQLException se){
			         se.printStackTrace();
			      	 }//end finally try
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
		
