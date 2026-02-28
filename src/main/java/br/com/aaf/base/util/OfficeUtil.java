package br.com.aaf.base.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

	public CellStyle fontePadraoXls(XSSFWorkbook workbook, boolean centralizar) {
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		font.setBold(false);
		Short tamanhofonte = 200;
		font.setFontHeight(tamanhofonte);
		// font.setUnderline(Font.U_SINGLE);
		font.setColor(HSSFColorPredefined.DARK_RED.getIndex());
		style.setFont(font);
		if (centralizar) {
			style.setAlignment(HorizontalAlignment.CENTER);
		}
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		return style;

	}

	public CellStyle fonteNegritoXLS(XSSFWorkbook workbook, Short tamanhoFonte, Short color) {
		if (color == null) {
			color = 0;
		}

		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");

		font.setColor(color);
		font.setBold(true);
		font.setFontHeight(tamanhoFonte);
		// font.setUnderline(Font.U_SINGLE);
		font.setColor(HSSFColorPredefined.DARK_RED.getIndex());
		style.setFont(font);

		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		return style;
	}

	public XSSFSheet criarArquivoExel(String localSalvarArquivo, String nomeArquivo, int mes, int ano) {
		try {

			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet(nomeArquivo);

			for (int i = 0; i < 20; i++) {
				sheet.autoSizeColumn(i);
			}

			montarCabecalhoExtrato(sheet);
			montarDetalheCabecalho(sheet, workbook, mes, ano);
			return sheet;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void montarCabecalhoExtrato(XSSFSheet sheet) {
		Row rowImagem = sheet.createRow(0);
		short tamanho = 1400;
		rowImagem.setHeight(tamanho);
		sheet.addMergedRegion(CellRangeAddress.valueOf("A1:G1"));
		//Cell cell = rowImagem.createCell(0);
		
		try {
			InputStream inputStream = getClass().getResourceAsStream("/logoFidenattoextrato.png");
			byte[] inputImageBytes1 = IOUtils.toByteArray(inputStream);
			int pictureureIdx = sheet.getWorkbook().addPicture(inputImageBytes1, Workbook.PICTURE_TYPE_PNG);
			inputStream.close();
			
			//insert picture anchored over the cells of the sheet
			  CreationHelper helper = sheet.getWorkbook().getCreationHelper();
			  Drawing drawing = sheet.createDrawingPatriarch();
			  ClientAnchor anchor = helper.createClientAnchor();
			  anchor.setCol1(0); //col A
			  anchor.setRow1(0); //row 1
			  Picture pict = drawing.createPicture(anchor, pictureureIdx);
			  pict.resize(); //now picture is anchored at A1 and sized to it's original size
			  
			//get picture's original size
			  int pictOriginalWidthInPixels = pict.getImageDimension().width;
			  int pictOriginalHeightInPixels = pict.getImageDimension().height;
			  
			//get height of row 1 to 4
			  float rowHeightInPixels = 0f;
			  for (int r = 0; r < 4; r++) {
				  rowImagem = sheet.getRow(r); if (rowImagem == null) rowImagem = sheet.createRow(r);
				  float rowHeightInPoints = rowImagem.getHeightInPoints(); 
				  rowHeightInPixels += rowHeightInPoints * Units.PIXEL_DPI / Units.POINT_DPI;
			  }
			  //we want scaling in aspect ratio
			  float scale = rowHeightInPixels*3 / pictOriginalHeightInPixels/ (8);
			  pict.resize(scale, scale); //now picture is resized to fit into the first 4 rows
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void montarDetalheCabecalho(XSSFSheet sheet, XSSFWorkbook workbook, int mes, int ano) {
		
		Short tamanhoFonte = 250;
		Row row = sheet.createRow(1);
		
		sheet.addMergedRegion(CellRangeAddress.valueOf("B2:C2"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("D2:G2"));
		Cell cell1 = row.createCell(0);
		Cell cell2 = row.createCell(1);
		Cell cell3 = row.createCell(3);
		cell1.setCellValue(ano);
		cell2.setCellValue(Util.getMes(mes));
		cell3.setCellValue("Contabilidade Fidenatto");
		cell1.setCellStyle(fonteNegritoXLS(workbook, tamanhoFonte, null));
		cell2.setCellStyle(fonteNegritoXLS(workbook, tamanhoFonte, null));
		cell3.setCellStyle(fonteNegritoXLS(workbook, tamanhoFonte, null));
	}

	public void montarHeaderBanco(XSSFSheet sheet, XSSFWorkbook workbook, String banco, int posicao) {
		Short tamanhoFonte = 270;
		Row row = sheet.createRow(posicao - 1);
		String posicao1 = "A" + posicao;
		String posicao2 = "G" + posicao;
		sheet.addMergedRegion(CellRangeAddress.valueOf(posicao1 + ":" + posicao2));
		Cell cell1 = row.createCell(0);
		cell1.setCellValue(banco);
		cell1.setCellStyle(fonteNegritoXLS(workbook, tamanhoFonte, IndexedColors.BLUE.index));
	}

	public void montarHeaderExtrato(XSSFSheet sheet, XSSFWorkbook workbook, int posicao) {
		sheet.setColumnWidth(0, 11 * 256);
		sheet.setColumnWidth(1, 11 * 256);
		sheet.setColumnWidth(2, 22 * 256);
		sheet.setColumnWidth(3, 12 * 256);
		sheet.setColumnWidth(4, 12 * 256);
		sheet.setColumnWidth(5, 50 * 256);
		sheet.setColumnWidth(6, 50 * 256);

		Short tamanhoFonte = 220;
		Row row = sheet.createRow(posicao - 1);

		Cell cell1 = row.createCell(0);
		Cell cell2 = row.createCell(1);
		Cell cell3 = row.createCell(2);
		Cell cell4 = row.createCell(3);
		Cell cell5 = row.createCell(4);
		Cell cell6 = row.createCell(5);
		Cell cell7 = row.createCell(6);

		cell1.setCellValue("Data");
		cell2.setCellValue("Nº Doc");
		cell3.setCellValue("Histórico");
		cell4.setCellValue("Valor");
		cell5.setCellValue("C/D");
		cell6.setCellValue("Nome do arquivo do comprovante");
		cell7.setCellValue("Descrição");

		cell1.setCellStyle(fonteNegritoXLS(workbook, tamanhoFonte, null));
		cell2.setCellStyle(fonteNegritoXLS(workbook, tamanhoFonte, null));
		cell3.setCellStyle(fonteNegritoXLS(workbook, tamanhoFonte, null));
		cell4.setCellStyle(fonteNegritoXLS(workbook, tamanhoFonte, null));
		cell5.setCellStyle(fonteNegritoXLS(workbook, tamanhoFonte, null));
		cell6.setCellStyle(fonteNegritoXLS(workbook, tamanhoFonte, null));
		cell7.setCellStyle(fonteNegritoXLS(workbook, tamanhoFonte, null));

	}

	public void montarHeaderCartao(XSSFSheet sheet, XSSFWorkbook workbook, int posicao) {
		String posicao1 = "D" + posicao;
		String posicao2 = "F" + posicao;
		sheet.addMergedRegion(CellRangeAddress.valueOf(posicao1 + ":" + posicao2));
		
		sheet.setColumnWidth(0, 11 * 256);
		sheet.setColumnWidth(1, 11 * 256);
		sheet.setColumnWidth(2, 22 * 256);
		sheet.setColumnWidth(3, 12 * 256);
		sheet.setColumnWidth(4, 12 * 256);
		sheet.setColumnWidth(5, 50 * 256);
		sheet.setColumnWidth(6, 50 * 256);

		Short tamanhoFonte = 220;
		Row row = sheet.createRow(posicao - 1);

		Cell cell1 = row.createCell(0);
		Cell cell2 = row.createCell(1);
		Cell cell3 = row.createCell(2);
		Cell cell4 = row.createCell(3);
		Cell cell5 = row.createCell(4);

		cell1.setCellValue("Data");
		cell2.setCellValue("Compra");
		cell3.setCellValue("Valor");
		cell4.setCellValue("Nota");
		cell5.setCellValue("Descrição");

		cell1.setCellStyle(fonteNegritoXLS(workbook, tamanhoFonte, null));
		cell2.setCellStyle(fonteNegritoXLS(workbook, tamanhoFonte, null));
		cell3.setCellStyle(fonteNegritoXLS(workbook, tamanhoFonte, null));
		cell4.setCellStyle(fonteNegritoXLS(workbook, tamanhoFonte, null));
		cell5.setCellStyle(fonteNegritoXLS(workbook, tamanhoFonte, null));

	}

	
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

	public static String dataFormatadaBanco(Date data) {
		if (data != null) {
			final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.format(data);
		} else {
			return null;
		}

	}

	public static String dataFormatadaBanco(int dia, int mes, int ano) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, dia);
		c.set(Calendar.MONTH, mes - 1);
		c.set(Calendar.YEAR, ano);
		final DateFormat df = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		return df.format(c.getTime());

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

	public static LocalDate retornaDataLocal(String dataString) throws ParseException {
		if (dataString != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
			SimpleDateFormat formatter2 = new SimpleDateFormat("ddMMyyyy");

			// String date = "16/08/2016";

			// LocalDate date = formatter.parse(dataString);

			LocalDate localDate = LocalDate.parse(dataString, formatter);
			return localDate;
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
		if (linha != null) {
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
