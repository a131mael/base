package br.com.aaf.base.whats.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.aaf.base.base.Constantes;
import br.com.aaf.base.base.RetornoEnvioWhats;
import br.com.aaf.base.comunicadores.EnviadorJson;

public class MensagensWatiUtilitario {

	static ObjectMapper mapper = new ObjectMapper();
	@PostConstruct
	public void init() {
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
	}

	EnviadorJson enviadorJson = new EnviadorJson();
	
//	public static void main(String[] args) {
//		MensagensWatiUtilitario m = new MensagensWatiUtilitario();
//		m.getMensagens("5548996498528");
//	}

	public List<MensagemWhatsapp> getMensagens(String telefone) {
		try {
			mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			String endpoint = Constantes.getMensagensPorNumero;
			Parametro p1 = new Parametro("whatsappNumber", normalizarTelefoneParaWhats(telefone));
			List<Parametro> parametros = new ArrayList<>();
			parametros.add(p1);
			String retornoJson = EnviadorJson.get(endpoint, Constantes.TOKEN, parametros);
			RetornoMensagemWhatsappWati retorno;
			retorno = mapper.readValue(retornoJson, RetornoMensagemWhatsappWati.class);
			if ("success".equalsIgnoreCase(retorno.getResult())) {
				List<MensagemWhatsapp> mensagens = Arrays.asList(retorno.getMessages().get(0).getItems());
				Collections.sort(mensagens, Comparator.comparing(MensagemWhatsapp::getCreated));

				boolean atual = true;
				for (MensagemWhatsapp mensagem : mensagens) {
					
					mensagem.setPrimeiro(atual);
					
					if (atual != mensagem.isOwner()) {
						System.out.println("-----------------");
					}
					atual = mensagem.isOwner();
					if ("broadcastMessage".equalsIgnoreCase(mensagem.getEventType())) {
						System.out.println(mensagem.getFinalText());
					} else if (!"ticket".equalsIgnoreCase(mensagem.getEventType())) {
						System.out.println(mensagem.getText());
					}
				}
				return mensagens;
			}

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String montarBodyMensagemOi(String telefone) {
		try {
			List<Parametro> parametros = new ArrayList<Parametro>();
			//parametros.add(new Parametro("1", getNomeResponsavelDevedor(aluno, contrato.getAno())));
			//parametros.add(new Parametro("2", contrato.getMesesAberto()));
			
			MensagemTemplateWhats mensagem = new MensagemTemplateWhats();
			mensagem.setTemplate_name("boa_tarde");
			//mensagem.setParameters(parametros);

			String bodyJson = mapper.writeValueAsString(mensagem);

			return bodyJson;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public boolean enviarOi(String telefone) {
		System.out.println("Enviando Mensagem de whats para " + telefone);
		try {
			List<Parametro> parametros = new ArrayList<Parametro>();
			parametros.add(new Parametro("whatsappNumber", normalizarTelefoneParaWhats(telefone)));

			String jsonRetorno = EnviadorJson.post(Constantes.postEnviarMensagemPorTemplate, Constantes.TOKEN,	montarBodyMensagemOi(normalizarTelefoneParaWhats(telefone)), parametros);
			System.out.println(jsonRetorno);
			RetornoEnvioWhats retorno = mapper.readValue(jsonRetorno, RetornoEnvioWhats.class);

			if (!retorno.isValidWhatsAppNumber()) {
				return retorno.isValidWhatsAppNumber();
			}

			return retorno.isResult();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	private static String normalizarTelefoneParaWhats(String telefone) {
		if (telefone == null) {
			return "";
		}

		telefone = telefone.replace(" ", "");
		telefone = telefone.replace("-", "");
		telefone = telefone.replace(".", "");
		telefone = telefone.replace("(", "");
		telefone = telefone.replace(")", "");

		if (telefone.length() < 8) {
			return "";
		}

		int tamanhoNumero = telefone.length();
		int primeironumero = Integer.valueOf(String.valueOf(telefone.charAt(0)));
		int terceironumero = Integer.valueOf(String.valueOf(telefone.charAt(2)));
		int quartonumero = Integer.valueOf(String.valueOf(telefone.charAt(3)));
		String tresPrimeiros = telefone.substring(0, 3);
		String doisPrimeiros = telefone.substring(0, 2);

		switch (tamanhoNumero) {
		case 8:

			// telefone fixo
			if (primeironumero != 7 && primeironumero != 8 && primeironumero != 9) {
				return "";
			} else {
				return telefone = "55489" + telefone;
			}

		case 9:
			return telefone = "5548" + telefone;

		case 10:

			// telefone fixo com dd de 2 numeros
			if ((terceironumero != 7 || terceironumero != 8 || terceironumero != 9)) {
				return "";
			} else {
				String dd = telefone.substring(0, 2);
				String foneFInal = "55" + dd + "9" + telefone.substring(2);

				return foneFInal;
			}

		case 11:
			if (tresPrimeiros.equalsIgnoreCase("048")) {

				if ((quartonumero == 8 || quartonumero == 9 || quartonumero == 7)) {
					return "55489" + telefone.substring(4);
				} else {
					return "";
				}

			} else {
				return telefone = "55" + telefone;
			}

		case 12:
			return "55" + telefone.substring(1);

		case 13:
			return telefone;

		default:
			return "";

		}
	}

}
