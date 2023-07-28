package org.example.createPathFile;

import java.io.File;

public class CreatePathFile {

    public CreatePathFile() {
    }


    public String  createPathFile (String fileName, String extension){

        Date date = new Date();
        String str = date.currentDate();

        return System.getProperty("user.home") + File.separator +
                "Downloads"+"\\" + fileName + "_" + str + "." + extension;
    }


}
