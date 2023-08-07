package TutorialsNinja.QAFox.Utils;

import java.util.Date;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	public static final int IMPLICIT_WAIT_TIME=10;

	public static String generateEmailWithTimeStamp()
	{
		Date date=new Date();
		String timeStamp=date.toString().replace(" ", "_").replace(":", "_");
		return "dhanasatish"+timeStamp+"@gmail.com";
	}
	
	//write data in excel and save it in "/QAFox/src/main/java/TutorialsNinja/QAFox/testdata" package
	// add poi-ooxml , poi ,poi-ooxml scheemas dependencies
	
	public static Object[][] getTestDataFromExcel(String sheetName) {
		
		File fileExcel=new File(System.getProperty("user.dir")+"\\src\\main\\java\\TutorialsNinja\\QAFox\\config\\testdata.xlsx");
		XSSFWorkbook workbook = null;
		
		try{
		FileInputStream fisExcel=new FileInputStream(fileExcel);
		workbook=new XSSFWorkbook();
		}catch(Throwable e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		//get the number of rows in the sheet
		int rows=sheet.getLastRowNum();
		
		//get the number of columns
		int cols=sheet.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[rows][cols];
		
		for(int i=0;i<rows;i++) {
			
			XSSFRow row = sheet.getRow(i+1);
			for(int j=0;j<cols;j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;
									
				}
			}
			
		}
		return data;
		
	}
	
	public static String captureScreenshot(WebDriver driver,String testName) {
		
		File srcScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}
	
	

}
