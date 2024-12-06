package napier.com.sem;

import com.napier.sem.App;
import com.napier.sem.City;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppTest {
    static App app;
    static City city;

    // Mocked database components
    static Connection mockConnection;
    static Statement mockStatement;
    static ResultSet mockResultSet;

    @BeforeAll
    static void init() throws Exception {
        // Create instances of relevant classes
        app = new App();
        city = new City();

        city.city_id = 1;
        city.countryCode = "TST";
        city.name = "Test City";
        city.district = "Test District";
        city.population = 10000;

        // Initialize mocks
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockResultSet = mock(ResultSet.class);

        // Set up the mocked connection in the App class
        app.setCon(mockConnection);

        // Mock behavior of connection and statement
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
    }

    @Test
    void displayCityTestNotNull() {
        String result = app.displayCity(city);
        // Assert that the result is not null
        assertNotNull(result);
    }

    @Test
    void displayCityTestContains() {
        String result = app.displayCity(city);
        // Assert that the result contains the expected city details
        assertTrue(result.contains("City ID: 1"));
        assertTrue(result.contains("City Name: Test City"));
        assertTrue(result.contains("City Country Code: TST"));
        assertTrue(result.contains("City District: Test District"));
        assertTrue(result.contains("City Population: 10000"));
    }

    @Test
    void displayCityTestNull() {
        String result = app.displayCity(null);
        // Assert that the result is "No city found."
        assertEquals("No city found.", result);
    }

    @Test
    void displayCityTestPartialData() {
        City partialCity = new City();
        partialCity.city_id = 2;
        partialCity.name = "Partial City";
        // Leave other fields null or default

        String result = app.displayCity(partialCity);

        // Assert partial data handling
        assertTrue(result.contains("City ID: 2"));
        assertTrue(result.contains("City Name: Partial City"));
        assertTrue(result.contains("City Country Code: null"));
        assertTrue(result.contains("City District: null"));
        assertTrue(result.contains("City Population: 0"));
    }

    @Test
    void getCityTestException() throws Exception {
        // Arrange: Mock Statement to throw an exception
        when(mockConnection.createStatement()).thenThrow(new RuntimeException("Database error"));

        // Act
        City result = app.getCity(3);

        // Assert
        assertNull(result);

        // Verify error handling
        verify(mockConnection).createStatement();
    }
}
