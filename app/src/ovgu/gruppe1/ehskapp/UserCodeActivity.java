package ovgu.gruppe1.ehskapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserCodeActivity extends Activity implements OnKeyListener {
	
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
        textField1.setOnKeyListener(this);
        textField2.setOnKeyListener(this);
        textField3.setOnKeyListener(this);
        textField4.setOnKeyListener(this);
        textField5.setOnKeyListener(this);
        
        submitUserCodeButton = (Button) findViewById(R.id.button1);
        submitUserCodeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(text1 == "" || text2 == "" || text3 == "" || text4 == "" || text5 == ""){
					showErrorMessager("Fehlende Eingabe");
				} else {
					finishActivity();
				}
				
			}
		});
        
    }

	private void showErrorMessager(String message){
		Context context = getApplicationContext();
		int time = 3000;
		
		Toast toast = Toast.makeText(context, message, time);
		toast.show();

	}
	
	
	/**
	 * closes the activity
	 */
	private void finishActivity(){
		String code = text1+text2+text3+text4+text5;	
		SharedPreferences preferences = this.getSharedPreferences("usercode", MODE_PRIVATE);
		Editor edit = preferences.edit();
		edit.putString("usercode", code);
		edit.commit();
		Toast.makeText(this, "Usercode saved", 3000).show();
		this.finish();
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		
		if(v==textField1){
			String text = textField1.getText().toString();
			text1 = text;
			if(text1.length()>1){
				textField1.setText("" + text1.toCharArray()[0]);
			}
		} //else
		
		if(v==textField2){
			String text = textField2.getText().toString();
			text2 = text;
			if(text2.length()>1){
				textField2.setText("" + text2.toCharArray()[0]);
			}
		} //else 
		
		if(v==textField3){
			String text = textField3.getText().toString();
			text3 = text;
			if(text3.length()>1){
				textField3.setText("" + text3.toCharArray()[0]);
			}
		} //else 
			
		if(v==textField4){
			String text = textField4.getText().toString();
			text4 = text;
			if(text4.length()>1){
				textField4.setText("" + text4.toCharArray()[0]);
			}
		} //else
		if(v==textField5){
			String text = textField5.getText().toString();
			text5 = text;
			if(text5.length()>1){
				textField5.setText("" + text5.toCharArray()[0]);
			}
		}
		
		return false;
	}

}
