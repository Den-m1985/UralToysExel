package org.example.xlsxRead;


import org.apache.poi.ss.usermodel.*;
import org.example.csvRead.csv.StructureCSV;
import org.example.xlsxRead.readExel.XlsxReader;

import java.util.ArrayList;
import java.util.List;

public class FindArticularXLSX {
    private final String fileNamePrice;
    private ArrayList<String[]> notUseArticle;
    private List<String> list;
    private final int cellArticular;
    private final int cellPoint;
    private final int numberSheet;
    private final int cellCode;


    public FindArticularXLSX(String fileNamePrice, int cellArticular, int cellCode, int cellPoint, int numberSheet) {
        this.fileNamePrice = fileNamePrice;
        this.cellArticular = cellArticular;
        this.cellCode = cellCode;
        this.cellPoint = cellPoint;
        this.numberSheet = numberSheet;
    }


    public Workbook findCellEXEL(List<StructureCSV> data) {
        //Row  строка
        //Cell столб

        Workbook workbook = new XlsxReader().xlsxRead(fileNamePrice);
        Sheet sheet = workbook.getSheetAt(numberSheet);

        list = new ArrayList<>();
        notUseArticle = new ArrayList<>();

        for (StructureCSV csv : data) {
            iteratorSheet(sheet, csv);
        }

        System.out.println("число совпадений " + list.size());

        return workbook;
    }


    private void iteratorSheet(Sheet sheet, StructureCSV csv) {
        String articularCSV = csv.getArtucul();
        boolean check = false;

        for (Row row : sheet) {
            Cell cellArtic = row.getCell(cellArticular);
            String articularEXL = cellRead(cellArtic);

            Cell cellCodeExl = row.getCell(cellCode);
            String codeExl = cellRead(cellCodeExl);

            if (articularEXL != null && codeExl != null) {
                check = checkArticular(articularEXL, articularCSV, codeExl);
                if (check) {
                    list.add(articularCSV);
                    String itemString = String.valueOf(csv.getItem()); // получаем кол-во, переводим в строку

                    Row row2 = sheet.getRow(row.getRowNum()); // получаем  строку
                    Cell cell = row2.getCell(cellPoint); // получаем  ячейку

                    if (cell == null) { // если ячейка пустая, создаем ее
                        cell = row.createCell(cellPoint);
                    }
                    cell.setCellValue(itemString);  // записываем значение

                    //cellWrite(sheet, row, itemString);
                    break;
                }
            }
        }
        if (!check) {
            String[] str = {articularCSV, "не найден артикул"};
            notUseArticle.add(str);
        }
    }


    // Получение содержимого ячейки в зависимости от типа данных
    private String cellRead(Cell cell) {
        if (cell != null) {
            CellType cellType = cell.getCellType();
            if (cellType == CellType.STRING) {
                return cell.getStringCellValue();
            } else if (cellType == CellType.NUMERIC) {
                double numericValue = cell.getNumericCellValue();
                int intValue = (int) numericValue;  // Преобразование к целому числу
                return String.valueOf(intValue);
            } else if (cellType == CellType.BOOLEAN) {
                return String.valueOf(cell.getBooleanCellValue());
            }
        }
        return null;
    }

    // запись в ячейку
    private void cellWrite(Sheet sheet, Row row, String itemString) {
        Row row2 = sheet.getRow(row.getRowNum()); // получаем  строку
        Cell cell = row2.getCell(cellPoint); // получаем  ячейку

        if (cell == null) { // если ячейка пустая, создаем ее
            cell = row.createCell(cellPoint);
        }
        cell.setCellValue(itemString);  // записываем значение
    }


    // проверяем регистры в артикулах
    private boolean checkArticular(String articularEXL, String articularCSV, String codeExl) {
        if (articularCSV.contains(" ")) {
            String[] divided = articularCSV.split(" ");
            String csvCode = divided[1];
            if (divided[1].contains("("))
                csvCode = csvCode.substring(1, csvCode.length() - 1);  // отсекаем скобки
            if (articularEXL.equals(divided[0]) && codeExl.equals(csvCode)) {
                return true;
            }
            String up = divided[0].toUpperCase();
            if (up.equals(articularEXL) && codeExl.equals(csvCode)) {
                return true;
            }
            String down = divided[0].toLowerCase();
            if (down.equals(articularEXL) && codeExl.equals(csvCode)) {
                return true;
            }
        }
        else if (articularEXL.equals(articularCSV) && codeExl.equals(articularCSV)) {
                return true;
            }

        return false;
    }


    public List<String[]> getNotUseArticle() {
        return notUseArticle;
    }

}