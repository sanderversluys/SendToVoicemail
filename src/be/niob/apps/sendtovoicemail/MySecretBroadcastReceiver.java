package be.niob.apps.sendtovoicemail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class MySecretBroadcastReceiver extends BroadcastReceiver {

	public static final String DEBUG_KEY = "DEBUG";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean(DEBUG_KEY, !prefs.getBoolean(DEBUG_KEY, false));
		editor.commit();
		
		Toast.makeText(context, "DEBUG mode is set to " + prefs.getBoolean(DEBUG_KEY, false), Toast.LENGTH_LONG).show();
	}

}
