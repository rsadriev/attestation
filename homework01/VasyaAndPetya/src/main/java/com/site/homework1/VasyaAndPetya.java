package com.site.homework1;

import java.util.Random;

public class VasyaAndPetya {


        public static void main(String args[]) {
            Random rd = new Random();
            String name[];
            int index;
            name = new String[2];
            name[0] = "Петя";
            name[1] = "Вася";
           index = rd.nextInt(2);
            System.out.println(name[index] + " выиграл");
        }


}
