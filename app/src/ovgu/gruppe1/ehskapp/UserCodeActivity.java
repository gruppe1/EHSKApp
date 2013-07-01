package ovgu.gruppe1.ehskapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

public class UserCodeActivity extends Activity implements OnKeyListener {
	
	EditText a, b, c, d, e;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_code_layout);
        
        a = (EditText) findViewById(R.id.editText1);
        b = (EditText) findViewById(R.id.EditText01);
        c = (EditText) findViewById(R.id.EditText02);
        d = (EditText) findViewById(R.id.EditText03);
        e = (EditText) findViewById(R.id.EditText04);
        a.setOnKeyListener(this);
        b.setOnKeyListener(this);
        c.setOnKeyListener(this);
        d.setOnKeyListener(this);
        e.setOnKeyListener(this);
        
    }

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		
		String text;
		
		if(v==a){
			text = a.getText().toString();
			if(text.length()>1){
				a.setText("" + text.toCharArray()[0]);
			}
		} else
		
		if(v==b){
			text = b.getText().toString();
			if(text.length()>1){
				b.setText("" + text.toCharArray()[0]);
			}
		} else 
		
		if(v==c){
			text = c.getText().toString();
			if(text.length()>1){
				c.setText("" + text.toCharArray()[0]);
			}
		} else 
			
		if(v==d){
			text = d.getText().toString();
			if(text.length()>1){
				d.setText("" + text.toCharArray()[0]);
			}
		} else {
			text = e.getText().toString();
			if(text.length()>1){
				e.setText("" + text.toCharArray()[0]);
			}
		}
		
		return false;
	}

}
