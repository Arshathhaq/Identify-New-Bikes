package utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	public static void writeExcel(String sheetName, int rownum, int colnum, String data)
			throws IOException, InvalidFormatException {

		// Opening the excel file
		File excelFile = new File(System.getProperty("user.dir") + "/TestData/ExcelFile.xlsx");
		
		FileOutputStream fo = new FileOutputStream(excelFile);

		XSSFWorkbook book = new XSSFWorkbook();

		// Creating the sheet if it does not exist
		if (book.getSheetIndex(sheetName) == -1) {
			book.createSheet(sheetName);
		}
		// fetching Sheet
		XSSFSheet sheet = book.getSheet(sheetName);

		
		// Creating Row
		XSSFRow row = sheet.createRow(rownum);

		// Creating cell
		XSSFCell cell = row.createCell(colnum);

		// Setting the data in the column
		cell.setCellValue(data);

		book.write(fo);

		book.close();

		fo.flush();

		fo.close();

	}

}
