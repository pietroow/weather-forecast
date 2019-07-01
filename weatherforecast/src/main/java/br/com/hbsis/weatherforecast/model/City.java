package br.com.hbsis.weatherforecast.model;

import br.com.hbsis.weatherforecast.model.dto.CityOpenWeather;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "city", schema = "weatherforecast")
public class City {

    @Id
    private UUID id;

    @Column(name = "city_id")
    private Long cityId;

    private String name;
    private String country;

    private City() {
        this.id = UUID.randomUUID();
    }

    public City(CityOpenWeather cityOpenWeather) {
        this();
        BeanUtils.copyProperties(cityOpenWeather, this);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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
