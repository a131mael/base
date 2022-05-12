package br.com.aaf.base.importacao.extrato;

public enum ExtratoGruposPagamentoRecebimentoAdonaiEnum {

	NAO_SELECIONADO("Selecionar ... "),
	APLICACAO("Aplicação"),
	AGUA_LUZ_TELEFONE("Agua, luz, telefonia"),
	MERCADO("Supermercado"),
	LIMPEZA("Limpeza"),
	EVENTO("Evento"),
	FINANCIAMENTO("Financiamento"),
	OBRA_REFORMA_MANUTENCAO("Obras, Reformas, Manutenção"),
	INDEVIDO("INDEVIDO"),
	TARIFA_BANCO("Tarifa banco"),
	TARIFA_GERAL("Impostos e documentos"),
	ENTRADA_BOLETO("Boleto Recebido"),
	ENTRADA_PIX("Pix Recebido"),
	SAQUE_DINHEIRO("Saque"),
	IMPRESSORA("Impressora"),
	UNIFORME("Uniforme"),
	LIVRO("Livros"),
	ENTRADA_AULA_EXTRA("Aulas extras"),
	ENTRADA_LANCHE("Lanche"),
	SALARIO("Salário"),
	OUTROS("Outro");
	
	private String nome;
	
	ExtratoGruposPagamentoRecebimentoAdonaiEnum(String name){
		this.setNome(name);
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}
