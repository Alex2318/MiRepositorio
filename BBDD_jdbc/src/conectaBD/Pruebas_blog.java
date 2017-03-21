package conectaBD;
import java.sql.*;


public class Pruebas_blog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			
			//1.CREAR CONEXI�N.
			
			Connection miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "");
			
			System.out.println("Conectado con �xito");
			
			//2.CREAR OBJETO STATEMENT
			
			Statement miStatement=miConexion.createStatement();
			
			System.out.println("Creado Statement");

			//3.EJECUTAR SQL
			
			ResultSet miResultSet=miStatement.executeQuery("SELECT * FROM productos");
			
			System.out.println("Paso 3");
			
			//4.RECORRER RESULTSET
			//EN EL miResultset.getString(x), x PUEDE SER EL NOMBRE DEL CAMPO O EL NUMERO DE COLUMNA, EN ESTE CASO SER�AN 3, 0 Y 4 RESPECTIVAMENTE
			while (miResultSet.next()){
				System.out.println(miResultSet.getString("NOMBREART�CULO")+" "+miResultSet.getString("C�DIGOART�CULO")+" "+miResultSet.getString("PRECIO"));
				
			}

			miConexion.close();
			System.out.println("Conexi�n cerrada");
		}catch(Exception e){
			
		}

	}

}
