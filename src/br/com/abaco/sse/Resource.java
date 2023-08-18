package br.com.abaco.sse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Resource {

	private int statusCode = 500;
	private String sJson ="";

	public Resource(String pUrl) throws Exception {
	
		IConnectHost connectHost = new UrlConnectHost(pUrl);

		if (connectHost.getGetResponseCode() != 200) {
			connectHost.disconnect();
			System.out.println("erro ao conectar com o objeto genexus \n url:"
					+ pUrl);
			return;
		}

		InputStream inputStream = connectHost.getInputStream();

		if (inputStream != null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));

			sJson = "";
			String nextLine;
			while ((nextLine = br.readLine()) != null) {
				sJson += nextLine;
			}
			br.close();
			connectHost.disconnect();
			statusCode = 200;
		}

	}

	public String getJSON() {
		return sJson;
	}

	public boolean isGetStatusCode(int code) {

		return (this.statusCode == 200);
	}

	public int getStatusCode() {
		return this.statusCode;
	}

}
