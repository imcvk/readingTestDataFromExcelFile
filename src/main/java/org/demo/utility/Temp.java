package org.demo.utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class Temp {
  public static void main (String[] args) {
    String excelFilePath = "src/main/resources/testData.xlsx";
    String testCaseId = "TC1"; // Replace with your test case ID
    String columnId = "TestDataHeader2"; // Replace with your column name
    String sheetName = "testdata1";
    JSONObject jsonObject = readExcelToJSON (excelFilePath, sheetName);
    System.out.println (jsonObject.toString (4));
    System.out.println ("-----------------------------------------------------------------------------------------------");
    JSONObject jsonObject_test_case_data = getTestCaseSpecificData (jsonObject, testCaseId);
    System.out.println (jsonObject_test_case_data.toString ());
    System.out.println ("-----------------------------------------------------------------------------------------------");
    String test_case_columns_data = getColumnSpecificData (jsonObject_test_case_data, testCaseId, columnId);
    System.out.println (test_case_columns_data);
    System.out.println ("-----------------------------------------------------------------------------------------------");
    System.out.println ("Test data from sheet 2");
    System.out.println ("-----------------------------------------------------------------------------------------------");

    sheetName = "testdata2";
    testCaseId = "TC8";
    jsonObject = readExcelToJSON (excelFilePath, sheetName);
    System.out.println (jsonObject.toString (4));
    System.out.println ("-----------------------------------------------------------------------------------------------");
    jsonObject_test_case_data = getTestCaseSpecificData (jsonObject, testCaseId);
    System.out.println (jsonObject_test_case_data.toString ());
    System.out.println ("-----------------------------------------------------------------------------------------------");
    test_case_columns_data = getColumnSpecificData (jsonObject_test_case_data, testCaseId, columnId);
    System.out.println (test_case_columns_data);
    System.out.println ("-----------------------------------------------------------------------------------------------");
  }

  public static JSONObject readExcelToJSON (String filePath, String sheetName) {
    JSONObject jsonObject = new JSONObject ();

    try (FileInputStream fis = new FileInputStream (filePath); Workbook workbook = new XSSFWorkbook (fis)) {

      Sheet sheet = workbook.getSheet (sheetName);
      Iterator<Row> rows = sheet.iterator ();
      Row headerRow = rows.next (); // Assuming the first row is the header
      JSONArray columns = new JSONArray ();

      for (Cell cell : headerRow) {
        columns.put (cell.getStringCellValue ());
      }

      while (rows.hasNext ()) {
        Row row = rows.next ();
        JSONObject testCaseData = new JSONObject ();

        String testCaseId = row.getCell (0).getStringCellValue (); // Assuming the first column is the test case ID

        for (int i = 1; i < columns.length (); i++) {
          Cell cell = row.getCell (i);
          String cellValue = cell != null ? cell.toString () : "";
          testCaseData.put (columns.getString (i), cellValue);
        }

        jsonObject.put (testCaseId, testCaseData);
      }

    } catch (IOException e) {
      e.printStackTrace ();
    }

    return jsonObject;
  }

  public static JSONObject getTestCaseSpecificData (JSONObject jsonObject, String testCaseId) {
    JSONObject testCaseData = jsonObject.getJSONObject (testCaseId);
    return testCaseData;
  }

  public static String getColumnSpecificData (JSONObject jsonObject_test_case_data, String testCaseId, String columnId) {
    String test_case_cell_data_by_columns = jsonObject_test_case_data.get (columnId).toString ();
    return test_case_cell_data_by_columns;
  }
}
