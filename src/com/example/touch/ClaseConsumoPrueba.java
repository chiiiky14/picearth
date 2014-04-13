package com.example.touch;
 

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;

import com.example.touch.ws.PicearthPostRegistro;


public class ClaseConsumoPrueba extends Activity {
    /** Called when the activity is first created. */
	PicearthPostRegistro postRegistro;
	String name, lastname, mail, nationality, Password;
	EditText etSugerencia;
	String stSugerencia;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumo);
        
        
        
        name = "jose";
        lastname = "de la O";
        mail = "chikyd.14@gmail.com";
        nationality = "SV";
        Password = "123456";
        
        new GetInfo	(name, lastname, mail, nationality, Password).execute();
         
    }
    
    private class GetInfo extends AsyncTask<Void, Void, Void> {

	 
		String name, lastname, mail, nationality, Password;

		public GetInfo(String name, String lastname, String mail, String nationality, String Password) {
			 this.name 			= name;
			 this.lastname		= lastname;
			 this.mail			= mail;
			 this.nationality	= nationality;
			 this.Password		= Password;
		}

		@Override
		protected void onPreExecute() {
			try {
				/*loading = new ProgressDialog(ClaseConsumoPrueba.this);
				loading.setMessage("Cargando");
				loading.setCancelable(false);
				loading.show();*/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		protected Void doInBackground(Void... arg0) {

			try {
				postRegistro = new PicearthPostRegistro();
				postRegistro.PostGuardarRegistro(name, lastname, mail,
						nationality, Password);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			 
			
			

		}

	}
    
    
}