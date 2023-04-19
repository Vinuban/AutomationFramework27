package vtiger.Organization.Tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;
import vtiger.ObjectRepository.CreateNewOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationPage;

public class CreateOrganizationTest {
	@Test
	public void createOrgTest() throws IOException
	//public static void main(String[] args) throws IOException 
	{
		
		//Step 1: Create Object of Generic Utilities
		ExcelFileUtility eUtil = new ExcelFileUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();
		
		//Step 2: Read all the required Data
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String URL = pUtil.readDataFromPropertyFile("url");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		String ORGNAME = eUtil.readDataFromExcel("Organization", 1, 2)+jUtil.getRandomNumber();
		
		WebDriver driver = null;
		
		//Step 3: Launch the Browser - RUNTIME POLYMORPHISM
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("invalid browser name");
		}
		
		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver);
		driver.get(URL);
		
		//Step 4: Login to App
		//driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		//driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		//driver.findElement(By.id("submitButton")).click();
		
		//Step 5: Navigate to Organizations link
		//driver.findElement(By.linkText("Organizations")).click();
		
		//Step 6: Click on Create Organization Look up Image
		//driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//Step 7: Create organization with mandatory Fields
		//driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		
		//Step 8: Save
		//driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 9: Validate for Organization
		//String OrgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		//if(OrgHeader.contains(ORGNAME))
		//{
			//System.out.println(OrgHeader+" ---- PASS ----");
		//}
		//else
		//{
			//System.out.println(" ---- Failed ----");
		//}
		
		//Step 10: Logout of app
		//WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//wUtil.mouseHoverAction(driver, ele);
		//driver.findElement(By.linkText("Sign Out")).click();
		//System.out.println("Sign out successful");
		
		// Step 4: Login to App
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);

		// Step 5: Navigate to Organizations link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLink();

		// Step 6: Click on Create Organization Look up Image
		OrganizationPage op = new OrganizationPage(driver);
		op.clickOnCreateOrganizationLookUpImg();

		// Step 7: Create organization with mandatory Fields
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(ORGNAME);

		// Step 8: Validate for Organization
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String OrgHeader = oip.getOrganizationHeader();
		if (OrgHeader.contains(ORGNAME)) {
			System.out.println(OrgHeader + " --- Organization Created ---");
		} else {
			System.out.println(" ----Organization creation Failed ----");
		}
		
		// Step 9: Logout
				hp.logoutOfApp(driver);
				System.out.println("Sign out successful");

		//Step 11: close the browser
		driver.quit();
		
		
		
	}

}
