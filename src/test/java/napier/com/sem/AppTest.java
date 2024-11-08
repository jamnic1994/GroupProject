package napier.com.sem;

import napier.napier.sem.App;
import napier.napier.sem.City;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;
    static City city;

    @BeforeAll
    static void init()
    {
        // Create instances of relevant classes
        app = new App();
        city = new City();

        city.city_id = 1;
        city.countryCode = "TST";
        city.name = "Test City";
        city.district = "Test District";
        city.population = 10000;
    }

    @Test
    void conTestNull()
    {
        assertNull(app.getCon());
    }

    @Test
    void displayCityTest() {

        String result = app.displayCity(city);

        // Assert that the result is not null
        assertNotNull(result);

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

        // Assert that the result is not null
        assertEquals("No city found.", result);
    }
}