package br.com.hbsis.weatherforecast.model.dto.custom;

import br.com.hbsis.weatherforecast.model.dto.response.WeatherMainDTO;
import br.com.hbsis.weatherforecast.model.dto.response.WeatherResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class DayForecast {

    @JsonProperty("date")
    private LocalDate date;

    @JsonProperty("temp")
    private Double temperature;

    @JsonProperty("temp_min")
    private Double temperatureMin;

    @JsonProperty("temp_max")
    private Double temperatureMax;

    @JsonProperty("humidity")
    private Double humidity;

    public DayForecast() {
    }

    public DayForecast(LocalDate date, Double temperature, Double temperatureMin, Double temperatureMax, Double humidity) {
        this.date = date;
        this.temperature = temperature;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.humidity = humidity;
    }

    public CustomResponse convert(WeatherResponseDTO infos) {
        HashMap<Integer, List<DayForecast>> byDate = new HashMap<>();

        infos.getDetails().forEach(weatherInfo -> {
            LocalDate date = weatherInfo.getDateTime().toLocalDate();
            int dayOfMonth = date.getDayOfMonth();
            WeatherMainDTO main = weatherInfo.getMain();

            if (byDate.containsKey(dayOfMonth)) {
                DayForecast dayForecast = new DayForecast(date, main.getTemperature(), main.getTemperatureMin(), main.getTemperatureMax(), main.getHumidity());
                List<DayForecast> dayForecasts = byDate.get(dayOfMonth);
                dayForecasts.add(dayForecast);
            } else {
                List<DayForecast> dayForecasts = new ArrayList<>();
                DayForecast dayForecast = new DayForecast(date, main.getTemperature(), main.getTemperatureMin(), main.getTemperatureMax(), main.getHumidity());
                dayForecasts.add(dayForecast);
                byDate.put(dayOfMonth, dayForecasts);
            }
        });

        List<DayForecast> finalList = new ArrayList<>();

        byDate.forEach((integer, dayForecasts) -> {
            Double minTemp = getMinimumTemperature(dayForecasts);
            Double maxTemp = getMaximumTemperature(dayForecasts);
            Double avgTemp = getAverageTemperature(minTemp, maxTemp);
            Double avgHumidity = getAverageHumidity(dayForecasts);
            finalList.add(new DayForecast(dayForecasts.get(0).getDate(), avgTemp, minTemp, maxTemp, avgHumidity));
        });

        return new CustomResponse(finalList, infos.getCity());
    }

    private double getAverageHumidity(List<DayForecast> dayForecasts) {
        return dayForecasts.stream()
                .mapToDouble(DayForecast::getHumidity)
                .average()
                .getAsDouble();
    }

    private double getAverageTemperature(Double minTemp, Double maxTemp) {
        return (minTemp + maxTemp) / 2;
    }

    private Double getMaximumTemperature(List<DayForecast> dayForecasts) {
        return dayForecasts.stream()
                .map(DayForecast::getTemperatureMax)
                .max(Comparator.naturalOrder())
                .get();
    }

    private Double getMinimumTemperature(List<DayForecast> dayForecasts) {
        return dayForecasts.stream()
                .map(DayForecast::getTemperatureMin)
                .min(Comparator.naturalOrder())
                .get();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getTemperature() {
        return BigDecimal.valueOf(temperature)
                .setScale(0, RoundingMode.UP)
                .doubleValue();
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTemperatureMin() {
        return BigDecimal.valueOf(temperatureMin)
                .setScale(0, RoundingMode.UP)
                .doubleValue();
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Double getTemperatureMax() {
        return BigDecimal.valueOf(temperatureMax)
                .setScale(0, RoundingMode.UP)
                .doubleValue();
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Double getHumidity() {
        return BigDecimal.valueOf(humidity)
                .setScale(0, RoundingMode.UP)
                .doubleValue();
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
