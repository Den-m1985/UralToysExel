package org.example.csvRead;

import com.opencsv.exceptions.CsvException;
import org.example.csvRead.csv.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestCSV {

    public static void main(String[] args) throws IOException, CsvException {

        String pathCSV = "C:\\Users\\User\\Downloads\\vendor578451_2023-07-18.csv";

        StructureCSV goods = new StructureCSV("name", "articular", 1, 2, 3);

        // Read csv
        int cellMinItem = 2;  // колонка с мин кол для заказа
        int cellPrice = 3;
        int cellItem = 4;   // Cell with item to order
        CsvFilter csvFilter = new CsvFilter(pathCSV);
        List<StructureCSV> data = csvFilter.csvFilter(cellMinItem, cellPrice, cellItem);
        List<String[]> reportList = new ArrayList<>(csvFilter.getError());


        for (StructureCSV x : data) {
            System.out.println(x.toString());
        }
        System.out.println();
        for (String[] x : reportList) {
            //System.out.println(x[0] + "---" + x[1]);
        }


    }


}
