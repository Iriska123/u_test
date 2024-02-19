package org.max.lesson3.seminar.accuweather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.max.lesson3.seminar.accuweather.weather.DailyForecast;
import org.max.lesson3.seminar.accuweather.weather.Weather;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Forecast5DaysTest extends AbstractAccuweatherTest {

    @ParameterizedTest
    @ValueSource(ints = {50, 75})
    void getResponseAsWeather(int location) {
        Weather weather = given().queryParam("apikey", getApiKey()).pathParams("location", location)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/5day/{location}")
                .then().statusCode(200).time(lessThan(1500L))
                .extract().response().body().as(Weather.class);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(weather.getHeadline()),
                () -> Assertions.assertEquals(5, weather.getDailyForecasts().size()),
                () -> Assertions.assertEquals(18, weather.getDailyForecasts().get(0).getTemperature().getMaximum().getUnitType())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {50, 75})
    void getResponseAsDailyForecasts(int location) {
        List<DailyForecast> dailyForecasts = given().queryParam("apikey", getApiKey()).pathParams("location", location)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/5day/{location}")
                .then().statusCode(200)
                .extract().body().jsonPath().getList("DailyForecasts", DailyForecast.class);

        Assertions.assertAll(
                () -> Assertions.assertEquals(5, dailyForecasts.size()),
                () -> Assertions.assertEquals(18, dailyForecasts.get(0).getTemperature().getMaximum().getUnitType())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {50, 75})
    void getResponseAsString(int location) {
        String json = given().queryParam("apikey", getApiKey()).pathParams("location", location)
                .when().get(getBaseUrl() + "/forecasts/v1/daily/5day/{location}")
                .then().statusCode(200)
                .extract().asString();

        Assertions.assertAll(
                () -> Assertions.assertTrue(json.contains("Headline")),
                () -> Assertions.assertTrue(json.contains("DailyForecasts"))
        );
    }

}
