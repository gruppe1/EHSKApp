package ovgu.gruppe1.ehskapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn_preferences = (Button) findViewById(R.id.btn_preferences);
		btn_preferences.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent mainIntent = new Intent(MainActivity.this, QuickPrefsActivity.class);
				startActivity(mainIntent);
			}
		});
		
		Button btn_popup = (Button) findViewById(R.id.btn_popup);
		btn_popup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent popupIntent = new Intent(MainActivity.this, PopupActivity.class);
				startActivity(popupIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
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
	}   
}