package ovgu.gruppe1.ehskapp;

import java.io.File;
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
			fw = new FileWriter(Environment.getExternalStorageDirectory().getPath() + "/" + filename, true);
			
			if (line != null) {

				for (int i = 0; i < line.length; i++) {

					fw.write(line[i]);
					if (i != line.length - 1)
						fw.write(separator);
				}

				fw.write("\n");
			} else {
				fw.write("");
			}
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
	
	public static boolean existsFile(String path) {
		File file = new File(Environment.getExternalStorageDirectory() + "/" + path);
		return file.exists();
	}
	
	public static void makeDirectoryOnSD(String path) throws IOException {
		if(!isExternalStorageWritable())
			throw new FileNotFoundException("No external storage mounted!");
		
		if(path == null)
			return;
		
		File folder = new File(Environment.getExternalStorageDirectory() + "/" + path);
		if(!folder.mkdirs()) {
			throw new IOException("Can't create Directory");
		}
	}
	
	private static boolean isExternalStorageWritable() {
	    String state = Environment.getExternalStorageState();
	    if (Environment.MEDIA_MOUNTED.equals(state)) {
	        return true;
	    }
	    return false;
	}

}
