package com.clover.solution.ui.reader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.annotations.Test;

public class excelWriter {
	public static HSSFWorkbook workbook;
	public static HSSFSheet worksheet;
	public String ColName = "Result";
	public int col_num;

	public void WriteResult(String Ress, int DR) throws Exception {
		FileInputStream file_input_stream = new FileInputStream(
				"/home/users/shivani.kukreti/Documents/akeneo-product.xls");
		workbook = new HSSFWorkbook(file_input_stream);
		worksheet = workbook.getSheet("Result");
		HSSFRow Row = worksheet.getRow(0);

		int sheetIndex = workbook.getSheetIndex("Result");
		DataFormatter formatter = new DataFormatter();
		if (sheetIndex == -1) {
			System.out.println("No such sheet in file exists");
		} else {
			col_num = -1;
			for (int i = 0; i < Row.getLastCellNum(); i++) {
				HSSFCell cols = Row.getCell(i);
				String colsval = formatter.formatCellValue(cols);
				if (colsval.trim().equalsIgnoreCase(ColName.trim())) {
					col_num = i;
					break;
				}
			}
// 
			Row = worksheet.getRow(DR);
			try {
				// get my Row which is equal to Data Result and that colNum
				HSSFCell cell = worksheet.getRow(DR).getCell(col_num);
				// if no cell found then it create cell
				if (cell == null) {
					cell = Row.createCell(col_num);
				}
				// Set Result is pass in that cell number
				cell.setCellValue(Ress);

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		FileOutputStream file_output_stream = new FileOutputStream(
				"/home/users/shivani.kukreti/Documents/akeneo-product.xls");
		workbook.write(file_output_stream);
		file_output_stream.close();
		if (col_num == -1) {
			System.out.println("Column you are searching for does not exist");
		}
	}
}