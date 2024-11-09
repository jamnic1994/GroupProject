/**
 * Application Created by 40690819, 40664564, 40650822, 40592313
 * IN THE APP FILE WE HAVE MADE A CONNECTION TO THE DATABASE AND CHECKS THAT IT CONNECTS OR NOT
 * THE SCRIPT WILL RUN AN SQL STATEMENT AND GIVE RESULTS
 **/
package com.napier.sem;

import java.sql.*;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // Create a single App instance and connect it to the database
        App a = new App();
        a.connect();

        // Pass the connected App instance to UI
        com.napier.sem.UI ui = new com.napier.sem.UI(a);

        // Run the console menu
        ui.UI();

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Getter for the database connection.
     *
     * @return Connection object, or null if not connected.
     */
    public Connection getCon() {
        return con;
    }

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

    /**
     * Method to generate population report.
     */
    public void reportpopulation() {
        try {
            Statement stmt = con.createStatement();

            //sql to get the total of urban population by country
            String strQuery = "Select c.Name AS Country, " +
                    "SUM(cty.Population) AS UrbanPopulation, " +
                    "c.Population AS TotalPopulation, " +
                    "c.Population - SUM(cty.Population) AS RuralPopulation " +
                    "FROM country c " +
                    "JOIN city cty on cty.CountryCode = c.Code " +
                    "GROUP BY c.Name, c.Population " +
                    "ORDER BY c.Name;";

            ResultSet rset = stmt.executeQuery(strQuery);

            //display report
            System.out.println("Country\t\tTotal Population\tUrban Population\tRural Population");

            // results
            while (rset.next()) {
                String country = rset.getString("Country");
                int totalPopulation = rset.getInt("TotalPopulation");
                int urbanPopulation = rset.getInt("UrbanPopulation");
                int ruralPopulation = rset.getInt("RuralPopulation");

                //display results for each country
                System.out.printf("%-15s\t%-15d\t%-15d\t%-15d%n", country, totalPopulation, urbanPopulation, ruralPopulation);
            }

        } catch (SQLException e) {
            System.out.println("Error generating population report: " + e.getMessage());
        }


    }

    /**
     * Method to generate population report by region.
     */
    public void RegionPopulationReport() {
        try {
            Statement stmt = con.createStatement();

            //SQL query to get population details by region
            String strQuery = "SELECT c.Region AS Region, " +
                    "SUM(cty.Population) AS UrbanPopulation, " +
                    "SUM(c.Population) AS TotalPopulation, " +
                    "SUM(c.Population) - SUM(cty.Population) AS RuralPopulation " +
                    "FROM country c " +
                    "JOIN  city  cty ON cty.CountryCode = c.Code " +
                    "GROUP BY c.Region " +
                    "ORDER BY c.Region;";

            ResultSet rset = stmt.executeQuery(strQuery);

            // display report
            System.out.println("Region\t\tTotal Population\tUrban Population\tRural Population");

            //results
            while (rset.next()) {
                String region = rset.getString("Region");
                long totalPopulation = rset.getLong("TotalPopulation");
                long urbanPopulation = rset.getLong("UrbanPopulation");
                long ruralPopulation = rset.getLong("RuralPopulation");

                // display the results for each region
                System.out.printf("%-15s\t%-15d\t%-15d\t%-15d%n", region, totalPopulation, urbanPopulation, ruralPopulation);
            }
        } catch (SQLException e) {
            System.out.println("Error generating regional population report: " + e.getMessage());
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    //Prints population of the world
    public void Population_World() {
        try {

            //SQL statement as a string
            String select =
                    "SELECT SUM(c.Population) AS TotalPopulation " +
                            "FROM country c";

            //Create & execute SQL statement
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            //Print SQL results
            while (resultSet.next()) {
                System.out.println(resultSet.getLong("TotalPopulation"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to print world population");
        }
    }

    //Prints population of a continent
    public void Population_Continent() {
        try {
            System.out.println("Type continent:");

            //Create scanner and process chosen continent as a string input
            Scanner scanner = new Scanner(System.in);
            String continentInput = scanner.nextLine();

            //SQL statement as a string
            String select =
                    "SELECT SUM(c.Population) AS ContinentPopulation " +
                            "FROM country c " +
                            "WHERE c.Continent = " + '"' + continentInput + '"';

            //Create & execute SQL statement
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            //Print SQL results
            while (resultSet.next()) {
                System.out.println(resultSet.getLong("ContinentPopulation"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to print continent population");
        }
    }

    //Prints population of a country
    public void Population_Country() {
        try {
            System.out.println("Type country:");

            //Create scanner and process chosen continent as a string input
            Scanner scanner = new Scanner(System.in);
            String countryInput = scanner.nextLine();

            //SQL statement as a string
            String select =
                    "SELECT Population AS CountryPopulation " +
                            "FROM country c " +
                            "WHERE c.name = " + '"' + countryInput + '"';

            //Create & execute SQL statement
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            //Print SQL results
            while (resultSet.next()) {
                System.out.println(resultSet.getLong("CountryPopulation"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to print country population");
        }
    }

    //Prints population of a region
    public void Population_Region() {
        try {
            System.out.println("Type region:");

            //Create scanner and process chosen continent as a string input
            Scanner scanner = new Scanner(System.in);
            String regionInput = scanner.nextLine();

            //SQL statement as a string
            String select =
                    "SELECT SUM(Population) AS RegionPopulation " +
                            "FROM country c " +
                            "WHERE c.region = " + '"' + regionInput + '"';

            //Create & execute SQL statement
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            //Print SQL results
            while (resultSet.next()) {
                System.out.println(resultSet.getLong("RegionPopulation"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to print region population");
        }
    }

    //Prints population of a district
    public void Population_District() {
        try {
            System.out.println("Type district:");

            //Create scanner and process chosen continent as a string input
            Scanner scanner = new Scanner(System.in);
            String districtInput = scanner.nextLine();

            //SQL statement as a string
            String select =
                    "SELECT SUM(Population) AS DistrictPopulation " +
                            "FROM city c " +
                            "WHERE c.district = " + '"' + districtInput + '"';

            //Create & execute SQL statement
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            //Print SQL results
            while (resultSet.next()) {
                System.out.println(resultSet.getLong("DistrictPopulation"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to print district population");
        }
    }

    //Prints population of a city
    public void Population_City() {
        try {
            System.out.println("Type city:");

            //Create scanner and process chosen continent as a string input
            Scanner scanner = new Scanner(System.in);
            String cityInput = scanner.nextLine();

            //SQL statement as a string
            String select =
                    "SELECT SUM(Population) AS CityPopulation " +
                            "FROM city c " +
                            "WHERE c.name = " + '"' + cityInput + '"';

            //Create & execute SQL statement
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            //Print SQL results
            while (resultSet.next()) {
                System.out.println(resultSet.getLong("CityPopulation"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to print city population");
        }
    }

}
