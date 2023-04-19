package vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFilePractice {

	public static void main(String[] args) throws IOException {
		// Step 1:Load the file in java readable format using file input stream
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		
		
		// Step 2: create an object of properties from java.util
		Properties pObj = new Properties();
		
		// Step 3: Load file input stream into properties
		pObj.load(fis);
		
		// Step 4: using the keys read the value
		String BROWSER = pObj.getProperty("browser");
		System.out.println(BROWSER);

	}

}
