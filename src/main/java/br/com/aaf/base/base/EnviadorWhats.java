package br.com.aaf.base.base;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.aaf.base.comunicadores.IntegradorRest;
import br.com.aaf.base.whats.model.MensagemTemplateWhats;
import br.com.aaf.base.whats.model.Parametro;

public class EnviadorWhats {

	static ObjectMapper mapper = new ObjectMapper();

	public static boolean enviarWhats(String nomeTemplate, String telefone, List<Parametro> parametros) {
		System.out.println("Enviando Mensagem de whats para " + telefone);
		try {

			IntegradorRest.aceptallSSL();
			CloseableHttpClient httpclient = IntegradorRest.getHttpCliente();
			// PARAMETROS
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			postParameters.add(new BasicNameValuePair("whatsappNumber", normalizarTelefoneParaWhats(telefone)));

			URIBuilder uriBuilder = new URIBuilder(Constantes.URL + Constantes.postEnviarMensagemPorTemplate);
			uriBuilder.addParameters(postParameters);

			HttpPost httppost = new HttpPost(uriBuilder.build());

			httppost.setHeader("Content-Type", "application/json");
			httppost.setHeader("Authorization", Constantes.TOKEN);

			// BODY POST
			MensagemTemplateWhats mensagem = new MensagemTemplateWhats();
			mensagem.setTemplate_name(nomeTemplate);
			mensagem.setParameters(parametros);

			String jsonString = mapper.writeValueAsString(mensagem);

			HttpEntity stringEntity = new StringEntity(jsonString, ContentType.APPLICATION_JSON);
			httppost.setEntity(stringEntity);
			CloseableHttpResponse response;
			response = httpclient.execute(httppost);

			System.out.println(response.getStatusLine().getStatusCode());
			String jsonRetorno = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println(jsonRetorno);
			RetornoEnvioWhats retorno = mapper.readValue(jsonRetorno, RetornoEnvioWhats.class);

			if (!retorno.isValidWhatsAppNumber()) {
				return retorno.isValidWhatsAppNumber();
			}

			if (response.getStatusLine().getStatusCode() >= 200 && response.getStatusLine().getStatusCode() < 300) {
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public static boolean enviarWhats(String nomeTemplate, List<String> telefones, List<Parametro> parametros) {
		for (String telefone : telefones) {
			enviarWhats(nomeTemplate, telefone, parametros);
		}
		return true;
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

		if(telefone.length() < 8){
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
			}else{
				String dd = telefone.substring(0, 2);
				String foneFInal = "55" + dd + "9" + telefone.substring(2);

				return foneFInal;
			}

		case 11 :
			if(tresPrimeiros.equalsIgnoreCase("048")){
			
				if ( (quartonumero == 8	|| quartonumero == 9	|| quartonumero == 7)) {
					return "55489" + telefone.substring(4);
				}else{
					return "";
				}
				
			}else{
				return telefone = "55" + telefone;
			}
			
		case 12 :
			return "55"+telefone.substring(1);
						
		case 13 :
			return telefone;
		
			
		default:
			return "";
		}
		


	}

}
