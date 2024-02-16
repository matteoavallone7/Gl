package com.example.ispw.utilities;

import java.util.Scanner;

public class Printer {

    protected Printer(){}

    public static void print(String text){
        System.out.println(text);
    }

    public static  void printError(String text){
        System.err.println(text);
    }

    public static String getChoice() {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine().trim();
        return inputLine;
    }

}
