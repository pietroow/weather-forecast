package br.com.hbsis.weatherforecast.service;

import br.com.hbsis.weatherforecast.exception.CityOpenWeatherNotFound;
import br.com.hbsis.weatherforecast.model.City;
import br.com.hbsis.weatherforecast.model.dto.CityForm;
import br.com.hbsis.weatherforecast.model.CityOpenWeather;
import br.com.hbsis.weatherforecast.model.dto.response.custom.CustomResponse;
import br.com.hbsis.weatherforecast.model.dto.response.custom.DayForecast;
import br.com.hbsis.weatherforecast.model.dto.response.openweatherapi.WeatherResponseDTO;
import br.com.hbsis.weatherforecast.repository.CityOpenWeatherRepository;
import br.com.hbsis.weatherforecast.repository.CityRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.com.hbsis.weatherforecast.util.Util.formatSearchInputToClass;

@Service
@Transactional
public class CityService {

    private static final String OPEN_WEATHER_URL = "http://api.openweathermap.org/data/2.5/forecast";
    private static final String API_KEY = "eb8b1a9405e659b2ffc78f0a520b1a46";
    private static final String UNIT = "metric";
    private final CityOpenWeatherRepository cityOpenWeatherRepository;
    private final CityRepository cityRepository;

    public CityService(CityOpenWeatherRepository cityOpenWeatherRepository, CityRepository cityRepository) {
        this.cityOpenWeatherRepository = cityOpenWeatherRepository;
        this.cityRepository = cityRepository;
    }

    public List<CityOpenWeather> findByName(String value) {
        CityOpenWeather cityOpenWeather = formatSearchInputToClass(value);
        List<CityOpenWeather> cities = cityOpenWeatherRepository.findByNameIgnoreCaseContainingAndCountryIgnoreCaseContainingOrderByNameAsc(cityOpenWeather.getName(), cityOpenWeather.getCountry());
        return cities.stream().limit(50).collect(Collectors.toList());
    }

    public CityOpenWeather findById(Long id) {
        return cityOpenWeatherRepository.findById(id)
                .orElseThrow(() -> new CityOpenWeatherNotFound(id));
    }

    public City registerCity(CityForm cityForm) {
        cityRepository.findByCityOpenWeatherId(cityForm.getCityId())
                .ifPresent(city -> {
                    throw new DataIntegrityViolationException("This city is already registered!");
                });
        CityOpenWeather cityFound = findById(cityForm.getCityId());
        City city = new City(cityFound);
        return cityRepository.save(city);
    }

    public List<CityOpenWeather> findAll() {
        return cityOpenWeatherRepository.findAll();
    }

    public CustomResponse getForecastByCityId(String cityId) {
        String url = String.format(OPEN_WEATHER_URL+"?id=%s&units=%s&appid=%s", cityId, UNIT, API_KEY);
        RestTemplate restTemplate = new RestTemplate();
        WeatherResponseDTO response = restTemplate.getForObject(url, WeatherResponseDTO.class);
        return DayForecast.convert(Objects.requireNonNull(response));
    }

    public List<City> findAllLocal() {
        return cityRepository.findAll();
    }

    public void deleteById(UUID id) {
        cityRepository.deleteById(id);
    }

    public void saveAll(List<CityOpenWeather> cityOpenWeathers) {
        cityOpenWeatherRepository.saveAll(cityOpenWeathers);
    }
}
