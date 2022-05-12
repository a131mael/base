package br.com.aaf.base.whats.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RetornoMensagemWhatsappWati implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<RetornoMensagemWhatsappWatiMensagensList> messages;
	
	private String result;

	public List<RetornoMensagemWhatsappWatiMensagensList> getMessages() {
		return messages;
	}

	public void setMessages(List<RetornoMensagemWhatsappWatiMensagensList> messages) {
		this.messages = messages;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
}
