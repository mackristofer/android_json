package br.exemplojsonparser;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	
	public void sendJson(View view){//metodo do botao sendjson
		
		Carro carro = new Carro();//cria um objeto carro que sera enviado por json para o servidor php
		carro.setMarca("FIAT");//seta a marca do carro
		carro.setModelo("Palio");//seta o modelo do carro
		carro.setPotencias(new ArrayList<Potencia>());//seta a potencia, passando uma lista da class potencia
		carro.getPotencias().add(new Potencia(1.0f, 60));//add valor a classe potencia
		carro.getPotencias().add(new Potencia(1.5f, 80));//add novo valor a classe potencia
		carro.getPotencias().add(new Potencia(2.0f, 100));//add mais um valor a classe potencia
		
		String json = generateJSON(carro);//chma o motodo generateJSON passando um objeto carro para gerar o json que sera enviado para o servidor php
		
		callServer("send-json", json);//chama call server, que usa HttpConnection para fazer a conexao com o servidor
	}
	
	
	public void getJson(View view){//botao get json, objeto sera criado la no servidor e sera processado no app
		callServer("get-json", "");//usa o callServer para receber o json vindo do php
	}
	
	
	private String generateJSON(Carro carro){//metodo que cria o json, recebe um objeto carro e retorna uma string
		JSONObject jo = new JSONObject();//cria um objeto json, ele que faz toda a magica, n me pergunte o que ele faz, eu nao aprofundei
		JSONArray ja = new JSONArray();//cria um objeto json array, ele sera tratado pelo objeto json, nele que passaremos a lista de potencia
		
		try{
			jo.put("marca", carro.getMarca());//objeto jo pega a marca do carro
			jo.put("modelo", carro.getModelo());//objeto jo pega o modelo do carro
			
			for(int i = 0, tam = carro.getPotencias().size(); i < tam; i++){//loop que tem como limite o tamanho do array passado para potencia
				JSONObject aux =  new JSONObject();//cria um novo json objeto auxiliar para receber a potencia
				aux.put("motor", carro.getPotencias().get(i).getMotor());//objeto aux pega moto do carro corrido pelo vetor com o metodo get(i)
				aux.put("cavalos", carro.getPotencias().get(i).getCavalos());//objeto aux pega cavalos do carro corrido pelo vetor com o metodo get(i)
				ja.put(aux);
			}
			
			jo.put("potencias", ja);//adiciona em jo as postencias, ele que sera convertido
		}
		catch(JSONException e){ e.printStackTrace(); }
		
		return(jo.toString());//retorna jo como string
	}
	
	
	private Carro degenerateJSON(String data){//metodo que recria o objeto json que vem do servidor php
		Carro carro = new Carro();//cria um novo objeto carro para pode receber as infos do servidor
		
		try{//maniputlar objeto json tem que ser dentro de um try
			JSONObject jo = new JSONObject(data);//cria objeto json, passando para seu construtor uma string que virar do php
			JSONArray ja;//cria um json array para recuperar as potencias
			
			carro.setMarca(jo.getString("marca"));//seta marca do carro com o valor de jo que veio do php
			carro.setModelo(jo.getString("modelo"));//seta modelo do carro com o valor de jo que veio do php
			carro.setPotencias(new ArrayList<Potencia>());//seta a potencia do carro passando uma array de potencia
			
			//jo.put("marca", carro.getMarca());
			//jo.put("modelo", carro.getModelo());
			
			ja = jo.getJSONArray("potencias");//recebe o array e guarda no objeto json array
			for(int i = 0, tam = ja.length(); i < tam; i++){//corre o vetor de ja
				
				Potencia p = new Potencia();//cria um objeto potencia
				p.setMotor(ja.getJSONObject(i).getDouble("motor"));//adiciona o valor de motor vindo do php no indice get(i) 
				p.setCavalos(ja.getJSONObject(i).getInt("cavalos"));//adiciona o valor de cavalos vindo do php no indice get(i)
				
				carro.getPotencias().add(p);//adiciona a potencia ao carro
			}
			
			// APRESENTAÇÃO
				Log.i("Script", "Marca: "+carro.getMarca());
				Log.i("Script", "Modelo: "+carro.getModelo());
				for(int i = 0, tam = carro.getPotencias().size(); i < tam; i++){
					Log.i("Script", "Motor: "+carro.getPotencias().get(i).getMotor());
					Log.i("Script", "Cavalos: "+carro.getPotencias().get(i).getCavalos());
				}
		
		}
		catch(JSONException e){ e.printStackTrace(); }
		
		return(carro);//retorna um objeto carro com os dados que vieram do php
	}
	
	
	
	@SuppressLint("NewApi")
	private void callServer(final String method, final String data){//metodo que passa o metodo e uma string para o servidor, a url ja esta estatica
		new Thread(){//o envio e recebimento do nosso objeto com o php deve ser feito em uma nova thread
			public void run(){//void run para rodar a thread
				String answer = HttpConnection.getSetDataWeb("http://www.villopim.com.br/android/json/process.php", method, data);//string que recebe resultado do acesso ao php, se for criar um json ele retorna 1.
				
				Log.i("Script", "ANSWER: "+answer);
				
				if(data.isEmpty()){//checa se foi passado algum valor, se n foi significa que vc esta recebendo dados do servidor, o motodo abaixo foi usado para montar o objeto de acordo com o json vindo do servidor
					degenerateJSON(answer);//metedo que ler json do servidor e cria o objeto no app
				}
			}
		}.start();//chama a thread
	}
	
}
