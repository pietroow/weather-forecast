package br.com.hbsis.weatherforecast.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "city_api_openweather", schema = "weatherforecast")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityOpenWeather {

    @Id
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("country")
    private String country;

    private CityOpenWeather() {
    }

    public CityOpenWeather(String citySearch, String countrySearch) {
        this();
        this.name = citySearch;
        this.country = countrySearch;
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
}
