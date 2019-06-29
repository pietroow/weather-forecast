package br.com.hbsis.weatherforecast.repository;

import br.com.hbsis.weatherforecast.model.dto.CityOpenWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityOpenWeather, Long> {
}
