package org.example.xlsxRead.readExel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class XlsxReader {


    public Workbook xlsxRead(String filePath) {
        /*
        Тест показал, что FileInputStream работает быстрее чем File.
         */

        try {
            return new XSSFWorkbook(new FileInputStream(filePath));
            //return new XSSFWorkbook(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
