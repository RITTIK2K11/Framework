package Selenium_testcases;

public class testin {

	public static void main(String[] args) {
		int abc = 0;
		//Switch case
		switch (abc)
		{
		case 0:
		System.out.println("case 0");
		break;
		case 100:
			System.out.println("case 100");
			break;
			default:
				System.out.println("default");
				break;
		}
		
		
		//For Loop
		for (int i=0;i<10;i++)
		{
			if(i==1)
			{
				continue;
				
			}
			System.out.println("Value of i = " + i);
			if(i==5)
				break;
		}	
		
		
		for(int i1=10;i1>0;i1--)
		{
			System.out.println(i1);
		}
		
		for(int i1=10;i1>0;i1=-2)
		{
			System.out.println(i1);
		}
		
		//while
		int j=0;
		while(j<10)
		{
			System.out.println("value of j="+j);
			j++;
		}
		//int j=0;
		while(true)
		{
			System.out.println("value of j="+j);
			if(j==20)
			{
				break;
			}
			j++;
			}
		}
	
	
	public void display()
	{
		System.out.println("In Display");
		
		
		
		
			



	}
public int sum(int a, int b)
{
	int summation=0;
	summation=a+b;
	return summation;
	//System.out.println("Sum of two numbers="+ summation);
}
}


