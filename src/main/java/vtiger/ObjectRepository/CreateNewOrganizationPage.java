package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtilities.WebDriverUtility;

public class CreateNewOrganizationPage extends WebDriverUtility {
	
	//Declaration
	@FindBy(name = "accountname")
	private WebElement OrgNameEdt;
	
	@FindBy(name = "industry")
	private WebElement IndustryDropDwn;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	// Initialization
		public CreateNewOrganizationPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		//Utilization
		public WebElement getOrgNameEdt() {
			return OrgNameEdt;
		}
	
		public WebElement getIndustryDropDwn() {
			return IndustryDropDwn;
		}
		
		public WebElement getSaveBtn() {
			return SaveBtn;	
		}
		
		//Business Libraries
		/**
		 * This method will create Organization with mandatory information
		 * @param ORGNAME
		 */
		public void createNewOrganization(String ORGNAME)
		{
			OrgNameEdt.sendKeys(ORGNAME);
			SaveBtn.click();
		}
		
		/**
		 * This method will create Organization with Industry Drop down
		 * @param ORGNAME
		 */
		public void createNewOrganization(String ORGNAME, String INDUSTRY)
		{
			OrgNameEdt.sendKeys(ORGNAME);
			handleDropdown(IndustryDropDwn, INDUSTRY);
			SaveBtn.click();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}
