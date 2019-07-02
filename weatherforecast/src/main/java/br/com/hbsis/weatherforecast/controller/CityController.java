package br.com.hbsis.weatherforecast.controller;

import br.com.hbsis.weatherforecast.model.City;
import br.com.hbsis.weatherforecast.model.dto.CityForm;
import br.com.hbsis.weatherforecast.model.dto.CityOpenWeather;
import br.com.hbsis.weatherforecast.model.dto.response.WeatherResponseDTO;
import br.com.hbsis.weatherforecast.service.CityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    @ApiOperation("Find a city by name from Open Weather")
    public List<CityOpenWeather> findByName(@RequestParam String name) {
        return cityService.findByName(name);
    }

    @GetMapping("/{cityId}")
    @ApiOperation("Find a city by ID from Open Weather API")
    public CityOpenWeather findById(@PathVariable("cityId") Long cityId) {
        return cityService.findById(cityId);
    }

    @PostMapping
    @ApiOperation("Create a new registry of a city in local database")
    public ResponseEntity<Object> registerCity(@RequestBody CityForm cityForm) {
        City city = cityService.registerCity(cityForm);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(city.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/openWeather/{cityId}")
    public WeatherResponseDTO consumeOpenWeatherApi(@PathVariable("cityId") String cityId) {
        return cityService.getForecastByCityId(cityId);
    }

    @GetMapping("/local")
    public List<City> findAll(){
        return cityService.findAllLocal();
    }

}
