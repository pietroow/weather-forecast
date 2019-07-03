package br.com.hbsis.weatherforecast.controller;

import br.com.hbsis.weatherforecast.model.City;
import br.com.hbsis.weatherforecast.model.dto.CityForm;
import br.com.hbsis.weatherforecast.model.CityOpenWeather;
import br.com.hbsis.weatherforecast.model.dto.response.custom.CustomResponse;
import br.com.hbsis.weatherforecast.service.CityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    @ApiOperation("Find a valid city from Open Weather API by name")
    public List<CityOpenWeather> findByName(@RequestParam String name) {
        return cityService.findByName(name);
    }

    @GetMapping("/{cityId}")
    @ApiOperation("Find a valid city from Open Weather API by ID")
    public CityOpenWeather findById(@PathVariable("cityId") Long cityId) {
        return cityService.findById(cityId);
    }

    @PostMapping
    @ApiOperation("Create a new registry of city in local database using OpenWeather cityId")
    public ResponseEntity<City> registerCity(@Valid @RequestBody CityForm cityForm) {
        City city = cityService.registerCity(cityForm);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(city.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/openWeather/{cityId}")
    @ApiOperation("Get the weather forecast from OpenWeather API by cityId")
    public CustomResponse consumeOpenWeatherApi(@PathVariable("cityId") String cityId) {
        return cityService.getForecastByCityId(cityId);
    }

    @GetMapping("/local")
    @ApiOperation("Get all cities registered in database")
    public List<City> findAll() {
        return cityService.findAllLocal();
    }

    @DeleteMapping("/{cityId}")
    @ApiOperation("Delete a city of database by localId")
    public void deleteCityById(@PathVariable("cityId") UUID id) {
        cityService.deleteById(id);
    }

}
