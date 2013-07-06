package ovgu.gruppe1.ehskapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Activity called first when the app starts and call other activities
 * @author Gruppe 1
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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

				/*String[] str1 = { "Code", "Datum", "Alarmzeit", "Antwortzeit",
						"Abbruch", "Kontakte", "Stunden", "Minuten" };
				String[] str2 = { "lbrht", "22.06.2013", "21:23", "21:24", "0",
						"0", "0", "0" };
				try {
					CSVWriter.writeLine(str1, Environment.getExternalStorageDirectory().getPath()+"/Probandencode.csv");
					CSVWriter.writeLine(str2, Environment.getExternalStorageDirectory().getPath()+"/Probandencode.csv");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), "External SD card not mounted", Toast.LENGTH_LONG).show();
					e.printStackTrace();				
				}*/
				
				
//				
//				Intent intent = new Intent(MainActivity.this,
//						QuickPrefsActivity.class);
//				startActivity(intent);
			}
		});

		Button btn_time_chooser = (Button) findViewById(R.id.btn_time_chooser);
		btn_time_chooser.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View v) {
				Intent time_chooserIntent = new Intent(MainActivity.this, TimeChooserActivity.class);
				startActivity(time_chooserIntent);
			}
		});
		
		Button btn_alarm = (Button) findViewById(R.id.btn_alarm);
		btn_alarm.setOnClickListener(new OnClickListener() {


			@Override
			public void onClick(View v) {
				Intent alarmIntent = new Intent(MainActivity.this, Alarm.class);
				startActivity(alarmIntent);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	/*public boolean onOptionsItemSelected(MenuItem item) {
	    switch(item.getItemId()) {
	        case R.id.menuitem_preferences:
	            Intent intentPreferences = new Intent(MainActivity.this, QuickPrefsActivity.class);
	            startActivity(intentPreferences);
	            return true;
	        case R.id.menuitem_popup:
	        	Intent intentPopup = new Intent(MainActivity.this, PopupActivity.class);
	            startActivity(intentPopup);
	            return true;
	    }


	    return super.onOptionsItemSelected(item);
	} */

}