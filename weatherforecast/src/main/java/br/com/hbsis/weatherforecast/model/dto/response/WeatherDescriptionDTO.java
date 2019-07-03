package br.com.hbsis.weatherforecast.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDescriptionDTO {

    @JsonProperty("main")
    private String main;

    @JsonProperty("description")
    private String description;

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
