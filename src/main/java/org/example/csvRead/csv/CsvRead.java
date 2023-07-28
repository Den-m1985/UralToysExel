package org.example.csvRead.csv;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.*;

import static org.example.Main.encoding;


public class CsvRead {


    public List<String[]> readCSV(String fileName) throws IOException, CsvException {
        List<String[]> rows;

        try (Reader reader = new InputStreamReader(new FileInputStream(fileName), encoding)) {
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build(); // separator with ;
            CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).build();
            rows = csvReader.readAll(); // read all rows in the file
        }
        return rows;
    }


}
