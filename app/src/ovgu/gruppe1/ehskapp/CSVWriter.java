package ovgu.gruppe1.ehskapp;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;


public class CSVWriter  {
	
	/**
	 * Writes a new line in an existing .csv file, if non exists
	 * a new one will be created
	 * @param line stringarray with columns for .csv file
	 * @param filename
	 * @param separator
	 * @throws FileNotFoundException if no external storage is mounted
	 */
	private static void writeLine(String[] line, String filename, char separator) throws FileNotFoundException {
		if(!isExternalStorageWritable())
			throw new FileNotFoundException("No external storage mounted!");
			
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(filename, true);
			
			for(int i=0; i<line.length; i++) {
				
				fw.write(line[i]);
				if(i != line.length-1)
					fw.write(separator);
			}

			fw.write("\n");
			fw.flush();
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
			
	}
	
	public static void writeLine(String[] line, String filename) throws FileNotFoundException {
		writeLine(line, filename, ';');
	}
	
	private static boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

}
