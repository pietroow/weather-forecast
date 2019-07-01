package br.com.hbsis.weatherforecast.controller;

import br.com.hbsis.weatherforecast.model.City;
import br.com.hbsis.weatherforecast.model.dto.CityForm;
import br.com.hbsis.weatherforecast.model.dto.CityOpenWeather;
import br.com.hbsis.weatherforecast.service.CityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
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
    @ApiOperation("")
    public List<CityOpenWeather> findByName(@RequestParam String name) throws IOException {
        return cityService.findByName(name);
    }

    @PostMapping
    @ApiOperation("")
    public ResponseEntity<Object> registerCity(@RequestBody CityForm cityForm){
        City city = cityService.registerCity(cityForm);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(city.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
