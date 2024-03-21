package com.site.lesson4;

import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long count = scanner.nextLong();
        String pad = scanner.next();

        for(int i=0; i < count; i++ )
        {
            String line = new String();
            for(int j=0; j < count; j++ )
            {
                line += pad;
            }
            System.out.println(line);
        }
    }
}
