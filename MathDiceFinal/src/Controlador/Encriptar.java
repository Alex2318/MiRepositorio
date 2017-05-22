package Controlador;

import org.apache.commons.codec.digest.DigestUtils;

/*CLASE CREADA ÚNICA Y EXCLUSIVAMENTE PARA ORGANIZAR CÓDIGO REFERENTE A LA ENCRIPTACIÓN DE LA CONTRASEÑA
EN ELLA TENEMOS UN STRING DE SALIDA Y UN MÉTODO QUE RECIBE UN STRING Y DEVUELVE OTRO STRING, PARA
EL CORRECTO FUNCIONAMIENTO IMPORTADO EL PAQUETE CORRESPONDIENTE*/

public class Encriptar {
	
	public static String encriptado;

	public static String EncriptarPassword(String password) {
		
		encriptado=DigestUtils.md5Hex(password);
		
		return encriptado;
	}

}
