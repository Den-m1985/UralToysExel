package org.example.xlsxRead.writeExel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class CreateExel {

    public XSSFWorkbook createExel(List<String[]> list) {
        XSSFWorkbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Sheet1");

        //Row  строка
        //Cell столб
        Row rowTop = sheet.createRow(0);
        Cell targetCell = rowTop.createCell(0);
        targetCell.setCellValue("Проверить эти товары");

//        rowTop.createCell(0,)
//        rowTop.createCell(0).setCellValue("Проверить эти товары");

        for (int i = 0; i < list.size(); i++) {
            Row row = sheet.createRow(i+2);
            row.createCell(0).setCellValue(list.get(i)[0]);
            row.createCell(2).setCellValue(list.get(i)[1]);
        }
        return workbook;
    }

}
