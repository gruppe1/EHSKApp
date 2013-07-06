package ovgu.gruppe1.ehskapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import android.os.Environment;

/**
 * Class for the IO Management of the App
 * (only access to external SD card not to internal memory)
 * -create new empty .csv file
 * -add line to file
 * -check whether file exists or not
 * -create new folders
 * -check if SD card is mounted and writable
 * 
 * @author Gruppe 1
 * 
 */
public class CSVWriter {

	/**
	 * Writes a new line in an existing .csv file, if non exists a new one will
	 * be created
	 * 
	 * @param line
	 *            stringarray with columns for .csv file
	 * @param filename
	 * @param separator
	 * @throws FileNotFoundException
	 *             if no external storage is mounted
	 */
	private static void writeLine(String[] line, String filename, char separator)
			throws FileNotFoundException {
		if (!isExternalStorageWritable())
			throw new FileNotFoundException("No external storage mounted!");

		FileWriter fw = null;

		try {
			fw = new FileWriter(Environment.getExternalStorageDirectory()
					.getPath() + "/" + filename, true);

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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * Public writeLine function with ';' as standard separator
	 * @param line
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public static void writeLine(String[] line, String filename)
			throws FileNotFoundException {
		writeLine(line, filename, ';');
	}

	/**
	 * Checks whether file at given path exists 
	 * @param path
	 * @return true if file exits else false
	 */
	public static boolean existsFile(String path) {
		File file = new File(Environment.getExternalStorageDirectory()
				.getPath() + "/" + path);
		return file.exists();
	}

	/**
	 * Add a Directory on SD-card with all new subfolders in the given path that
	 * not exist (e.g. existingFolder/newFolder1/newFolder2)
	 * @param path
	 * @throws IOException
	 */
	public static void makeDirectoryOnSD(String path) throws IOException {
		if (!isExternalStorageWritable())
			throw new FileNotFoundException("No external storage mounted!");

		if (path == null)
			return;

		File folder = new File(Environment.getExternalStorageDirectory()
				.getPath() + "/" + path);
		if (!folder.mkdirs()) {
			throw new IOException("Can't create Directory");
		}
	}

	/**
	 * Checks if an external SD card is mounted and writable
	 * @return true if external storage is writable (sd card is mounted), false
	 *         instead
	 */
	private static boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

}
