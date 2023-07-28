package org.example;

import org.example.window.Window;

import java.io.UnsupportedEncodingException;

public class Main {
    public static final String encoding = System.getProperty("console.encoding", "windows-1251");
    public static void main(String[] args) throws UnsupportedEncodingException {
        Window window = new Window();
        window.setVisible(true);
    }
}