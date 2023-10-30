package com.clover.solution.ui.reader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException; 
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.clover.solution.ui.util.logs.Log; 

public class excelReader {
		
	private String testcaseName;
	private FileInputStream file;
	LinkedList<LinkedHashMap<String,String>> dataMap;
	LinkedHashMap<Integer,String> header;
	
	//For Dataprovider
	private static HSSFSheet ExcelWSheet;
	private static HSSFWorkbook ExcelWBook;
	private static Cell Cell;
	private static HSSFRow Row;
	static String[][] tabArray = null;
	
		public excelReader(String testcaseName)
		{
			this.testcaseName=testcaseName;
		}
		
		public LinkedList<LinkedHashMap<String,String>> getExcelData()
		{
			return this.ExcelDataReader(testcaseName);
		}
		
		private LinkedList<LinkedHashMap<String, String>> ExcelDataReader(String testcaseName)
	    {
			
		String fileName = null;
	    String currentDirectory=System.getProperty("user.dir");
	    HSSFWorkbook workbook=null;
	    HSSFSheet sheet=null;
	    dataMap=new LinkedList<LinkedHashMap<String,String>>();
	    header=new LinkedHashMap<Integer,String>();
	    try {     
	    	fileName=currentDirectory + "/src//main//java//com//clover//solution//ui//data//" + testcaseName + ".xls";
	        file = new FileInputStream(fileName); 
	        workbook=new HSSFWorkbook(file); 
	        sheet=workbook.getSheet("Data"); 
		    } catch (FileNotFoundException e) {
	        	Log.error("ExcelDataReader-" + e.getStackTrace());
	            e.printStackTrace();
	        }catch (IOException e) {
	        	Log.error("ExcelDataReader-" + e.getStackTrace());
	            e.printStackTrace();
	        }
	    
	    try {     
	    	
	    	//HeaderManagement
	    	int headerrow=0;
	    	int columnCount=0;
	    	LinkedHashMap<String,String> internalData=null;
	    	//Iterate each row one by one 
	        Iterator<Row> rowIterator = sheet.iterator(); 
	           while(rowIterator.hasNext())  
	           { 
	        	   Row row = rowIterator.next(); 
	        	   if(headerrow == 0) {
	        		   Iterator<Cell> Cell = row.cellIterator(); 
		                  
		               while (Cell.hasNext())  
		               { 
		                   Cell cell = Cell.next();       
		                     // Check the cell type 
		                   columnCount=columnCount;
		                   String headerValue="";
		                   switch(cell.getCellType()) 
		                   { 
		                   case STRING: 
		                	   headerValue=cell.getStringCellValue().trim(); 
		                       break;
		                   case NUMERIC: 
		                	   headerValue=String.valueOf(cell.getNumericCellValue()).trim();  
		                       break;          
		                   case FORMULA: 
		                	   headerValue=String.valueOf(cell.getNumericCellValue()).trim(); 
		                       break;
		                   }
		                   header.put(columnCount, headerValue);
		                   columnCount++;
		               }
		               headerrow++;
	        	   }else {
	                 // For each row, iterate through all the columns 
	               Iterator<Cell> Cell = row.cellIterator(); 
	               String columnvalue="";
                   String dataValue="";
                   columnCount=0;
                   internalData=new LinkedHashMap<String,String>();
                   
	               while (Cell.hasNext())  
	               { 
	                   Cell cell = Cell.next();       
	                     // Check the cell type
	                   columnvalue=header.get(columnCount);
	                   dataValue="";
	                   switch(cell.getCellType()) 
	                   { 
	                   case STRING: 
	                	   dataValue=cell.getStringCellValue().trim();
	                       break;     
	                   case NUMERIC: 
	                	   dataValue=String.valueOf(cell.getNumericCellValue()).trim();  
	                       break;      
	                   case FORMULA: 
	                	   dataValue=String.valueOf(cell.getNumericCellValue()).trim(); 
	                       break; 
	                   } 
	                   columnCount++;
	                   internalData.put(columnvalue, dataValue);
	                   Log.info("Reading from Excel"+ columnvalue + "=" + dataValue);
		               }
	               	headerrow++;
			        }
	        	   if(headerrow > 1) {
	        	   dataMap.add(internalData);
	        	   Log.info("Adding to Excel LinkedHashMap"+internalData);
	        	   }          
            } 
	           workbook.close(); 
	           file.close(); 
	    }catch(Exception e){ 
	    	 Log.info("Exception in Excel Reading - "+e.toString());
	    }
		return dataMap;
}
		
	
			public static Object[][] ExcelReaderDataProvider(String testcaseName) throws Exception {   
				
				String fileName = null;
			    String currentDirectory=System.getProperty("user.dir");
			    String SheetName="Data";
			    
			    fileName=currentDirectory + "/src//main//java//com//clover//solution//ui//data//" + testcaseName + ".xls";
			   try {
				   FileInputStream ExcelFile = new FileInputStream(fileName);
				   ExcelWBook = new HSSFWorkbook(ExcelFile);
				   ExcelWSheet = ExcelWBook.getSheet(SheetName);

				   int startRow = 1;
				   int startCol = 1;
				   int ci,cj;
				   int totalRows = ExcelWSheet.getLastRowNum();
				   // you can write a function as well to get Column count
				   int totalCols = 2;
				   try {
					tabArray=new String[totalRows][totalCols];
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   ci=0;
				   for (int i=startRow;i<=totalRows;i++, ci++) {           	   
					  cj=0;
					   for (int j=startCol;j<=totalCols;j++, cj++){
						   tabArray[ci][cj]=getCellData(i,j);
						   System.out.println(tabArray[ci][cj]);  
							}
						}
					}
				catch (FileNotFoundException e){
					System.out.println("Could not read the Excel sheet");
					e.printStackTrace();
					}
				catch (IOException e){
					System.out.println("Could not read the Excel sheet");
					e.printStackTrace();
					}
				return(tabArray);
				}
			
			public static String getCellData(int RowNum, int ColNum) throws Exception {
				try{
					Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
					//int dataType = Cell.getCellType();
						if  (!Cell.getCellType().equals("STRING")) {
							return "";
						}else{
							String CellData = null;
							try {
								CellData = Cell.getStringCellValue();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return CellData;
						}
					}catch (Exception e){
					System.out.println(e.getMessage());
					throw (e);
					}
				}
		
}
