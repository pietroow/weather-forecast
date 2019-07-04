package br.com.hbsis.weatherforecast.model.dto.response.custom;

import br.com.hbsis.weatherforecast.model.dto.response.openweatherapi.CityResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CustomResponse {

    @JsonProperty("list")
    private List<DayForecast> details;

    @JsonProperty("city")
    private CityResponseDTO city;

    public CustomResponse() {
    }

    public CustomResponse(List<DayForecast> finalList, CityResponseDTO city) {
        this();
        this.city = city;
        this.details = finalList;
    }

    public List<DayForecast> getDetails() {
        return details;
    }

    public void setDetails(List<DayForecast> details) {
        this.details = details;
    }

    public CityResponseDTO getCity() {
        return city;
    }

    public void setCity(CityResponseDTO city) {
        this.city = city;
    }
}
