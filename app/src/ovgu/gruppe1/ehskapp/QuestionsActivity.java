package ovgu.gruppe1.ehskapp;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.view.View.OnClickListener;


public class QuestionsActivity extends Activity implements OnKeyListener {
	
	EditText numberOfContactsView, hoursView, minutesView; 
	String numberOfContacts, hours, minutes;
	Button submitButton, abortButton;

	/**
	 * set the variables to the text fields and the button
	 * set event listener
	 */
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);
        
        numberOfContactsView = (EditText) findViewById(R.id.editText1);
        hoursView = (EditText) findViewById(R.id.EditText01);
        minutesView = (EditText) findViewById(R.id.EditText02);
        
        minutesView.setOnKeyListener(this);
        hoursView.setOnKeyListener(this);
        numberOfContactsView.setOnKeyListener(this);
        
        submitButton = (Button) findViewById(R.id.button1);
        abortButton = (Button) findViewById(R.id.Button01);
        
        /**
         * 
         */
        submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(hours == null || minutes == null || numberOfContacts==null){
					showErrorMessager("Fehlende Eingabe");
				} else {
					String[] line = new String[8];
					line[0]="";//user code
					Date date = new Date();
					line[1]= new SimpleDateFormat("dd.MM.yyyy").format(date);//date
					line[2]="";//alert time
					line[3]= new SimpleDateFormat("hh:mm").format(date);//answer time
					line[4]="0";//wasn't aborted
					line[5]=numberOfContacts;
					line[6]=hours;
					line[7]=minutes;
					String filename = "";
					try {
						new CSVWriter();
						CSVWriter.writeLine(line, filename);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
				finishActivity();
			}
		});
        
        abortButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String[] line = new String[8];
				line[0]="";//user code
				Date date = new Date();
				line[1]= new SimpleDateFormat("dd.MM.yyyy").format(date);//date
				line[2]="";//alert time
				line[3]= "-1";//answer time
				line[4]="1";//was aborted
				line[5]="-1";
				line[6]="-1";
				line[7]="-1";
				String filename = "";
				try {
					new CSVWriter();
					CSVWriter.writeLine(line, filename);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				finishActivity();
			}
		});

    }
	
	private void showErrorMessager(String message){
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Fehler");
		alertDialog.setMessage(message);
		alertDialog.show();

	}
	
	private void finishActivity(){
		this.finish();
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		
		if(v==numberOfContactsView){
			String text = numberOfContactsView.getText().toString();
			numberOfContacts=text;
		}
		
		if(v==hoursView){
			String text = hoursView.getText().toString();
			hours=text;
		}

		if(v==minutesView){
			String text = minutesView.getText().toString();
			minutes=text;
		}
		
		return false;
	}	
	
}
