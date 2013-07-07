package ovgu.gruppe1.ehskapp.test;

import java.io.File;
import java.io.FileNotFoundException;

import android.os.Environment;

import ovgu.gruppe1.ehskapp.CSVWriter;

public class CSVWriterTest extends android.test.AndroidTestCase {
	
	public CSVWriterTest(String name){
		super();
		setName(name);
	}
	
	public void testFileExists() throws FileNotFoundException{
		
		String[] line = {"","","","","","","",""};
		
		CSVWriter.writeLine(line, "abcde.csv");
		
		File file = new File(Environment.getExternalStorageDirectory().getPath()
				+ "/" +"abcde.csv");
		
		assertTrue(file.exists());
	}
}
