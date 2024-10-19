/**
 * Application Created by 40690819, 40664564, 40650822, 40592313
 * IN THE APP FILE WE HAVE MADE A CONNECTION TO THE DATABASE AND CHECKS THAT IT CONNECTS OR NOT
 * THE SCRIPT WILL RUN AN SQL STATEMENT AND GIVE RESULTS
**/
package com.napier.sem;

import java.sql.*;

public class App
{
    public static void main(String[] args)
    {
        // Create class objects
        App a = new App();
        UI ui = new UI();

        // Connect to database
        a.connect();

        // Run the console menu
        UI.UI();

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    // Start of world.sql methods
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
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    public void displayCity(City city)
    {
        if (city != null)
        {
            System.out.println(
                "City ID: " + city.city_id + "\n"
                + "City Name: " +  city.name + "\n"
                + "City Country Code: " +  city.countryCode + "\n"
                + "City District: " +  city.district + "\n"
                + "City Population: " +  city.population + "\n"
            );
        }
    }

    public Country getCountryByCode(String code)
    {
        try
        {
            // Create an SQL statement
            Statement statement = con.createStatement();

            // Create string for SQL statement
            String select = "SELECT * FROM country WHERE Code = " + code;

            // Execute SQL statement
            ResultSet resultSet = statement.executeQuery(select);

            // Check if a result is returned
            if (resultSet.next())
            {
                Country country = new Country();
                country.country_Code = resultSet.getString("Code");
                country.country_Name = resultSet.getString("Name");
                country.country_Continent = resultSet.getString("Continent");
                country.country_Region = resultSet.getString("Region");
                country.country_Population = resultSet.getInt("Population");
                country.country_Capital = resultSet.getString("Capital");
                return country;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to find country");
            return null;
        }
    }

    public void displayCountry(Country country)
    {
        if (country != null)
        {
            System.out.println(
                             "Country Code: " +  country.country_Code + "\n"
                            + "Country Name: " +  country.country_Name + "\n"
                            + "Country Continent: " +  country.country_Continent + "\n"
                            + "Country Region: " +  country.country_Region + "\n"
                            + "Country Population: " +  country.country_Population + "\n"
                            + "Country Capital: " +  country.country_Capital + "\n"
            );
        }
    }
}
