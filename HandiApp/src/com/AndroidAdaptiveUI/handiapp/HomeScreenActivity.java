package com.AndroidAdaptiveUI.handiapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.BatteryManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;



public class HomeScreenActivity extends Activity implements OnInitListener {
	
	private int MY_DATA_CHECK_CODE = 0;
	
	private TextToSpeech tts; 
	
	/*
	 * Signal levels
	 * */
	private static final int LEVEL1 = 80;
    private static final int LEVEL2 = 60;
    private static final int LEVEL3 = 40;
    private static final int LEVEL4 = 20;
    private static final int LEVEL5 = 0;
    
    String formattedDate;
    String formattedDateSpeak; //Format for Speech
    String formattedTime;
    String formattedTimeSpeak;		//Format for Speech
    String signalStrengthSpeak;		//Format for Speech
    String statusString = "Unknown";	//Format for Speech
    String batteryLevelSpeak; 	//Format for Speech
    
    private ImageView Services_img;
    private TextView Services;
    private ImageView Settings_img;
  
	
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		
		Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/HelveticaThn.ttf");
		
		Calendar c = Calendar.getInstance(); 
		SimpleDateFormat df = new SimpleDateFormat("E, MMMMMM dd"); //Sets format for the date
		SimpleDateFormat df1 = new SimpleDateFormat("E MMMMMM dd yyyy ");
	    formattedDate = df.format(c.getTime()); //Stores the date value in a string variable
	    formattedDateSpeak = df1.format(c.getTime()); 
	
		
		TextView cdate; //Creates a TextView for displaying date
		cdate = (TextView) findViewById(R.id.current_date); //Finds the textView reference in R java file
		cdate.setTypeface(custom_font);
		cdate.setText("" +formattedDate); 
		
		cdate.setOnClickListener(new OnClickListener() {

			@Override
			   public void onClick(View v) {
				String text = formattedDateSpeak;
				tts.speak(text, TextToSpeech.QUEUE_ADD, null);
			}

		});
		
		 Intent checkIntent = new Intent();
		 checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		 startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

		
		
		Calendar dc = Calendar.getInstance();
		SimpleDateFormat digiclock = new SimpleDateFormat("h:mm");
		SimpleDateFormat digiclockspeak = new SimpleDateFormat("h:mm a");
		formattedTime = digiclock.format(dc.getTime());
		formattedTimeSpeak = digiclockspeak.format(dc.getTime());
		TextView Digital_clock;
		Digital_clock = (TextView) findViewById(R.id.digital_clock);
		Digital_clock.setTypeface(custom_font);
		Digital_clock.setText("" +formattedTime);
		
		Digital_clock.setOnClickListener(new OnClickListener() {

			@Override
			   public void onClick(View v) {
				String text = formattedTimeSpeak;
				tts.speak(text, TextToSpeech.QUEUE_ADD, null);
			}

		});
		
		
		
		TextView Settings;
		Settings = (TextView) findViewById(R.id.Settings);
		Settings.setTypeface(custom_font);
		
		Settings.setOnClickListener(new OnClickListener() { //To start Services Activity

			@Override
			   public void onClick(View v) {
				Intent i = new Intent(HomeScreenActivity.this, SettingsActivity.class);
				startActivity(i);
			}

		}); //End setOnClickListener*/
		
		
		Settings_img = (ImageView) findViewById(R.id.settings); 
		
		Settings_img.setOnClickListener(new OnClickListener() { //To start Services Activity

			@Override
			   public void onClick(View v) {
				Intent i = new Intent(HomeScreenActivity.this, SettingsActivity.class);
				startActivity(i);
			}

		}); //End setOnClickListener*/
		
		

		Services = (TextView) findViewById(R.id.Services);
		Services.setTypeface(custom_font);
		
		Services.setOnClickListener(new OnClickListener() { //To start Services Activity

			@Override
			   public void onClick(View v) {
				Intent i = new Intent(HomeScreenActivity.this, ServicesActivity.class);
				startActivity(i);
			}

		}); //End setOnClickListener*/
		
		

		Services_img = (ImageView) findViewById(R.id.services);
		
		Services_img.setOnClickListener(new OnClickListener() { //To start Services Activity

			@Override
			   public void onClick(View v) {

				Intent i = new Intent(HomeScreenActivity.this, ServicesActivity.class);
				startActivity(i);
			}
		

		}); //End setOnClickListener*/
		
		
		Log.i("ONCREATE", "<<------- START  Signallistener------- >>");
        startSignalLevelListener();
        Log.i("ONCREATE", "<<------- END  Signallister------- >>");
        
        Log.i("ONCREATE", "<<------- START  BatteryLevel Listner------- >>");
        registerBatteryLevelReceiver();
        Log.i("ONCREATE", "<<------- END  Batterylevel Listener------- >>");  
        
	} //End of onCreate method
	
	
	
	@Override
    protected void onStop() {
    	super.onStop();
    	//Stop listening to the telephony events 
    	StopListener();
    	
    } //End of onStop

    @Override
    protected void onPause() {
    	super.onPause();
    	//Stop listening to the telephony events
    	StopListener();
    } //End of onPause
    
    @Override
    protected void onResume() {
    	super.onResume();
    	//subscribes to the telephony related events
    	startSignalLevelListener();
    } //End of onResume
    
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
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	if (requestCode == MY_DATA_CHECK_CODE) {
		if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {  // success, create the TTS instance
			tts = new TextToSpeech(this, this);
		} else {
		    Intent installIntent = new Intent();		
		    installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
		    startActivity(installIntent);
		}

	}

}

    private void startSignalLevelListener() {
    	Log.i("startSignalLevelListener", "<<------- START ------- >>");
    	TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        @SuppressWarnings("deprecation")
        int events = PhoneStateListener.LISTEN_SIGNAL_STRENGTH;
        tm.listen(phoneListener, events);
        Log.i("startSignalLevelListener", "<<------- END ------- >>");
    } //End of startSignalLevelListener
    
    private void StopListener() {
    	TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
    	tm.listen(phoneListener, PhoneStateListener.LISTEN_NONE);
    } //End of StopListener
    
    private void setSignalLevel(int level){
    	
    	ImageView signal;
    	signal = (ImageView) findViewById(R.id.signal4);
    	
    	int progress = (int) ((((float)level)/31.0) * 100); //converts Signal Level to percent
    	
        if(progress > LEVEL1)            {
        	
        	signal.setImageResource(R.drawable.signals5);
        	signalStrengthSpeak = "Excellent";
        }
        else if(progress > LEVEL2)         {

        	signal.setImageResource(R.drawable.signals4);
        	signalStrengthSpeak = "Good";
        	
        }
        else if(progress > LEVEL3) 	{

        	signal.setImageResource(R.drawable.signals3);
        	signalStrengthSpeak = "Medium";
        }
        else if(progress > LEVEL4)    {

        	signal.setImageResource(R.drawable.signals2);
        	signalStrengthSpeak = "Weak";
        }
        else if(progress > LEVEL5)      {

        	signal.setImageResource(R.drawable.signals1);
        	signalStrengthSpeak = "Very weak";
        } 
        else if(progress == LEVEL5) {
        	
        	signalStrengthSpeak = "Zero";
        	signal.setImageResource(R.drawable.signals0);
        }
        
        signal.setOnClickListener(new OnClickListener() {

			@Override
			   public void onClick(View v) {
				String text = "Signal strength is " +signalStrengthSpeak;
				tts.speak(text, TextToSpeech.QUEUE_ADD, null);
			}

		});

    } //End of setSignalLevel
    
 
	private final PhoneStateListener phoneListener = new  PhoneStateListener(){
	
		@SuppressWarnings("deprecation")
		public void onSignalStrengthChanged(int lvl) {
			
			setSignalLevel(lvl);
			
			super.onSignalStrengthChanged(lvl);
		}
	};  //End of PhoneStateListener
	
	
	
	
	/*
	 * Battery Level Functions
	 */
	
	private BroadcastReceiver battery_receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			//Battery Plugged Information
			//int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            //Battery Scale
			int scale = intent.getIntExtra("scale", -1);
			//Battery Charging Status
			int status = intent.getIntExtra("status", 0); //Current battery status - charging/unplugged
			//Battery charging level
			int rawlevel = intent.getIntExtra("level", -1); //gets current battery level
			int level = 0; //initialize level
			Bundle bundle = intent.getExtras();
			Log.i("BatteryLevel", bundle.toString());	
			if (rawlevel >= 0 && scale > 0) {
				level = (rawlevel * 100) / scale;  //Convert to 100-percent scale
			}	
			setBatteryLevel(level);
			setStatusString(status);
		
		}
	}; //End of BroadcastReciever
	
	private void setBatteryLevel(int Level) {
		TextView battery_lvl;
		battery_lvl = (TextView) findViewById(R.id.battery_level);
		ImageView battery_img;
		battery_img = (ImageView) findViewById(R.id.battery_image);
		
		Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/HelveticaThn.ttf");
		battery_lvl.setTypeface(custom_font);
		battery_lvl.setText(""+Level+"% -");
		
		if(Level == 100) {
			battery_img.setImageResource(R.drawable.battery100);
		} else if(Level >= 95) {
			battery_img.setImageResource(R.drawable.battery95);
		} else if(Level >= 90) {
			battery_img.setImageResource(R.drawable.battery90);
		} else if(Level >= 85) {
			battery_img.setImageResource(R.drawable.battery85);
		} else if(Level >= 80) {
			battery_img.setImageResource(R.drawable.battery80);
		} else if(Level >= 75) {
			battery_img.setImageResource(R.drawable.battery75);
		} else if(Level >= 70) {
			battery_img.setImageResource(R.drawable.battery70);
		} else if(Level >= 65) {
			battery_img.setImageResource(R.drawable.battery65);
		} else if(Level >= 60) {
			battery_img.setImageResource(R.drawable.battery60);
		} else if(Level >= 55) {
			battery_img.setImageResource(R.drawable.battery55);
		} else if(Level >= 50) {
			battery_img.setImageResource(R.drawable.battery50);
		} else if(Level >= 45) {
			battery_img.setImageResource(R.drawable.battery45);
		} else if(Level >= 40) {
			battery_img.setImageResource(R.drawable.battery40);
		} else if(Level >= 35) {
			battery_img.setImageResource(R.drawable.battery35);
		} else if(Level >= 30) {
			battery_img.setImageResource(R.drawable.battery30);
		} else if(Level >= 25) {
			battery_img.setImageResource(R.drawable.battery25);
		} else if(Level >= 20) {
			battery_img.setImageResource(R.drawable.battery20);
		} else if(Level >= 15) {
			battery_img.setImageResource(R.drawable.battery15);
		} else if(Level >= 10) {
			battery_img.setImageResource(R.drawable.battery10);
		} else if(Level >= 5) {
			battery_img.setImageResource(R.drawable.battery05);
		} else if(Level == 95) {
			battery_img.setImageResource(R.drawable.battery00);
		}
		
		batteryLevelSpeak= Integer.toString(Level);
		
		battery_lvl.setOnClickListener(new OnClickListener() {

			@Override
			   public void onClick(View v) {
				String text = "Battery is currently at" +batteryLevelSpeak+ "% charge and Battery is" + statusString;
				tts.speak(text, TextToSpeech.QUEUE_ADD, null);
			}

		});
		
		battery_img.setOnClickListener(new OnClickListener() {

			@Override
			   public void onClick(View v) {
				String text = "Battery is currently at" +batteryLevelSpeak+ "%  charge and Battery is" + statusString;
				tts.speak(text, TextToSpeech.QUEUE_ADD, null);
			}

		});
			
	} //End of setBatteryLevel
	
	private void setStatusString(int status) {
		
		TextView battery_sts;
		battery_sts = (TextView) findViewById(R.id.battery_status);

		switch (status) {
		case BatteryManager.BATTERY_STATUS_CHARGING:
			statusString = "Charging";
			break;
		case BatteryManager.BATTERY_STATUS_DISCHARGING:
			statusString = "Discharging";
			break;
		case BatteryManager.BATTERY_STATUS_FULL:
			statusString = "Full";
			break;
		case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
			statusString = "Unplugged";
			break;
		}
		
		Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/HelveticaThn.ttf");
		battery_sts.setTypeface(custom_font);
		battery_sts.setText("  "+statusString);;
		
		battery_sts.setOnClickListener(new OnClickListener() { 

			@Override
			   public void onClick(View v) {
				String text = "Battery is currently at" +batteryLevelSpeak+ "% charge and Battery is" + statusString;
				tts.speak(text, TextToSpeech.QUEUE_ADD, null);
			}

		});
	} //End of setStatusString

	
	private void registerBatteryLevelReceiver() {
		IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(battery_receiver, filter);
	} //End of registerBatteryLevelReciever

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
	}



}


