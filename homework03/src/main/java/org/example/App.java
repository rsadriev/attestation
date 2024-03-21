package org.example;

public class App {
    public static void main(String[] args) {
        TV lg = new TV("colors", "lg", 24.5);
        TV samsung = new TV("colors", "samsung", 27.0);
        TV zarya = new TV("B-W", "ussr", 22.0);
        System.out.println(lg);
        System.out.println(samsung);
        System.out.println(zarya);
    }
}