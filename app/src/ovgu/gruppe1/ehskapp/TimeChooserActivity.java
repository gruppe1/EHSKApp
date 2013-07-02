package ovgu.gruppe1.ehskapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

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
	}
	
	Context mContext = getBaseContext();
	SharedPreferences mPrefs = mContext.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE);
    Editor mEditor = mPrefs.edit();
    
	OnCheckedChangeListener changeCheckerRow1 = new OnCheckedChangeListener() {
	    @Override
	    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	    	mEditor.putInt("time1", Integer.parseInt(buttonView.getText().toString()));
	    	mEditor.commit();
	        if (isChecked){
	            if (buttonView == btn_9) {
	            	btn_10.setChecked(false);
	            	btn_11.setChecked(false);
	            	btn_12.setChecked(false);
	            }
	            if (buttonView == btn_10) {
	            	btn_9.setChecked(false);
	            	btn_11.setChecked(false);
	            	btn_12.setChecked(false);
	            }
	            if (buttonView == btn_11) {
	            	btn_9.setChecked(false);
	            	btn_10.setChecked(false);
	            	btn_12.setChecked(false);
	            }
	            if (buttonView == btn_12) {
	            	btn_9.setChecked(false);
	            	btn_10.setChecked(false);
	            	btn_11.setChecked(false);
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
	            }
	            if (buttonView == btn_14) {
	            	btn_13.setChecked(false);
	            	btn_15.setChecked(false);
	            }
	            if (buttonView == btn_15) {
	            	btn_13.setChecked(false);
	            	btn_14.setChecked(false);
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
	            }
	            if (buttonView == btn_17) {
	            	btn_16.setChecked(false);
	            	btn_18.setChecked(false);
	            	btn_19.setChecked(false);
	            }
	            if (buttonView == btn_18) {
	            	btn_16.setChecked(false);
	            	btn_17.setChecked(false);
	            	btn_19.setChecked(false);
	            }
	            if (buttonView == btn_19) {
	            	btn_16.setChecked(false);
	            	btn_17.setChecked(false);
	            	btn_18.setChecked(false);
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
	            }
	            if (buttonView == btn_21) {
	            	btn_20.setChecked(false);
	            	btn_22.setChecked(false);
	            	btn_23.setChecked(false);
	            }
	            if (buttonView == btn_22) {
	            	btn_20.setChecked(false);
	            	btn_21.setChecked(false);
	            	btn_23.setChecked(false);
	            }
	            if (buttonView == btn_23) {
	            	btn_20.setChecked(false);
	            	btn_21.setChecked(false);
	            	btn_22.setChecked(false);
	            }
	        }
	    }
	};
}
