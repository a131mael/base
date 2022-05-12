package br.com.aaf.base.base;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.aaf.base.whats.model.Parametro;

@JsonIgnoreProperties
public class RetornoEnvioWhats implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean result;
	@JsonIgnore
	private String info;
	private boolean validWhatsAppNumber;
	private String phone_number;
	@JsonIgnore
	private String template_name;
	@JsonIgnore
	private List<Parametro> parameteres;
	@JsonIgnore
	private String contact;
	@JsonIgnore
	private String model;
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public boolean isValidWhatsAppNumber() {
		return validWhatsAppNumber;
	}
	public void setValidWhatsAppNumber(boolean validWhatsAppNumber) {
		this.validWhatsAppNumber = validWhatsAppNumber;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getTemplate_name() {
		return template_name;
	}
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
	public List<Parametro> getParameteres() {
		return parameteres;
	}
	public void setParameteres(List<Parametro> parameteres) {
		this.parameteres = parameteres;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
}
