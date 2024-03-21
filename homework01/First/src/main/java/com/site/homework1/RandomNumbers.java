package com.site.homework1;

public class RandomNumbers
{
    public static int getRandomDiceNumber()
    {
        return (int) (Math.random() * 10) + 1;
    }

    public static void main(String[] args)
    {
        for (int i = 0; i < 4; i++)
        {
            int x = getRandomDiceNumber();
            System.out.print(x + "   ");
        }
    }
}
