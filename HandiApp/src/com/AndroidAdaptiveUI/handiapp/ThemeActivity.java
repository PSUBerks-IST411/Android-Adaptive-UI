package com.AndroidAdaptiveUI.handiapp;


import android.app.Activity;
import android.app.AlertDialog;

import java.io.IOException;
import java.util.ArrayList;

import com.AndroidAdaptiveUI.handiapp.*;

import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.util.TypedValue;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.os.Build;

public class ThemeActivity extends Activity {
	
	private Gallery gallery;
	private ImageView Livewall;
	private ImageView set_img_button;
	private Integer[] Imgid = { R.drawable.wallpaper1,  R.drawable.wallpaper2,  R.drawable.wallpaper3,  R.drawable.wallpaper4, R.drawable.default_2x };
	private int Position =0;
	private int wallposition;
	ContextWrapper context = null;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setContentView(R.layout.activity_theme);
		super.onCreate(savedInstanceState);
		Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/HelveticaThn.ttf");
		
		TextView wallpaper;
		wallpaper = (TextView) findViewById(R.id.Wallpaper_label);
		wallpaper.setTypeface(custom_font);
		
		Livewall= (ImageView) findViewById(R.id.livewall);
		Livewall.setImageResource(Imgid[0]);
	
		gallery = (Gallery) findViewById(R.id.gallery);
		gallery.setSpacing(1);
        gallery.setAdapter(new GalleryImageAdapter(this));
        
        // clicklistener for Gallery
        gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(ThemeActivity.this, "Your selected position = " + position, Toast.LENGTH_SHORT).show();
                // show the selected Image
                Livewall.setImageResource(Imgid[position]);
                Position=position;
            }
        });
        
        set_img_button =(ImageView) findViewById(R.id.set_img);
        
		set_img_button.setOnClickListener(new OnClickListener() {			
			
			@Override
			public void onClick(View v) {
				
				setWallpaper();
				/*
				AlertDialog alertDialog = new AlertDialog.Builder(ThemeActivity.this).create();
				alertDialog.setTitle("Confirmation");
	            alertDialog.setMessage("Do you want to set this image as wallpaper?");
				
				*/			 
		        
			}
			
		});		
		
	}
	
	public void setWallpaper() {
			
			//SharedPreferences pref=c.getSharedPreferences("preference",0);
			//wallposition= pref.getInt("Position", 0);
			
			switch (Position) {
	        case 0:		
	        	Utils.changeToTheme(this, Utils.THEME_1);

	            break;		
	        case 1:		
	        	Utils.changeToTheme(this, Utils.THEME_2);

	            break;		
	        case 2:		
	        	Utils.changeToTheme(this, Utils.THEME_3);

	            break;		
	        case 3:
	        	Utils.changeToTheme(this, Utils.THEME_4);

	            break;		
	            
	        case 4:
	        	Utils.changeToTheme(this, Utils.THEME_DEFAULT);

	            break;	
	        }
			
		}
		/*Bitmap bm = null;
		 bm = Bitmap.createScaledBitmap(bm, 213, 189, true);
		 
		 
		 Livewall.setOnLongClickListener(new View.OnLongClickListener() {
		        public boolean onLongClick(View v) {

		            AlertDialog alertDialog = new AlertDialog.Builder(ThemeActivity.this).create();
		            alertDialog.setTitle("Confirmation");
		            alertDialog.setMessage("Do you want to set this image as wallaper?");
		            alertDialog.setButton("Yes",new DialogInterface.OnClickListener() {
		                        
		            	
		        public void onClick(DialogInterface dialog,int which) {

		             Bitmap bitmap = BitmapFactory.decodeResource(getResources(), Imgid[position]);
		                            try {
		                                ThemeActivity.this.setWallpaper(bitmap);
		                            } catch (IOException e) {
		                                // TODO Auto-generated catch block
		                                e.printStackTrace();
		                            }
		                            Log.d("Gallery Example", "Image settLed.");

		                        }
		                    });

		            alertDialog.show();
		            return true;
		        }
		    });	 */
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.theme, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_theme,
					container, false);
			return rootView;
		}
	}

}



