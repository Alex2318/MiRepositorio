package Controlador;

import org.apache.commons.codec.digest.DigestUtils;

public class Encriptar {
	
	public static String encriptado;

	public static String EncriptarPassword(String password) {
		
		encriptado=DigestUtils.md5Hex(password);
		
		return encriptado;
	}

}
