package vtiger.practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Scenario5 {
public static void main(String[] args) {
		
		Random r = new Random();
		int value = r.nextInt(1000);
		
		//Step 1: Launch the Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888");
		
		//Step 2: Login to App
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3: Navigate to Contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 4: Click on Create Contacts Look up Image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Step 5: Create Contact with mandatory Fields
		driver.findElement(By.name("lastname")).sendKeys("DV"+value);
		
		//Step 6: Save
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 7: Validate for Contact
		 String ContactHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		 if(ContactHeader.contains("DV"))
		 {
			System.out.println(ContactHeader+" ---- PASS ----");
		 }
			else
			{
			System.out.println(" ---- Failed ----");
			}
		
		//Step 8: Navigate to Organizations link
			driver.findElement(By.linkText("Organizations")).click();
			
		//Step 9: Click on Create Organization Look up Image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
				
		//Step 10: Create organization with mandatory Fields
		driver.findElement(By.name("accountname")).sendKeys("TATA"+value);
		
		//Step 11: Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				
		//Step 12: Validate for Organization
			String OrgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(OrgHeader.contains("TATA"))
			{
			System.out.println(OrgHeader+" ---- PASS ----");
			}
			else
			{
			System.out.println(" ---- Failed ----");
			}
				
		//Step 13: Logout of app
			WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			Actions act = new Actions(driver);
			act.moveToElement(ele).perform();
			driver.findElement(By.linkText("Sign Out")).click();
			System.out.println("Sign out successful");
}
}
