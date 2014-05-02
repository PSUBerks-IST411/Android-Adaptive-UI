package com.AndroidAdaptiveUI.handiapp;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.os.Build;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.provider.Settings.SettingNotFoundException;

public class SettingsActivity extends Activity implements OnInitListener {
	
	private TextToSpeech tts; 
	
	float progress = 0; // bright_bar progress

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		tts = new TextToSpeech(this, this);
		
		Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/HelveticaThn.ttf");
		TextView theme = (TextView) findViewById(R.id.theme);
		TextView brightness = (TextView) findViewById(R.id.brightness);
		theme.setTypeface(custom_font);
		brightness.setTypeface(custom_font);
		
		SeekBar bright_bar;  //Initialize Seekbar
		bright_bar = (SeekBar) findViewById(R.id.brightbar); //find bright_bar reference from R.java file
		
		
		
		float BrightnessValue = 0;
		
		try {
			     BrightnessValue = android.provider.Settings.System.getInt(
			     getContentResolver(),
			     android.provider.Settings.System.SCREEN_BRIGHTNESS);
		} catch (SettingNotFoundException e) {
			   e.printStackTrace();
	    }
		
		int screen_brightness = (int)BrightnessValue;
		progress = BrightnessValue*100/255;
		bright_bar.setProgress(screen_brightness);
		bright_bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		
		public void onProgressChanged(SeekBar seekBar, int progresValue,
		     boolean fromUser) {
		    progress = progresValue;
		    WindowManager.LayoutParams lp = getWindow().getAttributes();
		    lp.screenBrightness = (float)progress/100;
		    getWindow().setAttributes(lp);
		   
		    
		}
		public void onStartTrackingTouch(SeekBar seekBar) {
		    // Do something here,
		    // if you want to do anything at the start of
		    // touching the seekbar
		   }

		    public void onStopTrackingTouch(SeekBar seekBar) {
		    /*android.provider.Settings.System.putInt(getContentResolver(),
		      android.provider.Settings.System.SCREEN_BRIGHTNESS,
		      progress);*/
		   }
		  });
		
		brightness.setOnClickListener(new OnClickListener() {

			@Override
			   public void onClick(View v) {
				String text = "Screen brightness is at "+String.valueOf(progress)+"%";
				tts.speak(text, TextToSpeech.QUEUE_ADD, null);
			}

		});
		
		theme.setOnClickListener(new OnClickListener() { //To start Services Activity

			@Override
			   public void onClick(View v) {
				Intent i = new Intent(SettingsActivity.this, ThemeActivity.class);
				startActivity(i);
			}

		}); //End setOnClickListener*/
		
		

    }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_settings,
					container, false);
			return rootView;
		}
	}

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		
	}

}
