

import org.apache.commons.codec.digest.DigestUtils;

public class encriptación {
	private String sinEncriptar;	
	private String Encriptado;

	public encriptación() {

	sinEncriptar="12345";
	Encriptado=DigestUtils.md5Hex(sinEncriptar); 
	
	System.out.println(Encriptado);
	
	
	}

}
