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

public class GetComentario {

	BufferedReader in = null;
	String url = "http://observatoriodeprecios.defensoria.gob.sv/ApiREST.php/v1/getListaDeComprasProductos/";

	public Vector<String> Nombre 		= new Vector<String>();
	public Vector<String> Comentario 	= new Vector<String>();

	public int result;

	public void GetComentario(String id) {

		

		try {
			HttpClient client = new DefaultHttpClient(timeOut(5000, 5000));

			client.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
					"android");
			HttpGet request = new HttpGet();
			request.setHeader("Content-Type", "application/json; charset=utf-8");
			Log.i("Parameters", id);
			request.setURI(new URI(url + id ));

			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			String page = sb.toString();
			Log.i("PAGE", "" + page);
			JSONObject jsonObject = new JSONObject(page);
			int error = jsonObject.getInt("errorCode");

			
			if (error == 0) {
				
				Object intervention = jsonObject.get("response");
				
				if (intervention instanceof JSONObject) {
					
					JSONObject Json = jsonObject.getJSONObject("response");
					Nombre.addElement(Json.getString("name"));
					Comentario.addElement(Json.getString("comment"));
					
				} else if (intervention instanceof JSONArray) {
					
					JSONArray ListaComentarios = jsonObject.getJSONArray("response");
					
					for (int i = 0; i < ListaComentarios.length(); i++) {
						
						JSONObject json_data = ListaComentarios.getJSONObject(i);
						Nombre.addElement(json_data.getString("name"));
						Comentario.addElement(json_data.getString("comment"));
					}
				}
				result = 0;
			} else {
				result = 1;
			}

		} catch (URISyntaxException e) {
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
		} finally {

		}
		// return categoria;
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
