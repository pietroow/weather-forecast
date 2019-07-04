package br.com.hbsis.weatherforecast.model.dto.response.openweatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;

@Embeddable
public class WeatherMainDTO {

    @JsonProperty("temp")
    private Double temperature;

    @JsonProperty("temp_min")
    private Double temperatureMin;

    @JsonProperty("temp_max")
    private Double temperatureMax;

    @JsonProperty("humidity")
    private Double humidity;

    public WeatherMainDTO() {
    }

    public WeatherMainDTO(Double temperature, Double temperatureMin, Double temperatureMax, Double humidity) {
        this.temperature = temperature;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.humidity = humidity;
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
