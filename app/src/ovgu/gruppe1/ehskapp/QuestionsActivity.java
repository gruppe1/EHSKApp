package ovgu.gruppe1.ehskapp;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity to let the user make his input to the asked questions
 * the answers will be stored in a .csv file on SD card 
 * @author Gruppe 1
 *
 */
public class QuestionsActivity extends Activity {

	EditText numberOfContactsView, hoursView, minutesView;
	String numberOfContacts, hours, minutes;
	Button submitButton, abortButton;

	/**
	 * set the variables to the text fields and the button set event listener
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_layout);
		
		Alarm newAlarm1 = new Alarm();

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this.getBaseContext());
		final String usercode = preferences.getString("usercode", "");

		numberOfContactsView = (EditText) findViewById(R.id.editText1);
		hoursView = (EditText) findViewById(R.id.EditText01);
		minutesView = (EditText) findViewById(R.id.EditText02);

		submitButton = (Button) findViewById(R.id.button1);
		abortButton = (Button) findViewById(R.id.Button01);

		/**
         * 
         */
		submitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				numberOfContacts = numberOfContactsView.getText().toString();
				hours = hoursView.getText().toString();
				minutes = minutesView.getText().toString();

				if (hours.equals("") || minutes.equals("")
						|| numberOfContacts.equals("")) {
					showMessage("Fehlende Eingabe");
				} else {
					String[] line = new String[8];// data which will be written
													// into a csv file
					line[0] = usercode;// user code
					Date date = new Date();
					line[1] = new SimpleDateFormat("dd.MM.yyyy", Locale
							.getDefault()).format(date);// date
					line[2] = "";// alert time
					line[3] = new SimpleDateFormat("hh:mm", Locale.getDefault())
							.format(date);// answer
					// time
					line[4] = "0";// wasn't aborted
					line[5] = numberOfContacts;
					line[6] = hours;
					line[7] = minutes;
					String filename = usercode + ".csv";
					try {
						CSVWriter.writeLine(line, filename);
						showMessage("Kontaktzeiten gespeichert");
					} catch (FileNotFoundException e) {
						showMessage("Kein externer Speicher vorhanden");
						e.printStackTrace();
					}
					
					Intent alarmIntent = new Intent(QuestionsActivity.this, Alarm.class);
					startActivity(alarmIntent);
					
					finishActivity();
				}
			}
		});

		/**
		 * action when the user aborts the question
		 */
		abortButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String[] line = new String[8];// data which will be written into
												// a csv file
				line[0] = usercode;// user code
				Date date = new Date();
				line[1] = new SimpleDateFormat("dd.MM.yyyy", Locale
						.getDefault()).format(date);// date
				line[2] = "";// TODO: alert time
				line[3] = "-77";// answer time
				line[4] = "1";// was aborted
				line[5] = "-77";
				line[6] = "-77";
				line[7] = "-77";
				String filename = usercode + ".csv";
				try {
					CSVWriter.writeLine(line, filename);
				} catch (FileNotFoundException e) {
					showMessage("Kein externer Speicher vorhanden");
					e.printStackTrace();
				}
				
				Intent alarmIntent = new Intent(QuestionsActivity.this, Alarm.class);
				startActivity(alarmIntent);
				
				finishActivity();
			}
		});
		
		
		
	}

	/**
	 * shows message to the user
	 * 
	 * @param message
	 */
	private void showMessage(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG)
				.show();
	}

	/**
	 * closes the activity
	 */
	private void finishActivity() {
		this.finish();
	}
}
