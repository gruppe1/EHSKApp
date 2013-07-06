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

/**
 * Alarm Manager activity of the App
 * - setting new Alarm on create
 * 
 * @author Gruppe 1
 * 
 */
public class Alarm extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences mPrefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		Editor mEditor = mPrefs.edit();

		if (getTime() < mPrefs.getInt("time1", 0) * ONE_HOUR
				|| getTime() > mPrefs.getInt("time4", 0) * ONE_HOUR) {
			int startTime = mPrefs.getInt("time1", 0);
			mEditor.putInt("CalledTime", 1);
			mEditor.commit();
			setup();
			setAlarm(startTime);
		} else if (mPrefs.getInt("time1", 0) * ONE_HOUR < getTime()
				&& getTime() < mPrefs.getInt("time2", 0) * ONE_HOUR) {
			int startTime = mPrefs.getInt("time2", 0);
			mEditor.putInt("CalledTime", 2);
			mEditor.commit();
			setup();
			setAlarm(startTime);
		} else if (mPrefs.getInt("time2", 0) * ONE_HOUR < getTime()
				&& getTime() < mPrefs.getInt("time3", 0) * ONE_HOUR) {
			int startTime = mPrefs.getInt("time3", 0);
			mEditor.putInt("CalledTime", 3);
			mEditor.commit();
			setup();
			setAlarm(startTime);
		} else {
			int startTime = mPrefs.getInt("time4", 0);
			mEditor.putInt("CalledTime", 4);
			mEditor.commit();
			setup();
			setAlarm(startTime);
		}
		setup();
	}

	final static private long ONE_SECOND = 1000;
	final static private long ONE_MINUTE = ONE_SECOND * 60;
	final static private long ONE_HOUR = ONE_MINUTE * 60;
	final static private long ONE_DAY = ONE_HOUR * 24;

	PendingIntent pi;

	BroadcastReceiver br;

	AlarmManager am;

	/**
	 * setting up the AlarmManager, the BroadcastReceiver and register it
	 */
	private void setup() {

		br = new BroadcastReceiver() {

			@Override
			public void onReceive(Context c, Intent i) {

				Uri notification = RingtoneManager
						.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				if (notification == null) {
					// alert is null, using backup
					notification = RingtoneManager
							.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
					if (notification == null) { // I can't see this ever being
												// null (as always have a
												// default notification) but
												// just incase
						// alert backup is null, using 2nd backup
						notification = RingtoneManager
								.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
					}
				}
				Ringtone r = RingtoneManager.getRingtone(
						getApplicationContext(), notification);
				r.play();
				PopUpNotification.notify(c, "Answer Questions", 1);
			}

		};

		registerReceiver(br, new IntentFilter("ovgu.gruppe1.ehskapp"));

		pi = PendingIntent.getBroadcast(this, 0, new Intent(
				"ovgu.gruppe1.ehskapp"), 0);

		am = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));
		
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);

	}

	/**
	 * get the current system time in milliseconds 
	 * @return
	 */
	public static long getTime() {
		long time = System.currentTimeMillis();

		while (time > ONE_DAY) {
			time = time - ONE_DAY;
		}

		return time;
	}

	/**
	 * setting the new alarm at "hour" o'clock
	 * 
	 * @param hour
	 * 			specified hour for the alarm to be set
	 */
	public void setAlarm(long hour) {

		if (getTime() > hour * ONE_HOUR) {
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime()
							+ (ONE_DAY - (getTime() - ONE_HOUR * hour)), pi);
		} else {
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime()
							+ (ONE_HOUR * hour - getTime()), pi);

		}
	}

	protected void onDestroy() {
		am.cancel(pi);
		unregisterReceiver(br);
		super.onDestroy();
	}

}
