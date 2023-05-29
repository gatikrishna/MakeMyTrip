package com.demo.mypack.util;

import com.demo.mypack.GlobalProperty;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class CommonUtil {

    private static final Logger LOGGER  = Logger.getLogger(CommonUtil.class);

    public static void readPropertiesFile(String filePath) {
        try {
            FileInputStream file = new FileInputStream(filePath);
            Properties prop = new Properties();
            prop.load(file);
            prop.forEach((k, v) -> GlobalProperty.setProperty(k.toString(), v.toString()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void readFiles() {
        String filePath = ".\\..\\Demo\\src\\main\\resources\\application.properties";
        readPropertiesFile(filePath);
        String env = GlobalProperty.getProperty("env") + ".properties";
        filePath = ".\\..\\Demo\\src\\main\\resources\\" + env;
        readPropertiesFile(filePath);
    }

    public void readExcel() {
        try {
            FileInputStream fi = new FileInputStream(new File("src/main/resources/test.xlsx"));
            Workbook wb = new XSSFWorkbook(fi);
            Sheet sheet = wb.getSheet("Sheet1");
            Map<Integer, String> excelTopRowMap = new HashMap<Integer, String>();
            Row firstRow = sheet.getRow(sheet.getFirstRowNum());
            for (Integer i = 0; i < firstRow.getLastCellNum(); i++) {
                excelTopRowMap.put(i, firstRow.getCell(i).getStringCellValue());
            }


            Iterator<Row> rowSet = sheet.iterator();
            List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
            while (rowSet.hasNext()) {
                Row row = rowSet.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                HashMap<String, Object> map = new HashMap<String, Object>();
                Integer i = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getCellType() == CellType.NUMERIC) {
                        LOGGER.info("inside Numeric value: ");
                        map.put(excelTopRowMap.get(i), cell.getNumericCellValue());
                    } else if (cell.getCellType() == CellType.STRING) {
                        map.put(excelTopRowMap.get(i), cell.getStringCellValue());
                    }
                }
                list.add(map);

            }
//            Row row = sheet.getRow(0);
//            Cell cell = row.getCell(0);
//            list.stream((k)->k);
            LOGGER.info("cell value: " + list.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
