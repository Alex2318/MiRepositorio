package conectaBD;
import java.sql.*;

public class Conecta_Pruebas1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try{
	
			//1.CREAR CONEXIÓN.
			
			Connection miConexion=DriverManager.getConnection("jdbc:mysql://54.149.76.139:3306/pruebas", "paco", "paco");
			
			System.out.println("Conectado con éxito");
			
			//2.CREAR OBJETO STATEMENT
			
			Statement miStatement=miConexion.createStatement();
			
			System.out.println("Creado Statement");

			
			//3.EJECUTAR SQL
			
			ResultSet miResultSet=miStatement.executeQuery("SELECT * FROM productos");
			
			System.out.println("Paso 3");

			//4.RECORRER RESULTSET
			//EN EL miResultset.getString(x), x PUEDE SER EL NOMBRE DEL CAMPO O EL NUMERO DE COLUMNA, EN ESTE CASO SERÍAN 3, 0 Y 4 RESPECTIVAMENTE
			while (miResultSet.next()){
				System.out.println(miResultSet.getString("NOMBREARTÍCULO")+" "+miResultSet.getString("CÓDIGOARTÍCULO")+" "+miResultSet.getString("PRECIO"));
				
			}
			
		}catch(Exception e){
			
			System.out.println("NO CONECTAAA!!!");
			
			e.printStackTrace();
		}
		
		
		
	}

}
