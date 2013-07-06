package ovgu.gruppe1.ehskapp.test;
import ovgu.gruppe1.ehskapp.R;
import ovgu.gruppe1.ehskapp.TimeChooserActivity;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ToggleButton;


public class TimeChooserActivityTest extends ActivityInstrumentationTestCase2<TimeChooserActivity> {

	private Activity _activity;
	// Row1
	ToggleButton btn_9, btn_10, btn_11, btn_12;
	// Row2
	ToggleButton btn_13, btn_14, btn_15;
	// Row3
	ToggleButton btn_16, btn_17, btn_18, btn_19;
	// Row4
	ToggleButton btn_20, btn_21, btn_22, btn_23;
	// Save
	Button btn_save_times;

	
	public TimeChooserActivityTest(String name) {
		super("ovgu.gruppe1.ehskapp",TimeChooserActivity.class);
		setName(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		_activity=getActivity();
		// Row1
		btn_9 = (ToggleButton) _activity.findViewById(R.id.btn_time_9);
		btn_10 = (ToggleButton) _activity.findViewById(R.id.btn_time_10);
		btn_11 = (ToggleButton) _activity.findViewById(R.id.btn_time_11);
		btn_12 = (ToggleButton) _activity.findViewById(R.id.btn_time_12);
		// Row2
		btn_13 = (ToggleButton) _activity.findViewById(R.id.btn_time_13);
		btn_14 = (ToggleButton) _activity.findViewById(R.id.btn_time_14);
		btn_15 = (ToggleButton) _activity.findViewById(R.id.btn_time_15);
		// Row3
		btn_16 = (ToggleButton) _activity.findViewById(R.id.btn_time_16);
		btn_17 = (ToggleButton) _activity.findViewById(R.id.btn_time_17);
		btn_18 = (ToggleButton) _activity.findViewById(R.id.btn_time_18);
		btn_19 = (ToggleButton) _activity.findViewById(R.id.btn_time_19);
		// Row4
		btn_20 = (ToggleButton) _activity.findViewById(R.id.btn_time_20);
		btn_21 = (ToggleButton) _activity.findViewById(R.id.btn_time_21);
		btn_22 = (ToggleButton) _activity.findViewById(R.id.btn_time_22);
		btn_23 = (ToggleButton) _activity.findViewById(R.id.btn_time_23);
		// Save
		btn_save_times = (Button) _activity.findViewById(R.id.btn_save_times);

	}
	
	public void testViewsAreNotNull(){
		assertNotNull(btn_9);
		assertNotNull(btn_10);
		assertNotNull(btn_11);
		assertNotNull(btn_12);
		assertNotNull(btn_13);
		assertNotNull(btn_14);
		assertNotNull(btn_15);
		assertNotNull(btn_16);
		assertNotNull(btn_17);
		assertNotNull(btn_18);
		assertNotNull(btn_19);
		assertNotNull(btn_20);
		assertNotNull(btn_21);
		assertNotNull(btn_22);
		assertNotNull(btn_23);
	}
}
