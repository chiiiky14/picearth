package com.example.touch.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;


public class PicearthGetLogin {

	public String idperfil, estado, username, lerrorMessage, token;
	public int lerrorCode;

	public void login(String correo, String contrasena)
	{ 

	try{
		HttpParams httpParameters = new BasicHttpParams(); //parametros para la conexion

		int timeoutConnection = 5000;
		HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection); // mucho se tarda al conectar

		int timeoutSocket = 5000;
		HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);// no encontro la conexion

		HttpClient client = new DefaultHttpClient(httpParameters); //llamar metodo para abrir el cliente

		client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "android");//protocolos del cliente
		HttpGet request = new HttpGet(); // tipo de metodo de consumo ya sea get, post, delete,put 
		request.setHeader("Content-Type", "application/json; charset=utf-8");// setear el header del json
	    request.setURI(new URI("http://epicearth.buzzcoapp.com/epicearthApiREST/v1/getLogin/"+correo+"/"+contrasena));
		// setear la url donde esta alojado el servicio

		HttpResponse response = client.execute(request); // obtener la respuesta
		BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));// obtener la entidad de lo qe contiene

		StringBuffer sb = new StringBuffer("");
		String line = "";

		String NL = System.getProperty("line.separator");
		while ((line = in.readLine()) != null) {
			sb.append(line + NL);
		}
		in.close();
		String page = sb.toString(); //variable string qe va separado por elementos para qe venga ordenado

		JSONObject jsonObject 	= new JSONObject(page); // obetenr el contenido en un objeto
 
		
		try {
			lerrorCode				= jsonObject.getInt("errorCode");
			if (lerrorCode == 0) {
				JSONObject nom 	= jsonObject.getJSONObject("response");
				idperfil		= nom.getString("name");
				estado			= nom.getString("mail");
				username 		= nom.getString("token"); 

			}

		} catch (Exception e) {
			// TODO: handle exception
			lerrorCode = 1;
		}
		

  
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
 
	}



}