package vtiger.practice;

public class GenericMethodPackage {

	public static void main(String[] args) {
	      
		int c =add(10000,20);
		System.out.println(c);
		
	}
	
	public static int add(int a,int b) // generic
	{
		int sum = a+b; // action
		return sum;
	}

}
