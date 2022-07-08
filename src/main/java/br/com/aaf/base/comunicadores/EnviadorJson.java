package br.com.aaf.base.comunicadores;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.aaf.base.base.Constantes;
import br.com.aaf.base.base.RetornoEnvioWhats;
import br.com.aaf.base.whats.model.MensagemWhatsapp;
import br.com.aaf.base.whats.model.MensagensWatiUtilitario;
import br.com.aaf.base.whats.model.Parametro;
import br.com.aaf.base.whats.model.RetornoMensagemWhatsappWati;

public class EnviadorJson {

	static ObjectMapper mapper = new ObjectMapper();

	@PostConstruct
	public void init() {
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
	}

	public static String post(String endpoint, String token, String bodyJson, List<Parametro> parametrosPost) {
		try {
			IntegradorRest.aceptallSSL();
			mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			CloseableHttpClient httpclient = IntegradorRest.getHttpCliente();
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			for (Parametro param : parametrosPost) {
				postParameters.add(new BasicNameValuePair(param.getName(), param.getValue()));
			}
			URIBuilder uriBuilder = new URIBuilder(Constantes.URL+endpoint);
			uriBuilder.addParameters(postParameters);

			HttpPost httppost = new HttpPost(uriBuilder.build());
			httppost.setHeader("Content-Type", "application/json");
			httppost.setHeader("Authorization", token);

			HttpEntity stringEntity = new StringEntity(bodyJson, ContentType.APPLICATION_JSON);
			httppost.setEntity(stringEntity);
			CloseableHttpResponse response;
			response = httpclient.execute(httppost);

			System.out.println(response.getStatusLine().getStatusCode());
			String jsonRetorno = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println(jsonRetorno);

			return jsonRetorno;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public static String get(String endpoint, String token, List<Parametro> parametros) {
		try {
			mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			IntegradorRest.aceptallSSL();

			CloseableHttpClient httpclient = IntegradorRest.getHttpCliente();
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			for (Parametro param : parametros) {
				endpoint += param.getValue();
				// postParameters.add(new BasicNameValuePair(param.getName(),
				// param.getValue()));
			}

			URIBuilder uriBuilder = new URIBuilder(Constantes.URL+endpoint);
			uriBuilder.addParameters(postParameters);

			HttpGet httpget = new HttpGet(uriBuilder.build());
			httpget.setHeader("Content-Type", "application/json");
			httpget.setHeader("Authorization", token);

			CloseableHttpResponse response = httpclient.execute(httpget);

			System.out.println(response.getStatusLine().getStatusCode());
			String jsonRetorno = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println(jsonRetorno);

			return jsonRetorno;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}
	
	public static String get2(String endpoint, String token, List<Parametro> parametros) {
		try {
			mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			IntegradorRest.aceptallSSL();

			CloseableHttpClient httpclient = IntegradorRest.getHttpCliente();
		//	ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			for (Parametro param : parametros) {
				if(endpoint.contains("?")){
					endpoint += "&";
				}else{
					endpoint += "?";
				}
				endpoint += param.getName();
				endpoint += "=";
				endpoint += param.getValue();
				// postParameters.add(new BasicNameValuePair(param.getName(),
				// param.getValue()));
			}

			URIBuilder uriBuilder = new URIBuilder(endpoint);
		//	uriBuilder.addParameters(postParameters);

			HttpGet httpget = new HttpGet(uriBuilder.build());
	//		httpget.setHeader("Content-Type", "application/json");
	//		httpget.setHeader("Authorization", token);

			CloseableHttpResponse response = httpclient.execute(httpget);

			System.out.println(response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode() == 200){
				String jsonRetorno = EntityUtils.toString(response.getEntity(), "UTF-8");
				return jsonRetorno;
			}else{
				return "";	
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public static void main(String[] args) {
		MensagensWatiUtilitario enviador = new MensagensWatiUtilitario();
		enviador.getMensagens("5551995403269");
	}

	
}
