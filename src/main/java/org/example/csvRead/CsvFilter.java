package org.example.csvRead;

import com.opencsv.exceptions.CsvException;
import org.example.csvRead.csv.*;

import java.io.IOException;
import java.util.List;

public class CsvFilter {
    private final String fileName;
    private List<String[]> error;


    public CsvFilter(String fileName) {
        this.fileName = fileName;
    }


    public List<StructureCSV> csvFilter(int cellMinItem, int cellPrice, int cellItem) throws IOException, CsvException {

        List<String[]> rows = new CsvRead().readCSV(fileName);

        // В этом блоке оставляем только те колонки где есть цена и кол-во
        OnlyGoods onlyGoods = new OnlyGoods();
        List<StructureCSV> dataWithItem = onlyGoods.onlyGoods(rows, cellMinItem, cellPrice, cellItem);
        error = onlyGoods.reportCSV();

        // этот блок возвращает иникальные элементы
        UniqueGoods uniqueGoods = new UniqueGoods();
        List<StructureCSV> uniqueValues = uniqueGoods.uniqueGoods(dataWithItem);
        List<StructureCSV> duplicateNames = uniqueGoods.getDuplicateNames();

        // этот блок работатет с повторяющимися именами.
        List<StructureCSV> resolveDuplicatedNames = new DuplicateGoods().duplicateGoods(duplicateNames);
        uniqueValues.addAll(resolveDuplicatedNames);

        System.out.println();
        System.out.println(CsvMenu.COUNROWSCSV + uniqueValues.size());

        return uniqueValues;
    }


    public List<String[]> getError() {
        return error;
    }
}
