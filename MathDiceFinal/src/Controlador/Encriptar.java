package Controlador;

import org.apache.commons.codec.digest.DigestUtils;

/*CLASE CREADA �NICA Y EXCLUSIVAMENTE PARA ORGANIZAR C�DIGO REFERENTE A LA ENCRIPTACI�N DE LA CONTRASE�A
EN ELLA TENEMOS UN STRING DE SALIDA Y UN M�TODO QUE RECIBE UN STRING Y DEVUELVE OTRO STRING, PARA
EL CORRECTO FUNCIONAMIENTO IMPORTADO EL PAQUETE CORRESPONDIENTE*/

public class Encriptar {
	
	public static String encriptado;

	public static String EncriptarPassword(String password) {
		
		encriptado=DigestUtils.md5Hex(password);
		
		return encriptado;
	}

}
