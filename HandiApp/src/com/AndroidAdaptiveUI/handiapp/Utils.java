package com.AndroidAdaptiveUI.handiapp;

import android.app.Activity;
import android.content.Intent;
import com.AndroidAdaptiveUI.handiapp.*;

public class Utils {
	
	private static int sTheme;

	public final static int THEME_DEFAULT = 0;
	public final static int THEME_1 = 1;
	public final static int THEME_2 = 2;
	public final static int THEME_3 = 3;
	public final static int THEME_4 = 4;
	

	/**
	 * Set the theme of the Activity, and restart it by creating a new Activity
	 * of the same type.
	 */
	public static void changeToTheme(Activity activity, int theme)
	{
		sTheme = theme;
		activity.finish();

		activity.startActivity(new Intent(activity, activity.getClass()));
	}

	/** Set the theme of the activity, according to the configuration. */
	public static void onActivityCreateSetTheme(Activity activity)
	{
		switch (sTheme)
		{
		default:
		case THEME_DEFAULT:
			activity.setTheme(R.style.Background0);
			break;
		case THEME_1:
			activity.setTheme(R.style.Background1);
			break;
		case THEME_2:
			activity.setTheme(R.style.Background2);
			break;
		case THEME_3:
			activity.setTheme(R.style.Background3);
			break;
		case THEME_4:
			activity.setTheme(R.style.Background4);
			break;
		}
	}

}
