package com.AndroidAdaptiveUI.handiapp;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;

public class ServicesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_services);
		
		Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/HelveticaThn.ttf");
		TextView Text2Speech;
		Text2Speech = (TextView) findViewById(R.id.text2speech_label);
		Text2Speech.setTypeface(custom_font);
		
		Text2Speech.setOnClickListener(new OnClickListener() { //To start Services Activity

			@Override
			   public void onClick(View v) {
				Intent i = new Intent(ServicesActivity.this, Text2SpeechActivity.class);
				startActivity(i);
			}
		
		});
		
		
		
		ImageView Text2Speech_img;
		Text2Speech_img = (ImageView) findViewById(R.id.text2speech_img);
		
		Text2Speech_img.setOnClickListener(new OnClickListener() { //To start Services Activity

			@Override
			   public void onClick(View v) {
				Intent i = new Intent(ServicesActivity.this, Text2SpeechActivity.class);
				startActivity(i);
			}
		
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.services, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_services,
					container, false);
			return rootView;
		}
	}

}
