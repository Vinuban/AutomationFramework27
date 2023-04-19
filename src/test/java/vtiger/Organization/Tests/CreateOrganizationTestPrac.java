package vtiger.Organization.Tests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.CreateNewOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationPage;

public class CreateOrganizationTestPrac extends BaseClass {
	
	@Test( groups = "SmokeSuite")
	public void createOrgTest() throws EncryptedDocumentException, IOException
	{
		String ORGNAME = eUtil.readDataFromExcel("Organization", 1, 2)+jUtil.getRandomNumber();

		// Step 5: Navigate to Organizations link
		HomePage hp = new HomePage(driver);
		hp.clickOnOrganizationLink();
		Reporter.log("navigated to organizations link",true);//printed in report

		// Step 6: Click on Create Organization Look up Image
		OrganizationPage op = new OrganizationPage(driver);
		op.clickOnCreateOrganizationLookUpImg();
		Reporter.log("Clicked on create Organization look up image");

		// Step 7: Create organization with mandatory Fields
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createNewOrganization(ORGNAME);
		Reporter.log("New Organization Created");

		// Step 8: Validate for Organization
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String OrgHeader = oip.getOrganizationHeader();
		Assert.assertTrue(OrgHeader.contains(ORGNAME));
	
}

	@Test
	public void demo()
	{
		System.out.println("This is demo");
	}

}
