package Selenium_testcases;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.poi.xssf.usermodel.XSSFWorkbookType;
public class testpage {
	static WebDriver driver;
	public static void main(String args[])
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\user\\Desktop\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.pampers.com/en-us/registration");
		runTest();
		driver.quit();
		System.out.println("done");
	}
	public static void runTest()
	{
		XSSFWorkbook workbook=null;
		FileInputStream file=null;
		HashMap<String,String> data = new HashMap<String, String>();
		HashMap<String,String> xpath = new HashMap<String, String>();
		HashMap<String,String> objectType = new HashMap<String, String>();
		HashMap<Integer,String> header = new HashMap<Integer, String>();
		try{
			//can read from a notepad file to parameterize
			workbook = new XSSFWorkbook("C:\\Users\\user\\Desktop\\Book1.xlsx");
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		XSSFSheet worksheet = workbook.getSheet("Main");
		int sheetcount = workbook.getNumberOfSheets();
		//find number of rows in execute column where yes is the value
		ArrayList<Row> executeRows = searchSheet("Yes","Execute",worksheet);
		//For each row where execute is yes
		for(Row eachExecuteRow:executeRows)
		{
			//For each sheet in the given workbook
			for(int intSheet=0;intSheet<=sheetcount - 1;intSheet++)
			{
				//Get current sheet
				XSSFSheet currentSheet=workbook.getSheetAt(intSheet);
				//If current sheet name is main then we want to skip that sheet
				if(currentSheet.getSheetName().equals("Main"))
				{
					continue;
				}
				
				//Get position of iteration column
				int iterationposition = getColIndex("Iterations", worksheet);
				//Get current iteration number
				String currentiteration=getCellValue(eachExecuteRow.getCell(iterationposition),workbook);
				//Find the number of rows for current iteration in the given sheet
				ArrayList<Row> iterationRows=searchSheet(currentiteration,"Iterations",currentSheet);
				//Find the number of rows for current iteration in the given sheet
				ArrayList<Row> xpathRows=searchSheet("xpaths","Iterations",currentSheet);
				//Find the number of rows for current iteration in the given sheet
				ArrayList<Row> objectTypeRows=searchSheet("object type","Iterations",currentSheet);
				
				//Creating header row hashmap
				Row hdrRow=getHeaderRow(currentSheet);
				for(int i=0;i<hdrRow.getLastCellNum() - 1;i++)
				{
					header.put(i, getCellValue(hdrRow.getCell(i),workbook));
				}
				for(Row eachxpathRow:xpathRows)
				{
					int itr =0;
					//Each column in the sheet
					for (Cell cell:eachxpathRow)
					{
						if(eachxpathRow!=hdrRow)
						{
							String cellvalue=getCellValue(cell,workbook);
							xpath.put(header.get(itr),cellvalue);
							
							itr=itr+1;
							
						}
					}
				}				
				for(Row eachObjecttypeRow:objectTypeRows)
				{
					int itr =0;
					//Each column in the sheet
					for (Cell cell:eachObjecttypeRow)
					{
						if(eachObjecttypeRow!=hdrRow)
						{
							String cellvalue=getCellValue(cell,workbook);
							objectType.put(header.get(itr),cellvalue);
							itr=itr+1;
							
						}
					}
				}		
	
			//looping through each row
				for(Row eachIterationRow:iterationRows)
				{
					int itr =0;
					//Each column in the sheet
					for (Cell cell:eachIterationRow)
					{
						if(eachIterationRow!=hdrRow)
						{
							String cellvalue=getCellValue(cell,workbook);
							data.put(header.get(itr),cellvalue);
							dataEntry(data.get(header.get(itr)),xpath.get(header.get(itr)),objectType.get(header.get(itr)));
							itr=itr+1;
							
						}
					}
				}
						}
		}
	}
						
	
	public testpage(WebDriver driver) throws IOException
	{
		Date d = new Date();
		SimpleDateFormat d1 = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");
		File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File("C:\\ScreenCapture\\a" + d1.format(d)+".jpg"));
	}
	
	public testpage(WebDriver driver,String fileName) throws IOException
	{
		Date d = new Date();
		SimpleDateFormat d1 = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");
		File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File("C:\\ScreenCapture\\" + fileName + d1.format(d)+".jpg"));
	}
	
				
	private static String getCellValue(Cell cell,XSSFWorkbook wb)
	{
		//cell.setCellType(Cell.CELL_TYPE_STRING):
		XSSFFormulaEvaluator evaluator=new XSSFFormulaEvaluator(wb);
		String cellVal="";
		final int CELL_TYPE_STRING=1;
		final int CELL_TYPE_NUMERIC=0;
		final int CELL_TYPE_FORMULA=2;
		final int CELL_TYPE_BLANK=3;
		final int CELL_TYPE_BOOLEAN=4;
		
		if(cell!=null)
		{
			switch(cell.getCellType())
			{
			case(CELL_TYPE_STRING):
				cellVal=cell.getStringCellValue();
			
			break;
			case(CELL_TYPE_NUMERIC):
				cellVal=Double.toString(cell.getNumericCellValue());
			if(cellVal.endsWith(".0"))
					{
				cellVal=cellVal.replace(".0","");
				
					}
			break;
			case (CELL_TYPE_FORMULA):
			//cellVal=evaluator.evaluateInCell(cell).toString();
			cellVal = getCellValue(evaluator.evaluateInCell(cell),wb);
			break;
			
			case(CELL_TYPE_BLANK):
				cellVal="";
			break;
			
			case(CELL_TYPE_BOOLEAN):
				cellVal=Boolean.toString(cell.getBooleanCellValue());
			break;
			}
		}
		return cellVal;
	}
				//Find the position of a particular column in the given sheet
			public static int getColIndex(String colName,XSSFSheet worksheet){
				int colIndex=-1;
				//Find Header row position
				Row hdrRow=getHeaderRow(worksheet);
				if(hdrRow!=null)
				{
					//Iterating through each column and checking if value is matching or not
					for(int i=0;i<=hdrRow.getLastCellNum();i++)
					{
						if(getCellValue(hdrRow.getCell(i),(XSSFWorkbook) worksheet.getParent()).equals(colName))
						{
							colIndex=i;
						break;					}
				}
			}
				return colIndex;	
			}			
			
			
			//}
			private static Row getHeaderRow(XSSFSheet worksheet){
				Row row = null;
				System.out.println("SheetName - " + worksheet.getSheetName());
				for(Row rowLoop:worksheet)
				{
					try
					{
						String strCellVal=getCellValue(rowLoop.getCell(0),(XSSFWorkbook) worksheet.getParent());
								if(strCellVal.equals("Iterations") || strCellVal.equals("Sr. No."))
								{
									row=rowLoop;
									break;
									
								}
					}
					catch(Exception ex){}
				}
				return row;
				}
			
		//Get row listfor the given value in the given column for the given sheet
			public static ArrayList<Row> searchSheet(String filterVal,String colName,XSSFSheet worksheet)
			{
				ArrayList<Row> rows=new ArrayList<Row>();
				int colIndex=-1;
				//Finding column position
				colIndex=getColIndex(colName,worksheet);
				if(colIndex>=0)
				{
					//Iterating through each row in the given sheet and matching the filter val in the given
				for(Row row:worksheet)
				{
					//try{
					if(getCellValue(row.getCell(colIndex),(XSSFWorkbook) worksheet.getParent()).equals(filterVal))
					{
						rows.add(row);
						
					}
				//}
				//catch(Exception ex){}
				}
				}
				return rows;
				
				
				}
			public static void dataEntry(String value,String xpath,String objectType){
				if(!value.equals("")&& !value.equals("null") && xpath!=null && !xpath.equals("") && objectType!=null && !objectType.equals(""))
				switch(objectType.toLowerCase())
				{
				case "textbox": case "dropdown":
					driver.findElement(By.xpath(xpath)).clear();
					driver.findElement(By.xpath(xpath)).sendKeys(value);
					break;
					
				case "image" : case "button":
					driver.findElement(By.xpath(xpath)).click();
					break;
					
				case "wait":
				case "objectpresence":
				case "textcheck":
				case "datepick":
				case "makemytripdatepick":
				}
			
			}
		
		

//	private static String get(int itr) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	private static Row getHeaderRow(XSSFSheet currentSheet) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	private static String getCellValue(Cell cell, XSSFWorkbook workbook) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	private static int getColIndex(String string, XSSFSheet currentSheet) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	private static ArrayList<Row> searchSheet(String string, String string2, XSSFSheet worksheet) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//

	}

