package br.com.aaf.base.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * EXEMPLO
 * File arquivos[];
		File diretorio = new File(path);
		arquivos = diretorio.listFiles();

		for (int i = 0; i < arquivos.length; i++) {
			List<String> arquivo = OfficeUtil.lerArquivo(path + arquivos[i].getName());
 * 
 *  public String getLinha(List<String> arquivo, int linha ){
 *  
 *   public static String getValor(String linha, int inicio, int fim) {
 *  
 * **/

public class OfficeUtil {

	public static String quebraLinhaTXT = "%n";

	public static void criarTXT(String arquivo, String texto) {

		try {
			FileWriter arq;
			arq = new FileWriter(arquivo);
			PrintWriter gravarArq = new PrintWriter(arq);

			gravarArq.printf(texto);

			arq.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void editarTXT(String arquivo, String texto) {

		try {
			FileWriter arq;
			arq = new FileWriter(arquivo);
			PrintWriter gravarArq = new PrintWriter(arq);

			gravarArq.printf(texto);
			gravarArq.printf("+-------------+%n");

			arq.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<String> lerArquivo(String arquivo) {
		List<String> linhasAquivo = new ArrayList<String>();
		try {
			Path path = Paths.get(arquivo);
			BufferedReader br = Files.newBufferedReader(path, StandardCharsets.ISO_8859_1);

			while (br.ready()) {
				linhasAquivo.add(br.readLine());
			}
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return linhasAquivo;
	}

	public static String espacos(int quantidade) {
		String ret = "";
		for (int i = 0; i < quantidade; i++) {
			ret += " ";
		}

		return ret;
	}

	public static String preencherEspacosEsquerda(String texto, int quantidade) {
		String ret = "";
		for (int i = texto.length(); i < quantidade; i++) {
			ret += " ";
		}

		return ret + texto;
	}

	public static String preencherZeroEsquerda(String texto, int quantidade) {
		String ret = "";
		for (int i = texto.length(); i < quantidade; i++) {
			ret += "0";
		}

		return ret + texto;
	}

	public static String preencherEspacosDireita(String texto, int quantidade) {
		String ret = texto;
		for (int i = texto.length(); i < quantidade; i++) {
			ret += " ";
		}

		return ret;
	}

	public static String limiteMaximo(String texto, int quantidade) {
		return texto.substring(0, quantidade + 1);
	}

	public static String retornaDataSomenteNumeros(Date data) {
		if (data != null) {
			final DateFormat df = new SimpleDateFormat("ddMMyyyy");
			return df.format(data);
		} else {
			return null;
		}
	}

	public static String retornaHoraSomenteNumeros(Date data) {

		final DateFormat df = new SimpleDateFormat("hhmmss");
		return df.format(data);
	}

	public static String retornarValorFormatado(double valor) {
		String sb = String.format("%.2f", valor);
		sb = sb.replace(".", "");
		sb = sb.replace(",", "");
		return sb;
	}

	public static Date retornaData(String dataString) throws ParseException {
		if (dataString != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
			Date date = formatter.parse(dataString);
			return date;
		} else {
			return null;
		}
	}

	public static String retornarComVirgula(String valor) {
		String sb1 = valor.substring(valor.length() - 2);
		String sb2 = valor.substring(0, valor.length() - 2);

		return sb2 + "," + sb1;
	}

	public static byte[] pathToByteArray(String caminho) {
		Path path = Paths.get(caminho);
		byte[] data = null;
		try {
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public static String getLinha(List<String> arquivo, int linha) {
		if (linha >= arquivo.size()) {
			return null;
		}
		return arquivo.get(linha);
	}

	public static String getValor(String linha, int inicio, int fim) {
		if(linha != null){
			return linha.substring(inicio - 1, fim);
		}
		return "";
	}

	public static void moveFile(String origin, String target) throws IOException {
		System.out.println("movendo o arquivo de :" + origin + " para : " + target);
		try {
			System.out.println("Achando arquivo Origen");
			Path pathOrig = Paths.get(origin);
			System.out.println("Achando arquivo destino");
			Path pathDest = Paths.get(target);
			System.out.println("Movendo");
			Path temp = Files.move(pathOrig, pathDest);
			System.out.println("movido ");
			if (temp != null) {
				System.out.println("File renamed and moved successfully");
			} else {
				System.out.println("Failed to move the file");
			}

		} catch (IOException io) {
			System.out.println("--------------- ");
			System.out.println("Não Conseguiu mover o arquivo.. deu ruim na hora de mover");
			System.out.println("Origem " + origin);
			System.out.println("destino " + target);
			System.out.println("--------------- ");
			// io.printStackTrace();

			File file = new File(origin);
			if (file.renameTo(new File(target))) {
				file.delete();
				System.out.println("File moved successfully");
			} else {
				System.out.println("Failed to move the file");
			}

		}

	}

}
