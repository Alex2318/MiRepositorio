package gestionficherosapp;

//Importamos paquetes
import java.io.File;
import java.sql.Date;


import gestionficheros.FormatoVistas;
import gestionficheros.GestionFicheros;
import gestionficheros.GestionFicherosException;
import gestionficheros.TipoOrden;

	/*Clase implementa GestionFicheros que est� en las librer�as importadas*/
public class GestionFicherosImpl implements GestionFicheros {
	private File carpetaDeTrabajo = null;	//Declaramos objeto de tipo File
	private Object[][] contenido;	//Definimos variable global que ser� una matriz de objetos para guardar el contenido de las carpetas.
	private int filas = 0;
	private int columnas = 3;
	private FormatoVistas formatoVistas = FormatoVistas.NOMBRES;
	private TipoOrden ordenado = TipoOrden.DESORDENADO;

	public GestionFicherosImpl() {
		carpetaDeTrabajo = File.listRoots()[0];	//lamada al m�todo qued�ndonos con el primer elemento
		actualiza();	//M�todo para gestionar los cambios
	}

	private void actualiza() {

		String[] ficheros = carpetaDeTrabajo.list(); // obtener los nombres
		// calcular el n�mero de filas necesario
		filas = ficheros.length / columnas;
		if (filas * columnas < ficheros.length) {
			filas++; // si hay resto necesitamos una fila m�s
		}

		// dimensionar la matriz contenido seg�n los resultados

		contenido = new String[filas][columnas];
		// Rellenar contenido con los nombres obtenidos
		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				int ind = j * columnas + i;
				if (ind < ficheros.length) {
					contenido[j][i] = ficheros[ind];
				} else {
					contenido[j][i] = "";
				}
			}
		}
	}

//Se crea autim�ticamente (con el add unimplemental methods) todos los m�todos
	@Override
	public void arriba() {

		System.out.println("holaaa");
		if (carpetaDeTrabajo.getParentFile() != null) {
			carpetaDeTrabajo = carpetaDeTrabajo.getParentFile();
			actualiza();
		}

	}

	@Override
	public void creaCarpeta(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		//que se pueda escribir -> lanzar� una excepci�n
		if (!carpetaDeTrabajo.canWrite()) {
			throw new GestionFicherosException("Alerta. No se tienen permisos de escritura en "
					+ carpetaDeTrabajo.getAbsolutePath() + ".");
		}	
		
		//que no exista -> lanzar� una excepci�n
		if (file.exists()) {
			throw new GestionFicherosException("El nombre de la carpeta ya existe");
		}
		
		//crear la carpeta -> lanzar� una excepci�n
		try{
			file.mkdirs();
		}catch (Exception e){
			System.err.println("Ha habido un error en la creaci�n de la carpeta "+file.getName()+".");
		}finally{
			
		}
		actualiza();
	}

	@Override
	public void creaFichero(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		//que se pueda escribir -> lanzar� una excepci�n
		if (!carpetaDeTrabajo.canWrite()) {
			throw new GestionFicherosException("Alerta. No se tienen permisos de escritura en "
					+ carpetaDeTrabajo.getAbsolutePath() + ".");
		}	
		
		//que no exista -> lanzar� una excepci�n
		if (file.exists()) {
			throw new GestionFicherosException("El nombre del fichero ya existe");
		}
		
		//crear el fichero -> lanzar� una excepci�n
		try{
			file.createNewFile();
		}catch (Exception e){
			System.err.println("Ha habido un error en la creaci�n del fichero "+file.getName()+".");
		}finally{
			
		}
		actualiza();
	}
	

	@Override
	public void elimina(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		//que se pueda escribir -> lanzar� una excepci�n
		if (!file.canWrite()) {
			throw new GestionFicherosException("Alerta. No se tienen permisos de escritura en "
					+ file.getAbsolutePath() + ".");
		}
		
		//que exista -> lanzar� una excepci�n
		if (!file.exists()) {
			throw new GestionFicherosException("Alerta. El fichero "
					+ file.getAbsolutePath() + " no existe.");
		}
		
			try{
				file.delete();
			}catch (Exception e){
				System.err.println("Error en el borrado del archivo");
			}finally{
				
			}
			actualiza();
		}

	

	@Override
	public void entraA(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo, arg0);
		// se controla que el nombre corresponda a una carpeta existente
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se ha encontrado "
					+ file.getAbsolutePath()
					+ " pero se esperaba un directorio");
		}
		// se controla que se tengan permisos para leer carpeta
		if (!file.canRead()) {
			throw new GestionFicherosException("Alerta. No se puede acceder a "
					+ file.getAbsolutePath() + ". No hay permiso");
		}
		// nueva asignaci�n de la carpeta de trabajo
		carpetaDeTrabajo = file;
		// se requiere actualizar contenido
		actualiza();

	}

	@Override
	public int getColumnas() {
		return columnas;
	}

	@Override
	public Object[][] getContenido() {
		return contenido;
	}

	@Override
	public String getDireccionCarpeta() {
		return carpetaDeTrabajo.getAbsolutePath();
	}

	@Override
	public String getEspacioDisponibleCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEspacioTotalCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFilas() {
		return filas;
	}

	@Override
	public FormatoVistas getFormatoContenido() {
		return formatoVistas;
	}

	@Override
	public String getInformacion(String arg0) throws GestionFicherosException {
		
		StringBuilder strBuilder=new StringBuilder();
		File file = new File(carpetaDeTrabajo,arg0);
		
		//Controlar que existe. Si no, se lanzar� una excepci�n
		if (!file.isDirectory() && !file.isFile()) {
			throw new GestionFicherosException("Error. Se esperaba "
					+ "un directorio, o un fichero pero " + file.getAbsolutePath()
					+ " no es ninguno de ellos.");
		}
		
		//Controlar que haya permisos de lectura. Si no, se lanzar� una excepci�n
		if (!file.canRead()) {
			throw new GestionFicherosException("Alerta. No se puede acceder a "
					+ file.getAbsolutePath() + ". No hay permiso");
		}else if (!file.canExecute()) {
			throw new GestionFicherosException("Alerta. No se puede acceder a "
					+ file.getAbsolutePath() + ". No hay permiso de ejecuci�n");
		}else if (!file.canWrite()) {
			throw new GestionFicherosException("Alerta. No se puede acceder a "
					+ file.getAbsolutePath() + ". No hay permiso de escritura");
		}
		
		//T�tulo
		strBuilder.append("INFORMACI�N DEL SISTEMA");
		strBuilder.append("\n\n");
		
		//Nombre
		strBuilder.append("Nombre: ");
		strBuilder.append(arg0);
		strBuilder.append("\n");
		
		//Tipo: fichero o directorio
		if (file.isDirectory()) {
			strBuilder.append("Tipo: Directorio");
		}else if (file.isFile()){
			strBuilder.append("Tipo: Fichero");
		}
		strBuilder.append("\n");
		
		//Ubicaci�n
		strBuilder.append("Ubicaci�n: ");
		strBuilder.append(file.getAbsolutePath());
		strBuilder.append("\n");
		
		//Fecha de �ltima modificaci�n
		
        Date date=new Date(file.lastModified());
        //Dejo aqu� mis intentos por adecuar date a otro formato
        /*SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
        String dateText = df2.format(date);
        System.out.println(dateText);
        Date fecha1 = new Date(100,0,0,date%10000000,date%100000,date%1000);
        System.out.println( fecha1.toGMTString() )*/
        
		strBuilder.append("�ltima modificaci�n: ");
		strBuilder.append(date.toLocalDate());
		strBuilder.append("\n");
		
		//Si es un fichero oculto o no
		if (file.isHidden()) {
			strBuilder.append("Oculto: S�");
		}else{
			strBuilder.append("Oculto: No");
		}
		strBuilder.append("\n");
		
		//Si es directorio: Espacio libre, espacio disponible, espacio total y n�mero de elementos que contiene
		//bytes
		if (file.isDirectory()) {
			strBuilder.append("Espacio libre: "+file.getFreeSpace()+" bytes.");
			strBuilder.append("\n");
			strBuilder.append("Espacio disponible: "+file.getUsableSpace()+" bytes.");
			strBuilder.append("\n");
			strBuilder.append("Espacio total: "+file.getTotalSpace()+" bytes.");
			strBuilder.append("\n");
			try{
				//Con el m�todo listFiles(), creo un array de los elementos que hay dentro del directorio
		        File lista[]=file.listFiles();
		        //Despu�s le pido el n�mero de elementos del directorio con .length
				strBuilder.append("N�mero de elementos: "+lista.length);
				//listFiles() s�lo es v�lido para ficheros
			}catch (Exception e){
				System.err.println("Error");
			}finally{
				
			}

		}
		
		//Si es un fichero deber� mostrar su tama�o en bytes
		if (file.isFile()) {
			strBuilder.append("Tama�o: "+file.length()+" bytes.");
		}
		
		
		return strBuilder.toString();
	}

	@Override
	public boolean getMostrarOcultos() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNombreCarpeta() {
		return carpetaDeTrabajo.getName();
	}

	@Override
	public TipoOrden getOrdenado() {
		return ordenado;
	}

	@Override
	public String[] getTituloColumnas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getUltimaModificacion(String arg0)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String nomRaiz(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numRaices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void renombra(String arg0, String arg1) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		File file2 = new File(carpetaDeTrabajo,arg1);
		//que se pueda escribir -> lanzar� una excepci�n
		if (!carpetaDeTrabajo.canWrite()) {
			throw new GestionFicherosException("Alerta. No se tienen permisos de escritura en "
					+ carpetaDeTrabajo.getAbsolutePath() + ".");
		}else if (!file.canWrite()) {
			throw new GestionFicherosException("Alerta. No se tienen permisos de escritura en "
					+ file.getAbsolutePath() + ".");
		}
		
		//que exista -> lanzar� una excepci�n
		if (!file.exists()) {
			throw new GestionFicherosException("Alerta. El fichero "
					+ file.getAbsolutePath() + " no existe.");
			
		//comprueba que el nombre del fichero2 no exista
		}else if (file2.exists()){
			throw new GestionFicherosException("Alerta. El fichero "+file2.getAbsolutePath()+" ya existe.");
		}
			try{
				file.renameTo(file2);
			}catch (Exception e){
				System.err.println("Error en el borrado del archivo");
			}finally{
				
			}
			actualiza();
	}

	@Override
	public boolean sePuedeEjecutar(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeEscribir(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeLeer(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setColumnas(int arg0) {
		columnas = arg0;

	}

	@Override
	public void setDirCarpeta(String arg0) throws GestionFicherosException {
		File file = new File(arg0);

		// se controla que la direcci�n exista y sea directorio
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se esperaba "
					+ "un directorio, pero " + file.getAbsolutePath()
					+ " no es un directorio.");
		}

		// se controla que haya permisos para leer carpeta
		if (!file.canRead()) {
			throw new GestionFicherosException(
					"Alerta. No se puede acceder a  " + file.getAbsolutePath()
							+ ". No hay permisos");
		}

		// actualizar la carpeta de trabajo
		carpetaDeTrabajo = file;

		// actualizar el contenido
		actualiza();

	}

	@Override
	public void setFormatoContenido(FormatoVistas arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMostrarOcultos(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOrdenado(TipoOrden arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeEjecutar(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeEscribir(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeLeer(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUltimaModificacion(String arg0, long arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

}
