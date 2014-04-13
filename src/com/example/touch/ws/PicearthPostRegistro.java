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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
 

public class PicearthPostRegistro {
	
	BufferedReader in = null;
	String url = "http://epicearth.buzzcoapp.com/epicearthApiREST/v1/postregister";
	public int result;
	long idMiembro;
	
	public void PostGuardarRegistro(String name, String lastname, String mail, String nationality, String Password){
		
		try {
						
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name"				, name);
		jsonObj.put("lastname"			, lastname);
		jsonObj.put("mail"				, mail);
		jsonObj.put("nationality"		, nationality);
		jsonObj.put("Password"			, Password);
		
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
		Log.i("PAGE", ""+page);
		/*JSONObject jsonObject = new JSONObject(page);
		JSONObject msgJson = jsonObject.getJSONObject("response");
		int error = msgJson.getInt("errorCode");		
		if(error == 0){ 
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
		}*/
		
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result = 9;
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result = 9;
			e.printStackTrace();
		} finally{
			
		}
	
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
