package br.com.hbsis.weatherforecast.service;

import br.com.hbsis.weatherforecast.exception.CityOpenWeatherNotFound;
import br.com.hbsis.weatherforecast.model.City;
import br.com.hbsis.weatherforecast.model.dto.CityForm;
import br.com.hbsis.weatherforecast.model.dto.CityOpenWeather;
import br.com.hbsis.weatherforecast.model.dto.response.WeatherResponseDTO;
import br.com.hbsis.weatherforecast.repository.CityOpenWeatherRepository;
import br.com.hbsis.weatherforecast.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Transactional
public class CityService {

    private static final String COMMA = ",";
    private final CityOpenWeatherRepository cityOpenWeatherRepository;
    private final CityRepository cityRepository;

    public CityService(CityOpenWeatherRepository cityOpenWeatherRepository, CityRepository cityRepository) {
        this.cityOpenWeatherRepository = cityOpenWeatherRepository;
        this.cityRepository = cityRepository;
    }

    public List<CityOpenWeather> findByName(String value) {
        CityOpenWeather cityOpenWeather = getFinalValueSearch(value);
        return cityOpenWeatherRepository.findByNameIgnoreCaseContainingAndCountryIgnoreCaseContaining(cityOpenWeather.getName(), cityOpenWeather.getCountry());
    }

    public CityOpenWeather findById(Long id) {
        return cityOpenWeatherRepository.findById(id)
                .orElseThrow(() -> new CityOpenWeatherNotFound(id));
    }

    public City registerCity(CityForm cityForm) {
        CityOpenWeather cityFound = findById(cityForm.getId());
        return cityRepository.save(new City(cityFound));
    }

    public void saveAll(List<CityOpenWeather> cityOpenWeathers) {
        cityOpenWeatherRepository.saveAll(cityOpenWeathers);
    }

    public List<CityOpenWeather> findAll() {
        return cityOpenWeatherRepository.findAll();
    }

    public WeatherResponseDTO getForecastByCityId(String cityId) {
        String apiKey = "eb8b1a9405e659b2ffc78f0a520b1a46";
        String unit = "metric";
        String url = String.format("http://api.openweathermap.org/data/2.5/forecast?id=%s&units=%s&appid=%s", cityId, unit, apiKey);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, WeatherResponseDTO.class);
    }

    private CityOpenWeather getFinalValueSearch(String value) {
        String citySearch = "";
        String countrySearch = "";

        if (!value.contains(COMMA)) {
            citySearch = value;
        } else {
            String[] split = value.split(COMMA);
            citySearch = split[0].replace(" ", "");
            countrySearch = split[1].replace(" ", "");

        }
        return new CityOpenWeather(citySearch, countrySearch);
    }

    public List<City> findAllLocal() {
        return cityRepository.findAll();
    }
}
