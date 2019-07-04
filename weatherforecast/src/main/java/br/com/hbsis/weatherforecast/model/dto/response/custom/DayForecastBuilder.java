package br.com.hbsis.weatherforecast.model.dto.response.custom;

import java.time.LocalDate;

public final class DayForecastBuilder {

    private LocalDate date;
    private Double temperature;
    private Double temperatureMin;
    private Double temperatureMax;
    private Double humidity;

    private DayForecastBuilder() {
    }

    public static DayForecastBuilder aDayForecast() {
        return new DayForecastBuilder();
    }

    public DayForecastBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public DayForecastBuilder withTemperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    public DayForecastBuilder withTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
        return this;
    }

    public DayForecastBuilder withTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
        return this;
    }

    public DayForecastBuilder withHumidity(Double humidity) {
        this.humidity = humidity;
        return this;
    }

    public DayForecast build() {
        DayForecast dayForecast = new DayForecast();
        dayForecast.setDate(date);
        dayForecast.setTemperature(temperature);
        dayForecast.setTemperatureMin(temperatureMin);
        dayForecast.setTemperatureMax(temperatureMax);
        dayForecast.setHumidity(humidity);
        return dayForecast;
    }
}
