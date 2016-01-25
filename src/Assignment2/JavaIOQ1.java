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
	private static String FILEEXTENSION= ".txt"; 
	
	JavaIOQ1() {
//		this.filePath = path;
//		this.fileExtension = extension;
	}
	
	public static void main(String[] args) {
		JavaIOQ1 instance = new JavaIOQ1();
		System.out.println(instance.displayFiles(FILEPATH, FILEEXTENSION));
	}
	
	/* filter main method, override accept(File dir, String name){} method */
	public String[] fileFilter(File f, String ext){
		FilenameFilter fileNameFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.lastIndexOf('.') > 0){
					int ind = name.lastIndexOf('.'); // index of . in the string
					String str = name.substring(ind); // save the extension in str
					if (str.equals(ext)) {
						return true;
					}
				}
				return false;
			}
		};
		File[] paths = f.listFiles(fileNameFilter);
		String[] output = new String[paths.length];
		for (int i = 0; i < paths.length; i++) {
			output[i] = paths[i].getPath();
		}
		return output;
	}
	
	public String[] displayFiles(String path, String ext) {
		try {
			File f = new File(path);
			String[] str = fileFilter(f, ext);
			return str;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
}
