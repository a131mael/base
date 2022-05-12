package br.com.aaf.base.importacao.extrato;

public enum ExtratoTiposEntradaSaidaEnum {

	ENTRADA("Entrada"),
	SAIDA("Saida"),
	ESTORNO("Estorno"),
	APLICACAO("Aplicacao"),
	ENTRADA_EXTRAORDINARIA("Extraordinario");
	
	private String nome;
	
	ExtratoTiposEntradaSaidaEnum(String name){
		this.setNome(name);
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}
