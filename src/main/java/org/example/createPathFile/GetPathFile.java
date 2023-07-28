package org.example.createPathFile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class GetPathFile {
    private String path;


    public String getPathFile(String extension) {

        JFileChooser fileOpen = new JFileChooser();

        fileOpen.setPreferredSize(new Dimension(600, 500)); // window size
        FileNameExtensionFilter filter = new FileNameExtensionFilter("files " + extension, extension);
        fileOpen.setFileFilter(filter);


        // open directory by default.
        fileOpen.setCurrentDirectory(new File(System.getProperty("user.home") + File.separator +
                "Desktop"));
        int ret = fileOpen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileOpen.getSelectedFile();
            path = file.getAbsolutePath();
        }

        return path;
    }
}
