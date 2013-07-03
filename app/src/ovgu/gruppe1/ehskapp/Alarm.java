package ovgu.gruppe1.ehskapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

public class Alarm extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setup();
		snoozeAlarm(1);
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
				
				Log.d("notification trigger", "called");
				
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

		if (getTime() > hour * ONE_HOUR) {
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime()
							+ (ONE_DAY - (getTime() - ONE_HOUR * hour)), pi);
		} else {
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime() + (ONE_HOUR * hour - getTime()),
					pi);
			
		}
	}
	
	public void snoozeAlarm(long minutes) {
		
		am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
				SystemClock.elapsedRealtime() + minutes * ONE_MINUTE, pi);
		
	}

	protected void onDestroy() {
		am.cancel(pi);
		unregisterReceiver(br);
		super.onDestroy();
	}

}
