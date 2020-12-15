package reusableComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	private Workbook wb;
	private Sheet sheet;
	private HSSFRow rowHssf;
	private XSSFRow rowXssf;
	public String path;
	private Cell cell;
	FileOutputStream fileOut;
	Map<String, String> dataMap;

	public ExcelUtility(String excelFilePath) {
		String fileExtn = excelFilePath.substring(excelFilePath.indexOf("."));
		System.out.println(fileExtn);
		this.path = excelFilePath;
		if (fileExtn.equals(".xlsx")) {
			try {
				File src = new File(excelFilePath);
				FileInputStream fis = new FileInputStream(src);
				wb = new XSSFWorkbook(fis);
			} catch (Exception e) {
				System.out.println("File not found " + e.getMessage());
			}
		} else if (fileExtn.equals(".xls")) {
			try {
				FileInputStream fis = new FileInputStream(excelFilePath);
				wb = new HSSFWorkbook(fis);
			} catch (Exception e) {
				System.out.println("File not found " + e.getMessage());
			}
		}

	}

	@SuppressWarnings("deprecation")
	public String getCellData(int sheetNumber, int rowNum, int colNum) {
		if (wb instanceof HSSFWorkbook) {
			sheet = wb.getSheetAt(sheetNumber);
			try {
				Cell cell = sheet.getRow(rowNum).getCell(colNum);
				if (cell.getCellType().name().equals("NUMERIC") || cell.getCellType().name().equals("FORMULA")) {
					// sheet.getRow(row).getCell(column).setCellType(arg0);
					cell.setCellType(CellType.STRING);
					String cellText = cell.getStringCellValue();
					// String cellText =
					// Double.toString(cell.getNumericCellValue());
					// String cellText =
					// String.valueOf(cell.getNumericCellValue());
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						// format in form of M/D/YY
						double d = cell.getNumericCellValue();
						Calendar cal = Calendar.getInstance();
						cal.setTime(HSSFDateUtil.getJavaDate(d));
						cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
						cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;

					}
					return cellText;
				} else if (cell.getCellType().name().equals("STRING")) {
					return cell.getStringCellValue();
				} else if (cell.getCellType().BLANK != null) {
					return "";
				}
			} catch (Exception e) {
				System.out.println("Unable to get data from file" + e.getMessage());
			}

		} else if (wb instanceof XSSFWorkbook) {
			sheet = wb.getSheetAt(sheetNumber);
			try {
				Cell cell = sheet.getRow(rowNum).getCell(colNum);
				if (cell.getCellType().name().equals("NUMERIC") || cell.getCellType().name().equals("FORMULA")) {
					cell.setCellType(CellType.STRING);
					String cellText = cell.getStringCellValue();
					// sheet.getRow(row).getCell(column).setCellType(arg0);
					// String cellText =
					// Double.toString(cell.getNumericCellValue());
					return cellText;
				} else if (cell.getCellType().name().equals("STRING")) {
					return cell.getStringCellValue();
				} else if (cell.getCellType().BLANK != null) {
					return "";
				}
			} catch (Exception e) {
				System.out.println("Unable to get data from file" + e.getMessage());
			}
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public String getCellData(String sheetName, int rowNum, int colNum) {
		if (wb instanceof HSSFWorkbook) {
			sheet = wb.getSheet(sheetName);
			try {
				Cell cell = sheet.getRow(rowNum).getCell(colNum);
				if (cell.getCellType().name().equals("NUMERIC") || cell.getCellType().name().equals("FORMULA")) {
					// sheet.getRow(row).getCell(column).setCellType(arg0);
					cell.setCellType(CellType.STRING);
					String cellText = cell.getStringCellValue();
					// String cellText =
					// Double.toString(cell.getNumericCellValue());
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						// format in form of M/D/YY
						double d = cell.getNumericCellValue();
						Calendar cal = Calendar.getInstance();
						cal.setTime(HSSFDateUtil.getJavaDate(d));
						cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
						cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;

					}
					return cellText;
				} else if (cell.getCellType().name().equals("STRING")) {
					return cell.getStringCellValue();
				} else if (cell.getCellType().BLANK != null) {
					return "";
				}
			} catch (Exception e) {
				System.out.println("Unable to get data from file" + e.getMessage());
			}

		} else if (wb instanceof XSSFWorkbook) {
			sheet = wb.getSheet(sheetName);
			try {
				Cell cell = sheet.getRow(rowNum).getCell(colNum);
				if (cell.getCellType().name().equals("NUMERIC") || cell.getCellType().name().equals("FORMULA")) {
					cell.setCellType(CellType.STRING);
					String cellText = cell.getStringCellValue();
					// sheet.getRow(row).getCell(column).setCellType(arg0);
					// String cellText =
					// Double.toString(cell.getNumericCellValue());
					return cellText;
				} else if (cell.getCellType().name().equals("STRING")) {
					return cell.getStringCellValue();
				} else if (cell.getCellType().BLANK != null) {
					return "";
				}
			} catch (Exception e) {
				System.out.println("Unable to get data from file" + e.getMessage());
			}
		}
		return null;
	}
	
	public Map<String, String> getDataAsMap(String sheetName){
		dataMap = new HashMap<>();
		for(int row=0; row<getRowCount(sheetName); row++){
			String key=getCellData(sheetName, row, 0);
			String value= getCellData(sheetName, row, 1);
			dataMap.put(key, value);
		}
		return dataMap;
		
	}

	@SuppressWarnings("deprecation")
	public String getCellData(String sheetName, String colName, int rowNum) {
		int colNum = -1;
		if (wb instanceof HSSFWorkbook) {
			try {
				sheet = wb.getSheet(sheetName);
				rowHssf = (HSSFRow) sheet.getRow(0);
				for (int i = 0; i < rowHssf.getLastCellNum(); i++) {
					// System.out.println(row.getCell(i).getStringCellValue().trim());
					if (rowHssf.getCell(i).getStringCellValue().trim().equals(colName.trim()))
						colNum = i;
				}
				if (colNum == -1)
					return "";

				Cell cell = sheet.getRow(rowNum).getCell(colNum);
				if (cell.getCellType().name().equals("NUMERIC") || cell.getCellType().name().equals("FORMULA")) {
					cell.setCellType(CellType.STRING);
					String cellText = cell.getStringCellValue();
					// sheet.getRow(row).getCell(column).setCellType(arg0);
					// String cellText =
					// Double.toString(cell.getNumericCellValue());
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						// format in form of M/D/YY
						double d = cell.getNumericCellValue();
						Calendar cal = Calendar.getInstance();
						cal.setTime(HSSFDateUtil.getJavaDate(d));
						cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
						cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;

					}
					return cellText;
				} else if (cell.getCellType().name().equals("STRING")) {
					return cell.getStringCellValue();
				} else if (cell.getCellType().BLANK != null) {
					return "";
				}
			} catch (Exception e) {
				System.out.println("Unable to get data from file " + e.getMessage());
			}

		} else if (wb instanceof XSSFWorkbook) {
			try {
				sheet = wb.getSheet(sheetName);
				sheet = wb.getSheet(sheetName);
				rowXssf = (XSSFRow) sheet.getRow(0);
				for (int i = 0; i < rowXssf.getLastCellNum(); i++) {
					// System.out.println(row.getCell(i).getStringCellValue().trim());
					if (rowXssf.getCell(i).getStringCellValue().trim().equals(colName.trim()))
						colNum = i;
				}
				if (colNum == -1)
					return "";
				Cell cell = sheet.getRow(rowNum).getCell(colNum);
				if (cell.getCellType().name().equals("NUMERIC") || cell.getCellType().name().equals("FORMULA")) {
					cell.setCellType(CellType.STRING);
					String cellText = cell.getStringCellValue();
					// sheet.getRow(row).getCell(column).setCellType(arg0);
					// String cellText =
					// Double.toString(cell.getNumericCellValue());
					return cellText;
				} else if (cell.getCellType().name().equals("STRING")) {
					return cell.getStringCellValue();
				} else if (cell.getCellType().BLANK != null) {
					return "";
				}
			} catch (Exception e) {
				System.out.println("Unable to get data from file" + e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}

	public int getRowCount(int sheetNumber) {
		int rowCount = wb.getSheetAt(sheetNumber).getLastRowNum();
		return rowCount + 1;
	}

	public int getRowCount(String sheetName) {
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		return rowCount + 1;
	}

	// find whether sheets exists
	public boolean isSheetExist(String sheetName) {
		int index = wb.getSheetIndex(sheetName);
		if (index == -1) {
			index = wb.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	// returns number of columns in a sheet
	public int getColumnCount(String sheetName) {
		// check if sheet exists
		if (!isSheetExist(sheetName))
			return -1;
		if (wb instanceof XSSFWorkbook) {
			sheet = wb.getSheet(sheetName);
			rowXssf = (XSSFRow) sheet.getRow(0);
			if (rowXssf == null)
				return -1;
			return rowXssf.getLastCellNum();
		} else if (wb instanceof HSSFWorkbook) {
			sheet = wb.getSheet(sheetName);
			rowHssf = (HSSFRow) sheet.getRow(0);
			if (rowHssf == null)
				return -1;
			return rowHssf.getLastCellNum();
		}
		return -1;
	}

	public int getCellRowNum(String sheetName, String colName, String cellValue) {

		for (int i = 2; i <= getRowCount(sheetName); i++) {
			if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
				return i;
			}
		}
		return -1;

	}

	public boolean addSheet(String sheetname) {
		
		if(!isSheetExist(sheetname)){
		FileOutputStream fileOut;
		try {
			wb.createSheet(sheetname);
			fileOut = new FileOutputStream(path);
			wb.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		}
		else
		{
			System.out.println("Sheet already present");
			return false;
		}
	}

	public boolean removeSheet(String sheetName) {
		int index = wb.getSheetIndex(sheetName);
		if (index == -1)
			return false;

		FileOutputStream fileOut;
		try {
			wb.removeSheetAt(index);
			fileOut = new FileOutputStream(path);
			wb.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean addColumn(String sheetName, String colName) {
		// System.out.println("**************addColumn*********************");
		if (wb instanceof XSSFWorkbook) {
			try {
				int index = wb.getSheetIndex(sheetName);
				if (index == -1)
					return false;

				XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
				sheet = wb.getSheetAt(index);

				rowXssf = (XSSFRow) sheet.getRow(0);
				if (rowXssf == null)
					rowXssf = (XSSFRow) sheet.createRow(0);

				if (rowXssf.getLastCellNum() == -1)
					cell = rowXssf.createCell(0);
				else
					cell = rowXssf.createCell(rowXssf.getLastCellNum());

				cell.setCellValue(colName);
				cell.setCellStyle(style);
				FileOutputStream fileOut;
				fileOut = new FileOutputStream(path);
				wb.write(fileOut);
				fileOut.close();

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		} else {
			try {
				int index = wb.getSheetIndex(sheetName);
				if (index == -1)
					return false;

				HSSFCellStyle style = (HSSFCellStyle) wb.createCellStyle();
				sheet = wb.getSheetAt(index);

				rowHssf = (HSSFRow) sheet.getRow(0);
				if (rowHssf == null)
					rowHssf = (HSSFRow) sheet.createRow(0);

				if (rowHssf.getLastCellNum() == -1)
					cell = rowHssf.createCell(0);
				else
					cell = rowHssf.createCell(rowHssf.getLastCellNum());

				cell.setCellValue(colName);
				cell.setCellStyle(style);
				FileOutputStream fileOut;
				fileOut = new FileOutputStream(path);
				wb.write(fileOut);
				fileOut.close();

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;

		}

	}
	
	// returns true if data is set successfully else false
		public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
			if (wb instanceof XSSFWorkbook) {
			try {
				if (rowNum <= 0)
					return false;

				int index = wb.getSheetIndex(sheetName);
				int colNum = -1;
				if (index == -1)
					return false;

				sheet = wb.getSheetAt(index);

				rowXssf = (XSSFRow) sheet.getRow(0);
				for (int i = 0; i < rowXssf.getLastCellNum(); i++) {
					// System.out.println(row.getCell(i).getStringCellValue().trim());
					if (rowXssf.getCell(i).getStringCellValue().trim().equals(colName))
						colNum = i;
				}
				if (colNum == -1)
					return false;

				sheet.autoSizeColumn(colNum);
				rowXssf = (XSSFRow) sheet.getRow(rowNum);
				if (rowXssf == null)
					rowXssf = (XSSFRow) sheet.createRow(rowNum);

				cell = rowXssf.getCell(colNum);
				if (cell == null)
					cell = rowXssf.createCell(colNum);

				cell.setCellValue(data);

				fileOut = new FileOutputStream(path);

				wb.write(fileOut);

				fileOut.close();

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
			}
			else{
				try {
					if (rowNum <= 0)
						return false;

					int index = wb.getSheetIndex(sheetName);
					int colNum = -1;
					if (index == -1)
						return false;

					sheet = wb.getSheetAt(index);

					rowHssf = (HSSFRow) sheet.getRow(0);
					for (int i = 0; i < rowHssf.getLastCellNum(); i++) {
						// System.out.println(row.getCell(i).getStringCellValue().trim());
						if (rowHssf.getCell(i).getStringCellValue().trim().equals(colName))
							colNum = i;
					}
					if (colNum == -1)
						return false;

					sheet.autoSizeColumn(colNum);
					rowHssf = (HSSFRow) sheet.getRow(rowNum);
					if (rowHssf == null)
						rowHssf = (HSSFRow) sheet.createRow(rowNum);

					cell = rowHssf.getCell(colNum);
					if (cell == null)
						cell = rowHssf.createCell(colNum);

					cell.setCellValue(data);

					fileOut = new FileOutputStream(path);

					wb.write(fileOut);

					fileOut.close();

				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				return true;
				
				
			}
		}

	public String getDataFromExcelUsingKey(String sheetName, String key){
		return getDataAsMap(sheetName).get(key);
	}
		
	public void closeWorkBoook() throws IOException {
		wb.close();
	}

	public static void main(String[] args) {
		ExcelUtility excel = new ExcelUtility(
				System.getProperty("user.dir") + "\\Testdata\\TestdataFile.xlsx");
		System.out.println("Column Count: " + excel.getColumnCount("Sheet1"));
		System.out.println("Row count: " + excel.getRowCount("Sheet1"));
		
		System.out.println(excel.getCellData("Sheet1", 1 , 1));
	
		System.out.println(excel.getDataAsMap("Sheet1").get("Name"));
	}

}
