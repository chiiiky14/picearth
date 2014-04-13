package com.example.touch;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class TouchImageViewActivity extends Activity {
    /** Called when the activity is first created. */
	Button btnTwitter, btnFacebook;
	String url = "http://www.edmlounge.com/storage/heisenberg.png?__SQUARESPACE_CACHEVERSION=1360440540556";
	boolean facebookAppFound;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TouchImageView img = (TouchImageView) findViewById(R.id.img);
        img.setMaxZoom(4);
        
        btnTwitter = (Button) findViewById(R.id.btnTwitter); 
        btnTwitter.setOnClickListener(new Button.OnClickListener()
        { 
	    	@Override
	    	 public void onClick(View v) 
	    	 {
	    		Intent tweetIntent = new Intent(Intent.ACTION_SEND);
	    		tweetIntent.putExtra(Intent.EXTRA_TEXT, "This is a Test. " + url);
	    		tweetIntent.setType("text/plain");

	    		PackageManager packManager = getPackageManager();
	    		List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent,  PackageManager.MATCH_DEFAULT_ONLY);

	    		boolean resolved = false;
	    		for(ResolveInfo resolveInfo: resolvedInfoList){
	    		    if(resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")){
	    		        tweetIntent.setClassName(
	    		            resolveInfo.activityInfo.packageName, 
	    		            resolveInfo.activityInfo.name );
	    		        resolved = true;
	    		        break;
	    		    }
	    		}
	    		if(resolved){
	    		    startActivity(tweetIntent);
	    		}else{
	    			Toast.makeText(TouchImageViewActivity.this, "Twitter app isn't found", Toast.LENGTH_SHORT).show();
	    		}
	    	 }
	    });
        
        
        
        btnFacebook = (Button) findViewById(R.id.btnFacebook); 
        btnFacebook.setOnClickListener(new Button.OnClickListener()
        { 
	    	@Override
	    	 public void onClick(View v) 
	    	 {
	    		/*Intent intent = new Intent();
	    		intent.setAction(Intent.ACTION_SEND);
	    		intent.setType("image/*");      

	    		intent.putExtra(Intent.EXTRA_TEXT, "eample");
	    		intent.putExtra(Intent.EXTRA_TITLE, "example");
	    		intent.putExtra(Intent.EXTRA_SUBJECT, "example");
	    		intent.putExtra(Intent.EXTRA_STREAM, url);

	    		Intent openInChooser = new Intent(intent);
	    		String extraIntents;
				openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, url);
	    		startActivity(openInChooser);*/
	    		
	    		Intent intent = new Intent(Intent.ACTION_SEND);
	    		intent.setType("text/plain");
	    		intent.putExtra(Intent.EXTRA_TEXT, url);
	    		
	    		List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
	    		
	    		for (ResolveInfo info : matches) {
	    			if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
	    				intent.setPackage(info.activityInfo.packageName);
	    				facebookAppFound = true;
	    				break;
	    			}
	    		}

	    		startActivity(intent);
	    	 }
	    });
        
        
    }
    
    public void TouchViewer(View v) 
    {
        // does something very interesting
    	
    	Intent n= new Intent(getApplicationContext(), Activity_detalleimagen.class);
        startActivity(n);
    }
}