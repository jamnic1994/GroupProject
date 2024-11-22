/**
 * Application Created by 40690819, 40664564, 40650822, 40592313
 * IN THE APP FILE WE HAVE MADE A CONNECTION TO THE DATABASE AND CHECKS THAT IT CONNECTS OR NOT
 * THE SCRIPT WILL RUN AN SQL STATEMENT AND GIVE RESULTS
 **/
package com.napier.sem;

import java.sql.*;
import java.util.Scanner;

public class App {

    /**
     * Main method to start the application
     */
    public static void main(String[] args) {
        // Create new Application and connect to database
        App a = new App();

        if (args.length < 1) {
            a.connect("localhost:33060", 10000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

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

    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        boolean shouldWait = false;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                if (shouldWait) {
                    // Wait a bit for db to start
                    Thread.sleep(delay);
                }

                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());

                // Let's wait before attempting to reconnect
                shouldWait = true;
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
    public void ReportPopulation() {
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

    /**
     * Prints population of the world.
     */
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

    /**
     * Prints population of a continent
     */
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

    /**
     * Prints population of a country
     */
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

    /**
     * Prints population of a region
     */
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

    /**
     * Prints population of a district
     */
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

    /**
     * Prints population of a city
     */
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

    /**
     * Method to return the details for a city object.
     */
    public City getCity(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT * FROM city "
                            + "WHERE id  = " + ID;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Check if a result is returned
            if (rset.next())
            {
                City city = new City();
                city.city_id = rset.getInt("ID");
                city.name = rset.getString("Name");
                city.countryCode = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                return city;
            }
            else{
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    /**
     * Method to return the details for a city object.
     */
    public Country getCountry(int indepYear)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT * FROM country "
                            + "WHERE IndepYear  = " + indepYear;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Check if a result is returned
            if (rset.next())
            {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.name = rset.getString("Name");
                country.continent = rset.getString("Continent");
                country.region = rset.getString("Region");
                country.surfaceArea = rset.getFloat("SurfaceArea");
                country.indepYear = rset.getInt("IndepYear");
                country.population = rset.getInt("Population");
                country.lifeExpectancy = rset.getFloat("LifeExpectancy");
                country.gnp = rset.getFloat("GNP");
                country.gnpOld = rset.getFloat("GNPOld");
                country.localName = rset.getString("LocalName");
                country.governmentForm = rset.getString("GovernmentForm");
                country.headOfState = rset.getString("HeadOfState");
                country.capital = rset.getInt("Capital");
                country.code2 = rset.getString("Code2");
                return country;
            }
            else{
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
            return null;
        }
    }

    /**
     * Method to display the details from a city.
     *
     * @return
     */
    public String displayCity(City city)
    {
        if (city != null) {
            return "City ID: " + city.city_id + "\n"
                    + "City Name: " + city.name + "\n"
                    + "City Country Code: " + city.countryCode + "\n"
                    + "City District: " + city.district + "\n"
                    + "City Population: " + city.population + "\n";
        }
        else if (city == null){

            return "No city found.";

        } return null;
    }
}
