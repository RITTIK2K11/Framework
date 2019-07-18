package Selenium_testcases;

public class string {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a2 = "A;B;C;D";
		String arrA2[]= a2.split(";");
		String output="";
		for(int k=0;k<arrA2.length;k++){
		output=output+","+arrA2[k];
		
		}
		output=output.substring(1,output.length());
		System.out.println("Join String:"+output);
}
}

