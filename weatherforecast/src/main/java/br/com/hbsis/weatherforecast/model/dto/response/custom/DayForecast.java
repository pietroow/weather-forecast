package br.com.hbsis.weatherforecast.model.dto.response.custom;

import br.com.hbsis.weatherforecast.model.dto.response.openweatherapi.WeatherMainDTO;
import br.com.hbsis.weatherforecast.model.dto.response.openweatherapi.WeatherResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static br.com.hbsis.weatherforecast.util.Util.*;

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

    public static CustomResponse convert(WeatherResponseDTO infos) {
        HashMap<Integer, List<DayForecast>> byDate = new HashMap<>();

        infos.getDetails().forEach(weatherInfo -> {
            LocalDate date = weatherInfo.getDateTime().toLocalDate();
            int dayOfMonth = date.getDayOfMonth();
            WeatherMainDTO main = weatherInfo.getMain();

            if (byDate.containsKey(dayOfMonth)) {
                DayForecast dayForecast = copyProperties(main, date);
                List<DayForecast> dayForecasts = byDate.get(dayOfMonth);
                dayForecasts.add(dayForecast);
            } else {
                List<DayForecast> dayForecasts = new ArrayList<>();
                DayForecast dayForecast = copyProperties(main, date);
                dayForecasts.add(dayForecast);
                byDate.put(dayOfMonth, dayForecasts);
            }
        });

        List<DayForecast> resultList = new ArrayList<>();

        byDate.forEach((integer, dayForecasts) -> {
            Double minTemp = getMinimumTemperature(dayForecasts);
            Double maxTemp = getMaximumTemperature(dayForecasts);
            Double avgTemp = getAverageTemperature(minTemp, maxTemp);
            Double avgHumidity = getAverageHumidity(dayForecasts);
            resultList.add(
                    DayForecastBuilder.aDayForecast()
                            .withDate(dayForecasts.stream().findFirst().get().getDate())
                            .withTemperature(avgTemp)
                            .withTemperatureMin(minTemp)
                            .withTemperatureMax(maxTemp)
                            .withHumidity(avgHumidity)
                            .build());
        });
        return new CustomResponse(resultList, infos.getCity());
    }

    private static DayForecast copyProperties(WeatherMainDTO weatherMainDTO, LocalDate date) {
        DayForecast dayForecast = new DayForecast();
        BeanUtils.copyProperties(weatherMainDTO, dayForecast);
        dayForecast.setDate(date);
        return dayForecast;
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
