package br.com.aaf.base.whats.model;

import java.util.List;

public class MensagemTemplateWhats {

	private String template_name;
	private String broadcast_name = "string";
	private List<Parametro> parameters;
	
	public String getTemplate_name() {
		return template_name;
	}
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
	public String getBroadcast_name() {
		return broadcast_name;
	}
	public void setBroadcast_name(String broadcast_name) {
		this.broadcast_name = broadcast_name;
	}
	public List<Parametro> getParameters() {
		return parameters;
	}
	public void setParameters(List<Parametro> parameters) {
		this.parameters = parameters;
	}
	
}
