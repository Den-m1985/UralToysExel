package org.example.csvRead.csv;

import java.util.ArrayList;
import java.util.List;

public class OnlyGoods {
    private List<String[]> reportCSV;

    public OnlyGoods() {
    }

    public List<StructureCSV> onlyGoods(List<String[]> rows, int cellMinItem, int cellPrice, int cellItem) {

        List<StructureCSV> dataWithItem = new ArrayList<>();
        reportCSV = new ArrayList<>();

        for (String[] row : rows) {
            // Бывает, что в исходнике некорректно сделана структура, эти позиции отправляются в итоговый отчет.
            try {
                // Если в ячейке price и item число, то эту строку добавляем для дальнейшей работы.
                if (isFigure(row[cellMinItem]) && isFigure(row[cellItem]) && isFigure(row[cellPrice])) {
                    int minItem = Integer.parseInt(row[cellMinItem]);
                    int price = floatToInt(row[cellPrice]);
                    int item = Integer.parseInt(row[cellItem]);
                    dataWithItem.add(new StructureCSV(row[0], row[1], minItem, price, item));
                }
            } catch (Exception ignored) {
            }
        }
        return dataWithItem;
    }


    public List<String[]> reportCSV() {
        return reportCSV;
    }


    static boolean isFigure(String str) {
        if (str == null) {
            return false;
        }
        try {
            floatToInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static int floatToInt(String str) {
        if (str.contains(",")) {
            String str2 = str.replace(",", ".");
            return (int) Float.parseFloat(str2);
        }
        return (int) Float.parseFloat(str);
    }


}
