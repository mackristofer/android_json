package br.exemplojsonparser;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpConnection {//classe para conexao com servidor web, sera passado tanto get quanto post por essa classe, que vai processar no servidor o json
	public static String getSetDataWeb(String url, String method, String data){//metodo que estabelece a conexao e retorna um string
		
		HttpClient httpClient = new DefaultHttpClient();//cria objeto httpclient, e pega o DefaultHttpClient
		HttpPost httpPost = new HttpPost(url);//cria objeto post, ele e responsavel por passar a url da pagina que processa nosso script
		String answer = "";//string de retorno, inicia vazia.
		
		try{//o httpclient e HttpPost necessitam de um try
			ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();//cria um arraylis para formar o restante da string que deve ser passada para o webservice
			valores.add(new BasicNameValuePair("method", method));//add o method a method que sera interpretado no php
			valores.add(new BasicNameValuePair("json", data));//add data a json que vai ser interpretado no php
			
			httpPost.setEntity(new UrlEncodedFormEntity(valores));//chma o metro setEntity de HttpPost recebendo a UrlEncodedFormEntity com os method e json para enviar para o servidor
			HttpResponse resposta = httpClient.execute(httpPost);//obtem a resposta do servidor
			answer = EntityUtils.toString(resposta.getEntity());//carrega resposta para answer
		}
		catch(NullPointerException e){ e.printStackTrace(); }
		catch(ClientProtocolException e){ e.printStackTrace(); }
		catch(IOException e){ e.printStackTrace(); }
		
		return(answer);//se deu tudo certo retorna answer
	}
}
