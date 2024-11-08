/**
 * Application Created by 40690819, 40664564, 40650822, 40592313
 * IN THE APP FILE WE HAVE MADE A CONNECTION TO THE DATABASE AND CHECKS THAT IT CONNECTS OR NOT
 * THE SCRIPT WILL RUN AN SQL STATEMENT AND GIVE RESULTS
 **/
package napier.napier.sem;

import java.sql.*;

public class App {

    public static void main(String[] args) {
        // Create class objects
        App a = new App();
        UI ui = new UI(a);

        // Connect to database
        a.connect();

        // Run the console menu
        ui.startUI();

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

    /**
     * Method to generate population report.
     */
    public void reportpopulation() {
        try{
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
            while (rset.next()){
                String country = rset.getString("Country");
                int totalPopulation = rset.getInt("TotalPopulation");
                int urbanPopulation = rset.getInt("UrbanPopulation");
                int ruralPopulation = rset.getInt("RuralPopulation");

                //display results for each country
                System.out.printf("%-15s\t%-15d\t%-15d\t%-15d%n", country, totalPopulation,urbanPopulation,ruralPopulation);
            }

        }catch (SQLException e){
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
                long urbanPopulation =  rset.getLong("UrbanPopulation");
                long ruralPopulation = rset.getLong("RuralPopulation");

                // display the results for each region
                System.out.printf("%-15s\t%-15d\t%-15d\t%-15d%n", region, totalPopulation, urbanPopulation, ruralPopulation);
            }
        } catch (SQLException e){
            System.out.println("Error generating regional population report: " + e.getMessage());
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
                /*
                int resultCap = ui.GetUserIntInput(1, 10000);
                int iterator = 0;

                while (resultSet.next() && iterator < resultCap) {
                    System.out.println(resultSet.getString("Name") + ", "
                            + resultSet.getString("Country") + ", "
                            + resultSet.getString("District") + ", "
                            + resultSet.getString("Population"));
                    iterator++;


                }
                */

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to print cities");
        }
    }

}
