package br.com.aaf.base.importacao.extrato;

public enum ExtratoGruposPagamentoRecebimentoEnum {

	NAO_SELECIONADO("Selecionar ... "),
	COMBUSTIVEL("Combustivel"),
	CONSERTO("Conserto / Manutencao"),
	DOCUMENTACAO("Documentação"),
	INDEVIDO("INDEVIDO"),
	INTERNET("Internet"),
	LIMPEZA("Limpeza"),
	PECA("PEÇA"),
	MANUTENCAO("Manutencao veiculo"),
	SEGURO("Seguro"),
	SALARIO("Salário"),
	TARIFA_BANCO("Tarifa banco"),
	TARIFA_GERAL("Imposto e taxas"),
	ENTRADA_BOLETO("Boleto Recebido"),
	ENTRADA_PIX("Pix Recebido"),
	ENTRADA_FRETE("Entrada Frete"),
	ENTRADA_ACORDO_DIVIDA("Acordo Divida"),
	OUTROS("Outro");
	
	private String nome;
	
	ExtratoGruposPagamentoRecebimentoEnum(String name){
		this.setNome(name);
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}
