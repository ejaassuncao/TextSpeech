package br.com.ewa.voice;

import java.util.ArrayList;
import java.util.List;

public class AnalisadorFoneticoNumerico {

	private MapaArquivos mapa;
	private List<String> listaFonetica;
	
	public AnalisadorFoneticoNumerico(MapaArquivos pMapa){
		this.mapa = pMapa;
		this.listaFonetica = new ArrayList<String>();
		
	}

	public List<String> getAnalise(String numero) throws Exception{
		this.listaFonetica.clear();		
		
		if (numero == null)
			return null;

		if (Integer.parseInt(numero) == 0) {
			numero = "0";
		}
		
		MontaListaFonetica(numero);
		
		
		return listaFonetica;
	}

	private void MontaListaFonetica(String numero) throws Exception {

		Integer novoNumero;

		switch (numero.length()) {

		// unidade de 0 a 9
		case 1:
			// 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 = Total=>10			
			this.listaFonetica.add(mapa.get(numero));		
			break;

		// dezena de 10 a 99
		case 2:
			// 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 30, 40, 50, 60, 70, 80, 90 = Total			
			novoNumero = Integer.parseInt(numero);

			if (novoNumero == 0) {
				//se vier "00"
				break;
			}

			if (novoNumero.toString().length() < 2) {
				this.MontaListaFonetica(novoNumero.toString());
				break;
			}

			if (mapa.existe(numero)) {
				this.listaFonetica.add(mapa.get(numero));
			} else {
				String obterPrimeiroCaracter = String.valueOf(numero.charAt(0));
				String key = obterPrimeiroCaracter + "0";
				this.listaFonetica.add(mapa.get(key)); // adicionei ao mapa, vai existir no mapa
				String novoTexto = numero.substring(1, numero.length()); // remove o 1 caracter

				this.listaFonetica.add(mapa.get("E")); //e
				this.MontaListaFonetica(novoTexto);
			}
			break;

		// centena de 100 a 999
		case 3:
			// 100, 200, 300, 400, 500, 600, 700, 800, 900 = Total => 9

			novoNumero = Integer.parseInt(numero);

			if (novoNumero == 0) {
				break;
			}

			if (novoNumero.toString().length() < 3) {
				this.MontaListaFonetica(novoNumero.toString());
				break;
			}

			if (mapa.existe(numero)) { // se o valor for 100, 200, 300, 400, 500, 600, 700, 800, 900
				this.listaFonetica.add(mapa.get(numero)); // adicionei ao mapa
			} else {
				String obterPrimeiroCaracter = String.valueOf(numero.charAt(0));
				String key = obterPrimeiroCaracter + "00"; // Key = 900

				if (Integer.parseInt(key) == 100) {
					this.listaFonetica.add(mapa.get("#004")); // cento
				} else {
					this.listaFonetica.add(mapa.get(key)); // adicionei ao mapa, vai existir no mapa
				}

				String novoTexto = numero.substring(1, numero.length()); // novoTexto = "185"

				this.listaFonetica.add(mapa.get("E")); // eh
				this.MontaListaFonetica(novoTexto);

			}
			break;

		// Milhar de 1000 ate 9999
		case 4:
			// 1000
			novoNumero = Integer.parseInt(numero);
			
			//para caso vier 00000
			if (Integer.parseInt(numero) == 0) {
				break;
			}
			
			//para caso vier 0005
			if (novoNumero.toString().length() < 4) {
				this.MontaListaFonetica(novoNumero.toString());
				break;
			}

			if (mapa.existe(numero)) { // se o valor for 1000
				this.listaFonetica.add(mapa.get(numero)); // adicionei ao mapa
			} else {

				String obterPrimeiroCaracter = String.valueOf(numero.charAt(0));//5 - 206
				
				this.MontaListaFonetica(obterPrimeiroCaracter); //5 mil
				this.listaFonetica.add(mapa.get("1000"));
				
				//remove o 1 digito
				String novoTexto = numero.substring(1, numero.length()); // novoTexto = "185"								
			    if (Integer.parseInt(novoTexto) <=100) {
			    	this.listaFonetica.add(mapa.get("E")); // eh
			    }
				this.MontaListaFonetica(novoTexto);
			}
			break;

		case 5:
			// milhoes Total =>1
			throw new Exception("N�o implamentado valores igual a 5 casas decimais");

		case 6:
			// Bilh�o Total =>1
			throw new Exception("N�o implamentado valores igual a 6 casas decimais");

		case 7:
			// Trilh�o Total =>1
			throw new Exception("N�o implamentado valores igual a 7 casas decimais");

		}

	}

}