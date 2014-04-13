package com.example.touch.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
 
public class PicearthGetLogin {
	
	BufferedReader in = null;
	String url = "";
	
	public Vector<Long> IdCategoria = new Vector<Long>();
	public Vector<String> Categoria = new Vector<String>();
	public Vector<String> ImagenUrl = new Vector<String>();
	
	public int result;
	
	public void GetLogin(String flag){ 
		try {
			HttpClient client = new DefaultHttpClient(timeOut(5000,5000));
	
			client.getParams().setParameter(CoreProtocolPNames.USER_AGENT,"android");
			HttpGet request = new HttpGet();
			request.setHeader("Content-Type", "application/json; charset=utf-8");
	
			request.setURI(new URI(url+flag));
			
			HttpResponse response = client.execute(request);
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
			JSONObject msgJson = jsonObject.getJSONObject("response");
			int error = msgJson.getInt("errorCode");
			if(error == 0){
				
				Object intervention = msgJson.get("msg");
				if (intervention instanceof JSONObject) {
					JSONObject Json = msgJson.getJSONObject("msg");
					IdCategoria.addElement(Json.getLong("idCategoria"));
					Categoria.addElement(Json.getString("categoria"));
					ImagenUrl.addElement(Json.getString("imageUrl"));
				}else if (intervention instanceof JSONArray){
					JSONArray ListaCategoriasJson = msgJson.getJSONArray("msg");
					for (int i = 0; i < ListaCategoriasJson.length(); i++) {
						JSONObject json_data = ListaCategoriasJson.getJSONObject(i);
						IdCategoria.addElement(json_data.getLong("idCategoria"));
						Categoria.addElement(json_data.getString("categoria"));
						ImagenUrl.addElement(json_data.getString("imageUrl"));
					}
				}
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
			
		
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			Log.i("catch", "1");
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Log.i("catch", "2");

			e.printStackTrace();
		} catch (IOException e) {
			// ERROR DEL SOCKET TIMEOUT
			Log.i("catch", "3");
			result = 9;
			e.printStackTrace();
		} catch (JSONException e) {
			// ERROR DEL JSON DOCTYPE!
			Log.i("catch", "4");
			result = 9;
			e.printStackTrace();
		}finally{
			
		}
	 
	//	return categoria;
	}
	
	public HttpParams timeOut(int timeout, int timesocket) {

		HttpParams httpParameters = new BasicHttpParams();

		int timeoutConnection = timeout;
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				timeoutConnection);

		int timeoutSocket = timesocket;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

		return httpParameters;
	}

}
