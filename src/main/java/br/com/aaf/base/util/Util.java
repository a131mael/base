package br.com.aaf.base.util;

import java.util.Calendar;

public class Util {

	public static String removeCaracteresEspeciais(String texto) {
		texto = texto.replaceAll("[^aA-zZ-Z0-9 ]", "");
		return texto;
	}

	public static String getMes(int mes) {
		switch (mes) {
		case 1:
			return "Janeiro";

		case 2:
			return "Fevereiro";

		case 3:
			return "Março";

		case 4:
			return "Abril";

		case 5:
			return "Maio";

		case 6:
			return "Junho";

		case 7:
			return "Julho";

		case 8:
			return "Agosto";

		case 9:
			return "Setembro";

		case 10:
			return "Outubro";

		case 11:
			return "Novembro";

		case 12:
			return "Dezembro";

		default:
			return "Janeiro";
		}
	}

}
