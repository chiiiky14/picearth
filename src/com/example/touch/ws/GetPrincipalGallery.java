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

public class GetPrincipalGallery {

	BufferedReader in = null;
	String url = "http://epicearth.buzzcoapp.com/epicearthApiREST/v1/getGallery";

	public Vector<String> idPublicacion	= new Vector<String>();
	public Vector<String> name 			= new Vector<String>();
	public Vector<String> date 			= new Vector<String>();
	public Vector<String> description 	= new Vector<String>();
	public Vector<String> image 		= new Vector<String>();
	public Vector<String> author 		= new Vector<String>();
	public Vector<String> like 			= new Vector<String>();
	public Vector<String> social 		= new Vector<String>();
	public Vector<String> ranking 		= new Vector<String>();
	public Vector<String> location 		= new Vector<String>();
	public Vector<String> user 			= new Vector<String>();
	public Vector<String> region 		= new Vector<String>();

	public int result;

	public void GetComentario(String id) {


		

		try {
			HttpClient client = new DefaultHttpClient(timeOut(5000, 5000));

			client.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
					"android");
			HttpGet request = new HttpGet();
			request.setHeader("Content-Type", "application/json; charset=utf-8");
			request.setURI(new URI(url ));

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
					idPublicacion.addElement(Json.getString("idgaleria"));
					name.addElement(Json.getString("name"));
					date.addElement(Json.getString("date"));
					description.addElement(Json.getString("description"));
					image.addElement(Json.getString("image"));
					author.addElement(Json.getString("author"));
					social.addElement(Json.getString("social"));
					like.addElement(Json.getString("like"));
					ranking.addElement(Json.getString("ranking"));
					location.addElement(Json.getString("location"));
					user.addElement(Json.getString("user"));
					region.addElement(Json.getString("region"));
					
				} else if (intervention instanceof JSONArray) {
					
					JSONArray ListaComentarios = jsonObject.getJSONArray("response");
					
					for (int i = 0; i < ListaComentarios.length(); i++) {
						/**
						 * 	public Vector<String> name 			= new Vector<String>();
	public Vector<String> date 			= new Vector<String>();
	public Vector<String> description 	= new Vector<String>();
	public Vector<String> image 		= new Vector<String>();
	public Vector<String> author 		= new Vector<String>();
	public Vector<String> like 			= new Vector<String>();
	public Vector<String> social 		= new Vector<String>();
	public Vector<String> ranking 		= new Vector<String>();
	public Vector<String> location 		= new Vector<String>();
	public Vector<String> user 			= new Vector<String>();
	public Vector<String> region 		= new Vector<String>();
	*/
						JSONObject json_data = ListaComentarios.getJSONObject(i);
						idPublicacion.addElement(json_data.getString("idgaleria"));
						name.addElement(json_data.getString("name"));
						date.addElement(json_data.getString("date"));
						description.addElement(json_data.getString("description"));
						image.addElement(json_data.getString("image"));
						author.addElement(json_data.getString("author"));
						social.addElement(json_data.getString("social"));
						like.addElement(json_data.getString("like"));
						ranking.addElement(json_data.getString("ranking"));
						location.addElement(json_data.getString("location"));
						user.addElement(json_data.getString("user"));
						region.addElement(json_data.getString("region"));
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
