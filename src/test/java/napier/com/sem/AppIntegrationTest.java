package napier.com.sem;

import com.napier.sem.App;
import com.napier.sem.City;
import com.napier.sem.Country;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;
    static City city;
    static Country country;

    @BeforeAll
    static void init()
    {
        app = new App();
        city = new City();
        country = new Country();
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
    void testGetCityFound2()
    {
        City city = app.getCity(2);
        assertEquals(city.city_id, 2);
        assertEquals(city.name, "Qandahar");
        assertEquals(city.countryCode, "AFG");
        assertEquals(city.district, "Qandahar");
        assertEquals(city.population, 237500);
    }

    @Test
    void getCityTestNotFound() {
        City city = app.getCity(0);

        // Assert
        assertNull(city, "City result should be null when not found");
    }

    @Test
    void getCountryTestNotFound() {
        Country country = app.getCountry(0);

        // Assert
        assertNull(country, "Country result should be null when not found");
    }

    @Test
    void testGetCountry(){

        Country country = app.getCountry(1975);

        assertEquals(country.Code, "AGO");
        assertEquals(country.name, "Angola");
        assertEquals(country.continent, "Africa");
        assertEquals(country.region, "Central Africa");
        assertEquals(country.surfaceArea, 1246700.00);
        assertEquals(country.indepYear, 1975);
        assertEquals(country.population, 12878000);
        assertEquals(country.gnp, 6648.00);
        assertEquals(country.gnpOld, 7984.00);
        assertEquals(country.localName, "Angola");
        assertEquals(country.governmentForm, "Republic");
        assertEquals(country.headOfState, "José Eduardo dos Santos");
        assertEquals(country.capital, 56);
        assertEquals(country.code2, "AO");
    }
}