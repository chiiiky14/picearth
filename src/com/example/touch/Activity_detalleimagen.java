package com.example.touch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class Activity_detalleimagen extends Activity {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalleimagen_activity);
        TouchImageView img = (TouchImageView) findViewById(R.id.img);
        img.setMaxZoom(4);
        
         
    }
}