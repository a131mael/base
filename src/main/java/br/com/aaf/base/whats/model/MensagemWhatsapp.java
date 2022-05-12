package br.com.aaf.base.whats.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MensagemWhatsapp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String type;
	
	private boolean owner;
	
	private String eventType;
	
	private String finalText;
	
	private String text;
	
	private String data; 
	
	private String timestamp; //para mensagens normais
	
	private String created; // para mensagens de template

	private boolean primeiro;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getEventType() {
		return eventType;
	}


	public void setEventType(String eventType) {
		this.eventType = eventType;
	}


	public String getText() {
		if ("broadcastMessage".equalsIgnoreCase(getEventType())) {
			return finalText;
		} else if (!"ticket".equalsIgnoreCase(getEventType())) {
			return text;
		}
		
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public String getTimestamp() {
		return timestamp == null?"0":timestamp;
	}


	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


	public String getCreated() {
		return created;
	}


	public void setCreated(String created) {
		this.created = created;
	}

	public String getFinalText() {
		return finalText;
	}

	public void setFinalText(String finalText) {
		this.finalText = finalText;
	}

	public boolean isOwner() {
		return owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	public boolean isPrimeiro() {
		return primeiro;
	}

	public void setPrimeiro(boolean primeiro) {
		this.primeiro = primeiro;
	}
}
