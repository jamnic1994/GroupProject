package napier.com.sem;

import com.napier.sem.App;
import com.napier.sem.City;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;
    static City city;

    @BeforeAll
    static void init()
    {
        app = new App();
        city = new City();
        app.connect("localhost:33060", 30000);
    }

    @Test
    void testGetCityFound()
    {
        City city = app.getCity(1);
        assertEquals(city.city_id, 1);
        assertEquals(city.name, "Kabul");
        assertEquals(city.countryCode, "AFG");
        assertEquals(city.district, "Kabol");
        assertEquals(city.population, 1780000);
    }

    @Test
    void getCityTestNotFound() {
        City city = app.getCity(0);

        // Assert
        assertNull(city, "City result should be null when not found");
    }
}