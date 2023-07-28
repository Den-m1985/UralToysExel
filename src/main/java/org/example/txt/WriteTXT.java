package org.example.txt;

import org.example.createPathFile.CreatePathFile;
import org.example.createPathFile.Date;
import org.example.txt.writeTxt.WriteTxtFile;


public class WriteTXT {

    public WriteTXT(String data) {

        // создаем имя файла
        CreatePathFile createPathFile = new CreatePathFile();

        String downloadsPath = createPathFile.createPathFile("Ural_Toys_TXT", "txt");

        Date date = new Date();
        String time = date.currentDate();

        new WriteTxtFile(downloadsPath, time, data);
    }
}
