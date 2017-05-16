import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Conexion {



    	 public Conexion(String user, String password){
         Hashtable<String, String> env = new Hashtable<String, String>();
         env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
         env.put(Context.SECURITY_AUTHENTICATION, "simple");
         env.put(Context.PROVIDER_URL, "ldap://10.2.72.109");

         //Rellenamos con el usuario/dominio y password
         env.put(Context.SECURITY_PRINCIPAL, user+"@almobewin2012.com");
         env.put(Context.SECURITY_CREDENTIALS, password);

         DirContext ctx;

         try {
             // Authenticate the logon user
             ctx = new InitialDirContext(env);
             System.out.println("El usuario se ha autenticado correctamente");
             ctx.close();

         } catch (NamingException ex) {
             System.out.println("Ha habido un error en la autenticación"+ex);
         }



    	 }
     }
     
