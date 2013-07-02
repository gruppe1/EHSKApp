package ovgu.gruppe1.ehskapp;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
		String usercode = preferences.getString("usercode", "");
		Editor edit = preferences.edit();
		Log.d("Main", ""+ usercode.length());
				
		if (usercode.length() != 0) {
			
			String data = usercode + ".csv";
			Log.d("Main", ""+ CSVWriter.existsFile(data));
			if (CSVWriter.existsFile(data) == false) {
				edit.clear();
				edit.commit();
			}
			
		}
		
		preferences = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
		usercode = preferences.getString("usercode", "");
		
		if (usercode.length() == 0) {
			
			Intent popupIntent = new Intent(this, UserCodeActivity.class);
			startActivity(popupIntent);
		}
				

		Button btn_usercode = (Button) findViewById(R.id.btn_usercode);
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
		});

		
	}
	

}