package org.example.window.helper_classes;

import org.example.startProgram.Command;
//import org.example.command.Command;
import org.example.createPathFile.GetPathFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartCommand implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        String pathCSV = null;
        String pathXLS = null;

        GetPathFile getPathFile = new GetPathFile();

        int i = 0;
        while (i < 2) {
            pathCSV = getPathFile.getPathFile("csv");
            if (pathCSV != null)
                break;
            else i++;
        }

        int j = 0;
        while (j < 2) {
            pathXLS = getPathFile.getPathFile("xlsx");
            if (pathXLS != null)
                break;
            else j++;
        }

        System.out.println();
        System.out.println("Файлы исходники:");
        System.out.println(pathCSV);
        System.out.println(pathXLS);
        System.out.println();



        try {
            new Command().command(pathCSV, pathXLS);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null,"Успешно");
    }

}
