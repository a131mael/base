package br.com.aaf.base.importacao.extrato;

public enum ExtratoTiposEntradaEnum {

	DEBITO_BOLETO("DÉB.TIT.COMPE.EFE","Pagamento de Boleto"),
	
	DEBITO_SEGURO("DÉB. CONV. SEGURO","Pagamento de Seguro"),
	
	CREDITO_LIQUIDACAO_COBRANCA("CRÉD.LIQ.COBRANÇA","Credito Boleto"),
	
	COMPRA_COM_CARTAO("COMP MASTER MAEST","Compra com cartão"),
	
	PIX_RECEBIDO("PIX RECEB.OUTRA I","Pix Recebido"),
	
	PIX_ENVIADO("PIX EMIT.OUTRA IF","Pagamento com PIX"),
	
	PIX_ENVIADO_CONTAS_SICOOB("TRANSF. PIX SICOO","Pix entre contas sicoob"),
	
	CHEQUE("CHQ CMP INTEGRADA","Pagamento de cheque"),

	TED("TED INTERNET"," Tarifa Ted "),
	
	TARIFA_REN("TARIFA COBRANÇA","Tarifa de registro de boleto"),
	
	TARIFA("DB.CONV.TR FD-RFB","Tarifa de cobraça Tributo Federal"),
	
	APLICACAO("APLICAÇÃO RDC    ","Aplicacao RDC"),
	
	SAQUE("SAQUE NA AGENCIA ","Saque"),
	
	SAQUE_PESSOAL("SAQUE PESSOAL","Saque pessoal"),
	
	PACOTE_SERVICOS("DEB PACOTE SERVIÇ ","Débito pacote serviços conta"),
	
	TED_OUTRA_TITULARIDADE("DEB.EMI.TED DIF.T ","TED outra titularidade"),
	
	DEBITO_ORGAO_FEDERAL("DÉB.CONV.ORGÃOS G ","Débito tafifa federal"),
	
	DEBITO_TELECOMUNICACAO("DÉB.CONV.TELECOMU ","Débito telecomunicações"),
	
	NAO_IDENTIFICADO("naoIdentificado ","Não identificado"),
	
	ENTRADA_MAQUININHA_CREDITO("CR CMP MSTD SIPAG","Entrada Maquinha credito"),
	
	ENTRADA_MAQUININHA_DEBITO("CR CMP MST SIPAG_","Entrada Maquinha debito"),
	
	TRANSFERENCIA_OUTRA_CONTA_SICOOB("DEB.TR.CT.DIF.TIT","Transferencia outra conta"),
	
	CREDITO_TRANSFERENCIA("CRED.TR.CT.INTERC","Credito Transferencia"),
	
	DEBITO_TRANSFERENCIA("DB.TR.C.DIF.TIT.I","Debito Transferencia"),
	
	DEBITO_MASTERCARD_TRANSFERENCIA("DÉB.CONV.DEM.EMPR","Debito Cartão Mastercad"),
	
	RESGATE_APLICACAO("RESGATE RDC      ","Resgate aplicação"),
	
	Entrada_CREDITO_SIPAG("CR CMP VISA SIPAG","Entrada credito sipag"),
	
	DEBITO_EMPRESTIMO("DÉB.EMPRÉSTIMO  ","Emprestimo"),
	
	DEBITO_FGTS("DÉB CONV. FGTS   ","FGTS"),
	
	DEBITO_CELESC("DÉB.CNV.EN.ELET.G","Energia eletrica "),
	
	DEBITO_CASAM("DÉB.CONV.SANEAMEN","Agua e esgoto"),
	
	DESPESAS_DIVERSAS("DESPESAS DIVERSAS","Diversos"),
	
	DINHEIRO("Dinheiro","Dinheiro");
	
	
	
	
	
	private String nameExtrato;
	private String nameReal;
	
	ExtratoTiposEntradaEnum(String name, String nomeReal){
		this.setNameExtrato(name);
		this.setNameReal(nomeReal);
		
	}

	public String getNameExtrato() {
		return nameExtrato;
	}

	public void setNameExtrato(String nameExtrato) {
		this.nameExtrato = nameExtrato;
	}

	public String getNameReal() {
		return nameReal;
	}

	public void setNameReal(String nameReal) {
		this.nameReal = nameReal;
	}

}
