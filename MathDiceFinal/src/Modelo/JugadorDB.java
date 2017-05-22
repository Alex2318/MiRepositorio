package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Vista.SignUp;

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
		
		//Constructor: recoge la conexi�n establecida para la base de datos
		public JugadorDB(Connection c) {
			this.conexion=c;
		}
		
		//M�todo que permite insertar un usuario en la base de datos
		//La BBDD se llama usuarios, y la tabla correspondiente usuario
		public void insertarUsuario(String Nombre,String Apellidos,String User,String Password,int Edad){

			try{
				orden = conexion.createStatement();
			    String sql = "INSERT INTO jugador (Nombre,Apellidos,User,Password,Edad) " +
			                   "VALUES ('"+Nombre+"', '"+Apellidos+"', '"+User+"', '"+Password+"', "+Edad+")";
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
				        	 orden.close();
				         	System.out.println("Cerrado el statement");
				      }catch(SQLException se){
				    	  se.printStackTrace();
				      }
				      try{
				         if(conexion!=null)
				        	 conexion.close();
				         	System.out.println("Cerrada la conexi�n");

				      	 }catch(SQLException se){
				         se.printStackTrace();
				      }
				      //end finally try
				}//end try
		}
		
		//M�todo que permite buscar un usuario en la base de datos

		public Jugador buscarJugador(String user, String password){
					
			Jugador j=new Jugador();
			try{
				orden = conexion.createStatement();
			    String sql = "SELECT id,nombre, apellidos, edad, user, puntos, aciertos_seguidos_m�ximo FROM jugador WHERE user='"+user+"' AND password='"+password+"'";
				rs = orden.executeQuery(sql);
				      
				//Cogemos los usuarios y recorremos la BBDD mientras haya datos
				while(rs.next()){
		    	  j.setId(rs.getInt("id"));
			      j.setNombre(rs.getString("nombre"));
			      j.setApellidos(rs.getString("apellidos"));
			      j.setEdad(rs.getInt("edad"));
			      j.setUser(rs.getString("user"));
			      j.setPuntos(rs.getInt("puntos"));
			      j.setRecordAciertos(rs.getInt("aciertos_seguidos_m�ximo"));
			      //Cogera todos los usuarios que coincidan en nombre, pero solo devolver� el �ltimo encontrado
			      //porqu� va reescribiendo u
			      System.out.println(j.getId()+" "+j.getNombre()+" "+j.getApellidos()+" "+j.getEdad()+" "+j.getUser()+"\n");
			      }
				
				}catch(Exception e){//EN EL CASO DE QUE NO CONECTE...
					System.out.println("NO CONECTAAA!!!");//...DEVUELVE MENSAJE POR CONSOLA
					e.printStackTrace();
					
				}finally{
			      //Cerramos los recursos
			      try{
			         if(orden!=null)
			        	 orden.close();
			      }catch(SQLException se){
			    	  se.printStackTrace();
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
			      return j;
				}
		
				//M�TODO PARA BUSCAR EN LA BBDD LOS DIEZ MEJORES RESULTADOS
		public Jugador[] buscarDiez(){
					
			Jugador[] diez=new Jugador[11];
			try{
			  orden = conexion.createStatement();
		      String sql = "SELECT user, puntos FROM jugador ORDER BY puntos DESC LIMIT 11";
		      rs = orden.executeQuery(sql);
		      
		      int i=0;
		      
		      while(rs.next()){
		    	  
			      diez[i]= new Jugador();
			      diez[i].setUser(rs.getString("user"));
			      diez[i].setPuntos(rs.getInt("puntos"));

			      i++;
		      } 
		 

			}catch(Exception e){//EN EL CASO DE QUE NO CONECTE...
					System.out.println("NO CONECTAAA!!!");//...DEVUELVE MENSAJE POR CONSOLA
					e.printStackTrace();
				
			}finally{
			      //Cerramos los recursos
			      try{
			         if(orden!=null)
			        	 orden.close();
			      }catch(SQLException se){
			    	  se.printStackTrace();
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
		      return diez;
		}
				      
				     			    
	/*M�TODO PARA INSERTAR PUNTOS EN LA BBDD EN BASE A LO QUE VA CONSIGUIENDO EN EL JUEGO*/
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
			         	System.out.println("Cerrada la conexi�n");

			      	 }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
				}//end try
		}
		
		/*M�TODO PARA INSERTAR RECORD DE ACIERTOS SEGUIDOS EN LA BBDD EN BASE A LO QUE VA CONSIGUIENDO EN EL JUEGO*/
		public void insertarSeguidos(Jugador player1 ){
			try{
				orden = conexion.createStatement();
			    String sql = "UPDATE jugador SET aciertos_seguidos_m�ximo = "+player1.getRecordAciertos()+" WHERE id= "+player1.getId()+"";
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
				         	System.out.println("Cerrada la conexi�n");

				      	 }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
				}//end try
		}
		
		/*M�TODO QUE UTILIZAREMOS EN LA VENTANA PERFIL PARA ACTUALIZAR LOS DATOS DEL JUGADOR*/
		public void actualizarJugador(Jugador player1){
			try{
			  orden = conexion.createStatement();
			// create the java mysql update preparedstatement
			// Creamos la sentencia "tipo" que queremos ejecutar
		      String sql = "UPDATE jugador " +
	                       "SET nombre = ?"+
	                       ",apellidos = ?"+
	                       ",puntos = ? "+
	                       ",user = ? "+
	                       ",edad = ? "+
		    		       "WHERE id = "+player1.getId();
		      PreparedStatement preparedStmt = conexion.prepareStatement(sql);
		      
		      //Asignamos valores concretos a ?
		      preparedStmt.setString(1, player1.getNombre());
		      preparedStmt.setString(2, player1.getApellidos());
		      preparedStmt.setInt(3, player1.getPuntos());
		      preparedStmt.setString(4, player1.getUser());
		      preparedStmt.setInt(5, player1.getEdad());
		 
		      // Se ejecuta la consulta y el update
		      
		      preparedStmt.executeUpdate();
		      
		      //Comprobaci�n por monitor
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
			        	 orden.close();
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
		 * M�TODO GETTER PARA QUE NOS DEVUELVA UN VALOR QUE SER� A LA POSTRE LA �LTIMA ID INTRODUCIDA EN NUESTRA BBDD
		 * PARA RESCATAR DICHA ID LO HAREMOS A TRAV�S DE UNA SENTENCIA SELECT.
		 * APROVECHAMOS EL OBJETO DE LA CLASE CONEXI�N (conexion) Y EL OBJETO DE LA CLASE STATEMENT (orden) ANTERIORES.
		 */
		public int devolverID() {
			try{
				  orden = conexion.createStatement();
				//EJECUTAR SQL
				/*YA QUE LA �LTIMA ID INTRODICIDA ES LA M�S ALTA (EST� CON AUTOINCREMENTADO) Y LO QUE QUEREMOS ES LA �LTIMA
				 * ID, LE PEDIMOS LA ORDEN CON SELECT CON LA ESPECIFICACI�N DE MAX(id)
				 */
				String sql= "SELECT MAX(id) FROM jugador";
				rs = orden.executeQuery(sql);

				//RECORRER RESULTSET
				/*EN EL miResultset.getString(x), x PUEDE SER EL NOMBRE DEL CAMPO O EL NUMERO DE COLUMNA,
				EN ESTE CASO SER�AN 3, 0 Y 4 RESPECTIVAMENTE*/
				while (rs.next()){
					System.out.println(rs.getInt(1));
					id = rs.getInt(1);
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
		         	System.out.println("Cerrada la conexi�n");

		      	 }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		      try{
			         if(rs!=null)
			        	 rs.close();
			         	System.out.println("Cerrada el rs");

			      	 }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
		}//end try
			return id;//FINALMENTE DEVUELVE EL INT QUE LE PEDIMOS AL M�TODO, QUE NO ES M�S QUE EL ID RESCATADO
		}
}
		
