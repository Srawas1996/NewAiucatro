package org.example.base;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelReader {
    public static Object[][] readExcel(String filePath, String SheetName){
        try{
            FileInputStream fis = new FileInputStream(filePath);
            Workbook wb = new XSSFWorkbook(fis);
            Sheet sheet = wb.getSheet(SheetName);

            int rows = sheet.getPhysicalNumberOfRows();
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();

            Object[][] data = new Object[rows - 1][cols];

            for (int i = 1; i < rows; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < cols; j++) {
                    data[i - 1][j] = row.getCell(j).toString();
                }
            }

            wb.close();
            return data;

        } catch (Exception e) {
            throw new RuntimeException("Failed to find or interact with element", e);
        }
    }
}
