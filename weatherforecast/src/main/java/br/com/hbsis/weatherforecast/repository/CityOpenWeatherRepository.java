package br.com.hbsis.weatherforecast.repository;

import br.com.hbsis.weatherforecast.model.CityOpenWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityOpenWeatherRepository extends JpaRepository<CityOpenWeather, Long> {

    List<CityOpenWeather> findByNameIgnoreCaseContainingAndCountryIgnoreCaseContainingOrderByNameAsc(String name, String country);
}
