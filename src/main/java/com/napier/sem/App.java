/**
 * Application Created by 40690819, 40664564, 40650822, 40592313
 * IN THE APP FILE WE HAVE MADE A CONNECTION TO THE DATABASE AND CHECKS THAT IT CONNECTS OR NOT
 * THE SCRIPT WILL RUN AN SQL STATEMENT AND GIVE RESULTS
 **/
package com.napier.sem;

import java.sql.*;
import java.util.Scanner;

public class App {
    private Connection con = null;

    public static void main(String[] args) {
        // Create a single App instance and connect it to the database
        App a = new App();
        a.connect();

        // Pass the connected App instance to UI
        UI ui = new UI(a);

        // Run the console menu
        ui.UI();

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */


    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    // Start of world.sql methods
    public City getCity(int ID) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT * FROM city "
                            + "WHERE id  = " + ID;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Check if a result is returned
            if (rset.next()) {
                City city = new City();
                city.city_id = rset.getInt("ID");
                city.name = rset.getString("Name");
                city.countryCode = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                return city;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public void displayCity(City city) {
        if (city != null) {
            System.out.println(
                    "City ID: " + city.city_id + "\n"
                            + "City Name: " + city.name + "\n"
                            + "City Country Code: " + city.countryCode + "\n"
                            + "City District: " + city.district + "\n"
                            + "City Population: " + city.population + "\n"
            );
        }
    }

    //Prints all cities from largest to smallest by population
    public void Cities_LargestToSmallest_World(boolean topNPopulated) {
        try {

            //SQL statement as a string
            String select =
                    "SELECT ci.name, co.name AS Country, ci.district, ci.population " +
                            "FROM city ci INNER JOIN country co ON ci.countryCode = co.code " +
                            "ORDER BY population DESC";

            //Create & execute SQL statement
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            //Print SQL results
            if (!topNPopulated) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("Name") + ", "
                            + resultSet.getString("Country") + ", "
                            + resultSet.getString("District") + ", "
                            + resultSet.getString("Population"));
                }
            } else {
                UI ui = new UI(this);
                //Get 'N'
                int resultCap = ui.GetUserIntInput(1, 10000);
                int iterator = 0;

                while (resultSet.next() && iterator < resultCap) {
                    System.out.println(resultSet.getString("Name") + ", "
                            + resultSet.getString("Country") + ", "
                            + resultSet.getString("District") + ", "
                            + resultSet.getString("Population"));
                    iterator++;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to print cities");
        }
    }

    //Prints cities in an inputted region from largest to smallest by population
    public void Cities_LargestToSmallest_Region(boolean topNPopulated) {
        try {
            System.out.println("Type region:");

            //Create scanner and process chosen region as a string input
            Scanner scanner = new Scanner(System.in);
            String regionInput = scanner.nextLine();
            System.out.println("_________");

            //SQL statement as a string
            String select =
                    "SELECT ci.name, co.name AS Country, ci.district, ci.population " +
                            "FROM city ci INNER JOIN country co ON ci.countryCode = co.code " +
                            "WHERE co.region LIKE " + '"' + regionInput + '"' +
                            "ORDER BY population DESC";

            //Create & execute SQL statement
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            //Print SQL results
            if (!topNPopulated) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("Name") + ", "
                            + resultSet.getString("Country") + ", "
                            + resultSet.getString("District") + ", "
                            + resultSet.getString("Population"));
                }
            } else {
                UI ui = new UI(this);
                //Get 'N'
                int resultCap = ui.GetUserIntInput(1, 10000);
                int iterator = 0;

                while (resultSet.next() && iterator < resultCap) {
                    System.out.println(resultSet.getString("Name") + ", "
                            + resultSet.getString("Country") + ", "
                            + resultSet.getString("District") + ", "
                            + resultSet.getString("Population"));
                    iterator++;
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to print cities");
        }

    }

    //Prints cities in an inputted continent from largest to smallest by population
    public void Cities_LargestToSmallest_Continent(boolean topNPopulated) {
        try {
            System.out.println("Type continent:");

            //Create scanner and process chosen continent as a string input
            Scanner scanner = new Scanner(System.in);
            String continentInput = scanner.nextLine();
            System.out.println("_________");

            //SQL statement as a string
            String select =
                    "SELECT ci.name, co.name AS Country, ci.district, ci.population " +
                            "FROM city ci INNER JOIN country co ON ci.countryCode = co.code " +
                            "WHERE co.continent LIKE " + '"' + continentInput + '"' +
                            "ORDER BY population DESC";

            //Create & execute SQL statement
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            //Print SQL results
            if (!topNPopulated) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("Name") + ", "
                            + resultSet.getString("Country") + ", "
                            + resultSet.getString("District") + ", "
                            + resultSet.getString("Population"));
                }
            } else {
                UI ui = new UI(this);
                //Get 'N'
                int resultCap = ui.GetUserIntInput(1, 10000);
                int iterator = 0;

                while (resultSet.next() && iterator < resultCap) {
                    System.out.println(resultSet.getString("Name") + ", "
                            + resultSet.getString("Country") + ", "
                            + resultSet.getString("District") + ", "
                            + resultSet.getString("Population"));
                    iterator++;
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to print cities");
        }

    }

    //Prints cities in an inputted country from largest to smallest by population
    public void Cities_LargestToSmallest_Country(boolean topNPopulated) {
        try {
            System.out.println("Type country:");

            //Create scanner and process chosen continent as a string input
            Scanner scanner = new Scanner(System.in);
            String countryInput = scanner.nextLine();
            System.out.println("_________");

            //SQL statement as a string
            String select =
                    "SELECT ci.name, co.name AS Country, ci.district, ci.population " +
                            "FROM city ci INNER JOIN country co ON ci.countryCode = co.code " +
                            "WHERE co.name LIKE " + '"' + countryInput + '"' +
                            "ORDER BY population DESC";

            //Create & execute SQL statement
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            //Print SQL results
            if (!topNPopulated) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("Name") + ", "
                            + resultSet.getString("Country") + ", "
                            + resultSet.getString("District") + ", "
                            + resultSet.getString("Population"));
                }
            } else {
                UI ui = new UI(this);
                //Get 'N'
                int resultCap = ui.GetUserIntInput(1, 110);
                int iterator = 0;

                while (resultSet.next() && iterator < resultCap) {
                    System.out.println(resultSet.getString("Name") + ", "
                            + resultSet.getString("Country") + ", "
                            + resultSet.getString("District") + ", "
                            + resultSet.getString("Population"));
                    iterator++;
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to print cities");
        }

    }
}
