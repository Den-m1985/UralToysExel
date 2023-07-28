package org.example.xlsxRead.readExel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class XlsxReader {


    public Workbook xlsxRead(String filePath) {

        try {
            return new XSSFWorkbook(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}