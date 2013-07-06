package ovgu.gruppe1.ehskapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
/*import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;*/

/**
 * Activity started first an start other activities
 * TODO wenn man app im betrieb startet sollte die TimeChooseActivity starten und nicht der Alarm,
 * da er kein layout hat
 * @author Gruppe 1
 * 
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this.getBaseContext());
		String usercode = preferences.getString("usercode", "");
		Editor edit = preferences.edit();
		// Log.d("Main", ""+ usercode.length());

		// checks if usercode exists in sharedPreferences
		if (usercode.length() != 0) {

			String file = usercode + ".csv";
			// Log.d("Main", ""+ CSVWriter.existsFile(data));

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
		
		/*preferences = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
		usercode = preferences.getString("usercode", "");
		
		
		if (usercode.length() == 0) {
			
			Intent UserCodeIntent = new Intent(this, UserCodeActivity.class);
			startActivity(UserCodeIntent);
		}*/
				

		/*Button btn_usercode = (Button) findViewById(R.id.btn_usercode);
		btn_usercode.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View v) {
				Intent popupIntent = new Intent(MainActivity.this, UserCodeActivity.class);
				startActivity(popupIntent);
			}
		});
		
		
		
		
		Button btn_preferences = (Button) findViewById(R.id.btn_preferences);
		btn_preferences.setOnClickListener(new OnClickListener() {
		
			
			@Override
			public void onClick(View v) {


			}
		});
	
		Button btn_popup = (Button) findViewById(R.id.btn_time_chooser);
		btn_popup.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View v) {
				Intent popupIntent = new Intent(MainActivity.this, TimeChooserActivity.class);
				startActivity(popupIntent);
			}
		});
		
		Button btn_questions = (Button) findViewById(R.id.btn_questions);
		btn_questions.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View v) {
				Intent popupIntent = new Intent(MainActivity.this, QuestionsActivity.class);
				startActivity(popupIntent);
			}
		});*/
		
		
		finish();
	}
}