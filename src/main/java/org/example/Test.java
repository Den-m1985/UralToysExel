package org.example;


import org.example.startProgram.Controller;

public class Test {

    public static void main(String[] args) {


        //String pathCSV = "C:" + System.lineSeparator() + "Users" + System.lineSeparator() + "User" +
        //        System.lineSeparator() + " Downloads" + System.lineSeparator() + "vendor578451_2023-07-18.csv";
        String pathCSV = "C:\\Users\\User\\Downloads\\vendor578451_2023-07-18.csv";
        String pathXLS = "C:\\Users\\User\\Downloads\\price-ural-toys-18.07.2023_14-58.xlsx";
        //String pathXLS = "C:\\Users\\User\\Downloads\\Книга1.xlsx";

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


    }


}
