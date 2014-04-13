package com.example.touch.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class PostComentario {
	
	BufferedReader in = null;
	String url = "http://observatoriodeprecios.defensoria.gob.sv/ApiREST.php/v1/postListaDeCompras";
	int result;
	public String id,token,text;
	
	public PostComentario(String hashKey, String token, String text){
		this.id 	= hashKey;
		this.token 	= token;
		this.text 	= text;
	}
	
	public int PostListaCompras(){
		
		try {
						
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("id"	, id);
		jsonObj.put("token"	, token);
		jsonObj.put("text"	, text);
			
		// Create the POST object and add the parameters
		HttpPost httpPost = new HttpPost(url);

		StringEntity entity;
	
		entity = new StringEntity(jsonObj.toString(), HTTP.UTF_8);
		
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		HttpClient client = new DefaultHttpClient(timeOut(5000, 5000));

		HttpResponse response = client.execute(httpPost);
		in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer sb = new StringBuffer("");
		String line = "";

		String NL = System.getProperty("line.separator");
		while ((line = in.readLine()) != null) {
			sb.append(line + NL);
		}
		in.close();
		String page = sb.toString();
		JSONObject jsonObject = new JSONObject(page);
		Log.i("PAGE", ""+page);
		//JSONObject msgJson = jsonObject.getJSONObject("errorCode");
		int error = jsonObject.getInt("errorCode");
		if(error == 0){
			result = 0;
		}else if(error == 1){
			result = 1;
		}
		
		/*if(error == 0){		
			JSONObject Json = msgJson.getJSONObject("msg");
			idlista = Json.getString("idLista");
			result = 0;
		}else if(error == 1){
			result = 1;
		}else if(error == 2){
			result = 2;
		}else if(error == 3){
			result = 3;
		}else if(error == 4){
			result = 4;
		}else if(error == 5){
			result = 5;
		}else if(error == 6){
			result = 6;
		}else if(error == 7){
			result = 7;
		}else if(error == 8){
			result = 8;
		}
		
		*/
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result = 2;
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result = 3;
			e.printStackTrace();
		} finally{
			
		}
		return result;
	}
	
	public HttpParams timeOut(int timeout, int timesocket) {

		HttpParams httpParameters = new BasicHttpParams();

		int timeoutConnection = timeout;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);

		int timeoutSocket = timesocket;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		return httpParameters;
	}

}
