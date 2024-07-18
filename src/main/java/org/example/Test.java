package org.example;

import org.example.createPathFile.GetPathFile;
import org.example.startProgram.Controller;


public class Test {

    public static void main(String[] args) {

        String[] csv = {"csv"};
        String csv2 = openWindow(csv);

        String[] xlsx = {"xlsx"};
        String xlsx2 = openWindow(xlsx);

        try {
            new Controller(csv2, xlsx2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String openWindow(String[] extension) {
        int j = 0;
        String path = null;
        while (j < 2) {
            path = new GetPathFile().getPathFile(extension[0]);
            if (path != null)
                break;
            else j++;
        }
        return path;
    }

}
