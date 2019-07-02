package br.com.hbsis.weatherforecast.service;

import br.com.hbsis.weatherforecast.exception.CityOpenWeatherNotFound;
import br.com.hbsis.weatherforecast.model.City;
import br.com.hbsis.weatherforecast.model.dto.CityForm;
import br.com.hbsis.weatherforecast.model.dto.CityOpenWeather;
import br.com.hbsis.weatherforecast.model.dto.custom.DayForecast;
import br.com.hbsis.weatherforecast.model.dto.response.WeatherResponseDTO;
import br.com.hbsis.weatherforecast.repository.CityOpenWeatherRepository;
import br.com.hbsis.weatherforecast.repository.CityRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityService {

    private static final String COMMA = ",";
    public static final String BLANK = " ";
    private final CityOpenWeatherRepository cityOpenWeatherRepository;
    private final CityRepository cityRepository;

    public CityService(CityOpenWeatherRepository cityOpenWeatherRepository, CityRepository cityRepository) {
        this.cityOpenWeatherRepository = cityOpenWeatherRepository;
        this.cityRepository = cityRepository;
    }

    public List<CityOpenWeather> findByName(String value) {
        CityOpenWeather cityOpenWeather = getFinalValueSearch(value);
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
                    throw new DataIntegrityViolationException("This data already exists!");
                });
        CityOpenWeather cityFound = findById(cityForm.getCityId());
        return cityRepository.save(new City(cityFound));
    }

    public void saveAll(List<CityOpenWeather> cityOpenWeathers) {
        cityOpenWeatherRepository.saveAll(cityOpenWeathers);
    }

    public List<CityOpenWeather> findAll() {
        return cityOpenWeatherRepository.findAll();
    }

    public List<DayForecast> getForecastByCityId(String cityId) {
        String apiKey = "eb8b1a9405e659b2ffc78f0a520b1a46";
        String unit = "metric";
        String url = String.format("http://api.openweathermap.org/data/2.5/forecast?id=%s&units=%s&appid=%s", cityId, unit, apiKey);
        RestTemplate restTemplate = new RestTemplate();
        WeatherResponseDTO forObject = restTemplate.getForObject(url, WeatherResponseDTO.class);
        List<DayForecast> convert = new DayForecast().convert(forObject.getDetails());
        return convert;
    }

    private CityOpenWeather getFinalValueSearch(String value) {
        String citySearch = "";
        String countrySearch = "";

        if (!value.contains(COMMA)) {
            citySearch = value;
        } else {
            String[] split = value.split(COMMA);
            citySearch = split[0].replace(" ", "");
            if (!isLastCharCommaOrBlank(value)) {
                countrySearch = split[1].isEmpty() ? "" : split[1].replace(" ", "");
            }
        }
        return new CityOpenWeather(citySearch, countrySearch);
    }

    private boolean isLastCharCommaOrBlank(String value) {
        return value.endsWith(COMMA) || value.endsWith(BLANK);
    }

    public List<City> findAllLocal() {
        return cityRepository.findAll();
    }

    public void deleteById(UUID id) {
        cityRepository.deleteById(id);
    }
}
