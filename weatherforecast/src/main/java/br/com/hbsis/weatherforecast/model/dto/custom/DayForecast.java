package br.com.hbsis.weatherforecast.model.dto.custom;

import br.com.hbsis.weatherforecast.model.dto.response.WeatherInfo;
import br.com.hbsis.weatherforecast.model.dto.response.WeatherMainDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.*;

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

    public List<DayForecast> convert(List<WeatherInfo> infos) {
        HashMap<Integer, List<DayForecast>> byDate = new HashMap<>();

        infos.forEach(weatherInfo -> {
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
            Double avgTemp = getAverageTemperature(dayForecasts);
            Double avgHumidity = getAverageHumidity(dayForecasts);
            finalList.add(new DayForecast(dayForecasts.get(0).getDate(), avgTemp, minTemp, maxTemp, avgHumidity));
        });

        return finalList;
    }

    private double getAverageHumidity(List<DayForecast> dayForecasts) {
        return dayForecasts.stream()
                .mapToDouble(DayForecast::getHumidity)
                .average()
                .getAsDouble();
    }

    private double getAverageTemperature(List<DayForecast> dayForecasts) {
        return dayForecasts.stream()
                .mapToDouble(DayForecast::getTemperatureMin)
                .average()
                .getAsDouble();
    }

    private Double getMaximumTemperature(List<DayForecast> dayForecasts) {
        return dayForecasts.stream()
                .map(DayForecast::getTemperatureMin)
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
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}
