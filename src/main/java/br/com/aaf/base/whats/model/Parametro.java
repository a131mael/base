package br.com.aaf.base.whats.model;

public class Parametro {

	public Parametro() {

	}

	public Parametro(String nome, String value) {
		this.name = nome;
		this.value = value;
	}

	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
