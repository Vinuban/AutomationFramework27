package vtiger.practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice {
	
	
	@Test(dataProvider = "getData")
	public void addToCart(String name,int price,int qty,String color, boolean isAvailable)
	{
		System.out.println("-----"+name+"-"+price+"-"+qty+"-"+color+"-"+isAvailable+"----");
	}
	@DataProvider
	public Object[][] getData()
	{			          //row   //cell
		Object[][] data = new Object[2][5]; //-- two data sets of 4 details inside.
		
		data[0][0] = "iphone";
		data[0][1] = 12000;
		data[0][2] = 11;
		data[0][3] = "Pink";
		data[0][4] = false;
		
		data[1][0] = "Samsung";
		data[1][1] = 1000;
		data[1][2] = 12;
		data[1][3] = "Black";
		data[1][4] = true;
		
		return data;
	}

}
