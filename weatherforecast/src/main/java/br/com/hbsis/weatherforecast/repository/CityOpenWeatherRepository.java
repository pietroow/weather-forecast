package br.com.hbsis.weatherforecast.repository;

import br.com.hbsis.weatherforecast.model.dto.CityOpenWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityOpenWeatherRepository extends JpaRepository<CityOpenWeather, Long> {

    List<CityOpenWeather> findByNameIgnoreCaseContainingAndCountryIgnoreCaseContaining(String name, String country);
}
