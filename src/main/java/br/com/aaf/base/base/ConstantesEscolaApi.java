package br.com.aaf.base.base;

public class ConstantesEscolaApi {

	public static String TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiIyZjRlMGFkYi0xNzJlLTQxODAtODhhYi1jNmRkYmY0Y2IzMjIiLCJ1bmlxdWVfbmFtZSI6ImExMzFtYWVsQGdtYWlsLmNvbSIsIm5hbWVpZCI6ImExMzFtYWVsQGdtYWlsLmNvbSIsImVtYWlsIjoiYTEzMW1hZWxAZ21haWwuY29tIiwiYXV0aF90aW1lIjoiMDMvMTcvMjAyMiAxNDo1NDo0MyIsImRiX25hbWUiOiI4Mjg5IiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoiQURNSU5JU1RSQVRPUiIsImV4cCI6MjUzNDAyMzAwODAwLCJpc3MiOiJDbGFyZV9BSSIsImF1ZCI6IkNsYXJlX0FJIn0.frZFJ9mfOA9XpaNKqu6u1aLFFef07dYHZElQiDacyOw";
	public static String URL = "http://servidoradonai.ddns.net:1414";
	public static String URL_CARTORIO = "http://servidoradonai.ddns.net:2020";
	//public static String URL = "http://localhost:1414";
	
	
//	ENDPOINTS
	
	public static String getAlunosAvaliacao = "/alunoavaliacao/api/alunosavaliacoes";
	public static String postEnviarMensagemPorTemplate = "/api/v1/sendTemplateMessage";

	public static String ProtestoCartorio = "/integracao/api/enviarContratoProtesto";
	
	public static String STATATUS_CONTRATO = "/integracao/api/recuperarStatus";
	
	public static String FINALIZAR_ANO_LETIVO = "/servicesEscola/api/anoLetivoFinalizado";
	
}
