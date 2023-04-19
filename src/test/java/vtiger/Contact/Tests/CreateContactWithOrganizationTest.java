package vtiger.Contact.Tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.JavaUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;
import vtiger.ObjectRepository.ContactsInfoPage;
import vtiger.ObjectRepository.ContactsPage;
import vtiger.ObjectRepository.CreateNewContactPage;
import vtiger.ObjectRepository.CreateNewOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationPage;

public class CreateContactWithOrganizationTest {

	public static void main(String[] args) throws IOException {

		// Step 1: Create Object of Utilities
		ExcelFileUtility eUtil = new ExcelFileUtility();
		PropertyFileUtility pUtil = new PropertyFileUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverUtility wUtil = new WebDriverUtility();

		// Step 2: Read all the necessary Data
		String URL = pUtil.readDataFromPropertyFile("url");
		String BROWSER = pUtil.readDataFromPropertyFile("browser");
		String USERNAME = pUtil.readDataFromPropertyFile("username");
		String PASSWORD = pUtil.readDataFromPropertyFile("password");

		String LASTNAME = eUtil.readDataFromExcel("Contact", 4, 2);
		String ORGNAME = eUtil.readDataFromExcel("Contact", 4, 3)+jUtil.getRandomNumber();

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
		cnop.createNewOrganization(ORGNAME);

		// Step 8: Validate for Organization
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String OrgHeader = oip.getOrganizationHeader();
		if (OrgHeader.contains(ORGNAME)) {
			System.out.println(OrgHeader + " --- Organization Created ---");
		} else {
			System.out.println(" ----Organization creation Failed ----");
		}

		// Step 9: Navigate Contacts Link
		hp.clickOnContactsLink();

		// Step 10: Click on create Contact Look Up Image
		ContactsPage cp = new ContactsPage(driver);
		cp.clickOnCreateContactLookUpImg();

		// Step 11: Create contact
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.createNewContact(driver, LASTNAME, ORGNAME);
		wUtil.takeScreenshot(driver, "Screenshot1");

		// Step 12: Validate for Contacts
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String ContactHeader = cip.getContactHeader();
		if (ContactHeader.contains(LASTNAME)) {
			System.out.println(ContactHeader + " --- PASS ---");
		} else {
			System.out.println(" ---- Failed ----");
		}

		// Step 13: Logout
		hp.logoutOfApp(driver);
		System.out.println("Sign out successful");

		// Step 11: close the browser
		driver.quit();

	}

}
