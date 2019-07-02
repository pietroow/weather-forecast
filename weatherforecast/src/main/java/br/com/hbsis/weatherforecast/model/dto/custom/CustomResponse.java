package br.com.hbsis.weatherforecast.model.dto.custom;

import br.com.hbsis.weatherforecast.model.dto.response.CityResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CustomResponse {

    @JsonProperty("list")
    private List<DayForecast> details;

    @JsonProperty("city")
    private CityResponseDTO city;

}
