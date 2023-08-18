package br.com.abaco.voice;

public class ChamadaSenha {

	private String senha;
	private String guiche;
	private String senhaGuiche;
	
	public ChamadaSenha(String senha, String guiche) {
		this.senha = senha.toUpperCase().trim();
		this.guiche = guiche.toUpperCase().trim();
		this.senhaGuiche = this.senha + "_" + this.guiche+".mp3";
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getGuiche() {
		return guiche;
	}

	public void setGuiche(String guiche) {
		this.guiche = guiche;
	}

	@Override
	public String toString() {		
		return this.senhaGuiche;
	}

	public void isValide() throws Exception {
		if (this.guiche==null || this.guiche.isEmpty()){
			throw new Exception("Paramentro guiche não informado");
		}
		if (this.senha==null || this.senha.isEmpty()){
			throw new Exception("Paramentro senha não informado");
		}
		
	}

}
