package org.example.startProgram;

import com.opencsv.exceptions.CsvException;
import org.apache.poi.ss.usermodel.Workbook;
import org.example.createPathFile.CreatePathFile;
import org.example.csvRead.CsvFilter;
import org.example.csvRead.csv.StructureCSV;
import org.example.xlsxRead.writeExel.CreateReportExel;
import org.example.xlsxRead.FindArticularXLSX;
import org.example.xlsxRead.writeExel.WriteExelXlsx;

import java.io.IOException;
import java.util.List;


public class Controller {

    public Controller(String pathCSV, String pathXLS) throws IOException, CsvException  {
        long start = System.nanoTime();

        PrintMenu printMenu = new PrintMenu();

        // Read csv
        int cellMinItem = 2;  // колонка с мин кол для заказа
        int cellPrice = 3;
        int cellItem = 4;   // Cell with item to order
        CsvFilter csvFilter = new CsvFilter(pathCSV);
        List<StructureCSV> data = csvFilter.csvFilter(cellMinItem, cellPrice, cellItem);
        List<String[]> reportList = csvFilter.getError();

        // read xls
        int numberSheet = 0;  // номер страницы в файле.
        int cellName = 2;
        int cellEXL = 4;  // Cell with articular
        int cellCode = 5;  // код товара
        int cellPriceXLS = 6;  // price без скидки
        int minToOrder = 8; // миним кол-во для заказа
        int cellWriteItem = 9;  //номер строки куда мы записываем
        FindArticularXLSX findArticularXLSX = new FindArticularXLSX(pathXLS, cellEXL, cellCode,cellPriceXLS, cellWriteItem, numberSheet);
        Workbook workbook = findArticularXLSX.findCellEXEL(data);

        // write xls. new path "Price" in downloads
        CreatePathFile createPathFile = new CreatePathFile();
        String pricePath = createPathFile.createPathFile("Ural_Toys_Price", "xlsx");
        new WriteExelXlsx(workbook, pricePath);

        //no find article
        List<String[]> notUseArticle = findArticularXLSX.getNotUseArticle();
        if (notUseArticle.size() != 0) {
            reportList.addAll(findArticularXLSX.getNotUseArticle());
        }

        System.out.println();
        System.out.println("Сохраненные файлы");
        System.out.println(pricePath);

        //create no find article
        if (reportList.size() != 0) {
            String downloadsPath = createPathFile.createPathFile("Ural_Toys_Report", "xlsx");
            new CreateReportExel(reportList, downloadsPath);
            System.out.println(downloadsPath);
        } else
            System.out.println("Ошибок нет");

        long end = System.nanoTime();
        long time = (end - start) / 1000000000;

        printMenu.printFinish((int) time);

    }

}
