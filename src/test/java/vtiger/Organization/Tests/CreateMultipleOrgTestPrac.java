package vtiger.Organization.Tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
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

public class CreateMultipleOrgTestPrac {

	ExcelFileUtility eUtil = new ExcelFileUtility();
	JavaUtility jUtil = new JavaUtility();

	@Test(dataProvider = "OrgWithIndustry")
	public void createOrgTest(String ORG, String INDUSTRY) throws IOException {
		
		// Step 1: Create Object of Generic Utilities
		PropertyFileUtility pUtil = new PropertyFileUtility();
		WebDriverUtility wUtil = new WebDriverUtility();

		// Step 2: Read all the required Data
		String URL = pUtil.readDataFromPropertyFile("url");
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");
		
		String ORGNAME = ORG+jUtil.getRandomNumber();

		WebDriver driver = null;

		// Step 3: Launch the Browser - RUNTIME POLYMORPHISM
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("invalid browser name");
		}

		wUtil.maximizeWindow(driver);
		wUtil.waitForPageLoad(driver);
		driver.get(URL);

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
		cnop.createNewOrganization(ORGNAME, INDUSTRY);

		// Step 8: Validate for Organization
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String OrgHeader = oip.getOrganizationHeader();
		if (OrgHeader.contains(ORGNAME)) {
			System.out.println(OrgHeader + " --- Organization Created ---");
		} else {
			System.out.println(" ----Organization creation Failed ----");
		}

		// Step 10: Logout of app
		hp.logoutOfApp(driver);
		System.out.println("signout successful");
	}

	@DataProvider(name = "OrgWithIndustry")
	public Object[][] getData() throws EncryptedDocumentException, IOException {
		Object[][] data = eUtil.readDatafromExcelToDataProvider("dataProviderOrg");
		return data;
	}

}

