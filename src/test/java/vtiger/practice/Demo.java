package vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo 
{

	public static void main(String[] args) throws IOException {
		
		//Step 1: Read all the neccessary Data
		/*Read the data from property file*/
		FileInputStream fisp = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pObj = new Properties();
		pObj.load(fisp);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");
		
		Random r = new Random();
		int value = r.nextInt(1000);
		
		/*Read the data from excel sheet*/
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		String ORGNAME = wb.getSheet("Organization").getRow(1).getCell(2).getStringCellValue()+value;
		wb.close();
		
		WebDriver driver = null;
		
		//Step 1: Launch the Browser - RUNTIME POLYMORPHISM
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
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		
		//Step 2: Login to App
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3: Navigate to Organizations link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 4: Click on Create Organization Look up Image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//Step 5: Create organization with mandatory Fields
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
		
		//Step 6: Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 7: Validate for Organization
		String OrgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(OrgHeader.contains(ORGNAME))
		{
			System.out.println(OrgHeader+" ---- PASS ----");
		}
		else
		{
			System.out.println(" ---- Failed ----");
		}
		
		//Step 8: Logout of app
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("Sign out successful");
		
		driver.quit();
		
	}
	
}
