package com.napier.sem;

import java.util.Scanner;

public class UI {

    public static void UI() {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object for user input
        int inputNum = 0;

        // Check if running in CI/CD environment
        boolean isCICD = System.getenv("CI") != null && System.getenv("CI").equals("true");  // This assumes a CI environment variable is set
        System.out.println("Is CI/CD: " + isCICD);  // Debugging output

        // Main loop for user interaction
        while (inputNum != -1) {
            // Print the menu in both interactive and CI/CD mode
            System.out.print("\n _____________________________________________________________\n");
            System.out.println("Enter question number to receive answer: \n" +
                    "(1) Countries from largest to smallest \n" +
                    "(2) Top N populated countries \n" +
                    "(3) Cities from largest to smallest \n" +
                    "(4) Top N populated cities \n" +
                    "(5) Capital cities from largest to smallest \n" +
                    "(6) Top N populated capital cities \n" +
                    "(7) Population of people living in/out of cities \n" +
                    "(8) Total populations \n" +
                    "(9) Language speakers from greatest to smallest \n" +
                    "(-1) Exit");

            // Handle input based on environment mode
            if (!isCICD) {
                System.out.print("Your choice: ");  // Prompt for input
                try {
                    inputNum = Integer.parseInt(scanner.nextLine());  // Read input and parse it
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    continue;  // Skip to the next iteration if input is invalid
                }
            } else {
                // CI/CD mode: Automatically run tests for options 1 to 9
                for (int testInput = 1; testInput <= 9; testInput++) {
                    System.out.println("Running automated test for option " + testInput);
                    inputNum = testInput;

                    // Process the test input
                    switch (inputNum) {
                        case 1: Countries_LargestToSmallest(); break;
                        case 2: Countries_TopPopulated(); break;
                        case 3: Cities_LargestToSmallest(); break;
                        case 4: Cities_TopPopulated(); break;
                        case 5: CapCities_LargestToSmallest(); break;
                        case 6: CapCities_TopPopulated(); break;
                        case 7: Population_InAndOutOfCities(); break;
                        case 8: TotalPopulations(); break;
                        case 9: Languages_GreatestToSmallest(); break;
                    }
                }
                inputNum = -1;  // End the loop after automated tests
            }

            // Validate the input range if not in CI/CD mode
            if (inputNum < -1 || inputNum > 9) {
                System.out.println("Invalid input. Please enter a number between -1 and 9.");
                continue;  // Skip to the next iteration for invalid inputs
            }

            // Process the user input
            switch (inputNum) {
                case 1: Countries_LargestToSmallest(); break;
                case 2: Countries_TopPopulated(); break;
                case 3: Cities_LargestToSmallest(); break;
                case 4: Cities_TopPopulated(); break;
                case 5: CapCities_LargestToSmallest(); break;
                case 6: CapCities_TopPopulated(); break;
                case 7: Population_InAndOutOfCities(); break;
                case 8: TotalPopulations(); break;
                case 9: Languages_GreatestToSmallest(); break;
                case -1: Exit(); break;  // Exit on -1
            }
        }
        scanner.close();  // Close the scanner to free resources
    }

    public static void Countries_LargestToSmallest()
    {
        System.out.println("Selected: Countries_LargestToSmallest");
    }

    public static void Countries_TopPopulated()
    {
        System.out.println("Selected: Countries_TopPopulated");
    }

    public static void Cities_LargestToSmallest()
    {
        System.out.println("Selected: Cities_LargestToSmallest");
    }

    public static void Cities_TopPopulated()
    {
        System.out.println("Selected: Cities_TopPopulated");
    }

    public static void CapCities_LargestToSmallest()
    {
        System.out.println("Selected: CapCities_LargestToSmallest");
    }

    public static void CapCities_TopPopulated()
    {
        System.out.println("Selected: CapCities_TopPopulated");
    }

    public static void Population_InAndOutOfCities()
    {
        System.out.println("Selected: Population_InAndOutOfCities");
    }

    public static void TotalPopulations()
    {
        System.out.println("Selected: TotalPopulations");
    }

    public static void Languages_GreatestToSmallest()
    {
        System.out.println("Selected: Languages_GreatestToSmallest");
    }

    public static void Exit()
    {
        System.out.println("Exiting....");
        System.exit(0);
    }

}
