package br.com.abaco.sse;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlConnectHost implements IConnectHost {

	private int responseCode;
	private URL url;
	private HttpURLConnection con;

	public UrlConnectHost(String pUrl) {
		try {
			this.url = new URL(pUrl);
			this.con = (HttpURLConnection) url.openConnection();
			this.con.setRequestMethod("GET");
			this.con.connect();
			
			this.responseCode = con.getResponseCode();
		} catch (Exception e) {			
			System.out.println("Erro Interno\n url:" + pUrl + "\n"
					+ e.getMessage());
			this.responseCode = 500;
		}
	}

	public void disconnect() {
		this.con.disconnect();
	}

	public int getGetResponseCode() {
		return this.responseCode;
	}

	public InputStream getInputStream() {		
		try {
			return con.getInputStream();
		} catch (IOException e) {
			System.out.println("sem conteudo:" + e.getMessage());			
		}
		return null ;
	}

}
