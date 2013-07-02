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
import android.view.Menu;
import android.view.View;

public class Alarm extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void test(View view) {
		setup();
		setAlarm(5);
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

				// was passiert wenn Alarm ausgeführt wird (Popup)

			}

		};

		registerReceiver(br, new IntentFilter("com.authorwjf.wakeywakey"));

		pi = PendingIntent.getBroadcast(this, 0, new Intent(
				"com.authorwjf.wakeywakey"), 0);

		am = (AlarmManager) (this.getSystemService(Context.ALARM_SERVICE));

	}

	public static long getTime() {
		long time = System.currentTimeMillis();

		while (time > ONE_DAY) {
			time = time - ONE_DAY;
		}

		return time;
	}

	public void setAlarm(long l) {
		System.out.println(getTime());
		System.out.println(l);
		if (getTime() > l * ONE_HOUR) {
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime()
							+ (ONE_DAY - (getTime() - ONE_HOUR * l)), pi);
		} else {
			am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
					SystemClock.elapsedRealtime() + (ONE_HOUR * l - getTime()),
					pi);
		}
	}

	protected void onDestroy() {
		am.cancel(pi);
		unregisterReceiver(br);
		super.onDestroy();
	}

}
