package ovgu.gruppe1.ehskapp.test;
import ovgu.gruppe1.ehskapp.QuestionsActivity;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.EditText;


public class QuestionsActivityTest extends ActivityInstrumentationTestCase2<QuestionsActivity> {

	private Activity _activity;
	private EditText numberOfContactsView, hoursView, minutesView;
	
	public QuestionsActivityTest(String name) {
		super("ovgu.gruppe1.ehskapp",QuestionsActivity.class);
		setName(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		_activity=getActivity();
		numberOfContactsView=(EditText) _activity.findViewById(ovgu.gruppe1.ehskapp.R.id.editText1);
		hoursView=(EditText) _activity.findViewById(ovgu.gruppe1.ehskapp.R.id.EditText01);
		minutesView=(EditText) _activity.findViewById(ovgu.gruppe1.ehskapp.R.id.EditText02);

	}
	
	public void testViewsAreNot_Null(){
		assertNotNull(numberOfContactsView);
		assertNotNull(hoursView);
		assertNotNull(minutesView);
	}
	
	private boolean thereAreOnlyNumbers(EditText e){
		e.clearComposingText();
		TouchUtils.tapView(this, e);
		sendKeys("b");
		if(e.getText().toString().equals("")){
			return true;
		} {
			return false;
		}
	}
	
	public void testThereAreOnlyNumbers(){
		assertTrue(thereAreOnlyNumbers(numberOfContactsView));
		assertTrue(thereAreOnlyNumbers(hoursView));
		assertTrue(thereAreOnlyNumbers(minutesView));
	}

}
