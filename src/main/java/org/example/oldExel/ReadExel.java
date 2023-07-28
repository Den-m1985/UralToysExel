package org.example.oldExel;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.example.csvRead.csv.StructureCSV;

import java.util.ArrayList;
import java.util.List;

public class ReadExel {
    private final String fileNamePrice;
    private ArrayList<String[]> notUseArticle;


    public ReadExel(String fileNamePrice) {
        this.fileNamePrice = fileNamePrice;
    }


    public HSSFWorkbook findCellEXEL(List<StructureCSV> data, int cellEXL, int cellPoint, int numberSheet) {
        //Row  строка
        //Cell столб

        OpenOldExelFile openOldExelFile = new OpenOldExelFile(fileNamePrice);
        HSSFWorkbook workbook = openOldExelFile.openFile();
        HSSFSheet sheet = workbook.getSheetAt(numberSheet);

        List<String> list = new ArrayList<>();

        for (StructureCSV csv : data) {
            String articularCSV = csv.getArtucul();

            for (Row row : sheet) {
                Cell cell1 = row.getCell(cellEXL);
                if (cell1 != null) {
                    String articularEXL = cell1.getStringCellValue();
                    if (articularEXL.equals(articularCSV)) {
                        list.add(articularCSV);

                        Row row2 = sheet.getRow(row.getRowNum()); // получаем  строку
                        Cell cell = row2.getCell(cellPoint); // получаем  ячейку

                        if (cell == null) { // если ячейка пустая, создаем ее
                            cell = row.createCell(cellPoint);
                        }
                        String strItem = String.valueOf(csv.getItem());
                        cell.setCellValue(strItem);
                    }
                }
            }
        }

        for (String x:list) {
            System.out.println(x);
        }

        notUseArticle = new ArrayList<>();

//        for (StructureCSV csv : data) {
//            String str = csv.getArtucul();
//            if (!list.contains(str)) {
//                String[] noFind = {str, "артикул НЕ найден"};
//                notUseArticle.add(noFind);
//            }
//        }

        int lastRow = sheet.getLastRowNum() + 1;
        System.out.println("число строк в price: " + lastRow);
        System.out.println("кол-во строк не вошедших в price: " + notUseArticle.size());
        System.out.println("число совпадений " + list.size());

        return workbook;
    }


    public List<String[]> getNotUseArticle() {
        return notUseArticle;
    }

}