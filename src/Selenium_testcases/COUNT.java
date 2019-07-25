package Selenium_testcases;

public class COUNT {
//COUNT THE NO OF 'C' PRESENT IN THE STRING
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//count the no of string
		String s = "ABCABCABC";
		 int count = 0;

		for (int i = 0; i < s.length(); i++)
		{
			if(s.charAt(i)=='C')
				
			count++;

			}
		
			System.out.println("Number of A in a string = " + count);
	 
	
	}
	
	
	}



