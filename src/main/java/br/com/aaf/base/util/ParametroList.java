package br.com.aaf.base.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.aaf.base.whats.model.Parametro;

public class ParametroList {

	private static List<Parametro> parametros = new ArrayList<>();

	public static void addParametro(Parametro parametro) {
		getParametros().add(parametro);
	}

	public static void addParametro(String nome, String valor) {
		Parametro p1 = new Parametro(nome, valor);
		getParametros().add(p1);
	}

	public static List<Parametro> getParametros() {
		return parametros;
	}

	public static void limpar() {
		setParametros(new ArrayList<>());
	}

	public static void addParametro(Map<String, Object> parametrosMap) {
		Iterator<Map.Entry<String, Object>> itr = parametrosMap.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry<String, Object> entry = itr.next();
			addParametro(entry.getKey(), entry.getValue().toString());
		}
	}

	public static List<Parametro> convert(Map<String, Object> parametrosMap) {
		Iterator<Map.Entry<String, Object>> itr = parametrosMap.entrySet().iterator();
		List<Parametro> parametros = new ArrayList<>();
		while (itr.hasNext()) {
			Map.Entry<String, Object> entry = itr.next();
			Parametro p = new Parametro(entry.getKey(), entry.getValue().toString());
			parametros.add(p);
		}

		return parametros;
	}

	public static ParametroList getObjeto() {
		ParametroList p = new ParametroList();
		p.setParametros(getParametros());
		return p;
	}

	public static void setParametros(List<Parametro> parametros) {
		ParametroList.parametros = parametros;
	}

}
