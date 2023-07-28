package org.example;


import org.example.startProgram.Command;

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
            new Command().command(pathCSV, pathXLS);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

//        String a = "У572 (241444)";
//        String b = "У572";
//        String c = "241444";
//        boolean d = checkArticular(b, a, c);


    }

//    private static boolean checkArticular(String articularEXL, String articularCSV, String codeExl) {
//        if (articularCSV.contains(" ")) {
//            String[] divided = articularCSV.split(" ");
//            String csvCode = divided[1];
//            if (divided[1].contains("("))
//                csvCode = csvCode.substring(1, csvCode.length() - 1);  // отсекаем скобки
//            if (articularEXL.equals(divided[0]) && codeExl.equals(csvCode)) {
//                return true;
//            }
//            String up = divided[0].toUpperCase();
//            if (up.equals(articularEXL) && codeExl.equals(csvCode)) {
//                return true;
//            }
//            String down = divided[0].toLowerCase();
//            if (down.equals(articularEXL) && codeExl.equals(csvCode)) {
//                return true;
//            }
//        }
//        else if (articularEXL.equals(articularCSV) && codeExl.equals(articularCSV)) {
//            return true;
//        }
//
//        return false;
//    }



}
