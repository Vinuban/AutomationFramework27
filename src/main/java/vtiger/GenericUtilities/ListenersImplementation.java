package vtiger.GenericUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * This class provides implementation for the ITestListener Interface of TestNG
 * @author USER
 *
 */
public class ListenersImplementation implements ITestListener
{

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"==== test script execution started ====");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"==== test script PASSED ====");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"==== test script FAILED ====");
		System.out.println(result.getThrowable());
		
		WebDriverUtility wUtil = new WebDriverUtility();
		
		String screenShotName = methodName+"-"+new JavaUtility().getSystemDateInFormat();
		
		try {
			wUtil.takeScreenshot(BaseClass.sDriver, screenShotName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"==== test script SKIPPED ====");
		System.out.println(result.getThrowable()); // peint the exception
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	

}
