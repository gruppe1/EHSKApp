package ovgu.gruppe1.ehskapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Activity to let the user choose four alarm times a day
 * -one out of 9 to 12 o'clock
 * -one out of 13 to 15 o'clock
 * -one out of 16 to 19 o'clock
 * -one out of 20 to 23 o'clock
 * @author Gruppe 1
 *
 */
public class TimeChooserActivity extends Activity {

	protected static final String TAG = "TimeChooserActivity";
	
	// Row1
	ToggleButton btn_9, btn_10, btn_11, btn_12;
	// Row2
	ToggleButton btn_13, btn_14, btn_15;
	// Row3
	ToggleButton btn_16, btn_17, btn_18, btn_19;
	// Row4
	ToggleButton btn_20, btn_21, btn_22, btn_23;
	// Save
	Button btn_save_times;
	
	int chosen_time1, chosen_time2, chosen_time3, chosen_time4;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time_chooser_layout);
		// Row1
		btn_9 = (ToggleButton) findViewById(R.id.btn_time_9);
		btn_10 = (ToggleButton) findViewById(R.id.btn_time_10);
		btn_11 = (ToggleButton) findViewById(R.id.btn_time_11);
		btn_12 = (ToggleButton) findViewById(R.id.btn_time_12);
		// Row2
		btn_13 = (ToggleButton) findViewById(R.id.btn_time_13);
		btn_14 = (ToggleButton) findViewById(R.id.btn_time_14);
		btn_15 = (ToggleButton) findViewById(R.id.btn_time_15);
		// Row3
		btn_16 = (ToggleButton) findViewById(R.id.btn_time_16);
		btn_17 = (ToggleButton) findViewById(R.id.btn_time_17);
		btn_18 = (ToggleButton) findViewById(R.id.btn_time_18);
		btn_19 = (ToggleButton) findViewById(R.id.btn_time_19);
		// Row4
		btn_20 = (ToggleButton) findViewById(R.id.btn_time_20);
		btn_21 = (ToggleButton) findViewById(R.id.btn_time_21);
		btn_22 = (ToggleButton) findViewById(R.id.btn_time_22);
		btn_23 = (ToggleButton) findViewById(R.id.btn_time_23);
		// Save
		btn_save_times = (Button) findViewById(R.id.btn_save_times);
		
		// Row1
		btn_9.setOnCheckedChangeListener(changeCheckerRow1);
		btn_10.setOnCheckedChangeListener(changeCheckerRow1);
		btn_11.setOnCheckedChangeListener(changeCheckerRow1);
		btn_12.setOnCheckedChangeListener(changeCheckerRow1);
		// Row2
		btn_13.setOnCheckedChangeListener(changeCheckerRow2);
		btn_14.setOnCheckedChangeListener(changeCheckerRow2);
		btn_15.setOnCheckedChangeListener(changeCheckerRow2);
		// Row3
		btn_16.setOnCheckedChangeListener(changeCheckerRow3);
		btn_17.setOnCheckedChangeListener(changeCheckerRow3);
		btn_18.setOnCheckedChangeListener(changeCheckerRow3);
		btn_19.setOnCheckedChangeListener(changeCheckerRow3);
		// Row4
		btn_20.setOnCheckedChangeListener(changeCheckerRow4);
		btn_21.setOnCheckedChangeListener(changeCheckerRow4);
		btn_22.setOnCheckedChangeListener(changeCheckerRow4);
		btn_23.setOnCheckedChangeListener(changeCheckerRow4);
		// Save
		btn_save_times.setOnClickListener(saveClick);
		
		SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		if (mPrefs.contains("time1")) {
			switch(mPrefs.getInt("time1", 0)) {
			case 9:
				btn_9.setChecked(true);
				break;
			case 10:
				btn_10.setChecked(true);
				break;
			case 11:
				btn_11.setChecked(true);
				break;
			case 12:
				btn_12.setChecked(true);
				break;
			}
		}
		if (mPrefs.contains("time2")) {
			switch(mPrefs.getInt("time2", 0)) {
			case 13:
				btn_13.setChecked(true);
				break;
			case 14:
				btn_14.setChecked(true);
				break;
			case 15:
				btn_15.setChecked(true);
				break;
			}
		}
		if (mPrefs.contains("time3")) {
			switch(mPrefs.getInt("time3", 0)) {
			case 16:
				btn_16.setChecked(true);
				break;
			case 17:
				btn_17.setChecked(true);
				break;
			case 18:
				btn_18.setChecked(true);
				break;
			case 19:
				btn_19.setChecked(true);
				break;
			}
		}
		if (mPrefs.contains("time4")) {
			switch(mPrefs.getInt("time4", 0)) {
			case 20:
				btn_20.setChecked(true);
				break;
			case 21:
				btn_21.setChecked(true);
				break;
			case 22:
				btn_22.setChecked(true);
				break;
			case 23:
				btn_23.setChecked(true);
				break;
			}
		}

	}

	OnClickListener saveClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(chosen_time1 >= 9 && chosen_time1 <= 12 && 
			chosen_time2 >= 13 && chosen_time2 <= 15 &&
			chosen_time3 >= 16 && chosen_time3 <= 19 &&
			chosen_time4 >= 20 && chosen_time4 <= 23) {
			SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			Editor mEditor = mPrefs.edit();
			mEditor.putInt("time1", chosen_time1);
			mEditor.putInt("time2", chosen_time2);
			mEditor.putInt("time3", chosen_time3);
			mEditor.putInt("time4", chosen_time4);
			mEditor.commit();
			Toast.makeText(getApplicationContext(), "Alarm-Zeiten gespeichert", Toast.LENGTH_LONG).show();
			
			Intent alarmIntent = new Intent(TimeChooserActivity.this, Alarm.class);
			startActivity(alarmIntent);
			
			finish();
			}
			else {
				Toast.makeText(getApplicationContext(), "Alarm-Zeiten konnten NICHT gespeichert werden. Bitte pro Spalte eine Zeit auswählen.", Toast.LENGTH_LONG).show();
			}
		}
	};
    
	OnCheckedChangeListener changeCheckerRow1 = new OnCheckedChangeListener() {
	    @Override
	    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	        if (isChecked){
	            if (buttonView == btn_9) {
	            	btn_10.setChecked(false);
	            	btn_11.setChecked(false);
	            	btn_12.setChecked(false);
	            	chosen_time1 = 9;
	            }
	            if (buttonView == btn_10) {
	            	btn_9.setChecked(false);
	            	btn_11.setChecked(false);
	            	btn_12.setChecked(false);
	            	chosen_time1 = 10;
	            }
	            if (buttonView == btn_11) {
	            	btn_9.setChecked(false);
	            	btn_10.setChecked(false);
	            	btn_12.setChecked(false);
	            	chosen_time1 = 11;
	            }
	            if (buttonView == btn_12) {
	            	btn_9.setChecked(false);
	            	btn_10.setChecked(false);
	            	btn_11.setChecked(false);
	            	chosen_time1 = 12;
	            }
	        }
//	        Log.d(TAG, "time1 Preference set: "+PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("time1", 0));
	    }
	};
	OnCheckedChangeListener changeCheckerRow2 = new OnCheckedChangeListener() {
	    @Override
	    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	        if (isChecked){
	            if (buttonView == btn_13) {
	            	btn_14.setChecked(false);
	            	btn_15.setChecked(false);
	            	chosen_time2 = 13;
	            }
	            if (buttonView == btn_14) {
	            	btn_13.setChecked(false);
	            	btn_15.setChecked(false);
	            	chosen_time2 = 14;
	            }
	            if (buttonView == btn_15) {
	            	btn_13.setChecked(false);
	            	btn_14.setChecked(false);
	            	chosen_time2 = 15;
	            }
	        }
	    }
	};
	OnCheckedChangeListener changeCheckerRow3 = new OnCheckedChangeListener() {
	    @Override
	    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	        if (isChecked){
	            if (buttonView == btn_16) {
	            	btn_17.setChecked(false);
	            	btn_18.setChecked(false);
	            	btn_19.setChecked(false);
	            	chosen_time3 = 16;
	            }
	            if (buttonView == btn_17) {
	            	btn_16.setChecked(false);
	            	btn_18.setChecked(false);
	            	btn_19.setChecked(false);
	            	chosen_time3 = 17;
	            }
	            if (buttonView == btn_18) {
	            	btn_16.setChecked(false);
	            	btn_17.setChecked(false);
	            	btn_19.setChecked(false);
	            	chosen_time3 = 18;
	            }
	            if (buttonView == btn_19) {
	            	btn_16.setChecked(false);
	            	btn_17.setChecked(false);
	            	btn_18.setChecked(false);
	            	chosen_time3 = 19;
	            }
	        }
	    }
	};
	OnCheckedChangeListener changeCheckerRow4 = new OnCheckedChangeListener() {
	    @Override
	    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	        if (isChecked){
	            if (buttonView == btn_20) {
	            	btn_21.setChecked(false);
	            	btn_22.setChecked(false);
	            	btn_23.setChecked(false);
	            	chosen_time4 = 20;
	            }
	            if (buttonView == btn_21) {
	            	btn_20.setChecked(false);
	            	btn_22.setChecked(false);
	            	btn_23.setChecked(false);
	            	chosen_time4 = 21;
	            }
	            if (buttonView == btn_22) {
	            	btn_20.setChecked(false);
	            	btn_21.setChecked(false);
	            	btn_23.setChecked(false);
	            	chosen_time4 = 22;
	            }
	            if (buttonView == btn_23) {
	            	btn_20.setChecked(false);
	            	btn_21.setChecked(false);
	            	btn_22.setChecked(false);
	            	chosen_time4 = 23;
	            }
	        }
	    }
	};
}
