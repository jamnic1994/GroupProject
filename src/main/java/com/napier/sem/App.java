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

    public void LangSpeakersGreatestToSmallest() {
        System.out.println("No. of Speakers, World Percentage, Language");
        try {
            String select =
                    "SELECT ROUND(SUM(cl.percentage / 100 * c.population)) AS 'NumOfSpeakers', " +
                            "(SUM(cl.percentage / 100 * c.population) / (SELECT SUM(c.Population) FROM country c)) * 100 " +
                            "AS Percentage, cl.Language AS 'Lang' " +
                            "FROM countrylanguage cl JOIN country c ON cl.countrycode = c.code " +
                            "WHERE cl.language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                            "GROUP BY cl.Language " +
                            "ORDER BY ROUND(SUM(cl.percentage / 100 * c.population)) DESC";

            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()) {
                System.out.println(resultSet.getString("NumOfSpeakers") + ", " +
                        resultSet.getString("Percentage") + ", " +
                        resultSet.getString("Lang"));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to print percentages");
        }

    }

}
