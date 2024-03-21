package com.site.lesson3;

import java.util.Scanner;

public class task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String pad = scanner.next();
        long count = scanner.nextLong();


            String line = new String();
            for(int j=0; j < count; j++ )
            {
                line += pad;
            }
            System.out.println(line);

    }
}
