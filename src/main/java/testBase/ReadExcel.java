package testBase;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public String getDataFromCell(String xlFilePath,String sheetName,int row,int coloumn)
	{
		String a = null;
		try {
		FileInputStream fis = new FileInputStream(xlFilePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		Row row1 = sheet.getRow(row);
		Cell cell = row1.getCell(coloumn);
		XSSFCell cellval = sheet.getRow(row).getCell(coloumn);
		a =  cellval.toString();
		}catch (Exception e) {
			// TODO: handle exception
		}
		return a;
	}
}
