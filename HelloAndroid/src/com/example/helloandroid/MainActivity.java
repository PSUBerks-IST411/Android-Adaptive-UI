package com.example.helloandroid;

import java.util.HashMap;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener, OnUtteranceCompletedListener {
	
	TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       tts = new TextToSpeech(MainActivity.this, MainActivity.this);
       final Button speech = (Button) findViewById(R.id.button1);
       final EditText editText = (EditText) findViewById(R.id.editText1);

       speech.setOnClickListener(new OnClickListener(){
    	   
    	   @Override
    	   public void onClick(View v){
    		   if(!tts.isSpeaking())
    		   {
    			   HashMap <String, String> params = new HashMap <String, String> ();
    			   params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "sampletext");
    			   tts.speak(editText.getText().toString(), TextToSpeech.QUEUE_ADD, params);
    			   speech.setVisibility(Button.GONE);
    		   } else
    		   {
    			   tts.stop();
    		   }
    	   }
    	   
       });

}

	@Override
	public void onUtteranceCompleted(String utteranceId) {
		runOnUiThread(new Runnable(){
			@Override
			public void run(){
				Toast.makeText(MainActivity.this, "Text To Speech Complete", Toast.LENGTH_LONG).show();
				Button btn = (Button) findViewById(R.id.button1);
				btn.setVisibility(Button.VISIBLE);
			}
		});
		
	}

	@Override
	public void onInit(int status) {
		tts.setOnUtteranceCompletedListener(this);
		
	}
	
	@Override
	public void onDestroy(){
		if(tts != null)
		{
			tts.stop();
			tts.shutdown();
			tts = null;
		}
		super.onDestroy();
	}
	
}
