package br.com.abaco.commons;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class ProxyAuthenticator  extends Authenticator{
	  private String userName, password;

	    public ProxyAuthenticator(String userName, String password) {
	        this.userName = userName;
	        this.password = password;
	    }

	    protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication(userName, password.toCharArray());
	    }
	    
	    
//		if(this.con.usingProxy()){
//		Authenticator.setDefault(new ProxyAuthenticator("elton.assuncao", "elton@21"));
//         SocketAddress addr = new InetSocketAddress("proxy.server.com", 80);
//		 Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
//		 this.con = (HttpURLConnection) url.openConnection(proxy);
//	}else{
//	}
}
