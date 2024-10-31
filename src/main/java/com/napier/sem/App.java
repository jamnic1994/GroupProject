/**
 * Application Created by 40690819, 40664564, 40650822, 40592313
 * IN THE APP FILE WE HAVE MADE A CONNECTION TO THE DATABASE AND CHECKS THAT IT CONNECTS OR NOT
 * THE SCRIPT WILL RUN AN SQL STATEMENT AND GIVE RESULTS
 **/
package com.napier.sem;

import java.sql.*;

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
                System.out.println("Failed to connect to database attempt " + i);
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
                System.out.println("Closing connection...");
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public City getCity(int ID) {
        try {
            Statement stmt = con.createStatement();
            String strSelect = "SELECT * FROM city WHERE id = " + ID;
            ResultSet rset = stmt.executeQuery(strSelect);

            if (rset.next()) {
                City city = new City();
                city.city_id = rset.getInt("ID");
                city.name = rset.getString("Name");
                city.countryCode = rset.getString("CountryCode");
                city.district = rset.getString("District");
                city.population = rset.getInt("Population");
                return city;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    public void displayCity(City city) {
        if (city != null) {
            System.out.println(
                    "City ID: " + city.city_id + "\n" +
                            "City Name: " + city.name + "\n" +
                            "City Country Code: " + city.countryCode + "\n" +
                            "City District: " + city.district + "\n" +
                            "City Population: " + city.population + "\n"
            );
        }
    }

    public Country getCountryByCode(String code) {
        try {
            Statement statement = con.createStatement();
            String select = "SELECT * FROM country WHERE Code = '" + code + "'";
            ResultSet resultSet = statement.executeQuery(select);

            if (resultSet.next()) {
                Country country = new Country();
                country.country_Code = resultSet.getString("Code");
                country.country_Name = resultSet.getString("Name");
                country.country_Continent = resultSet.getString("Continent");
                country.country_Region = resultSet.getString("Region");
                country.country_Population = resultSet.getInt("Population");
                country.country_Capital = resultSet.getString("Capital");
                return country;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to find country");
            return null;
        }
    }

    public void displayCountry(Country country) {
        if (country != null) {
            System.out.println(
                    "Country Code: " + country.country_Code + "\n" +
                            "Country Name: " + country.country_Name + "\n" +
                            "Country Continent: " + country.country_Continent + "\n" +
                            "Country Region: " + country.country_Region + "\n" +
                            "Country Population: " + country.country_Population + "\n" +
                            "Country Capital: " + country.country_Capital + "\n"
            );
        }
    }

    public void Countries_LargestToSmallest_World() {
        try {
            Statement statement = con.createStatement();
            String select = "SELECT name FROM country ORDER BY SurfaceArea";
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("Name"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to print countries");
        }
    }
}
