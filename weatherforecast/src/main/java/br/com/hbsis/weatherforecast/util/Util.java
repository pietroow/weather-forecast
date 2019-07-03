package br.com.hbsis.weatherforecast.util;

import br.com.hbsis.weatherforecast.model.CityOpenWeather;
import br.com.hbsis.weatherforecast.model.dto.response.custom.DayForecast;

import java.util.Comparator;
import java.util.List;

public class Util {

    private static final String COMMA = ",";
    private static final String BLANK = " ";
    private static final String EMPTY = "";

    public static CityOpenWeather formatSearchInputToClass(String value){
        String citySearch = BLANK;
        String countrySearch = BLANK;

        if (!value.contains(COMMA)) {
            citySearch = value;
        } else {
            String[] split = value.split(COMMA);
            citySearch = split[0].replace(BLANK, EMPTY);
            if (!isLastCharCommaOrBlank(value)) {
                countrySearch = split[1].isEmpty() ? EMPTY : split[1].replace(BLANK, EMPTY);
            }
        }
        return new CityOpenWeather(citySearch, countrySearch);
    }

    public static double getAverageHumidity(List<DayForecast> dayForecasts) {
        return dayForecasts.stream()
                .mapToDouble(DayForecast::getHumidity)
                .average()
                .getAsDouble();
    }

    public static double getAverageTemperature(Double minTemp, Double maxTemp) {
        return (minTemp + maxTemp) / 2;
    }

    public static Double getMaximumTemperature(List<DayForecast> dayForecasts) {
        return dayForecasts.stream()
                .map(DayForecast::getTemperatureMax)
                .max(Comparator.naturalOrder())
                .get();
    }

    public static Double getMinimumTemperature(List<DayForecast> dayForecasts) {
        return dayForecasts.stream()
                .map(DayForecast::getTemperatureMin)
                .min(Comparator.naturalOrder())
                .get();
    }

    private static boolean isLastCharCommaOrBlank(String value) {
        return value.endsWith(COMMA) || value.endsWith(BLANK);
    }

}
