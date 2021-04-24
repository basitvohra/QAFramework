package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadDemo {
	XSSFWorkbook wb ;
	XSSFSheet sh ;
	
	public ExcelReadDemo() throws IOException
	{
		File f = new File("src\\TestData\\data.xlsx");
		FileInputStream fis = new FileInputStream(f);
		wb = new XSSFWorkbook(fis);
	}
	
	public String getExcelData(int shno , int row , int col)
	{
		sh = wb.getSheetAt(shno);
		String data = sh.getRow(row).getCell(col).getStringCellValue();
		return data;
	}
	
	public int getLastRowDetails(int shno)
	{
		sh = wb.getSheetAt(shno);
	  int row = sh.getLastRowNum();
	return row;
	}
	
	public int getLastColDetails(int shno)
	{
		sh = wb.getSheetAt(shno);
	  int col = sh.getRow(0).getLastCellNum();
	return col;
	}
}
