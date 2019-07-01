package br.com.hbsis.weatherforecast.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

public class WeatherInfo {

    @Embedded
    @JsonProperty("main")
    private WeatherMainDTO main;

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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
