package Assignment2;

/*
 * create a Java pgm to filter the file types and display only files of specific extension type.
 */

import java.io.File;
import java.io.FilenameFilter;

public class JavaIOQ1 {
	/* declare constants */
	/** file directory */
	private static final String FILEPATH = "/User/Yuecheng/Desktop/";
	
	/** file type to be filtered */
	private static String FILENAME = "text1.txt"; 
	
	public static void main(String[] args) {
		JavaIOQ1 instance = new JavaIOQ1();
		try {
			File f = new File(FILEPATH + FILENAME);
			instance.fileFilter(f);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void fileFilter(File f){
		FilenameFilter ff = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String s) {
				if (s.lastIndexOf('.') > 0){
					int ind = s.lastIndexOf('.'); // index of . in the string
					String str = s.substring(ind); // save the extension in str
					if (str.equals(".txt")) {
						return true;
					}
				} else {
					System.out.println("Please re-enter the file type you want.e.g.: filter.txt");
				}
				return false;
			}
		};
		File[] paths = f.listFiles(ff);
		for (File path:paths) {
			System.out.println(path);
		}
	}
}
