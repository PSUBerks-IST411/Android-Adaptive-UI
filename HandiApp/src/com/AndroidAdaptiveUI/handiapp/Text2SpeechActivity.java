package com.AndroidAdaptiveUI.handiapp;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.os.Build;

public class Text2SpeechActivity extends Activity implements OnInitListener {
	
	
	private TextToSpeech tts;
	
	EditText TextInput;

	ImageButton Clear;
	ImageButton Speak;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text2_speech);
		
		tts = new TextToSpeech(this, this);
		
		TextInput = (EditText) findViewById(R.id.TextInput);

		Speak = (ImageButton) findViewById(R.id.speaker_button);
		Clear = (ImageButton) findViewById(R.id.clear_button);
		
		Speak.setOnClickListener(new OnClickListener() {

			@Override
			   public void onClick(View v) {
				String text = TextInput.getText().toString();;
				tts.speak(text, TextToSpeech.QUEUE_ADD,null);
			}

		});
		
		Clear.setOnClickListener(new OnClickListener() {

			@Override
			   public void onClick(View v) {
				String text="";
				TextInput.setText(text);
				Toast.makeText(Text2SpeechActivity.this,"Inputbox is cleared!", Toast.LENGTH_SHORT).show();
			}

		});
		
		
	}



	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		
	}

}
