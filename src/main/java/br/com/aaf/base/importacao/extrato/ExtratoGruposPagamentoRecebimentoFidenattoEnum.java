package br.com.aaf.base.importacao.extrato;

public enum ExtratoGruposPagamentoRecebimentoFidenattoEnum {
	NAO_SELECIONADO("Selecionar ... "),
	COMBUSTIVEL("Combustivel"),
	
	MAO_OBRA_PEDREIRO("Salário pedreiro"),
	MAO_OBRA_SERVENTE("Salário servente"),
	MAO_OBRA_MEIA_OFICIAL("Salário meia oficial"),
	
	SERVICO_COLOCACAO_ABERTURAS_MADEIRA("Servico colocacao aberturas de madeira"),
	SERVICO_COLOCACAO_ABERTURAS_ALUMINIO("Servico colocacao aberturas de aluminio"),
	SERVICO_COLOCACAO_RODAPE("Servico colocação rodapé"),
	SERVICO_GESSO("Serviço Gesso"),
	SERVICO_PISO("Serviço Piso"),
	SERVICO_LIMPEZA("Serviço Limpeza"),
	SERVICO_AMARRACAO_FERRO("Serviço amarração Ferro"),
	SERVICO_TERRAPLANAGEM("Serviço Terraplanagem"),
	SERVICO_PINTURA("Serviço pintura"),
	SERVICO_CALHA_RUFO("Serviço calha e rufo"),
	SERVICO_ELETRICA("Serviço elétrica"),
	SERVICO_INSTALACAO_GAS("Serviço gás"),
	SERVICO_INSTALACAO_POSTE_LUZ("Serviço intalação poste"),
	
	MATERIAL_COLOCACAO_ABERTURAS_MADEIRA("Material abertura madeira"),
	MATERIAL_COLOCACAO_ABERTURAS_ALUMINIO("Material abertura de aluminio"),
	MATERIAL_COLOCACAO_RODAPE("Material rodape"),
	MATERIAL_GESSO("Material gesso"),
	MATERIAL_PISO("Material piso"),
	MATERIAL_LIMPEZA("Material Limpeza"),
	MATERIAL_AMARRACAO_FERRO("Material ferragem"),
	MATERIAL_TERRAPLANAGEM("Material terraplanagem"),
	MATERIAL_PINTURA("Material pintura"),
	MATERIAL_CALHA_RUFO("Material calha e rufo"),
	MATERIAL_ELETRICA("Material eletrica"),
	MATERIAL_INSTALACAO_GAS("Material gás"),
	MATERIAL_INSTALACAO_POSTE_LUZ("Material poste luz"),
	
	MATERIAL_HIDRAULICO("Material hidraulico"),
	MATERIAL_CONSTRUCAO_PESADA("Material construcao pesada"),
	
	INDEVIDO("INDEVIDO"),
	VENDA_UNIDADE_HABITACIONAL("Venda unidade habitacional"),
	PIX("PIX"),
	SEGURO("Seguro"),
	TARIFA_BANCO("Tarifa banco"),
	IMPOSTOS_TAXAS("Imposto e taxas"),
	TERRENO("Terreno"),
	
	
	TERCERIZADO("Serviços extras"),
	
	INTERNET("Internet"),
	LUZ_GAS("Luz e Gás"),
	
	SALDO("SALDO"),
	OUTROS("Outro");
	
	private String nome;
	
	ExtratoGruposPagamentoRecebimentoFidenattoEnum(String name){
		this.setNome(name);
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}
