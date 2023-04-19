package vtiger.practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Scenario3 {
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
		
		//Step 3: Navigate to Organizations link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 4: Click on Create Organization Look up Image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//Step 5: Create organization with mandatory Fields
		driver.findElement(By.name("accountname")).sendKeys("LINK CHEMICALS"+value);
		
		
		//Step 6: Select Chemicals in the industry dropdown
			Select dropindustry = new Select (driver.findElement(By.name("industry")));
		    dropindustry.selectByVisibleText("Chemicals");
		
		//Step 7: Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 7: Validate for Organization
		String OrgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(OrgHeader.contains("LINK CHEMICALS"))
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
		
}
}
