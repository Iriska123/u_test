package org.max.lesson3.seminar.accuweather;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.lesson3.seminar.accuweather.weather.DailyForecast;
import org.max.lesson3.seminar.accuweather.weather.Weather;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Forecast5DaysTest extends AccuweatherAbstractTest {

    @Test
    void testGetResponseAsWeather() {
        Weather response = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/5day/{locationKey}")
                .then().statusCode(200).time(lessThan(2000L))
                .extract().response().body().as(Weather.class);
        Assertions.assertEquals(7, response.getHeadline().getSeverity());
        Assertions.assertEquals(5, response.getDailyForecasts().size());
        Assertions.assertEquals("F", response.getDailyForecasts().get(0).getTemperature().getMinimum().getUnit());
        Assertions.assertEquals("F", response.getDailyForecasts().get(0).getTemperature().getMaximum().getUnit());
    }

    @Test
    void testGetResponseAsForecastList() {
        List<DailyForecast> response = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/5day/{locationKey}")
                .then().statusCode(200).time(lessThan(5000L))
                .extract().body().jsonPath().getList("DailyForecasts", DailyForecast.class);
        Assertions.assertEquals(5, response.size());
        Assertions.assertEquals("F", response.get(0).getTemperature().getMinimum().getUnit());
        Assertions.assertEquals("F", response.get(0).getTemperature().getMaximum().getUnit());
    }

    @Test
    void testGetResponseAsString() {
        String response = given().queryParam("apikey", getApiKey()).pathParam("locationKey", 50)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/5day/{locationKey}")
                .then().statusCode(200).time(lessThan(5000L))
                .extract().asString();
        System.out.println(response);
        Assertions.assertTrue(response.contains("Headline"));
        Assertions.assertTrue(response.contains("DailyForecasts"));
    }

}
