package ibm.restassured.assignment;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AssignmentData {
	public static Object[][] testdata() throws IOException
	{
		FileInputStream fis = new FileInputStream(".\\AssignmentTestData.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		XSSFSheet sheet = workbook.getSheetAt(0);

		int totalrows = sheet.getLastRowNum();  // to check how much data is available in the excel sheet so that we can iterate it one by one
		System.out.println("Total rows in sheet "+totalrows);
		
		Row row = sheet.getRow(1);
		int totalcolumn = row.getLastCellNum();  //total avaialable columns in my sheet
		System.out.println("Total columns in sheet "+totalcolumn);
		
		//String value = sheet.getRow(1).getCell(0).getStringCellValue();
		//System.out.println(value);
//		System.out.println(value);
		
		Object[][] data = new Object[totalrows+1][totalcolumn];
		
		for(int i=1;i<=totalrows;i++)
		{
			for (int j=0;j<totalcolumn;j++)
			{
				data[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
				//System.out.println(value);
			}
		}
		return data;

		
	}
}
