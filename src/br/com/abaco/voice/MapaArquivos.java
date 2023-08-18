package br.com.abaco.voice;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

public class MapaArquivos {

	Map<String, String> audios;
	private String caminhAbs;
	private ServletContext servletContext;
	private String diretorioPath;
		
	public MapaArquivos(ServletContext servletContext) throws IOException {
		this.servletContext = servletContext;
		MontarMapa();
	}

	public String getDiretorioPath(){
		return this.diretorioPath;
	}
	
	private void MontarMapa() throws IOException {

		if (audios != null)
			return;
		
		String separador = File.separator;
		this.diretorioPath = this.servletContext.getRealPath("")+ separador;
		this.caminhAbs = this.diretorioPath   + "resource" + separador	+ "isabele" + separador;
		
		audios = new HashMap<String, String>();
		
		// unidade
		audios.put("0", "voice_0.mp3");
		audios.put("1", "voice_1.mp3");
		audios.put("2", "voice_2.mp3");
		audios.put("3", "voice_3.mp3");
		audios.put("4", "voice_4.mp3");
		audios.put("5", "voice_5.mp3");
		audios.put("6", "voice_6.mp3");
		audios.put("7", "voice_7.mp3");
		audios.put("8", "voice_8.mp3");
		audios.put("9", "voice_9.mp3");

		// dezena
		audios.put("10", "voice_10.mp3");
		audios.put("11", "voice_11.mp3");
		audios.put("12", "voice_12.mp3");
		audios.put("13", "voice_13.mp3");
		audios.put("14", "voice_14.mp3");
		audios.put("15", "voice_15.mp3");
		audios.put("16", "voice_16.mp3");
		audios.put("17", "voice_17.mp3");
		audios.put("18", "voice_18.mp3");
		audios.put("19", "voice_19.mp3");

		audios.put("20", "voice_20.mp3");
		audios.put("30", "voice_30.mp3");
		audios.put("40", "voice_40.mp3");
		audios.put("50", "voice_50.mp3");
		audios.put("60", "voice_60.mp3");
		audios.put("70", "voice_70.mp3");
		audios.put("80", "voice_80.mp3");
		audios.put("90", "voice_90.mp3");

		// centena
		audios.put("100", "voice_100.mp3");
		audios.put("200", "voice_200.mp3");
		audios.put("300", "voice_300.mp3");
		audios.put("400", "voice_400.mp3");
		audios.put("500", "voice_500.mp3");
		audios.put("600", "voice_600.mp3");
		audios.put("700", "voice_700.mp3");
		audios.put("800", "voice_800.mp3");
		audios.put("900", "voice_900.mp3");

		// milhar
		audios.put("1000", "voice_1000.mp3");

		// as letras
		audios.put("A", "voice_A.mp3");
		audios.put("B", "voice_B.mp3");
		audios.put("C", "voice_C.mp3");
		audios.put("D", "voice_D.mp3");
		audios.put("E", "voice_E.mp3");
		audios.put("F", "voice_F.mp3");
		audios.put("G", "voice_G.mp3");
		audios.put("H", "voice_H.mp3");
		audios.put("I", "voice_I.mp3");
		audios.put("J", "voice_J.mp3");
		audios.put("L", "voice_L.mp3");
		audios.put("M", "voice_M.mp3");
		audios.put("N", "voice_N.mp3");
		audios.put("O", "voice_O.mp3");
		audios.put("P", "voice_P.mp3");
		audios.put("Q", "voice_Q.mp3");
		audios.put("R", "voice_R.mp3");
		audios.put("S", "voice_S.mp3");
		audios.put("T", "voice_T.mp3");
		audios.put("U", "voice_U.mp3");
		audios.put("V", "voice_V.mp3");
		audios.put("X", "voice_X.mp3");
		audios.put("Z", "voice_Z.mp3");
		audios.put("Y", "voice_Y.mp3");
		audios.put("W", "voice_W.mp3");
		audios.put("K", "voice_K.mp3");

		// complementais
		audios.put("#002", "voice_comparecer.mp3");
		audios.put("#003", "voice_senha.mp3");
		audios.put("#004", "voice_cento.mp3");
		
	}

	public String get(String key) {
		return this.caminhAbs + audios.get(key);
	}

	public boolean existe(String key) {
		return audios.containsKey(key);
	}
}
