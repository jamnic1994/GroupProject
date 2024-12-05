/**
 * Application Created by 40690819, 40664564,40592313
 * IN THE APP FILE WE HAVE MADE A CONNECTION TO THE DATABASE AND CHECKS THAT IT CONNECTS OR NOT
 * THE SCRIPT WILL RUN AN SQL STATEMENT AND GIVE RESULTS
 **/
package com.napier.sem;

import java.util.Scanner;

public class UI {
    private App app;
    private Scanner scanner; // Define scanner at the class level

    /**
     * Constructor that accepts an App instance with an active connection
     */
    public UI(App app) {
        this.app = app;
        this.scanner = new Scanner(System.in); // Initialize scanner
    }

    /**
     * Main menu UI for the program
     */
    public void mainUI() {

        int inputNum = 0;

        String ciEnv = System.getenv("CI");
        boolean isCICD = ciEnv != null && ciEnv.equals("True");
        System.out.println("CI Environment Variable: " + ciEnv);  // Debugging output
        System.out.println("Is CI/CD: " + isCICD);  // Debugging output

        // Main loop for user interaction
        while (inputNum != -1) {
            // Print the menu in both interactive and CI/CD mode
            System.out.print("\n _____________________________________________________________\n");
            System.out.println("What type of report would you like to see: \n" +
                    "(1) Country Reports \n" +
                    "(2) City Reports \n" +
                    "(3) Capital City Reports \n" +
                    "(4) Population Reports \n" +
                    "(5) Total Population Reports \n" +
                    "(6) Language Reports \n" +
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
                // Completed report methods should be added here for testing.
                inputNum = -1;  // End the loop after automated tests
            }

            // Validate the input range if not in CI/CD mode
            if (inputNum < -1 || inputNum > 6) {
                System.out.println("Invalid input. Please enter a number between -1 and 6.");
                continue;  // Skip to the next iteration for invalid inputs
            }

            switch (inputNum) {
                case 1:
                    System.out.println("Country Reports:");
                    CountryReportMenu();
                    break;
                case 2:
                    System.out.println("City Reports:");
                    CityReportsMenu();
                    break;
                case 3:
                    System.out.println("Capital City Reports:");
                    CapitalCityReportsMenu();
                    break;
                case 4:
                    System.out.println("Population Reports:");
                    PopulationReportsMenu();
                    break;
                case 5:
                    System.out.println("Total Population Reports:");
                    TotalPopulationMenu();
                    break;
                case 6:
                    System.out.println("Language Reports:");
                    LanguageReportsMenu();
                    break;
                case -1:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Feature not yet implemented.");
            }
        }
    }

    /**
     * Terminal UI for country reports
     */
    public void CountryReportMenu(){
        Scanner scanner = new Scanner(System.in);
        int inputNum = 0;

        // Main loop for user interaction
        while (inputNum != -1) {
            // Print the menu in both interactive and CI/CD mode
            System.out.print("\n _____________________________________________________________\n");
            System.out.println("Country Reports Menu: \n" +
                    "(1) All countries in the world (largest to smallest population) \n" +
                    "(2) All countries in a continent (largest to smallest population) \n" +
                    "(3) All countries in a region (largest to smallest population) \n" +
                    "(4) Top N populated countries in the world \n" +
                    "(5) Top N populated countries in a continent \n" +
                    "(6) Top N populated countries in a region \n" +
                    "(-1) Back to Main Menu");
                    System.out.print("Your choice: ");

            try {
                inputNum = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                continue;
            }

            switch (inputNum) {
                case 1:
                    System.out.println("Fetching all countries in the world (largest to smallest population):");
                    // app.getAllCountriesByPopulation(); // Call the appropriate method
                    break;
                case 2:
                    System.out.println("Fetching all countries in a continent (largest to smallest population):");
                    System.out.print("Enter the continent name: ");
                    String continent = scanner.nextLine();
                     // app.getCountriesInContinentByPopulation(continent); // Call the appropriate method
                    break;
                case 3:
                    System.out.println("Fetching all countries in a region (largest to smallest population):");
                    System.out.print("Enter the region name: ");
                    String region = scanner.nextLine();
                    // app.getCountriesInRegionByPopulation(region); // Call the appropriate method
                    break;
                case 4:
                    System.out.println("Fetching top N populated countries in the world:");
                    int topNWorld = getTopNFromUser(scanner);
                    // app.getTopNCountriesByPopulationWorldwide(topNWorld); // Call the appropriate method
                    break;
                case 5:
                    System.out.println("Fetching top N populated countries in a continent:");
                    System.out.print("Enter the continent name: ");
                    String topNContinent = scanner.nextLine();
                    int topNForContinent = getTopNFromUser(scanner);
                    // app.getTopNCountriesByPopulationInContinent(topNContinent, topNForContinent); // Call the appropriate method
                    break;
                case 6:
                    System.out.println("Fetching top N populated countries in a region:");
                    System.out.print("Enter the region name: ");
                    String topNRegion = scanner.nextLine();
                    int topNForRegion = getTopNFromUser(scanner);
                    // app.getTopNCountriesByPopulationInRegion(topNRegion, topNForRegion); // Call the appropriate method
                    break;
                case -1:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid input. Please select an option between 1 and 6, or -1 to return to the Main Menu.");
            }
        }
    }

    /**
     * Terminal UI for city reports
     */
    public void CityReportsMenu(){
        Scanner scanner = new Scanner(System.in);
        int inputNum = 0;

        while (inputNum != -1) {
            System.out.print("\n _____________________________________________________________\n");
            System.out.println("City Reports Menu:: \n" +
                    "(1) All cities in the world (largest to smallest population) \n" +
                    "(2) All cities in a continent (largest to smallest population) \n" +
                    "(3) All cities in a region (largest to smallest population) \n" +
                    "(4) All cities in a country (largest to smallest population) \n" +
                    "(5) All cities in a district (largest to smallest population) \n" +
                    "(6) Top N populated cities in the world \n" +
                    "(7) Top N populated cities in a continent \n" +
                    "(8) Top N populated cities in a region \n" +
                    "(9) Top N populated cities in a country \n" +
                    "(10) Top N populated cities in a district \n" +
                    "(-1) Back to Main Menu");
            System.out.print("Your choice: ");

            try {
                inputNum = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                continue;
            }

            switch (inputNum) {
                case 1:
                    System.out.println("Fetching all cities in the world (largest to smallest population):");
                    // app.getAllCitiesByPopulationWorldwide(); // Call the appropriate method
                    break;
                case 2:
                    System.out.println("Fetching all cities in a continent (largest to smallest population):");
                    System.out.print("Enter the continent name: ");
                    String continent = scanner.nextLine();
                    // app.getCitiesInContinentByPopulation(continent); // Call the appropriate method
                    break;
                case 3:
                    System.out.println("Fetching all cities in a region (largest to smallest population):");
                    System.out.print("Enter the region name: ");
                    String region = scanner.nextLine();
                    // app.getCitiesInRegionByPopulation(region); // Call the appropriate method
                    break;
                case 4:
                    System.out.println("Fetching all cities in a country (largest to smallest population):");
                    System.out.print("Enter the country name: ");
                    String country = scanner.nextLine();
                    // app.getCitiesInCountryByPopulation(country); // Call the appropriate method
                    break;
                case 5:
                    System.out.println("Fetching all cities in a district (largest to smallest population):");
                    System.out.print("Enter the district name: ");
                    String district = scanner.nextLine();
                    // app.getCitiesInDistrictByPopulation(district); // Call the appropriate method
                    break;
                case 6:
                    System.out.println("Fetching top N populated cities in the world:");
                    int topNWorld = getTopNFromUser(scanner);
                    // app.getTopNCitiesByPopulationWorldwide(topNWorld); // Call the appropriate method
                    break;
                case 7:
                    System.out.println("Fetching top N populated cities in a continent:");
                    System.out.print("Enter the continent name: ");
                    String topNContinent = scanner.nextLine();
                    int topNForContinent = getTopNFromUser(scanner);
                    // app.getTopNCitiesByPopulationInContinent(topNContinent, topNForContinent); // Call the appropriate method
                    break;
                case 8:
                    System.out.println("Fetching top N populated cities in a region:");
                    System.out.print("Enter the region name: ");
                    String topNRegion = scanner.nextLine();
                    int topNForRegion = getTopNFromUser(scanner);
                    // app.getTopNCitiesByPopulationInRegion(topNRegion, topNForRegion); // Call the appropriate method
                    break;
                case 9:
                    System.out.println("Fetching top N populated cities in a country:");
                    System.out.print("Enter the country name: ");
                    String topNCountry = scanner.nextLine();
                    int topNForCountry = getTopNFromUser(scanner);
                    // app.getTopNCitiesByPopulationInCountry(topNCountry, topNForCountry); // Call the appropriate method
                    break;
                case 10:
                    System.out.println("Fetching top N populated cities in a district:");
                    System.out.print("Enter the district name: ");
                    String topNDistrict = scanner.nextLine();
                    int topNForDistrict = getTopNFromUser(scanner);
                    // app.getTopNCitiesByPopulationInDistrict(topNDistrict, topNForDistrict); // Call the appropriate method
                    break;
                case -1:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid input. Please select an option between 1 and 10, or -1 to return to the Main Menu.");
            }
        }
    }

    /**
     * Terminal UI for capital city reports
     */
    public void CapitalCityReportsMenu() {
        Scanner scanner = new Scanner(System.in);
        int inputNum = 0;

        while (inputNum != -1) {
            System.out.print("\n _____________________________________________________________\n");
            System.out.println("Capital City Reports Menu:: \n" +
                    "(1) All capital cities in the world (largest population to smallest) \n" +
                    "(2) All capital cities in a continent (largest population to smallest) \n" +
                    "(3) All capital cities in a region (largest population to smallest) \n" +
                    "(4) The top N populated capital cities in the world \n" +
                    "(5) The top N populated capital cities in a continent \n" +
                    "(6) The top N populated capital cities in a region \n" +
                    "(-1) Back to Main Menu");
            System.out.print("Your choice: ");

            try {
                inputNum = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                continue;
            }

            switch (inputNum) {
                case 1:
                    System.out.println("Fetching all capital cities in the world organized by largest population to smallest:");
                    app.getAllCapitalCitiesWorldwideByPopulation(); // Call the appropriate method
                    break;
                case 2:
                    System.out.println("Fetching all capital cities in a continent organized by largest population to smallest:");
                    System.out.print("Enter the continent name: ");
                    String continent = scanner.nextLine();
                    app.getCapitalCitiesInContinentByPopulation(continent); // Call the appropriate method
                    break;
                case 3:
                    System.out.println("Fetching all capital cities in a region organized by largest population to smallest:");
                    System.out.print("Enter the region name: ");
                    String region = scanner.nextLine();
                    app.getCapitalCitiesInRegionByPopulation(region); // Call the appropriate method
                    break;
                case 4:
                    System.out.println("Fetching the top N populated capital cities in the world:");
                    int topNWorld = getTopNFromUser(scanner);

                    app.getTopNCapitalCitiesWorldwide(topNWorld); // Call the appropriate method
                    break;
                case 5:
                    System.out.println("Fetching the top N populated capital cities in a continent:");
                    System.out.print("Enter the continent name: ");
                    String topNContinent = scanner.nextLine();
                    int topNForContinent = getTopNFromUser(scanner);
                    app.getTopNCapitalCitiesInContinent(topNContinent, topNForContinent); // Call the appropriate method
                    break;
                case 6:
                    System.out.println("Fetching the top N populated capital cities in a region:");
                    System.out.print("Enter the region name: ");
                    String topNRegion = scanner.nextLine();
                    int topNForRegion = getTopNFromUser(scanner);
                    app.getTopNCapitalCitiesInRegion(topNRegion, topNForRegion); // Call the appropriate method
                    break;
                case -1:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid input. Please select an option between 1 and 6, or -1 to return to the Main Menu.");
            }
        }
    }

    /**
     * Terminal UI for population reports
     */
    public void PopulationReportsMenu() {
        Scanner scanner = new Scanner(System.in);
        int inputNum = 0;

        while (inputNum != -1) {
            System.out.print("\n _____________________________________________________________\n");
            System.out.println("Population Reports Menu:: \n" +
                    "(1) Population of people living in/out of cities in a continent \n" +
                    "(2) Population of people living in/out of cities in a region \n" +
                    "(3) Population of people living in/out of cities in a country \n" +
                    "(-1) Back to Main Menu");
            System.out.print("Your choice: ");

            try {
                inputNum = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                continue;
            }

            switch (inputNum) {
                case 1:
                    System.out.println("\nFetching population of people living in/out of cities in a continent:");
                    System.out.print("\nEnter the continent name: ");
                    String continent = scanner.nextLine();
                    app.getPopulationInOutCitiesByContinent(continent); // Call the appropriate method
                    break;
                case 2:
                    System.out.println("\nFetching population of people living in/out of cities in a region:");
                    System.out.print("\nEnter the region name: ");
                    String region = scanner.nextLine();
                    app.getPopulationInOutCitiesByRegion(region); // Call the appropriate method
                    break;
                case 3:
                    System.out.println("\nFetching population of people living in/out of cities in a country:");
                    System.out.print("\nEnter the country name: ");
                    String country = scanner.nextLine();
                    app.getPopulationInOutCitiesByCountry(country); // Call the appropriate method
                    break;
                case -1:
                    System.out.println("\nReturning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid input. Please select an option between 1 and 3, or -1 to return to the Main Menu.");
            }
        }
    }

    /**
     * Terminal UI for total population reports
     */
    public void TotalPopulationMenu() {
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
                    System.out.println("\nPopulation of the world:\n_______\n");
                    app.Population_World();
                    break;
                case 2:
                    System.out.println("\nPopulation of a continent:\n_______\n");
                    app.Population_Continent();
                    break;
                case 3:
                    System.out.println("\nPopulation of a country:\n_______\n");
                    app.Population_Country();
                    break;
                case 4:
                    System.out.println("\nPopulation of a region:\n_______\n");
                    app.Population_Region();
                    break;
                case 5:
                    System.out.println("\nPopulation of a district:\n_______\n");
                    app.Population_District();
                    break;
                case 6:
                    System.out.println("\nPopulation of a city:\n_______\n");
                    app.Population_City();
                    break;
                case -1:
                    System.out.println("\nExiting...");
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between -1 and 4.");
                    inputNum = 0;
                    break;
            }

        }
    }

    /**
     * Displays the submenu for Language Reports and handles user selection.
     */
    public void LanguageReportsMenu() {
        Scanner scanner = new Scanner(System.in);
        int inputNum = 0;

        while (inputNum != -1) {
            System.out.print("\n _____________________________________________________________\n");
            System.out.println("Language Reports Menu:: \n" +
                    "(1) Number of Chinese speakers (including percentage of world population) \n" +
                    "(2) Number of English speakers (including percentage of world population) \n" +
                    "(3) Number of Hindi speakers (including percentage of world population) \n" +
                    "(4) Number of Spanish speakers (including percentage of world population) \n" +
                    "(5) Number of Arabic speakers (including percentage of world population) \n" +
                    "(-1) Back to Main Menu");
            System.out.print("Your choice: ");

            try {
                inputNum = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                continue;
            }

            switch (inputNum) {
                case 1:
                    System.out.println("\nFetching number of Chinese speakers:");
                    app.getLanguageSpeakers("Chinese"); // Call the method for Chinese speakers
                    break;
                case 2:
                    System.out.println("\nFetching number of English speakers:");
                    app.getLanguageSpeakers("English"); // Call the method for English speakers
                    break;
                case 3:
                    System.out.println("\nFetching number of Hindi speakers:");
                    app.getLanguageSpeakers("Hindi"); // Call the method for Hindi speakers
                    break;
                case 4:
                    System.out.println("\nFetching number of Spanish speakers:");
                    app.getLanguageSpeakers("Spanish"); // Call the method for Spanish speakers
                    break;
                case 5:
                    System.out.println("\nFetching number of Arabic speakers:");
                    app.getLanguageSpeakers("Arabic"); // Call the method for Arabic speakers
                    break;
                case -1:
                    System.out.println("\nReturning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid input. Please select an option between 1 and 5, or -1 to return to the Main Menu.");
            }
        }
    }

    /**
     * Prompts the user to input a valid number for "Top N" queries.
     */
    private int getTopNFromUser(Scanner scanner) {
        int topN = 0;
        while (topN <= 0) {
            System.out.print("Enter the number of top results to fetch (N): ");
            try {
                topN = Integer.parseInt(scanner.nextLine());
                if (topN <= 0) {
                    System.out.println("Please enter a positive number greater than zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid positive integer.");
            }
        }
        return topN;
    }

    /**
     * Returns int for "Top N questions"
     */
    public int GetUserIntInput(int min, int max) {
        // Setup scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of results to display: ");

        int inputNum = 0;
        while (inputNum != -1) {
            try {
                // Ensure input is int and within range
                inputNum = Integer.parseInt(scanner.nextLine());
                if (inputNum <= min || inputNum >= max) {
                    System.out.println("Invalid input. Please enter a valid integer within the range " + min + " - " + max + ".");
                    inputNum = 0;
                } else {
                    // Close scanner and return int if int is valid
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
