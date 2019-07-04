package br.com.hbsis.weatherforecast.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "city", schema = "weatherforecast")
public class City {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "city_open_weather_id")
    @JsonProperty("city_open_weather")
    private CityOpenWeather cityOpenWeather;

    private City() {
        this.id = UUID.randomUUID();
    }

    public City(CityOpenWeather cityOpenWeather) {
        this();
        this.cityOpenWeather = cityOpenWeather;
    }

    public City(UUID id, CityOpenWeather cityOpenWeather) {
        this.id = id;
        this.cityOpenWeather = cityOpenWeather;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CityOpenWeather getCityOpenWeather() {
        return cityOpenWeather;
    }

    public void setCityOpenWeather(CityOpenWeather cityOpenWeather) {
        this.cityOpenWeather = cityOpenWeather;
    }
}
