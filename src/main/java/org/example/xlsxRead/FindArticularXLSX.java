package org.example.xlsxRead;

import org.apache.poi.ss.usermodel.*;
import org.example.csvRead.csv.StructureCSV;
import org.example.xlsxRead.readExel.XlsxReader;

import java.util.ArrayList;
import java.util.List;

public class FindArticularXLSX {
    private final String pathXLS;
    private ArrayList<String[]> notUseArticle;
    private List<String> list;
    private final int cellEXL;
    private final int cellWriteItem;
    private final int numberSheet;
    private final int cellCode;
    private final int cellPriceXLS;


    public FindArticularXLSX(String pathXLS, int cellEXL, int cellCode, int cellPriceXLS, int cellWriteItem, int numberSheet) {
        this.pathXLS = pathXLS;
        this.cellEXL = cellEXL;
        this.cellCode = cellCode;
        this.cellPriceXLS = cellPriceXLS;
        this.cellWriteItem = cellWriteItem;
        this.numberSheet = numberSheet;
    }


    public Workbook findCellEXEL(List<StructureCSV> data) {
        //Row  строка
        //Cell столб
        Workbook workbook = new XlsxReader().xlsxRead(pathXLS);
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
        String articularCSV = csv.getArtucul();  // артикул с csv
        int priceCSV = csv.getPrice();  // price с csv
        boolean checkArticXLSX_CSV = false;

        for (Row row : sheet) {
            String articularEXL = cellRead(row.getCell(cellEXL));  // артикул с xlsx
            String codeExl = cellRead(row.getCell(cellCode));   // код товара с xlsx
            String cellPriceXlsDiscount = cellRead(row.getCell(cellPriceXLS));   // цена товара без скидки с xlsx


            if (articularEXL != null && codeExl != null) {
                checkArticXLSX_CSV = checkArticular(articularEXL, articularCSV, codeExl);
                if (checkArticXLSX_CSV) {
                    CheckPrice checkPrice = new CheckPrice();
                    boolean checkPriceXLSX = checkPrice.checkPrice(cellPriceXlsDiscount, priceCSV);
                    if (checkPriceXLSX) {
                        list.add(articularCSV);
                        String itemString = String.valueOf(csv.getItem()); // получаем кол-во, переводим в строку
                        cellWrite(sheet, row, itemString);  // записываем в ячейку
                        break;
                    } else notUseArticle.add(checkPrice.getErrorPrice(articularCSV));
                }
            }
        }
        if (!checkArticXLSX_CSV) {
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
        Cell cell = row2.getCell(cellWriteItem); // получаем  ячейку

        if (cell == null) { // если ячейка пустая, создаем ее
            cell = row.createCell(cellWriteItem);
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
        } else if (articularEXL.equals(articularCSV) && codeExl.equals(articularCSV)) {
            return true;
        }

        return false;
    }


    public List<String[]> getNotUseArticle() {
        return notUseArticle;
    }

}
