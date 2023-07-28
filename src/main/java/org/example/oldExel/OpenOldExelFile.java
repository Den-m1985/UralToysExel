package org.example.oldExel;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class OpenOldExelFile {
    private String fileNamePrice;


    public OpenOldExelFile(String fileNamePrice) {
        this.fileNamePrice = fileNamePrice;
    }

    public HSSFWorkbook openFile() {
        try {
            // Чтение .xls файла
            FileInputStream inputStream = new FileInputStream(new File(fileNamePrice));
            return new HSSFWorkbook(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
