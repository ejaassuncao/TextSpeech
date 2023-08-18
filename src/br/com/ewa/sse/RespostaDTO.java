package br.com.ewa.sse;

import java.util.Date;


public class RespostaDTO {
	private static final String newLinha ="\n";
	public static String Parse(String event, String json) {
		StringBuilder sb = new StringBuilder();		
		long id = new Date().getTime();	
		if (event!="message"){
			sb.append("event:").append(event).append(newLinha);
		}
		sb.append("id:").append(id).append(newLinha);
		sb.append("data:").append(json).append(newLinha);
		sb.append(newLinha).append(newLinha);	
		
		return sb.toString();
	}

}
