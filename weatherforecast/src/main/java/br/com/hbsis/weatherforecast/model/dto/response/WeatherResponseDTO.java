package br.com.hbsis.weatherforecast.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embedded;
import java.util.List;

public class WeatherResponseDTO {

    @JsonProperty("cod")
    private Integer cod;

    @JsonProperty("list")
    private List<WeatherInfo> details;

    @Embedded
    @JsonProperty("city")
    private CityResponseDTO city;

    public WeatherResponseDTO() {
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public List<WeatherInfo> getDetails() {
        return details;
    }

    public void setDetails(List<WeatherInfo> details) {
        this.details = details;
    }

    public CityResponseDTO getCity() {
        return city;
    }

    public void setCity(CityResponseDTO city) {
        this.city = city;
    }
}
