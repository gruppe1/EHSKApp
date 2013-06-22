package ovgu.gruppe1.ehskapp;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btn_preferences = (Button) findViewById(R.id.btn_preferences);
		btn_preferences.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String[] str1 = { "Code", "Datum", "Alarmzeit", "Antwortzeit",
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
				}
				
				
				
				/*Intent intent = new Intent(MainActivity.this,
						QuickPrefsActivity.class);
				startActivity(intent);*/
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}