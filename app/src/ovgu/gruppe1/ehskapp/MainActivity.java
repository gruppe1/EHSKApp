package ovgu.gruppe1.ehskapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Activity started first an start other activities
 * 
 * TODO Reset funktioniert nicht, könnte an der onResume in Alarm liegen...
 * 
 * @author 1
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this.getBaseContext());
		String usercode = preferences.getString("usercode", "");
		Editor edit = preferences.edit();

		// checks if usercode exists in sharedPreferences
		if (usercode.length() != 0) {

			String file = usercode + ".csv";

			/*
			 * if usercode exists in sharedPrefs but there is noch csv file (app
			 * was reseted) delete usercode in sharedPrefs (and start
			 * UserCodeActivity)
			 */
			if (!CSVWriter.existsFile(file)) {
				edit.clear();
				edit.commit();

				Intent UserCodeIntent = new Intent(this, UserCodeActivity.class);
				startActivity(UserCodeIntent);
			} else {// csv file exists -> start TimeChooserActivity
				Intent TimeChooserIntent = new Intent(this,
						TimeChooserActivity.class);
				startActivity(TimeChooserIntent);
			}

		} else {/*
				 * no usercode in sharedPrefs i.e. first start of app or reseted
				 * -> start UserCodeActivity
				 */
			Intent UserCodeIntent = new Intent(this, UserCodeActivity.class);
			startActivity(UserCodeIntent);
		}

		finish();
	}
}