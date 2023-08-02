package org.example.xlsxRead;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class TestXlsx {


    public static void main(String[] args) {
//        String chromedriver = System.getProperty("user.Downloads") + File.separator +
//                "chromedriver_win32"+"\\" + "chromedriver.exe";
//
//        String pathXLS = System.getProperty("user.Downloads") + File.separator +
//                "price-ural-toys-18.07.2023_14-58.xlsx";
        String pathXLS = "C:\\Users\\User\\Downloads\\price-ural-toys-18.07.2023_14-58.xlsx";

        //int numberSheet = 0; // номер страницы
        //List<List<String>> data = new Data().data(pathXLS, numberSheet);

        //System.out.println(data.size());




/*
тест на скорость чтения File и FileInputStream
 */
        int tempt = 10;
        int time1 = 0;

        for (int i = 0; i < tempt; i++) {

            long start = System.nanoTime();
            try {
                XSSFWorkbook c =  new XSSFWorkbook(new File(pathXLS));
                c.close();
            } catch (IOException | InvalidFormatException e) {
                e.printStackTrace();
            }
            long end = System.nanoTime();
            long time = (end - start) / 1000000000;
            time1 = time1 + (int)time;
        }
        System.out.println(time1/tempt + "    new File");




        int time2 = 0;

        for (int i = 0; i < tempt; i++) {

            long start = System.nanoTime();
            try {
                XSSFWorkbook c =  new XSSFWorkbook(new FileInputStream(pathXLS));
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            long end = System.nanoTime();
            long time = (end - start) / 1000000000;
            time2 = time2 + (int)time;
        }
        System.out.println(time2/tempt + "    new FileInputStream");


    }
}
