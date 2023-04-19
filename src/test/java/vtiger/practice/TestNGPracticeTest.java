package vtiger.practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNGPracticeTest {
	
	@Test //(invocationCount = 2, priority = 1)//(priority = -1)
	
	public void createUserTest()
	{
		//Assert.fail();
		System.out.println("user created"); // passed // executed - fail
	}
	
	@Test //(enabled=false)//(dependsOnMethods = "createUserTest") //(priority = -2)
	public void modifyUserTest()
	{
		System.out.println("user modified"); // should not executed
	}
	
	@Test (dependsOnMethods = "createUserTest")//(priority = 2)
	public void deleteUserTest()
	{
		System.out.println("user deleted"); 
		
	}

}
