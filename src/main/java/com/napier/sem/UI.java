/**
 * Application Created by 40690819, 40664564,40592313
 * IN THE APP FILE WE HAVE MADE A CONNECTION TO THE DATABASE AND CHECKS THAT IT CONNECTS OR NOT
 * THE SCRIPT WILL RUN AN SQL STATEMENT AND GIVE RESULTS
 **/
package com.napier.sem;

import java.util.Scanner;

public class UI {
    private App app;

    // Constructor that accepts an App instance with an active connection
    public UI(App app) {
        this.app = app;
    }

    public void UI() {
        Scanner scanner = new Scanner(System.in);
        int inputNum = 0;

        while (inputNum != -1) {
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

            System.out.print("Your choice: ");
            try {
                inputNum = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                continue;
            }

            if (inputNum < -1 || inputNum > 9) {
                System.out.println("Invalid input. Please enter a number between -1 and 9.");
                continue;
            }

            switch (inputNum) {
                case 1:
                    System.out.println("Countries from largest to smallest:");
                    // Countries_LargestToSmallest();
                    break;
                case 2:
                    System.out.println("Selected: Countries_TopPopulated");
                    // Add the actual logic for Top N populated countries
                    break;
                case 3:
                    System.out.println("Cities from largest to smallest:");

                    break;
                case 4:
                    System.out.println("Top N populated cities:");

                    break;
                case 5:
                    System.out.println("Selected: CapCities_LargestToSmallest");
                    // Add the actual logic for Capital cities from largest to smallest
                    break;
                case 6:
                    System.out.println("Selected: CapCities_TopPopulated");
                    // Add the actual logic for Top N populated capital cities
                    break;
                case 7:
                    System.out.println("Selected: Population_InAndOutOfCities");
                    // Add the actual logic for Population in and out of cities
                    break;
                case 8:
                    System.out.println("Total populations: ");
                    TotalPopulations();
                    break;
                case 9:
                    System.out.println("Selected: Languages_GreatestToSmallest");
                    // Add the actual logic for Language speakers from greatest to smallest
                    break;
                case -1:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Feature not yet implemented.");
            }
        }
        scanner.close();
    }

    //Terminal UI for population questions
    public void TotalPopulations() {
        Scanner scanner = new Scanner(System.in);
        int inputNum = 0;

        while (inputNum != -1) {

            System.out.print(" \n______________________________\n");
            System.out.println(
                    "(1) Population of the world \n" +
                            "(2) Population of a continent \n" +
                            "(3) Population of a country \n" +
                            "(4) Population of a region \n" +
                            "(5) Population of a district \n" +
                            "(6) Population of a city \n" +
                            "(-1) Exit");

            System.out.print("Your choice: ");
            try {
                inputNum = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer");
                continue;
            }

            switch (inputNum) {
                case 1:
                    System.out.println("Population of the world:\n_______\n");
                    app.Population_World();
                    inputNum = -1;
                    break;
                case 2:
                    System.out.println("Population of a continent:\n_______\n");
                    app.Population_Continent();
                    inputNum = -1;
                    break;
                case 3:
                    System.out.println("Population of a country:\n_______\n");
                    app.Population_Country();
                    inputNum = -1;
                    break;
                case 4:
                    System.out.println("Population of a region:\n_______\n");
                    app.Population_Region();
                    inputNum = -1;
                    break;
                case 5:
                    System.out.println("Population of a district:\n_______\n");
                    app.Population_District();
                    inputNum = -1;
                    break;
                case 6:
                    System.out.println("Population of a city:\n_______\n");
                    app.Population_City();
                    inputNum = -1;
                    break;
                case -1:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between -1 and 4.");
                    inputNum = 0;
                    break;
            }

        }
        scanner.close();
    }

    //Returns int for "Top N questions"
    public int GetUserIntInput(int min, int max) {
        //Setup scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of results to display: ");

        int inputNum = 0;
        while (inputNum != -1) {
            try {
                //Ensure intput is int and within range
                inputNum = Integer.parseInt(scanner.nextLine());
                if (inputNum <= min || inputNum >= max) {
                    System.out.println("Invalid input. Please enter a valid integer within the range " + min + " - " + max + ".");
                    inputNum = 0;
                } else {
                    //Close scanner and return int if int is valid
                    scanner.close();
                    return inputNum;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer within the range " + min + " - " + max + ".");
            }
        }

        return -1;
    }
}
