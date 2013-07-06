package ovgu.gruppe1.ehskapp.test;
import ovgu.gruppe1.ehskapp.UserCodeActivity;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.EditText;


public class UserCodeActivityTest extends ActivityInstrumentationTestCase2<UserCodeActivity> {

	private Activity _activity;
	private EditText textField1, textField2, textField3, textField4, textField5;

	
	public UserCodeActivityTest(String name) {
		super("ovgu.gruppe1.ehskapp",UserCodeActivity.class);
		setName(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		_activity=getActivity();
		textField1=(EditText) _activity.findViewById(ovgu.gruppe1.ehskapp.R.id.editText1);
		textField2=(EditText) _activity.findViewById(ovgu.gruppe1.ehskapp.R.id.EditText01);
		textField3=(EditText) _activity.findViewById(ovgu.gruppe1.ehskapp.R.id.EditText02);
		textField4=(EditText) _activity.findViewById(ovgu.gruppe1.ehskapp.R.id.EditText03);
		textField5=(EditText) _activity.findViewById(ovgu.gruppe1.ehskapp.R.id.EditText04);

	}
	
	public void testViewsAreNotNull(){
		assertNotNull(textField1);
		assertNotNull(textField2);
		assertNotNull(textField3);
		assertNotNull(textField4);
		assertNotNull(textField5);
	}
	
	private boolean thereAreNoNumbers(EditText e){
		e.clearComposingText();
		TouchUtils.tapView(this, e);
		sendKeys("5");
		if(e.getText().toString().equals("")){
			return true;
		} {
			return false;
		}
	}
	
	public void testThereAreOnlyNumbers(){
		assertTrue(thereAreNoNumbers(textField1));
		assertTrue(thereAreNoNumbers(textField2));
		assertTrue(thereAreNoNumbers(textField3));
		assertTrue(thereAreNoNumbers(textField4));
		assertTrue(thereAreNoNumbers(textField5));
	}

}
