package org.example.startProgram;

public class PrintMenu {

    public static final String TEXTFILEOPEN = "Файл исходник:";

    public void printFinish(int time){
        String finishText1 = "\n" +
                "У С П Е Ш Н О \n";
        System.out.println(finishText1);
        System.out.println("Время выполнения: " + time + "сек");

        String finishText2 = "Оля молодец\n" +
                "\n" +
                "Попей чайку";
        System.out.println(finishText2);

    }

}
