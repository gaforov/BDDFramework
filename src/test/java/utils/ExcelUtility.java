package utils;

import com.google.common.collect.Maps;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtility {
    private static FileInputStream fileInputStream;
    private static Workbook workbook;
    private static Sheet sheet;

//    public static void main(String[] args) {
//        String projectPath = System.getProperty("user.dir");  // The last forward-slash '/' at the very end is not included in the path. If you append anything start with /filename/etc.
//        System.out.println(projectPath);
//        String filePath = projectPath + "/data/HrmTestData.xlsx";
//        System.out.println(filePath);
//        String absoluteFilePath = System.getProperty("user.dir") + "/data/HrmTestData.xlsx";
//        System.out.println(absoluteFilePath);
//        System.out.println(Arrays.deepToString(excelToArray(System.getProperty("user.dir") + "/data/HrmTestData.xlsx", "Employee")));
//    }

    private static void openExcel(String filePath) {
        try {
            fileInputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadSheet(String sheetName) {
        sheet = workbook.getSheet(sheetName);
    }

    private static int rowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    private static int colsCount() {
        return sheet.getRow(0).getLastCellNum(); // blank cells, in between, are counted as well
    }

    private static String cellData(int rowIndex, int colIndex) {
        return sheet.getRow(rowIndex).getCell(colIndex).toString();
    }

    // Return a 2d object array of data (using inner loop to retrieve data)
    public static Object[][] excelToArray(String filePath, String sheetName) {
        openExcel(filePath);
        loadSheet(sheetName);

        int rows = rowCount();
        int cols = colsCount(); // this is calling above method and is same as:
                                          // sheet.getRow(0).getLastCellNum(); Length of given column.

        Object[][] data = new Object[rows - 1][cols];  // -1 is to deduct header from the rows.

        for (int i = 1; i < rows; i++) {             // we start from 2nd raw, skip header(which is index of 0), thus we start with i=1.
            for (int j = 0; j < cols; j++) {         // we start from 1st col of 2nd row, thus j=0.
                data[i - 1][j] = cellData(i, j);     // coordinates of very first top-left corner cell is row=0,col=0.
            }
        }
        //System.out.println(Arrays.deepToString(data)); // this is to print cell data when needed and see if there is any null/empty cells exits, to void nullException.

        // Once you are done, close everything.
        try {
            workbook.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;

    }




    //Above code slightly differently, without loadSheet() method. Method with single-parameter --> sheetName as parameter only.
    private static String filePath = Constants.TESTDATA_FILEPATH;

    static void openExcel2(String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int numberOfRows() {
        return sheet.getPhysicalNumberOfRows();
    }

    static int numberOfColumns() {
        return sheet.getRow(0).getLastCellNum();
    }

    static String getCellData(int rowIndex, int cellIndex) {
        return sheet.getRow(rowIndex).getCell(cellIndex).toString();
    }

    /**
     * Overloaded method with single parameter - only spreadsheet name required. File destination is managed
     * from Constants Class, "Constants.TESTDATA_FILEPATH".
     * @param sheetName provide sheet name you are about to pull data from
     * @return loops through and returns cell data from given spreadsheet, excluding header data.
     */
    public static Object[][] excelToArray(String sheetName) {
        openExcel2(sheetName);
        Object[][] data = new Object[numberOfRows()-1][numberOfColumns()]; // we do not include row-header in total count, thus we deduct one row by inserting -1
        for (int i = 1; i < numberOfRows(); i++) {
            for (int j = 0; j < numberOfColumns(); j++) {
                data[i-1][j] = getCellData(i, j);
            }
        }
        return data;
    }

    // Note: Map version: Retrieve data using Map, instead of inner loop.
    public static List<Map<String, String>> excelIntoListOfMaps(String filePath, String sheetName) {
        openExcel(filePath);
        loadSheet(sheetName);

        List<Map<String,String>> mapsList = new ArrayList<>();
        Map<String, String> excelMap; // LHM preserves insertion order


        for (int i = 1; i < rowCount(); i++) {
            excelMap = new LinkedHashMap<>();
            for (int j = 0; j < colsCount(); j++) {
                excelMap.put(cellData(0,j),cellData(i,j));
            }
            mapsList.add(excelMap);
        }
        return mapsList;
    }

}
