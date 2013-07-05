package ovgu.gruppe1.ehskapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;

public class Alarm extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.d("awdawd", "aöw,da");
		
		SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Editor mEditor = mPrefs.edit();
		
		
		if(getTime() < mPrefs.getInt("time1", 0)* ONE_HOUR || getTime() > mPrefs.getInt("time4", 0)* ONE_HOUR ) {
			int startTime = mPrefs.getInt("time1", 0);
			mEditor.putInt("CalledTime", 1);
			mEditor.commit();
			setup();
//			Log.d("startTime",""+startTime);
			setAlarm(startTime);
		}else if(mPrefs.getInt("time1", 0)* ONE_HOUR < getTime() && getTime() < mPrefs.getInt("time2", 0)* ONE_HOUR) {
			int startTime = mPrefs.getInt("time2", 0);
			mEditor.putInt("CalledTime", 2);
			mEditor.commit();
			setup();
//			Log.d("startTime",""+startTime);
			setAlarm(startTime);
		}else if(mPrefs.getInt("time2", 0)* ONE_HOUR < getTime() && getTime() < mPrefs.getInt("time3", 0)* ONE_HOUR) {
			int startTime = mPrefs.getInt("time3", 0);
			mEditor.putInt("CalledTime", 3);
			mEditor.commit();
			setup();
//			Log.d("startTime",""+startTime);
			setAlarm(startTime);
		}else {
			int startTime = mPrefs.getInt("time4", 0);
			mEditor.putInt("CalledTime", 4);
			mEditor.commit();
			setup();
//			Log.d("startTime",""+startTime);
			setAlarm(startTime);
		}
		
//		Log.d("getTime()", ""+getTime());
//		Log.d("Time1", ""+mPrefs.getInt("time1", 0)* ONE_HOUR);
//		Log.d("Time2", ""+mPrefs.getInt("time2", 0)* ONE_HOUR);
//		Log.d("Time3", ""+mPrefs.getInt("time3", 0)* ONE_HOUR);
//		Log.d("Time4", ""+mPrefs.getInt("time4", 0)* ONE_HOUR);
			
		setup();
		
	}

	final static private long ONE_SECOND = 1000;
	final static private long ONE_MINUTE = ONE_SECOND * 60;
	final static private long ONE_HOUR = ONE_MINUTE * 60;
	final static private long ONE_DAY = ONE_HOUR * 24;

	PendingIntent pi;
	
	BroadcastReceiver br;

	AlarmManager am;

	private void setup() {

		br = new BroadcastReceiver() {

			@Override
			public void onReceive(Context c, Intent i) {
				
				Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				if(notification == null){
			         // alert is null, using backup
					notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			         if(notification == null){  // I can't see this ever being null (as always have a default notification) but just incase
			             // alert backup is null, using 2nd backup
			        	 notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);               
			         }
			     }
				Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
				r.play();
				PopUpNotification.notify(c, "bla", 1);
			}

		};

		registerReceiver(br, new IntentFilter("ovgu.gruppe1.ehskapp"));

		pi = PendingIntent.getBroadcast(this, 0, new Intent(
				"ovgu.gruppe1.ehskapp"), 0);

		am = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));

	}

	public static long getTime() {
		long time = System.currentTimeMillis();

		while (time > ONE_DAY) {
			time = time - ONE_DAY;
		}

		return time;
	}
			
	public void setAlarm(long hour) {
		
		Log.d("Alarm set", "new Alarm");

		if (getTime() > hour * ONE_HOUR) {
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime()
							+ (ONE_DAY - (getTime() - ONE_HOUR * hour)), pi);
		} else {
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime() + (ONE_HOUR * hour - getTime()),
					pi);
			
		}
		
		SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Editor mEditor = mPrefs.edit();
		
		if (mPrefs.contains("nextCalledTime")) {
			switch (mPrefs.getInt("nextCalledTime", 0)) {
			case 1:
				mEditor.putInt("nextCalledTime", 2);
				mEditor.commit();
				break;
			case 2:
				mEditor.putInt("nextCalledTime", 3);
				mEditor.commit();
				break;
			case 3:
				mEditor.putInt("nextCalledTime", 4);
				mEditor.commit();
				break;
			case 4:
				mEditor.putInt("nextCalledTime", 1);
				mEditor.commit();
				break;
			}
		}
		
		
	}
	
//	public void snoozeAlarm(long minutes) {
//		
//		am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//				SystemClock.elapsedRealtime() + minutes * ONE_MINUTE, pi);
//		
//	}

	protected void onDestroy() {
		am.cancel(pi);
		unregisterReceiver(br);
		super.onDestroy();
	}

}
