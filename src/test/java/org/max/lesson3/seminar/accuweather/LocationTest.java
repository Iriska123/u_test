package org.max.lesson3.seminar.accuweather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.lesson3.seminar.accuweather.location.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LocationTest extends AbstractAccuweatherTest {

    @Test
    void getLocationAsStringTest() {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", getApiKey());
        params.put("q", "Moscow");
        String json = given().queryParams(params)
                .when().get(getBaseUrl() + "/locations/v1/cities/autocomplete")
                .then().statusCode(200)
                .extract().asString();
        Assertions.assertAll(
                () -> Assertions.assertTrue(json.contains("Moscow")),
                () -> Assertions.assertTrue(json.contains("Russia"))
        );
    }

    @Test
    void getLocationAsLocation() {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", getApiKey());
        params.put("q", "Moscow");
        List<Location> locations = given().queryParams(params)
                .when().get(getBaseUrl() + "/locations/v1/cities/autocomplete")
                .then().statusCode(200)
                .extract().body().jsonPath().getList(".", Location.class);
        Assertions.assertAll(
                () -> Assertions.assertEquals("Moscow", locations.get(0).getLocalizedName()),
                () -> Assertions.assertEquals("Russia", locations.get(0).getCountry().getLocalizedName()),
                () -> Assertions.assertEquals("RU", locations.get(0).getCountry().getId()),
                () -> Assertions.assertEquals("MOW", locations.get(0).getAdministrativeArea().getId())
        );
    }

}
