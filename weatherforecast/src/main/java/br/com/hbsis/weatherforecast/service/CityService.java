package br.com.hbsis.weatherforecast.service;

import br.com.hbsis.weatherforecast.exception.CityOpenWeatherNotFound;
import br.com.hbsis.weatherforecast.model.City;
import br.com.hbsis.weatherforecast.model.dto.CityForm;
import br.com.hbsis.weatherforecast.model.dto.CityOpenWeather;
import br.com.hbsis.weatherforecast.repository.CityRepository;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityService {

    private static final String COMMA = ",";
    private CityRepository cityRepository;
    private List<CityOpenWeather> cities;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
        this.cities = Lists.newArrayList();
    }

    public List<CityOpenWeather> findByName(String value) {
        CityOpenWeather cityOpenWeather = getFinalValueSearch(value);
        return this.cities.stream()
                .filter(obj -> obj.getName().toLowerCase().contains(cityOpenWeather.getName().toLowerCase()))
                .filter(obj -> obj.getCountry().toLowerCase().contains(cityOpenWeather.getCountry().toLowerCase()))
                .sorted(Comparator.comparing(CityOpenWeather::getName))
                .sorted(Comparator.comparing(CityOpenWeather::getCountry))
                .collect(Collectors.toList());
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

    public void loadCities(List<CityOpenWeather> citiesJson) {
        this.cities = citiesJson;
    }

    public City registerCity(CityForm cityForm) {
//        CityOpenWeather cityOpenWeather = this.cities.stream()
//                .filter(city -> city.getId().equals(cityForm.getId()))
//                .findFirst()
//                .orElseThrow(() -> new CityOpenWeatherNotFound(cityForm.getId()));
//
//        return cityRepository.save(new City(cityForm.get));
        return null;
    }

    public void saveAll(List<CityOpenWeather> cityOpenWeathers) {
        cityRepository.saveAll(cityOpenWeathers);
    }
}
