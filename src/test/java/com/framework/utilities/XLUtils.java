package com.framework.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class XLUtils {
    List<HashMap<String, String>> lst;
    HashMap<String, String> hMap;
    Workbook wb;

    public XLUtils() {
        try {
            String filePath = System.getProperty("user.dir") + "/src/test/java/com/framework/testData/TestData.xlsx";
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            wb = new XSSFWorkbook(fis);
        } catch (Exception ex) {
        }

    }

    public List<HashMap<String, String>> readExcelData(String sheetName, String tcName) {
        Sheet sh = wb.getSheet(sheetName);
        int rowCount = sh.getLastRowNum();
        int colCount = sh.getRow(0).getLastCellNum();
        lst = new ArrayList<>();
        for (int i = 1; i <= rowCount; i++) {
            hMap = new HashMap<>();
            Row row = sh.getRow(i);
            if (row.getCell(0).toString().trim().equalsIgnoreCase(tcName)) {
                for (int j = 0; j < colCount; j++) {
                    hMap.put(sh.getRow(0).getCell(j).getStringCellValue(), getCellValue(sh.getRow(i).getCell(j)));
                }
                lst.add(hMap);

            } else {
                continue;
            }

        }

        return lst;

    }

    public String getCellValue(Cell cell) {
        DataFormatter fmt = new DataFormatter();
        return fmt.formatCellValue(cell);
    }


    public Object[][] getTestData(String sheetName, String testCaseName) throws IOException {
        XLUtils xUtils = new XLUtils();
        List<HashMap<String, String>> lst = xUtils.readExcelData(sheetName, testCaseName);
        int lstSize = lst.size();
        Object[][] loginData = new Object[lstSize][1];
        for (int i = 0; i < lst.size(); i++) {
            loginData[i][0] = lst.get(i);

        }

        return loginData;

    }
}
