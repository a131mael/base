package br.com.aaf.base.comunicadores;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;

public class IntegradorRest {
	
	
	public static void aceptallSSL() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}

			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// TODO Auto-generated method stub
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// TODO Auto-generated method stub

			}

		} };

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory())  ;
		} catch (Exception e) {
		}
//		// Now you can access an https URL without having the certificate in the
//		// truststore
//		try {
//			URL url = new URL("https://hostname/index.html");
//		} catch (MalformedURLException e) {
//		}
	}
	
	public static CloseableHttpClient getHttpCliente(){
		 try {
			 TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
			SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
			Registry<ConnectionSocketFactory> socketFactoryRegistry = 
				      RegistryBuilder.<ConnectionSocketFactory> create()
				      .register("https", sslsf)
				      .register("http", new PlainConnectionSocketFactory())
				      .build();
			BasicHttpClientConnectionManager connectionManager = 
				      new BasicHttpClientConnectionManager(socketFactoryRegistry);
			 CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
				      .setConnectionManager(connectionManager).build();
			 
			 return httpClient;

			
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public synchronized boolean enviarNFS(String localNomeNotaGerada, String login, String senha) {
		System.out.println("ENVIANDO NOTAS FISCAIS  ........");

		aceptallSSL();
		
//		try {
//			//DefaultHttpClient httpclient = new DefaultHttpClient();
//			CloseableHttpClient httpclient = getHttpCliente();
//		//	HttpPost httppost = new HttpPost(Contants.URL);
//			httppost.setHeader("Authorization",	"Basic " + new String(java.util.Base64.getEncoder().encode((login + ":" + senha).getBytes())));
//			 File file = new File(localNomeNotaGerada);
//			 HttpEntity entity = MultipartEntityBuilder.create()
//                     .addPart("f1", new FileBody(file))
//                     .build();
//			 httppost.setEntity(entity);
//			 
////			 MultipartEntity mpEntity = new
//	//		 MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
//		//	 ContentBody cbFile = new FileBody(file);
//			// mpEntity.addPart("Content-Type", cbFile);
//			// mpEntity.addPart("Authorization", new StringBody ("Basic " + new
//			// String(java.util.Base64.getEncoder().encode((login+":"+senha).getBytes()))));
//			// mpEntity.addPart("senha", new StringBody(senha));
//			// mpEntity.addPart("cidade", new StringBody("8223"));
//			// httppost.setEntity(mpEntity);
//			System.out.println("executing request " + httppost.getRequestLine());
//			System.out.println("Now uploading your file into uploadbox.com");
//			HttpResponse response = httpclient.execute(httppost);
//			System.out.println(response.getStatusLine().getStatusCode());
//			System.out.println(EntityUtils.toString(response.getEntity(), "UTF-8"));
//			
//			if (response.getStatusLine().getStatusCode() >= 200 && response.getStatusLine().getStatusCode() < 300) {
//				return true;
//			}
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return false;
	}
	
	
}
