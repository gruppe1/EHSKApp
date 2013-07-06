package ovgu.gruppe1.ehskapp;

import java.io.FileNotFoundException;
import java.util.Locale;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity to let the user input his usercode consisting of five letters
 * an empty .csv file named as the usercode will be created on SD card
 * 
 * @author 1
 *
 */
public class UserCodeActivity extends Activity {

	EditText textField1, textField2, textField3, textField4, textField5;
	String text1, text2, text3, text4, text5;
	Button submitUserCodeButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_code_layout);

		textField1 = (EditText) findViewById(R.id.editText1);
		textField2 = (EditText) findViewById(R.id.EditText01);
		textField3 = (EditText) findViewById(R.id.EditText02);
		textField4 = (EditText) findViewById(R.id.EditText03);
		textField5 = (EditText) findViewById(R.id.EditText04);

		submitUserCodeButton = (Button) findViewById(R.id.btn_save_code);
		submitUserCodeButton.setOnClickListener(new OnClickListener() { //Error: Null-Pointer-Exception

			@Override
			public void onClick(View v) {
				text1 = textField1.getText().toString();
				text2 = textField2.getText().toString();
				text3 = textField3.getText().toString();
				text4 = textField4.getText().toString();
				text5 = textField5.getText().toString();

				if (text1.equals("") || text2.equals("") || text3.equals("")
						|| text4.equals("") || text5.equals("")) {
					showMessage("Fehlende Eingabe");
				} else {
					finishActivity();
				}
			}
		});

	}
	
	/**
	 * shows message to the user
	 * 
	 * @param message
	 */
	private void showMessage(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
	}

	/**
	 * closes the activity
	 */
	private void finishActivity() {
		String code = text1 + text2 + text3 + text4 + text5;
		code = code.toLowerCase(Locale.getDefault());
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this.getBaseContext());
		Editor edit = preferences.edit();
		edit.putString("usercode", code);
		edit.commit();

		try {
			CSVWriter.writeLine(null, code + ".csv");
			showMessage("Probandencode gespeichert");
		} catch (FileNotFoundException e) {
			showMessage("Kein externer Speicher vorhanden");
			e.printStackTrace();
		}
		
		Intent TimeChooserIntent = new Intent(this, TimeChooserActivity.class);
		startActivity(TimeChooserIntent);
		
		this.finish();
	}
}
