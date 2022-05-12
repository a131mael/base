package br.com.aaf.base.whats.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RetornoMensagemWhatsappWatiMensagensList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MensagemWhatsapp [] items;
	public MensagemWhatsapp [] getItems() {
		return items;
	}
	public void setItems(MensagemWhatsapp [] items) {
		this.items = items;
	}
}
