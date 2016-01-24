package Assignment2;

/*
 * create a Java pgm to filter the file types and display only files of specific extension type.
 */

import java.io.File;
import java.io.FilenameFilter;

public class JavaIOQ1 {
	/* declare constants */
	/** file directory */
	private static final String FILEPATH = "/Users/Yuecheng/Desktop/";
	
	/** file type to be filtered */
	private static String FILEEXTENTSION= ".txt"; 
	
	public static void main(String[] args) {
		JavaIOQ1 instance = new JavaIOQ1();
		try {
			File f = new File(FILEPATH);
//			System.out.println(f);
			instance.fileFilter(f);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/* filter main method, override accept(File dir, String name){} method */
	public void fileFilter(File f){
		FilenameFilter fileNameFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.lastIndexOf('.') > 0){
					int ind = name.lastIndexOf('.'); // index of . in the string
					String str = name.substring(ind); // save the extension in str
					if (str.equals(FILEEXTENTSION)) {
						return true;
					}
				}
				return false;
			}
		};
		File[] paths = f.listFiles(fileNameFilter);
		for (File path:paths) {
			System.out.println(path);
		}
	}
}
