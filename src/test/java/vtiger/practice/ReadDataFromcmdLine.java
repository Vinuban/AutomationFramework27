package vtiger.practice;

import org.testng.annotations.Test;

public class ReadDataFromcmdLine {
	
	@Test
	public void test()
	{
		String BROWSER = System.getProperty("browser"); //runtime parameter
		System.out.println(BROWSER);
		
		
		String BROWSER1 = System.getProperty("browser"); //runtime parameter
		System.out.println(BROWSER);
	}

}
