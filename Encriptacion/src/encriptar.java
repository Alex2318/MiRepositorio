import org.apache.commons.codec.digest.DigestUtils;


public class encriptar {

	public static void main(String[] args) {
		

		String sinEncriptar="12345";
		String Encriptado=DigestUtils.md5Hex(sinEncriptar); 
		System.out.println(Encriptado);
		

	}

}
