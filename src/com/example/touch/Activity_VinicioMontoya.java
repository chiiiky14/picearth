package com.example.touch;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.touch.ws.PicearthGetLogin;

public class Activity_VinicioMontoya extends Activity {
    /** Called when the activity is first created. */
	PicearthGetLogin getLogin;
	String name, lastname, mail, nationality, Password;
	EditText etSugerencia;
	String stSugerencia;
	int error;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viniciomontoya_activity);
         
    }
}