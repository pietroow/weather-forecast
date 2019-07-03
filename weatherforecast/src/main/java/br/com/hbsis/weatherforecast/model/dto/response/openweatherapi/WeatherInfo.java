package br.com.hbsis.weatherforecast.model.dto.response.openweatherapi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embedded;
import java.time.LocalDateTime;
import java.util.List;

public class WeatherInfo {

    @Embedded
    @JsonProperty("main")
    private WeatherMainDTO main;

    @JsonProperty("weather")
    private List<WeatherDescriptionDTO> weather;

    @JsonProperty("dt_txt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    public WeatherInfo() {
    }

    public WeatherMainDTO getMain() {
        return main;
    }

    public void setMain(WeatherMainDTO main) {
        this.main = main;
    }

    public List<WeatherDescriptionDTO> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherDescriptionDTO> weather) {
        this.weather = weather;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
