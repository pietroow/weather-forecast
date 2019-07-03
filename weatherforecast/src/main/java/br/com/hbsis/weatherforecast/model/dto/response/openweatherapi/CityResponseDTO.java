package br.com.hbsis.weatherforecast.model.dto.response.openweatherapi;

import javax.persistence.Embeddable;

@Embeddable
public class CityResponseDTO {

    private Long id;
    private String name;
    private String country;
    private Long population;
    private String timezone;

    public CityResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
