package com.site.lesson1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double farengate = scanner.nextDouble();
        double Celcius = ( 5.0/9.0 ) * (farengate - 32.0);
        System.out.println (Celcius);
    }
}
