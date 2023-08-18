package br.com.ewa.voice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Vector;

import br.com.abaco.commons.UrlHost;

public class MergeMp3Builder {

	private MapaArquivos mapa;
	private String dirTemp;
	private String nomeAbsOutArquivo;
	private AnalisadorFoneticoNumerico analisador; // depois mudar para um
													// interface, para ter um
													// analisador de numero e
	private ChamadaSenha chamadaSenha;

	public MergeMp3Builder(ChamadaSenha chamadaSenha, MapaArquivos mapaArquivos)
			throws Exception {
		chamadaSenha.isValide();

		this.chamadaSenha = chamadaSenha;
		this.mapa = mapaArquivos;
		this.analisador = new AnalisadorFoneticoNumerico(mapa);
		String separador = File.separator;
		this.dirTemp = mapaArquivos.getDiretorioPath() + "tmp" + separador;
	}

	private Vector<InputStream> GetMontarElemento(Vector<InputStream> vector,
			String texto) throws Exception {

		// apenas para senha PWS123
		// n�o ve bem imbalharada exemplo: PE6NHF23
		
		//TODO:criar uma analisado de texto
		// soletre
		String letras = texto.replaceAll("[^A-Z]", "");	
		for (int i = 0; i < letras.length(); i++) {
			String key = String.valueOf(letras.charAt(i));
			String absArquivo = mapa.get(key);
			try {
				vector.add(new FileInputStream(absArquivo));
			} catch (FileNotFoundException e) {
				throw new FileNotFoundException(
						"Arquivo de audio n�o encontrado: " + absArquivo);
			}
		}
				
		//Analisador numerico
		String numero = texto.replaceAll("[^0-9]", "");
		if (!numero.isEmpty()) {
			for (String absArquivo : analisador.getAnalise(numero)) {
				try {
					vector.add(new FileInputStream(absArquivo));
				} catch (FileNotFoundException e) {
					throw new FileNotFoundException(
							"Arquivo de audio n�o encontrado: " + absArquivo);
				}
			}
		}

		return vector;

	}

	private SequenceInputStream getMontaSequencia() throws Exception {

		Vector<InputStream> vector = new Vector<InputStream>();

		// senha,
		vector.add(new FileInputStream(mapa.get("#003")));

		// X
		// monta senha
		vector = GetMontarElemento(vector, chamadaSenha.getSenha());

		// comparecer ao guiche
		vector.add(new FileInputStream(mapa.get("#002")));

		// X
		// monta Guiche
		vector = GetMontarElemento(vector, chamadaSenha.getGuiche());

		return new SequenceInputStream(vector.elements());

	}

	public String getAbsFile() throws Exception {

		nomeAbsOutArquivo = dirTemp + chamadaSenha.toString();

		File file = new File(nomeAbsOutArquivo);

		if (file.isFile()) {
			return nomeAbsOutArquivo;
		}

		// monta saida do arquivo
		SequenceInputStream sequenciaFile = this.getMontaSequencia();

		FileOutputStream outStream = new FileOutputStream(nomeAbsOutArquivo);

		int temp;
		while ((temp = sequenciaFile.read()) != -1) {
			outStream.write(temp);
		}
		outStream.close();
		sequenciaFile.close();

		return nomeAbsOutArquivo;
	}
	
	public String getAbsUrl(UrlHost urlHost) throws Exception {
		this.getAbsFile();
		return urlHost.getBaseUrl() + "tmp/" + this.chamadaSenha.toString();
	}
}
