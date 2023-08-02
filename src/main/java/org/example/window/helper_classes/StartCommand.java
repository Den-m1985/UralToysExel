package org.example.window.helper_classes;

import org.example.startProgram.Controller;
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
            if (pathCSV != null) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Не выбран файл csv с SP39");
                i++;

            }
        }

        int j = 0;
        while (j < 2) {
            pathXLS = getPathFile.getPathFile("xlsx");
            if (pathXLS != null)
                break;
            else {
                JOptionPane.showMessageDialog(null, "Не выбран файл xlsx поставщика");
                j++;
            }
        }



        if (pathCSV == null && pathXLS == null) {
            throw new RuntimeException("Не выбраны файлы для работы");
        }

        System.out.println();
        System.out.println("Файлы исходники:");
        System.out.println(pathCSV);
        System.out.println(pathXLS);
        System.out.println();

        try {
            new Controller(pathCSV, pathXLS);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Успешно");
    }

}
