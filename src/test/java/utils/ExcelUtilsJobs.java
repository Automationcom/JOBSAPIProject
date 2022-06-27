package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilsJobs {
	public FileInputStream finst;
	public FileOutputStream fotst;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String path = null;
	
	public ExcelUtilsJobs(String path)
	{
		this.path=path;
	}
	
	public int getRowCOunt(String sheetName) throws IOException {
		finst=new FileInputStream(path);
		workbook=new  XSSFWorkbook(finst);
		sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		finst.close();
		return rowcount ;
		
	}
	
	public int getCellCOunt(String sheetName, int rownum) throws IOException {
		finst=new FileInputStream(path);
		workbook=new  XSSFWorkbook(finst);
		sheet = workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount= row.getLastCellNum();
		workbook.close();
		finst.close();
		return cellcount;
	}
	//get the row value. read the row 
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		finst=new FileInputStream(path);
		workbook=new  XSSFWorkbook(finst);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		DataFormatter formatter = new DataFormatter();
		String data;
		try { // it gives the value with out considaring the datatype
			data = formatter.formatCellValue(cell);
		}
		catch(Exception e)
		{
			data = "";
		}
		workbook.close();
		finst.close();
		return data;
	}
	//Write the data in Excel
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
		
		finst=new FileInputStream(path);
		workbook=new  XSSFWorkbook(finst);
		sheet = workbook.getSheet(sheetName);
		
		row=sheet.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		
		fotst= new FileOutputStream(path);
		workbook.write(fotst);
		workbook.close();
		finst.close();
		fotst.close();
		
		
	}
	
	

}
