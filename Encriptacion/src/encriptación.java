

import org.apache.commons.codec.digest.DigestUtils;

public class encriptaci�n {
	private String sinEncriptar;	
	private String Encriptado;

	public encriptaci�n() {

	sinEncriptar="12345";
	Encriptado=DigestUtils.md5Hex(sinEncriptar); 
	
	System.out.println(Encriptado);
	
	
	}

}
