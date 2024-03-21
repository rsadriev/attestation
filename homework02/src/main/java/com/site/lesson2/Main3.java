package com.site.lesson2;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scaner = new Scanner(System.in);
        long a = scaner.nextLong();
        long b = scaner.nextLong();
        System.out.println(a+b);
        System.out.println(a-b);
        System.out.println(a*b);
        System.out.println((a+b)/2);
        System.out.println(Math.abs (a-b));
        System.out.println(Math.max(a, b));
        System.out.println(Math.min(a, b));
    }
}
