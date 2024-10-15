package com.napier.sem;

import java.io.Console;

public class UI {

    public static void main(String[] args)
    {
        int inputNum = 0;

        while (inputNum != -1)
        {
            // Ask for input, give options
            System.out.print("\n _____________________________________________________________\n");
            System.out.println("Enter question number to recieve answer: \n " +
                    "(1) Countries from largest to smallest \n " +
                    "(2) Top N populated countries \n " +
                    "(3) Cities from largest to smallest \n " +
                    "(4) Top N populated cities \n " +
                    "(5) Capital cities from largest to smallest \n " +
                    "(6) Top N populated capital cities \n " +
                    "(7) Population of people living in/out of cities \n " +
                    "(8) Total populations \n " +
                    "(9) Language speakers from greatest to smallest \n " +
                    "(-1) Exit" );

            // Read in number input, make sure it is in range, and is an int
            try
            {
                String inputString = "";
                Console console = System.console();
                inputString = console.readLine();

                inputNum = Integer.parseInt(inputString);

                if(inputNum < -1 || inputNum > 9)
                {
                    throw new Exception();
                }

                switch (inputNum)
                {
                    case 1: Countries_LargestToSmallest(); break;
                    case 2: Countries_TopPopulated(); break;
                    case 3: Cities_LargestToSmallest(); break;
                    case 4: Cities_TopPopulated(); break;
                    case 5: CapCities_LargestToSmallest(); break;
                    case 6: CapCities_TopPopulated(); break;
                    case 7: Population_InAndOutOfCities(); break;
                    case 8: TotalPopulations(); break;
                    case 9: Languages_GreatestToSmallest(); break;
                    case -1: Exit(); break;
                }
                break;
            }
            catch (Exception e)
            {
                System.out.println("Input must be an int between -1 and 9");
                inputNum = 0;
            }
            //Call function based on input
        }

    }


    public static void Countries_LargestToSmallest()
    {

    }

    public static void Countries_TopPopulated()
    {

    }

    public static void Cities_LargestToSmallest()
    {

    }

    public static void Cities_TopPopulated()
    {

    }

    public static void CapCities_LargestToSmallest()
    {

    }

    public static void CapCities_TopPopulated()
    {

    }

    public static void Population_InAndOutOfCities()
    {

    }

    public static void TotalPopulations()
    {

    }

    public static void Languages_GreatestToSmallest()
    {

    }

    public static void Exit()
    {

    }
}
